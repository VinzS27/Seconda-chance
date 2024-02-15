package Project;

public class Armi extends Oggetti{
    
    protected int attMagico;
    protected int attFisico;

    public Armi(int attMagico, int attFisico, int costo, int lvl,
            String nome, String descrizione, String rarità, int id) {
        this.attMagico = attMagico;
        this.attFisico = attFisico;
        this.costo = costo;
        this.lvl = lvl;
        this.nome = nome;
        this.descrizione = descrizione;
        this.rarità = rarità;
        this.id = id;
    }

    public int getAttMagico() {
        return this.attMagico;
    }

    public int getAttFisico() {
        return this.attFisico;
    }
    
    public int getLivello(){
        return this.lvl;
    }
    
    @Override
    public void setCosto(int aggiunta) {
        this.costo = aggiunta;
    }
    
    @Override
    public String getStatInventario(){
           return(this.getNome()+" AF: "+this.getAttFisico()+" AM: "+this.getAttMagico()+" $: "+this.getCosto());
    }
    
    @Override
    public String toString(){
        String format = String.format("%-15s$%-10dLiv:%-8dAM:%-10dAF:%-8s%-1s\n",
                getNome() , getCosto(), getLivello(),getAttMagico(),
                getAttFisico(),getDescrizione());
       return (format);
    }
    @Override
    public String TabStat(){
        String format = String.format("%-7s%-15s%-25s%-7s%-7s%-1s\n", "$", "Nome",
                "Descrizione", "Mag","Fis","lvl");
        return(format);
    }
}