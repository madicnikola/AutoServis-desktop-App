/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.ui.view.controller;

import fon.ai.np.mvnautoservisklijent.controller.ControllerC;
import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.exception.SelectedItemException;
import java.util.List;
import javax.swing.JOptionPane;
import fon.ai.np.mvnautoservisklijent.ui.tablemodels.KlijentTableModel;
import fon.ai.np.mvnautoservisklijent.ui.view.FMain;
import fon.ai.np.mvnautoservisklijent.ui.view.FPretragaKlijenata;
import fon.ai.np.mvnautoservisklijent.ui.view.mode.FormMode;

/**
 *
 * @author Nikola
 */
public class FPretragaKlijenataController {

    FPretragaKlijenata frmPretragaKlijenta;
    String kriterijum;

    public FPretragaKlijenataController() {
    }

    public void openFPretragaKlijenata(FMain frmMain, FormMode formMode) {
        frmPretragaKlijenta = new FPretragaKlijenata(frmMain, true);
        prepareTableKlijenti();
        prepareForm(formMode);

        frmPretragaKlijenta.getBtnPretrazi().addActionListener(e -> pretraziKlijente());
        frmPretragaKlijenta.getTxtKriterijumPretrage().addActionListener(e -> pretraziKlijente());
        frmPretragaKlijenta.getBtnPrikaziDetalje().addActionListener(e -> prikaziDetalje());
        frmPretragaKlijenta.getBtnIzmeni().addActionListener(e -> izmeni());
        frmPretragaKlijenta.getBtnObrisi().addActionListener(e -> obrisi());

        frmPretragaKlijenta.pack();
        frmPretragaKlijenta.setVisible(true);
    }

    private void prepareTableKlijenti() {
        List<Klijent> lista = ControllerC.getInstance().pretraziKlijente("");
        frmPretragaKlijenta.getTblKlijenti().setModel(new KlijentTableModel(lista));
    }

    private void prepareForm(FormMode formMode) {
        if (formMode.equals(FormMode.FORM_VIEW)) {
            frmPretragaKlijenta.setTitle("Pretraga klijenata");
            frmPretragaKlijenta.getBtnObrisi().setVisible(false);
            frmPretragaKlijenta.getBtnIzmeni().setVisible(false);
        }
        if (formMode.equals(FormMode.FORM_UPDATE)) {
            frmPretragaKlijenta.setTitle("Izmena podataka o klijentima");
            frmPretragaKlijenta.getBtnObrisi().setVisible(false);
            frmPretragaKlijenta.getBtnPrikaziDetalje().setVisible(false);

        }
        if (formMode.equals(FormMode.FORM_DELETE)) {
            frmPretragaKlijenta.getBtnIzmeni().setVisible(false);
            frmPretragaKlijenta.getBtnPrikaziDetalje().setVisible(false);
        }
    }

    private void pretraziKlijente() {
        kriterijum = frmPretragaKlijenta.getTxtKriterijumPretrage().getText().trim();
        String whereUpit = " WHERE ime LIKE '" + kriterijum + "%'" + " or prezime LIKE '" + kriterijum + "%'";
        List<Klijent> lista = ControllerC.getInstance().pretraziKlijente(whereUpit);
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(frmPretragaKlijenta, "Sistem ne može da nađe klijente po zadatoj vrednosti.", "Obaveštenje", JOptionPane.ERROR_MESSAGE);
            return;
        }
        KlijentTableModel ktm = new KlijentTableModel(lista);
        frmPretragaKlijenta.getTblKlijenti().setModel(ktm);
        if (kriterijum.isEmpty()) {
            return;
        }
        JOptionPane.showMessageDialog(frmPretragaKlijenta, "Sistem je našao klijente po zadatoj vrednosti.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
    }

    private void prikaziDetalje() {
        try {
            Klijent klijent = getSelectedObject();
            ControllerC.getInstance().prikaziDetalje(klijent);
            frmPretragaKlijenta.getTxtKriterijumPretrage().setText("");
            pretraziKlijente();
        } catch (SelectedItemException ex) {
        }
    }

    private void izmeni() {
        try {
            Klijent klijent = getSelectedObject();
            ControllerC.getInstance().izmeni(klijent);
            frmPretragaKlijenta.getTxtKriterijumPretrage().setText("");
            pretraziKlijente();
        } catch (SelectedItemException ex) {
        }
    }

    private void obrisi() {
        try {
            Klijent klijent = getSelectedObject();
            ControllerC.getInstance().obrisi(klijent);
            frmPretragaKlijenta.getTxtKriterijumPretrage().setText("");
            pretraziKlijente();
        } catch (SelectedItemException ex) {
        }
    }

    private Klijent getSelectedObject() throws SelectedItemException {
        int red = frmPretragaKlijenta.getTblKlijenti().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(frmPretragaKlijenta, "Morate izabrati klijenta", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
            throw new SelectedItemException();
        }
        KlijentTableModel ktm = (KlijentTableModel) frmPretragaKlijenta.getTblKlijenti().getModel();
        Klijent klijent = ktm.vratiKlijenta(red);
        return klijent;
    }

}
