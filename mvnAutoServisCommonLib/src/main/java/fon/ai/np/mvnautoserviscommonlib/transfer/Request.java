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
public class Request implements Serializable {

    private int operacija;
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
