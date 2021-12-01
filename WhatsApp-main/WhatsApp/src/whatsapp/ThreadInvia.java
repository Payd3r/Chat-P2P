/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package whatsapp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Payd3r
 */
public class ThreadInvia extends Thread {

    DatagramSocket client;
    BufferInviati buffer;

    public ThreadInvia() throws SocketException {
        client = new DatagramSocket(666);
        buffer = BufferInviati.getIstance();
    }

    @Override
    public void run() {
        Messaggio temp = new Messaggio();
        while (true) {
            if (buffer.controllo()) {
                System.out.println("ciao");
                try {
                    temp = buffer.getMessaggio();
                    byte[] responseBuffer = temp.toString().getBytes();
                    DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
                    responsePacket.setAddress(temp.getAddress());
                    responsePacket.setPort(12345);
                    client.send(responsePacket);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(ThreadInvia.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ThreadInvia.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.print("");
            }

        }
    }
}
