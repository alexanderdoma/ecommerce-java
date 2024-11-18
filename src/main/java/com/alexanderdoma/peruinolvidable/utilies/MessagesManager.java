package com.alexanderdoma.peruinolvidable.utilies;

import java.util.ResourceBundle;

public class MessagesManager {
    public static String getProperty(String key){
        ResourceBundle objResourceBundle = ResourceBundle.getBundle("database.messages");
        return objResourceBundle.getString(key);
    }
}
