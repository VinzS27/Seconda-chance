package Project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SchermataPrincipaleTest {
    
    private SchermataPrincipale schermata;
    
    public SchermataPrincipaleTest() {
    }
    
    @Test
    public void testSchermataPrincipale(){  //verifica che gli oggetti personaggio, negozio, salvataggio e file della musica non siano null, e che le giuste musiche siano in esecuzione
        Personaggio p = new Personaggio("Nome", "Classe");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        assertNotNull(schermata.getP());
        assertNotNull(schermata.getN());
        assertNotNull(schermata.getS());
        //assertFalse(schermata.s.getVerifica());  //salvataggio attivato
        assertNotNull(schermata.getFMusica());
        assertTrue(schermata.clipMusicaIsRunning());
        assertEquals("Main.wav", schermata.getPathFMusica());
        
        assertNotNull(schermata.scenario);
        assertEquals(schermata.p.getLvlGioco(), schermata.k);
        assertEquals(schermata.p.getProgressilvl(), schermata.y);
    }
    
    @Test
    public void testEquipaggia(){
        Personaggio p = new Personaggio("Nome", "Guerriero");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        schermata.p.setSoldi(500);
        schermata.negozio("NEGOZIO");
        schermata.negozioChoice("Siciliana");
        schermata.negozioChoice("Skipper");
        schermata.negozioChoice("Salute");
        schermata.negozio("ESCI");
        
        //Uso della pozione con il massimo della salute
        schermata.EquipaggiaChoice("Salute1");
        assertEquals(210, schermata.p.getSalute());
        assertEquals(3, schermata.p.getInventario().size());
        //assertTrue(schermata.GameConsoleGetTesto().contains("massimo"));
        
        //Usa della pozione con salute meno del massimo
        schermata.p.setSalute(-30);
        schermata.EquipaggiaChoice("Salute1");
        assertEquals(180, schermata.p.getSalute());
        assertEquals(3, schermata.p.getInventario().size());
        //assertTrue(schermata.GameConsoleGetTesto().contains("curato"));
        
        //Equipaggiamento Armatura
        schermata.EquipaggiaChoice("Siciliana");
        assertEquals(22, schermata.p.getArmatura());
        assertEquals(3, schermata.p.getInventario().size());
        
        //Equipaggiamento Arma
        schermata.EquipaggiaChoice("Skipper");
        assertEquals(15, schermata.p.getAttFisico());
        assertEquals(9, schermata.p.getAttMagico());
        assertTrue(!schermata.p.getInventario().isEmpty());
    }

    @Test
    public void testNegozioEntra() {  //verifica che gli oggetti file della musica e file dei suoni non siano null, e che le giuste musiche siano in esecuzione
        Personaggio p = new Personaggio("Nome", "Guerriero");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        schermata.negozio("NEGOZIO");
        assertTrue(schermata.s.getVerifica());  //salvataggio disattivato
        assertNotNull(schermata.getFMusica());
        assertNotNull(schermata.getFSuoni());
        assertTrue(schermata.clipMusicaIsRunning());
        assertTrue(schermata.clipSuoniIsOpen());
        assertEquals("Negozio.wav", schermata.getPathFMusica());
        assertEquals("Porta Negozio.wav", schermata.getPathFSuoni());
        assertEquals("ESCI", schermata.getNegozioText());
        assertTrue(schermata.vendiIsEnable());
        assertTrue(schermata.compraIsEnable());
        assertTrue(schermata.shopListIsEnable());
        assertFalse(schermata.dmNegozio.isEmpty());
    }
    @Test
    public void testNegozioEntraConSalvataggioDisabilitato() {  //verifica che gli oggetti file della musica e file dei suoni non siano null, e che le giuste musiche siano in esecuzione
        Personaggio p = new Personaggio("Nome", "Guerriero");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        schermata.s.disattivaSalvataggio();
        schermata.negozio("NEGOZIO");
        assertTrue(schermata.s.getVerifica());  //salvataggio disattivato
        assertNotNull(schermata.getFMusica());
        assertNotNull(schermata.getFSuoni());
        assertTrue(schermata.clipMusicaIsRunning());
        assertTrue(schermata.clipSuoniIsOpen());
        assertEquals("Negozio.wav", schermata.getPathFMusica());
        assertEquals("Porta Negozio.wav", schermata.getPathFSuoni());
        assertEquals("ESCI", schermata.getNegozioText());
        assertTrue(schermata.vendiIsEnable());
        assertTrue(schermata.compraIsEnable());
        assertTrue(schermata.shopListIsEnable());
        assertFalse(schermata.dmNegozio.isEmpty());
    }
    @Test
    public void testNegozioEsci() {  //verifica che gli oggetti file della musica e file dei suoni non siano null, e che le giuste musiche siano in esecuzione
        Personaggio p = new Personaggio("Nome", "Guerriero");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        //schermata.s.disattivaSalvataggio();
        //schermata.negozio("NEGOZIO");
        schermata.negozio("ESCI");
        assertFalse(schermata.s.getVerifica());  //salvataggio attivato
        assertNotNull(schermata.getFMusica());
        assertNotNull(schermata.getFSuoni());
        assertTrue(schermata.clipMusicaIsRunning());
        assertTrue(schermata.clipSuoniIsOpen());
        assertEquals("Intro.wav", schermata.getPathFMusica());
        assertEquals("Porta Negozio.wav", schermata.getPathFSuoni());
        assertEquals("NEGOZIO", schermata.getNegozioText());
        assertFalse(schermata.vendiIsEnable());
        assertFalse(schermata.compraIsEnable());
        assertFalse(schermata.shopListIsEnable());
        assertTrue(schermata.dmNegozio.isEmpty());
    }
    @Test
    public void testNegozioEsciConSalvataggioDisabilitato() {  //verifica che gli oggetti file della musica e file dei suoni non siano null, e che le giuste musiche siano in esecuzione
        Personaggio p = new Personaggio("Nome", "Guerriero");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        schermata.s.disattivaSalvataggio();
        schermata.negozio("NEGOZIO");
        schermata.negozio("ESCI");
        //assertTrue(schermata.s.getVerifica());  //salvataggio disabilitato
        assertNotNull(schermata.getFMusica());
        assertNotNull(schermata.getFSuoni());
        assertTrue(schermata.clipMusicaIsRunning());
        assertTrue(schermata.clipSuoniIsOpen());
        assertEquals("Intro.wav", schermata.getPathFMusica());
        assertEquals("Porta Negozio.wav", schermata.getPathFSuoni());
        assertEquals("NEGOZIO", schermata.getNegozioText());
        assertFalse(schermata.vendiIsEnable());
        assertFalse(schermata.compraIsEnable());
        assertFalse(schermata.shopListIsEnable());
        assertTrue(schermata.dmNegozio.isEmpty());
    }
    @Test
    public void testCompra(){  //verifica che l'oggetto acquistato sia presente nell'inventario (con prezzo diminuito del 25%) 
        //e che non sia più presente nel negozio (nel caso dei consumabili che la quantità sia sia diminuita di 1)
        testNegozioEntra();
        schermata.p.setSoldi(500);
        
        //Acquisto Armatura
        int soldi = schermata.p.getSoldi();
        assertEquals("Siciliana", schermata.getShopList(1).getNome());
        schermata.negozioChoice("Siciliana");
        assertFalse(schermata.p.getInventario().isEmpty());
        assertEquals("Siciliana", schermata.p.getInventario().get(0).getNome());
        assertEquals(30, schermata.p.getInventario().get(0).getCosto());
        assertNotEquals("Siciliana", schermata.getShopList(1).getNome());
        assertEquals(soldi - 40, schermata.p.getSoldi());
        assertEquals("Siciliana Comprato per 40 Oro.", schermata.GameConsoleGetTesto());
        
        //Acquisto Arma
        soldi = schermata.p.getSoldi();
        assertEquals("Skipper", schermata.getShopList(4).getNome());
        schermata.negozioChoice("Skipper");
        assertFalse(schermata.p.getInventario().isEmpty());
        assertEquals("Skipper", schermata.p.getInventario().get(1).getNome());
        assertEquals(30, schermata.p.getInventario().get(1).getCosto());
        assertNotEquals("Skipper", schermata.getShopList(4).getNome());
        assertEquals(soldi - 40, schermata.p.getSoldi());
        assertEquals("Skipper Comprato per 40 Oro.", schermata.GameConsoleGetTesto());
        
        //Acquisto Consumabile
        soldi = schermata.p.getSoldi();
        assertEquals(9, schermata.getShopListConsumabili(7).getQuantità());
        schermata.negozioChoice("Salute");
        assertFalse(schermata.p.getInventario().isEmpty());
        assertTrue(schermata.p.getInventario().get(2).getNome().contains("Salute"));
        assertEquals(20, schermata.p.getInventario().get(2).getCosto());
        assertEquals(8, schermata.getShopListConsumabili(7).getQuantità());
        assertEquals(soldi - 20, schermata.p.getSoldi());
        assertTrue(schermata.GameConsoleGetTesto().contains("Comprato") && schermata.GameConsoleGetTesto().contains("Salute"));
        
        //soldi insufficienti
    }
    @Test
    public void testVendi(){  //verifica che l'oggetto venduto sia presente nel negozio //(con prezzo diminuito del 25%) 
        //e che non sia più presente nell'inventario (nel caso dei consumabili che la quantità sia sia aumentata di 1)
        testCompra();
        
        //Vendita Armatura
        int soldi = schermata.p.getSoldi();
        schermata.inventarioChoice("SicilianaV");
        assertNotEquals("Siciliana", schermata.p.getInventario().get(0).getNome());
        assertFalse(schermata.p.getInventario().isEmpty());
        assertEquals(soldi + 30, schermata.p.getSoldi());  //deve essere corretto il valore di vendita
        assertEquals("Siciliana", schermata.getShopList(3).getNome());
        assertTrue(schermata.GameConsoleGetTesto().contains("Venduto") && schermata.GameConsoleGetTesto().contains("Siciliana"));
        
        //Vendita Arma
        soldi = schermata.p.getSoldi();
        schermata.inventarioChoice("SkipperV");
        assertNotEquals("Skipper", schermata.p.getInventario().get(0).getNome());
        assertFalse(schermata.p.getInventario().isEmpty());
        assertEquals(soldi + 30, schermata.p.getSoldi());  //deve essere corretto il valore di vendita
        assertEquals("Skipper", schermata.getShopList(7).getNome());
        assertTrue(schermata.GameConsoleGetTesto().contains("Venduto") && schermata.GameConsoleGetTesto().contains("Skipper"));
        
        //Vendita Salute
        soldi = schermata.p.getSoldi();
        schermata.inventarioChoice("SaluteV");
        assertTrue(schermata.p.getInventario().isEmpty());
        //assertNotEquals("Salute", schermata.p.getInventario().get(0).getNome());
        assertEquals(soldi + 20, schermata.p.getSoldi());  //deve essere corretto il valore di vendita
        assertTrue(schermata.getShopList(9).getNome().contains("Salute"));
        assertTrue(schermata.GameConsoleGetTesto().contains("Venduto") && schermata.GameConsoleGetTesto().contains("Salute"));
    }
    
    @Test
    public void testBauleComuneApriSoldi(){
        Personaggio p = new Personaggio("Nome", "Guerriero");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        
        boolean  x;
        int soldi, salute;
        do{
            soldi = schermata.p.getSoldi();
            salute = schermata.p.getSalute();
            schermata.baule();
            assertTrue(schermata.s.getVerifica());  //salvataggio disabilitato
            assertEquals("Baule.wav", schermata.getPathFMusica());
            assertTrue(schermata.GameConsoleGetTesto().contains("Hai incontrato un baule "));
            x = schermata.GameConsoleGetTesto().contains("Hai incontrato un baule Comune!\nScegli cosa fare:");
            if(x==true){
                //Baule Comune
                assertFalse(schermata.negozioIsEnable());
                schermata.bauleChoice("Apri");
                if(schermata.GameConsoleGetTesto().contains("interno")){
                    assertEquals(salute, schermata.p.getSalute());
                    assertTrue(schermata.p.getSoldi()-soldi>=3 && schermata.p.getSoldi()-soldi<=10);
                    assertFalse(schermata.s.getVerifica());  //salvataggio abilitato
                }
                else 
                    x = false;
            }
            
        }while(!x);
    }
    @Test
    public void testBauleComuneApriCombattimento(){
        Personaggio p = new Personaggio("Nome", "Guerriero");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        
        boolean  x;
        int soldi, salute;
        do{
            soldi = schermata.p.getSoldi();
            salute = schermata.p.getSalute();
            schermata.baule();
            assertTrue(schermata.s.getVerifica());  //salvataggio disabilitato
            assertEquals("Baule.wav", schermata.getPathFMusica());
            assertTrue(schermata.GameConsoleGetTesto().contains("Hai incontrato un baule "));
            x = schermata.GameConsoleGetTesto().contains("Hai incontrato un baule Comune!\nScegli cosa fare:");
            if(x==true){
                //Baule Comune
                assertFalse(schermata.negozioIsEnable());
                schermata.bauleChoice("Apri");
                if(schermata.GameConsoleGetTesto().contains("apparso")){
                    assertEquals(salute, schermata.p.getSalute());
                    assertEquals(soldi, schermata.p.getSoldi());
                    assertTrue(schermata.s.getVerifica());  //salvataggio diabilitato
                }
                else 
                    x = false;
            }
            
        }while(!x);
    }
    @Test
    public void testBauleComuneApriTrappola(){
        Personaggio p = new Personaggio("Nome", "Guerriero");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        
        boolean  x;
        int soldi, salute;
        do{
            soldi = schermata.p.getSoldi();
            salute = schermata.p.getSalute();
            schermata.baule();
            assertTrue(schermata.s.getVerifica());  //salvataggio disabilitato
            assertEquals("Baule.wav", schermata.getPathFMusica());
            assertTrue(schermata.GameConsoleGetTesto().contains("Hai incontrato un baule "));
            x = schermata.GameConsoleGetTesto().contains("Hai incontrato un baule Comune!\nScegli cosa fare:");
            if(x==true){
                //Baule Comune
                assertFalse(schermata.negozioIsEnable());
                schermata.bauleChoice("Apri");
                if(schermata.GameConsoleGetTesto().contains("trappola")){
                    assertEquals(salute-20, schermata.p.getSalute());
                    assertEquals(soldi, schermata.p.getSoldi());
                    assertFalse(schermata.s.getVerifica());  //salvataggio abilitato
                }
                else 
                    x = false;
            }
            
        }while(!x);
    }
    @Test
    public void testBauleComuneIgnora(){
        Personaggio p = new Personaggio("Nome", "Guerriero");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        
        boolean  x;
        int soldi, salute;
        do{
            soldi = schermata.p.getSoldi();
            salute = schermata.p.getSalute();
            schermata.baule();
            assertTrue(schermata.s.getVerifica());  //salvataggio disabilitato
            assertEquals("Baule.wav", schermata.getPathFMusica());
            assertTrue(schermata.GameConsoleGetTesto().contains("Hai incontrato un baule "));
            x = schermata.GameConsoleGetTesto().contains("Hai incontrato un baule Comune!\nScegli cosa fare:");
            if(x){
                //Baule Comune
                assertFalse(schermata.negozioIsEnable());
                schermata.bauleChoice("Ignora");
                assertTrue(schermata.GameConsoleGetTesto().contains("ignorato"));
                assertEquals(salute, schermata.p.getSalute());
                assertEquals(soldi, schermata.p.getSoldi());
                assertFalse(schermata.s.getVerifica());  //salvataggio abilitato
            }
        }while(!x);
    }
    

    @Test
    public void testBauleRaroApriConGrimaldelloSoldi(){
        Personaggio p = new Personaggio("Nome", "Guerriero");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        schermata.p.setSoldi(100);
        boolean  x;
        int soldi, salute;
        do{
            soldi = schermata.p.getSoldi();
            salute = schermata.p.getSalute();
            schermata.baule();
            assertTrue(schermata.s.getVerifica());  //salvataggio disabilitato
            assertEquals("Baule.wav", schermata.getPathFMusica());
            assertTrue(schermata.GameConsoleGetTesto().contains("Hai incontrato un baule "));
            x = schermata.GameConsoleGetTesto().contains("Hai incontrato un baule Raro!\nScegli cosa fare:");
            if(x==true){
                //Baule Raro
                assertTrue(schermata.negozioIsEnable());
                schermata.bauleChoice("Scassina");
                if(schermata.GameConsoleGetTesto().contains("oro")){
                    assertEquals(salute, schermata.p.getSalute());
                    assertTrue((schermata.p.getSoldi()-soldi)>=6 && (schermata.p.getSoldi()-soldi)<=20);
                    assertFalse(schermata.s.getVerifica());  //salvataggio abilitato
                }
                else {
                    x = false;
                    //Acquisto Grimaldello
                    schermata.p.setSoldi(30);
                    schermata.negozio("NEGOZIO");
                    schermata.negozioChoice("Grimaldello");
                    schermata.negozio("ESCI");
                }
            }
            
        }while(!x);
    }
    @Test
    public void testBauleRaroApriConGrimaldelloConsumabili(){
        Personaggio p = new Personaggio("Nome", "Guerriero");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        
        boolean  x;
        int soldi, salute;
        do{
            soldi = schermata.p.getSoldi();
            salute = schermata.p.getSalute();
            schermata.baule();
            assertTrue(schermata.s.getVerifica());  //salvataggio disabilitato
            assertEquals("Baule.wav", schermata.getPathFMusica());
            assertTrue(schermata.GameConsoleGetTesto().contains("Hai incontrato un baule "));
            x = schermata.GameConsoleGetTesto().contains("Hai incontrato un baule Raro!\nScegli cosa fare:");
            if(x==true){
                //Baule Raro
                assertTrue(schermata.negozioIsEnable());
                schermata.bauleChoice("Scassina");
                if(schermata.GameConsoleGetTesto().contains("aggiunto")){
                    assertEquals(salute, schermata.p.getSalute());
                    assertEquals(soldi, schermata.p.getSoldi());
                    assertFalse(schermata.p.getInventario().isEmpty());
                    assertTrue(schermata.p.getInventario().get(0).getNome().contains("Salute") || 
                            schermata.p.getInventario().get(0).getNome().contains("Magia") ||
                            schermata.p.getInventario().get(0).getNome().contains("Forza") ||
                            schermata.p.getInventario().get(0).getNome().contains("Grimaldello"));
                    assertFalse(schermata.s.getVerifica());  //salvataggio abilitato
                }
                else {
                    x = false;
                    //Acquisto Grimaldello
                    schermata.p.setSoldi(30);
                    schermata.negozio("NEGOZIO");
                    schermata.negozioChoice("Grimaldello");
                    schermata.negozio("ESCI");
                }
            }
            
        }while(!x);
    }
    @Test
    public void testBauleRaroApriSenzaGrimaldello(){
        Personaggio p = new Personaggio("Nome", "Guerriero");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        
        boolean  x;
        int soldi, salute;
        do{
            soldi = schermata.p.getSoldi();
            salute = schermata.p.getSalute();
            schermata.baule();
            assertTrue(schermata.s.getVerifica());  //salvataggio disabilitato
            assertEquals("Baule.wav", schermata.getPathFMusica());
            assertTrue(schermata.GameConsoleGetTesto().contains("Hai incontrato un baule "));
            x = schermata.GameConsoleGetTesto().contains("Hai incontrato un baule Raro!\nScegli cosa fare:");
            if(x==true){
                //Baule Raro
                assertTrue(schermata.negozioIsEnable());
                schermata.bauleChoice("Scassina");
                assertEquals(salute, schermata.p.getSalute());
                assertEquals(soldi, schermata.p.getSoldi());
                assertTrue(schermata.p.getInventario().isEmpty());
                assertTrue(schermata.GameConsoleGetTesto().contains("negozio"));
                assertTrue(schermata.s.getVerifica());  //salvataggio disabilitato
            }
        }while(!x);
    }
    @Test
    public void testBauleRaroIgnora(){
        Personaggio p = new Personaggio("Nome", "Guerriero");
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        
        boolean  x;
        int soldi, salute;
        do{
            soldi = schermata.p.getSoldi();
            salute = schermata.p.getSalute();
            schermata.baule();
            assertTrue(schermata.s.getVerifica());  //salvataggio disabilitato
            assertEquals("Baule.wav", schermata.getPathFMusica());
            assertTrue(schermata.GameConsoleGetTesto().contains("Hai incontrato un baule "));
            x = schermata.GameConsoleGetTesto().contains("Hai incontrato un baule Raro!\nScegli cosa fare:");
            if(x){
                //Baule Raro
                assertTrue(schermata.negozioIsEnable());
                schermata.bauleChoice("Ignora");
                assertTrue(schermata.GameConsoleGetTesto().contains("ignorato"));
                assertEquals(salute, schermata.p.getSalute());
                assertEquals(soldi, schermata.p.getSoldi());
                assertFalse(schermata.s.getVerifica());  //salvataggio abilitato
            }
        }while(!x);
    }
    
    @Test
    public void testCombattimento(){
        Personaggio p = new Personaggio("Nome", "Guerriero");
        
        p.setLvlGiocatore();
        p.setLvlGiocatore();
        p.setLvlGiocatore();
        p.setLvlGiocatore();
        
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        testStatistichePersonaggio();
        testStatisticheNemico();
        schermata.playerChoice("Combattimento");
        assertNotNull(schermata.nemico);
        
        if(schermata.nemico.getTipo().equals("Magico")){
            assertEquals(8, schermata.tipoAttacco);
        }
        else {
            assertEquals(15, schermata.tipoAttacco);
        }
        //System.out.println(schermata.nemico.getNome() + " | " + schermata.nemico.getTipo());
        assertEquals("Combattimento.wav", schermata.getPathFMusica());
        assertTrue(schermata.s.getVerifica());
        assertFalse(schermata.negozioIsEnable());
        assertFalse(schermata.vendiIsEnable());
        assertFalse(schermata.compraIsEnable());
        assertFalse(schermata.shopListIsEnable());
        assertTrue(schermata.dmNegozio.isEmpty());
        
        testStatisticheNemicoComb();
        testStatistichePersonaggioComb();
    }
    @Test
    public void testStatisticheNemico(){
        assertEquals("", schermata.getStatNemico());
    }
    @Test
    public void testStatisticheNemicoComb(){
        String s = schermata.getStatNemico();
        assertTrue(s.contains("Nemico") && s.contains(String.valueOf(schermata.nemico.getNome())) && 
                   s.contains("Livello") && s.contains(String.valueOf(schermata.nemico.getLvl())) && 
                   s.contains("Salute") && s.contains(String.valueOf(schermata.nemico.getSalute()))
        );
    }
    @Test
    public void testStatistichePersonaggio(){
        String s = schermata.getStatistiche();
        assertTrue(s.contains("Nome") && s.contains(String.valueOf(schermata.p.getNome())) && 
                   s.contains("Livello") && s.contains(String.valueOf(schermata.p.getLvlGiocatore())) && 
                   s.contains("Salute") && s.contains(String.valueOf(schermata.p.getSalute())) && 
                   s.contains("AttMagico") && s.contains(String.valueOf(schermata.p.getAttMagico())) &&
                   s.contains("AttFisico") && s.contains(String.valueOf(schermata.p.getAttFisico())) &&
                   s.contains("Armatura") && s.contains(String.valueOf(schermata.p.getArmatura())) && 
                   s.contains("Arma") && 
                   s.contains("Exp") && s.contains(String.valueOf(schermata.p.getEsperienza())) && 
                   s.contains("Oro") && s.contains(String.valueOf(schermata.p.getSoldi()))
        );
    }
    @Test
    public void testStatistichePersonaggioComb(){ //aggiornare con le statistiche corrette
        String s = schermata.getStatistiche();
        assertTrue(s.contains("Nome") && s.contains(String.valueOf(schermata.p.getNome())) && 
                   s.contains("Livello") && s.contains(String.valueOf(schermata.p.getLvlGiocatore())) && 
                   s.contains("Salute") && s.contains(String.valueOf(schermata.p.getSalute())) && 
                   s.contains("Attacco") && s.contains(String.valueOf(schermata.tipoA)) && 
                   s.contains("Difesa") && s.contains(String.valueOf(schermata.p.getArmatura())) && 
                   s.contains("Arma") && s.contains("Armatura") && 
                   !s.contains("Exp") && !s.contains("Oro") && !s.contains("AttMagico") && !s.contains("AttFisico")
        );
    }
    
    @Test
    public void testAttacca(){
        testCombattimento();
        
        boolean cond;
        int salutePersonaggio, saluteNemico, esperienza = schermata.p.getEsperienza(), soldi = schermata.p.getSoldi();
        String statistiche = schermata.getStatistiche(), statNemico = schermata.getStatNemico();
        for(int i = 0; i < 50; i++){
            salutePersonaggio = schermata.p.getSalute();
            saluteNemico = schermata.nemico.getSalute();
            schermata.playerChoice("Attacca");
            //System.out.println(schermata.choice);
            if(schermata.choice == true){ //nemico attacca
                
                assertEquals(saluteNemico-((schermata.tipoAttacco-schermata.nemico.getArmatura()) < 1 ? 1 : (schermata.tipoAttacco-schermata.nemico.getArmatura())), schermata.nemico.getSalute());
                assertNotEquals(statNemico, schermata.getStatNemico());
                statNemico = schermata.getStatNemico();
                testStatisticheNemicoComb();
                if(schermata.nemico.getSalute()<=0){
                    assertEquals(esperienza+schermata.nemico.getEsperienza(), schermata.p.getEsperienza());
                    assertEquals("Sei Sopravvissuto.wav", schermata.getPathFSuoni());
                    testStatistichePersonaggio();
                    testStatisticheNemicoComb();
                    assertFalse(schermata.s.getVerifica());  //verifica salvataggio ancora disabilitato
                    assertTrue(schermata.negozioIsEnable());
                    if(schermata.p.getSoldi()<=(soldi+9) && schermata.p.getSoldi()>=(soldi+3)){
                        cond = true;
                        assertTrue(cond);
                    }
                    else {
                        cond = false;
                        assertFalse(cond);
                    }
                    return;
                }
                else {
                    assertEquals(salutePersonaggio-((schermata.nemico.getAttacco()-schermata.p.getArmatura()) < 1 ? 1 : (schermata.nemico.getAttacco()-schermata.p.getArmatura())), schermata.p.getSalute());
                    assertNotEquals(statistiche, schermata.getStatistiche());
                    statistiche = schermata.getStatistiche();
                    testStatistichePersonaggioComb();
                    if(schermata.p.getSalute()<=0){
                        assertEquals("Sei Morto.wav", schermata.getPathFSuoni());
                        testStatistichePersonaggioComb();
                        assertTrue(schermata.s.getVerifica());  //verifica salvataggio ancora disabilitato
                        return;
                    }
                }
            }
            else { //nemico si difende
                
                if(schermata.choice == false){  //nemico difesa riuscita
                    assertEquals(salutePersonaggio, schermata.p.getSalute());
                    assertEquals(saluteNemico, schermata.nemico.getSalute());
                    assertEquals(statNemico, schermata.getStatNemico());
                    testStatisticheNemicoComb();
                    assertEquals(statistiche, schermata.getStatistiche());
                }
                else {  //nemico difesa non riuscita
                    assertEquals(saluteNemico-((schermata.tipoAttacco-schermata.nemico.getArmatura()) < 1 ? 1 : (schermata.tipoAttacco-schermata.nemico.getArmatura())), schermata.nemico.getSalute());
                    assertNotEquals(statNemico, schermata.getStatNemico());
                    statNemico = schermata.getStatNemico();
                    testStatisticheNemicoComb();
                    if(schermata.nemico.getSalute()<=0){
                        assertEquals(esperienza+schermata.nemico.getEsperienza(), schermata.p.getEsperienza());
                        assertEquals("Sei Sopravvissuto.wav", schermata.getPathFSuoni());
                        testStatistichePersonaggio();
                        testStatisticheNemicoComb();
                        assertFalse(schermata.s.getVerifica());  //verifica salvataggio ancora disabilitato
                        assertTrue(schermata.negozioIsEnable());
                        if(schermata.p.getSoldi()<=(soldi+9) && schermata.p.getSoldi()>=(soldi+3)){
                            cond = true;
                            assertTrue(cond);
                        }
                        else {
                            cond = false;
                            assertFalse(cond);
                        }
                        return;
                    }
                    else {
                        assertEquals(salutePersonaggio-((schermata.nemico.getAttacco()-schermata.p.getArmatura()) < 1 ? 1 : (schermata.nemico.getAttacco()-schermata.p.getArmatura())), schermata.p.getSalute());
                        assertNotEquals(statistiche, schermata.getStatistiche());
                        testStatistichePersonaggioComb();
                        if(schermata.p.getSalute()<=0){
                            assertEquals("Sei Morto.wav", schermata.getPathFSuoni());
                            testStatistichePersonaggioComb();
                            assertTrue(schermata.s.getVerifica());  //verifica salvataggio ancora disabilitato
                            return;
                        }
                    }
                }
            }
            assertEquals("Spada.wav", schermata.getPathFSuoni());
        }
    }
    
    @Test
    public void testDifendi(){
        testCombattimento();
        
        boolean cond;
        int salutePersonaggio, saluteNemico, esperienza = schermata.p.getEsperienza(), soldi = schermata.p.getSoldi();
        String statistiche = schermata.getStatistiche(), statNemico = schermata.getStatNemico();
        for(int i=0, k = 0; i<20; i++){
            salutePersonaggio = schermata.p.getSalute();
            saluteNemico = schermata.nemico.getSalute();
            schermata.playerChoice("Difenditi");
            k++;
            if(k<=2){  //difesa riuscita
                assertEquals(salutePersonaggio, schermata.p.getSalute());
                assertEquals(saluteNemico, schermata.nemico.getSalute());
                assertEquals("Scudo.wav", schermata.getPathFSuoni());
                assertEquals(statNemico, schermata.getStatNemico());
                testStatisticheNemicoComb();
            }
            else {//difesa non riuscita
                k = 0;
                assertEquals("Spada.wav", schermata.getPathFSuoni());
                if(schermata.choice == true){ //nemico attacca
                    assertEquals(saluteNemico-((schermata.tipoAttacco-schermata.nemico.getArmatura()) < 1 ? 1 : (schermata.tipoAttacco-schermata.nemico.getArmatura())), schermata.nemico.getSalute());
                    assertNotEquals(statNemico, schermata.getStatNemico());
                    statNemico = schermata.getStatNemico();
                    testStatisticheNemicoComb();
                    if(schermata.nemico.getSalute()<=0){
                        assertEquals(esperienza+schermata.nemico.getEsperienza(), schermata.p.getEsperienza());
                        assertEquals("Sei Sopravvissuto.wav", schermata.getPathFSuoni());
                        testStatistichePersonaggio();
                        testStatisticheNemicoComb();
                        assertFalse(schermata.s.getVerifica());  //verifica salvataggio ancora disabilitato
                        assertTrue(schermata.negozioIsEnable());
                        if(schermata.p.getSoldi()<=(soldi+9) && schermata.p.getSoldi()>=(soldi+3)){
                            cond = true;
                            assertTrue(cond);
                        }
                        else {
                            cond = false;
                            assertFalse(cond);
                        }
                        return;
                    }
                    else {
                        assertEquals(salutePersonaggio-((schermata.nemico.getAttacco()-schermata.p.getArmatura()) < 1 ? 1 : (schermata.nemico.getAttacco()-schermata.p.getArmatura())), schermata.p.getSalute());
                        assertNotEquals(statistiche, schermata.getStatistiche());
                        statistiche = schermata.getStatistiche();
                        testStatistichePersonaggioComb();
                        if(schermata.p.getSalute()<=0){
                            assertEquals("Sei Morto.wav", schermata.getPathFSuoni());
                            testStatistichePersonaggioComb();
                            assertTrue(schermata.s.getVerifica());  //verifica salvataggio ancora disabilitato
                            return;
                        }
                    }
                }
                else {  //nemico difesa
                    assertEquals(salutePersonaggio, schermata.p.getSalute());
                    assertEquals(saluteNemico, schermata.nemico.getSalute());
                    assertEquals(statNemico, schermata.getStatNemico());
                    testStatisticheNemicoComb();
                    assertEquals(statistiche, schermata.getStatistiche());
                }
            }
        }
    }
    
    @Test
    public void testFuga(){
        testCombattimento();
        
        int salutePersonaggio = schermata.p.getSalute();
        schermata.playerChoice("Fuggi");
        if(schermata.GameConsoleGetTesto().contains("Sei fuggito")){
            System.out.println("Fuggito");
            assertEquals(salutePersonaggio, schermata.p.getSalute());
            assertEquals("Fuga.wav", schermata.getPathFSuoni());
            testStatistichePersonaggio();
            testStatisticheNemicoComb();
        }
        else {
            System.out.println("Non fuggito");
            assertEquals("Colpito.wav", schermata.getPathFSuoni());
            assertEquals(salutePersonaggio-((schermata.nemico.getAttacco()-schermata.p.getArmatura()) < 1 ? 1 : (schermata.nemico.getAttacco()-schermata.p.getArmatura())), schermata.p.getSalute());
            testStatistichePersonaggioComb();
            testStatisticheNemicoComb();
            if(schermata.p.getSalute()<=0){
                assertEquals("Sei Morto.wav", schermata.getPathFSuoni());
                testStatistichePersonaggioComb();
                assertTrue(schermata.s.getVerifica());  //verifica salvataggio ancora disabilitato
            }
        }
    }
    
    
    
    
    public void testLivello(int lvl, int progressiLvl){
        Personaggio p = new Personaggio("Nome", "Guerriero");
        p.setLvlGioco(lvl);
        p.setProgressilvl(progressiLvl);
        Negozio n = new Negozio();
        this.schermata = new SchermataPrincipale(p, new Salvataggio(p, n), n);
        p.setLvlGioco(lvl);
        p.setProgressilvl(progressiLvl);
        schermata.playerChoice("Avanti");
        //assertEquals(schermata.scenario.get(progressiLvl).getTesto(), schermata.GameConsoleGetTesto());
    }
    @Test
    public void testLivelloAvantiNarrazione(){
        testLivello(0, 0);
        
        
        schermata.playerChoice("Avanti");
        assertEquals(Slide.TIPO.NARRAZIONE.ordinal(), schermata.scenario.get(1).getTipo());
        assertEquals(schermata.scenario.get(0).getTesto(), schermata.GameConsoleGetTesto());

        assertEquals(schermata.p.getLvlGioco(), schermata.k);
        assertEquals(schermata.p.getProgressilvl(), schermata.y);
        
        assertEquals("Intro.wav", schermata.getPathFMusica());
    }
    @Test
    public void testLivelloAvantiCombattimento(){
        testLivello(1, 11);  //progresso corretto
        
        //schermata.playerChoice("Avanti");
        assertEquals(Slide.TIPO.COMBATTIMENTO.ordinal(), schermata.scenario.get(11).getTipo());  //progresso corretto
        assertNotNull(schermata.nemico);
        assertTrue(schermata.GameConsoleGetTesto().contains(schermata.nemico.getNome()));
        assertTrue((schermata.GameConsoleGetTesto().contains("Fantasma") || 
                    schermata.GameConsoleGetTesto().contains("Grifone") || 
                    schermata.GameConsoleGetTesto().contains("Arpia") || 
                    schermata.GameConsoleGetTesto().contains("Scheletro") || 
                    schermata.GameConsoleGetTesto().contains("Licantropo") ||
                    schermata.GameConsoleGetTesto().contains("Demoni")) && 
                    (!schermata.GameConsoleGetTesto().contains("Goblin") && 
                     !schermata.GameConsoleGetTesto().contains("Drago") && 
                     !schermata.GameConsoleGetTesto().contains("Gigante")));
        
        assertEquals(schermata.p.getLvlGioco(), schermata.k);
        assertEquals(schermata.p.getProgressilvl(), schermata.y);
        
        assertEquals("Combattimento.wav", schermata.getPathFMusica());
    }
    @Test
    public void testLivelloAvantiBoss(){
        testLivello(1, 28);  //progresso corretto
        
        //schermata.playerChoice("Avanti");
        assertEquals(Slide.TIPO.BOSS.ordinal(), schermata.scenario.get(28).getTipo());  //progresso corretto
        assertNotNull(schermata.nemico);
        assertTrue(schermata.GameConsoleGetTesto().contains(schermata.nemico.getNome()));
        assertTrue(schermata.GameConsoleGetTesto().contains("Drago") &&
                  (!schermata.GameConsoleGetTesto().contains("Fantasma") && 
                   !schermata.GameConsoleGetTesto().contains("Grifone") && 
                   !schermata.GameConsoleGetTesto().contains("Arpia") && 
                   !schermata.GameConsoleGetTesto().contains("Scheletro") && 
                   !schermata.GameConsoleGetTesto().contains("Licantropo") &&
                   !schermata.GameConsoleGetTesto().contains("Demoni") && 
                   !schermata.GameConsoleGetTesto().contains("Goblin") && 
                   !schermata.GameConsoleGetTesto().contains("Gigante")));
        
        assertEquals(schermata.p.getLvlGioco(), schermata.k);
        assertEquals(schermata.p.getProgressilvl(), schermata.y);
        
        assertEquals("Boss.wav", schermata.getPathFMusica());
    }
    @Test
    public void testLivelloAvantiMinisBoss(){
        testLivello(1, 17);  //progresso corretto
        
        //schermata.playerChoice("Avanti");
        assertEquals(Slide.TIPO.MINIBOSS.ordinal(), schermata.scenario.get(17).getTipo());  //progresso corretto
        assertNotNull(schermata.nemico);
        assertTrue(schermata.GameConsoleGetTesto().contains(schermata.nemico.getNome()));
        assertTrue(schermata.GameConsoleGetTesto().contains("Gigante") &&
                  (!schermata.GameConsoleGetTesto().contains("Fantasma") && 
                   !schermata.GameConsoleGetTesto().contains("Grifone") && 
                   !schermata.GameConsoleGetTesto().contains("Arpia") && 
                   !schermata.GameConsoleGetTesto().contains("Scheletro") && 
                   !schermata.GameConsoleGetTesto().contains("Licantropo") &&
                   !schermata.GameConsoleGetTesto().contains("Demoni") && 
                   !schermata.GameConsoleGetTesto().contains("Goblin") && 
                   !schermata.GameConsoleGetTesto().contains("Drago")));
        
        assertEquals(schermata.p.getLvlGioco(), schermata.k);
        assertEquals(schermata.p.getProgressilvl(), schermata.y);
        
        assertEquals("Combattimento.wav", schermata.getPathFMusica());
    }
    @Test
    public void testLivelloAvantiBaule(){
        testLivello(1, 12);  //progresso corretto
        
        //schermata.playerChoice("Avanti");
        assertEquals(Slide.TIPO.BAULE.ordinal(), schermata.scenario.get(12).getTipo());
        assertTrue(schermata.GameConsoleGetTesto().contains("baule"));
        System.out.println(schermata.GameConsoleGetTesto());
        assertEquals(schermata.p.getLvlGioco(), schermata.k);
        assertEquals(schermata.p.getProgressilvl(), schermata.y);
        
        assertEquals("Baule.wav", schermata.getPathFMusica());
    }
    @Test
    public void testLivelloAvantiEventoCasuale(){
        testLivello(1, 19);  //progresso corretto
        
        //schermata.playerChoice("Avanti");
        assertEquals(Slide.TIPO.EVENTO_CASUALE.ordinal(), schermata.scenario.get(19).getTipo());  //progresso corretto
        assertTrue(schermata.GameConsoleGetTesto().contains("baule") || 
                   schermata.GameConsoleGetTesto().contains("incontrato") ||
                  (schermata.GameConsoleGetTesto().contains("Fantasma") || 
                   schermata.GameConsoleGetTesto().contains("Grifone") || 
                   schermata.GameConsoleGetTesto().contains("Arpia") || 
                   schermata.GameConsoleGetTesto().contains("Scheletro") || 
                   schermata.GameConsoleGetTesto().contains("Licantropo") ||
                   schermata.GameConsoleGetTesto().contains("Demoni")) && 
                   (!schermata.GameConsoleGetTesto().contains("Goblin") && 
                    !schermata.GameConsoleGetTesto().contains("Drago") && 
                    !schermata.GameConsoleGetTesto().contains("Gigante"))
                   );
        
        assertEquals(schermata.p.getLvlGioco(), schermata.k);
        assertEquals(schermata.p.getProgressilvl(), schermata.y);
    }
    @Test
    public void testLivelloSceltaImportante(){
        testLivello(1, 20);  //progresso corretto
        
        //schermata.playerChoice("Avanti");
        assertEquals(Slide.TIPO.SCELTA_IMPORTANTE.ordinal(), schermata.scenario.get(20).getTipo());  //progresso corretto
        assertTrue(schermata.GameConsoleGetTesto().contains("incontrato"));
        
        assertEquals(schermata.p.getLvlGioco(), schermata.k);
        assertEquals(schermata.p.getProgressilvl(), schermata.y);
    }
    
    
    
    //ELIMINARE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Test
    public void testFineLivello(){
        testLivello(0, 0);  //progresso corretto
        
        schermata.playerChoice("Avanti");
        assertTrue(schermata.k==1 && schermata.y==0);
        
        assertEquals(schermata.p.getLvlGioco(), schermata.k);
        assertEquals(schermata.p.getProgressilvl(), schermata.y);
    }
    //test fine gioco
    @Test
    public void testFineGioco(){
        testLivello(0, 0);  //progresso corretto
    }
}