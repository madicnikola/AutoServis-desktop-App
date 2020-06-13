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

/**
 * Klasa koja predstavlja klijenta koji koristi usluge autoservisa.
 *
 * @author Nikola
 * @version 1.0
 */
public class Klijent implements OpstiDomenskiObjekat {

    /**
     * ID broj klijenta.
     */
    private int klijentID;
    /**
     * ime klijenta.
     */
    private String ime;
    /**
     * prezime klijenta.
     */
    private String prezime;
    /**
     * adresa stanovanja.
     */
    private String adresa;
    /**
     * jedinstveni maticni broj sadrzi 13 cifara.
     */
    private String JMBG;

    /**
     * mobilni telefon.
     */
    private String telefon;
    /**
     * elektronska posta.
     */
    private String email;
    /**
     * lista racuna izdatih klijentu.
     */
    private List<Racun> racuni;

    /**
     * Neparametrizovan konstruktor koji inicializuje listu racuna.
     *
     * @see Racun
     */
    public Klijent() {
        racuni = new LinkedList<>();
    }

    /**
     * Konstruktor koji inicializuje sve atribute klijenta.
     *
     * @param klijentID id klijenta
     * @param ime ime klijenta
     * @param prezime prezime klijenta
     * @param adresa adresa stanovanja
     * @param JMBG jedinstveni maticni broj sadrzi 13 cifara
     * @param telefon mobilni telefon
     * @param email elektronska posta
     */
    public Klijent(int klijentID, String ime, String prezime, String adresa, String JMBG, String telefon, String email) {
        this();
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.JMBG = JMBG;
        this.telefon = telefon;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(int klijentID) {
        this.klijentID = klijentID;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public List<Racun> getRacuni() {
        return racuni;
    }

    public void setRacuni(List<Racun> racuni) {
        this.racuni = racuni;
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
        final Klijent other = (Klijent) obj;
        if (this.klijentID != other.klijentID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiNazivTabele() {
        return "klijent";
    }

    @Override
    public String vratiAtributeZaInsert() {
        return "ime, prezime, adresa, jmbg, telefon, email";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + adresa + "', '" + JMBG + "', '" + telefon + "', '" + email + "'";
    }

    @Override
    public String vratiJoinUpit() {
        return "";
    }

    @Override
    public String vratiUslovZaNadjiSlog() {
        return "KlijentID = " + klijentID;
    }

    @Override
    public String vratiUslovZaListuObjekata() {
        return "";
    }

    @Override
    public String vratiID() {
        return "klijentID";
    }

    @Override
    public String vratiSetZaIzmenu() {
        return String.format("ime = '%s', prezime = '%s', adresa = '%s', JMBG = '%s', telefon = '%s',email = '%s'", ime, prezime, adresa, JMBG, telefon, email);
    }

    @Override
    public boolean jesteAutoInkrement() {
        return true;
    }

    @Override
    public void postaviIdObjekta(int id) {
        setKlijentID(klijentID);
    }

    @Override
    public List<OpstiDomenskiObjekat> ucitajSve(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int klijentID = rs.getInt("KlijentID");
            String ime = rs.getString("Ime");
            String prezime = rs.getString("Prezime");
            String adresa = rs.getString("adresa");
            String jmbg = rs.getString("jmbg");
            String telefon = rs.getString("telefon");
            String email = rs.getString("email");

            Klijent klijent = new Klijent(klijentID, ime, prezime, adresa, jmbg, telefon, email);
            lista.add(klijent);
        }
        return lista;
    }

    @Override
    public OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws SQLException {
        Klijent klijent = null;
        if (rs.next()) {
            int klijentID = rs.getInt("KlijentID");
            String ime = rs.getString("ime");
            String prezime = rs.getString("Prezime");
            String adresa = rs.getString("adresa");
            String jmbg = rs.getString("jmbg");
            String telefon = rs.getString("telefon");
            String email = rs.getString("email");

            klijent = new Klijent(klijentID, ime, prezime, adresa, jmbg, telefon, email);
        }

        return klijent;
    }

}
