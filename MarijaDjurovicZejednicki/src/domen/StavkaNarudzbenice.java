/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Miroslav
 */
public class StavkaNarudzbenice implements OpstiDomenskiObjekat{
    private Narudzbenica narudzbenica;
    private int rb;
    private Proizvod proizvod;
    private int kolicina;
    private double vrednost;

    public StavkaNarudzbenice() {
    }

    public StavkaNarudzbenice(Narudzbenica narudzbenica, int rb, Proizvod proizvod, int kolicina, double vrednost) {
        this.narudzbenica = narudzbenica;
        this.rb = rb;
        this.proizvod = proizvod;
        this.kolicina = kolicina;
        this.vrednost = vrednost;
    }

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    public Narudzbenica getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(Narudzbenica narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String tabelaNaziv() {
        return " stavkanarudzbenice ";
    }

    @Override
    public String koloneInsert() {
        return "narudzbenicaID,redniBroj,sifraProizvoda,kolicina,vrednost";
    }

    @Override
    public String insertVrednosti() {
        return narudzbenica.getNarudzbenicaID()+","+rb+","+proizvod.getSifraProizvoda()+","+kolicina+","+vrednost;
    }

    @Override
    public String updateVrednosti() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String wherePrimarniKljuc() {
        return " narudzbenicaID="+narudzbenica.getNarudzbenicaID();
    }

    @Override
    public String joinUslov() {
        return " st join proizvod p on st.sifraProizvoda=p.sifraProizvoda join narudzbenica n on st.narudzbenicaID = n.narudzbenicaID join klijent k on n.pib=k.pib join zaposleni z on n.jmbg = z.jmbg join nalog nal on n.sifraNaloga=nal.sifraNaloga ";
    }

    @Override
    public String whereSelect() {
        return " where st.narudzbenicaID="+narudzbenica.getNarudzbenicaID();
    }

    @Override
    public List<OpstiDomenskiObjekat> napraviListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {            
            Klijent klijent = new Klijent(rs.getInt("pib"),rs.getString("nazivKlijenta"), rs.getString("adresa"), rs.getString("brojTelefona"), rs.getString("emailKlijenta"));
            Zaposleni zap = new Zaposleni(rs.getString("jmbg"), rs.getString("imeZaposlenog"), rs.getString("prezimeZaposlenog"), rs.getString("email"));
            Nalog nalog = new Nalog(rs.getInt("sifraNaloga"),rs.getString("username"),rs.getString("password"), null);
            Proizvod proizvod = new Proizvod(rs.getInt("sifraProizvoda"), rs.getString("nazivProizvoda"), rs.getDouble("cenaProizvoda"), rs.getString("opisProizvoda"));
            Narudzbenica narudzbenica = new Narudzbenica(rs.getInt("narudzbenicaID"), new Date(rs.getDate("datum").getTime()), rs.getDouble("ukupno"), klijent, zap, nalog, null);
            
            StavkaNarudzbenice stavka = new StavkaNarudzbenice(narudzbenica, rs.getInt("redniBroj"), proizvod, rs.getInt("kolicina"), rs.getDouble("vrednost"));
            lista.add(stavka);
        }
        
        return lista;
    }

    @Override
    public String max() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
