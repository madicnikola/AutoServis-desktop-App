/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so;

import fon.ai.np.mvnautoserviscommonlib.domen.OpstiDomenskiObjekat;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Nikola
 */
public abstract class OpstaSOTest {

    protected OpstiDomenskiObjekat odo;
    protected OpstaSO so;

    public OpstaSOTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        so.getDb().zatvoriKonekciju();
        odo = null;
        so = null;
    }

    /**
     * Test template of IzvrsiOperaciju method, of interface OpstaSO.
     */
    public void testIzvrsiOperaciju() throws Exception {
        izvrsiOperaciju();
        so.getDb().otvoriKonekciju();
    }

    protected void izvrsiOperaciju() throws SQLException, ValidationException {
        System.out.println("izvrsiOperaciju - " + this.getClass().getName());
        so.izvrsenjeSO();
        odo = so.getOpstiDomenskiObjekat();
    }

}
