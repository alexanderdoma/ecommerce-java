package com.alexanderdoma.peruinolvidable.utilies;

import java.util.ResourceBundle;

public class PaypalKeysManager {
    public static String getProperty(String key){
        ResourceBundle objResourceBundle = ResourceBundle.getBundle("keys.paypal");
        return objResourceBundle.getString(key);
    }
}
