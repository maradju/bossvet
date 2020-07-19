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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author Miroslav
 */
public class PretraziNarudzbenica extends OpstaSistemskaOperacija{

    @Override
    public ServerskiTransferObjekat izvrsiOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiTransferObjekat transferObjekat = new ServerskiTransferObjekat();
        try {
            List<OpstiDomenskiObjekat> listaPomocna = new ArrayList<>();
            List<OpstiDomenskiObjekat> lista = odo.napraviListu(DBBroker.getInstance().select(odo));
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : lista) {
                Narudzbenica nar = (Narudzbenica) opstiDomenskiObjekat;
                StavkaNarudzbenice stavka = new StavkaNarudzbenice();
                stavka.setNarudzbenica(nar);
                List<OpstiDomenskiObjekat> listaStavki = stavka.napraviListu(DBBroker.getInstance().select(stavka));
                nar.setListaStavki(listaStavki);
                listaPomocna.add(nar);
            }
            transferObjekat.setOdgovor(listaPomocna);
            transferObjekat.setUspesno(true);
            transferObjekat.setPoruka("Sistem je pronasao narudzbenice po zadatim kriterijumima pretrage");
        } catch (Exception ex) {
            Logger.getLogger(PretraziNarudzbenica.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem nije pronasao narudzbenice po zadatim kriterijumima pretrage");
        }
        return transferObjekat;
    }

    @Override
    public void proveriPreduslov(OpstiDomenskiObjekat odo) throws Exception {
    }
    
}
