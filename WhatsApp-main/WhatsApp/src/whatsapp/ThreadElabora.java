/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package whatsapp;

import java.util.logging.Level;
import java.util.logging.Logger;
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
        this.indirizzoDest = "";
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
            try {
                temp = buffferRic.getMessaggio();
                switch (temp.scelta) {
                    case "a":
                        indirizzoDest = temp.indirizzo;
                        nomeDestinatario = temp.contenuto;
                        if (richiediUtente(nomeDestinatario)) {
                            bufferInv.aggiungi("y;Andrea", temp.indirizzo);
                        } else {
                            bufferInv.aggiungi("n; ;", temp.indirizzo);
                        }
                        break;
                    case "y":
                        if (temp.indirizzo == indirizzoDest) {
                            //iniziare la connessione
                            JOptionPane.showMessageDialog(null, "Connessione iniziata!");
                        }
                        break;
                    case "n":

                        break;
                    case "m":
                        //disegna messaggio inviato
                        break;
                    case "c":
                        //cancella tutti messaggi
                        break;
                    default:
                        break;
                }
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadElabora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
