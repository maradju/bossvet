/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miroslav
 */
public class Klijent implements OpstiDomenskiObjekat{
    private int pib;
    private String nazivKlijenta;
    private String adresa;
    private String brojTelefona;
    private String emailKlijenta;

    public Klijent() {
    }

    public Klijent(int pib, String nazivKlijenta, String adresa, String brojTelefona, String emailKlijenta) {
        this.pib = pib;
        this.nazivKlijenta = nazivKlijenta;
        this.adresa = adresa;
        this.brojTelefona = brojTelefona;
        this.emailKlijenta = emailKlijenta;
    }

    public String getEmailKlijenta() {
        return emailKlijenta;
    }

    public void setEmailKlijenta(String emailKlijenta) {
        this.emailKlijenta = emailKlijenta;
    }

    public int getPib() {
        return pib;
    }

    public void setPib(int pib) {
        this.pib = pib;
    }

    public String getNazivKlijenta() {
        return nazivKlijenta;
    }

    public void setNazivKlijenta(String nazivKlijenta) {
        this.nazivKlijenta = nazivKlijenta;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    @Override
    public String toString() {
        return nazivKlijenta;
    }

    @Override
    public String tabelaNaziv() {
        return " klijent ";
    }

    @Override
    public String koloneInsert() {
        return "pib,nazivKlijenta,adresa,brojTelefona,emailKlijenta";
    }

    @Override
    public String insertVrednosti() {
        return pib+",'"+nazivKlijenta+"','"+adresa+"','"+brojTelefona+"','"+emailKlijenta+"'";
    }

    @Override
    public String updateVrednosti() {
        return "nazivKlijenta='"+nazivKlijenta+"',adresa='"+adresa+"',brojTelefona='"+brojTelefona+"',emailKlijenta='"+emailKlijenta+"'";
    }

    @Override
    public String wherePrimarniKljuc() {
        return " pib ="+pib;
    }

    @Override
    public String joinUslov() {
        return "";
    }

    @Override
    public String whereSelect() {
        
        if(nazivKlijenta != null){
            return " WHERE nazivKlijenta LIKE '%"+nazivKlijenta+"%'";
        }
        
        return "";
    }

    @Override
    public List<OpstiDomenskiObjekat> napraviListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> listaKlijenata = new ArrayList<>();
        
        while (rs.next()) {            
            listaKlijenata.add(new Klijent(rs.getInt("pib"),rs.getString("nazivKlijenta"), rs.getString("adresa"), rs.getString("brojTelefona"), rs.getString("emailKlijenta")));
        }
        return listaKlijenata;
    }

    @Override
    public String max() {
        return " pib ";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Klijent other = (Klijent) obj;
        if (this.pib != other.pib) {
            return false;
        }
        return true;
    }
    
    
}
