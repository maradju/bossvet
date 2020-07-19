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
public class Narudzbenica implements OpstiDomenskiObjekat{
    private int narudzbenicaID;
    private Date datum;
    private double ukupno;
    private Klijent klijent;
    private Zaposleni zaposleni;
    private Nalog nalog;
    private List<OpstiDomenskiObjekat> listaStavki;

    public Narudzbenica() {
    }

    public Narudzbenica(int narudzbenicaID, Date datum, double ukupno, Klijent klijent, Zaposleni zaposleni, Nalog nalog, List<OpstiDomenskiObjekat> listaStavki) {
        this.narudzbenicaID = narudzbenicaID;
        this.datum = datum;
        this.ukupno = ukupno;
        this.klijent = klijent;
        this.zaposleni = zaposleni;
        this.nalog = nalog;
        this.listaStavki = listaStavki;
    }

    public int getNarudzbenicaID() {
        return narudzbenicaID;
    }

    public void setNarudzbenicaID(int narudzbenicaID) {
        this.narudzbenicaID = narudzbenicaID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getUkupno() {
        return ukupno;
    }

    public void setUkupno(double ukupno) {
        this.ukupno = ukupno;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Nalog getNalog() {
        return nalog;
    }

    public void setNalog(Nalog nalog) {
        this.nalog = nalog;
    }

    public List<OpstiDomenskiObjekat> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<OpstiDomenskiObjekat> listaStavki) {
        this.listaStavki = listaStavki;
    }

    @Override
    public String tabelaNaziv() {
        return " narudzbenica ";
    }

    @Override
    public String koloneInsert() {
        return "datum,ukupno,pib,jmbg,sifraNaloga";
    }

    @Override
    public String insertVrednosti() {
        return "'"+new java.sql.Date(datum.getTime())+"',"+ukupno+","+klijent.getPib()+",'"+zaposleni.getJmbg()+"',"+nalog.getSifraNaloga();
    }

    @Override
    public String updateVrednosti() {
        return "datum='"+new java.sql.Date(datum.getTime())+"',ukupno="+ukupno+",pib="+klijent.getPib()+",jmbg='"+zaposleni.getJmbg()+"',sifraNaloga="+nalog.getSifraNaloga();
    }

    @Override
    public String wherePrimarniKljuc() {
        return " narudzbenicaID="+narudzbenicaID;
    }

    @Override
    public String joinUslov() {
        return " n join klijent k on n.pib=k.pib join zaposleni z on n.jmbg = z.jmbg join nalog nal on n.sifraNaloga=nal.sifraNaloga ";
    }

    @Override
    public String whereSelect() {
        return " where k.nazivKlijenta LIKE '%"+klijent.getNazivKlijenta()+"%' ";
    }

    @Override
    public List<OpstiDomenskiObjekat> napraviListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {            
            Klijent klijent = new Klijent(rs.getInt("pib"),rs.getString("nazivKlijenta"), rs.getString("adresa"), rs.getString("brojTelefona"), rs.getString("emailKlijenta"));
            Zaposleni zap = new Zaposleni(rs.getString("jmbg"), rs.getString("imeZaposlenog"), rs.getString("prezimeZaposlenog"), rs.getString("email"));
            Nalog nalog = new Nalog(rs.getInt("sifraNaloga"),rs.getString("username"),rs.getString("password"), null);
            Narudzbenica narudzbenica = new Narudzbenica(rs.getInt("narudzbenicaID"), new Date(rs.getDate("datum").getTime()), rs.getDouble("ukupno"), klijent, zap, nalog, null);
            
            lista.add(narudzbenica);
        }
        
        return lista;
    }

    @Override
    public String max() {
        return "narudzbenicaID";
    }
    
    
}
