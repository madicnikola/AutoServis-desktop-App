/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.proizvod;

import fon.ai.np.mvnautoserviscommonlib.domen.Proizvod;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSO;
import fon.ai.np.mvnautoservisserver.validator.impl.ProizvodValidator;
import java.sql.SQLException;

/**
 *
 * @author Nikola
 */
public class SOZapamtiProizvod extends OpstaSO {

    public SOZapamtiProizvod(Proizvod proizvod) {
        super();
        odo = proizvod;
        validator = new ProizvodValidator();
    }

    @Override
    protected void proveriPreduslov() throws ValidationException {
        validator.validate(odo);
    }

    @Override
    protected void izvrsiOperaciju() throws SQLException {
        db.zapamti(odo);
    }

}
