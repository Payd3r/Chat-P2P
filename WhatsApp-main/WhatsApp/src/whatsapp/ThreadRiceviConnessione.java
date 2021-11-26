/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whatsapp;

import java.io.IOException;
import java.net.SocketException;
import javax.swing.JOptionPane;

/**
 *
 * @author mauri_andrea
 */
public class ThreadRiceviConnessione extends Thread {

    IO InviaRicevi;

    public ThreadRiceviConnessione() throws SocketException {
        InviaRicevi = IO.getIstance();
    }

    private void chiedoUtente() throws SocketException, IOException {
        int opzione = JOptionPane.showConfirmDialog(null, "Vuoi accettare la comunicazione con: " + InviaRicevi.indirizzoD, "Messaggio", JOptionPane.YES_NO_OPTION);
        if (opzione == 0) {
            InviaRicevi.invia("y;andrea;");
        } else {
            InviaRicevi.invia("n; ;");
        }
    }

    @Override
    public void run() {
        String messaggio = "";
        String[] caso = new String[3];
        while (true) {
            messaggio = InviaRicevi.ricevi();
            caso = messaggio.split(";");
            if (caso[0] == "a") {
                chiedoUtente();
                InviaRicevi.destinatario = caso[1];
            } else if (caso[0] == "y" && InviaRicevi.destinatario != "") {
                break;
            } else if (caso[0] == "n") {
                //connessione rifiutata
                InviaRicevi.indirizzoD = null;
                InviaRicevi.destinatario = "";
            } else {
            }
        }
    }
}