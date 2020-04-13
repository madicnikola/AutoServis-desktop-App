/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.klijent;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.domen.OpstiDomenskiObjekat;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSO;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class SOVratiListuKlijenata extends OpstaSO {

    List<Klijent> listaKlijenata = new LinkedList<>();

    public SOVratiListuKlijenata(Klijent klijent) {
        super();
        odo = klijent;
    }

    @Override
    protected void proveriPreduslov() throws ValidationException {

    }

    @Override
    protected void izvrsiOperaciju() throws SQLException {
        List<OpstiDomenskiObjekat> lista = db.vratiSve(odo, "");
        for (OpstiDomenskiObjekat o : lista) {
            listaKlijenata.add((Klijent) o);
        }
    }

    public List<Klijent> getListaKlijenata() {
        return listaKlijenata;
    }

}
