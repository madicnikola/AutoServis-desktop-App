/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoserviscommonlib.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Klasa koja predstavlja proizvode i usluge zavedene na fakturi sa uracunatom
 * cenom i kolicinom
 *
 * @author Nikola
 * @version 1.2
 */
public class StavkaRacuna implements OpstiDomenskiObjekat {

    /**
     * Predstavlja racun za koji je stavka vezana.
     *
     * @see Racun
     */
    private transient Racun racun;
    /**
     * Redni broj stavke.
     */
    private int RBStavke;
    /**
     * Vrednost stavke u RSD najcesce.
     */
    private double vrednostStavke;
    /**
     * Kolicina robe navede u stavci.
     */
    private int kolicina;
    /**
     * Proizvod/usluga koja je navedena u stavci.
     *
     * @see Proizvod
     */
    private Proizvod proizvod;

    /**
     * Neparametrizovan konstruktor.
     */
    public StavkaRacuna() {
    }

    /**
     * Konstruktor koji inicializuje racun za koji je stavka vezana.
     *
     * @param racun racun za koji se stavka vezuje.
     */
    public StavkaRacuna(Racun racun) {
        this.racun = racun;
    }

    /**
     * Konstruktor koji inicializuje racun za koji je stavka vezana i redni broj
     * stavke.
     *
     * @param racun racun za koji se stavka vezuje.
     * @param RBStavke redni broj stavke.
     */
    public StavkaRacuna(Racun racun, int RBStavke) {
        this.racun = racun;
        this.RBStavke = RBStavke;
    }

    /**
     * Konstruktor koji inicializuje sve atribute stavke.
     *
     * @param racun racun za koji se stavka vezuje.
     * @param RBStavke redni broj stavke.
     * @param vrednostStavke vrednost proizvoda sa stavke.
     * @param kolicina kolicina proizvoda sa stavke.
     * @param proizvod proizvod ili usluga koja je navedena na stavci racuna.
     */
    public StavkaRacuna(Racun racun, int RBStavke, double vrednostStavke, int kolicina, Proizvod proizvod) {
        this.racun = racun;
        this.RBStavke = RBStavke;
        this.vrednostStavke = vrednostStavke;
        this.kolicina = kolicina;
        this.proizvod = proizvod;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRBStavke() {
        return RBStavke;
    }

    public void setRBStavke(int RBStavke) {
        this.RBStavke = RBStavke;
    }

    public double getVrednostStavke() {
        return vrednostStavke;
    }

    public void setVrednostStavke(double vrednostStavke) {
        this.vrednostStavke = vrednostStavke;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaRacuna";
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getKolicina() {
        return kolicina;
    }

    @Override
    public String vratiAtributeZaInsert() {
        return "RacunID, RBStavke, vrednostStavke, kolicina, ProizvodID";
    }

    @Override

    public String vratiVrednostiZaInsert() {
        return String.format("%d, %d, %f, %d, %d", racun.getRacunID(), RBStavke, vrednostStavke, kolicina, proizvod.getProizvodID());
    }

    @Override
    public String vratiJoinUpit() {
        return "sr JOIN proizvod p ON sr.proizvodID = p.proizvodID JOIN racun r ON r.racunID=sr.racunID";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "redniBroj = " + RBStavke + " AND " + "racunID = " + racun.getRacunID();

    }

    @Override
    public String vratiUslovZaListuObjekata() {
        return String.format(" WHERE sr.racunID = %d", racun.getRacunID());
    }

    @Override
    public String vratiID() {
        return "racunID, redniBroj";
    }

    @Override
    public String vratiSetZaIzmenu() {
        return String.format("vrednostStavke = %f, kolicina = %f, proizvodID = %d", vrednostStavke, kolicina, proizvod.getProizvodID());
    }

    @Override
    public boolean jesteAutoInkrement() {
        return false;
    }

    @Override
    public void postaviIdObjekta(int id) {
        setRBStavke(id);
    }

    @Override
    public List<OpstiDomenskiObjekat> ucitajSve(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int racunID = rs.getInt("r.racunID");
            double ukupnaVrednostRacuna = rs.getDouble("r.ukupnaVrednost");
            boolean storniran = rs.getBoolean("r.storniran");
            Date datum = rs.getDate("r.datum");
            Racun racun = new Racun(racunID, ukupnaVrednostRacuna, storniran, datum, new Korisnik(), new Klijent());

            int proizvodID = rs.getInt("p.proizvodID");
            String nazivProizvoda = rs.getString("p.Naziv");
            double vrednostProizvoda = rs.getDouble("p.vrednost");
            Proizvod proizvod = new Proizvod(proizvodID, nazivProizvoda, vrednostProizvoda);

            int redniBroj = rs.getInt("sr.RBStavke");
            double vrednostStavke = rs.getDouble("sr.vrednostStavke");
            int kolicina = rs.getInt("sr.kolicina");

            StavkaRacuna stavkaRacuna = new StavkaRacuna(racun, redniBroj, vrednostStavke, kolicina, proizvod);
            lista.add(stavkaRacuna);
        }
        return lista;
    }

    @Override
    public OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws SQLException {
        if (rs.next()) {
            int racunID = rs.getInt("r.racunID");
            double ukupnaVrednostRacuna = rs.getDouble("r.ukupnaVrednost");
            boolean storniran = rs.getBoolean("r.storniran");
            Date datum = rs.getDate("r.datum");

            Racun racun = new Racun(racunID, ukupnaVrednostRacuna, storniran, datum, new Korisnik(), new Klijent());

            int proizvodID = rs.getInt("p.proizvodID");
            String nazivProizvoda = rs.getString("p.Naziv");
            double vrednostProizvoda = rs.getDouble("p.vrednost");

            Proizvod proizvod = new Proizvod(proizvodID, nazivProizvoda, vrednostProizvoda);

            int redniBroj = rs.getInt("sr.RBStavke");
            double vrednostStavke = rs.getDouble("sr.vrednostStavke");
            int kolicina = rs.getInt("sr.kolicina");

            return new StavkaRacuna(racun, redniBroj, vrednostStavke, kolicina, proizvod);

        }
        return null;
    }
}
