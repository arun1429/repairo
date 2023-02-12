package com.app.repairo.app.utils;

import java.text.DecimalFormat;

public class DecimalForaterUtils {
    static String format="0.00";
    static String format2="00";
    public static String changeFormate(double price){
        DecimalFormat decimalFormat=new DecimalFormat(format);
        String strPrice=decimalFormat.format(price);
        return strPrice;
    }

    public static String changeFormat2(String price){
        DecimalFormat decimalFormat=new DecimalFormat(format2);
        String strPrice = decimalFormat.format(price);
        return strPrice;
    }
}

