/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.threads;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.domen.Korisnik;
import fon.ai.np.mvnautoserviscommonlib.domen.Proizvod;
import fon.ai.np.mvnautoserviscommonlib.domen.Racun;
import fon.ai.np.mvnautoserviscommonlib.domen.Usluga;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoserviscommonlib.transfer.Request;
import fon.ai.np.mvnautoserviscommonlib.transfer.Response;
import fon.ai.np.mvnautoserviscommonlib.transfer.constants.Operacija;
import fon.ai.np.mvnautoserviscommonlib.transfer.constants.Status;
import fon.ai.np.mvnautoservisserver.controller.ControllerS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;


/**
 *
 * @author Nikola
 */
public class ClientThreadHandler extends Thread {

    private Socket socket;
    private int rb;
    private LocalTime time;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    BufferedReader bufferedReader;
    PrintWriter printWriter;

    public ClientThreadHandler() {
    }

    public ClientThreadHandler(Socket socket, int rb, LocalTime time) {
        this();
        this.socket = socket;
        this.rb = rb;
        this.time = time;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Greska prilikom pokretanja komunikacije sa klijentom");
        }
    }

    @Override
    public void run() {
        try {
            executeOperations();
        } catch (IOException | ClassNotFoundException ex) {
            ControllerS.getInstance().removeClient(this);
            this.interrupt();
        }
        System.out.println("Klijent" + rb + " je otisao");
    }

    private void executeOperations() throws IOException, ClassNotFoundException {
        while (!isInterrupted()) {
            try {
                Request request = primiZahtev();
                Response response = new Response();
                switch (request.getOperacija()) {
                    case Operacija.LOGIN:
                        login(request, response);
                        break;
                    case Operacija.SAVE_CLIENT:
                        saveClient(request, response);
                        break;
                    case Operacija.SEARCH_CLIENTS:
                        searchClients(request, response);
                        break;
                    case Operacija.UPDATE_CLIENT:
                        updateClient(request, response);
                        break;
                    case Operacija.DELETE_CLIENT:
                        deleteClient(request, response);
                        break;
                    case Operacija.LOGOUT:
                        logout();
                        break;
                    case Operacija.SAVE_SERVICE:
                        saveService(request, response);
                        break;
                    case Operacija.SEARCH_SERVICES:
                        searchServices(request, response);
                        break;
                    case Operacija.UPDATE_SERVICE:
                        updateService(request, response);
                        break;
                    case Operacija.DELETE_SERVICE:
                        deleteService(request, response);
                        break;
                    case Operacija.SELECT_ALL_PRODUCTS:
                        selectAllProducts(request, response);
                        break;
                    case Operacija.SELECT_ALL_CLIENTS:
                        selectAllClients(request, response);
                        break;
                    case Operacija.SAVE_INVOICE:
                        saveInvoice(request, response);
                        break;
                    case Operacija.SEARCH_INVOICES:
                        searchInvoices(request, response);
                        break;
                    case Operacija.REVERSE_INVOICE:
                        reverseInvoice(request, response);
                        break;
                }

                posaljiOdgovor(response);
            } catch (SocketException se) {

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    private void login(Request request, Response response) throws ValidationException, SQLException {
        Korisnik korisnik = ControllerS.getInstance().login((Korisnik) request.getRequestObject());
        response.setStatus(Status.OK);
        response.setResponseObject(korisnik);
        response.setOperacija(Operacija.LOGIN);
    }

    private void logout() {
        ControllerS.getInstance().logout(this);
    }

    public Request primiZahtev() throws IOException, ClassNotFoundException {
        return (Request) objectInputStream.readObject();
    }

    public void posaljiOdgovor(Response odgovor) throws IOException {
        objectOutputStream.writeObject(odgovor);
        objectOutputStream.flush();
    }

    private void saveClient(Request request, Response response) {
        try {
            Klijent klijent = (Klijent) request.getRequestObject();
            klijent = ControllerS.getInstance().saveKlijenta(klijent);
            response.setStatus(Status.OK);
            response.setResponseObject(klijent);
            response.setOperacija(Operacija.SAVE_CLIENT);
            response.setPoruka("Klijent: " + klijent.getIme() + " je uspesno sacuvan.");
            System.out.println(klijent.getKlijentID());
        } catch (ValidationException | SQLException ex) {
            response.setStatus(Status.ERROR);
            response.setPoruka(ex.getMessage());
        } catch (Exception e) {
            response.setStatus(Status.ERROR);
            System.out.println(e.getMessage());
        }
    }

    private void searchClients(Request request, Response response) {
        try {
            String kriterijum = (String) request.getRequestObject();
            List<Klijent> list = ControllerS.getInstance().searchKlijente(kriterijum);
            response.setStatus(Status.OK);
            response.setResponseObject(list);
        } catch (SQLException ex) {
            response.setStatus(Status.ERROR);
            response.setPoruka(ex.getMessage());
        } catch (Exception e) {
            response.setStatus(Status.ERROR);
            System.out.println(e.getMessage());
        }
    }

    private void updateClient(Request request, Response response) {
        try {
            Klijent klijent = (Klijent) request.getRequestObject();
            klijent = ControllerS.getInstance().updateKlijenta(klijent);
            response.setStatus(Status.OK);
            response.setResponseObject(klijent);
            response.setOperacija(Operacija.UPDATE_CLIENT);
            response.setPoruka("Klijent: " + klijent.getIme() + " je uspesno sacuvan.");
        } catch (ValidationException | SQLException ex) {
            response.setStatus(Status.ERROR);
            response.setPoruka(ex.getMessage());
        } catch (Exception e) {
            response.setStatus(Status.ERROR);
            System.out.println(e.getMessage());
        }
    }

    private void deleteClient(Request request, Response response) {
        try {
            Klijent klijent = (Klijent) request.getRequestObject();
            klijent = ControllerS.getInstance().deleteKlijenta(klijent);
            response.setStatus(Status.OK);
            response.setResponseObject(klijent);
            response.setOperacija(Operacija.DELETE_CLIENT);
            response.setPoruka("Klijent: " + klijent.getIme() + " je uspesno sacuvan.");
        } catch (ValidationException | SQLException ex) {
            response.setStatus(Status.ERROR);
            response.setPoruka(ex.getMessage());
        } catch (Exception e) {
            response.setStatus(Status.ERROR);
            System.out.println(e.getMessage());
        }
    }

    private void saveService(Request request, Response response) {
        try {
            Usluga usluga = (Usluga) request.getRequestObject();
            Proizvod proizvod = ControllerS.getInstance().saveProizvod(new Proizvod(usluga));
            usluga.setProizvodID(proizvod.getProizvodID());
            usluga = ControllerS.getInstance().saveUslugu(usluga);
            response.setStatus(Status.OK);
            response.setResponseObject(usluga);
            response.setOperacija(Operacija.SAVE_SERVICE);
            response.setPoruka("Usluga: " + usluga.getNaziv() + " je uspesno sacuvana.");
        } catch (ValidationException | SQLException ex) {
            response.setStatus(Status.ERROR);
            response.setPoruka(ex.getMessage());
        } catch (Exception e) {
            response.setStatus(Status.ERROR);
            System.out.println(e.getMessage());
        }
    }

    private void searchServices(Request request, Response response) {
        try {
            String kriterijum = (String) request.getRequestObject();
            List<Usluga> list = ControllerS.getInstance().searchUsluge(kriterijum);
            response.setStatus(Status.OK);
            response.setResponseObject(list);
        } catch (SQLException ex) {
            response.setStatus(Status.ERROR);
            response.setPoruka(ex.getMessage());
        } catch (Exception e) {
            response.setStatus(Status.ERROR);
            System.out.println(e.getMessage());
        }
    }

    private void updateService(Request request, Response response) {
        try {
            Usluga usluga = (Usluga) request.getRequestObject();
            Proizvod proizvod = ControllerS.getInstance().updateProizvod(new Proizvod(usluga));
            usluga.setProizvodID(proizvod.getProizvodID());
            usluga = ControllerS.getInstance().updateUslugu(usluga);
            response.setStatus(Status.OK);
            response.setResponseObject(usluga);
            response.setOperacija(Operacija.UPDATE_SERVICE);
            response.setPoruka("Usluga: " + usluga.getNaziv() + " je uspesno izmenjena.");
        } catch (ValidationException | SQLException ex) {
            response.setStatus(Status.ERROR);
            response.setPoruka(ex.getMessage());
        } catch (Exception e) {
            response.setStatus(Status.ERROR);
            System.out.println(e.getMessage());
        }
    }

    private void deleteService(Request request, Response response) {
        try {
            Usluga usluga = (Usluga) request.getRequestObject();
            ControllerS.getInstance().deleteProizvod(new Proizvod(usluga));
            usluga = ControllerS.getInstance().deleteUslugu(usluga);
            response.setStatus(Status.OK);
            response.setResponseObject(usluga);
            response.setOperacija(Operacija.DELETE_SERVICE);
            response.setPoruka("Usluga: " + usluga.getNaziv() + " je uspesno izmenjena.");
        } catch (ValidationException | SQLException ex) {
            response.setStatus(Status.ERROR);
            response.setPoruka(ex.getMessage());
        } catch (Exception e) {
            response.setStatus(Status.ERROR);
            System.out.println(e.getMessage());
        }
    }

    private void selectAllProducts(Request request, Response response) {
        try {
            Proizvod proizvod = (Proizvod) request.getRequestObject();
            List<Proizvod> listaProizvoda = ControllerS.getInstance().getAllProducts(proizvod);
            response.setStatus(Status.OK);
            response.setResponseObject(listaProizvoda);
            response.setOperacija(Operacija.SELECT_ALL_PRODUCTS);

        } catch (ValidationException | SQLException ex) {
            response.setStatus(Status.ERROR);
            response.setPoruka(ex.getMessage());
        } catch (Exception e) {
            response.setStatus(Status.ERROR);
            System.out.println(e.getMessage());
        }
    }

    private void selectAllClients(Request request, Response response) {
        try {
            Klijent klijent = (Klijent) request.getRequestObject();
            List<Klijent> listaKlijenata = ControllerS.getInstance().searchKlijente("");
            response.setStatus(Status.OK);
            response.setResponseObject(listaKlijenata);
            response.setOperacija(Operacija.SELECT_ALL_CLIENTS);

        } catch (ValidationException | SQLException ex) {
            response.setStatus(Status.ERROR);
            response.setPoruka(ex.getMessage());
        } catch (Exception e) {
            response.setStatus(Status.ERROR);
            System.out.println(e.getMessage());
        }
    }

    private void saveInvoice(Request request, Response response) {
        try {
            Racun racun = (Racun) request.getRequestObject();
            racun = ControllerS.getInstance().saveRacun(racun);
            response.setStatus(Status.OK);
            response.setResponseObject(racun);
            response.setOperacija(Operacija.SAVE_SERVICE);
            response.setPoruka("Racun: " + racun.getRacunID() + " je uspesno sacuvana.");
        } catch (ValidationException | SQLException ex) {
            response.setStatus(Status.ERROR);
            response.setPoruka(ex.getMessage());
        } catch (Exception e) {
            response.setStatus(Status.ERROR);
            System.out.println(e.getMessage());
        }
    }

    private void searchInvoices(Request request, Response response) {
        try {
            String kriterijum = (String) request.getRequestObject();
            List<Racun> list = ControllerS.getInstance().searchRacune(kriterijum);
            response.setStatus(Status.OK);
            response.setResponseObject(list);
        } catch (SQLException ex) {
            response.setStatus(Status.ERROR);
            response.setPoruka(ex.getMessage());
        } catch (Exception e) {
            response.setStatus(Status.ERROR);
            System.out.println(e.getMessage());
        }
    }

    private void reverseInvoice(Request request, Response response) {
        try {
            Racun racun = (Racun) request.getRequestObject();
            racun = ControllerS.getInstance().reverseRacun(racun);
            response.setStatus(Status.OK);
            response.setResponseObject(racun);
            response.setOperacija(Operacija.REVERSE_INVOICE);
            response.setPoruka("Sistem je stornirao racun.");
        } catch (ValidationException | SQLException ex) {
            response.setStatus(Status.ERROR);
            response.setPoruka(ex.getMessage());
        } catch (Exception e) {
            response.setStatus(Status.ERROR);
            System.out.println(e.getMessage());
        }
    }

}
