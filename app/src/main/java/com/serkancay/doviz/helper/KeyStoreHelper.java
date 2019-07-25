package com.serkancay.doviz.helper;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import androidx.annotation.Nullable;
import java.security.KeyStore;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Created by S.Serkan Cay on 25.07.2019
 */

public class KeyStoreHelper {

    @Nullable
    private static SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            keyGenerator.init(new KeyGenParameterSpec.Builder("Key",
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            return keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String text) {
        String encryptedText = "";
        try {
            SecretKey secretKey = generateSecretKey();
            Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC +
                    "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptionIv = cipher.getIV();
            byte[] textBytes = text.getBytes("UTF-8");
            byte[] encryptedTextBytes = cipher.doFinal(textBytes);
            encryptedText = Base64.encodeToString(encryptedTextBytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return encryptedText;
    }

}
