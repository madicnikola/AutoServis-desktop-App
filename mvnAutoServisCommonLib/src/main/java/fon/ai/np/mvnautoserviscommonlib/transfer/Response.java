/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoserviscommonlib.transfer;

import java.io.Serializable;

/**
 *
 * @author Nikola
 */
public class Response implements Serializable {

    private int operacija;
    private String poruka;
    private int status;
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
