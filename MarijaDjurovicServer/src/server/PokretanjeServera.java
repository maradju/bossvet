/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import forme.ServerskaForma;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miroslav
 */
public class PokretanjeServera extends Thread{
    ServerskaForma sf;

    public PokretanjeServera(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(9000);
            System.out.println("Server je pokrenut");
            sf.podesiDaJeServerPokrenut();
            while (true) {    
                Socket soket = ss.accept();
                System.out.println("Klijent se povezao");
                NitKlijent nk = new NitKlijent(soket);
                nk.start();   
            }
        } catch (IOException ex) {
            Logger.getLogger(PokretanjeServera.class.getName()).log(Level.SEVERE, null, ex);
            sf.podesiDaNijeServerPokrenut();
        }
    }
    
    
            
            
}
