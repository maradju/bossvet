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
public class ObrisiNarudzbenicu extends OpstaSistemskaOperacija{

    @Override
    public ServerskiTransferObjekat izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiTransferObjekat transferObjekat = new ServerskiTransferObjekat();
        try {
            DBBroker.getInstance().delete(odo);
            transferObjekat.setUspesno(true);
            transferObjekat.setPoruka("Sistem je obrisao narudzbenicu");
        } catch (Exception ex) {
            Logger.getLogger(ObrisiNarudzbenicu.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem nije obrisao narudzbenicu");
        }
        return transferObjekat;
    }

    @Override
    public void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
    }
    
}
