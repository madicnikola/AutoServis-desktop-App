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
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nikola
 */
public class SOPretraziKlijenteTest extends OpstaSOTest {

    List<Klijent> listaKlijenata = new ArrayList() {
        {
            add(new Klijent(0, "test", "test", "test adress", "test104521", "060test", "test@test"));
            add(new Klijent(1, "testDva", "test2", "test adress2", "test104521", "060test", "test@test"));
        }
    };

    public SOPretraziKlijenteTest() {
    }

    @Before
    @Override
    public void setUp() {
        super.setUp();
        for (Klijent klijent : listaKlijenata) {
            SOZapamtiKlijenta soZapamti = new SOZapamtiKlijenta(klijent);
            try {
                soZapamti.izvrsenjeSO();
                klijent = (Klijent) soZapamti.getOpstiDomenskiObjekat();
            } catch (ValidationException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        String kriterijum = " WHERE ime LIKE 'test%'";
        so = new SOPretraziKlijente(kriterijum);

    }

    @Override
    public void tearDown() {
        for (Klijent klijent : listaKlijenata) {
            SOObrisiKlijenta soObrisi = new SOObrisiKlijenta(klijent);
            try {
                soObrisi.izvrsenjeSO();
            } catch (ValidationException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        listaKlijenata = null;
        super.tearDown();
    }

    /**
     * Test of izvrsiOperaciju method, of class SOPretraziKlijente.
     */
    @Test
    @Override
    public void testIzvrsiOperaciju() throws Exception {
        super.testIzvrsiOperaciju();
        List<Klijent> expResult = getListaKlijenata();
        List<Klijent> result = ((SOPretraziKlijente) so).getListaKlijenata();
        assertEquals(expResult, result);
    }

    /**
     * Test2 of izvrsiOperaciju method, of class SOPretraziKlijente.
     */
    @Test
    public void test2IzvrsiOperacijuPrazanString() throws Exception {
        so = new SOPretraziKlijente("");
        super.testIzvrsiOperaciju();
        List<Klijent> expResult = getListaKlijenata();
        List<Klijent> result = ((SOPretraziKlijente) so).getListaKlijenata();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getListaKlijenata method, of class SOPretraziKlijente.
     */
    @Test
    public void testGetListaKlijenata() {
        System.out.println("getListaKlijenata");
        List<Klijent> expResult = ((SOPretraziKlijente) so).listaKlijenata;
        List<Klijent> result = ((SOPretraziKlijente) so).getListaKlijenata();
        assertEquals(expResult, result);
    }

    public List<Klijent> getListaKlijenata() {
        return listaKlijenata;
    }

}
