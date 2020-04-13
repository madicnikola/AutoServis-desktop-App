/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.klijent;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSO;
import fon.ai.np.mvnautoservisserver.validator.impl.ClientValidator;
import java.sql.SQLException;

/**
 *
 * @author Nikola
 */
public class SOZapamtiKlijenta extends OpstaSO {

    public SOZapamtiKlijenta(Klijent klijent) {
        super();
        odo = klijent;
        validator = new ClientValidator();
    }

    @Override
    protected void proveriPreduslov() throws ValidationException {
        validator.validate(odo);
    }

    @Override
    protected void izvrsiOperaciju() throws SQLException {
        odo = db.zapamti(odo);

    }

}
