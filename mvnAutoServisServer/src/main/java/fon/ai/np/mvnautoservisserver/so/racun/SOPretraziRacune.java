/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.racun;

import fon.ai.np.mvnautoserviscommonlib.domen.OpstiDomenskiObjekat;
import fon.ai.np.mvnautoserviscommonlib.domen.Racun;
import fon.ai.np.mvnautoserviscommonlib.domen.StavkaRacuna;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class SOPretraziRacune extends OpstaSO {

    String kriterijum = "";
    List<Racun> listaRacuna = new ArrayList<>();

    public SOPretraziRacune(String kriterijum) {
        super();
        this.kriterijum = kriterijum;
        this.odo = new Racun();
    }

    @Override
    protected void proveriPreduslov() throws ValidationException {

    }

    @Override
    protected void izvrsiOperaciju() throws SQLException {
        List<OpstiDomenskiObjekat> lista = db.vratiSve(odo, kriterijum);
        for (OpstiDomenskiObjekat o : lista) {
            Racun racun = (Racun) o;
            List<OpstiDomenskiObjekat> listaStavki = db.vratiSve(new StavkaRacuna(racun), "");
            List<StavkaRacuna> stavkeRacuna = new LinkedList<>();
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaStavki) {
                stavkeRacuna.add((StavkaRacuna) opstiDomenskiObjekat);
            }
            racun.setListaStavki(stavkeRacuna);
            listaRacuna.add(racun);
        }
    }

    public List<Racun> getListaRacuna() {
        return listaRacuna;
    }

}
