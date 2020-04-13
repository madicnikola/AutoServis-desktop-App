/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.ui.view.controller;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.domen.Korisnik;
import fon.ai.np.mvnautoserviscommonlib.domen.Proizvod;
import fon.ai.np.mvnautoserviscommonlib.domen.Racun;
import fon.ai.np.mvnautoserviscommonlib.domen.StavkaRacuna;
import fon.ai.np.mvnautoserviscommonlib.exception.SelectedItemException;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisklijent.controller.ControllerC;
import fon.ai.np.mvnautoservisklijent.coordinator.GUICoordinator;
import fon.ai.np.mvnautoservisklijent.ui.tablemodels.StavkaRacunaTableModel;
import fon.ai.np.mvnautoservisklijent.ui.view.FMain;
import fon.ai.np.mvnautoservisklijent.ui.view.FRacun;
import fon.ai.np.mvnautoservisklijent.ui.view.mode.FormMode;
import fon.ai.np.mvnautoservisklijent.validator.Validator;
import fon.ai.np.mvnautoservisklijent.validator.implementations.DateValidator;
import fon.ai.np.mvnautoservisklijent.validator.implementations.RacunValidator;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Nikola
 */
public class FRacunController {

    private FRacun fr;
    private List<StavkaRacuna> listaStavki;
    private Racun racun;
    private StavkaRacuna stavkaRacuna;
    private Validator validator;
    private Korisnik korisnik;

    public FRacunController() {
        validator = new RacunValidator();
    }

    public void openFRacun(FMain frmMain, FormMode formMode, Korisnik korisnik) {
        fr = new FRacun(frmMain, true);

        this.korisnik = korisnik;

        prepareForm(formMode);
        addListeners();

        fr.pack();
        fr.setLocationRelativeTo(frmMain);
        fr.setVisible(true);
    }

    public void openFRacun(FormMode formMode, Racun racun) {
        FMain frmMain = GUICoordinator.getInstance().getfMainController().getFrmMain();
        fr = new FRacun(frmMain, true);
        this.racun = racun;

        prepareForm(formMode);
        addListeners();
        setFields(racun);

        fr.pack();
        fr.setLocationRelativeTo(frmMain);
        fr.setVisible(true);
    }

    private void prepareForm(FormMode formMode) {
        prepareTable(formMode);
        prepareCombo(formMode);
        hide(formMode);
        if (formMode.equals(FormMode.FORM_ADD)) {
            kreirajRacun();
            fr.getTxtUkupnaVrednost().setText("0");
            fr.getTxtKolicina().setText("1");
            postaviDanasnjiDatum();
        }

    }

    private void hide(FormMode formMode) {
        if (formMode.equals(FormMode.FORM_ADD)) {

            fr.getTxtRacunID().setVisible(false);
            fr.getLblRacunID().setVisible(false);
            fr.getCheckStorniran().setVisible(false);
            fr.getLblStorniran().setVisible(false);

            fr.getBtnStornirajRacun().setVisible(false);
        } else if (formMode.equals(FormMode.FORM_VIEW)) {
            fr.getTxtDatum().setEditable(false);
            fr.getCmbKlijenti().setEnabled(false);
            fr.getPnlStavkaRacuna().setVisible(false);
            fr.getBtnObrisiStavku().setVisible(false);
            fr.getBtnPonisti().setVisible(false);
            fr.getBtnSacuvaj().setVisible(false);

            fr.getBtnStornirajRacun().setVisible(true);
        }
    }

    private void addListeners() {
        fr.getCmbProizvod().addActionListener(e -> postaviVrednostStavke());
        fr.getBtnUnesi().addActionListener(e -> unesiStavku());
        fr.getTxtKolicina().addActionListener(e -> unesiStavku());
        fr.getBtnSacuvaj().addActionListener(e -> sacuvajRacun());
        fr.getBtnOtkazi().addActionListener(e -> fr.dispose());
        fr.getBtnPonisti().addActionListener(e -> resetFields());
        fr.getBtnStornirajRacun().addActionListener(e -> stornirajRacun());
        fr.getBtnObrisiStavku().addActionListener(e -> obrisiStavku());

    }

    private void resetFields() {
        postaviDanasnjiDatum();
        fr.getTxtKolicina().setText("");
        listaStavki = new LinkedList<>();
        refreshTable();
    }

    private void unesiStavku() {
        stavkaRacuna = new StavkaRacuna();
        Proizvod proizvod = (Proizvod) fr.getCmbProizvod().getSelectedItem();
        dodajStavku(proizvod);
        azurirajUkupnuVrednost();
        refreshTable();
    }

    private void dodajStavku(Proizvod proizvod) throws NumberFormatException, HeadlessException {
        validateKolicina();
        stavkaRacuna.setKolicina(Integer.parseInt(fr.getTxtKolicina().getText().trim()));
        if (!(proveriDaLiPostojiProizvod(proizvod))) {
            stavkaRacuna.setVrednostStavke(proizvod.getVrednost());
            stavkaRacuna.setProizvod(proizvod);
            listaStavki.add(stavkaRacuna);
            stavkaRacuna.setRBStavke(listaStavki.indexOf(stavkaRacuna) + 1);
        }
    }

    private boolean proveriDaLiPostojiProizvod(Proizvod proizvod) {
        for (StavkaRacuna sr : listaStavki) {
            if (sr.getProizvod().equals(proizvod)) {
                sr.setKolicina(sr.getKolicina() + stavkaRacuna.getKolicina());
                return true;
            }
        }
        return false;
    }

    private void validateKolicina() throws HeadlessException, NumberFormatException {
        try {
            Integer.parseInt(fr.getTxtKolicina().getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(fr, "Kolicina mora biti pozitivni broj", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);

        }
        if (Integer.parseInt(fr.getTxtKolicina().getText().trim()) <= 0) {
            JOptionPane.showMessageDialog(fr, "Kolicina mora biti pozitivna", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void sacuvajRacun() {
        validator = new DateValidator();
        String datumString = fr.getTxtDatum().getText();
        try {
            validator.validate(datumString);
            racun.setDatum(new SimpleDateFormat("dd.MM.yyyy").parse(datumString));
            racun.setKlijent((Klijent) fr.getCmbKlijenti().getSelectedItem());
            racun.setKorisnik(korisnik);
            racun.setUkupnaVrednost(Double.parseDouble(fr.getTxtUkupnaVrednost().getText()));
            racun.setStorniran(false);
            racun.setListaStavki(listaStavki);
            validator = new RacunValidator();
            validator.validate(racun);
            racun = ControllerC.getInstance().sacuvajRacun(racun);
            JOptionPane.showMessageDialog(fr, "Sistem je zapamtio racun.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(fr, "Sistem ne moze da zapamti racun.", "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(fr, "Sistem ne moze da zapamti racun.", "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(fr, "Sistem ne moze da zapamti racun.", "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void kreirajRacun() {
        racun = new Racun();
    }

    private void stornirajRacun() {

        if (!racun.isStorniran()) {
            try {
                racun.setStorniran(true);
                racun = ControllerC.getInstance().stornirajRacun(racun);
                if (racun.isStorniran()) {
                    fr.getCheckStorniran().setEnabled(true);
                    fr.getCheckStorniran().doClick();
                    fr.getCheckStorniran().setEnabled(false);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(fr, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(fr, "Sistem ne moze da stornira racun.", "Greska", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(fr, "Sistem je stornirao racun.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
    }

    private void obrisiStavku() {
        try {
            listaStavki.remove(getSelectedRow());
            azurirajUkupnuVrednost();
            azurirajRBstavki();
            refreshTable();
        } catch (SelectedItemException ex) {
        }
    }

    private void prepareTable(FormMode formMode) {
        if (formMode.equals(FormMode.FORM_ADD)) {
            listaStavki = new LinkedList<>();
            ((TitledBorder) fr.getScrollPaneTblStavke().getBorder()).setTitle("Unete stavke");
        } else if (formMode.equals(FormMode.FORM_VIEW)) {
            ((TitledBorder) fr.getScrollPaneTblStavke().getBorder()).setTitle("Stavke racuna");
            listaStavki = racun.getListaStavki();
        }
        fr.getTblStavke().setModel(new StavkaRacunaTableModel(listaStavki));
    }

    private void prepareCombo(FormMode formMode) {
        prepareComboProizvodi(formMode);
        prepareComboKlijenti(formMode);
    }

    private void prepareComboProizvodi(FormMode formMode) {
        if (formMode.equals(FormMode.FORM_ADD)) {

            try {
                List<Proizvod> listaProizvoda = ControllerC.getInstance().vratiSveProizvode(new Proizvod());
                fr.getCmbProizvod().removeAllItems();
                for (Proizvod proizvod : listaProizvoda) {
                    fr.getCmbProizvod().addItem(proizvod);
                }
            } catch (Exception ex) {
                System.out.println("Greska prilikom ucitavanja proizvoda iz baze");
                ex.printStackTrace();
            }
        }
    }

    private void prepareComboKlijenti(FormMode formMode) {

        if (formMode.equals(FormMode.FORM_ADD)) {
            try {
                List<Klijent> listaKlijenata = ControllerC.getInstance().pretraziKlijente("");
                fr.getCmbKlijenti().removeAllItems();
                for (Klijent klijent : listaKlijenata) {
                    fr.getCmbKlijenti().addItem(klijent);
                }
            } catch (Exception ex) {
                System.out.println("Greska prilikom ucitavanja klijenata iz baze");
                ex.printStackTrace();
            }
        } else if (formMode.equals(FormMode.FORM_VIEW)) {
            fr.getCmbKlijenti().removeAllItems();
            fr.getCmbKlijenti().addItem(racun.getKlijent());
        }

    }

    private void postaviVrednostStavke() {
        fr.getTxtKolicina().setText("1");
        fr.getTxtVrednostStavke().setText(String.valueOf(((Proizvod) fr.getCmbProizvod().getSelectedItem()).getVrednost()));
    }

    private void refreshTable() {
        ((StavkaRacunaTableModel) fr.getTblStavke().getModel()).fireTableDataChanged();
    }

    private void azurirajUkupnuVrednost() {
        JTextField txt = fr.getTxtUkupnaVrednost();
        double suma = 0;
        if (listaStavki.isEmpty()) {
            txt.setText("0");
            return;
        }
        for (StavkaRacuna sr : listaStavki) {
            suma += sr.getVrednostStavke() * sr.getKolicina();
            txt.setText(String.valueOf(suma));
        }
    }

    private int getSelectedRow() throws SelectedItemException {
        int red = fr.getTblStavke().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(fr, "Morate izabrati stavku", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
            throw new SelectedItemException();
        }
        return red;
    }

    private StavkaRacuna getSelectedObject() throws SelectedItemException {
        int red = fr.getTblStavke().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(fr, "Morate izabrati stavku", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
            throw new SelectedItemException();
        }
        StavkaRacunaTableModel stm = (StavkaRacunaTableModel) fr.getTblStavke().getModel();
        StavkaRacuna stavkaRacuna = stm.vratiStavku(red);
        return stavkaRacuna;
    }

    private void postaviDanasnjiDatum() {
        fr.getTxtDatum().setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
    }

    private void azurirajRBstavki() {
        for (StavkaRacuna sr : listaStavki) {
            sr.setRBStavke(listaStavki.indexOf(sr) + 1);
        }
    }

    private void setFields(Racun racun) {
        fr.getTxtRacunID().setText(String.valueOf(racun.getRacunID()));
        fr.getTxtUkupnaVrednost().setText(String.valueOf(racun.getUkupnaVrednost()));
        fr.getTxtDatum().setText(new SimpleDateFormat("dd.MM.yyyy").format(racun.getDatum()));
        if (racun.isStorniran()) {
            fr.getCheckStorniran().setEnabled(true);
            fr.getCheckStorniran().doClick();
            fr.getCheckStorniran().setEnabled(false);
        }

    }

}
