/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.ui.view.controller;

import fon.ai.np.mvnautoservisserver.threads.ServerThread;
import fon.ai.np.mvnautoservisserver.ui.view.FConfigServer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikola
 */
public class FConfigServerController {

    FConfigServer frmConfigServer;
    FServerController serverController;

    public void otvoriFConfigServer() {
    }

    public void otvoriFConfigServer(JFrame main, ServerThread serverThread, FServerController fServerController) {
        this.serverController = fServerController;

        frmConfigServer = new FConfigServer(main, true);
        frmConfigServer.getTxtPort().setText(String.valueOf(serverThread.getServerSocket().getLocalPort()));

        addListeners();
        frmConfigServer.setLocationRelativeTo(main);
        frmConfigServer.setVisible(true);
    }

    private void addListeners() {
        frmConfigServer.getBtnApply().addActionListener(e -> applyPortNumber());
        frmConfigServer.getBtnDiscard().addActionListener(e -> frmConfigServer.dispose());
    }

    private void applyPortNumber() {
        try {
            serverController.applyPortNumber(Integer.valueOf(frmConfigServer.getTxtPort().getText().trim()));
            JOptionPane.showMessageDialog(frmConfigServer, "sistem je uspesno promenio port.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            serverController.restartServer();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmConfigServer, "sistem ne moze da promeni port.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

}
