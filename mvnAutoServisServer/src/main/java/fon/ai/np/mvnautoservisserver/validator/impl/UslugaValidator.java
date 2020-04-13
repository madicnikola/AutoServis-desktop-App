/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.validator.impl;

import fon.ai.np.mvnautoserviscommonlib.domen.OpstiDomenskiObjekat;
import fon.ai.np.mvnautoserviscommonlib.domen.Usluga;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.validator.Validator;

/**
 *
 * @author Nikola
 */
public class UslugaValidator implements Validator {

    @Override
    public void validate(OpstiDomenskiObjekat odo) throws ValidationException {
        Usluga usluga = (Usluga) odo;
        proveriNaziv(usluga);
        proveriVrednost(usluga);

    }

    private void proveriVrednost(Usluga usluga) throws ValidationException {
        if (usluga.getVrednost() <= 0) {
            throw new ValidationException("Vrednost mora biti pozitivna");
        }
    }

    private void proveriNaziv(Usluga usluga) throws ValidationException {
        if (usluga.getNaziv().equals("")) {
            throw new ValidationException("Naziv je obavezno polje.");
        }
    }

}
