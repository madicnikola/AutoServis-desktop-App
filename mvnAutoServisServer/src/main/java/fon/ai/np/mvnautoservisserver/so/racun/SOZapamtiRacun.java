/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.racun;

import fon.ai.np.mvnautoserviscommonlib.domen.Racun;
import fon.ai.np.mvnautoserviscommonlib.domen.StavkaRacuna;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSO;
import fon.ai.np.mvnautoservisserver.validator.impl.RacunValidator;
import java.sql.SQLException;

/**
 *
 * @author Nikola
 */
public class SOZapamtiRacun extends OpstaSO {

    public SOZapamtiRacun(Racun racun) {
        super();
        odo = racun;
        validator = new RacunValidator();
    }

    @Override
    protected void proveriPreduslov() throws ValidationException {
        validator.validate(odo);
    }

    @Override
    protected void izvrsiOperaciju() throws SQLException {
        db.zapamti(odo);
        Racun racun = (Racun) odo;
        for (StavkaRacuna stavkaRacuna : racun.getListaStavki()) {
            stavkaRacuna.setRacun(racun);
            db.zapamti(stavkaRacuna);
        }
    }

}
