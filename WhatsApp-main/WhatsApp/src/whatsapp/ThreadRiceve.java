/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package whatsapp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Payd3r
 */
public class ThreadRiceve extends Thread {

    DatagramSocket server;
    BufferRicevuti buffer;

    public ThreadRiceve() throws SocketException {
        server = new DatagramSocket(12345);
        buffer = BufferRicevuti.getIstance();
    }

    @Override
    public void run() {
        while (true) {
            try {
                byte[] vett = new byte[1500];
                DatagramPacket packet = new DatagramPacket(vett, vett.length);
                server.receive(packet);
                byte[] dataReceived = packet.getData();
                buffer.aggiungi(new String(dataReceived, 0, packet.getLength()), packet.getAddress().toString());
            } catch (IOException ex) {
                Logger.getLogger(ThreadRiceve.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
