/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.validator.implementations;

import fon.ai.np.mvnautoserviscommonlib.exception.ValidationException;
import fon.ai.np.mvnautoservisklijent.validator.Validator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Madic
 */
public class DateValidator implements Validator {

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void validate(Object odo) throws ValidationException {
        String stringDatum = (String) odo;
        try {
            Date datum = sdf.parse(stringDatum);
        } catch (ParseException ex) {
            throw new ValidationException("Unesite datum u formatu: dd.MM.yyyy");
        }
    }

}
