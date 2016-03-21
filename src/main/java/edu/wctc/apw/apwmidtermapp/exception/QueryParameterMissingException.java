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
public class QueryParameterMissingException extends IllegalArgumentException {

    // I don't remember this having to be static to do this.
    public static String MSG = "A Query Parameter was null, empty or invalid.";
    
    public QueryParameterMissingException() {
        super(MSG);
    }

    public QueryParameterMissingException(String s) {
        super(MSG);
    }

    public QueryParameterMissingException(String message, Throwable cause) {
        super(MSG, cause);
    }

    public QueryParameterMissingException(Throwable cause) {
        super(cause);
    }
    
  

   
}
