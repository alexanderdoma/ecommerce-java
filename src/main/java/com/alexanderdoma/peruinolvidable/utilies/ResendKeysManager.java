package com.alexanderdoma.peruinolvidable.utilies;

import java.util.ResourceBundle;

public class ResendKeysManager {
    public static String getProperty(String key){
        ResourceBundle objResourceBundle = ResourceBundle.getBundle("keys.resend");
        return objResourceBundle.getString(key);
    }
}
