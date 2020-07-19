/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;



import domen.OpstiDomenskiObjekat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Konstante;
import kontroler.Kontroler;
import transfer.KlijentskiTransferObjekat;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author Cule
 */
public class NitKlijent extends Thread{
    
    Socket soket;
    boolean kraj = false;
    

    public NitKlijent(Socket soket) {
        this.soket = soket;
    }

    @Override
    public void run() {
        while (!kraj) {            
            KlijentskiTransferObjekat kto = primiZahtev();
            ServerskiTransferObjekat sto = new ServerskiTransferObjekat();
            OpstiDomenskiObjekat odo = kto.getParametar();
            switch(kto.getOperacija()){
                case Konstante.ULOGUJ:
                    sto = Kontroler.getInstance().uloguj(odo);
                    break;
                case Konstante.UNESI_KLIJENTA:
                    sto = Kontroler.getInstance().unesiKlijenta(odo);
                    break;
                case Konstante.PRETRAZI_KLIJENTA:
                    sto = Kontroler.getInstance().pretraziKlijenta(odo);
                    break;
                case Konstante.IZMENI_KLIJENTA:
                    sto = Kontroler.getInstance().izmeniKlijenta(odo);
                    break;
                    
                case Konstante.OBRISI_KLIJENTA:
                    sto = Kontroler.getInstance().obrisiKlijenta(odo);
                    break;
                case Konstante.PRETRAGA_ZAPOSLENIH:
                    sto = Kontroler.getInstance().pretagaZaposlenih(odo);
                    break;
                case Konstante.PRETRAGA_PROIZVODA:
                    sto = Kontroler.getInstance().pretragaProizvoda(odo);
                    break;
                case Konstante.SACUVAJ_NARUDZBENICU:
                    sto = Kontroler.getInstance().sacuvajNarudzbenicu(odo);
                    break;
                case Konstante.PRETRAGA_NARUDZBENICE:
                    sto = Kontroler.getInstance().pretraziNarudzbenice(odo);
                    break;
                case Konstante.IZMENI_NARUDZBENICU:
                    sto = Kontroler.getInstance().izmeniNarudzbenicu(odo);
                    break;
                case Konstante.OBRISI_NARUDZBENICU:
                    sto = Kontroler.getInstance().obrisiNarudzbenicu(odo);
                    break;
                case Konstante.KRAJ_RADA:
                    kraj=true;
                    sto.setPoruka("Sistem je zavrsio sa radom.");
                    break;
            }
            posalji(sto);
        }
    }
    
    public void posalji(ServerskiTransferObjekat sto){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(soket.getOutputStream());
            oos.writeObject(sto);
        } catch (IOException ex) {
            Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public KlijentskiTransferObjekat primiZahtev(){
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat();
        try {
            ObjectInputStream ois = new ObjectInputStream(soket.getInputStream());
            kto = (KlijentskiTransferObjekat) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return kto;
    }
}
