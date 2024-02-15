package Project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonaggioTest {
    
    public PersonaggioTest() {
    }

    @Test
    public void testGuerriero(){
        Personaggio p = new Personaggio("Nome", "Guerriero");
        assertNotNull(p);
        assertEquals("Nome", p.getNome());
        assertEquals("Guerriero", p.getClasse());
        assertEquals(210, p.getSalute());
        assertEquals(210, p.getMaxSalute());
        assertEquals(15, p.getAttFisico());
        assertEquals(8, p.getAttMagico());
        assertEquals(22, p.getArmatura());
        assertEquals(10, p.getFortuna());
        assertEquals(1, p.abilità);
        
        assertEquals(0, p.getEsperienza());
        assertEquals(0, p.getSoldi());
        assertEquals(1, p.getLvlGiocatore());
        assertEquals(0, p.getLvlGioco());
        assertEquals(0, p.getProgressilvl());
        assertTrue(p.getInventario().isEmpty());
        assertEquals(2, p.equip.length);
    }
    @Test
    public void testMago(){
        Personaggio p = new Personaggio("Nome", "Mago");
        assertNotNull(p);
        assertEquals("Nome", p.getNome());
        assertEquals("Mago", p.getClasse());
        assertEquals(200, p.getSalute());
        assertEquals(200, p.getMaxSalute());
        assertEquals(10, p.getAttFisico());
        assertEquals(18, p.getAttMagico());
        assertEquals(18, p.getArmatura());
        assertEquals(10, p.getFortuna());
        assertEquals(2, p.abilità);
        
        assertEquals(0, p.getEsperienza());
        assertEquals(0, p.getSoldi());
        assertEquals(1, p.getLvlGiocatore());
        assertEquals(0, p.getLvlGioco());
        assertEquals(0, p.getProgressilvl());
        assertTrue(p.getInventario().isEmpty());
        assertEquals(2, p.equip.length);
    }
    @Test
    public void testStratega(){
        Personaggio p = new Personaggio("Nome", "Stratega");
        assertNotNull(p);
        assertEquals("Nome", p.getNome());
        assertEquals("Stratega", p.getClasse());
        assertEquals(170, p.getSalute());
        assertEquals(170, p.getMaxSalute());
        assertEquals(9, p.getAttFisico());
        assertEquals(9, p.getAttMagico());
        assertEquals(20, p.getArmatura());
        assertEquals(20, p.getFortuna());
        assertEquals(3, p.abilità);
        
        assertEquals(0, p.getEsperienza());
        assertEquals(0, p.getSoldi());
        assertEquals(1, p.getLvlGiocatore());
        assertEquals(0, p.getLvlGioco());
        assertEquals(0, p.getProgressilvl());
        assertTrue(p.getInventario().isEmpty());
        assertEquals(2, p.equip.length);
    }
    
    
    @Test
    public void testSetFortuna() {
        Personaggio p = new Personaggio("Nome", "Guerriero");
        p.setFortuna();
        assertEquals(12, p.getFortuna());
    }

    @Test
    public void testDiminuisciSalute() {
        Personaggio p = new Personaggio("Nome", "Guerriero");
        
        p.diminuisciSalute(10);
        assertEquals(209, p.getSalute());
        
        p.diminuisciSalute(30);
        assertEquals(201, p.getSalute());
    }

    @Test
    public void testSetSalute() {
        Personaggio p = new Personaggio("Nome", "Guerriero");
        
        p.diminuisciSalute(10);
        p.setSalute(2);
        assertEquals(200, p.getSalute());
        
        p.setSalute(30);
        assertEquals(210, p.getSalute());
    }

    @Test
    public void testSetLvlGiocatore() {
        Personaggio p = new Personaggio("Nome", "Guerriero");
        
        p.setLvlGiocatore();
        assertEquals(2, p.getLvlGiocatore());
    }

    @Test
    public void testSetEsperienza() {
        Personaggio p = new Personaggio("Nome", "Guerriero");
        
        p.setEsperienza(50);
        assertEquals(50, p.getEsperienza());
        
        p.setEsperienza(51);
        assertEquals(2, p.getLvlGiocatore());
        assertEquals(1, p.getEsperienza());
        assertEquals(240, p.getMaxSalute());
        assertEquals(240, p.getSalute());
        assertEquals(22, p.getAttFisico());
        assertEquals(13, p.getAttMagico());
        assertEquals(33, p.getArmatura());
        assertEquals(12, p.getFortuna());
    }

    @Test
    public void testSetSoldi() {
        Personaggio p = new Personaggio("Nome", "Guerriero");
        
        p.setSoldi(50);
        assertEquals(50, p.getSoldi());
    }
}