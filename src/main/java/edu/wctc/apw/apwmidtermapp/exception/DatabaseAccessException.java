/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.exception;

/**
 *
 * @author andre_000
 */
public class DatabaseAccessException extends Exception {

    private static String MSG = "We have a problem accessing the database";
    
    
    public DatabaseAccessException() {
        super(MSG);
    }

    public DatabaseAccessException(String message) {
        super(message);
    }

    public DatabaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseAccessException(Throwable cause) {
        super(cause);
    }

    public DatabaseAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
    
    
    
}
