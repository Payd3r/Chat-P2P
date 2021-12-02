package whatsapp;

import java.util.ArrayList;
import whatsapp.Messaggio;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Payd3r
 */
public class Cronologia {

    static Cronologia istance;
    ArrayList<Messaggio> messaggi;

    public Cronologia() {
        messaggi = new ArrayList<Messaggio>();
    }

    static Cronologia getIstance() {
        if (istance == null) {
            synchronized (Cronologia.class) {
                if (istance == null) {
                    istance = new Cronologia();
                }
            }
        }
        return istance;
    }

    public void aggiungiMSG(Messaggio temp) {
        messaggi.add(temp);
    }

    public void reset() {
        messaggi = new ArrayList<Messaggio>();
    }
}
