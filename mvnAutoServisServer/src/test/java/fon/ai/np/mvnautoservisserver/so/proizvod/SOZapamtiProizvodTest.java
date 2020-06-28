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
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

/**
 *
 * @author Nikola
 */
public class SOZapamtiProizvodTest extends OpstaSOTest {

    public SOZapamtiProizvodTest() {
    }

    @Before
    @Override
    public void setUp() {
        odo = new Proizvod(0, "test", 100);
        so = new SOZapamtiProizvod((Proizvod) odo);
        super.setUp();
    }

    @After
    @Override
    public void tearDown() {
        try {
            SOObrisiProizvod soObrisi = new SOObrisiProizvod((Proizvod) odo);
            soObrisi.izvrsenjeSO();
        } catch (SQLException | ValidationException ex) {
            ex.printStackTrace();
        }
        super.tearDown();
    }

    /**
     * Test of izvrsiOperaciju method, of class SOZapamtiProizvod.
     */
    @Override
    public void testIzvrsiOperaciju() throws Exception {
        super.testIzvrsiOperaciju();
        assertEquals((Proizvod) so.getDb().vratiJedan((Proizvod) odo), (Proizvod) odo);

    }
}
