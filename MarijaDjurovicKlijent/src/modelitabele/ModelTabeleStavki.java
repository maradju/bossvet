package modelitabele;

import domen.Klijent;
import domen.Narudzbenica;
import domen.OpstiDomenskiObjekat;
import domen.StavkaNarudzbenice;
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
public class ModelTabeleStavki extends AbstractTableModel{
    List<OpstiDomenskiObjekat> listaStavki;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public ModelTabeleStavki() {
        listaStavki = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return listaStavki.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public List<OpstiDomenskiObjekat> getListaStavki() {
        return listaStavki;
    }
    
    

    @Override
    public Object getValueAt(int row, int column) {
        StavkaNarudzbenice s = (StavkaNarudzbenice) listaStavki.get(row);
        
        switch(column){
            case 0: return s.getRb();
            case 1: return s.getProizvod();
            case 2: return s.getProizvod().getCenaProizvoda();
            case 3: return s.getKolicina();
            case 4: return s.getVrednost();
            default:return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Redni broj";
            case 1: return "Prozivod";
            case 2: return "Cena";
            case 3: return "Kolicina";
            case 4: return "Ukupno";
            default:return "N/A";
        }
    }

    public void setListaStavki(List<OpstiDomenskiObjekat> listaStavki) {
        this.listaStavki = listaStavki;
        fireTableDataChanged();
    }

    public Narudzbenica vratiNarudzbenicu(int red){
        return (Narudzbenica) listaStavki.get(red);
    }

    public void dodajStavku(StavkaNarudzbenice stavka) {
        listaStavki.add(stavka);
        srediRedneBrojeve();
        fireTableDataChanged();
    }

    private void srediRedneBrojeve() {
        List<OpstiDomenskiObjekat> sredjenaLista = new ArrayList<>();
        int brojac = 1;
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaStavki) {
            StavkaNarudzbenice stavka = (StavkaNarudzbenice) opstiDomenskiObjekat;
            stavka.setRb(brojac);
            brojac++;
            sredjenaLista.add(stavka);
        }
        
        this.listaStavki = sredjenaLista;
    }

    public void obrisiStavku(int red) {
        listaStavki.remove(red);
        srediRedneBrojeve();
        fireTableDataChanged();
    }
    
}
