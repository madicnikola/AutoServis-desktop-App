/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.usluga;

import fon.ai.np.mvnautoserviscommonlib.domen.Proizvod;
import fon.ai.np.mvnautoserviscommonlib.domen.Usluga;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSOTest;
import fon.ai.np.mvnautoservisserver.so.proizvod.SOObrisiProizvod;
import fon.ai.np.mvnautoservisserver.so.proizvod.SOZapamtiProizvod;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Nikola
 */
public class SOAzurirajUsluguTest extends OpstaSOTest {

    Usluga expResultUsluga;

    public SOAzurirajUsluguTest() {
    }

    @Before
    @Override
    public void setUp() {
        odo = new Usluga(0, "test", 100, "test usluga");
        SOZapamtiProizvod zapamtiProizvod = new SOZapamtiProizvod(new Proizvod((Usluga) odo));
        try {
            zapamtiProizvod.izvrsenjeSO();
            ((Usluga) odo).setProizvodID(((Proizvod) zapamtiProizvod.getOpstiDomenskiObjekat()).getProizvodID());
            expResultUsluga = (Usluga) odo;
            so = new SOZapamtiUslugu((Usluga) odo);
            so.izvrsenjeSO();
        } catch (SQLException | ValidationException ex) {
            ex.printStackTrace();
        }
        super.setUp();
    }

    @After
    @Override
    public void tearDown() {
        try {
            SOObrisiProizvod soObrisiProizvod = new SOObrisiProizvod(new Proizvod((Usluga) odo));
            soObrisiProizvod.izvrsenjeSO();
        } catch (SQLException | ValidationException ex) {
            ex.printStackTrace();
        }
        super.tearDown();
    }

    /**
     * Test of izvrsiOperaciju method, of class SOAzurirajUslugu.
     */
    @Test(expected = Test.None.class)
    @Override
    public void testIzvrsiOperaciju() throws Exception {
        expResultUsluga.setOpisUsluge("testUpdateUslugaOpis");
        System.out.println(expResultUsluga.getProizvodID());

        so = new SOAzurirajUslugu(expResultUsluga);
        super.izvrsiOperaciju();

    }

}
