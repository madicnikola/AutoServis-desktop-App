/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.controller;

import fon.ai.np.mvnautoservisklijent.communication.Communication;
import fon.ai.np.mvnautoservisklijent.coordinator.GUICoordinator;
import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.domen.Korisnik;
import fon.ai.np.mvnautoserviscommonlib.domen.Proizvod;
import fon.ai.np.mvnautoserviscommonlib.domen.Racun;
import fon.ai.np.mvnautoserviscommonlib.domen.Usluga;
import fon.ai.np.mvnautoserviscommonlib.transfer.Request;
import fon.ai.np.mvnautoserviscommonlib.transfer.Response;
import fon.ai.np.mvnautoserviscommonlib.transfer.constants.Operacija;
import fon.ai.np.mvnautoserviscommonlib.transfer.constants.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import fon.ai.np.mvnautoservisklijent.ui.view.mode.FormMode;

/**
 *
 * @author Nikola
 */
public class ControllerC {

    private static ControllerC instance;
    private final Map<String, Object> map;

    private ControllerC() {
        map = new HashMap<>();
    }

    public static ControllerC getInstance() {
        if (instance == null) {
            instance = new ControllerC();
        }
        return instance;
    }

    public void setKorisnik(Korisnik korisnik) {
        map.put("korisnik", korisnik);
    }

    public Korisnik login(Korisnik korisnik) throws Exception {
        Request request = new Request(Operacija.LOGIN, korisnik);
        Communication.getInstance().posaljiZahtev(request);

        Response response = Communication.getInstance().primiOdgovor();

        korisnik = (Korisnik) response.getResponseObject();
        if (response.getStatus() == Status.ERROR || korisnik.toString().equals("null")) {
            throw new Exception(response.getPoruka());
        }
        setKorisnik(korisnik);

        return korisnik;
    }

    public Klijent sacuvajKlijenta(Klijent klijent) throws Exception {
        Request request = new Request(Operacija.SAVE_CLIENT, klijent);
        Communication.getInstance().posaljiZahtev(request);
        Response response = Communication.getInstance().primiOdgovor();

        if (response.getStatus() == Status.ERROR) {
            throw new Exception(response.getPoruka());
        }
        klijent = (Klijent) response.getResponseObject();

        setKlijent(klijent);
        return klijent;
    }

    private void setKlijent(Klijent klijent) {
        map.put("klijent", klijent);
    }

    private void setRacun(Racun racun) {
        map.put("racun", racun);
    }

    public void logout() {
        Communication.getInstance().posaljiZahtev(new Request(Operacija.LOGOUT));
    }

    public Usluga sacuvajUslugu(Usluga usluga) throws Exception {
        Request request = new Request(Operacija.SAVE_SERVICE, usluga);
        Communication.getInstance().posaljiZahtev(request);
        Response response = Communication.getInstance().primiOdgovor();
        if (response.getStatus() == Status.ERROR) {
            throw new Exception(response.getPoruka());
        }
        usluga = (Usluga) response.getResponseObject();

        setUsluga(usluga);
        return usluga;

    }

    private void setUsluga(Usluga usluga) {
        map.put("usluga", usluga);
    }

    public void prikaziDetalje(Klijent klijent) {
        GUICoordinator.getInstance().openKlijentFrame(FormMode.FORM_VIEW, klijent);
    }

    public List<Klijent> pretraziKlijente(String kriterijum) {
        Request request = new Request(Operacija.SEARCH_CLIENTS, kriterijum);
        Communication.getInstance().posaljiZahtev(request);
        Response response = Communication.getInstance().primiOdgovor();
        if (response.getStatus() == Status.ERROR) {
            return new ArrayList<>();
        }
        return (List<Klijent>) response.getResponseObject();

    }

    public Klijent updateKlijenta(Klijent klijent) throws Exception {
        Request request = new Request(Operacija.UPDATE_CLIENT, klijent);
        Communication.getInstance().posaljiZahtev(request);
        Response response = Communication.getInstance().primiOdgovor();

        if (response.getStatus() == Status.ERROR) {
            throw new Exception(response.getPoruka());
        }
        klijent = (Klijent) response.getResponseObject();

        setKlijent(klijent);
        return klijent;
    }

    public Klijent deleteKlijenta(Klijent klijent) throws Exception {
        Request request = new Request(Operacija.DELETE_CLIENT, klijent);
        Communication.getInstance().posaljiZahtev(request);
        Response response = Communication.getInstance().primiOdgovor();

        if (response.getStatus() == Status.ERROR) {
            throw new Exception(response.getPoruka());
        }
        klijent = (Klijent) response.getResponseObject();

        setKlijent(klijent);
        return klijent;
    }

    public void izmeni(Klijent klijent) {
        GUICoordinator.getInstance().openKlijentFrame(FormMode.FORM_UPDATE, klijent);
    }

    public void obrisi(Klijent klijent) {
        GUICoordinator.getInstance().openKlijentFrame(FormMode.FORM_DELETE, klijent);
    }

    public List<Usluga> pretraziUsluge(String kriterijum) {
        Request request = new Request(Operacija.SEARCH_SERVICES, kriterijum);
        Communication.getInstance().posaljiZahtev(request);
        Response response = Communication.getInstance().primiOdgovor();
        if (response.getStatus() == Status.ERROR) {
            return new ArrayList<>();
        }
        return (List<Usluga>) response.getResponseObject();
    }

    public void izmeni(Usluga usluga) {
        GUICoordinator.getInstance().openUslugaFrame(FormMode.FORM_UPDATE, usluga);

    }

    public void obrisi(Usluga usluga) {
        GUICoordinator.getInstance().openUslugaFrame(FormMode.FORM_DELETE, usluga);
    }

    public void prikaziDetalje(Usluga usluga) {
        GUICoordinator.getInstance().openUslugaFrame(FormMode.FORM_VIEW, usluga);

    }

    public Usluga updateUslugu(Usluga usluga) throws Exception {
        Request request = new Request(Operacija.UPDATE_SERVICE, usluga);
        Communication.getInstance().posaljiZahtev(request);
        Response response = Communication.getInstance().primiOdgovor();

        if (response.getStatus() == Status.ERROR) {
            throw new Exception(response.getPoruka());
        }
        usluga = (Usluga) response.getResponseObject();

        setUsluga(usluga);
        return usluga;
    }

    public Usluga deleteUslugu(Usluga usluga) throws Exception {
        Request request = new Request(Operacija.DELETE_SERVICE, usluga);
        Communication.getInstance().posaljiZahtev(request);
        Response response = Communication.getInstance().primiOdgovor();

        if (response.getStatus() == Status.ERROR) {
            throw new Exception(response.getPoruka());
        }
        usluga = (Usluga) response.getResponseObject();

        setUsluga(usluga);
        return usluga;
    }

    public List<Proizvod> vratiSveProizvode(Proizvod proizvod) throws Exception {
        Request request = new Request(Operacija.SELECT_ALL_PRODUCTS, proizvod);
        Communication.getInstance().posaljiZahtev(request);
        Response response = Communication.getInstance().primiOdgovor();

        if (response.getStatus() == Status.ERROR) {
            throw new Exception(response.getPoruka());
        }
        return (List<Proizvod>) response.getResponseObject();
    }

    public Racun sacuvajRacun(Racun racun) throws Exception {
        Request request = new Request(Operacija.SAVE_INVOICE, racun);
        Communication.getInstance().posaljiZahtev(request);
        Response response = Communication.getInstance().primiOdgovor();

        if (response.getStatus() == Status.ERROR) {
            throw new Exception(response.getPoruka());
        }
        racun = (Racun) response.getResponseObject();

        setRacun(racun);
        return racun;
    }

    public Racun stornirajRacun(Racun racun) throws Exception {
        Request request = new Request(Operacija.REVERSE_INVOICE, racun);
        Communication.getInstance().posaljiZahtev(request);
        Response response = Communication.getInstance().primiOdgovor();

        if (response.getStatus() == Status.ERROR) {
            throw new Exception(response.getPoruka());
        }
        racun = (Racun) response.getResponseObject();

        setRacun(racun);
        return racun;
    }

    public List<Racun> pretraziRacune(String kriterijum) {
        Request request = new Request(Operacija.SEARCH_INVOICES, kriterijum);
        Communication.getInstance().posaljiZahtev(request);
        Response response = Communication.getInstance().primiOdgovor();
        if (response.getStatus() == Status.ERROR) {
            System.out.println(response.getPoruka());
            return new ArrayList<>();
        }
        return (List<Racun>) response.getResponseObject();
    }

    public void prikaziDetalje(Racun racun) {
        GUICoordinator.getInstance().openRacunFrame(FormMode.FORM_VIEW, racun);

    }
}
