/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeOperacije;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author Miroslav
 */
public class UlogujSO extends OpstaSistemskaOperacija{

    @Override
    public ServerskiTransferObjekat izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiTransferObjekat transferObjekat = new ServerskiTransferObjekat();
        try {
            List<OpstiDomenskiObjekat> lista = odo.napraviListu(DBBroker.getInstance().select(odo));
            if(lista.isEmpty())
                throw new Exception("Neuspesno prijavljivanje na sistem");
            
            transferObjekat.setOdgovor(lista.get(0));
            transferObjekat.setUspesno(true);
            transferObjekat.setPoruka("Uspesno ste se prijavili na sistem");
        } catch (Exception ex) {
            Logger.getLogger(UlogujSO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno prijavljivanje na sistem");
        }
        return transferObjekat;
    }

    @Override
    public void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
    }
    
}
