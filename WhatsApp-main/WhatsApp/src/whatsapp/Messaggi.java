/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package whatsapp;

import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author Payd3r
 */
public class Messaggi {

    static Messaggi istance;
    int id;
    boolean comunicazione;
    ArrayList<Messaggio> Messaggi;

    public Messaggi() {
        id = 0;
        Messaggi = new ArrayList<Messaggio>();
        comunicazione = false;
    }

    static Messaggi getIstance() throws SocketException {
        if (istance == null) {
            synchronized (Messaggi.class) {
                if (istance == null) {
                    istance = new Messaggi();
                }
            }
        }
        return istance;
    }

    public synchronized void aggiungi(String s, boolean tipo) {
        Messaggi.add(new Messaggio(s, id++, tipo));
    }

    public void clear() {
        istance = new Messaggi();
    }

    public Messaggio controlloInviare() {
        Messaggio m = new Messaggio();
        for (int i = 0; i < Messaggi.size(); i++) {
            if (!Messaggi.get(i).fatto) {
                Messaggi.get(i).fatto = true;
                return Messaggi.get(i);
            }
        }
        return m;
    }
}
