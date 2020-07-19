/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Nalog;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import konstante.Konstante;
import transfer.KlijentskiTransferObjekat;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author Miroslav
 */
public class Kontroler {

    private static Kontroler instance;
    Nalog ulogovaniNalog;
    
    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if(instance == null)
            instance = new Kontroler();
        return instance;
    }

    public void setUlogovaniNalog(Nalog ulogovaniNalog) {
        this.ulogovaniNalog = ulogovaniNalog;
    }

    public Nalog getUlogovaniNalog() {
        return ulogovaniNalog;
    }

    public ServerskiTransferObjekat posaljiZahtevIPrimiOdgovor(int operacija, OpstiDomenskiObjekat odo) {
        KlijentskiTransferObjekat kto = new KlijentskiTransferObjekat(operacija, odo);
        Komunikacija.getInstanca().posaljiZahtev(kto);
        return Komunikacija.getInstanca().primiOdgovor();
    }
    
    
}
