/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.validator.implementations;

import fon.ai.np.mvnautoserviscommonlib.domen.Racun;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisklijent.validator.Validator;

/**
 *
 * @author Nikola
 */
public class RacunValidator implements Validator {

    public RacunValidator() {
    }

    @Override
    public void validate(Object odo) throws ValidationException {
        Racun racun = (Racun) odo;
    }

}
