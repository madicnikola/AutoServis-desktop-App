/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.ui.view.controller;

import fon.ai.np.mvnautoserviscommonlib.domen.Usluga;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisklijent.controller.ControllerC;
import fon.ai.np.mvnautoservisklijent.coordinator.GUICoordinator;
import fon.ai.np.mvnautoservisklijent.ui.view.FMain;
import fon.ai.np.mvnautoservisklijent.ui.view.FUsluga;
import fon.ai.np.mvnautoservisklijent.ui.view.mode.FormMode;
import fon.ai.np.mvnautoservisklijent.validator.Validator;
import fon.ai.np.mvnautoservisklijent.validator.implementations.UslugaValidator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikola
 */
public class FUslugaController {

    FUsluga frmUsluga;
    FMain frmMain;
    private Usluga usluga;
    private final Validator validator;

    public void openFUsluga(FMain frmMain, FormMode formMode) {
        this.frmMain = frmMain;
        frmUsluga = new FUsluga(frmMain, true);
        kreirajUslugu();
        prepareForm(formMode);
        addListeners();

        frmUsluga.pack();
        frmUsluga.setLocationRelativeTo(null);
        frmUsluga.setVisible(true);
    }

    public void openFUsluga(FormMode formMode, Usluga usluga) {
        frmMain = GUICoordinator.getInstance().getfMainController().getFrmMain();
        frmUsluga = new FUsluga(frmMain, true);

        setValues(usluga);
        addListeners();
        prepareForm(formMode);

        frmUsluga.pack();
        frmUsluga.setLocationRelativeTo(frmMain);
        frmUsluga.setVisible(true);
    }

    public FUslugaController() {
        validator = new UslugaValidator();
    }

    public FUsluga getFrmUsluga() {
        return frmUsluga;
    }

    public void setFrmUsluga(FUsluga frmUsluga) {
        this.frmUsluga = frmUsluga;
    }

    private void kreirajUslugu() {
        usluga = new Usluga();
    }

    private void addListeners() {
        frmUsluga.getBtnSacuvaj().addActionListener(e -> sacuvajUslugu());
        frmUsluga.getBtnSacuvajPromene().addActionListener(e -> sacuvajPromene());
        frmUsluga.getBtnOmoguciIzmenu().addActionListener(e -> omoguciIzmenu());
        frmUsluga.getBtnObrisi().addActionListener(e -> obrisi());
    }

    private void prepareForm(FormMode formMode) {
        if (formMode.equals(FormMode.FORM_ADD)) {
            frmUsluga.setTitle("Unos usluge");
            frmUsluga.getTxtUslugaID().setVisible(false);
            frmUsluga.getLblUslugaID().setVisible(false);

            frmUsluga.getBtnOmoguciIzmenu().setVisible(false);
            frmUsluga.getBtnSacuvajPromene().setVisible(false);
            frmUsluga.getBtnObrisi().setVisible(false);

        }
        if (formMode.equals(FormMode.FORM_VIEW)) {
            frmUsluga.getBtnSacuvajPromene().setVisible(false);
            frmUsluga.getBtnSacuvaj().setVisible(false);
            frmUsluga.getBtnObrisi().setVisible(false);

            disableTxtFields();
        }
        if (formMode.equals(FormMode.FORM_UPDATE)) {
            frmUsluga.setTitle("Izmena usluga");

            frmUsluga.getBtnSacuvajPromene().setVisible(true);
            frmUsluga.getBtnOmoguciIzmenu().setVisible(false);
            frmUsluga.getBtnSacuvaj().setVisible(false);
            frmUsluga.getBtnObrisi().setVisible(false);

            enableTxtFields();
        }
        if (formMode.equals(FormMode.FORM_DELETE)) {
            frmUsluga.setTitle("Brisanje usluge");

            frmUsluga.getBtnSacuvajPromene().setVisible(false);
            frmUsluga.getBtnSacuvaj().setVisible(false);
            frmUsluga.getBtnOmoguciIzmenu().setVisible(false);
            frmUsluga.getBtnObrisi().setVisible(true);

            disableTxtFields();
        }
    }

    private void sacuvajUslugu() {
        try {
            Double.valueOf(frmUsluga.getTxtVrednost().getText().trim());
            Usluga u = new Usluga(0, frmUsluga.getTxtNaziv().getText().trim(), Double.valueOf(frmUsluga.getTxtVrednost().getText().trim()), frmUsluga.getTxtOpis().getText().trim());
            validator.validate(u);
            ControllerC.getInstance().sacuvajUslugu(u);
            JOptionPane.showMessageDialog(frmUsluga, "Sistem je zapamtio uslugu.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(frmUsluga, "Sistem ne moze da zapamti novu uslugu", "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(frmUsluga, "Sistem ne moze da zapamti novu uslugu", "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmUsluga, ex.getMessage());
        }

    }

    private void sacuvajPromene() {
        usluga = new Usluga(Integer.valueOf(frmUsluga.getTxtUslugaID().getText().trim()), frmUsluga.getTxtNaziv().getText(), Double.valueOf(frmUsluga.getTxtVrednost().getText()), frmUsluga.getTxtOpis().getText());
        try {
            usluga = ControllerC.getInstance().updateUslugu(usluga);
            resetujPoljaZaUnos();
            JOptionPane.showMessageDialog(frmUsluga, "Sistem je izmenio uslugu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
            frmUsluga.dispose();

        } catch (Exception ex) {
            Logger.getLogger(FKlijentController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frmUsluga, "Sistem ne može da zapamti uslugu.\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void omoguciIzmenu() {
        prepareForm(FormMode.FORM_UPDATE);
    }

    private void setValues(Usluga usluga) {
        this.usluga = usluga;
        frmUsluga.getTxtNaziv().setText(usluga.getNaziv());
        frmUsluga.getTxtUslugaID().setText(String.valueOf(usluga.getProizvodID()));
        frmUsluga.getTxtVrednost().setText(String.valueOf(usluga.getVrednost()));
        frmUsluga.getTxtOpis().setText(usluga.getOpisUsluge());
    }

    private void obrisi() {
        usluga = new Usluga(Integer.valueOf(frmUsluga.getTxtUslugaID().getText().trim()), frmUsluga.getTxtNaziv().getText(), Double.valueOf(frmUsluga.getTxtVrednost().getText()), frmUsluga.getTxtOpis().getText().trim());
        try {
            usluga = ControllerC.getInstance().deleteUslugu(usluga);
            JOptionPane.showMessageDialog(frmUsluga, "Sistem je obrisao uslugu.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
            frmUsluga.dispose();

        } catch (Exception ex) {
            Logger.getLogger(FKlijentController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frmUsluga, "Sistem ne može da obriše uslugu.", "Greška", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void disableTxtFields() {
        frmUsluga.getTxtUslugaID().setEditable(false);
        frmUsluga.getTxtNaziv().setEditable(false);
        frmUsluga.getTxtVrednost().setEditable(false);
        frmUsluga.getTxtOpis().setEditable(false);
    }

    private void enableTxtFields() {
        frmUsluga.getTxtUslugaID().setEditable(false);
        frmUsluga.getTxtNaziv().setEditable(true);
        frmUsluga.getTxtVrednost().setEditable(true);
        frmUsluga.getTxtOpis().setEditable(true);
    }

    private void resetujPoljaZaUnos() {
        frmUsluga.getTxtUslugaID().setText("");
        frmUsluga.getTxtNaziv().setText("");
        frmUsluga.getTxtVrednost().setText("");
        frmUsluga.getTxtOpis().setText("");
    }

}
