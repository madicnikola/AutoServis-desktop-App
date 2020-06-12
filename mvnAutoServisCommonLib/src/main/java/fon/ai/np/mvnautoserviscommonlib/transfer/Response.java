/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoserviscommonlib.transfer;

import fon.ai.np.mvnautoserviscommonlib.transfer.constants.Status;
import java.io.Serializable;

/**
 * Klasa koja objedinjuje objekte koji se salju kroz mrezu.
 * Serverski odgovor - od servera ka klijentu.
 * @author Nikola
 * @version 1.2
 */
public class Response implements Serializable {
  /**
     * kod zahteva.
     * Svaki kod operacije predstavlja tip zahteva na koji se odgovara.
     * tip zahteva se moze videti u:
     * @see Operacija
     */
    private int operacija;
    /**
     * poruka koju server salje klijentu za prikaz na ekranu.
     */
    private String poruka;
    /**
    * kod statusa.
    * @see Status
    */
    private int status;
    /**
     * objekat koji se salje klijentu kao odgovor na zahtev.
     */
    private Object responseObject;

    public Response() {
    }

    public Response(int operacija, String poruka, int status) {
        this();
        this.operacija = operacija;
        this.poruka = poruka;
        this.status = status;
    }

    public Response(int operacija, String poruka, int status, Object responseObject) {
        this();
        this.operacija = operacija;
        this.poruka = poruka;
        this.status = status;
        this.responseObject = responseObject;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }

}
