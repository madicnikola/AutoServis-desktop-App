/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.ui.view.controller;

import fon.ai.np.mvnautoservisklijent.controller.ControllerC;
import fon.ai.np.mvnautoserviscommonlib.domen.Usluga;
import fon.ai.np.mvnautoserviscommonlib.exception.SelectedItemException;
import java.util.List;
import javax.swing.JOptionPane;
import fon.ai.np.mvnautoservisklijent.ui.tablemodels.UslugaTableModel;
import fon.ai.np.mvnautoservisklijent.ui.view.FMain;
import fon.ai.np.mvnautoservisklijent.ui.view.FPretragaUsluga;
import fon.ai.np.mvnautoservisklijent.ui.view.mode.FormMode;

/**
 *
 * @author Nikola
 */
public class FPretragaUslugaController {

    FPretragaUsluga frmPretragaUsluga;

    public FPretragaUslugaController() {
    }

    public void openFPretragaUsluga(FMain frmMain, FormMode formMode) {
        frmPretragaUsluga = new FPretragaUsluga(frmMain, true);

        prepareTableUsluge();
        prepareForm(formMode);
        addListeners();

        frmPretragaUsluga.pack();
        frmPretragaUsluga.setVisible(true);
    }

    private void prepareTableUsluge() {
        List<Usluga> lista = ControllerC.getInstance().pretraziUsluge("");
        frmPretragaUsluga.getTblUsluge().setModel(new UslugaTableModel(lista));
    }

    private void prepareForm(FormMode formMode) {
        if (formMode.equals(FormMode.FORM_VIEW)) {
            frmPretragaUsluga.setTitle("Pretraga usluga");
            frmPretragaUsluga.getBtnObrisi().setVisible(false);
            frmPretragaUsluga.getBtnIzmeni().setVisible(false);
        }
        if (formMode.equals(FormMode.FORM_UPDATE)) {
            frmPretragaUsluga.setTitle("Izmena podataka o uslugama");
            frmPretragaUsluga.getBtnObrisi().setVisible(false);
            frmPretragaUsluga.getBtnPrikaziDetalje().setVisible(false);

        }
        if (formMode.equals(FormMode.FORM_DELETE)) {
            frmPretragaUsluga.getBtnIzmeni().setVisible(false);
            frmPretragaUsluga.getBtnPrikaziDetalje().setVisible(false);
        }
    }

    private void pretraziUsluge() {
        String kriterijum = frmPretragaUsluga.getTxtKriterijumPretrage().getText().trim();
        String whereUpit = " WHERE naziv LIKE '" + kriterijum + "%'";
        List<Usluga> lista = ControllerC.getInstance().pretraziUsluge(whereUpit);
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(frmPretragaUsluga, "Sistem ne može da nađe usluge po zadatoj vrednosti.", "Obaveštenje", JOptionPane.ERROR_MESSAGE);
            return;
        }
        UslugaTableModel ktm = new UslugaTableModel(lista);
        frmPretragaUsluga.getTblUsluge().setModel(ktm);
        if (kriterijum.isEmpty()) {
            return;
        }
        JOptionPane.showMessageDialog(frmPretragaUsluga, "Sistem je našao usluge po zadatoj vrednosti.", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);

    }

    private void prikaziDetalje() {
        try {
            Usluga usluga = getSelectedObject();
            ControllerC.getInstance().prikaziDetalje(usluga);
            frmPretragaUsluga.getTxtKriterijumPretrage().setText("");
            pretraziUsluge();
        } catch (Exception ex) {
        }
    }

    private void izmeni() {
        try {
            Usluga usluga = getSelectedObject();
            ControllerC.getInstance().izmeni(usluga);
            frmPretragaUsluga.getTxtKriterijumPretrage().setText("");
            pretraziUsluge();
        } catch (Exception ex) {
        }
    }

    private void obrisi() {
        try {
            Usluga usluga = getSelectedObject();
            ControllerC.getInstance().obrisi(usluga);
            frmPretragaUsluga.getTxtKriterijumPretrage().setText("");
            pretraziUsluge();
        } catch (Exception ex) {
        }
    }

    private Usluga getSelectedObject() throws Exception {
        int red = frmPretragaUsluga.getTblUsluge().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(frmPretragaUsluga, "Morate izabrati uslugu", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
            throw new SelectedItemException("Morate izabrati uslugu");
        }
        UslugaTableModel ktm = (UslugaTableModel) frmPretragaUsluga.getTblUsluge().getModel();
        Usluga usluga = ktm.vratiUslugu(red);
        return usluga;
    }

    private void addListeners() {
        frmPretragaUsluga.getBtnPretrazi().addActionListener(e -> pretraziUsluge());
        frmPretragaUsluga.getTxtKriterijumPretrage().addActionListener(e -> pretraziUsluge());
        frmPretragaUsluga.getBtnPrikaziDetalje().addActionListener(e -> prikaziDetalje());
        frmPretragaUsluga.getBtnIzmeni().addActionListener(e -> izmeni());
        frmPretragaUsluga.getBtnObrisi().addActionListener(e -> obrisi());
    }

}
