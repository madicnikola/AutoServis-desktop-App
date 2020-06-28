/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so.korisnik;

import fon.ai.np.mvnautoserviscommonlib.domen.Korisnik;
import fon.ai.np.mvnautoservisserver.so.OpstaSOTest;
import fon.ai.np.mvnautoservisserver.util.PasswordHash;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Nikola
 */
public class SOLoginTest extends OpstaSOTest {

    public SOLoginTest() {
    }

    @Override
    public void setUp() {
        odo = new Korisnik();
        ((Korisnik) odo).setKorisnickoIme("test");
        ((Korisnik) odo).setLozinka(PasswordHash.getInstance().hash("test"));
        so = new SOLogin(((Korisnik) odo));
        super.setUp();

    }

    @Override
    public void tearDown() {
        super.tearDown();
    }

    @Override
    @Test
    public void testIzvrsiOperaciju() throws Exception {
        super.testIzvrsiOperaciju();
        assertEquals((Korisnik) odo, (Korisnik) so.getOpstiDomenskiObjekat());
    }

}
