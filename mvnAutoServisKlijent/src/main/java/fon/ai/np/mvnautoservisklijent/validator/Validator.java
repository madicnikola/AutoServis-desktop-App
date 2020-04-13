/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.validator;

import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;

/**
 *
 * @author Nikola
 */
public interface Validator {

    public void validate(Object odo) throws ValidationException;
}
