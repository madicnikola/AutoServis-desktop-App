/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.ui.view.controller;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import fon.ai.np.mvnautoserviscommonlib.domen.Racun;
import fon.ai.np.mvnautoserviscommonlib.exception.SelectedItemException;
import fon.ai.np.mvnautoservisklijent.controller.ControllerC;
import fon.ai.np.mvnautoservisklijent.ui.tablemodels.RacunTableModel;
import fon.ai.np.mvnautoservisklijent.ui.view.FMain;
import fon.ai.np.mvnautoservisklijent.ui.view.FPretragaRacuna;
import fon.ai.np.mvnautoservisklijent.ui.view.mode.FormMode;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author Nikola
 */
public class FPretragaRacunaController {

    FPretragaRacuna fr;
    Racun racun;

    public FPretragaRacunaController() {
    }

    public void openFPretragaRacuna(FMain frmMain, FormMode formMode) {
        fr = new FPretragaRacuna(frmMain, true);

        prepareTableRacun();
        prepareForm(formMode);
        addListeners();

        fr.pack();
        fr.setVisible(true);
    }

    private void prepareTableRacun() {
        List<Racun> lista = ControllerC.getInstance().pretraziRacune("");
        fr.getTblRacuni().setModel(new RacunTableModel(lista));

        JCheckBox cbStorniran = new JCheckBox();
        TableColumn tcStorniran = fr.getTblRacuni().getColumnModel().getColumn(4);
        tcStorniran.setCellEditor(new DefaultCellEditor(cbStorniran));
    }

    private void prepareForm(FormMode formMode) {
        if (formMode.equals(FormMode.FORM_VIEW)) {
            fr.setTitle("Pretraga racuna");
            fr.getBtnStorniraj().setVisible(false);
        }
        if (formMode.equals(FormMode.FORM_UPDATE)) {
            fr.setTitle("Storniranje racuna");
            fr.getBtnStorniraj().setVisible(true);
            fr.getBtnPrikaziDetalje().setVisible(true);
            fr.getBtnImportJSON().setVisible(false);
        }
    }

    private void pretraziRacune() {
        String kriterijum = fr.getTxtKriterijumPretrage().getText().trim();
        String whereUpit = " WHERE kl.ime LIKE '" + kriterijum + "%' or " + "kl.prezime LIKE '" + kriterijum + "%'";
        List<Racun> lista = ControllerC.getInstance().pretraziRacune(whereUpit);
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(fr, "Sistem ne može da nađe racune po zadatoj vrednosti.", "Obaveštenje", JOptionPane.ERROR_MESSAGE);
            return;
        }
        RacunTableModel rtm = new RacunTableModel(lista);
        fr.getTblRacuni().setModel(rtm);
        if (kriterijum.isEmpty()) {
            return;
        }
        JOptionPane.showMessageDialog(fr, "Sistem je nasao racune po zadatoj vrednosti.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
    }

    private void prikaziDetalje() {
        try {
            racun = getSelectedObject();
            ControllerC.getInstance().prikaziDetalje(racun);
            fr.getTxtKriterijumPretrage().setText("");
            pretraziRacune();
        } catch (Exception ex) {
        }
    }

    private void storniraj() {
        try {
            racun = getSelectedObject();
        } catch (Exception ex) {
            return;
        }
        if (!racun.isStorniran()) {
            for (int i = 0; i < 2; i++) {
                try {
                    racun.setStorniran(true);
                    racun = ControllerC.getInstance().stornirajRacun(racun);
                    if (i == 1) {
                        JOptionPane.showMessageDialog(fr, "Sistem je stornirao racun.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(fr, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(fr, "Sistem ne moze da stornira racun.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
        pretraziRacune();
    }

    private void importFromJSON() {
        try {
            racun = ControllerC.getInstance().importFromJSON();
            JOptionPane.showMessageDialog(fr, "Sistem je uspesno uvezao racun.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            ControllerC.getInstance().prikaziDetalje(racun);
            pretraziRacune();
        } catch (Exception ex) {
            Logger.getLogger(FPretragaRacunaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(fr, "Racun vec postoji u sistemu.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Racun getSelectedObject() throws Exception {
        int red = fr.getTblRacuni().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(fr, "Morate izabrati racun", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
            throw new SelectedItemException("Morate izabrati racun");
        }
        RacunTableModel rtm = (RacunTableModel) fr.getTblRacuni().getModel();
        racun = rtm.vratiRacun(red);
        return racun;
    }

    private void addListeners() {
        fr.getBtnPretrazi().addActionListener(e -> pretraziRacune());
        fr.getTxtKriterijumPretrage().addActionListener(e -> pretraziRacune());
        fr.getBtnPrikaziDetalje().addActionListener(e -> prikaziDetalje());
        fr.getBtnStorniraj().addActionListener(e -> storniraj());
        fr.getBtnImportJSON().addActionListener(e -> importFromJSON());
    }

}
