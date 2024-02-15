package Project;

import java.io.Serializable;
import java.util.ArrayList;

public class Negozio implements Serializable{
    
    protected ArrayList<Armi> listArmi;
    protected ArrayList<Armatura> listArmature;
    protected ArrayList<Consumabili> listConsumabili;

    public Negozio() {
        
        this.listArmi = new ArrayList<>();
        this.listArmi.add(new Armi(8, 15, 40, 1, "Skipper", "Spada di base", "C", 1));
        this.listArmi.add(new Armi(16, 30, 70, 4, "Santal", "Spada avanzata", "B",1));
        this.listArmi.add(new Armi(32, 60, 130, 8, "Tropico", "Spada veterana", "A",1));
        
        this.listArmi.add(new Armi(18, 8, 40, 1, "Sambuca", "libro di base", "C",1));
        this.listArmi.add(new Armi(36, 16, 70, 4, "Tequila", "libro avanzata", "B",1));
        this.listArmi.add(new Armi(72, 32, 130, 8, "Absolut", "libro veterana", "A",1));
        
        this.listArmi.add(new Armi(12, 12, 30, 1, "Sansui", "Carte di base", "C",1));
        this.listArmi.add(new Armi(24, 24, 60, 4, "Sharp", "Carte avanzate", "B",1));
        this.listArmi.add(new Armi(48, 48, 120, 8, "Panasonic", "Carte veterane", "A",1));
        
        this.listArmature = new ArrayList<>();
        this.listArmature.add(new Armatura(1,22, 40, "Siciliana", "Armatura di base   ", "C",2));
        this.listArmature.add(new Armatura(4, 44, 70, "Abruzzese", "Armatura avanzato", "B",2));
        this.listArmature.add(new Armatura(8, 88, 130, "Lombarda ", "Armatura veterano", "A ",2));
        
        this.listArmature.add(new Armatura(1, 18, 30, "Ruscella", "Tunica di base", "C",2));
        this.listArmature.add(new Armatura(4, 36, 60, "Uliveto", "Tunica avanzato", "B",2));
        this.listArmature.add(new Armatura(8, 72, 120, "Evian", "Tunica veterano", "A",2));
        
        this.listArmature.add(new Armatura(1, 20, 35, "Yomo", "Veste di base", "C",2));
        this.listArmature.add(new Armatura(4, 40, 65, "Activia", "Veste avanzata", "B",2));
        this.listArmature.add(new Armatura(8, 80, 125, "Muller", "Veste veterana", "A",2));
        
        this.listConsumabili = new ArrayList<>();
        this.listConsumabili.add(new Consumabili("Salute +15", "Pozione di salute", "C", 15, 20, 1,3,9));
        this.listConsumabili.add(new Consumabili("Magia +5", "Pozione di magia", "C", 5, 50, 1,3,9));
        this.listConsumabili.add(new Consumabili("Forza +5", "Pozione di forza", "C", 5, 50, 1,3, 9));
        this.listConsumabili.add(new Consumabili("Grimaldello", "Apre serrature", "C", 0, 20, 1,3, 999999999));
    }
    
    //Elimina dal negozio e aggiunge all'inventario
    protected void acquista(Oggetti o){
        switch(o.getId()){
            case 1 ->
                this.listArmi.remove((Armi)o);
            case 2 ->
                this.listArmature.remove((Armatura)o);
            case 3 -> {
                int i = this.listConsumabili.indexOf(o);
                this.listConsumabili.get(i).setQuantità(this.listConsumabili.get(i).getQuantità()-1);
            }
            default -> {
            }
        }
    }
    
    //Aggiunge dall'inventario al negozio
    protected void vendi(Oggetti o){
                switch(o.getId()){
            case 1 -> {
                o.setCosto((int) (o.getCosto()*1.25));
                this.listArmi.add((Armi)o);
            }
            case 2 -> {
                o.setCosto((int) (o.getCosto()*1.25));
                this.listArmature.add((Armatura)o);
            }
            case 3 -> {
                Consumabili c = (Consumabili) o;
                c.setQuantità(c.getQuantità()+1);
            }
            default -> {
            }
        }
    }
}