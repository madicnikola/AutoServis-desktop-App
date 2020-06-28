/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.klijent;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSOTest;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nikola
 */
public class SOZapamtiKlijentaTest extends OpstaSOTest {

    public SOZapamtiKlijentaTest() {

    }

    @Override
    public void setUp() {
        odo = new Klijent(0, "test", "test", "test adress", "test104521", "060test", "test@test");
        so = new SOZapamtiKlijenta((Klijent) odo);
        super.setUp();
    }

    @Override
    public void tearDown() {
        try {
            SOObrisiKlijenta soObrisi = new SOObrisiKlijenta((Klijent) odo);
            soObrisi.izvrsenjeSO();
        } catch (SQLException | ValidationException ex) {
            ex.printStackTrace();
        }
        super.tearDown();
    }

    @Override
    public void testIzvrsiOperaciju() throws Exception {
        super.testIzvrsiOperaciju();
        assertEquals((Klijent) so.getDb().vratiJedan((Klijent) odo), (Klijent) odo);
    }

    /**
     * Test of proveriPreduslov method when bad email is entered, of class
     * SOZapamtiKlijenta.
     *
     * @throws Exception.class
     */
    @Test(expected = Exception.class)
    public void testProveriPreduslovLosEmail() throws Exception {
        System.out.println("IzvrsiOperaciju - LosEmail");
        ((Klijent) odo).setEmail("test");
        super.testIzvrsiOperaciju();
    }

}
