/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import properties.DBProperties;

/**
 *
 * @author Miroslav
 */
public class DBBroker {

    private static DBBroker instance;
    private Connection konekcija;
    
    private DBBroker() {
    }

    public static DBBroker getInstance() {
        if(instance == null)
            instance = new DBBroker();
        return instance;
    }
    public void ucitajDrajver() throws ClassNotFoundException{
        Class.forName(DBProperties.getInstance().vratiVrednost("driver"));

    }
    
    public void otvoriKonekciju() throws SQLException{
      
        konekcija = DriverManager.getConnection(DBProperties.getInstance().vratiVrednost("url"), DBProperties.getInstance().vratiVrednost("user"), DBProperties.getInstance().vratiVrednost("pass"));
        konekcija.setAutoCommit(false);
      
    }
    public void zatvoriKonekciju() throws SQLException{
        konekcija.close();
    }
    
    public void commit(){
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void rollback(){
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insert(OpstiDomenskiObjekat odo) throws SQLException{
        String sql = "INSERT INTO "+odo.tabelaNaziv()+"("+odo.koloneInsert()+")"+" VALUES ("+odo.insertVrednosti()+")";
        System.out.println(sql);
        Statement s = konekcija.createStatement();
        s.executeUpdate(sql);
        s.close();
    }
    
    public void update(OpstiDomenskiObjekat odo) throws SQLException{
        String sql = "UPDATE "+odo.tabelaNaziv()+" SET "+odo.updateVrednosti()+" WHERE "+odo.wherePrimarniKljuc();
        System.out.println(sql);
        Statement s = konekcija.createStatement();
        s.executeUpdate(sql);
        s.close();
    }
    
    public void delete(OpstiDomenskiObjekat odo) throws SQLException{
        String sql = "DELETE FROM "+odo.tabelaNaziv()+" WHERE "+odo.wherePrimarniKljuc();
        System.out.println(sql);
        Statement s = konekcija.createStatement();
        s.executeUpdate(sql);
        s.close();
    }
    
    public ResultSet select(OpstiDomenskiObjekat odo) throws SQLException{
        String sql = "SELECT * FROM "+odo.tabelaNaziv()
                +odo.joinUslov()+odo.whereSelect();
        System.out.println(sql);
        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(sql);
        return rs;
    }

    public int maxKljuc(OpstiDomenskiObjekat odo) throws SQLException{
        int maks = 0;
        String sql = "SELECT max("+odo.max()+") as max FROM "+odo.tabelaNaziv();
        System.out.println(sql);
        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while(rs.next())
            maks = rs.getInt("max");
        
        return maks;
    }
}
