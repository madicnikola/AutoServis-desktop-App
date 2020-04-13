/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.validator.implementations;

import fon.ai.np.mvnautoserviscommonlib.domen.Klijent;
import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisklijent.validator.Validator;

/**
 *
 * @author Nikola
 */
public class ClientValidator implements Validator {

    @Override
    public void validate(Object odo) throws ValidationException {
        Klijent klijent = (Klijent) odo;
        proveriIme(klijent.getIme());
        proveriPrezime(klijent.getPrezime());
        proveriJMBG(klijent.getJMBG());
        proveriAdresu(klijent.getAdresa());
        proveriBrojTelefona(klijent.getTelefon());
        proveriEmail(klijent.getEmail());

    }

    private void proveriEmail(String email) throws ValidationException {
        if (!email.contains("@")) {
            throw new ValidationException("Email mora sadrzati @");
        }
    }

    private void proveriIme(String ime) throws ValidationException {
        if (ime.isEmpty()) {
            throw new ValidationException("Ime je obavezno polje");
        }
    }

    private void proveriPrezime(String prezime) throws ValidationException {
        if (prezime.isEmpty()) {
            throw new ValidationException("Prezime je obavezno polje");
        }
    }

    private void proveriJMBG(String jmbg) throws ValidationException {
        if (jmbg.isEmpty()) {
            throw new ValidationException("JMBG je obavezno polje");
        }
        for (Character c : jmbg.toCharArray()) {
            if (Character.isLetter(c)) {
                throw new ValidationException("JMBG ne sme sadrzati slova");
            }
        }
    }

    private void proveriAdresu(String adresa) throws ValidationException {
        if (adresa.isEmpty()) {
            throw new ValidationException("Adresa je obavezno polje");
        }
    }

    private void proveriBrojTelefona(String telefon) throws ValidationException {
        String pattern = "^06[0-9]\\/[0-9]{6,7}$";

        if (!telefon.matches(pattern)) {
            throw new ValidationException("Broj telefona mora biti u obliku 06?/???????");
        }
    }
}
