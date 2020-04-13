/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.ai.np.mvnautoservisklijent.communication;

import fon.ai.np.mvnautoserviscommonlib.transfer.Request;
import fon.ai.np.mvnautoserviscommonlib.transfer.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikola
 */
public class Communication {

    private static Communication instance;
    private Socket socket;
    static boolean flag = false;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    PrintWriter printWriter;
    BufferedReader bufferedReader;

    private Communication() {
        try {
            flag = connectToServer();
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
//            printWriter = new PrintWriter(socket.getOutputStream(), true);
//            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    private boolean connectToServer() {
        try {
            socket = new Socket("localhost", 9000);
            System.out.println("Uspostavljena komunikacija sa serverom");
            return true;
        } catch (IOException ex) {
            System.out.println("Greska prilikom povezivanja na server");
            return false;
        }
    }

    public void posaljiZahtev(Request request) {
        try {
            objectOutputStream.writeObject(request);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Server nije dostupan.");
            System.out.println("Greska prilikom slanja zahteva");
            System.exit(0);
        }
    }

    public Response primiOdgovor() {
        Response odgovor = new Response();
        try {
            odgovor = (Response) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Greska prilikom prijema odgovora");
        }
        return odgovor;
    }

//    private void buildGson() {
//        GsonBuilder builder = new GsonBuilder();
//        builder.serializeNulls();
//        gson = builder.create();
//    }

//    public void sendRequest(Request request) {
//        String jsonRequest = gson.toJson(request);
//        printWriter.print(jsonRequest);
//        System.out.println(jsonRequest);
//        printWriter.close();
//
//    }
//
//    public Response receiveResponse() {
//        Response response = new Response();
//        try {
//            String json = bufferedReader.readLine();
//            response = gson.fromJson(json, Response.class);
//        } catch (IOException ex) {
//            System.out.println("Greska prilikom prijema odgovora");
//            ex.printStackTrace();
//        }
//
//        return response;
//    }
}
