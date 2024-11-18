package com.alexanderdoma.peruinolvidable.config;

import com.alexanderdoma.peruinolvidable.utilies.DatabaseKeysManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private final String URL = DatabaseKeysManager.getProperty("URL");
    private final String USER = DatabaseKeysManager.getProperty("USER");
    private final String PASSWORD = DatabaseKeysManager.getProperty("PASSWORD");
    private static Connector instance;
    
    public static Connector getInstance() {
        if (instance == null){
            synchronized (Connector.class){
                if(instance == null){
                    instance = new Connector();
                }
            }
        }
        return instance;
    }
    
    public Connection getConnection(){
        Connection objConnection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            objConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            return objConnection;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return objConnection;
        }
    }
}
