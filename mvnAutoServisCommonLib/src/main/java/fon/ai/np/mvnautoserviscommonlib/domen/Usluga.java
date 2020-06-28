/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoserviscommonlib.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa koja predstavlja uslugu koja se pruza klijentu autoservisa.
 *
 * @author Nikola
 * @version 1.1
 * @see Proizvod
 */
public class Usluga extends Proizvod {

    /**
     * Tekstualni opis procesa pruzanja usluge.
     */
    private String opisUsluge;

    /**
     * Neparametrizovani konstruktor.
     */
    public Usluga() {
    }

    /**
     * Konstruktor koji inicializuje sve atribute klase Usluga.
     *
     * @param proizvodID ID usluge.
     * @param naziv naziv usluge.
     * @param vrednost vrednost usluge.
     * @param opisUsluge Tekstualni opis procesa pruzanja usluge.
     */
    public Usluga(int proizvodID, String naziv, double vrednost, String opisUsluge) {
        super(proizvodID, naziv, vrednost);
        this.opisUsluge = opisUsluge;
    }

    public String getOpisUsluge() {
        return opisUsluge;
    }

    public void setOpisUsluge(String opisUsluge) {
        this.opisUsluge = opisUsluge;
    }

    @Override
    public String vratiNazivTabele() {
        return "usluga";
    }

    @Override
    public String vratiAtributeZaInsert() {
        return "UslugaID, opisUsluge";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("%d, '%s'", proizvodID, opisUsluge);
    }

    @Override
    public String vratiJoinUpit() {
        return "u JOIN proizvod p ON u.uslugaID = p.proizvodID";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "UslugaID = " + proizvodID;
    }

    @Override
    public String vratiUslovZaListuObjekata() {
        return "";
    }

    @Override
    public String vratiID() {
        return "uslugaID";
    }

    @Override
    public String vratiSetZaIzmenu() {
        return String.format("opisUsluge = '%s'", opisUsluge);
    }

    @Override
    public boolean jesteAutoInkrement() {
        return false;
    }

    @Override
    public void postaviIdObjekta(int id) {
        setProizvodID(id);
    }

    @Override
    public List<OpstiDomenskiObjekat> ucitajSve(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int proizvodID = rs.getInt("p.proizvodID");
            String naziv = rs.getString("p.Naziv");
            double vrednost = rs.getDouble("p.vrednost");
            String opisUsluge = rs.getString("u.opisUsluge");
            lista.add(new Usluga(proizvodID, naziv, vrednost, opisUsluge));
        }
        return lista;
    }

    @Override
    public OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws SQLException {
        Usluga usluga = null;
        if (rs.next()) {
            int proizvodID = rs.getInt("p.proizvodID");
            String naziv = rs.getString("p.Naziv");
            double vrednost = rs.getDouble("p.vrednost");
            String opisUsluge = rs.getString("u.opisUsluge");
            usluga = new Usluga(proizvodID, naziv, vrednost, opisUsluge);
        }
        return usluga;

    }
}
