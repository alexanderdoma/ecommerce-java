package com.alexanderdoma.peruinolvidable.utilies;

import java.util.ResourceBundle;

public class SQLSentencesManager {
    public static String getProperty(String key){
        ResourceBundle objResourceBundle = ResourceBundle.getBundle("database.sentences");
        return objResourceBundle.getString(key);
    }
}
