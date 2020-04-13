/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.ui.view.controller;

import fon.ai.np.mvnautoservisserver.controller.ControllerS;
import fon.ai.np.mvnautoservisserver.db.constants.Constants;
import fon.ai.np.mvnautoservisserver.model.TableModelClients;
import fon.ai.np.mvnautoservisserver.threads.RefreshThread;
import fon.ai.np.mvnautoservisserver.threads.ServerThread;
import fon.ai.np.mvnautoservisserver.ui.view.FServer;
import fon.ai.np.mvnautoservisserver.ui.view.coordinator.ServerCoordinator;
import fon.ai.np.mvnautoservisserver.util.SettingsLoader;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Nikola
 */
public class FServerController {

    private FServer frmServer;
    private ServerThread serverThread;

    public void otvoriFServer() {
        frmServer = new FServer();
        prepareForm();
        prepareTableClients();

        frmServer.getjMenuItemStart().addActionListener(e -> startServer());
        frmServer.getjMenuItemStop().addActionListener(e -> stopServer());

        frmServer.getjMenuItemServerConfig().addActionListener(e -> configServer());
        frmServer.getjMenuItemDatabaseConfig().addActionListener(e -> configDatabase());

        frmServer.getBtnZatvori().addActionListener(e -> zatvoriServer());
        frmServer.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent evt) {
                startServer();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                stopServer();
            }

        });

        RefreshThread refresh = new RefreshThread();
        refresh.start();

        frmServer.pack();
        frmServer.setVisible(true);
    }

    private void prepareForm() {

    }

    private void prepareTableClients() {
        TableModelClients tmc = new TableModelClients(ControllerS.getInstance().getClientThreads());
        frmServer.getTblClients().setModel(tmc);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        frmServer.getTblClients().setDefaultRenderer(Integer.class, centerRenderer);

    }

    private void startServer() {
        if (serverThread == null || !serverThread.isAlive()) {
            serverThread = new ServerThread();
            serverThread.start();
            frmServer.getjMenuItemStart().setEnabled(false);
            frmServer.getjMenuItemStop().setEnabled(true);
            frmServer.getTxtStatusServera().setText("Server je pokrenut");
        }
    }

    private void stopServer() {
        if (serverThread != null && serverThread.getServerSocket().isBound()) {
            try {
                ControllerS.getInstance().logoutClients();
                serverThread.interrupt();
                serverThread.getServerSocket().close();
                frmServer.getjMenuItemStart().setEnabled(true);
                frmServer.getTxtStatusServera().setText("Server je zaustavljen");
            } catch (IOException ex) {
                System.out.println("Greska prilikom zatvaranja serverskog soketa.");
            }
        }

    }

    public void refreshTableClients() {
        TableModelClients tmc = (TableModelClients) frmServer.getTblClients().getModel();
        tmc.setClients(ControllerS.getInstance().getClientThreads());
        tmc.editRb();
    }

    private void zatvoriServer() {
        stopServer();
        System.exit(0);
    }

    private void configServer() {
        ServerCoordinator.getInstance().openFConfigServer(serverThread);
    }

    private void configDatabase() {
        ServerCoordinator.getInstance().openFConfigDatabase();
    }

    public FServer getFrmServer() {
        return frmServer;
    }

    public ServerThread getServerThread() {
        return serverThread;
    }

    public void applyPortNumber(Integer port) {
        String url = SettingsLoader.getInstance().getValue(Constants.URL);
        String user = SettingsLoader.getInstance().getValue(Constants.USER);
        String pass = SettingsLoader.getInstance().getValue(Constants.PASS);
        String props = String.format("url = %s\n"
                + "user=%s\n"
                + "pass=%s\n"
                + "port=%d", url, user, pass, port);
        try (PrintWriter out = new PrintWriter("settings.properties")) {
            out.print(props);
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        }
        SettingsLoader.getInstance().loadProperties();

    }

    public void restartServer() {
        if (JOptionPane.showConfirmDialog(frmServer, "Da li zelite da restartujete server?") == 0) {
            if (frmServer.getjMenuItemStart().isEnabled()) {
                startServer();
            } else {
                stopServer();
                startServer();
            }
        }
    }

}
