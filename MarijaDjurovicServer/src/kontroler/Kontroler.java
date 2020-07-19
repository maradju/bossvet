/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.OpstiDomenskiObjekat;
import sistemskeOperacije.IzmeniKlijenta;
import sistemskeOperacije.IzmeniNarudzbenicu;
import sistemskeOperacije.ObrisiKlijenta;
import sistemskeOperacije.ObrisiNarudzbenicu;
import sistemskeOperacije.PretragaProizvoda;
import sistemskeOperacije.PretragaZaposlenih;
import sistemskeOperacije.PretraziKlijente;
import sistemskeOperacije.PretraziNarudzbenica;
import sistemskeOperacije.SacuvajKlijenta;
import sistemskeOperacije.SacuvajNarudzbenicu;
import sistemskeOperacije.UlogujSO;
import transfer.ServerskiTransferObjekat;

/**
 *
 * @author Miroslav
 */
public class Kontroler {
    private static Kontroler instance;

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if(instance == null)
            instance = new Kontroler();
        
        return instance;
    }

    public ServerskiTransferObjekat uloguj(OpstiDomenskiObjekat odo) {
        return new UlogujSO().izvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat unesiKlijenta(OpstiDomenskiObjekat odo) {
        return new SacuvajKlijenta().izvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat pretraziKlijenta(OpstiDomenskiObjekat odo) {
        return new PretraziKlijente().izvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat izmeniKlijenta(OpstiDomenskiObjekat odo) {
        return new IzmeniKlijenta().izvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat obrisiKlijenta(OpstiDomenskiObjekat odo) {
        return new ObrisiKlijenta().izvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat pretagaZaposlenih(OpstiDomenskiObjekat odo) {
        return new PretragaZaposlenih().izvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat pretragaProizvoda(OpstiDomenskiObjekat odo) {
        return new PretragaProizvoda().izvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat sacuvajNarudzbenicu(OpstiDomenskiObjekat odo) {
        return new SacuvajNarudzbenicu().izvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat pretraziNarudzbenice(OpstiDomenskiObjekat odo) {
        return new PretraziNarudzbenica().izvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat izmeniNarudzbenicu(OpstiDomenskiObjekat odo) {
        return new IzmeniNarudzbenicu().izvrsiTransakciju(odo);
    }

    public ServerskiTransferObjekat obrisiNarudzbenicu(OpstiDomenskiObjekat odo) {
        return new ObrisiNarudzbenicu().izvrsiTransakciju(odo);
    }
    
    
}
