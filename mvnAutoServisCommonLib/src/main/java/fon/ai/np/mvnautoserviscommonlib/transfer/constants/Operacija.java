/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoserviscommonlib.transfer.constants;

/**
 * Intefejs u kome su navedene sve konstante operacija koje odredjuju tip
 * klijentskog zahteva ili serverskog odgovora.
 *
 * @author Nikola
 * @version 1.3
 */
public interface Operacija {

    /**
     * Operacija prijave korisnika na sistem.
     */
    public static final int LOGIN = 1;

    /**
     * Operacija odjave korisnika iz sistema.
     */
    public static final int LOGOUT = 2;

    /**
     * Operacija za cuvanje klijenta.
     */
    public static final int SAVE_CLIENT = 3;

    /**
     * Operacija pretrazivanja klijente po zadatom kriterijumu.
     */
    public static final int SEARCH_CLIENTS = 4;

    /**
     * Operacija za cuvanje usluge.
     */
    public static final int SAVE_SERVICE = 5;

    /**
     * Operacija za izmenu podataka o klijentu.
     */
    public static final int UPDATE_CLIENT = 6;

    /**
     * Operacija za brisanje podataka o klijentu.
     */
    public static final int DELETE_CLIENT = 7;

    /**
     * Operacija za pretragu usluga.
     */
    public static final int SEARCH_SERVICES = 8;

    /**
     * Operacija za izmenu podataka o usluzi.
     */
    public static final int UPDATE_SERVICE = 9;

    /**
     * Operacija za brisanje podataka o usluzi.
     */
    public static final int DELETE_SERVICE = 10;

    /**
     * Operacija za prikaz svih proizvoda.
     */
    public static final int SELECT_ALL_PRODUCTS = 11;

    /**
     * Operacija za prikaz svih klijenata.
     */
    public static final int SELECT_ALL_CLIENTS = 12;

    /**
     * Operacija za cuvanje racuna.
     */
    public static final int SAVE_INVOICE = 13;

    /**
     * Operacija za storniranje racuna.
     */
    public static final int REVERSE_INVOICE = 14;

    /**
     * Operacija za pretragu racuna po zadatom kriterijumu.
     */
    public static final int SEARCH_INVOICES = 15;
}
