/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.coordinator;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.domen.Korisnik;
import fon.ai.np.mvnautoserviscommonlib.domen.Racun;
import fon.ai.np.mvnautoserviscommonlib.domen.Usluga;
import fon.ai.np.mvnautoservisklijent.ui.view.FMain;
import fon.ai.np.mvnautoservisklijent.ui.view.controller.FKlijentController;
import fon.ai.np.mvnautoservisklijent.ui.view.controller.FLoginController;
import fon.ai.np.mvnautoservisklijent.ui.view.controller.FMainController;
import fon.ai.np.mvnautoservisklijent.ui.view.controller.FPretragaKlijenataController;
import fon.ai.np.mvnautoservisklijent.ui.view.controller.FPretragaRacunaController;
import fon.ai.np.mvnautoservisklijent.ui.view.controller.FPretragaUslugaController;
import fon.ai.np.mvnautoservisklijent.ui.view.controller.FRacunController;
import fon.ai.np.mvnautoservisklijent.ui.view.controller.FUslugaController;
import fon.ai.np.mvnautoservisklijent.ui.view.mode.FormMode;
import java.awt.Frame;

/**
 *
 * @author Nikola
 */
public class GUICoordinator {

    private static GUICoordinator instance;
    private FLoginController fLoginController;
    private FMainController fMainController;
    private FKlijentController fKlijentController;
    private FPretragaKlijenataController fPretragaKlijenataController;
    private FUslugaController fUslugaController;
    private FPretragaUslugaController fPretragaUslugaController;
    private FRacunController fRacunController;
    private FPretragaRacunaController fPretragaRacunaController;

    private GUICoordinator() {
    }

    public static GUICoordinator getInstance() {
        if (instance == null) {
            instance = new GUICoordinator();
        }

        return instance;
    }

    public void otvoriLogin() {
        if (fLoginController == null) {
            fLoginController = new FLoginController();
        }
        fLoginController.otvoriFLogin();
    }

    public void openMainFrame(Korisnik korisnik) {
        if (fMainController == null) {
            fMainController = new FMainController(korisnik);
        }
    }

    public void openKlijentFrame(Frame frmMain, FormMode formMode) {
        if (fKlijentController == null) {
            fKlijentController = new FKlijentController();
        }
        fKlijentController.openFKlijent(frmMain, formMode);
    }

    public void openKlijentFrame(FormMode formMode, Klijent klijent) {
        if (fKlijentController == null) {
            fKlijentController = new FKlijentController();
        }
        fKlijentController.openFKlijent(formMode, klijent);
    }

    public void openPretragaKlijenata(FMain frmMain, FormMode formMode) {
        if (fPretragaKlijenataController == null) {
            fPretragaKlijenataController = new FPretragaKlijenataController();
        }
        fPretragaKlijenataController.openFPretragaKlijenata(frmMain, formMode);
    }

    public void openUslugaFrame(FMain frmMain, FormMode formMode) {
        if (fUslugaController == null) {
            fUslugaController = new FUslugaController();
        }
        fUslugaController.openFUsluga(frmMain, formMode);
    }

    public void openPretragaUsluga(FMain frmMain, FormMode formMode) {
        if (fPretragaUslugaController == null) {
            fPretragaUslugaController = new FPretragaUslugaController();
        }
        fPretragaUslugaController.openFPretragaUsluga(frmMain, formMode);
    }

    public void openUslugaFrame(FormMode formMode, Usluga usluga) {
        if (fUslugaController == null) {
            fUslugaController = new FUslugaController();
        }
        fUslugaController.openFUsluga(formMode, usluga);
    }

    public void opeRacunFrame(FMain frmMain, FormMode formMode, Korisnik korisnik) {
        if (fRacunController == null) {
            fRacunController = new FRacunController();
        }
        fRacunController.openFRacun(frmMain, formMode, korisnik);
    }

    public void openPretragaRacuna(FMain frmMain, FormMode formMode) {
        if (fPretragaRacunaController == null) {
            fPretragaRacunaController = new FPretragaRacunaController();
        }
        fPretragaRacunaController.openFPretragaRacuna(frmMain, formMode);
    }

    public void openRacunFrame(FormMode formMode, Racun racun) {
        if (fRacunController == null) {
            fRacunController = new FRacunController();
        }
        fRacunController.openFRacun(formMode, racun);
    }

    public FMainController getfMainController() {
        return fMainController;
    }
    
    
    
}
