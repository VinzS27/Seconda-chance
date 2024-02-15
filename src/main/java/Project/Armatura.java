package Project;

public class Armatura extends Oggetti {
    
    protected int armatura;

    public Armatura(int livello, int armatura, int costo, String nome,
            String descrizione, String rarità, int id) {
        this.lvl = livello;
        this.armatura = armatura;
        this.costo = costo;
        this.nome = nome;
        this.descrizione = descrizione;
        this.rarità = rarità;
        this.id = id;
    }

    public int getArmatura() {
        return this.armatura;
    }
    
    public int getLivello(){
        return this.lvl;
    }
    
    @Override
    public String getStatInventario(){
           return(this.getNome()+" D: "+this.getArmatura()+" $: "+this.getCosto());
    }
    
    @Override
    public void setCosto(int aggiunta) {
        this.costo = aggiunta;
    }
    
    @Override
    public String toString(){
        String format = String.format("%-15s$%-10dLiv:%-8dA:%-10d%-1s\n",
                getNome() , getCosto(), getLivello(),getArmatura(),getDescrizione());
       return (format);
    }
    
    @Override
    public String TabStat(){
        String format = String.format("%-7s%-15s%-27s%-7s%-1s\n", "$", "Nome", "Descrizione", "Dif","lvl");
        return(format);
    }
}