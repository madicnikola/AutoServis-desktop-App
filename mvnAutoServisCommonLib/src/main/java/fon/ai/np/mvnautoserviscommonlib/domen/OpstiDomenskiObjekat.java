/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoserviscommonlib.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nikola
 */
public interface OpstiDomenskiObjekat extends Serializable {

    String vratiNazivTabele();

    String vratiAtributeZaInsert();

    String vratiVrednostiZaInsert();

    String vratiJoinUpit();

    String vratiUslovZaNadjiSlog();

    String vratiUslovZaListuObjekata();

    String vratiID();

    String vratiSetZaIzmenu();

    boolean jesteAutoInkrement();

    void postaviIdObjekta(int id);

    List<OpstiDomenskiObjekat> ucitajSve(ResultSet rs) throws SQLException;

    OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws SQLException;
}
