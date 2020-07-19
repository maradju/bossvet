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
public class Nalog implements OpstiDomenskiObjekat{
    private int sifraNaloga;
    private String username;
    private String password;
    private Zaposleni zaposleni;

    public Nalog() {
    }

    public Nalog(int sifraNaloga, String username, String password, Zaposleni zaposleni) {
        this.sifraNaloga = sifraNaloga;
        this.username = username;
        this.password = password;
        this.zaposleni = zaposleni;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public int getSifraNaloga() {
        return sifraNaloga;
    }

    public void setSifraNaloga(int sifraNaloga) {
        this.sifraNaloga = sifraNaloga;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return zaposleni.toString();
    }

    @Override
    public String tabelaNaziv() {
        return " nalog ";
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
        return " n join zaposleni z on n.jmbg=z.jmbg";
    }

    @Override
    public String whereSelect() {
        if(!username.isEmpty() && !password.isEmpty()){
            return " WHERE username='"+username+"' and password='"+password+"'";
        }
        
        return "";
    }

    @Override
    public List<OpstiDomenskiObjekat> napraviListu(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> listaNaloga = new ArrayList<>();
        
        while (rs.next()) {            
            listaNaloga.add(new Nalog(rs.getInt("sifraNaloga"),rs.getString("username"),rs.getString("password"), new Zaposleni(rs.getString("jmbg"), rs.getString("imeZaposlenog"), rs.getString("prezimeZaposlenog"), rs.getString("email"))));
        }
        return listaNaloga;
    }

    @Override
    public String max() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
