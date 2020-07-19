/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeOperacije;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author Miroslav
 */
public abstract class OpstaSistemskaOperacija {
    
    public ServerskiTransferObjekat izvrsiTransakciju(OpstiDomenskiObjekat odo) {
        ServerskiTransferObjekat transferObjekat = new ServerskiTransferObjekat();
        try {
            DBBroker.getInstance().ucitajDrajver();
            DBBroker.getInstance().otvoriKonekciju();
            proveriPreduslov(odo);
            transferObjekat = izvrsiOperaciju(odo);
            DBBroker.getInstance().commit();
        } catch (Exception ex) {
            Logger.getLogger(OpstaSistemskaOperacija.class.getName()).log(Level.SEVERE, null, ex);
            DBBroker.getInstance().rollback();
            transferObjekat.setUspesno(false);
            transferObjekat.setPoruka(ex.getMessage());
        }finally {
            try {
                DBBroker.getInstance().zatvoriKonekciju();
            } catch (SQLException ex) {
                transferObjekat.setUspesno(false);
                transferObjekat.setPoruka(ex.getMessage());
            }
        }
        
        return transferObjekat;
    }

    public abstract ServerskiTransferObjekat izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception;
    public abstract void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception;
}
