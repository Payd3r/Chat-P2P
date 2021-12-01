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
public class BufferInviati {

    static BufferInviati istance;
    ArrayList<Messaggio> messaggi;

    public BufferInviati() {
        messaggi = new ArrayList<Messaggio>();
    }

    static BufferInviati getIstance() {
        if (istance == null) {
            synchronized (BufferInviati.class) {
                if (istance == null) {
                    istance = new BufferInviati();
                }
            }
        }
        return istance;
    }

    public synchronized void aggiungi(String a, String b) {
        messaggi.add(new Messaggio(a.split(";")[0], a.split(";")[1], b));
    }

    public synchronized Messaggio getMessaggio() {
        return messaggi.remove(messaggi.size());
    }
}
