package Project;

import java.util.Random;

public class Nemico {
    
    protected String nome;
    protected String tipo;
    protected int attacco;
    protected int armatura;
    protected int salute;
    protected int lvl;
    protected int esperienza;
    protected String fraseComb;

    public Nemico(String nome, int lvl) {
        Random rand = new Random();
        this.lvl = lvl + rand.nextInt(2);
        this.nome = nome;
        int exp = 30;
        switch(nome){
            case "Fantasma" :
                this.tipo = "Magico";
                this.salute = 30;
                this.attacco = 24;
                this.armatura = 6;
                this.fraseComb = " E' apparso un Fantasma";
                break;
            
            case "Grifone" :
                this.tipo = "Magico";
                this.salute = 32;
                this.attacco = 25;
                this.armatura = 5;
                this.fraseComb = " E' apparso un Grifone";
                break;
            
            case "Arpia" :
                this.tipo = "Magico";
                this.salute = 28;
                this.attacco = 26;
                this.armatura = 4;
                this.fraseComb = " E' apparsa un'Arpia";
                break;
            
            case "Scheletro" :
                this.tipo = "Fisico";
                this.salute = 30;
                this.attacco = 24;
                this.armatura = 6;
                this.fraseComb = " E' apparso uno Scheletro";
                break;
            
            case "Licantropo" :
                this.tipo = "Fisico";
                this.salute = 32;
                this.attacco = 25;
                this.armatura = 4;
                this.fraseComb = " E' apparso un Licantropo";
                break;
            
            case "Demoni" :
                this.tipo = "Fisico";
                this.salute = 28;
                this.attacco = 26;
                this.armatura = 5;
                this.fraseComb = " Sono apparsi dei Demoni";
                break;
            
            case "Goblin" :
                this.tipo = "Fisico";
                this.salute = 20;
                this.attacco = 20;
                this.armatura = 2;
                this.fraseComb = " Dentro il baule si nascondeva un Goblin";
                this.lvl = lvl;
                exp = 20;
                break;
                
            case "Gigante" :
                this.tipo = "Fisico";
                this.salute = 60;
                this.attacco = 37;
                this.armatura = 8;
                this.fraseComb = " Un Gigante minaccioso si erge di fronte a noi.";
                this.lvl = lvl;
                exp = 35;
                break;
            
            case "Drago" :
                this.tipo = "Magico";
                this.salute = 100;
                this.attacco = 42;
                this.armatura = 14;
                this.fraseComb = " Un possente Drago aleggia sopra le nostre teste...";
                this.lvl = lvl;
                exp = 40;
                break;
                
            default :
                break;
        }
        this.esperienza = exp + (this.lvl - lvl);
        this.salute += (this.salute/2)*(this.lvl-1);
        this.attacco += (this.attacco/2)*(this.lvl-1);
        this.armatura += (this.armatura/2)*(this.lvl-1);
    }

    public String getNome() {
        return this.nome;
    }

    public String getTipo() {
        return this.tipo;
    }

    public int getAttacco() {
        return this.attacco;
    }

    public int getArmatura() {
        return this.armatura;
    }

    public int getSalute() {
        return this.salute;
    }

    public int getLvl() {
        return this.lvl;
    }
    
    public String getFraseComb(){
        return this.fraseComb;
    }
    
    public int getEsperienza(){
        return this.esperienza;
    }
    
    public void setSalute(int attaccoPersonaggio){
        this.salute -= (attaccoPersonaggio-this.armatura) < 1 ? 1 : attaccoPersonaggio-this.armatura;
    }
}