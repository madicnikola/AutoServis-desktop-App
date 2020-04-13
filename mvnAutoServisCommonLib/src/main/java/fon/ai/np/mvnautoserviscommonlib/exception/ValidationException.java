/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoserviscommonlib.exception;

/**
 *
 * @author Nikola
 */
public class ValidationException extends Exception{

    public ValidationException() {
    }
    
    public ValidationException(String message){
        super(message);
    }
}
