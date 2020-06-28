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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Nikola
 */
public class SOZapamtiRacunTest extends OpstaSOTest {

    public SOZapamtiRacunTest() {
    }

    @Before
    @Override
    public void setUp() {
        try {
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
            so = new SOZapamtiRacun((Racun) odo);

        } catch (ParseException | SQLException | ValidationException ex) {
            ex.printStackTrace();
        }
        super.setUp();
    }

    @After
    @Override
    public void tearDown() {
        try {
            SOObrisiKlijenta soObrisiKlijenta = new SOObrisiKlijenta(((Racun) odo).getKlijent());
            soObrisiKlijenta.izvrsenjeSO();
        } catch (SQLException | ValidationException ex) {
            ex.printStackTrace();
        }
        super.tearDown();
    }

    /**
     * Test of izvrsiOperaciju method, of class SOZapamtiRacun.
     */
    @Test(expected = Test.None.class)
    @Override
    public void testIzvrsiOperaciju() throws Exception {
        super.izvrsiOperaciju();
    }

}
