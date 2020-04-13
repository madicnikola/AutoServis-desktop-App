/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.threads;

import fon.ai.np.mvnautoservisserver.controller.ControllerS;
import fon.ai.np.mvnautoservisserver.util.SettingsLoader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

/**
 *
 * @author Nikola
 */
public class ServerThread extends Thread {

    ServerSocket serverSocket;
    int port;

    public ServerThread() {
    }

    @Override
    public void run() {
        try {
            port = Integer.parseInt(SettingsLoader.getInstance().getValue("port"));
            serverSocket = new ServerSocket(port);
            System.out.println("Server je pokrenut");
            while (!isInterrupted()) {
                Socket socket = serverSocket.accept();
                ClientThreadHandler klijent = new ClientThreadHandler(socket, ControllerS.getInstance().getClientThreads().size() + 1, LocalTime.now());
                klijent.start();
                System.out.println("Klijent se povezao");
                ControllerS.getInstance().addClient(klijent);
            }
        } catch (IOException ex) {
            System.out.println("Server je zaustavljen");
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public int getPort() {
        return port;
    }

}
