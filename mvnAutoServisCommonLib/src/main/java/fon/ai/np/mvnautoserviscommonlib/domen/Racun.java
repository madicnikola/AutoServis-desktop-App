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
 *
 * @author Nikola
 */
public class Racun implements OpstiDomenskiObjekat {

    private int racunID;
    private double ukupnaVrednost;
    private boolean storniran;
    private Date datum;
    private Korisnik korisnik;
    private Klijent klijent;
    private List<StavkaRacuna> listaStavki;

    public Racun() {
        listaStavki = new ArrayList<>();
    }

    public Racun(int racunID, double ukupnaVrednost, boolean storniran, Date datum, Korisnik korisnik, Klijent klijent) {
        this();
        this.racunID = racunID;
        this.ukupnaVrednost = ukupnaVrednost;
        this.storniran = storniran;
        this.datum = datum;
        this.korisnik = korisnik;
        this.klijent = klijent;
    }

    public Racun(int racunID, double ukupnaVrednost, boolean storniran, Date datum, Korisnik korisnik, Klijent klijent, List<StavkaRacuna> listaStavki) {
        this.racunID = racunID;
        this.ukupnaVrednost = ukupnaVrednost;
        this.storniran = storniran;
        this.datum = datum;
        this.korisnik = korisnik;
        this.klijent = klijent;
        this.listaStavki = listaStavki;
    }

    public int getRacunID() {
        return racunID;
    }

    public void setRacunID(int racunID) {
        this.racunID = racunID;
    }

    public double getUkupnaVrednost() {
        return ukupnaVrednost;
    }

    public void setUkupnaVrednost(double ukupnaVrednost) {
        this.ukupnaVrednost = ukupnaVrednost;
    }

    public boolean isStorniran() {
        return storniran;
    }

    public void setStorniran(boolean storniran) {
        this.storniran = storniran;
    }

    public List<StavkaRacuna> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkaRacuna> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getDatum() {
        return datum;
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
        final Racun other = (Racun) obj;
        if (this.racunID != other.racunID) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiNazivTabele() {
        return "racun";
    }

    @Override
    public String vratiAtributeZaInsert() {
        return "racunID, ukupnaVrednost, storniran, datum, klijentID, korisnikID";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        java.sql.Date dateDB = new java.sql.Date(datum.getTime());
        return String.format("%d, %f, %b, '%s', %d, %d", racunID, ukupnaVrednost, storniran, dateDB, klijent.getKlijentID(), korisnik.getKorisnikID());
    }

    @Override
    public String vratiJoinUpit() {
        return "r JOIN klijent kl ON r.klijentID = kl.klijentID JOIN korisnik ko ON r.korisnikID = ko.korisnikID";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return String.format("racunID = %d", racunID);
    }

    @Override
    public String vratiUslovZaListuObjekata() {
        return "";
    }

    @Override
    public String vratiID() {
        return "RacunID";
    }

    @Override
    public String vratiSetZaIzmenu() {
        return String.format("storniran = %d", isStorniran() ? 1 : 0);
    }

    @Override
    public boolean jesteAutoInkrement() {
        return true;
    }

    @Override
    public void postaviIdObjekta(int id) {
        setRacunID(id);
    }

    @Override
    public List<OpstiDomenskiObjekat> ucitajSve(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int racunID = rs.getInt("r.racunID");
            double ukupnaVrednost = rs.getDouble("r.ukupnaVrednost");
            boolean storniran = rs.getBoolean("r.storniran");
            Date datum = rs.getDate("r.datum");

            int klijentID = rs.getInt("kl.KlijentID");
            String ime = rs.getString("kl.Ime");
            String prezime = rs.getString("kl.Prezime");
            String adresa = rs.getString("kl.adresa");
            String jmbg = rs.getString("kl.jmbg");
            String telefon = rs.getString("kl.telefon");
            String email = rs.getString("kl.email");
            Klijent klijent = new Klijent(klijentID, ime, prezime, adresa, jmbg, telefon, email);

            int korisnikID = rs.getInt("ko.KorisnikID");
            jmbg = rs.getString("ko.jmbg");
            ime = rs.getString("ko.Ime");
            prezime = rs.getString("ko.Prezime");
            String korisnickoIme = rs.getString("ko.KorisnickoIme");
            String lozinka = rs.getString("ko.Lozinka");
            Korisnik korisnik = new Korisnik(korisnikID, jmbg, ime, prezime, korisnickoIme, lozinka);

            Racun r = new Racun(racunID, ukupnaVrednost, storniran, datum, korisnik, klijent);
            lista.add(r);
        }
        return lista;
    }

    @Override
    public OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws SQLException {
        if (rs.next()) {
            int racunID = rs.getInt("r.racunID");
            double ukupnaVrednost = rs.getDouble("r.ukupnaVrednost");
            boolean storniran = rs.getBoolean("r.storniran");
            Date datum = rs.getDate("r.datum");

            int klijentID = rs.getInt("kl.KlijentID");
            String ime = rs.getString("kl.Ime");
            String prezime = rs.getString("kl.Prezime");
            String adresa = rs.getString("kl.adresa");
            String jmbg = rs.getString("kl.jmbg");
            String telefon = rs.getString("kl.telefon");
            String email = rs.getString("kl.email");
            Klijent klijent = new Klijent(klijentID, jmbg, ime, prezime, jmbg, telefon, email);

            int korisnikID = rs.getInt("ko.KorisnikID");
            jmbg = rs.getString("ko.jmbg");
            ime = rs.getString("ko.Ime");
            prezime = rs.getString("ko.Prezime");
            String korisnickoIme = rs.getString("ko.KorisnickoIme");
            String lozinka = rs.getString("ko.Lozinka");
            Korisnik korisnik = new Korisnik(korisnikID, jmbg, ime, prezime, korisnickoIme, lozinka);

            return new Racun(racunID, ukupnaVrednost, storniran, datum, korisnik, klijent);

        }
        return null;
    }

}
