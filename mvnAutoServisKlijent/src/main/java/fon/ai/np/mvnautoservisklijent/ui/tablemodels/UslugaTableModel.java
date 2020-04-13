/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.ui.tablemodels;

import fon.ai.np.mvnautoserviscommonlib.domen.Usluga;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola
 */
public class UslugaTableModel extends AbstractTableModel {

    private final List<Usluga> usluge;

    private String[] columnNames = new String[]{"ID", "Naziv", "Vrednost", "Opis"};
    private Class[] columnClasses = new Class[]{Integer.class, String.class, Double.class, String.class};

    public UslugaTableModel(List<Usluga> usluge) {
        this.usluge = usluge;
    }

    @Override
    public int getRowCount() {
        return usluge.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usluga u = usluge.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getProizvodID();
            case 1:
                return u.getNaziv();
            case 2:
                return u.getVrednost();
            case 3:
                return u.getOpisUsluge();
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

    @Override
    public void setValueAt(Object objectAt, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 1:
                usluge.get(rowIndex).setNaziv((String) objectAt);
                break;
            case 2:
                usluge.get(rowIndex).setVrednost((double) objectAt);
                break;
            case 3:
                usluge.get(rowIndex).setOpisUsluge((String) objectAt);
                break;

        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Usluga vratiUslugu(int red) {
        return usluge.get(red);
    }

}
