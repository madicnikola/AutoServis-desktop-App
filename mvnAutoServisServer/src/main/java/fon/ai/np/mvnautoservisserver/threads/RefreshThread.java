
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.threads;

import fon.ai.np.mvnautoservisserver.ui.view.coordinator.ServerCoordinator;

/**
 *
 * @author Nikola
 */
public class RefreshThread extends Thread {

    @Override
    public void run() {
        while (!isInterrupted()) {
            ServerCoordinator.getInstance().osveziFServer();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }
    }

}
