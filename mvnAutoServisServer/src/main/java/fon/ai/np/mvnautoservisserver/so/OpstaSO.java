/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.so;

import fon.ai.np.mvnautoserviscommonlib.domen.OpstiDomenskiObjekat;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisserver.db.DBBroker;
import fon.ai.np.mvnautoservisserver.validator.Validator;
import java.sql.SQLException;

/**
 *
 * @author Nikola
 */
public abstract class OpstaSO {

    protected Validator validator;
    protected DBBroker db;
    protected OpstiDomenskiObjekat odo;

    public OpstaSO() {
        db = new DBBroker();
    }

    public final void izvrsenjeSO() throws SQLException, ValidationException {
        try {
            proveriPreduslov();
            otvoriKonekciju();
            izvrsiOperaciju();
            commitTransakcije();
        } catch (SQLException | ValidationException ex) {
            rollbackTransakcije();
            throw ex;
        } finally {
            zatvoriKonekciju();
        }
    }

    public OpstiDomenskiObjekat getOpstiDomenskiObjekat() {
        return odo;
    }

    private void otvoriKonekciju() {
        db.otvoriKonekciju();
    }

    private void commitTransakcije() {
        db.commit();
    }

    private void rollbackTransakcije() {
        db.rollback();
    }

    private void zatvoriKonekciju() {
        db.zatvoriKonekciju();
    }

    protected abstract void proveriPreduslov() throws ValidationException;

    protected abstract void izvrsiOperaciju() throws SQLException;

}
