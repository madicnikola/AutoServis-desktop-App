/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.proizvod;

import fon.ai.np.mvnautoserviscommonlib.domen.Proizvod;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSO;
import java.sql.SQLException;

/**
 *
 * @author Nikola
 */
public class SOObrisiProizvod extends OpstaSO {

    public SOObrisiProizvod(Proizvod proizvod) {
        super();
        odo = proizvod;
    }

    @Override
    protected void proveriPreduslov() throws ValidationException {
    }

    @Override
    protected void izvrsiOperaciju() throws SQLException {
        db.obrisi(odo);
    }

}
