/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.ui.view.controller;

import fon.ai.np.mvnautoservisserver.db.constants.Constants;
import fon.ai.np.mvnautoservisserver.ui.view.FConfigDatabase;
import fon.ai.np.mvnautoservisserver.util.SettingsLoader;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikola
 */
public class FConfigDatabaseController {

    FConfigDatabase frmConfigDatabase;
    FServerController serverController;

    public void otvoriFConfigDatabase(FServerController serverController) {
        frmConfigDatabase = new FConfigDatabase(serverController.getFrmServer(), true);
        prepareView();
        addListeners();

        frmConfigDatabase.setLocationRelativeTo(serverController.getFrmServer());
        frmConfigDatabase.setVisible(true);
    }

    private void addListeners() {
        frmConfigDatabase.getBtnApply().addActionListener(e -> applyConfig());
        frmConfigDatabase.getBtnCancel().addActionListener(e -> frmConfigDatabase.dispose());

    }

    private void prepareView() {
        frmConfigDatabase.getTxtImeBaze().setText(SettingsLoader.getInstance().getValue(Constants.URL));
        frmConfigDatabase.getTxtUsername().setText(SettingsLoader.getInstance().getValue(Constants.USER));
        frmConfigDatabase.getTxtPassword().setText(SettingsLoader.getInstance().getValue(Constants.PASS));
    }

    private void applyConfig() {
        String user = frmConfigDatabase.getTxtUsername().getText();
        String pass = frmConfigDatabase.getTxtPassword().getText();
        String url = frmConfigDatabase.getTxtImeBaze().getText();
        int port = Integer.valueOf(SettingsLoader.getInstance().getValue(Constants.PORT));

        if (validate(user, url)) {
            return;
        }
        String props = String.format("url = %s\n"
                + "user=%s\n"
                + "pass=%s\n"
                + "port=%d", url, user, pass, port);
        try (PrintWriter out = new PrintWriter("settings.properties")) {
            out.print(props);
            out.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        }
        SettingsLoader.getInstance().loadProperties();
        frmConfigDatabase.dispose();
    }

    private boolean validate(String username, String url) throws HeadlessException {
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(frmConfigDatabase, "Username je obavezno polje");
            return true;
        }
        if (url.isEmpty()) {
            JOptionPane.showMessageDialog(frmConfigDatabase, "Url je obavezno polje");
            return true;
        }
        return false;
    }
}
