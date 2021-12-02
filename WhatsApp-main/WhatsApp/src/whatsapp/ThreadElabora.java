/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package whatsapp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Payd3r
 */
public class ThreadElabora extends Thread {

    boolean connessione;
    String indirizzoDest;
    String nomeDestinatario;
    BufferInviati bufferInv;
    BufferRicevuti buffferRic;

    public ThreadElabora() {
        this.connessione = false;
        this.indirizzoDest = null;
        this.nomeDestinatario = null;
        bufferInv = BufferInviati.getIstance();
        buffferRic = BufferRicevuti.getIstance();
    }

    public boolean richiediUtente(String a) {
        int scelta = JOptionPane.showConfirmDialog(null, "Vuoi stabilire la connessione con:" + a + " ?", "Domanda", JOptionPane.YES_NO_OPTION);
        if (scelta == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {
        Messaggio temp = new Messaggio();
        while (true) {
            temp = buffferRic.getMessaggio();
            switch (temp.scelta) {
                case "a":
                    //se non c'e' niente posso accettare una nuova connessione
                    if (indirizzoDest == null && nomeDestinatario == null) {
                        indirizzoDest = temp.indirizzo;
                        nomeDestinatario = temp.contenuto;
                        if (richiediUtente(nomeDestinatario)) {
                            bufferInv.aggiungi("y;Andrea", temp.indirizzo);
                        } else {
                            bufferInv.aggiungi("c;", temp.indirizzo);
                        }
                    }
                    break;
                case "y":
                    if (temp.indirizzo.equals("127.0.0.1")) {
                        if (temp.contenuto.equals(" ")) {
                            //ho ricevuto una richiesta e mando la conferma
                        } else {
                            bufferInv.aggiungi("y;", temp.indirizzo);
                        }
                        JOptionPane.showMessageDialog(null, "Connessione iniziata!");
                        connessione = true;
                    }
                    break;
                case "n":
                    indirizzoDest = null;
                    nomeDestinatario = null;
                    connessione = false;
                    break;
                case "m":
                    //disegna messaggio inviato
                    Cronologia.getIstance().aggiungiMSG(new Messaggio(temp.contenuto, false));
                    break;
                case "c":
                    //cancella tutti messaggi
                    indirizzoDest = null;
                    nomeDestinatario = null;
                    connessione = false;
                    break;
            }
            System.out.print("");
        }
    }
}
