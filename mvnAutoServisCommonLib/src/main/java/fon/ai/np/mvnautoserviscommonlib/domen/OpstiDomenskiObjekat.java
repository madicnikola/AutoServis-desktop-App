/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoserviscommonlib.domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
<<<<<<< HEAD
 * Interfejs koji sadrzi sve potrebne metode za manipulaciju sa opstim domenskim objektima prilikom rada sa bazom.
=======
 * Interfejs koji sadrzi sve potrebne metode za manipulaciju sa opstim domenskim objektima.
>>>>>>> e8c5c9b9e4f6f36f64ce24085962cb7913db4d3a
 * @author Nikola
 * @version 1.1
 */
public interface OpstiDomenskiObjekat extends Serializable {
/**
 * Metoda koja vraca naziv tabele u bazi.
 * @return naziv tabele kao String.
 */
    String vratiNazivTabele();
/**
 * Metoda koja vraca sve atribute za insert sql upit.
 * @return svi atributi objekta za insert sql upit.
 */
    String vratiAtributeZaInsert();
/**
 * Metoda koja vraca sve vrednosti atributa za insert sql upit.
 * @return sve vrednosti atributa za insert sql upit.
 */
    String vratiVrednostiZaInsert();
/**
 * Metoda koja vraca String za join upit.
 * @return vraca String za join sa drugim tabelama potrebnim za sql upit.
 */
    String vratiJoinUpit();
/**
 * Vraca uslov za nalazenje jednog sloga u bazi najcesce preko primarnog kljuca.
 * @return uslov kao String.
 */
    String vratiUslovZaNadjiSlog();
/**
 * Vraca uslov za nalazenje potrebne liste objekata iz baze.
 * @return uslov kao String.
 */
    String vratiUslovZaListuObjekata();
/**
 * Vraca ID objekta.
 * @return ID kao String.
 */
    String vratiID();
/**
 * Vraca set za update sql upit.
 * @return Set kao String.. 
 */
    String vratiSetZaIzmenu();
/**
 * Vraca true ili false u zavisnosti da li je primarni kljuc autoIncrement ili ne.
 * @return jeste ili nije.
 */
    boolean jesteAutoInkrement();
/**
 * Postavlja id koji je prosledjen datom objektu.
 * @param id koji treba postaviti objektu.
 */
    void postaviIdObjekta(int id);
/**
 * Ucitava sve slogove iz baze kao listu.
 * @param rs resultSet koji je dobijen iz baze.
 * @return lista opstih domenskih objekata.
 * @throws SQLException izuzetak u slucaju greske u radu sa bazom. 
 */
    List<OpstiDomenskiObjekat> ucitajSve(ResultSet rs) throws SQLException;
/**
 * Ucitava jedan slog iz baze kao opsti domenski objekat.
 * @param rs resultSet koji je dobijen iz baze.
 * @return opsti domenski objekat.
 * @throws SQLException izuzetak u slucaju greske u radu sa bazom.
 */
    OpstiDomenskiObjekat ucitajJedan(ResultSet rs) throws SQLException;
}
