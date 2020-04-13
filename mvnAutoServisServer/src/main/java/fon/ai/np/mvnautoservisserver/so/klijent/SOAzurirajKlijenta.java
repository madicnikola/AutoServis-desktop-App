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
public class SOAzurirajKlijenta extends OpstaSO {

    public SOAzurirajKlijenta(Klijent klijent) {
        super();
        odo = klijent;
        validator = new ClientValidator();
    }

    protected void proveriPreduslov() throws ValidationException {
        validator.validate(odo);
    }

    protected void izvrsiOperaciju() throws SQLException {
        db.izmeni(odo);
    }
}
