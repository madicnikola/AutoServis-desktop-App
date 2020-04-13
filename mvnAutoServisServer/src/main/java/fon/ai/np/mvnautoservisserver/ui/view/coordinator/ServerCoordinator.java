/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.ui.view.coordinator;

import fon.ai.np.mvnautoservisserver.threads.ServerThread;
import fon.ai.np.mvnautoservisserver.ui.view.controller.FConfigDatabaseController;
import fon.ai.np.mvnautoservisserver.ui.view.controller.FConfigServerController;
import fon.ai.np.mvnautoservisserver.ui.view.controller.FServerController;

/**
 *
 * @author Nikola
 */
public class ServerCoordinator {

    private static ServerCoordinator instance;
    private FServerController serverController;
    private FConfigServerController configServerController;
    private FConfigDatabaseController configDatabaseController;

    private ServerCoordinator() {
    }

    public static ServerCoordinator getInstance() {
        if (instance == null) {
            instance = new ServerCoordinator();
        }
        return instance;
    }

    public void otvoriFServer() {
        if (serverController == null) {
            serverController = new FServerController();
        }
        serverController.otvoriFServer();
    }

    public void osveziFServer() {
        if (serverController != null) {
            serverController.refreshTableClients();
        }
    }

    public void openFConfigServer(ServerThread serverThread) {
        if (configServerController == null) {
            configServerController = new FConfigServerController();
        }
        configServerController.otvoriFConfigServer(serverController.getFrmServer(), serverThread, serverController);

    }

    public void openFConfigDatabase() {
        if (configDatabaseController == null) {
            configDatabaseController = new FConfigDatabaseController();
        }
        configDatabaseController.otvoriFConfigDatabase(serverController);
    }
}
