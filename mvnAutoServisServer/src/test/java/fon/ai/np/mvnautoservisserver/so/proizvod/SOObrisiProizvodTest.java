/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.proizvod;

import fon.ai.np.mvnautoserviscommonlib.domen.Proizvod;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSOTest;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nikola
 */
public class SOObrisiProizvodTest extends OpstaSOTest {

    public SOObrisiProizvodTest() {
    }

    @Before
    @Override
    public void setUp() {
        odo = new Proizvod(0, "test", 200);
        so = new SOObrisiProizvod((Proizvod) odo);
        SOZapamtiProizvod soZapamti = new SOZapamtiProizvod((Proizvod) odo);
        try {
            soZapamti.izvrsenjeSO();
            odo = soZapamti.getOpstiDomenskiObjekat();
        } catch (ValidationException | SQLException ex) {
            ex.printStackTrace();
        }
        super.setUp();
    }

    /**
     * Test of izvrsiOperaciju method, of class SOObrisiProizvod.
     */
    @Test
    @Override
    public void testIzvrsiOperaciju() throws Exception {
        super.testIzvrsiOperaciju();
        assertNotEquals(so.getDb().vratiJedan(odo), (Proizvod) odo);

    }

}
