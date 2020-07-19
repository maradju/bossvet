package modelitabele;

import domen.Klijent;
import domen.Narudzbenica;
import domen.OpstiDomenskiObjekat;
import java.text.SimpleDateFormat;
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
public class ModelTabeleNarudzbenica extends AbstractTableModel{
    List<OpstiDomenskiObjekat> listaNarudzbenica;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public ModelTabeleNarudzbenica() {
        listaNarudzbenica = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return listaNarudzbenica.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Narudzbenica n = (Narudzbenica) listaNarudzbenica.get(row);
        
        switch(column){
            case 0: return n.getKlijent();
            case 1: return n.getZaposleni();
            case 2: return n.getUkupno();
            case 3: return sdf.format(n.getDatum());
            default:return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Klijent";
            case 1: return "Zaposleni";
            case 2: return "Ukupno";
            case 3: return "Datum";
            default:return "N/A";
        }
    }

    public void setListaNarudzbenica(List<OpstiDomenskiObjekat> listaNarudzbenica) {
        this.listaNarudzbenica = listaNarudzbenica;
        fireTableDataChanged();
    }

    public Narudzbenica vratiNarudzbenicu(int red){
        return (Narudzbenica) listaNarudzbenica.get(red);
    }
    
}
