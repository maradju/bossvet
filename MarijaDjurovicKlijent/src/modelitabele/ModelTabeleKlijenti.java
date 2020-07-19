package modelitabele;

import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Miroslav
 */
public class ModelTabeleKlijenti extends AbstractTableModel{
    List<OpstiDomenskiObjekat> listaKlijenata;

    public ModelTabeleKlijenti() {
        listaKlijenata = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return listaKlijenata.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Klijent k = (Klijent) listaKlijenata.get(row);
        
        switch(column){
            case 0: return k.getPib();
            case 1: return k.getNazivKlijenta();
            case 2: return k.getAdresa();
            case 3: return k.getBrojTelefona();
            case 4: return k.getEmailKlijenta();
            default:return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "PIB";
            case 1: return "Naziv klijenta";
            case 2: return "Adresa";
            case 3: return "Broj telefona";
            case 4: return "Email";
            default:return "N/A";
        }
    }

    public void setListaKlijenata(List<OpstiDomenskiObjekat> listaKlijenata) {
        this.listaKlijenata = listaKlijenata;
        fireTableDataChanged();
    }

    public Klijent vratiKlijenta(int red) {
        return (Klijent) listaKlijenata.get(red);
    }
    
}
