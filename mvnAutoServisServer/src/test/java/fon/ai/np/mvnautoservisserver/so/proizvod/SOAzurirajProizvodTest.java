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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Nikola
 */
public class SOAzurirajProizvodTest extends OpstaSOTest {

    Proizvod expResultProizvod;

    public SOAzurirajProizvodTest() {
    }

    @Before
    @Override
    public void setUp() {
        odo = new Proizvod(0, "test", 100);
        SOZapamtiProizvod soZapamti = new SOZapamtiProizvod((Proizvod) odo);
        try {
            soZapamti.izvrsenjeSO();
            odo = soZapamti.getOpstiDomenskiObjekat();
        } catch (ValidationException | SQLException ex) {
            ex.printStackTrace();
        }
        expResultProizvod = (Proizvod) odo;
        so = new SOAzurirajProizvod(expResultProizvod);
        super.setUp();
    }

    @Override
    public void tearDown() {
        SOObrisiProizvod soObrisi = new SOObrisiProizvod((Proizvod) odo);
        try {
            soObrisi.izvrsenjeSO();
        } catch (ValidationException | SQLException ex) {
            ex.printStackTrace();
        }
        super.tearDown();
    }

    /**
     * Test of izvrsiOperaciju method, of class SOAzurirajProizvod.
     */
    @Test
    @Override
    public void testIzvrsiOperaciju() throws Exception {
        expResultProizvod.setNaziv("testUpdateIme");
        expResultProizvod.setVrednost(500);

        so = new SOAzurirajProizvod(expResultProizvod);
        super.testIzvrsiOperaciju();

        Proizvod result = (Proizvod) so.getDb().vratiJedan(odo);
        assertEquals(result.getNaziv(), expResultProizvod.getNaziv());
        assertEquals(result.getVrednost(), expResultProizvod.getVrednost(), 0.001);
    }

}
