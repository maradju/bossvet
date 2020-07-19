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
import java.util.Objects;

/**
 *
 * @author Miroslav
 */
public class Zaposleni implements OpstiDomenskiObjekat{
    private String jmbg;
    private String imeZaposlenog;
    private String prezimeZaposlenog;
    private String email;

    public Zaposleni() {
    }

    public Zaposleni(String jmbg, String imeZaposlenog, String prezimeZaposlenog, String email) {
        this.jmbg = jmbg;
        this.imeZaposlenog = imeZaposlenog;
        this.prezimeZaposlenog = prezimeZaposlenog;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getImeZaposlenog() {
        return imeZaposlenog;
    }

    public void setImeZaposlenog(String imeZaposlenog) {
        this.imeZaposlenog = imeZaposlenog;
    }

    public String getPrezimeZaposlenog() {
        return prezimeZaposlenog;
    }

    public void setPrezimeZaposlenog(String prezimeZaposlenog) {
        this.prezimeZaposlenog = prezimeZaposlenog;
    }

    @Override
    public String toString() {
        return imeZaposlenog+" "+prezimeZaposlenog;
    }

    @Override
    public String tabelaNaziv() {
        return " zaposleni ";
    }

    @Override
    public String koloneInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String insertVrednosti() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateVrednosti() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String wherePrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinUslov() {
        return "";
    }

    @Override
    public String whereSelect() {
        return "";
    }

    @Override
    public List<OpstiDomenskiObjekat> napraviListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> listaZaposlenih = new ArrayList<>();
        
        while (rs.next()) {            
            listaZaposlenih.add(new Zaposleni(rs.getString("jmbg"), rs.getString("imeZaposlenog"), rs.getString("prezimeZaposlenog"), rs.getString("email")));
        }
        return listaZaposlenih;
    }

    @Override
    public String max() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        final Zaposleni other = (Zaposleni) obj;
        if (!Objects.equals(this.jmbg, other.jmbg)) {
            return false;
        }
        return true;
    }
    
    
}
