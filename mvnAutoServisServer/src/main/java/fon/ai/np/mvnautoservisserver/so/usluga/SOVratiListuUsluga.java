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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class SOVratiListuUsluga extends OpstaSO {

    List<Usluga> listaUsluga = new LinkedList<>();

    public SOVratiListuUsluga(Usluga usluga) {
        super();
        odo = usluga;
    }

    @Override
    protected void proveriPreduslov() throws ValidationException {

    }

    @Override
    protected void izvrsiOperaciju() throws SQLException {
        List<OpstiDomenskiObjekat> lista = db.vratiSve(odo, "");
        for (OpstiDomenskiObjekat o : lista) {
            listaUsluga.add((Usluga) o);
        }
    }

    public List<Usluga> getListaUsluga() {
        return listaUsluga;
    }

}
