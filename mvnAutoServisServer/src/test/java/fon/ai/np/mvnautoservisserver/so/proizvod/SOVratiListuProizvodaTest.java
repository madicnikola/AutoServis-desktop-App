/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.proizvod;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.domen.OpstiDomenskiObjekat;
import fon.ai.np.mvnautoserviscommonlib.domen.Proizvod;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.so.OpstaSOTest;
import fon.ai.np.mvnautoservisserver.so.klijent.SOZapamtiKlijenta;
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
public class SOVratiListuProizvodaTest extends OpstaSOTest {

    public SOVratiListuProizvodaTest() {
    }

    @Before
    @Override
    public void setUp() {
        odo = new Proizvod();
        so = new SOVratiListuProizvoda((Proizvod) odo);
    }

    /**
     * Test of izvrsiOperaciju method, of class SOVratiListuProizvoda.
     */
    @Test
    @Override
    public void testIzvrsiOperaciju() throws Exception {
        super.testIzvrsiOperaciju();
        List<Proizvod> expResult = new ArrayList<>();
        List<OpstiDomenskiObjekat> lista = so.getDb().vratiSve(odo, "");
        for (OpstiDomenskiObjekat o : lista) {
            expResult.add((Proizvod) o);
        }
        List<Proizvod> result = ((SOVratiListuProizvoda) so).getListaProizvoda();

        assertEquals(expResult, result);
    }

    /**
     * Test of getListaProizvoda method, of class SOVratiListuProizvoda.
     */
    @Test
    public void testGetListaProizvoda() {
        System.out.println("getListaProizvoda");
        List<Proizvod> expResult = ((SOVratiListuProizvoda) so).listaProizvoda;
        List<Proizvod> result = ((SOVratiListuProizvoda) so).getListaProizvoda();
        assertEquals(expResult, result);
    }

}
