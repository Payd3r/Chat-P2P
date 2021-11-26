/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package whatsapp;

/**
 *
 * @author Payd3r
 */
public class Messaggio {

    int id;
    String contenuto;
    boolean tipo; //true -> inviato || false -> ricevuto
    boolean fatto; //true -> gia' inviato || false -> in attesa di essere inviato

    public Messaggio(String contenuto, int id, boolean t) {
        this.contenuto = contenuto;
        this.id = id;
        this.tipo = t;
        if (tipo == true) {
            fatto = false;
        }
        fatto = true;
    }

    public Messaggio() {
        id = -1;
        contenuto = "";
        tipo = false;
        fatto = false;
    }
}
