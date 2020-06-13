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
 * Klasa koja predstavlja autodeo.
 *
 * @author Nikola
 * @version 1.0
 * @see Proizvod
 */
public class Autodeo extends Proizvod {

    /**
     * Ime proizvodjaca.
     */
    private String proizvodjac;

    /**
<<<<<<< HEAD
     * Neparametrizovani konstruktor koji poziva konstruktor nadklase.
=======
     * Prazan konstruktor koji poziva konstruktor nadklase.
>>>>>>> e8c5c9b9e4f6f36f64ce24085962cb7913db4d3a
     *
     * @see Proizvod
     */
    public Autodeo() {
        super();
    }

    /**
     * Konstruktor koji inicializuje sve atribute autodela.
     *
     * @param proizvodID id proizvoda.
     * @param nazivAutodela naziv autodela.
     * @param vrednost vrednost proizvoda.
     * @param proizvodjac naziv proizvodjaca.
     */
    public Autodeo(int proizvodID, String nazivAutodela, double vrednost, String proizvodjac) {
        super(proizvodID, nazivAutodela, vrednost);
        this.proizvodjac = proizvodjac;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    @Override
    public String vratiNazivTabele() {
        return "autodeo";
    }

    @Override
    public String vratiAtributeZaInsert() {
        return "ProizvodID, proizvodjac";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("%d, '%s'", proizvodID, proizvodjac);

    }

    @Override
    public String vratiJoinUpit() {
        return "a JOIN proizvod p ON a.autodeoID = p.proizvodID";

    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return String.format("AutodeoID = %d", proizvodID);
    }

    @Override
    public String vratiUslovZaListuObjekata() {
        return "";
    }

    @Override
    public String vratiID() {
        return "AutodeoID";
    }

    @Override
    public String vratiSetZaIzmenu() {
        return String.format("AutodeoID = %d, proizvodjac = '%s'", proizvodID, proizvodjac);

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
            String proizvodjac = rs.getString("a.proizvodjac");
            lista.add(new Autodeo(proizvodID, naziv, vrednost, proizvodjac));
        }
        return lista;
    }

    @Override
    public OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws SQLException {
        Autodeo autodeo = null;
        if (rs.next()) {
            int proizvodID = rs.getInt("p.proizvodID");
            String naziv = rs.getString("p.Naziv");
            double vrednost = rs.getDouble("p.vrednost");
            String proizvodjac = rs.getString("a.proizvodjac");
            autodeo = new Autodeo(proizvodID, naziv, vrednost, proizvodjac);
        }

        return autodeo;
    }

}
