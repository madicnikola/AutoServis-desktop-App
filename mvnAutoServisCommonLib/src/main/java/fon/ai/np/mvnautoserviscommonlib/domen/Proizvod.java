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
 * Klasa koja predstavlja proizvod u sirem smislu koji moze biti proizvod ili
 * usluga.
 *
 * @author Nikola
 * @version 1.0
 */
public class Proizvod implements OpstiDomenskiObjekat {

    /**
     * jedinstveni id proizvoda.
     */
    protected int proizvodID;
    /**
     * Naziv proizvoda.
     */
    protected String naziv;
    /**
     * vrednost proizvoda u RSD valuti.
     */
    protected double vrednost;

    /**
     * Neparametrizovani konstruktor.
     */
    public Proizvod() {
    }

    /**
     * Konstruktor koji inicializuje sve atribute klase proizvod.
     *
     * @param proizvodID ID broj proizvoda.
     * @param naziv naziv proizvoda.
     * @param vrednost vrednost proizvoda.
     */
    public Proizvod(int proizvodID, String naziv, double vrednost) {
        this();
        this.proizvodID = proizvodID;
        this.naziv = naziv;
        this.vrednost = vrednost;
    }

    public Proizvod(Usluga usluga) {
        this(usluga.getProizvodID(), usluga.getNaziv(), usluga.getVrednost());
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "proizvod";
    }

    @Override
    public String vratiAtributeZaInsert() {
        return "naziv, vrednost";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("'%s', %f", naziv, vrednost);
    }

    @Override
    public String vratiJoinUpit() {
        return "";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return String.format("ProizvodID = %d", proizvodID);
    }

    @Override
    public String vratiUslovZaListuObjekata() {
        return "";
    }

    @Override
    public String vratiID() {
        return "ProizvodID";
    }

    @Override
    public String vratiSetZaIzmenu() {
        return String.format("naziv = '%s', vrednost = %f", naziv, vrednost);

    }

    @Override
    public boolean jesteAutoInkrement() {
        return true;
    }

    @Override
    public void postaviIdObjekta(int id) {
        setProizvodID(id);
    }

    @Override
    public List<OpstiDomenskiObjekat> ucitajSve(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int proizvodID = rs.getInt("proizvodID");
            String naziv = rs.getString("naziv");
            double vrednost = rs.getDouble("vrednost");
            lista.add(new Proizvod(proizvodID, naziv, vrednost));
        }

        return lista;
    }

    @Override
    public OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws SQLException {
        Proizvod proizvod = null;
        if (rs.next()) {
            int proizvodID = rs.getInt("proizvodID");
            String naziv = rs.getString("Naziv");
            double vrednost = rs.getDouble("vrednost");
            proizvod = new Proizvod(proizvodID, naziv, vrednost);
        }

        return proizvod;
    }

    public int getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(int proizvodID) {
        this.proizvodID = proizvodID;
    }

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proizvod other = (Proizvod) obj;
        if (this.proizvodID != other.proizvodID) {
            return false;
        }
        return true;
    }

}
