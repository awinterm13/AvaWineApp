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
public class DaoIsNullException extends NullPointerException {
  
    public static String MSG = "Dao object is Null.";
    
    public DaoIsNullException() {
        super(MSG);
    }

    public DaoIsNullException(String s) {
        super(MSG);
    }

   
}
