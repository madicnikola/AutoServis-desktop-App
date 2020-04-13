/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.db;

import fon.ai.np.mvnautoserviscommonlib.domen.OpstiDomenskiObjekat;
import fon.ai.np.mvnautoservisserver.db.constants.Constants;
import fon.ai.np.mvnautoservisserver.util.SettingsLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class DBBroker {

    private Connection connection;

    public void otvoriKonekciju() {
        try {
            String url = SettingsLoader.getInstance().getValue(Constants.URL);
            String user = SettingsLoader.getInstance().getValue(Constants.USER);
            String pass = SettingsLoader.getInstance().getValue(Constants.PASS);
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("Greska prilikom uspostavljanja konekcije sa bazom: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void zatvoriKonekciju() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Greska prilikom zatvaranja konekcije sa bazom: " + ex.getMessage());
            }
        }

    }

    public void commit() {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException ex) {
                System.out.println("Greska prilikom commit-a transakcije: " + ex.getMessage());
            }
        }
    }

    public void rollback() {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Greska prilikom rollback-a transakcije: " + ex.getMessage());
            }
        }
    }

    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat odo, String filter) throws SQLException {
        try {
            String query = "SELECT * FROM "
                    + odo.vratiNazivTabele()
                    + " " + odo.vratiJoinUpit()
                    + odo.vratiUslovZaListuObjekata()
                    + filter;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return odo.ucitajSve(rs);
        } catch (SQLException ex) {
            throw new SQLException("Greska prilikom ucitavanja objekata " + odo.vratiNazivTabele(), ex);
        }
    }

    public OpstiDomenskiObjekat vratiJedan(OpstiDomenskiObjekat odo) throws SQLException {
        try {
            String query = "SELECT * FROM "
                    + odo.vratiNazivTabele()
                    + " " + odo.vratiJoinUpit()
                    + " WHERE " + odo.vratiUslovZaNadjiSlog();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return odo.ucitajJedan(rs);
        } catch (SQLException ex) {
            throw new SQLException("Greska prilikom ucitavanja objekta " + odo.vratiNazivTabele(), ex);
        }
    }

    public OpstiDomenskiObjekat zapamti(OpstiDomenskiObjekat odo) throws SQLException {
        try {
            String query = "INSERT INTO " + odo.vratiNazivTabele()
                    + " (" + odo.vratiAtributeZaInsert() + ") "
                    + "VALUES (" + odo.vratiVrednostiZaInsert() + ")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            if (odo.jesteAutoInkrement()) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    odo.postaviIdObjekta(rs.getInt(1));
                }
            }

            return odo;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Greska prilikom dodavanja objekta " + odo.vratiNazivTabele(), ex);
        }
    }

    public void obrisi(OpstiDomenskiObjekat odo) throws SQLException {
        try {
            String query = "DELETE FROM " + odo.vratiNazivTabele()
                    + " WHERE " + odo.vratiUslovZaNadjiSlog();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw new SQLException("Greska prilikom brisanja objekta " + odo.vratiNazivTabele(), ex);
        }
    }

    public void izmeni(OpstiDomenskiObjekat odo) throws SQLException {
        try {
            String query = "UPDATE " + odo.vratiNazivTabele()
                    + " SET " + odo.vratiSetZaIzmenu()
                    + " WHERE " + odo.vratiUslovZaNadjiSlog();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw new SQLException("Greska prilikom izmene objekta " + odo.vratiNazivTabele(), ex);
        }
    }

}
