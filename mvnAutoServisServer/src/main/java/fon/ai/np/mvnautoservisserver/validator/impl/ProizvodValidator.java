/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.validator.impl;

import fon.ai.np.mvnautoserviscommonlib.domen.OpstiDomenskiObjekat;
import fon.ai.np.mvnautoserviscommonlib.domen.Proizvod;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.validator.Validator;

/**
 *
 * @author Nikola
 */
public class ProizvodValidator implements Validator {

    @Override
    public void validate(OpstiDomenskiObjekat odo) throws ValidationException {
        Proizvod proizvod = (Proizvod) odo;
        proveriNaziv(proizvod);
        proveriVrednost(proizvod);
    }

    private void proveriVrednost(Proizvod proizvod) throws ValidationException {
        if (proizvod.getVrednost() <= 0) {
            throw new ValidationException("Vrednost mora biti pozitivna");
        }
    }

    private void proveriNaziv(Proizvod proizvod) throws ValidationException {
        if (proizvod.getNaziv().equals("")) {
            throw new ValidationException("Naziv je obavezno polje.");
        }
    }
}
