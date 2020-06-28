/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.racun;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.domen.Korisnik;
import fon.ai.np.mvnautoserviscommonlib.domen.Proizvod;
import fon.ai.np.mvnautoserviscommonlib.domen.Racun;
import fon.ai.np.mvnautoserviscommonlib.domen.StavkaRacuna;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSOTest;
import fon.ai.np.mvnautoservisserver.so.klijent.SOObrisiKlijenta;
import fon.ai.np.mvnautoservisserver.so.klijent.SOZapamtiKlijenta;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nikola
 */
public class SOPretraziRacuneTest extends OpstaSOTest {

    List<Racun> listaRacuna;

    public SOPretraziRacuneTest() {
    }

    @Before
    @Override
    public void setUp() {
        try {
            listaRacuna = new ArrayList();
            Korisnik korisnik = new Korisnik(3, "11503", "test", "test", "test", "098f6bcd4621d373cade4e832627b4f6");
            Klijent klijent = new Klijent(0, "test", "test", "test adress", "test104521", "060test", "test@test");
            SOZapamtiKlijenta soZapamtiKlijenta = new SOZapamtiKlijenta(klijent);
            soZapamtiKlijenta.izvrsenjeSO();
            klijent = (Klijent) soZapamtiKlijenta.getOpstiDomenskiObjekat();
            odo = new Racun();
            odo = new Racun(0, 35000, false, new SimpleDateFormat("dd.MM.yyyy").parse("28.06.2020"), korisnik,
                    klijent,
                    new ArrayList<StavkaRacuna>() {
                {
                    add(new StavkaRacuna((Racun) odo, 2, 35000, 1, new Proizvod(9, "Veliki servis", 35000)));
                }
            });
            listaRacuna.add((Racun) odo);
            SOZapamtiRacun sOZapamtiRacun = new SOZapamtiRacun((Racun) odo);
            sOZapamtiRacun.izvrsenjeSO();

            String kriterijum = " WHERE kl.ime LIKE '" + klijent.getIme() + "%' or " + "kl.prezime LIKE '" + klijent.getPrezime() + "%'";
            so = new SOPretraziRacune(kriterijum);

        } catch (ParseException | SQLException | ValidationException ex) {
            ex.printStackTrace();
        }
        super.setUp();
    }

    @After
    @Override
    public void tearDown() {
        try {
            SOObrisiKlijenta soObrisiKlijenta = new SOObrisiKlijenta(listaRacuna.get(0).getKlijent());
            soObrisiKlijenta.izvrsenjeSO();
        } catch (SQLException | ValidationException ex) {
            ex.printStackTrace();
        }
        super.tearDown();
    }

    /**
     * Test of izvrsiOperaciju method, of class SOPretraziRacune.
     */
    @Test
    @Override
    public void testIzvrsiOperaciju() throws Exception {
        super.testIzvrsiOperaciju();
        List<Racun> expResult = getListaRacuna();
        List<Racun> result = ((SOPretraziRacune) so).getListaRacuna();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListaRacuna method, of class SOPretraziRacune.
     */
    @Test
    public void testGetListaRacuna() {
        System.out.println("getListaRacuna");
        List<Racun> expResult = ((SOPretraziRacune) so).listaRacuna;
        List<Racun> result = ((SOPretraziRacune) so).getListaRacuna();
        assertEquals(expResult, result);
    }

    public List<Racun> getListaRacuna() {
        return listaRacuna;
    }

}
