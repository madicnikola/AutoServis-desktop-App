/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.validator.impl;

import fon.ai.np.mvnautoserviscommonlib.domen.OpstiDomenskiObjekat;
import fon.ai.np.mvnautoserviscommonlib.domen.Racun;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.validator.Validator;

/**
 *
 * @author Nikola
 */
public class RacunValidator implements Validator {

    @Override
    public void validate(OpstiDomenskiObjekat odo) throws ValidationException {
        Racun racun = (Racun) odo;
        proveriDaliImaStavki(racun);
    }

    private void proveriDaliImaStavki(Racun racun) throws ValidationException {
        if(racun.getListaStavki().isEmpty())
            throw new ValidationException("Lista stavki ne moze biti prazna");
    }

}
