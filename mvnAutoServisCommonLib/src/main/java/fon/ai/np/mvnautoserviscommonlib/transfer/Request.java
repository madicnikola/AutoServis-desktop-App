/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoserviscommonlib.transfer;

import fon.ai.np.mvnautoserviscommonlib.transfer.constants.Operacija;
import java.io.Serializable;

/**
 * Klasa koja objedinjuje objekte koji se salju kroz mrezu. Zahtev - od klijenta
 * ka serveru.
 *
 * @author Nikola
 * @version 1.1
 */
public class Request implements Serializable {

    /**
     * kod zahteva. Svaki kod operacije predstavlja tip zahteva koji se salje.
     * tip zahteva se moze videti u:
     *
     * @see Operacija
     */
    private int operacija;
    /**
     * objekat koji se salje serveru.
     */
    private Object requestObject;

    public Request() {
    }

    public Request(int operacija) {
        this();
        this.operacija = operacija;
    }

    public Request(int operacija, Object requestObject) {
        this(operacija);
        this.requestObject = requestObject;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public Object getRequestObject() {
        return requestObject;
    }

    public void setRequestObject(Object requestObject) {
        this.requestObject = requestObject;
    }
}
