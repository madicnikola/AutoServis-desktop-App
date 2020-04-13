/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoserviscommonlib.domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Nikola
 */
public class Korisnik implements OpstiDomenskiObjekat {

    private int korisnikID;
    private String JMBG;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private List<Racun> racuni;

    public Korisnik() {
        racuni = new LinkedList<>();
    }

    public Korisnik(int korisnikID, String JMBG, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.korisnikID = korisnikID;
        this.JMBG = JMBG;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public Korisnik(int korisnikID, String JMBG, String ime, String prezime, String korisnickoIme, String lozinka, List<Racun> racuni) {
        this.korisnikID = korisnikID;
        this.JMBG = JMBG;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.racuni = racuni;
    }

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setRacuni(List<Racun> racuni) {
        this.racuni = racuni;
    }

    public List<Racun> getRacuni() {
        return racuni;
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
        final Korisnik other = (Korisnik) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        if (!Objects.equals(this.lozinka, other.lozinka)) {
            return false;
        }
        return true;
    }

    @Override
    public String vratiNazivTabele() {
        return "korisnik";
    }

    @Override
    public String vratiAtributeZaInsert() {
        return "JMBG, Ime, Prezime, KorisnickoIme, Lozinka";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + JMBG + "', '" + ime + "', '" + prezime + "', '" + korisnickoIme + "', '"
                + korisnickoIme + "', '" + lozinka + "'";
    }

    @Override
    public String vratiJoinUpit() {
        return "";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "KorisnickoIme = '" + korisnickoIme + "' AND Lozinka ='" + lozinka + "'";
    }

    @Override
    public String vratiID() {
        return "KorisnikID";
    }

    @Override
    public String vratiSetZaIzmenu() {
        return "Ime = '" + ime + "', Prezime = '" + prezime + "', KorisnickoIme = '" + korisnickoIme
                + "', Lozinka = '" + lozinka + "'";
    }

    @Override
    public List<OpstiDomenskiObjekat> ucitajSve(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int korisnikID = rs.getInt("KorisnikID");
            String jmbg = rs.getString("jmbg");
            String ime = rs.getString("Ime");
            String prezime = rs.getString("Prezime");
            String korisnickoIme = rs.getString("KorisnickoIme");
            String lozinka = rs.getString("Lozinka");

            Korisnik korisnik = new Korisnik(korisnikID, jmbg, ime, prezime, korisnickoIme, lozinka);
            lista.add(korisnik);
        }
        return lista;
    }

    @Override
    public OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws SQLException {
        Korisnik korisnik = null;
        if (rs.next()) {
            int korisnikID = rs.getInt("korisnikID");
            String jmbg = rs.getString("jmbg");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String korisnickoIme = rs.getString("korisnickoime");
            String lozinka = rs.getString("lozinka");

            korisnik = new Korisnik(korisnikID, jmbg, ime, prezime, korisnickoIme, lozinka);
        }
        return korisnik;
    }

    @Override
    public String vratiUslovZaListuObjekata() {
        return "";
    }

    @Override
    public boolean jesteAutoInkrement() {
        return true;
    }

    @Override
    public void postaviIdObjekta(int id) {
        setKorisnikID(id);
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

}
