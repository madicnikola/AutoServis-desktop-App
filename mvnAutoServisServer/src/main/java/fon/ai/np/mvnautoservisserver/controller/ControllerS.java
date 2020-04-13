/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.controller;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.domen.Korisnik;
import fon.ai.np.mvnautoserviscommonlib.domen.Proizvod;
import fon.ai.np.mvnautoserviscommonlib.domen.Racun;
import fon.ai.np.mvnautoserviscommonlib.domen.Usluga;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoserviscommonlib.transfer.Response;
import fon.ai.np.mvnautoserviscommonlib.transfer.constants.Operacija;
import fon.ai.np.mvnautoserviscommonlib.transfer.constants.Status;
import fon.ai.np.mvnautoservisserver.so.klijent.SOAzurirajKlijenta;
import fon.ai.np.mvnautoservisserver.so.klijent.SOObrisiKlijenta;
import fon.ai.np.mvnautoservisserver.so.klijent.SOPretraziKlijente;
import fon.ai.np.mvnautoservisserver.so.klijent.SOZapamtiKlijenta;
import fon.ai.np.mvnautoservisserver.so.korisnik.SOLogin;
import fon.ai.np.mvnautoservisserver.so.proizvod.SOAzurirajProizvod;
import fon.ai.np.mvnautoservisserver.so.proizvod.SOObrisiProizvod;
import fon.ai.np.mvnautoservisserver.so.proizvod.SOVratiListuProizvoda;
import fon.ai.np.mvnautoservisserver.so.proizvod.SOZapamtiProizvod;
import fon.ai.np.mvnautoservisserver.so.racun.SOPretraziRacune;
import fon.ai.np.mvnautoservisserver.so.racun.SOStornirajRacun;
import fon.ai.np.mvnautoservisserver.so.racun.SOZapamtiRacun;
import fon.ai.np.mvnautoservisserver.so.usluga.SOAzurirajUslugu;
import fon.ai.np.mvnautoservisserver.so.usluga.SOObrisiUslugu;
import fon.ai.np.mvnautoservisserver.so.usluga.SOPretraziUsluge;
import fon.ai.np.mvnautoservisserver.so.usluga.SOZapamtiUslugu;
import fon.ai.np.mvnautoservisserver.threads.ClientThreadHandler;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikola
 */
public class ControllerS {

    private static ControllerS instance;
    private final List<ClientThreadHandler> clientThreads;

    private ControllerS() {
        clientThreads = new LinkedList<>();
    }

    public static ControllerS getInstance() {
        if (instance == null) {
            instance = new ControllerS();
        }
        return instance;
    }

    public void removeClient(ClientThreadHandler client) {
        try {
            client.interrupt();
            client.getSocket().close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Greska prilikom zatvaranja soketa");
        }
        clientThreads.remove(client);

    }

    public List<ClientThreadHandler> getClientThreads() {
        return clientThreads;
    }

    public void addClient(ClientThreadHandler klijent) {
        clientThreads.add(klijent);
    }

    public void logout(ClientThreadHandler clientThread) {
        removeClient(clientThread);
    }

    public void logoutClients() {
        for (ClientThreadHandler clientThread : clientThreads) {
            try {
                clientThread.posaljiOdgovor(new Response(Operacija.LOGOUT, "Server nije dostupan. Dovidjenja. Code: " + Status.ERROR, Status.ERROR));
            } catch (IOException ex) {
                Logger.getLogger(ControllerS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Korisnik login(Korisnik korisnik) throws SQLException, ValidationException {
        SOLogin so = new SOLogin(korisnik);
        so.izvrsenjeSO();
        return (Korisnik) so.getOpstiDomenskiObjekat();
    }

    public Proizvod saveProizvod(Proizvod proizvod) throws SQLException, ValidationException {
        SOZapamtiProizvod so = new SOZapamtiProizvod(proizvod);
        so.izvrsenjeSO();
        return (Proizvod) so.getOpstiDomenskiObjekat();
    }

    public Proizvod updateProizvod(Proizvod proizvod) throws SQLException, ValidationException {
        SOAzurirajProizvod so = new SOAzurirajProizvod(proizvod);
        so.izvrsenjeSO();
        return (Proizvod) so.getOpstiDomenskiObjekat();
    }

    public Proizvod deleteProizvod(Proizvod proizvod) throws SQLException, ValidationException {
        SOObrisiProizvod so = new SOObrisiProizvod(proizvod);
        so.izvrsenjeSO();
        return (Proizvod) so.getOpstiDomenskiObjekat();
    }

    public Klijent saveKlijenta(Klijent klijent) throws SQLException, ValidationException {
        SOZapamtiKlijenta so = new SOZapamtiKlijenta(klijent);
        so.izvrsenjeSO();
        return (Klijent) so.getOpstiDomenskiObjekat();
    }

    public List<Klijent> searchKlijente(String kriterijum) throws SQLException, ValidationException {
        SOPretraziKlijente so = new SOPretraziKlijente(kriterijum);
        so.izvrsenjeSO();
        return so.getListaKlijenata();
    }

    public Klijent updateKlijenta(Klijent klijent) throws SQLException, ValidationException {
        SOAzurirajKlijenta so = new SOAzurirajKlijenta(klijent);
        so.izvrsenjeSO();
        return (Klijent) so.getOpstiDomenskiObjekat();
    }

    public Klijent deleteKlijenta(Klijent klijent) throws SQLException, ValidationException {
        SOObrisiKlijenta so = new SOObrisiKlijenta(klijent);
        so.izvrsenjeSO();
        return (Klijent) so.getOpstiDomenskiObjekat();
    }

    public Usluga saveUslugu(Usluga usluga) throws SQLException, ValidationException {
        SOZapamtiUslugu so = new SOZapamtiUslugu(usluga);
        so.izvrsenjeSO();
        return (Usluga) so.getOpstiDomenskiObjekat();
    }

    public List<Usluga> searchUsluge(String kriterijum) throws SQLException, ValidationException {
        SOPretraziUsluge so = new SOPretraziUsluge(kriterijum);
        so.izvrsenjeSO();
        return (List<Usluga>) so.getListaUsluga();
    }

    public Usluga updateUslugu(Usluga usluga) throws SQLException, ValidationException {
        SOAzurirajUslugu so = new SOAzurirajUslugu(usluga);
        so.izvrsenjeSO();
        return (Usluga) so.getOpstiDomenskiObjekat();
    }

    public Usluga deleteUslugu(Usluga usluga) throws SQLException, ValidationException {
        SOObrisiUslugu so = new SOObrisiUslugu(usluga);
        so.izvrsenjeSO();
        return (Usluga) so.getOpstiDomenskiObjekat();
    }

    public List<Proizvod> getAllProducts(Proizvod proizvod) throws SQLException, ValidationException {
        SOVratiListuProizvoda so = new SOVratiListuProizvoda(proizvod);
        so.izvrsenjeSO();
        return so.getListaProizvoda();
    }

    public Racun saveRacun(Racun racun) throws SQLException, ValidationException {
        SOZapamtiRacun so = new SOZapamtiRacun(racun);
        so.izvrsenjeSO();
        return (Racun) so.getOpstiDomenskiObjekat();
    }

    public List<Racun> searchRacune(String kriterijum) throws SQLException, ValidationException {
        SOPretraziRacune so = new SOPretraziRacune(kriterijum);
        so.izvrsenjeSO();
        return (List<Racun>) so.getListaRacuna();
    }

    public Racun reverseRacun(Racun racun) throws SQLException, ValidationException {
        SOStornirajRacun so = new SOStornirajRacun(racun);
        so.izvrsenjeSO();
        return (Racun) so.getOpstiDomenskiObjekat();
    }
}
