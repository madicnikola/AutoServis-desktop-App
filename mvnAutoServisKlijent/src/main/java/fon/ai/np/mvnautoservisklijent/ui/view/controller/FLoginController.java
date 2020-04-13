/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.ui.view.controller;

import fon.ai.np.mvnautoserviscommonlib.domen.Korisnik;
import fon.ai.np.mvnautoservisklijent.controller.ControllerC;
import fon.ai.np.mvnautoservisklijent.coordinator.GUICoordinator;
import fon.ai.np.mvnautoservisklijent.ui.view.FLogin;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikola
 */
public class FLoginController {

    FLogin frmLogin;
    Korisnik korisnik;

    public void otvoriFLogin() {
        frmLogin = new FLogin();
        kreirajKorinika();

        frmLogin.getTxtKorisnickoIme().setText("admin");
        frmLogin.getTxtLozinka().setText("123");

        frmLogin.getBtnUloguj().addActionListener(e -> ulogujKorinika());
        frmLogin.getBtnOdustani().addActionListener(e -> odustani());
        frmLogin.getTxtKorisnickoIme().addActionListener(e -> ulogujKorinika());
        frmLogin.getTxtLozinka().addActionListener(e -> ulogujKorinika());

        frmLogin.pack();
        frmLogin.setLocationRelativeTo(null);
        frmLogin.setVisible(true);
    }

    private void kreirajKorinika() {
        korisnik = new Korisnik();
    }

    private void ulogujKorinika() {
        frmLogin.getLblGreskaKorisnicko().setText("");
        frmLogin.getLblGreskaLozinka().setText("");

        String korisnickoIme = frmLogin.getTxtKorisnickoIme().getText().trim();
        String lozinka = String.valueOf(frmLogin.getTxtLozinka().getPassword());

        if (korisnickoIme.isEmpty()) {
            frmLogin.getLblGreskaKorisnicko().setText("Korisnicko ime je obavezno");
            return;
        }
        if (lozinka.isEmpty()) {
            frmLogin.getLblGreskaLozinka().setText("Lozinka je obavezna");
            return;
        }
        korisnik.setKorisnickoIme(korisnickoIme);
        korisnik.setLozinka(lozinka);

        try {
            korisnik = ControllerC.getInstance().login(korisnik);
            JOptionPane.showMessageDialog(frmLogin, "Zaposleni je uspešno prijavljen u sistem", "Uspešno prijavljivanje", JOptionPane.INFORMATION_MESSAGE);
            frmLogin.dispose();
            GUICoordinator.getInstance().openMainFrame(korisnik);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmLogin, "Sistem ne može da prijavi zaposlenog na osnovu unetih vrednosti za prijavljivanje.",
                    "Neuspešno prijavljivanje", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }

    private void odustani() {
        frmLogin.dispose();
        System.exit(0);
    }

}
