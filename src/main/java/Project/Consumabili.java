package Project;

public class Consumabili extends Oggetti{
  
    protected int bonus;
    protected int quantità;

    public Consumabili(String nome, String descrizione, String rarità,
            int bonus, int costo, int lvl, int id, int qta) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.rarità = rarità;
        this.bonus = bonus;
        this.costo = costo;
        this.lvl = lvl;
        this.id = id;
        this.quantità = qta;
    }

    public int getQuantità(){
        return this.quantità;
    }
    
    public int getLivello(){
        return this.lvl;
    }
    
    public void setQuantità(int qta){
        this.quantità = qta;
    }
    
    public int getBonus() {
        return this.bonus;
    }
    
    @Override
    public void setCosto(int aggiunta) {
        this.costo = aggiunta;
    }
    
    @Override
    public String toString(){
        String format;
        if(this.getNome().equals("Grimaldello"))
            format = String.format("%-15s$%-8dLiv:%-8d%-10s%-1s\n",getNome() , getCosto(), getLivello()
            ,"∞",getDescrizione());
        else
            format = String.format("%-15s$%-8dLiv:%-8dx%-10d%-1s\n",getNome() , getCosto(), getLivello()
            ,getQuantità(),getDescrizione());
        return (format);
    }
    @Override
    public String TabStat(){
        String format = String.format("%-7s%-30s%-10s%-5s%-1s\n", "$", "Nome",
                "Bonus","Qta","LvL");
        return(format);
    }
    
    @Override
    public String getStatInventario(){
           return(this.getNome()+" B: "+this.getBonus()+" x"+this.getQuantità());
    }
}