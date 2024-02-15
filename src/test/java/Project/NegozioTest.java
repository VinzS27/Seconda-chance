package Project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NegozioTest {
    
    public NegozioTest() {
    }
    
    @Test
    public void testNegozio(){
        Negozio n = new Negozio();
        
        assertFalse(n.listArmature.isEmpty());
        assertEquals(9, n.listArmature.size());
        
        assertFalse(n.listArmi.isEmpty());
        assertEquals(9, n.listArmi.size());
        
        assertFalse(n.listConsumabili.isEmpty());
        assertEquals(4, n.listConsumabili.size());
    }

    @Test
    public void testAcquista() {
        Negozio n = new Negozio();
        
        Oggetti o1 = new Armi(8, 15, 30, 1, "Skipper", "Spada di base", "C", 1);
        n.acquista(o1);
        assertEquals(8, n.listArmi.size());
        
        Oggetti o2 = new Armatura(4, 36, 60, "Uliveto", "Tunica avanzato", "B",2);
        n.acquista(o2);
        assertEquals(8, n.listArmature.size());
        
        Oggetti o3 = new Consumabili("Magia +5", "Pozione di magia", "C", 5, 50, 1,3,3);
        n.acquista(o3);
        assertEquals(4, n.listConsumabili.size());
        assertEquals(2, n.listConsumabili.get(1).quantità);
    }

    @Test
    public void testVendi() {
    }
    
}