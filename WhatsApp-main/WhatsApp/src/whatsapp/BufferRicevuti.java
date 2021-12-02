/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package whatsapp;

import java.util.ArrayList;

/**
 *
 * @author Payd3r
 */
public class BufferRicevuti {

    static BufferRicevuti istance;
    ArrayList<Messaggio> messaggi;

    public BufferRicevuti() {
        messaggi = new ArrayList<Messaggio>();
    }

    static BufferRicevuti getIstance() {
        if (istance == null) {
            synchronized (BufferRicevuti.class) {
                if (istance == null) {
                    istance = new BufferRicevuti();
                }
            }
        }
        return istance;
    }

    public void aggiungi(String a, String b) {
        try {
            messaggi.add(new Messaggio(a.split(";")[0], a.split(";")[1], controlloInd(b)));
        } catch (Exception ex) {
            messaggi.add(new Messaggio(a.split(";")[0], "", controlloInd(b)));
        }
    }

    public Messaggio getMessaggio() {
        if (messaggi.size() > 0) {
            return messaggi.remove(messaggi.size() - 1);
        } else {
            return new Messaggio();
        }
    }

    public String controlloInd(String s) {
        if (s.substring(0, 1).equals("/")) {
            return s.substring(1, s.length());
        } else {
            return s;
        }
    }
}
