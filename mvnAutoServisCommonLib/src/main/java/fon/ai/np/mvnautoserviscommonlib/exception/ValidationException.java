/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoserviscommonlib.exception;

/**
 * Izuzetak koji se baca usled ne uspele validacije. 
 * @author Nikola
 * @version 1.0
 */
public class ValidationException extends Exception{

    public ValidationException() {
    }
    
    public ValidationException(String message){
        super(message);
    }
}
