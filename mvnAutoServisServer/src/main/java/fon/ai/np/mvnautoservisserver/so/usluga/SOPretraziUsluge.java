/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.usluga;

import fon.ai.np.mvnautoserviscommonlib.domen.OpstiDomenskiObjekat;
import fon.ai.np.mvnautoserviscommonlib.domen.Usluga;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class SOPretraziUsluge extends OpstaSO {

    String kriterijum = "";
    List<Usluga> listaUsluga = new ArrayList<>();

    public SOPretraziUsluge(String kriterijum) {
        super();
        this.kriterijum = kriterijum;
        this.odo = new Usluga();
    }

    @Override
    protected void proveriPreduslov() throws ValidationException {

    }

    @Override
    protected void izvrsiOperaciju() throws SQLException {
        List<OpstiDomenskiObjekat> lista = db.vratiSve(odo, kriterijum);
        for (OpstiDomenskiObjekat o : lista) {
            listaUsluga.add((Usluga) o);
        }
    }

    public List<Usluga> getListaUsluga() {
        return listaUsluga;
    }

}
