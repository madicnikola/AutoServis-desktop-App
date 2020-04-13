/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.korisnik;

import fon.ai.np.mvnautoserviscommonlib.domen.Korisnik;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSO;
import fon.ai.np.mvnautoservisserver.util.PasswordHash;
import java.sql.SQLException;

/**
 *
 * @author Nikola
 */
public class SOLogin extends OpstaSO {

    public SOLogin(Korisnik korisnik) {
        super();
        odo = korisnik;
    }

    @Override
    protected void proveriPreduslov() throws ValidationException {

    }

    @Override
    protected void izvrsiOperaciju() throws SQLException {
        Korisnik korisnik = (Korisnik) odo;
        String lozinka = korisnik.getLozinka();
        String lozinkaHash = PasswordHash.getInstance().hash(lozinka);
        korisnik.setLozinka(lozinkaHash);
        odo = db.vratiJedan(korisnik);
    }

}
