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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Nikola
 */
public class SOAzurirajKlijentaTest extends OpstaSOTest {

    Klijent expResultKlijent;

    public SOAzurirajKlijentaTest() {
    }

    @Before
    @Override
    public void setUp() {
        super.setUp();
        odo = new Klijent(0, "test", "test", "test adress", "test104521", "060test", "test@test");
        SOZapamtiKlijenta soZapamti = new SOZapamtiKlijenta((Klijent) odo);
        try {
            soZapamti.izvrsenjeSO();
            odo = soZapamti.getOpstiDomenskiObjekat();
        } catch (ValidationException | SQLException ex) {
            ex.printStackTrace();
        }
        expResultKlijent = (Klijent) odo;
        so = new SOAzurirajKlijenta(expResultKlijent);
    }

    @Override
    public void tearDown() {
        SOObrisiKlijenta soObrisi = new SOObrisiKlijenta((Klijent) odo);
        try {
            soObrisi.izvrsenjeSO();
        } catch (ValidationException | SQLException ex) {
            ex.printStackTrace();
        }
        super.tearDown(); 
    }

    /**
     * Test of izvrsiOperaciju method, of class SOAzurirajKlijenta.
     */
    @Test
    @Override
    public void testIzvrsiOperaciju() throws Exception {
        expResultKlijent.setIme("testUpdateIme");
        expResultKlijent.setEmail("test@test2");
        super.testIzvrsiOperaciju();
        Klijent result = (Klijent) so.getDb().vratiJedan(odo);
        System.out.println(odo.vratiUslovZaNadjiSlog());
        System.out.println(result);
        assertEquals(result.getIme(), expResultKlijent.getIme());
        assertEquals(result.getEmail(), expResultKlijent.getEmail());

    }

    /**
     * Test of izvrsiOperaciju method, of class SOAzurirajKlijenta.
     */
    @Test(expected = Exception.class)
    public void testIzvrsiOperacijuEmail() throws Exception {
        expResultKlijent.setEmail("test");
        so = new SOAzurirajKlijenta(expResultKlijent);
        super.testIzvrsiOperaciju();
    }
}
