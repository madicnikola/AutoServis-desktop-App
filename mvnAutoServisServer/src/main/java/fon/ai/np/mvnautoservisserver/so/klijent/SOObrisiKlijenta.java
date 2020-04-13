/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.klijent;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSO;
import java.sql.SQLException;

/**
 *
 * @author Nikola
 */
public class SOObrisiKlijenta extends OpstaSO {

    public SOObrisiKlijenta(Klijent klijent) {
        super();
        odo = klijent;
    }

    @Override
    protected void proveriPreduslov() throws ValidationException {
    }

    @Override
    protected void izvrsiOperaciju() throws SQLException {
        db.obrisi(odo);
    }

}
