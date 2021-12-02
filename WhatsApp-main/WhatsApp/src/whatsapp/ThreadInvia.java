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
import javax.swing.JFrame;

/**
 *
 * @author Payd3r
 */
public class ThreadInvia extends Thread {

    DatagramSocket client;
    BufferInviati buffer;
    JFrame frame;

    public ThreadInvia(JFrame a) throws SocketException {
        client = new DatagramSocket(666);
        buffer = BufferInviati.getIstance();
        frame = a;
    }

    @Override
    public void run() {
        Messaggio temp = new Messaggio();
        while (true) {
            if (buffer.controllo()) {
                try {
                    temp = buffer.getMessaggio();
                    byte[] responseBuffer = temp.toString().getBytes();
                    DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
                    responsePacket.setAddress(temp.getAddress());
                    responsePacket.setPort(12346);
                    client.send(responsePacket);
                    //stampo contenuto
                    System.out.println("Invio:" + temp.toString());
                } catch (UnknownHostException ex) {
                    Logger.getLogger(ThreadInvia.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ThreadInvia.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.print("");
            }
            frame.repaint();
        }
    }
}
