package com.alexanderdoma.peruinolvidable.utilies;

import java.util.ResourceBundle;

public class DatabaseKeysManager {
    public static String getProperty(String key){
        ResourceBundle objResourceBundle = ResourceBundle.getBundle("database.keys");
        return objResourceBundle.getString(key);
    }
}
