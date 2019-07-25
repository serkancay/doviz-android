package com.serkancay.doviz.util.keystore;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import com.serkancay.doviz.BuildConfig;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Calendar;
import javax.crypto.KeyGenerator;
import javax.security.auth.x500.X500Principal;

/**
 * Created by S.Serkan Cay on 25.07.2019
 */

public class CryptKeyGenerator {

    public static final String ANDROID_KEY_STORE = "AndroidKeyStore";

    public static final String KEY_ALIAS = BuildConfig.ALIAS;

    private static final String KEY_STORE_FILE_NAME = BuildConfig.KEY_STORE_FILE_NAME;

    private static final String KEY_STORE_PASSWORD = BuildConfig.KEY_STORE_PASSWORD;

    @TargetApi(Build.VERSION_CODES.M)
    static SecurityKey generateSecretKey(KeyStore keyStore) {
        try {
            if (!keyStore.containsAlias(KEY_ALIAS)) {
                KeyGenerator keyGenerator =
                        KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE);
                keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_ALIAS,
                        KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT).setBlockModes(
                        KeyProperties.BLOCK_MODE_GCM)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                        .setRandomizedEncryptionRequired(false)
                        .build());
                return new SecurityKey(keyGenerator.generateKey());
            }
        } catch (KeyStoreException | NoSuchProviderException | NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
        }
        try {
            final KeyStore.SecretKeyEntry entry =
                    (KeyStore.SecretKeyEntry) keyStore.getEntry(KEY_ALIAS, null);
            return new SecurityKey(entry.getSecretKey());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException e) {
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    static SecurityKey generateKeyPairPreM(Context context, KeyStore keyStore) {
        try {
            if (!keyStore.containsAlias(KEY_ALIAS)) {
                Calendar start = Calendar.getInstance();
                Calendar end = Calendar.getInstance();
                //1 Year validity
                end.add(Calendar.YEAR, 1);

                KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(context).setAlias(KEY_ALIAS)
                        .setSubject(new X500Principal("CN=" + KEY_ALIAS))
                        .setSerialNumber(BigInteger.TEN)
                        .setStartDate(start.getTime())
                        .setEndDate(end.getTime())
                        .build();

                KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", ANDROID_KEY_STORE);
                kpg.initialize(spec);
                kpg.generateKeyPair();
            }
        } catch (KeyStoreException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchProviderException e) {
        }

        try {
            final KeyStore.PrivateKeyEntry entry =
                    (KeyStore.PrivateKeyEntry) keyStore.getEntry(KEY_ALIAS, null);
            return new SecurityKey(
                    new KeyPair(entry.getCertificate().getPublicKey(), entry.getPrivateKey()));
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException e) {
        }
        return null;
    }

    static SecurityKey generateSecretKeyPre18(Context context) {

        try {
            KeyStore androidCAStore = KeyStore.getInstance(KeyStore.getDefaultType());

            char[] password = KEY_STORE_PASSWORD.toCharArray();

            boolean isKeyStoreLoaded = loadKeyStore(context, androidCAStore, password);
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(password);
            if (!isKeyStoreLoaded || !androidCAStore.containsAlias(KEY_ALIAS)) {
                //Create and save new secret key
                saveMyKeystore(context, androidCAStore, password, protParam);
            }

            // Fetch Secret Key
            KeyStore.SecretKeyEntry pkEntry =
                    (KeyStore.SecretKeyEntry) androidCAStore.getEntry(KEY_ALIAS, protParam);

            return new SecurityKey(pkEntry.getSecretKey());
        } catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException | UnrecoverableEntryException e) {
        }
        return null;
    }

    private static boolean loadKeyStore(Context context, KeyStore androidCAStore, char[] password) {
        java.io.FileInputStream fis;
        try {
            fis = context.openFileInput(KEY_STORE_FILE_NAME);
        } catch (FileNotFoundException e) {
            return false;
        }
        try {
            androidCAStore.load(fis, password);
            return true;
        } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
        }
        return false;
    }

    private static void saveMyKeystore(Context context, KeyStore androidCAStore, char[] password,
            KeyStore.ProtectionParameter protParam)
            throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException {

        javax.crypto.SecretKey mySecretKey = KeyGenerator.getInstance("AES").generateKey();

        KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(mySecretKey);
        androidCAStore.load(null);
        androidCAStore.setEntry(KEY_ALIAS, skEntry, protParam);
        java.io.FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(KEY_STORE_FILE_NAME, Context.MODE_PRIVATE);

            androidCAStore.store(fos, password);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

}
