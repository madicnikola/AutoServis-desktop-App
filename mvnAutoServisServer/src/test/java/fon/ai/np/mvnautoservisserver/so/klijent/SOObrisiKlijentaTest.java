/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.klijent;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSO;
import fon.ai.np.mvnautoservisserver.so.OpstaSOTest;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nikola
 */
public class SOObrisiKlijentaTest extends OpstaSOTest {

    public SOObrisiKlijentaTest() {
    }

    @Before
    @Override
    public void setUp() {
        super.setUp();
        odo = new Klijent(0, "test", "test", "test adress", "test104521", "060test", "test@test");
        so = new SOObrisiKlijenta((Klijent) odo);
        SOZapamtiKlijenta soZapamti = new SOZapamtiKlijenta((Klijent) odo);
        try {
            soZapamti.izvrsenjeSO();
            odo = soZapamti.getOpstiDomenskiObjekat();
        } catch (ValidationException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of izvrsiOperaciju method, of class SOObrisiKlijenta.
     */
    @Test
    @Override
    public void testIzvrsiOperaciju() throws Exception {
        super.testIzvrsiOperaciju();
        assertNotEquals(so.getDb().vratiJedan(odo), (Klijent) odo);
    }

}
