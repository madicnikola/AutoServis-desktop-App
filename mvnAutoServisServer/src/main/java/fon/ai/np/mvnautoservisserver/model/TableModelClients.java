/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.model;

import fon.ai.np.mvnautoservisserver.threads.ClientThreadHandler;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola
 */
public class TableModelClients extends AbstractTableModel {

    private List<ClientThreadHandler> clients;
    private String[] columns = {"RB", "KLIJENT", "VREME POVEZIVANJA"};
    private DateTimeFormatter dtf;
    private Class[] columnClasses = {Integer.class, String.class, String.class};

    public TableModelClients(List<ClientThreadHandler> clients) {
        this.clients = clients;
        dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        ClientThreadHandler client = clients.get(row);

        switch (column) {
            case 0:
                return client.getRb();
            case 1:
                return "Klijent" + client.getRb() + " ("
                        + client.getSocket().getInetAddress().getHostName() + ")";
            case 2:
                return client.getTime().format(dtf);
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return columnClasses[column];
    }

    public void setClients(List<ClientThreadHandler> clients) {
        this.clients = clients;
        fireTableDataChanged();
    }

    public void editRb() {
        int counter = 0;
        for (ClientThreadHandler client : clients) {
            client.setRb(++counter);
        }
        fireTableDataChanged();
    }

}
