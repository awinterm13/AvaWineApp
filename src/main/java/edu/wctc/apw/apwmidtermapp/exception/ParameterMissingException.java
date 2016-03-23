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
public class ParameterMissingException extends IllegalArgumentException {

    // I don't remember this having to be static to do this.
    public static String MSG = "A parameter was null, empty or invalid.";
    
    public ParameterMissingException() {
        super(MSG);
    }

    public ParameterMissingException(String s) {
        super(MSG);
    }

    public ParameterMissingException(String message, Throwable cause) {
        super(MSG, cause);
    }

    public ParameterMissingException(Throwable cause) {
        super(cause);
    }
    
  

   
}
