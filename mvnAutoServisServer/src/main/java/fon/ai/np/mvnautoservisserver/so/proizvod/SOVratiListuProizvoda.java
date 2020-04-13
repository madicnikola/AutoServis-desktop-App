/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.proizvod;

import fon.ai.np.mvnautoserviscommonlib.domen.OpstiDomenskiObjekat;
import fon.ai.np.mvnautoserviscommonlib.domen.Proizvod;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSO;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class SOVratiListuProizvoda extends OpstaSO {

    List<Proizvod> listaProizvoda = new LinkedList<>();

    public SOVratiListuProizvoda(Proizvod proizvod) {
        super();
        odo = proizvod;
    }

    @Override
    protected void proveriPreduslov() throws ValidationException {

    }

    @Override
    protected void izvrsiOperaciju() throws SQLException {
        List<OpstiDomenskiObjekat> lista = db.vratiSve(odo, "");
        for (OpstiDomenskiObjekat o : lista) {
            listaProizvoda.add((Proizvod) o);
        }
    }

    public List<Proizvod> getListaProizvoda() {
        return listaProizvoda;
    }

}
