package Project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NemicoTest {
    
    private Nemico nemico;
    public NemicoTest() {
    }
    
    @Test
    public void testNemicoFantasma(){
        boolean cond;
        int lvlGiocatore = 5;
        String nomeNemico = "Fantasma";
        String tipoNemico = "Magico";
        this.nemico = new Nemico(nomeNemico, lvlGiocatore);
        assertNotNull(this.nemico);
        assertEquals(nomeNemico, this.nemico.getNome());
        assertEquals(tipoNemico, this.nemico.getTipo());
        if(this.nemico.getLvl()<=(lvlGiocatore+2) && this.nemico.getLvl()>=lvlGiocatore){
            cond = true;
            assertTrue(cond);
        }
        else {
            cond = false;
            assertFalse(cond);
        }
        assertTrue(this.nemico.getFraseComb().contains(this.nemico.getNome()));
        assertEquals(30+(15*(this.nemico.getLvl()-1)), this.nemico.getSalute());
        assertEquals(24+(12*(this.nemico.getLvl()-1)), this.nemico.getAttacco());
        assertEquals(6+(3*(this.nemico.getLvl()-1)), this.nemico.getArmatura());
        assertTrue(this.nemico.getEsperienza()>=25);
    }
    
    @Test
    public void testNemicoGrifone(){
        boolean cond;
        int lvlGiocatore = 5;
        String nomeNemico = "Grifone";
        String tipoNemico = "Magico";
        this.nemico = new Nemico(nomeNemico, lvlGiocatore);
        assertNotNull(this.nemico);
        assertEquals(nomeNemico, this.nemico.getNome());
        assertEquals(tipoNemico, this.nemico.getTipo());
        if(this.nemico.getLvl()<=(lvlGiocatore+2) && this.nemico.getLvl()>=lvlGiocatore){
            cond = true;
            assertTrue(cond);
        }
        else {
            cond = false;
            assertFalse(cond);
        }
        assertTrue(this.nemico.getFraseComb().contains(this.nemico.getNome()));
        assertEquals(32+(16*(this.nemico.getLvl()-1)), this.nemico.getSalute());
        assertEquals(25+(12*(this.nemico.getLvl()-1)), this.nemico.getAttacco());
        assertEquals(5+(2*(this.nemico.getLvl()-1)), this.nemico.getArmatura());
        assertTrue(this.nemico.getEsperienza()>=25);
    }
    
    @Test
    public void testNemicoArpia(){
        boolean cond;
        int lvlGiocatore = 5;
        String nomeNemico = "Arpia";
        String tipoNemico = "Magico";
        this.nemico = new Nemico(nomeNemico, lvlGiocatore);
        assertNotNull(this.nemico);
        assertEquals(nomeNemico, this.nemico.getNome());
        assertEquals(tipoNemico, this.nemico.getTipo());
        if(this.nemico.getLvl()<=(lvlGiocatore+2) && this.nemico.getLvl()>=lvlGiocatore){
            cond = true;
            assertTrue(cond);
        }
        else {
            cond = false;
            assertFalse(cond);
        }
        assertTrue(this.nemico.getFraseComb().contains(this.nemico.getNome()));
        assertEquals(28+(14*(this.nemico.getLvl()-1)), this.nemico.getSalute());
        assertEquals(26+(13*(this.nemico.getLvl()-1)), this.nemico.getAttacco());
        assertEquals(4+(2*(this.nemico.getLvl()-1)), this.nemico.getArmatura());
        assertTrue(this.nemico.getEsperienza()>=25);
    }
    
    @Test
    public void testNemicoScheletro(){
        boolean cond;
        int lvlGiocatore = 5;
        String nomeNemico = "Scheletro";
        String tipoNemico = "Fisico";
        this.nemico = new Nemico(nomeNemico, lvlGiocatore);
        assertNotNull(this.nemico);
        assertEquals(nomeNemico, this.nemico.getNome());
        assertEquals(tipoNemico, this.nemico.getTipo());
        if(this.nemico.getLvl()<=(lvlGiocatore+2) && this.nemico.getLvl()>=lvlGiocatore){
            cond = true;
            assertTrue(cond);
        }
        else {
            cond = false;
            assertFalse(cond);
        }
        assertTrue(this.nemico.getFraseComb().contains(this.nemico.getNome()));
        assertEquals(30+(15*(this.nemico.getLvl()-1)), this.nemico.getSalute());
        assertEquals(24+(12*(this.nemico.getLvl()-1)), this.nemico.getAttacco());
        assertEquals(6+(3*(this.nemico.getLvl()-1)), this.nemico.getArmatura());
        assertTrue(this.nemico.getEsperienza()>=25);
    }
    
    @Test
    public void testNemicoLicantropo(){
        boolean cond;
        int lvlGiocatore = 5;
        String nomeNemico = "Licantropo";
        String tipoNemico = "Fisico";
        this.nemico = new Nemico(nomeNemico, lvlGiocatore);
        assertNotNull(this.nemico);
        assertEquals(nomeNemico, this.nemico.getNome());
        assertEquals(tipoNemico, this.nemico.getTipo());
        if(this.nemico.getLvl()<=(lvlGiocatore+2) && this.nemico.getLvl()>=lvlGiocatore){
            cond = true;
            assertTrue(cond);
        }
        else {
            cond = false;
            assertFalse(cond);
        }
        assertTrue(this.nemico.getFraseComb().contains(this.nemico.getNome()));
        assertEquals(32+(16*(this.nemico.getLvl()-1)), this.nemico.getSalute());
        assertEquals(25+(12*(this.nemico.getLvl()-1)), this.nemico.getAttacco());
        assertEquals(4+(2*(this.nemico.getLvl()-1)), this.nemico.getArmatura());
        assertTrue(this.nemico.getEsperienza()>=25);
    }
    
    @Test
    public void testNemicoDemoni(){
        boolean cond;
        int lvlGiocatore = 5;
        String nomeNemico = "Demoni";
        String tipoNemico = "Fisico";
        this.nemico = new Nemico(nomeNemico, lvlGiocatore);
        assertNotNull(this.nemico);
        assertEquals(nomeNemico, this.nemico.getNome());
        assertEquals(tipoNemico, this.nemico.getTipo());
        if(this.nemico.getLvl()<=(lvlGiocatore+2) && this.nemico.getLvl()>=lvlGiocatore){
            cond = true;
            assertTrue(cond);
        }
        else {
            cond = false;
            assertFalse(cond);
        }
        assertTrue(this.nemico.getFraseComb().contains(this.nemico.getNome()));
        assertEquals(28+(14*(this.nemico.getLvl()-1)), this.nemico.getSalute());
        assertEquals(26+(13*(this.nemico.getLvl()-1)), this.nemico.getAttacco());
        assertEquals(5+(2*(this.nemico.getLvl()-1)), this.nemico.getArmatura());
        assertTrue(this.nemico.getEsperienza()>=25);
    }
    
    @Test
    public void testNemicoGoblin(){
        boolean cond;
        int lvlGiocatore = 5;
        String nomeNemico = "Goblin";
        String tipoNemico = "Fisico";
        this.nemico = new Nemico(nomeNemico, lvlGiocatore);
        assertNotNull(this.nemico);
        assertEquals(nomeNemico, this.nemico.getNome());
        assertEquals(tipoNemico, this.nemico.getTipo());
        if(this.nemico.getLvl()<=(lvlGiocatore+2) && this.nemico.getLvl()>=lvlGiocatore){
            cond = true;
            assertTrue(cond);
        }
        else {
            cond = false;
            assertFalse(cond);
        }
        assertTrue(this.nemico.getFraseComb().contains(this.nemico.getNome()));
        assertEquals(20+(10*(this.nemico.getLvl()-1)), this.nemico.getSalute());
        assertEquals(20+(11*(this.nemico.getLvl()-1)), this.nemico.getAttacco());
        assertEquals(2+(1*(this.nemico.getLvl()-1)), this.nemico.getArmatura());
        assertTrue(this.nemico.getEsperienza()>=20);
    }
    
    @Test
    public void testNemicoDrago(){
        boolean cond;
        int lvlGiocatore = 5;
        String nomeNemico = "Drago";
        String tipoNemico = "Magico";
        this.nemico = new Nemico(nomeNemico, lvlGiocatore);
        assertNotNull(this.nemico);
        assertEquals(nomeNemico, this.nemico.getNome());
        assertEquals(tipoNemico, this.nemico.getTipo());
        if(this.nemico.getLvl()<=(lvlGiocatore+2) && this.nemico.getLvl()>=lvlGiocatore){
            cond = true;
            assertTrue(cond);
        }
        else {
            cond = false;
            assertFalse(cond);
        }
        assertTrue(this.nemico.getFraseComb().contains(this.nemico.getNome()));
        assertEquals(100+(50*(this.nemico.getLvl()-1)), this.nemico.getSalute());
        assertEquals(42+(21*(this.nemico.getLvl()-1)), this.nemico.getAttacco());
        assertEquals(14+(7*(this.nemico.getLvl()-1)), this.nemico.getArmatura());
        assertTrue(this.nemico.getEsperienza()>=25);
    }
    
    @Test
    public void testNemicoGigante(){
        boolean cond;
        int lvlGiocatore = 5;
        String nomeNemico = "Gigante";
        String tipoNemico = "Fisico";
        this.nemico = new Nemico(nomeNemico, lvlGiocatore);
        assertNotNull(this.nemico);
        assertEquals(nomeNemico, this.nemico.getNome());
        assertEquals(tipoNemico, this.nemico.getTipo());
        if(this.nemico.getLvl()<=(lvlGiocatore+2) && this.nemico.getLvl()>=lvlGiocatore){
            cond = true;
            assertTrue(cond);
        }
        else {
            cond = false;
            assertFalse(cond);
        }
        assertTrue(this.nemico.getFraseComb().contains(this.nemico.getNome()));
        assertEquals(60+(30*(this.nemico.getLvl()-1)), this.nemico.getSalute());
        assertEquals(37+(18*(this.nemico.getLvl()-1)), this.nemico.getAttacco());
        assertEquals(8+(4*(this.nemico.getLvl()-1)), this.nemico.getArmatura());
        assertTrue(this.nemico.getEsperienza()>=25);
    }
    
    public void testNemico(String nomeNemico){
        switch(nomeNemico){
            case "Fantasma" :
                testNemicoFantasma();
                break;
            case "Grifone" :
                testNemicoGrifone();
                break;
            case "Arpia" :
                testNemicoArpia();
                break;
            case "Scheletro" :
                testNemicoScheletro();
                break;
            case "Licantropo" :
                testNemicoLicantropo();
                break;
            case "Demoni" :
                testNemicoDemoni();
                break;
            case "Goblin" :
                testNemicoGoblin();
                break;
            case "Drago" :
                testNemicoDrago();
                break;
            default :
                break;
        }
    }
    
    @Test
    public void testSetSalute() {
        testNemico("Fantasma");
        
        int attacco = 20;
        int saluteNemico = this.nemico.getSalute();
        this.nemico.setSalute(attacco);
        assertEquals(saluteNemico-((attacco-this.nemico.getArmatura()) < 1 ? 1 : (attacco-this.nemico.getArmatura())), this.nemico.getSalute());
        
        saluteNemico = this.nemico.getSalute();
        this.nemico.setSalute(attacco);
        assertEquals(saluteNemico-((attacco-this.nemico.getArmatura()) < 1 ? 1 : (attacco-this.nemico.getArmatura())), this.nemico.getSalute());
    }
}