/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package whatsapp;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Payd3r
 */
public class Messaggio {

    String scelta;
    String contenuto;
    String indirizzo;

    public Messaggio(String a, String b, String c) {
        this.scelta = a;
        if (b == "") {
            this.contenuto = " ";
        } else {
            this.contenuto = b;
        }
        this.indirizzo = c;
    }

    public Messaggio(String a, String b) {
        this.scelta = a;
        if (b == "") {
            this.contenuto = " ";
        } else {
            this.contenuto = b;
        }
        this.indirizzo = "";
    }

    public Messaggio() {
        this.scelta = "";
        this.contenuto = "";
        this.indirizzo = "";
    }

    public InetAddress getAddress() throws UnknownHostException {
        return InetAddress.getByName(indirizzo);
    }

    @Override
    public String toString() {
        if (contenuto.equals(" ")) {
            return scelta + ";";
        } else {
            return scelta + ";" + contenuto + ";";
        }
    }
}
