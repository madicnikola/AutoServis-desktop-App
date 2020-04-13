/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.ui.tablemodels;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.domen.Korisnik;
import fon.ai.np.mvnautoserviscommonlib.domen.Racun;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola
 */
public class RacunTableModel extends AbstractTableModel {

    private final List<Racun> listaRacuna;

    private final String[] columnNames = new String[]{"ID", "Klijent", "Datum", "Vrednost", "Storniran", "Fakturisao"};
    private final Class[] columnClasses = new Class[]{Integer.class, Klijent.class, String.class, Double.class, Boolean.class, Korisnik.class};

    public RacunTableModel(List<Racun> listaRacuna) {
        this.listaRacuna = listaRacuna;
    }

    @Override
    public int getRowCount() {
        return listaRacuna.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Racun racun = listaRacuna.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return racun.getRacunID();
            case 1:
                return racun.getKlijent();
            case 2:
                return new SimpleDateFormat("dd.MM.yyyy").format(racun.getDatum());
            case 3:
                return racun.getUkupnaVrednost();
            case 4:
                return racun.isStorniran();
            case 5:
                return racun.getKorisnik();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    public Racun vratiRacun(int red) {
        return listaRacuna.get(red);
    }

    public void obrisiRacun(int index) {
        listaRacuna.remove(index);
        fireTableDataChanged();
    }

    public void unesiRacun(Racun racun) {
        listaRacuna.add(racun);
        fireTableDataChanged();
    }
}
