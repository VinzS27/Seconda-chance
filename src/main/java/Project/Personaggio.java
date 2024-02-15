package Project;

import java.io.Serializable;
import java.io.Serial;
import java.util.ArrayList;

public class Personaggio implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    protected String nome;
    protected String classe;
    protected int attMagico;
    protected int attFisico;
    protected int armatura;
    protected int fortuna;
    protected int salute;
    protected int lvlGiocatore;
    protected int esperienza;
    protected int maxESperienza;
    protected int soldi;
    protected int lvlGioco;
    protected int progressilvl;
    protected int abilità;
    protected boolean[] scelteImp;
    protected int[] equip;
    protected ArrayList <Oggetti> inventario;
    protected int maxSalute;
    protected int i;  //ultima posizione scelte importanti
    protected boolean a;  //se è stata usata l'abilità true, false altrimenti
    protected int fuga;  //per la fuga dal boss

    public Personaggio(String nome, String classe) {
        this.nome = nome;
        this.classe = classe;
        switch (this.classe){
            case "Guerriero" -> {
                this.abilità = 1;
                this.attMagico = 9;
                this.attFisico = 15;
                this.armatura = 22;
                this.fortuna = 10;
                this.salute = 210;
                this.maxSalute = 210;
                this.soldi = 0;
            }
            case "Mago" -> {
                this.abilità = 2;
                this.attMagico = 18;
                this.attFisico = 10;
                this.armatura = 18;
                this.fortuna = 10;
                this.salute = 200;
                this.maxSalute = 200;
                this.soldi = 0;
            }
            case "Stratega" -> {
                this.abilità = 3;
                this.attMagico = 12;
                this.attFisico = 12;
                this.armatura = 20;
                this.fortuna = 20;
                this.salute = 170;
                this.maxSalute = 170;
                this.soldi = 0;
            }
            default -> {
            }
        }
        this.lvlGioco = 0;
        this.lvlGiocatore = 1;
        this.esperienza = 0;
        this.maxESperienza = 100;
        this.progressilvl = 0;
        this.i = 0;
        this.a = false;
        this.fuga = 0;
        this.scelteImp = new boolean[10];
        this.equip = new int[2];
        this.equip[0] = -1;
        this.equip[1] = -1;
        this.inventario = new ArrayList(9); 
    }

    public int getMaxSalute(){
        return this.maxSalute;
    }   
    
    public String getNome() {
        return nome;
    }

    public String getClasse() {
        return classe;
    }

    public int getAttMagico() {
        int att = this.attMagico;
        Armi a;
        if(this.getEquip()[0] != -1){
            a = (Armi) this.inventario.get(this.equip[0]);
            att += a.getAttMagico();
        }
        return att;
    }

    public int getAttFisico() {
        int att=this.attFisico;
        Armi a;
        if(this.getEquip()[0] != -1){
            a = (Armi) this.inventario.get(this.equip[0]);
            att += a.getAttFisico();
        }
        return att;
    }

    public int getArmatura() {
        int arm=this.armatura;
        Armatura a;
        if (this.getEquip()[1] != -1){
            a = (Armatura) this.inventario.get(this.equip[1]);
            arm += a.getArmatura();
        }
        return arm;
    }

    public int getFortuna() {
        return fortuna;
    }

    public int getSalute() {
        return salute;
    }

    public int getLvlGiocatore() {
        return lvlGiocatore;
    }

    public int getEsperienza() {
        return esperienza;
    }
    
    public int getMaxEsperienza(){
        return this.maxESperienza;
    }

    public int getSoldi() {
        return soldi;
    }

    public int getLvlGioco() {
        return lvlGioco;
    }

    public int getProgressilvl() {
        return progressilvl;
    }

    public boolean getA(){
        return this.a;
    }
    
    public boolean[] getScelteImp() {
        return scelteImp;
    }

    public int[] getEquip() {
        return equip;
    }

    public ArrayList<Oggetti> getInventario() {
        return inventario;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }

    protected void setClasse(String classe) {
        this.classe = classe;
    }

    protected void setAttMagico() {
        int x;
        x = switch(this.classe){
            case "Guerriero" -> 9;
            case "Mago" -> 18;
            default -> 12;
        };
        this.attMagico += (x/2)*(this.lvlGiocatore-1);
    }
    
    protected void setAttMagico(int attmagico) {
        this.attMagico += attmagico;
    }
        
    protected void setAttFisico() {
        int x;
        x = switch(this.classe){
            case "Guerriero" -> 15;
            case "Mago" -> 10;
            default -> 12;
        };
        this.attFisico += (x/2)*(this.lvlGiocatore-1);
    }

    protected void setAttFisico(int attfisico) {
        this.attFisico += attfisico;
    }
    
    protected void setArmatura() {
        int x;
        x = switch(this.classe){
            case "Guerriero" -> 22;
            case "Mago" -> 18;
            default -> 20;
        };
        this.armatura += (x/2)*(this.lvlGiocatore-1);
    }
    
    protected void setArmatura(int armatura) {
        this.armatura += armatura;
    }

    protected void setFortuna() {
       this.fortuna += 2; 
    }
    
    protected void diminuisciSalute(int attaccoNemico) {
        this.salute -= (attaccoNemico-this.getArmatura()) < 1 ? 1 : attaccoNemico-this.getArmatura();
    }

    protected void setSalute(int salute){  //per la cura
        this.salute += salute;
        if(this.salute>this.maxSalute)
            this.salute = this.maxSalute;
    }

    protected void setA(boolean b){
        this.a = b;
    }
    protected void setLvlGiocatore() {
        this.lvlGiocatore += 1;
    }

    protected void setEsperienza(int esperienza) {
        this.esperienza += esperienza;
        if(this.esperienza >= this.maxESperienza){
            this.setLvlGiocatore();
            this.setAttFisico();
            this.setAttMagico();
            this.setArmatura();
            this.setFortuna();
            this.maxSalute += 30;
            this.salute = this.maxSalute;
            this.esperienza = this.esperienza-this.maxESperienza;
            this.maxESperienza += 10;
        }
    }

    protected void setSoldi(int soldi) {
        this.soldi += soldi;
    }

    protected void setLvlGioco(int lvlGioco) {
        this.lvlGioco = lvlGioco;
    }

    protected void setProgressilvl(int progressilvl) {
        this.progressilvl = progressilvl;
    }

    protected void setScelteImp(boolean scelta) {
        this.scelteImp[this.i] = scelta;
        this.i++;
    }

    protected void setEquip(Oggetti o) {
        switch(o.getId()){
            case 1://Armi
                this.equip[0]=this.inventario.indexOf(o);
                break;
            case 2://Armatura
                this.equip[1]=this.inventario.indexOf(o);
                break;
            default:
                break;
        }
    }

    protected void setVendiEquip(int i)
    {
        this.equip[i] = -1;
    }
    
    protected void setInventario(Oggetti inventario) {
        if(inventario.getId() == 3){
           Consumabili c = (Consumabili) inventario;
           this.inventario.add(c);
        }else{
            inventario.setCosto((int) (inventario.getCosto()-inventario.getCosto()*0.25));
            this.inventario.add(inventario);
       }
    }
    
    protected void setInvTutorial(Oggetti o)
    {
        this.inventario.add(o);
    }
    
    protected void eliminaInventario(int i){
        Armi a;
        Armatura arm;
        
        if(this.getEquip()[0] != -1)
            a = (Armi) this.getInventario().get(this.getEquip()[0]);
        else a = null;
        if(this.getEquip()[1] != -1)
            arm = (Armatura) this.getInventario().get(this.getEquip()[1]);
        else arm = null;
        this.getInventario().remove(i);
        if(a != null) this.setEquip(a);
        if(arm != null) this.setEquip(arm);
    }
}