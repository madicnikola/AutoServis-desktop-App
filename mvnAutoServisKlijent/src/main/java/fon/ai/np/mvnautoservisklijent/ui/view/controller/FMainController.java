/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.ui.view.controller;

import fon.ai.np.mvnautoserviscommonlib.domen.Korisnik;
import fon.ai.np.mvnautoservisklijent.controller.ControllerC;
import fon.ai.np.mvnautoservisklijent.coordinator.GUICoordinator;
import fon.ai.np.mvnautoservisklijent.ui.view.FMain;
import fon.ai.np.mvnautoservisklijent.ui.view.mode.FormMode;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Nikola
 */
public class FMainController {

    private FMain frmMain;
    private Korisnik korisnik;

    public FMainController(Korisnik korisnik) {
        this.korisnik = korisnik;
        openFMain(korisnik);
    }

    public void openFMain(Korisnik korisnik) {
        frmMain = new FMain();
        frmMain.getLblTrenutniKorisnik().setText(korisnik.getIme() + " " + korisnik.getPrezime());
        addListeners();
        frmMain.pack();
        frmMain.setLocationRelativeTo(null);
        frmMain.setExtendedState(MAXIMIZED_BOTH);
        frmMain.setVisible(true);
    }

    private void addListeners() {

        frmMain.getjMenuItemDodajKlijenta().addActionListener(e -> unosKlijenta());
        frmMain.getjMenuItemIzmeniKlijenta().addActionListener(e -> pretragaKlijenata(FormMode.FORM_UPDATE));
        frmMain.getjMenuItemPretraziKlijenta().addActionListener(e -> pretragaKlijenata(FormMode.FORM_VIEW));
        frmMain.getjMenuItemIzbrisiKlijenta().addActionListener(e -> pretragaKlijenata(FormMode.FORM_DELETE));

        frmMain.getjMenuItemDodajNovuUslugu().addActionListener(e -> unosUsluge());
        frmMain.getjMenuItemPretraziUsluge().addActionListener(e -> pretragaUsluga(FormMode.FORM_VIEW));
        frmMain.getjMenuItemIzmeniUslugu().addActionListener(e -> pretragaUsluga(FormMode.FORM_UPDATE));
        frmMain.getjMenuItemIzbrisiUslugu().addActionListener(e -> pretragaUsluga(FormMode.FORM_DELETE));

        frmMain.getjMenuItemKreirajNoviRacun().addActionListener(e -> kreirajRacun(FormMode.FORM_ADD));
        frmMain.getjMenuItemPretraziRacune().addActionListener(e -> pretragaRacuna(FormMode.FORM_VIEW));
        frmMain.getjMenuItemStornirajRacun().addActionListener(e -> pretragaRacuna(FormMode.FORM_UPDATE));

        frmMain.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ControllerC.getInstance().logout();
            }
        });

    }

    private void unosKlijenta() {
        GUICoordinator.getInstance().openKlijentFrame(frmMain, FormMode.FORM_ADD);
    }

    private void pretragaKlijenata(FormMode formMode) {
        GUICoordinator.getInstance().openPretragaKlijenata(frmMain, formMode);

    }

    private void unosUsluge() {
        GUICoordinator.getInstance().openUslugaFrame(frmMain, FormMode.FORM_ADD);
    }

    private void pretragaUsluga(FormMode formMode) {
        GUICoordinator.getInstance().openPretragaUsluga(frmMain, formMode);
    }

    private void kreirajRacun(FormMode formMode) {
        GUICoordinator.getInstance().opeRacunFrame(frmMain, formMode, korisnik);
    }

    public FMain getFrmMain() {
        return frmMain;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    private void pretragaRacuna(FormMode formMode) {
        GUICoordinator.getInstance().openPretragaRacuna(frmMain, formMode);
    }

}
