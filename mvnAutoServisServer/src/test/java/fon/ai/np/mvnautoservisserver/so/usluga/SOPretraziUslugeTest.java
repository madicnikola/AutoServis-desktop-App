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
public class SOPretraziUslugeTest extends OpstaSOTest {

    List<Usluga> listaUsluga = new ArrayList() {
        {
            add(new Usluga(0, "test", 100, "test opis"));
            add(new Usluga(1, "test2", 200, "test opis2"));
        }
    };

    public SOPretraziUslugeTest() {
    }

    @Before
    @Override
    public void setUp() {
        for (Usluga usluga : listaUsluga) {
            SOZapamtiUslugu soZapamti = new SOZapamtiUslugu(usluga);
            try {
                SOZapamtiProizvod zapamtiProizvod = new SOZapamtiProizvod(new Proizvod(usluga));
                zapamtiProizvod.izvrsenjeSO();
                usluga.setProizvodID(((Proizvod) zapamtiProizvod.getOpstiDomenskiObjekat()).getProizvodID());
                soZapamti.izvrsenjeSO();
                usluga = (Usluga) soZapamti.getOpstiDomenskiObjekat();
            } catch (ValidationException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        String kriterijum = " WHERE p.naziv LIKE 'test%'";
        so = new SOPretraziUsluge(kriterijum);
        super.setUp();
    }

    @After
    @Override
    public void tearDown() {
        for (Usluga usluga : listaUsluga) {
            SOObrisiProizvod soObrisi = new SOObrisiProizvod(new Proizvod(usluga));
            try {
                soObrisi.izvrsenjeSO();
            } catch (ValidationException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        listaUsluga = null;
        super.tearDown();
    }

    @Test
    @Override
    public void testIzvrsiOperaciju() throws Exception {
        super.testIzvrsiOperaciju();
        List<Usluga> expResult = getListaUsluga();
        List<Usluga> result = ((SOPretraziUsluge) so).getListaUsluga();
        assertEquals(expResult, result);
    }

    /**
     * Test of getListaUsluga method, of class SOPretraziUsluge.
     */
    @Test
    public void testGetListaUsluga() {
        System.out.println("getListaUsluga");
        List<Usluga> expResult = ((SOPretraziUsluge) so).listaUsluga;
        List<Usluga> result = ((SOPretraziUsluge) so).getListaUsluga();
        assertEquals(expResult, result);
    }

    public List<Usluga> getListaUsluga() {
        return listaUsluga;
    }

}
