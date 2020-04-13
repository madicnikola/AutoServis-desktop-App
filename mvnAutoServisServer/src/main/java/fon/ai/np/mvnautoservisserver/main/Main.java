/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisserver.main;

import fon.ai.np.mvnautoservisserver.ui.view.coordinator.ServerCoordinator;

/**
 *
 * @author Nikola
 */
public class Main {

    public static void main(String[] args) {
        ServerCoordinator.getInstance().otvoriFServer();
    }
}
