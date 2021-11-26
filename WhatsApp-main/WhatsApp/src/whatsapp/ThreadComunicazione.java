/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package whatsapp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Payd3r
 */
public class ThreadComunicazione extends Thread {

    String nomeMittente;
    String nomeDestinatario;

    ThreadComunicazione(String n) throws SocketException {
        nomeMittente = n;
        nomeDestinatario = null;
    }


    @Override
    public void run() {
        while (true) {
            //INIZIO UNA COMUNICAZIONE Passiva
            //?? come si inviano le cose da JFrame ??
            //aspetto che mi arriva un messaggio
            String[] explode = null;
            try {
                explode = ricevoMessaggio().split(";");
            } catch (IOException ex) {
                Logger.getLogger(ThreadComunicazione.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ("a".equals(explode[0])) {
                //caso inizio comunicazione
                nomeDestinatario = explode[1];
                try {
                    chiedoUtente();
                } catch (IOException ex) {
                    Logger.getLogger(ThreadComunicazione.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("m".equals(explode[0])) {
                try {
                    //caso scambio messaggi
                    Messaggi.getIstance().aggiungi(explode[1], false);
                } catch (SocketException ex) {
                    Logger.getLogger(ThreadComunicazione.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("c".equals(explode[0])) {
                //caso chiudo comunicazione
                try {
                    Messaggi.getIstance().comunicazione = false;
                    Messaggi.getIstance().clear();
                    nomeDestinatario = null;
                    IO.getIstance().indirizzoD = null;
                } catch (SocketException ex) {
                    Logger.getLogger(ThreadComunicazione.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("y".equals(explode[0])) {
                //inizo comunicazione
                try {
                    Messaggi.getIstance().comunicazione = true;
                    nomeDestinatario = explode[1];
                } catch (SocketException ex) {
                    Logger.getLogger(ThreadComunicazione.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("n".equals(explode[0])) {
                //caso rifiuto comunicazione
                nomeDestinatario = null;
            } else {
                //ERRORE
            }
            //controllo se ci sono messaggi da inviare
            while (true) {
                Messaggio m = null;
                try {
                    m = Messaggi.getIstance().controlloInviare();
                } catch (SocketException ex) {
                    Logger.getLogger(ThreadComunicazione.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (m.id != -1) {
                    try {
                        IO.getIstance().invia("m;" + m.contenuto);
                    } catch (SocketException ex) {
                        Logger.getLogger(ThreadComunicazione.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadComunicazione.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    break;
                }
            }
            //fine
        }
    }
}
