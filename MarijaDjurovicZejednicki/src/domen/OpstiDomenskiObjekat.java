/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Miroslav
 */
public interface OpstiDomenskiObjekat extends Serializable{

    public String tabelaNaziv();
    public String koloneInsert();
    public String insertVrednosti();
    public String updateVrednosti();
    public String wherePrimarniKljuc();
    public String joinUslov();
    public String whereSelect();
    public List<OpstiDomenskiObjekat> napraviListu(ResultSet rs) throws SQLException;
    public String max();
}
