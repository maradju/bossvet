/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeOperacije;

import db.DBBroker;
import domen.Narudzbenica;
import domen.OpstiDomenskiObjekat;
import domen.StavkaNarudzbenice;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author Miroslav
 */
public class IzmeniNarudzbenicu extends OpstaSistemskaOperacija{

    @Override
    public ServerskiTransferObjekat izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiTransferObjekat transferObjekat = new ServerskiTransferObjekat();
        try {
            Narudzbenica nar = (Narudzbenica) odo;
            DBBroker.getInstance().update(nar);
            StavkaNarudzbenice stavka = new StavkaNarudzbenice();
            stavka.setNarudzbenica(nar);
            
            DBBroker.getInstance().delete(stavka);

            for (OpstiDomenskiObjekat s : nar.getListaStavki()) {
                StavkaNarudzbenice sn = (StavkaNarudzbenice) s;
                sn.setNarudzbenica(nar);
                DBBroker.getInstance().insert(sn);
            }
            transferObjekat.setUspesno(true);
            transferObjekat.setPoruka("Sistem je izmenio narudzbenicu");
        } catch (Exception ex) {
            Logger.getLogger(IzmeniNarudzbenicu.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem nije izmenio narudzbenicu");
        }
        return transferObjekat;
    }

    @Override
    public void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
    }
    
}
