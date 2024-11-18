package com.alexanderdoma.peruinolvidable.model;

public class DAOException extends Exception {

    public DAOException(String string){
        super(string);
    }
    
    public DAOException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
    
    public DAOException(Throwable thrwbl) {
        super(thrwbl);
    }
    
}
