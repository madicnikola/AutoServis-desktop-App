/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.validator.impl;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.domen.OpstiDomenskiObjekat;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.validator.Validator;

/**
 *
 * @author Nikola
 */
public class ClientValidator implements Validator {

    @Override
    public void validate(OpstiDomenskiObjekat odo) throws ValidationException {
        Klijent klijent = (Klijent) odo;
        proveriEmail(klijent);
    }

    private void proveriEmail(Klijent klijent) throws ValidationException {
        if (!klijent.getEmail().contains("@")) {
            throw new ValidationException("Email mora sadrzati @");
        }
    }

}
