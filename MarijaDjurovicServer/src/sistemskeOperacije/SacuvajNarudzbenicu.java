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
public class SacuvajNarudzbenicu extends OpstaSistemskaOperacija{

    @Override
    public ServerskiTransferObjekat izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiTransferObjekat transferObjekat = new ServerskiTransferObjekat();
        try {
            Narudzbenica nar = (Narudzbenica) odo;
            DBBroker.getInstance().insert(nar);
            int narudzbenicaID = DBBroker.getInstance().maxKljuc(nar);
            nar.setNarudzbenicaID(narudzbenicaID);
            for (OpstiDomenskiObjekat stavka : nar.getListaStavki()) {
                StavkaNarudzbenice sn = (StavkaNarudzbenice) stavka;
                sn.setNarudzbenica(nar);
                DBBroker.getInstance().insert(sn);
            }
            transferObjekat.setUspesno(true);
            transferObjekat.setPoruka("Sistem je zapamtio narudzbenicu");
        } catch (Exception ex) {
            Logger.getLogger(SacuvajNarudzbenicu.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem nije zapamtio narudzbenicu");
        }
        return transferObjekat;
    }

    @Override
    public void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
    }
    
}
