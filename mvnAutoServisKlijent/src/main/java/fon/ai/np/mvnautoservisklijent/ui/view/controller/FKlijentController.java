/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.ui.view.controller;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoservisklijent.controller.ControllerC;
import fon.ai.np.mvnautoservisklijent.ui.view.FKlijent;
import fon.ai.np.mvnautoservisklijent.ui.view.mode.FormMode;
import fon.ai.np.mvnautoservisklijent.validator.implementations.ClientValidator;
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikola
 */
public class FKlijentController {

    private FKlijent frmKlijent;
    private Klijent klijent;
    private Frame frmMain;
    private ClientValidator validator;

    public void openFKlijent(Frame frmMain, FormMode formMode) {
        this.frmMain = frmMain;
        validator = new ClientValidator();
        initFKlijent(frmMain, formMode);

    }

    public void openFKlijent(FormMode formMode, Klijent klijent) {
        frmKlijent = new FKlijent(frmMain, true);
        setValues(klijent);
        prepareForm(formMode);
        addListeners();
        frmKlijent.pack();
        frmKlijent.setVisible(true);
        frmKlijent.setLocationRelativeTo(null);
    }

    private void setValues(Klijent klijent) {
        this.klijent = klijent;
        frmKlijent.getTxtKlijentID().setText(String.valueOf(klijent.getKlijentID()));
        frmKlijent.getTxtIme().setText(klijent.getIme());
        frmKlijent.getTxtPrezime().setText(klijent.getPrezime());
        frmKlijent.getTxtJMBG().setText(klijent.getJMBG());
        frmKlijent.getTxtAdresa().setText(klijent.getAdresa());
        frmKlijent.getTxtBrojTelefona().setText(klijent.getTelefon());
        frmKlijent.getTxtEmail().setText(klijent.getEmail());
    }

    private void addListeners() {
        frmKlijent.getBtnSacuvaj().addActionListener(e -> sacuvajKlijenta());
        frmKlijent.getBtnOmoguciIzmenu().addActionListener(e -> omoguciIzmenu());
        frmKlijent.getBtnSacuvajPromene().addActionListener(e -> sacuvajPromene());
        frmKlijent.getBtnObrisi().addActionListener(e -> obrisi());
    }

    private void kreirajKlijenta() {
        klijent = new Klijent();
    }

    private void prepareForm(FormMode formMode) {
        if (formMode.equals(FormMode.FORM_ADD)) {
            frmKlijent.setTitle("Unos klijenta");
            frmKlijent.getTxtKlijentID().setVisible(false);
            frmKlijent.getLblKlijentID().setVisible(false);
            frmKlijent.getBtnOmoguciIzmenu().setVisible(false);
            frmKlijent.getBtnSacuvajPromene().setVisible(false);
            frmKlijent.getBtnObrisi().setVisible(false);
        }
        if (formMode.equals(FormMode.FORM_VIEW)) {
            frmKlijent.getBtnSacuvajPromene().setVisible(false);
            frmKlijent.getBtnSacuvaj().setVisible(false);
            frmKlijent.getBtnObrisi().setVisible(false);
            disableTxt();

        }
        if (formMode.equals(FormMode.FORM_UPDATE)) {
            frmKlijent.setTitle("Izmena klijenta");

            frmKlijent.getBtnSacuvajPromene().setVisible(true);
            frmKlijent.getBtnOmoguciIzmenu().setVisible(false);
            frmKlijent.getBtnSacuvaj().setVisible(false);
            frmKlijent.getBtnObrisi().setVisible(false);

            enableTxt();
        }
        if (formMode.equals(FormMode.FORM_DELETE)) {
            frmKlijent.getBtnSacuvajPromene().setVisible(false);
            frmKlijent.getBtnSacuvaj().setVisible(false);
            frmKlijent.getBtnOmoguciIzmenu().setVisible(false);
            frmKlijent.getBtnObrisi().setVisible(true);

            disableTxt();

        }
    }

    private void disableTxt() {
        frmKlijent.getTxtKlijentID().setEditable(false);
        frmKlijent.getTxtIme().setEditable(false);
        frmKlijent.getTxtPrezime().setEditable(false);
        frmKlijent.getTxtJMBG().setEditable(false);
        frmKlijent.getTxtBrojTelefona().setEditable(false);
        frmKlijent.getTxtEmail().setEditable(false);
        frmKlijent.getTxtAdresa().setEditable(false);
    }

    private void sacuvajKlijenta() {
        klijent = new Klijent(0, frmKlijent.getTxtIme().getText(), frmKlijent.getTxtPrezime().getText(), frmKlijent.getTxtAdresa().getText(), frmKlijent.getTxtJMBG().getText(), frmKlijent.getTxtBrojTelefona().getText(), frmKlijent.getTxtEmail().getText());
        try {
            validator.validate(klijent);
            klijent = ControllerC.getInstance().sacuvajKlijenta(klijent);
            resetujPoljaZaUnos();
            JOptionPane.showMessageDialog(frmKlijent, "Sistem je zapamtio klijenta.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(FKlijentController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frmKlijent, "Sistem ne moze da zapamti klijenta.", "Greška", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void omoguciIzmenu() {
        prepareForm(FormMode.FORM_UPDATE);
    }

    private void sacuvajPromene() {
        klijent = new Klijent(Integer.valueOf(frmKlijent.getTxtKlijentID().getText().trim()), frmKlijent.getTxtIme().getText(), frmKlijent.getTxtPrezime().getText(), frmKlijent.getTxtAdresa().getText(), frmKlijent.getTxtJMBG().getText(), frmKlijent.getTxtBrojTelefona().getText(), frmKlijent.getTxtEmail().getText());
        try {
            klijent = ControllerC.getInstance().updateKlijenta(klijent);
            resetujPoljaZaUnos();
            JOptionPane.showMessageDialog(frmKlijent, "Sistem je izmenio klijenta.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
            frmKlijent.dispose();

        } catch (Exception ex) {
            Logger.getLogger(FKlijentController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frmKlijent, "Sistem ne može da zapamti klijenta.\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void resetujPoljaZaUnos() {
        frmKlijent.getTxtIme().setText("");
        frmKlijent.getTxtPrezime().setText("");
        frmKlijent.getTxtAdresa().setText("");
        frmKlijent.getTxtJMBG().setText("");
        frmKlijent.getTxtBrojTelefona().setText("");
        frmKlijent.getTxtEmail().setText("");
    }

    private void initFKlijent(Frame frmMain, FormMode formMode) {
        frmKlijent = new FKlijent(frmMain, true);
        kreirajKlijenta();
        prepareForm(formMode);
        addListeners();
        frmKlijent.setLocationRelativeTo(null);
        frmKlijent.pack();
        frmKlijent.setVisible(true);
    }

    private void enableTxt() {
        frmKlijent.getTxtKlijentID().setEditable(false);
        frmKlijent.getTxtIme().setEditable(true);
        frmKlijent.getTxtPrezime().setEditable(true);
        frmKlijent.getTxtJMBG().setEditable(true);
        frmKlijent.getTxtBrojTelefona().setEditable(true);
        frmKlijent.getTxtEmail().setEditable(true);
        frmKlijent.getTxtAdresa().setEditable(true);
    }

    private void obrisi() {
        klijent = new Klijent(Integer.valueOf(frmKlijent.getTxtKlijentID().getText().trim()), frmKlijent.getTxtIme().getText(), frmKlijent.getTxtPrezime().getText(), frmKlijent.getTxtAdresa().getText(), frmKlijent.getTxtJMBG().getText(), frmKlijent.getTxtBrojTelefona().getText(), frmKlijent.getTxtEmail().getText());
        try {
            klijent = ControllerC.getInstance().deleteKlijenta(klijent);
            resetujPoljaZaUnos();
            JOptionPane.showMessageDialog(frmKlijent, "Sistem je obrisao klijenta.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
            frmKlijent.dispose();

        } catch (Exception ex) {
            Logger.getLogger(FKlijentController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frmKlijent, "Sistem ne može da obrise klijenta.\n", "Greška", JOptionPane.ERROR_MESSAGE);

        }
    }
}
