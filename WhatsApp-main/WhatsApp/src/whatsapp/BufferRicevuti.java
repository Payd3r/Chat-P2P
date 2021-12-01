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
        messaggi.add(new Messaggio(a.split(";")[0], a.split(";")[1], b));
    }

    public synchronized Messaggio getMessaggio() {
        return messaggi.remove(messaggi.size());
    }
}
