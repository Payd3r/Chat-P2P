/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package whatsapp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Payd3r
 */
public class IO {

    static IO istance;
    //socket PortaRicezione
    static DatagramSocket ricezione;
    //socket PortaInvio
    static DatagramSocket invio;
    //indirizzo destinatario
    InetAddress indirizzoD;
    String destinatario;

    IO() throws SocketException {
        this.ricezione = new DatagramSocket(12345);
        this.invio = new DatagramSocket(666);
        indirizzoD = null;
    }

    static IO getIstance() throws SocketException {
        if (istance == null) {
            synchronized (IO.class) {
                if (istance == null) {
                    istance = new IO();
                }
            }
        }
        return istance;
    }

    public boolean invia(String s) throws IOException {
        //controllo se so con chi sto comunicando
        if (indirizzoD != null) {
            byte[] responseBuffer = s.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            responsePacket.setAddress(indirizzoD);
            responsePacket.setPort(invio.getPort());
            invio.send(responsePacket);
            return true;
        }
        return false;
    }

    public boolean inviaInizio(String s, InetAddress ip) throws IOException {
        //controllo se so con chi sto comunicando
        if (indirizzoD == null) {
            byte[] responseBuffer = s.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
            responsePacket.setAddress(ip);
            responsePacket.setPort(666);
            invio.send(responsePacket);
            return true;
        }
        return false;
    }

    public String ricevi() throws IOException {
        //ricevo un messaggio       
        byte[] buffer = new byte[1500];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        ricezione.receive(packet);
        byte[] dataReceived = packet.getData(); // copia del buffer dichiarato sopra
        if (indirizzoD != null) {
            //se non sto ancora comunicando 
            //salvo l'indirizzo
            indirizzoD = packet.getAddress();
            //ricevo il pacchetto
            return new String(dataReceived, 0, packet.getLength());
        }
        return "";
    }
}
