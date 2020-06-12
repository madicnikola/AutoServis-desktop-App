/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoserviscommonlib.transfer.constants;

/**
 * Interfejs u kome su navede sve konstante statusa serverskog odgovora.
 *
 * @author Nikola
 * @version 1.0
 */
public interface Status {

    /**
     * Ukoliko je zahtev uspesno obradjen.
     */
    static final int OK = 0;

    /**
     * Ukoliko je doslo do greske prilikom obrade zahteva.
     */
    static final int ERROR = 1;

}
