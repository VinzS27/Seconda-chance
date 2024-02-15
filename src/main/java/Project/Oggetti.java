package Project;

import java.io.Serializable;

public abstract class Oggetti implements Serializable{
    
    protected int costo;
    protected int lvl;
    protected String nome;
    protected String descrizione;
    protected String rarità;
    protected int id;

    public int getId() {
        return id;
    }
    
    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public String getRarità() {
        return this.rarità;
    }
    
     public int getLvl() {
        return this.lvl;
    }
     
    public int getCosto() {
        return this.costo;
    }
    
    public abstract void setCosto(int aggiunta);
    public abstract String TabStat();
    public abstract String getStatInventario();
   
}