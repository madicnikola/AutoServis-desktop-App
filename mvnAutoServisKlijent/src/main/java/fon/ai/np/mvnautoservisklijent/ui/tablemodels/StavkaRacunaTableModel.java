/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.ui.tablemodels;

import fon.ai.np.mvnautoserviscommonlib.domen.StavkaRacuna;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola
 */
public class StavkaRacunaTableModel extends AbstractTableModel {

    private final List<StavkaRacuna> listaStavki;

    private final String[] columnNames = new String[]{"Redni broj", "Proizvod", "Vrednost", "Kolicina"};
    private final Class[] columnClasses = new Class[]{Integer.class, String.class, Double.class, Integer.class};

    public StavkaRacunaTableModel(List<StavkaRacuna> listaStavki) {
        this.listaStavki = listaStavki;
    }

    @Override
    public int getRowCount() {
        return listaStavki.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRacuna stavka = listaStavki.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getRBStavke();
            case 1:
                return stavka.getProizvod();
            case 2:
                return stavka.getVrednostStavke();
            case 3:
                return stavka.getKolicina();
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

    public StavkaRacuna vratiStavku(int red) {
        return listaStavki.get(red);
    }

    public void obrisiStavku(int index) {
        listaStavki.remove(index);
        fireTableDataChanged();
    }

    public void unesiStavku(StavkaRacuna stavka) {
        listaStavki.add(stavka);
        fireTableDataChanged();
    }
}
