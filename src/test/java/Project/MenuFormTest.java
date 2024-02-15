package Project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuFormTest {
    
    public MenuFormTest() {
    }
    
    @Test
    public void testMenuForm(){
        MenuForm menu = new MenuForm();
        assertNotNull(menu.getF());
        assertNotNull(menu.getFNegozio());
        assertNotNull(menu.getFMusica());
        assertEquals("Menu.wav", menu.getPathFMusica());
        assertTrue(menu.clipMusicaIsOpen());
        
    }

    @Test
    public void testNuovaPartitaBTActionPerformed() {  //verifica se viene correttamente cambiata la schermata, che la musica giusta sia in esecuzione
        MenuForm menu = new MenuForm();
        assertEquals(1, menu.nuovaPartita());
        assertNotNull(menu.getFMusica());
        assertEquals("Intro.wav", menu.getPathFMusica());
        assertTrue(menu.clipMusicaIsOpen());
    }
    
    @Test
    public void testIntroAvantBTActionPerformed() {  //verifica se viene correttamente cambiata la schermata
        MenuForm menu = new MenuForm();
        assertEquals(2, menu.avanti());
    }
    
    @Test
    public void testContinuaBTActionPerformed(){  //verifica che gli oggetti personaggio, negozio e salvataggio non siano null, che il salvataggio sia attivo
        MenuForm menu = new MenuForm();
        menu.continua();
        assertNotNull(menu.getP());
        assertNotNull(menu.getNegozio());
        assertNotNull(menu.getS());
        assertTrue(menu.sIsStarted());
        assertTrue(menu.clipMusicaIsClose());
    }
    @Test
    public void testGuerrieroBTActionPerformed(){  //verifica che gli oggetti personaggio, negozio e salvataggio non siano null, che il salvataggio sia attivo
        MenuForm menu = new MenuForm();
        menu.guerriero();
        assertNotNull(menu.getP());
        assertNotNull(menu.getNegozio());
        assertNotNull(menu.getS());
        assertTrue(menu.sIsStarted());
        assertTrue(menu.clipMusicaIsClose());
    }
    @Test
    public void testMagoBTActionPerformed(){  //verifica che gli oggetti personaggio, negozio e salvataggio non siano null, che il salvataggio sia attivo
        MenuForm menu = new MenuForm();
        menu.mago();
        assertNotNull(menu.getP());
        assertNotNull(menu.getNegozio());
        assertNotNull(menu.getS());
        assertTrue(menu.sIsStarted());
        assertTrue(menu.clipMusicaIsClose());
    }
    @Test
    public void testStrategaBTActionPerformed(){  //verifica che gli oggetti personaggio, negozio e salvataggio non siano null, che il salvataggio sia attivo
        MenuForm menu = new MenuForm();
        menu.stratega();
        assertNotNull(menu.getP());
        assertNotNull(menu.getNegozio());
        assertNotNull(menu.getS());
        assertTrue(menu.sIsStarted());
        assertTrue(menu.clipMusicaIsClose());
    }
}