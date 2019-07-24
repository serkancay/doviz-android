package com.serkancay.doviz.util;

import java.text.NumberFormat;
import java.util.Currency;

/**
 * Created by S.Serkan Cay on 24.07.2019
 */

public class CurrencyFormatter {

    public static final String TRY = "TRY";

    public static String format(String currency, float value) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setCurrency(Currency.getInstance(currency));
        return format.format(value);
    }

}
