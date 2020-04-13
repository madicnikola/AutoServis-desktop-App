/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.racun;

import fon.ai.np.mvnautoserviscommonlib.domen.OpstiDomenskiObjekat;
import fon.ai.np.mvnautoserviscommonlib.domen.Racun;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSO;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class SOVratiListuRacuna extends OpstaSO {

    List<Racun> listaRacuna = new LinkedList<>();

    public SOVratiListuRacuna(Racun racun) {
        super();
        odo = racun;
    }

    @Override
    protected void proveriPreduslov() throws ValidationException {

    }

    @Override
    protected void izvrsiOperaciju() throws SQLException {
        List<OpstiDomenskiObjekat> lista = db.vratiSve(odo, "");
        for (OpstiDomenskiObjekat o : lista) {
            listaRacuna.add((Racun) o);
        }
    }

    public List<Racun> getListaRacuna() {
        return listaRacuna;
    }

}
