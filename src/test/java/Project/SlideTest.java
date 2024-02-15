package Project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SlideTest {
    
    public SlideTest() {
    }
    
    @Test
    public void testSlide(){
        Slide slide1 = new Slide(0, "Dialogo 1", "Immagine1.png", "");
        assertEquals(0, slide1.getTipo());
        assertEquals("Dialogo 1", slide1.getTesto());
        assertEquals("src\\main\\java\\Project\\Immagini\\Immagine1.png", slide1.getImg());
        
        //Con enum
        Slide slide2 = new Slide(Slide.TIPO.NARRAZIONE, "Dialogo 2", "Immagine2.png", "");
        assertEquals(0, slide2.getTipo());
        assertEquals("Dialogo 2", slide2.getTesto());
        assertEquals("src\\main\\java\\Project\\Immagini\\Immagine2.png", slide2.getImg());
    }
}