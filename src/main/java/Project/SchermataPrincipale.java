package Project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import static java.awt.event.MouseEvent.MOUSE_CLICKED;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class SchermataPrincipale extends javax.swing.JFrame {

    protected Personaggio p;
    protected Salvataggio s;
    protected Negozio n;
    protected Nemico nemico;
    protected Random rand = new Random();
    
    protected String tipoA;  //per visualizzare nelle statistiche l'attacco usato nel combattimento
    protected String testo;  //per salvare l'ultimo testo presente
    protected String stato = " ";  //per salvare quale evento si è verificato
    protected int tipoAttacco;  //per utilizzare il relativo attacco rispetto al nemico nel combattimento
    protected boolean v = true;  //per la gestione del nemico casuale nel combattimento
    protected boolean choice;  //true attacca, false difendi del nemico
    protected int i,j;  //contatore per le difese di fila del nemico e personaggio
    protected int t,f;  //contatori per la scelta del finale
    protected int cont; //verifica del tipo nemico
    
    protected String[] pathMusica = {"src\\main\\java\\Project\\Musica\\Main.wav", "src\\main\\java\\Project\\Musica\\Negozio.wav", "src\\main\\java\\Project\\Musica\\Combattimento.wav", "src\\main\\java\\Project\\Musica\\Baule.wav", "src\\main\\java\\Project\\Musica\\Boss.wav", "src\\main\\java\\Project\\Musica\\Vittoria.wav", "src\\main\\java\\Project\\Musica\\Intro.wav"};
    protected String[] pathSuoni = {"src\\main\\java\\Project\\Musica\\Porta Negozio.wav", "src\\main\\java\\Project\\Musica\\Scudo.wav", "src\\main\\java\\Project\\Musica\\Spada.wav", "src\\main\\java\\Project\\Musica\\Magia.wav", "src\\main\\java\\Project\\Musica\\Fuga.wav", "src\\main\\java\\Project\\Musica\\Colpito.wav", "src\\main\\java\\Project\\Musica\\Sei Morto.wav", "src\\main\\java\\Project\\Musica\\Sei Sopravvissuto.wav", "src\\main\\java\\Project\\Musica\\Soldi.wav"};
    protected File fMusica, fSuoni;
    protected AudioInputStream inputMusica;
    protected Clip clipMusica, clipSuoni;
    protected int music;
    
    protected String[] nomiNemici = {"Arpia","Grifone","Fantasma","Demoni","Scheletro","Licantropo","Goblin","Gigante","Drago"};
    protected String[] scelteSecondarie = {"Si","No"};
    protected String[] secondarie = {"Dona","Aiuta","Accumula"};
    protected String[] bauli = {"Comune","Raro"};
    protected String[] scelteBauleC = {"Apri","Ignora"};
    protected String[] scelteBauleR = {"Scassina","Ignora"};
    protected String[] comb = {"Attacca","Difenditi","Fuggi"};
    protected String[] avanti = {"Avanti"};
    protected String[] eventi = {"Baule","Combattimento"};
    protected String[] sceltaApri = {"Combattimento", "Soldi","Trappola"};
        
    protected ArrayList <Slide> scenario;//livelli di gioco
    protected int k,y;  //per scorrere i livelli
    
    DefaultListModel dmNegozio = new DefaultListModel();
    DefaultListModel dmInventario = new DefaultListModel();
    protected int countArmi = 0, countArmature = 0, countConsumabili = 0;
    
    
    public SchermataPrincipale(Personaggio p, Salvataggio s, Negozio n) {
        initComponents();
        
        ShopList.setModel(dmNegozio);//crea la ShopList del negozio e la setta vuota
        InventoryList.setModel(dmInventario);
        StatNemico.setVisible(false);
        PlayerChoice.setListData(avanti);
        Abilità.setEnabled(true);
        
        this.p = p;
        this.s = s;
        this.n = n;
        
        initPersonaggio();
        
        this.k = this.p.getLvlGioco();
        this.y = this.p.getProgressilvl();
        
        
        switch(this.k){
            case 0 -> {
                this.scenario = Tutorial();
                this.s.disattivaSalvataggio();
                this.NegozioBT.setEnabled(false);
                this.music = 6;
            }
            case 1 -> {
                this.scenario = Livello1();
                this.s.attivaSalvataggio();
                this.NegozioBT.setEnabled(true);
                this.music = 0;
            }
            default -> {
                this.scenario = Livello1();
                this.music = 0;
            }
        }
        
        if(this.k >= 0 && this.y > 0)
            GameConsole.setText("\n\n Saluti " + this.p.getNome() + ", finalmente sei tornato.");
        else 
            GameConsole.setText(this.scenario.get(y).getTesto());
        
        GameImg.setIcon(new ImageIcon(this.scenario.get(y).getSfondoImg()));
        this.GameImgLabel.setIcon(new ImageIcon(this.scenario.get(y).getImg()));
        
        try{
            this.fMusica = new File(this.pathMusica[music]);
            this.inputMusica = AudioSystem.getAudioInputStream(this.fMusica);
            this.clipMusica = AudioSystem.getClip();
            this.clipMusica.open(inputMusica);
            this.clipMusica.start();
            this.clipMusica.loop(Clip.LOOP_CONTINUOUSLY);
        }catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){}
    }
    
    //Metodo per inizializzare il personaggio con la relativa classe e abilità
    private void initPersonaggio()
    {
        if(this.p.getClasse().contains("Guerriero")){
            ClasseIco.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\IcoGuerriero.png"));
            Abilità.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Scudo.png"));
            Abilità.setToolTipText("Rigenera la tua salute al massimo");
        }else if(this.p.getClasse().contains("Mago")){
            ClasseIco.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\IcoMago.png"));
            Abilità.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Fuoco.png"));
            Abilità.setToolTipText("Ottieni un bonus in attacco, a discapito di salute e armatura");
        }else if(this.p.getClasse().contains("Stratega")){
            ClasseIco.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\IcoStratega.png"));
            Abilità.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Fortuna.png"));
            Abilità.setToolTipText("Ottieni un consumabile random dal negozio");
        }
        this.aggiornaStatistiche();
        this.aggiornaInventario();
    }
    
    //Metodo che genera il Livello 1
    private ArrayList<Slide> Livello1(){
        ArrayList<Slide> livello = new ArrayList<>();
        //Atto 1.
        livello.add(new Slide(Slide.TIPO.CAMBIO_SFONDO, "\n ...", "", "Città_3.jpg"));
        livello.add(new Slide(Slide.TIPO.CAMBIO_SFONDO, "\n Ehi! Svegliati!", "", "Città_2.jpg"));
        livello.add(new Slide(Slide.TIPO.CAMBIO_SFONDO, "\n Chi sei?\n Che ci fai qui tutto solo?", "Veronica.png", "Città_1.jpg"));
        livello.add(new Slide(Slide.TIPO.NARRAZIONE, "\n Ahhh!!\n Sei " + this.p.getNome() + "?\n Sei qui per aiutarci?!", "Veronica.png","Città_1.jpg"));
        livello.add(new Slide(Slide.TIPO.NARRAZIONE, "\n Mi presento, sono Veronica. È un onore conoscerti.\n La profezia parlava di un certo " + this.p.getClasse() + ","
                + "                     \n di nome " + this.p.getNome() + " che ci avrebbe salvato da queste creature mostruose.", "Veronica.png", "Città_1.jpg"));
        livello.add(new Slide(0, "\n Attenzione, se ne sta avvicinando una proprio adesso!!\n Aiutami!", "Veronica.png", "Città_1.jpg"));
        livello.add(new Slide(Slide.TIPO.COMBATTIMENTO, "", "", "Città_1.jpg"));
        
        //Atto 2.
        livello.add(new Slide(Slide.TIPO.NARRAZIONE, "\n Uff!\n Ce la siamo vista proprio brutta.", "Veronica.png", "Città_1.jpg"));
        livello.add(new Slide(Slide.TIPO.NARRAZIONE, "\n La vita qui era serena, fino a quando, un anno fa...\n sono cominciate a comparire questi abomini.\n "
                                         + "Tante persone hanno provato a tenergli testa, ma invano.\n Siamo disperati e nessuno ha più le forze per combattere.", "Veronica.png", "Città_1.jpg"));
        livello.add(new Slide(Slide.TIPO.NARRAZIONE, "\n Ma adesso dirigiamoci in un posto sicuro.", "Veronica.png", "Città_1.jpg"));
        livello.add(new Slide(Slide.TIPO.SCELTA_IMPORTANTE, "", "", "Città_1.jpg"));
        livello.add(new Slide(Slide.TIPO.COMBATTIMENTO, "", "", "Città_1.jpg"));
        
        //Atto 3.
        livello.add(new Slide(Slide.TIPO.BAULE, "", "", "Città_1.jpg"));
        livello.add(new Slide(Slide.TIPO.CAMBIO_SFONDO, "\n Questi strani bauli sono apparsi poco dopo\n l'avvento dei mostri."
                                        + " Quei maledetti Goblin stanno facendo\n razzie dei tesori al loro interno,"
                + " è per questo che a volte li puoi \n trovare nascosti in essi.", "Veronica.png", "Foresta.jpg"));
        livello.add(new Slide(Slide.TIPO.COMBATTIMENTO, "", "", "Foresta.jpg"));
        
        //Atto 4.
        livello.add(new Slide(Slide.TIPO.NARRAZIONE, "\n AHH!\n Che succede? Sta tremando tutto!\n Oh...\n Sembra che si stia avvicinando qualcosa...", "Veronica.png", "Foresta.jpg"));
        livello.add(new Slide(Slide.TIPO.NARRAZIONE, "\n (canticchiando allegramente...)\n\n Creature insignificanti,"
                + "\n il mio capo sarà contento quando vi porterò da loro.\n AHAHAHAHAH!", "Gigante.png", "Foresta.jpg"));
        livello.add(new Slide(Slide.TIPO.MINIBOSS, "", "", "Foresta.jpg"));
        livello.add(new Slide(Slide.TIPO.NARRAZIONE, "\n Complimenti!!\n Questo sembrava più tosto degli altri.\n Riprendiamo la nostra strada.", "Veronica.png", "Foresta.jpg"));
        livello.add(new Slide(Slide.TIPO.EVENTO_CASUALE, "", "", "Foresta.jpg"));
        livello.add(new Slide(Slide.TIPO.SCELTA_IMPORTANTE, "", "", "Foresta.jpg"));
        livello.add(new Slide(Slide.TIPO.EVENTO_CASUALE, "", "", "Foresta.jpg"));
        
        //Atto 5.
        livello.add(new Slide(Slide.TIPO.CAMBIO_SFONDO, "\n Sembra che il cielo stia cambiando...", "Veronica.png", "Fiume.jpg"));
        livello.add(new Slide(Slide.TIPO.SCELTA_IMPORTANTE, "", "", "Fiume.jpg"));
        livello.add(new Slide(Slide.TIPO.NARRAZIONE, "\n Questo posto mi inquieta... affrettiamoci!", "Veronica.png", "Fiume.jpg"));
        livello.add(new Slide(Slide.TIPO.NARRAZIONE, "\n Finalmente vi ho scovati!"
                + "\n Pensavate di seminare il caos nel MIO territorio?!", "Drago_nero.png", "Fiume.jpg"));
        livello.add(new Slide (Slide.TIPO.NARRAZIONE, "\n AAAAHHHH!!", "Veronica.png", "Fiume.jpg"));
        livello.add(new Slide (Slide.TIPO.NARRAZIONE, "\n Lei viene con me!\n\n (Veronica è stata presa in ostaggio)", "Drago_nero.png", "Fiume.jpg"));
        livello.add(new Slide(Slide.TIPO.BOSS, "", "", "Fiume.jpg"));
        
        //Atto 6. (Opzinale)
        livello.add(new Slide(Slide.TIPO.CAMBIO_SFONDO, "\n ´È troppo forte..."
                +"\n fortunatamente sono fuggito in questa caverna." +"\n Devo adottare un'altra strategia.´", "", "Caverna.jpg"));
        livello.add(new Slide(Slide.TIPO.EVENTO_CASUALE, "", "", "Caverna.jpg"));
        livello.add(new Slide(Slide.TIPO.EVENTO_CASUALE, "", "", "Caverna.jpg"));
        livello.add(new Slide(Slide.TIPO.EVENTO_CASUALE, "", "", "Caverna.jpg"));
        livello.add(new Slide(Slide.TIPO.NARRAZIONE, "\n ´Il sentiero dietro di me è franato, non posso più tornare indietro."
                + "\n Proseguendo su questa strada ritornerò dal Drago... e da Veronica!´", "", "Caverna.jpg"));
        livello.add(new Slide(Slide.TIPO.CAMBIO_SFONDO, "\n ´Sono fuori ma è già sera..."
                +"\n laggiu' c'è il Drago.\n Stavolta riuscirò a sconfiggerlo!´", "", "Fiume_sera.jpg"));
        livello.add(new Slide(Slide.TIPO.BOSS, "", "", "Fiume_sera.jpg"));
        
        //Atto 6.1 (vittoria, finale)
        livello.add(new Slide(Slide.TIPO.DOPO_BOSS, "\n Mi hai salvata!"
                + "\n Allora sei davvero l'eroe della profezia che salverà il mondo!"
                + "\n\n Potrai davvero sconfiggere...", "Veronica.png","Fiume_sera.png"));
        livello.add(new Slide(Slide.TIPO.CAMBIO_SFONDO, "\n ...´Ma che succede?´", "", "Finale.jpg"));
        livello.add(new Slide(Slide.TIPO.NARRAZIONE, "\n Rieccomi " + this.p.getNome() + ","
                + "\n ho seguito tutte le tue gesta in questa avventura.\n E...", "Dea.png", "Finale.jpg"));
        livello.add(new Slide(Slide.TIPO.FINALE, "", "Dea.png", "Finale.jpg"));
        return livello;
    }
    
    //Metodo che genera il Tutorial
    private ArrayList<Slide> Tutorial(){
        this.s.disattivaSalvataggio();
        
        ArrayList<Slide> tutorial = new ArrayList<>();  
        
        tutorial.add(new Slide(Slide.TIPO.CAMBIO_SFONDO, "\n Ciao " + p.getNome() + ", a quanto pare sei un " + p.getClasse().toLowerCase() +
                                ".\n\n Ti spiegherò brevemente come funziona questo mondo.\n\n" + " Andiamo!", "Dea.png", "Tutorial.jpg"));
        
        tutorial.add(new Slide(0, """
                                  
                                  A destra della schermata potrai vedere le tue 
                                  statistiche.
                                  
                                  Hai notato l'icona in basso alla mia sinistra? Quella è la tua abilità.
                                  Potrai utilizzarla soltanto una volta, 
                                  quindi scegli il momento migliore.
                                 ""","Dea.png", "Tutorial.jpg"));

        tutorial.add(new Slide(6, """
                                  
                                  Come vedi nel tuo inventario è presente un oggetto, il grimaldello.
                                  Tienilo stretto, ti servirà presto!
                                  
                                  È anche presente il negozio dove potrai comprare 
                                  nuovi oggetti in cambio di oro.
                                  Clicca su "negozio" per esplorarlo se ti va.
                                 ""","Dea.png", "Tutorial.jpg"));
        
        tutorial.add(new Slide(0,"""
                                 
                                 Affronterai tante battaglie quindi ti farò
                                 sfidare il tuo primo nemico! Fa la tua mossa.
                                 
                                 Attaccalo o ricevi il suo attacco difendendoti.
                                 Fuggi se pensi di non essere alla sua altezza. Ma ricorda
                                 ci vuole grande fortuna per riuscire a sfuggire al nemico.
                                """, "Dea.png", "Tutorial.jpg"));
        
        tutorial.add(new Slide(2, "", "", ""));
        
        tutorial.add(new Slide(0,"""
                                 
                                 Grandioso! Hai ottenuto dell'oro e anche dei punti exp!
                                 Ti servirà per diventare piu' forte.
                                 
                                 Ma.. vedo qualcosa laggiu'. Sembra proprio un baule!
                                 È arrivato il momento di utilizzare il grimaldello.
                                """, "Dea.png", "Tutorial.jpg"));
        
        tutorial.add(new Slide(7, "", "", ""));
          
        tutorial.add(new Slide(11, "\n Hai ottenuto nuovi oggetti e oro,\n"
                + " a questo punto credo che puoi\n"
                + " continuare la tua avventura da solo.", "Dea.png", "Tutorial.jpg"));
        
        tutorial.add(new Slide(0,"""
                                 
                                 Non preoccuparti, ci vedremo piu' avanti!
                                 Ciao e buona fortuna!
                                ""","Dea.png", "Tutorial.jpg"));
        return tutorial;
    }
    
    //Metodo per l'aggiornamento delle statistiche del personaggio
    private void aggiornaStatistiche(){
        AttFisField.setVisible(true);
        AttMagField.setVisible(true);
        ArmaturaField.setVisible(true);
        
        Statistiche.setText(" Nome: " + this.p.getNome() + "\n Livello: "+this.p.getLvlGiocatore() + "\n Salute: " + this.p.getSalute()+ "/" +
                this.p.getMaxSalute() + "\n Exp: " + this.p.getEsperienza() + "/" + this.p.getMaxEsperienza() + "\n Oro: " + this.p.getSoldi()+
                "\n AttFisico: "+this.p.getAttFisico() + "\n AttMagico: " + this.p.getAttMagico() 
                + "\n Armatura: "+this.p.getArmatura()+"\n");
        
     
        if(this.p.getEquip()[0] == -1){
            Statistiche.append("\n Arma: ");
            AttFisField.setText("");
            AttMagField.setText("");
        }else{
            Statistiche.append("\n Arma: " + this.p.getInventario().get(this.p.getEquip()[0]).getNome());
            Armi a = (Armi)this.p.getInventario().get(this.p.getEquip()[0]);
            AttFisField.setText("(+" + a.getAttFisico()+")");
            AttMagField.setText("(+" + a.getAttMagico()+")");
        }
        if(this.p.getEquip()[1] == -1){
            Statistiche.append("\n Armatura: ");
            ArmaturaField.setText("");
        
        }else{
            Statistiche.append("\n Armatura: " + this.p.getInventario().get(this.p.getEquip()[1]).getNome());
            Armatura a = (Armatura)this.p.getInventario().get(this.p.getEquip()[1]);
            ArmaturaField.setText("(+" + a.getArmatura()+")");
        }
    }
    
    //Metodo per l'aggiornamento delle statistiche durante il combattimento
    protected void aggiornaStatisticheCombattimento(){
        
        if(this.nemico.getTipo().equals("Magico"))
           this.tipoAttacco = this.p.getAttMagico();
        else
           this.tipoAttacco = this.p.getAttFisico();
        
        AttFisField.setVisible(false);
        AttMagField.setVisible(false);
        ArmaturaField.setVisible(false);
        Statistiche.setText(" Nome: " + this.p.getNome() +"\n Livello: " + this.p.getLvlGiocatore() + "\n Salute: " + this.p.getSalute() + "/" + this.p.getMaxSalute() + "\n Attacco " + this.tipoA + 
        ": " + this.tipoAttacco+ "\n Difesa: " + this.p.getArmatura());  
        if(this.p.getEquip()[0] == -1)
            Statistiche.append("\n\n Arma: ");
        else
            Statistiche.append("\n\n Arma: " + this.p.getInventario().get(this.p.getEquip()[0]).getNome());
        
        if(this.p.getEquip()[1] == -1)
            Statistiche.append("\n Armatura: ");
        else
            Statistiche.append("\n Armatura: " + this.p.getInventario().get(this.p.getEquip()[1]).getNome());
        StatNemico.setText("Nemico: " + this.nemico.nome + "\nLivello: "+ this.nemico.getLvl() + "\nSalute: " + this.nemico.salute);
        
    }
    
    //Metodo per aggiornare l'Inventario
    private void aggiornaInventario(){
        int i = this.InventoryList.getSelectedIndex();
        this.dmInventario.removeAllElements();
        this.dmInventario.addAll(this.p.getInventario());
        this.InventoryList.setSelectedIndex(i);
    }
    
    //Metodo per aggiornare gli elementi del negozio
    private void aggiornaNegozio(){
        this.countArmi=0; this.countArmature = 0; this.countConsumabili = 0;
        this.dmNegozio.removeAllElements();
            this.dmNegozio.addElement("---------------------Armature--------------------------------");
            for(int i=0; i<this.n.listArmature.size(); i++)
                if(this.n.listArmature.get(i).getLvl() <= this.p.getLvlGiocatore()){
                        this.dmNegozio.addElement(this.n.listArmature.get(i));
                        this.countArmature++;
               }

            this.dmNegozio.addElement("---------------------Armi--------------------------------------");
            for(int i=0; i<this.n.listArmi.size(); i++)
                if(this.n.listArmi.get(i).getLvl() <= this.p.getLvlGiocatore()){
                        this.dmNegozio.addElement(this.n.listArmi.get(i));
                        this.countArmi++;
                }
           
            this.dmNegozio.addElement("---------------------Consumabili-----------------------------");
            for(int i=0; i<this.n.listConsumabili.size(); i++)
                if(this.n.listConsumabili.get(i).getLvl() <= this.p.getLvlGiocatore()){
                        this.dmNegozio.addElement(this.n.listConsumabili.get(i));
                        this.countConsumabili++;
               }
    }
    
    //Metodo per il drop dei Consumabili nei bauli Rari
    protected void dropConsumabili(){
        Consumabili c ;
        switch(rand.nextInt(4)){
            case 0 -> c = new Consumabili("Salute +15", "Pozione di salute", "C", 15, 20, 1,3,1);
            case 1 -> c = new Consumabili("Magia +5", "Pozione di magia", "C", 5, 50, 1,3,1);
            case 2 -> c = new Consumabili("Forza +5", "Pozione di forza", "C", 5, 50, 1,3, 1);
            default -> c = new Consumabili("Grimaldello", "Apre serrature", "C", 0, 20, 1,3, 1);
        }
        this.p.setInventario(c);
        GameConsole.append("\n\n Hai trovato \"" + c.getNome() + "\" ed è stato aggiunto nell'inventario!");
        this.aggiornaInventario();
    }
    
    //Metodo per la scelta dell'evento casuale
    protected void sceltaRandom(){
        if(this.k == 0)
            this.baule();
        else{
            this.stato = eventi[rand.nextInt(this.eventi.length)];
            switch (this.stato) {
                case "Combattimento" -> this.combattimento();
                case "Baule" -> this.baule();
            }
        }
    }
    
    //Metodo per capire se il personaggio avrà un finale positivo o negativo
    protected void sceltaFinale(){
        this.s.disattivaSalvataggio();
        boolean finale[] = this.p.getScelteImp();
        
        for(int i = 0; i < this.p.i-1; i++){
            if(finale[i] == true)
                this.t++;
            else 
                this.f++;
        }
        
        if(this.t >= this.f)
            GameConsole.setText("\n ...in base ai tuoi comportamenti avrai, come promesso,\n la possibilità di tornare alla tua vecchia vita!");
        else
            GameConsole.setText("\n ...in base ai tuoi comportamenti...\n non avrai tua vecchia vita!");
        this.y++;
        this.p.setProgressilvl(y);
    }
    
    //Metodo per la scelta delle quest secondarie
    protected void questSecondarie(){
        this.s.disattivaSalvataggio();
        NegozioBT.setEnabled(false);
        dmNegozio.removeAllElements();
        NegozioBT.setText("NEGOZIO");
        VendiBT.setEnabled(false);
        CompraBT.setEnabled(false);
        ShopList.setEnabled(false);
        GameImgLabel.setVisible(false);
        if(this.stato.equals("Importante"))
        {
            switch(secondarie[this.rand.nextInt(2)]){
                case "Aiuta"->{
                    this.stato = "Aiuta";
                    GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Carro.png"));
                    GameImgLabel.setVisible(true);
                    GameConsole.setText(" Hai incontrato un anziano con un carro! \n Vuoi aiutarlo a spingerlo durante la salita??");
                    PlayerChoice.setListData(scelteSecondarie);
                }
                case "Dona"->{
                    this.stato = "Dona";
                    GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Bambina.png"));
                    GameImgLabel.setVisible(true);
                    GameConsole.setText(" Hai incontrato una bambina povera! \n Vuoi aiutarla donandogli 15 monete d'oro??");
                    PlayerChoice.setListData(scelteSecondarie);
                }
            }
        }
        else
        {
            switch(secondarie[this.rand.nextInt(this.secondarie.length)]){
                case "Aiuta"->{
                    this.stato = "Aiuta";
                    GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Carro.png"));
                    GameImgLabel.setVisible(true);
                    GameConsole.setText(" Hai incontrato un anziano con un carro! \n Vuoi aiutarlo a spingerlo durante la salita??");
                    PlayerChoice.setListData(scelteSecondarie);
                }
                case "Dona"->{
                    this.stato = "Dona";
                    GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Bambina.png"));
                    GameImgLabel.setVisible(true);
                    GameConsole.setText(" Hai incontrato una bambina povera! \n Vuoi aiutarla donandogli 15 monete d'oro??");
                    PlayerChoice.setListData(scelteSecondarie);
                }
                default ->{
                    this.stato = "Accumula";
                    GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Maestro.png"));
                    GameImgLabel.setVisible(true);
                    GameConsole.setText(" Vuoi allenarti con il maestro del villaggio " + "\nper migliorare la tua esperienza??");
                    PlayerChoice.setListData(scelteSecondarie);
                }
            }
        }
        this.y++;
        this.p.setProgressilvl(y);
    }
    
    //Metodo per la scelta dei Bauli
    protected void baule(){
        this.stato = "Baule";
        
        this.s.disattivaSalvataggio();
        NegozioBT.setEnabled(false);
        try{
            this.clipMusica.stop();
                
            this.fMusica = new File(this.pathMusica[3]);
            this.inputMusica = AudioSystem.getAudioInputStream(this.fMusica);
            this.clipMusica = AudioSystem.getClip();
            this.clipMusica.open(inputMusica);
            this.clipMusica.start();
            this.clipMusica.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){ }
        
        if(this.k == 0)
        {
            NegozioBT.setEnabled(true);
            GameConsole.setText(" Hai incontrato un baule " + this.bauli[1] + "!\n Scegli cosa fare:");
            PlayerChoice.setListData(scelteBauleR);
        }
        else
        {
            if(bauli[rand.nextInt(this.bauli.length)].contains(this.bauli[1]))
            {
                NegozioBT.setEnabled(true);
                GameConsole.setText(" Hai incontrato un baule " + this.bauli[1] + "!\n Scegli cosa fare:");
                PlayerChoice.setListData(scelteBauleR);
            }
            else
            {
                GameConsole.setText(" Hai incontrato un baule " + this.bauli[0] + "!\n Scegli cosa fare:");
                PlayerChoice.setListData(scelteBauleC);
            }
        }
        GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Baule_chiuso.png"));
        GameImgLabel.setVisible(true);
        this.y++;
        this.p.setProgressilvl(y);
    }
    
    //Metodo per avvio del combattimento
    protected void combattimento(){
        this.s.disattivaSalvataggio();
        
        if (this.v == true && this.stato.equals("Boss")){
            this.cont = 2;
            this.nemico = new Nemico(nomiNemici[this.nomiNemici.length-1],this.p.getLvlGiocatore());
        }
        else if(this.v == true && this.stato.equals("MiniBoss")){
            this.cont = 1;
            this.nemico = new Nemico(nomiNemici[this.nomiNemici.length-2], this.p.getLvlGiocatore());
        }
        else if(this.v == false || this.k==0){
            this.cont = 0;
           this.nemico = new Nemico("Goblin", this.p.getLvlGiocatore());
        }
        else{ 
            this.cont = 0;
            this.nemico = new Nemico(nomiNemici[rand.nextInt(this.nomiNemici.length-3)],this.p.getLvlGiocatore());
        }
        int music;
        if(this.stato.equals("Boss")){
            music = 4;
        }
        else
            music = 2;
        
        this.v = true;
        this.stato = "Combattimento";
        this.tipoA = this.nemico.getTipo();
       
        NegozioBT.setEnabled(false);
        dmNegozio.removeAllElements();
        NegozioBT.setText("NEGOZIO");
        VendiBT.setEnabled(false);
        CompraBT.setEnabled(false);
        ShopList.setEnabled(false);
        GameConsole.setText(this.nemico.getFraseComb());
        PlayerChoice.setListData(comb);
        this.aggiornaStatisticheCombattimento();
        sceltaNemico(this.nemico.nome);
        try{
            this.clipMusica.stop();
            
            this.fMusica = new File(this.pathMusica[music]);
            this.inputMusica = AudioSystem.getAudioInputStream(this.fMusica);
            this.clipMusica = AudioSystem.getClip();
            this.clipMusica.open(inputMusica);
            this.clipMusica.start();
            this.clipMusica.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){}
        this.y++;
        this.p.setProgressilvl(y);
    }
    
    //Metodo per la scelta del nemico
    protected void sceltaNemico(String nome){
        switch (nome) {
            case "Arpia" -> GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Arpia.png"));
            case "Grifone" -> GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Grifone.png"));
            case "Fantasma" -> GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Fantasma.png"));
            case "Demoni" -> GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Demoni.png"));
            case "Scheletro" -> GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Scheletro.png"));
            case "Licantropo" -> GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Licantropo.png"));
            case "Goblin" -> GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Goblin.png"));
            case "Gigante" -> GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Gigante.png"));
            case "Drago" -> GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Drago_nero.png"));
            default -> {
            }
        }
        StatNemico.setVisible(true);
        StatNemico.setBorder(null);
        StatNemico.setBackground(new Color(0,0,0,0));
        GameImg.setEnabled(false);
        GameImgLabel.setVisible(true);        
    }
    
    //Metodo per la gestione della morte del nemico
    @SuppressWarnings("empty-statement")
    protected void morteNemico(){
        GameConsole.setText("\n Il nemico è morto!\n");
        try{
            this.clipMusica.stop();
            this.fSuoni = new File(this.pathSuoni[7]);
            this.inputMusica = AudioSystem.getAudioInputStream(this.fSuoni);
            this.clipSuoni = AudioSystem.getClip();
            this.clipSuoni.open(inputMusica);
            this.clipSuoni.start();
        }
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){ }
        this.aggiornaStatistiche();
        StatNemico.setVisible(false);
        GameImg.setEnabled(true);
        GameImgLabel.setVisible(false);
        PlayerChoice.setListData(avanti);
        int x = this.p.getLvlGiocatore();
        this.p.setEsperienza(this.nemico.getEsperienza());
        int a = rand.nextInt(5, 16);
        this.p.setSoldi(a);
        GameConsole.append(" Hai ottenuto " + this.nemico.getEsperienza() + " punti esperienza e " + a + " di oro!");
        this.aggiornaStatistiche();
        if(x != this.p.getLvlGiocatore()){
            GameConsole.append("\n\n Adesso sei al livello " + this.p.getLvlGiocatore() + ", le tue statistiche\n"
                    + " sono migliorate e  la tua salute è stata ripristinata.");
        }
        
        //saltare le slide dal boss al finale
        if(this.cont == 2 && this.p.fuga == 0){
            while(this.scenario.get(this.y++).getTipo()!=Slide.TIPO.DOPO_BOSS.ordinal());
            this.y--;
            this.p.setProgressilvl(y);
        }
        
        NegozioBT.setEnabled(true);
        
        if(this.cont != 2){
            this.s.attivaSalvataggio();
            this.stato = " ";
        }else {
            this.s.disattivaSalvataggio();
            this.music = 5;
        }
    }
    
    //Metodo per la gestione della morte del personaggio
    protected void mortePersonaggio(){
        if(this.p.getSalute() <= 0){
            Statistiche.setText(" Nome: " +this.p.getNome() + "\n Livello: " + this.p.getLvlGiocatore() + "\n Salute: " + 0 + "\n Attacco " + this.tipoA + 
            ": " + this.tipoAttacco+ "\n Difesa: " + this.p.getArmatura());  
            if(this.p.getEquip()[0] == -1)
                Statistiche.append("\n\n Arma: ");
            else
                Statistiche.append("\n\n Arma: " + this.p.getInventario().get(this.p.getEquip()[0]).getNome());
            if(this.p.getEquip()[1] == -1)
                Statistiche.append("\n Armatura: ");
            else
                Statistiche.append("\n Armatura: " + this.p.getInventario().get(this.p.getEquip()[1]).getNome()); 
            StatNemico.setText("Nemico: " + this.nemico.getNome() + "\nLivello: "+ this.nemico.getLvl() + "\nSalute: " + this.nemico.salute);
            
            try{
                this.clipMusica.stop();

                this.fSuoni = new File(this.pathSuoni[6]);
                this.inputMusica = AudioSystem.getAudioInputStream(this.fSuoni);
                this.clipSuoni = AudioSystem.getClip();
                this.clipSuoni.open(inputMusica);
                this.clipSuoni.start();
            }
            catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){ }
            Object[] options = { "OK" };
            JOptionPane.showOptionDialog(null, "Sei morto. Verrai mandato al Menù Principale per riprendere dall'ultimo salvataggio!", "ATTENZIONE!",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
            null,options, options[0]);
                try{Thread.sleep(1000);}catch(InterruptedException e){}
                GamePanel.setVisible(false);
                this.s.interrupt();
                this.clipMusica.stop();
                this.clipMusica.close();
                new MenuForm().setVisible(true);
                dispose();
        }
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GamePanel = new javax.swing.JPanel();
        GameConsole = new javax.swing.JTextArea();
        AttFisField = new javax.swing.JTextField();
        AttMagField = new javax.swing.JTextField();
        ArmaturaField = new javax.swing.JTextField();
        Statistiche = new javax.swing.JTextArea();
        NegozioBT = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShopList = new javax.swing.JList<>();
        PlayerChoice = new javax.swing.JList<>();
        InventoryList = new javax.swing.JList<>();
        Abilità = new javax.swing.JLabel();
        StatNemico = new javax.swing.JTextArea();
        GameImgLabel = new javax.swing.JLabel();
        GameImg = new javax.swing.JButton();
        ExitBT = new javax.swing.JButton();
        VendiBT = new javax.swing.JButton();
        CompraBT = new javax.swing.JButton();
        ClasseIco = new javax.swing.JLabel();
        InventarioField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Seconda Chance");
        setResizable(false);

        GamePanel.setBackground(new java.awt.Color(0, 51, 102));
        GamePanel.setEnabled(false);
        GamePanel.setPreferredSize(new java.awt.Dimension(1050, 640));
        GamePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GameConsole.setEditable(false);
        GameConsole.setBackground(new java.awt.Color(0, 0, 0));
        GameConsole.setColumns(20);
        GameConsole.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        GameConsole.setForeground(new java.awt.Color(255, 255, 255));
        GameConsole.setRows(5);
        GameConsole.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));
        GamePanel.add(GameConsole, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 339, 491, 161));

        AttFisField.setEditable(false);
        AttFisField.setBackground(new java.awt.Color(0, 0, 0));
        AttFisField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AttFisField.setForeground(new java.awt.Color(102, 255, 102));
        AttFisField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AttFisField.setBorder(null);
        GamePanel.add(AttFisField, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 147, 40, 30));

        AttMagField.setEditable(false);
        AttMagField.setBackground(new java.awt.Color(0, 0, 0));
        AttMagField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AttMagField.setForeground(new java.awt.Color(102, 255, 102));
        AttMagField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AttMagField.setBorder(null);
        AttMagField.setPreferredSize(new java.awt.Dimension(64, 22));
        GamePanel.add(AttMagField, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 167, 40, 30));

        ArmaturaField.setEditable(false);
        ArmaturaField.setBackground(new java.awt.Color(0, 0, 0));
        ArmaturaField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ArmaturaField.setForeground(new java.awt.Color(102, 255, 102));
        ArmaturaField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ArmaturaField.setBorder(null);
        GamePanel.add(ArmaturaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 187, 40, 30));

        Statistiche.setEditable(false);
        Statistiche.setBackground(new java.awt.Color(0, 0, 0));
        Statistiche.setColumns(20);
        Statistiche.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Statistiche.setForeground(new java.awt.Color(255, 255, 255));
        Statistiche.setRows(5);
        Statistiche.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));
        GamePanel.add(Statistiche, new org.netbeans.lib.awtextra.AbsoluteConstraints(593, 49, 192, 261));

        NegozioBT.setBackground(new java.awt.Color(51, 153, 0));
        NegozioBT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        NegozioBT.setForeground(new java.awt.Color(255, 255, 255));
        NegozioBT.setText("NEGOZIO");
        NegozioBT.setEnabled(false);
        NegozioBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NegozioBTActionPerformed(evt);
            }
        });
        GamePanel.add(NegozioBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(593, 339, 419, -1));

        ShopList.setBackground(new java.awt.Color(0, 0, 0));
        ShopList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));
        ShopList.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ShopList.setForeground(new java.awt.Color(255, 255, 255));
        ShopList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ShopList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ShopList.setEnabled(false);
        ShopList.setSelectionBackground(new java.awt.Color(102, 153, 0));
        jScrollPane1.setViewportView(ShopList);

        GamePanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(593, 368, 419, 192));

        PlayerChoice.setBackground(new java.awt.Color(0, 0, 0));
        PlayerChoice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));
        PlayerChoice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PlayerChoice.setForeground(new java.awt.Color(255, 255, 255));
        PlayerChoice.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Si", "No", "Forse" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        PlayerChoice.setSelectionBackground(new java.awt.Color(102, 153, 0));
        PlayerChoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayerChoiceMouseClicked(evt);
            }
        });
        GamePanel.add(PlayerChoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 506, 491, 81));

        InventoryList.setBackground(new java.awt.Color(0, 0, 0));
        InventoryList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));
        InventoryList.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        InventoryList.setForeground(new java.awt.Color(255, 255, 255));
        InventoryList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        InventoryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        InventoryList.setSelectionBackground(new java.awt.Color(102, 153, 0));
        InventoryList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventoryListMouseClicked(evt);
            }
        });
        GamePanel.add(InventoryList, new org.netbeans.lib.awtextra.AbsoluteConstraints(807, 48, 205, 232));

        Abilità.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Abilità.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AbilitàMouseClicked(evt);
            }
        });
        GamePanel.add(Abilità, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, 100, 80));

        StatNemico.setEditable(false);
        StatNemico.setBackground(new java.awt.Color(0, 0, 0));
        StatNemico.setColumns(20);
        StatNemico.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        StatNemico.setForeground(new java.awt.Color(255, 255, 255));
        StatNemico.setRows(5);
        StatNemico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));
        GamePanel.add(StatNemico, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 160, 110));

        GameImgLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GameImgLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        GamePanel.add(GameImgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 350, 250));

        GameImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 3));
        GameImg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        GameImg.setFocusable(false);
        GamePanel.add(GameImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 20, 491, 290));

        ExitBT.setBackground(new java.awt.Color(153, 0, 51));
        ExitBT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ExitBT.setForeground(new java.awt.Color(255, 255, 255));
        ExitBT.setText("MENU PRINCIPALE");
        ExitBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 3));
        ExitBT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ExitBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBTActionPerformed(evt);
            }
        });
        GamePanel.add(ExitBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(593, 20, 192, -1));

        VendiBT.setBackground(new java.awt.Color(51, 153, 0));
        VendiBT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        VendiBT.setForeground(new java.awt.Color(255, 255, 255));
        VendiBT.setText("VENDI");
        VendiBT.setEnabled(false);
        VendiBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VendiBTActionPerformed(evt);
            }
        });
        GamePanel.add(VendiBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(807, 286, 205, 24));

        CompraBT.setBackground(new java.awt.Color(51, 153, 0));
        CompraBT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CompraBT.setForeground(new java.awt.Color(255, 255, 255));
        CompraBT.setText("COMPRA");
        CompraBT.setEnabled(false);
        CompraBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompraBTActionPerformed(evt);
            }
        });
        GamePanel.add(CompraBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(593, 561, 419, 26));

        ClasseIco.setText("Classe");
        ClasseIco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 102), 3));
        GamePanel.add(ClasseIco, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 51, 51));

        InventarioField.setEditable(false);
        InventarioField.setBackground(new java.awt.Color(0, 0, 0));
        InventarioField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        InventarioField.setForeground(new java.awt.Color(255, 255, 255));
        InventarioField.setText("                    INVENTARIO");
        InventarioField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));
        GamePanel.add(InventarioField, new org.netbeans.lib.awtextra.AbsoluteConstraints(807, 20, 205, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Metodo per ritornare al menù principale dalla schermata di gioco
    private void ExitBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBTActionPerformed
        Object[] options = {"SI","NO"};
        int n = JOptionPane.showOptionDialog(rootPane, "Vuoi tornare al menu principale?",
        "ESCI",JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        
        if (n==JOptionPane.YES_OPTION)
        {
            this.s.interrupt();

            this.clipMusica.stop();
            this.clipMusica.close();
            try{
                this.inputMusica.close();
            }catch(IOException ex){ }

            new MenuForm().setVisible(true);
            dispose();
            
        }
    }//GEN-LAST:event_ExitBTActionPerformed

    //Metodo per il bottone vendi del negozio
    private void VendiBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VendiBTActionPerformed
        if(!InventoryList.isSelectionEmpty()){
            Object[] options = {"SI","NO"};
            int n = JOptionPane.showOptionDialog(rootPane, "Sicuro di voler vendere questo oggetto?",
            "VENDI",JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        
            if (n==JOptionPane.YES_OPTION){
                
                Oggetti o = (Oggetti)dmInventario.getElementAt(InventoryList.getSelectedIndex());
                this.p.setSoldi(o.getCosto());
                GameConsole.setText(o.getNome() + " Venduto per " + o.getCosto() + " Oro.");
                this.n.vendi(o);
                
                try{
                    this.fSuoni = new File(this.pathSuoni[8]);
                    this.inputMusica = AudioSystem.getAudioInputStream(this.fSuoni);
                    this.clipSuoni = AudioSystem.getClip();
                    this.clipSuoni.open(inputMusica);
                    this.clipSuoni.start();
                }
                catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){}
                
                this.p.eliminaInventario(this.p.getInventario().indexOf(o));
                
                
                this.aggiornaStatistiche();
                this.aggiornaInventario();
                this.aggiornaNegozio();
            }
        }
    }//GEN-LAST:event_VendiBTActionPerformed

    //Metodo per l'apertura e la chiusura del negozio
    private void NegozioBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NegozioBTActionPerformed
        if(NegozioBT.getText().equals("NEGOZIO") == true){
            this.testo = GameConsole.getText();
            this.s.disattivaSalvataggio();
            try{
                this.clipMusica.stop();
                
                this.fSuoni = new File(this.pathSuoni[0]);
                this.inputMusica = AudioSystem.getAudioInputStream(this.fSuoni);
                this.clipSuoni = AudioSystem.getClip();
                this.clipSuoni.open(inputMusica);
                this.clipSuoni.start();
                
                this.fMusica = new File(this.pathMusica[1]);
                this.inputMusica = AudioSystem.getAudioInputStream(this.fMusica);
                this.clipMusica = AudioSystem.getClip();
                this.clipMusica.open(inputMusica);
                this.clipMusica.start();
                this.clipMusica.loop(Clip.LOOP_CONTINUOUSLY);
            }
            catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){ }

            NegozioBT.setText("ESCI");
            VendiBT.setEnabled(true);
            CompraBT.setEnabled(true);
            ShopList.setEnabled(true);

            this.aggiornaNegozio();
        }else{
            this.s.attivaSalvataggio();
            try{
                this.clipMusica.stop();
                
                this.fSuoni = new File(this.pathSuoni[0]);
                this.inputMusica = AudioSystem.getAudioInputStream(this.fSuoni);
                this.clipSuoni = AudioSystem.getClip();
                this.clipSuoni.open(inputMusica);
                this.clipSuoni.start();
                
                this.fMusica = new File(this.pathMusica[this.stato.equals("Baule")? 3 : this.music]);
                this.inputMusica = AudioSystem.getAudioInputStream(this.fMusica);
                this.clipMusica = AudioSystem.getClip();
                this.clipMusica.open(inputMusica);
                this.clipMusica.start();
                this.clipMusica.loop(Clip.LOOP_CONTINUOUSLY);
            }
            catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){ }
            
            dmNegozio.removeAllElements();
            NegozioBT.setText("NEGOZIO");
            VendiBT.setEnabled(false);
            CompraBT.setEnabled(false);
            ShopList.setEnabled(false);
            
            GameConsole.setText(this.testo);
        }
    }//GEN-LAST:event_NegozioBTActionPerformed

    //Metodo per la gestione delle scelte del giocatore dal pannello di gioco
    private void PlayerChoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayerChoiceMouseClicked
        //Fase avanti del gioco
        if(PlayerChoice.getSelectedValue().contains("Avanti")){
            this.dmNegozio.removeAllElements();
            NegozioBT.setText("NEGOZIO");
            VendiBT.setEnabled(false);
            CompraBT.setEnabled(false);
            ShopList.setEnabled(false);
            
            if(((GameConsole.getText().contains("esperienza") && !this.stato.equals("Accumula"))||
                    GameConsole.getText().contains("fuggito")||GameConsole.getText().contains("aperto")||
                    GameConsole.getText().contains("ignorato")||GameConsole.getText().contains("aiutato")||
                    GameConsole.getText().contains("Allenandoti")||GameConsole.getText().contains("Raro")))
            {
                if(this.stato.equals("Baule"))
                    this.stato = " ";
                if(!this.fMusica.getName().equals("Main.wav") || !this.fMusica.getName().equals("Intro.wav"))
                {
                    try{
                        if(this.clipSuoni.isRunning())
                            this.clipSuoni.stop();
                        this.clipMusica.stop();

                        this.fMusica = new File(this.pathMusica[this.music]);
                        this.inputMusica = AudioSystem.getAudioInputStream(this.fMusica);
                        this.clipMusica = AudioSystem.getClip();
                        this.clipMusica.open(inputMusica);
                        this.clipMusica.start();
                        this.clipMusica.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){}
                }
                
                if(this.cont != 2)
                    this.s.attivaSalvataggio();
                else
                    this.s.disattivaSalvataggio();
                
                NegozioBT.setEnabled(true);
            }
            
            
            if(this.y == this.scenario.size())
            {
                GameImgLabel.setVisible(false);
                this.k++;
                this.p.setLvlGioco(k);
                this.y = 0;
                this.p.setProgressilvl(y);
                
                this.scenario = Livello1();
                this.music = 0;
                try{
                    this.clipMusica.stop();

                    this.fMusica = new File(this.pathMusica[this.music]);
                    this.inputMusica = AudioSystem.getAudioInputStream(this.fMusica);
                    this.clipMusica = AudioSystem.getClip();
                    this.clipMusica.open(inputMusica);
                    this.clipMusica.start();
                    this.clipMusica.loop(Clip.LOOP_CONTINUOUSLY);
                }
                catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){ }
                this.s.attivaSalvataggio();
                
                /*
                    Qui andrebbe uno switch per avanzare di livello, ma visto che facciamo solo il primo non serve.
                */
                
                if(this.k == 2){
                    this.s.disattivaSalvataggio();
                    Object[] options = { "OK" };
                    JOptionPane.showOptionDialog(null, "CONGRATULAZIONI!\nIl Team ARGV ti ringrazia per aver giocato.", "RICONOSCIMENTI",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null,options, options[0]);
                    try{Thread.sleep(1000);}catch(InterruptedException e){}
                    GamePanel.setVisible(false);
                    this.s.interrupt();
                    this.clipMusica.stop();
                    this.clipMusica.close();
                    new MenuForm().setVisible(true);
                    dispose();
                }
                else
                {
                    GameConsole.setText(scenario.get(y).getTesto());
                    GameImgLabel.setIcon(new ImageIcon(scenario.get(y).getImg()));
                    GameImgLabel.setVisible(true);
                    GameImg.setIcon(new ImageIcon());
                    GameImg.setIcon(new ImageIcon(this.scenario.get(y).getSfondoImg()));
                      
                    this.y++;
                    this.p.setProgressilvl(this.y);   
                }
            }
            else{
                switch(this.scenario.get(y).getTipo()){
                    case 0 -> {               
                        GameConsole.setText(scenario.get(y).getTesto());
                        GameImgLabel.setIcon(new ImageIcon(scenario.get(y).getImg()));
                        GameImgLabel.setVisible(true);
                        this.y++;
                        this.p.setProgressilvl(y);
                    }
                    case 1 -> this.sceltaRandom();
                    case 2 -> this.combattimento();
                    case 3 ->{
                        this.stato = "Importante";
                        this.questSecondarie();
                    }
                    case 4 ->{
                        this.stato = "MiniBoss";
                        this.combattimento();
                    }
                    case 5 ->{
                        this.stato = "Boss";
                        this.combattimento();
                    }
                    case 6->{
                        GameConsole.setText(scenario.get(y).getTesto());
                        GameImgLabel.setIcon(new ImageIcon(scenario.get(y).getImg()));
                        GameImgLabel.setVisible(true);
                        this.NegozioBT.setEnabled(true);
                        
                        if(this.k == 0 && this.y == 2){
                            Oggetti o = this.n.listConsumabili.get(3);
                            Consumabili c = (Consumabili) o;
                            if(!this.p.inventario.contains(c)){
                                this.p.setInventario(c);
                                this.n.acquista(c);
                                this.aggiornaInventario();
                            }
                        }
                        
                        this.y++;
                        this.p.setProgressilvl(y);
                    }
                    case 7 -> this.baule();
                    case 8 -> {
                        GameConsole.setText(scenario.get(y).getTesto());
                        GameImgLabel.setIcon(new ImageIcon(scenario.get(y).getImg()));
                        GameImgLabel.setVisible(true);
                        GameImg.setIcon(new ImageIcon());
                        GameImg.setIcon(new ImageIcon(this.scenario.get(y).getSfondoImg()));
                        
                        this.y++;
                        this.p.setProgressilvl(this.y);
                    }
                    case 9 -> {
                        GameConsole.setText(scenario.get(y).getTesto());
                        GameImgLabel.setIcon(new ImageIcon(scenario.get(y).getImg()));
                        GameImgLabel.setVisible(true);
                        
                        this.y++;
                        this.p.setProgressilvl(this.y);
                        
                        try{
                            this.clipMusica.stop();

                            this.fMusica = new File(this.pathMusica[5]);
                            this.inputMusica = AudioSystem.getAudioInputStream(this.fMusica);
                            this.clipMusica = AudioSystem.getClip();
                            this.clipMusica.open(inputMusica);
                            this.clipMusica.start();
                            this.clipMusica.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                        catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){}
                    }
                    case 10 ->{ 
                        GameConsole.setText(scenario.get(y).getTesto());
                        GameImgLabel.setIcon(new ImageIcon(scenario.get(y).getImg()));
                        GameImgLabel.setVisible(true);
                        this.sceltaFinale();                    
                    }
                    case 11 ->{
                        this.p.setSoldi(15);
                        this.aggiornaStatistiche();
                        GameConsole.setText(scenario.get(y).getTesto());
                        GameImgLabel.setIcon(new ImageIcon(scenario.get(y).getImg()));
                        GameImgLabel.setVisible(true);
                        this.NegozioBT.setEnabled(true);
                        this.y++;
                        this.p.setProgressilvl(this.y);
                    }
                    default ->{}
                }
            }
        }
        
        //Fase scassina dei bauli rari
        else if(PlayerChoice.getSelectedValue().contains("Scassina")){
            GameConsole.setText(" Hai incontrato un baule " + this.bauli[1] + "!\n Scegli cosa fare:");
            
            int b;
            boolean check = false;
            for(int i = 0; i < this.p.getInventario().size(); i++){
                if(this.p.getInventario().get(i).getNome().equals("Grimaldello")){
                    GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Baule_aperto.png"));
                    this.p.eliminaInventario(i);
                    this.aggiornaInventario();
                    int r = rand.nextInt(6);
                    if(r == 5 || r == 6)
                        this.dropConsumabili();
                    else
                    {
                        b = rand.nextInt(20, 41);
                        this.p.setSoldi(b);
                        GameConsole.append("\n\n All'interno hai trovato " + b + " di oro!" );
                        this.aggiornaStatistiche();
                    }
                    check = true;
                    this.s.attivaSalvataggio();
                    PlayerChoice.setListData(this.avanti);
                    break;
                }
            }
            if(check == false) {
                GameConsole.setText(" Hai incontrato un baule " + this.bauli[1] + "!\n Scegli cosa fare:");
                GameConsole.append("\n\n Non hai grimaldelli. Passa dal negozio!");
                PlayerChoice.setListData(this.scelteBauleR);
            }
        }
        
        //Fase ignora dei bauli
        else if(PlayerChoice.getSelectedValue().contains("Ignora")){
            GameConsole.append("\n\n Hai ignorato il baule!");
            GameImgLabel.setVisible(false);
            NegozioBT.setEnabled(true);
            PlayerChoice.setListData(avanti);
            this.s.attivaSalvataggio();
        }
        
        //Fase apertura baule comune
        else if(PlayerChoice.getSelectedValue().contains("Apri")){
            GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Baule_aperto.png"));
            GameConsole.append("\n\n Hai aperto il baule!");
            switch (sceltaApri[rand.nextInt(this.sceltaApri.length)]) {
                case "Combattimento" -> {
                    this.v = false;
                    PlayerChoice.setListData(comb);
                    GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Goblin.png"));
                    this.combattimento();
                }
                case "Trappola" -> {
                    GameImgLabel.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Esplosione.png"));
                    this.p.setSalute(-20);
                    GameConsole.append("\n\n Hai trovato una trappola. Hai subito " + 20 + " di danno!" );
                    this.aggiornaStatistiche();
                    PlayerChoice.setListData(avanti);
                    NegozioBT.setEnabled(true);
                    this.s.attivaSalvataggio();
                }
                default -> {
                    int a = rand.nextInt(5, 16);
                    this.p.setSoldi(a);
                    GameConsole.append("\n\n All'interno hai trovato " + a + " di oro!" );
                    PlayerChoice.setListData(avanti);
                    this.aggiornaStatistiche();
                    GameImgLabel.setVisible(false);
                    NegozioBT.setEnabled(true);
                    this.s.attivaSalvataggio();
                }
            }
        }
        
        //Scelta "Si" delle quest secondarie
        else if(PlayerChoice.getSelectedValue().contains("Si")){
            switch(this.stato){
            case "Aiuta"->{
                GameConsole.append("\n\n Complimenti. Hai aiutatato il signore.\n In futuro potrebbe esserti utile!");
                this.p.setScelteImp(true);
            }
            case "Dona"->{
                if(this.p.getSoldi() >= 15)
                {
                    GameConsole.append("\n\n Complimenti. Hai aiutatato la bambina.\n In futuro potrebbe esserti utile!");
                    this.p.setScelteImp(true);
                    this.p.setSoldi(-15);
                    this.aggiornaStatistiche();
                }
                else
                    GameConsole.append("\n\n Purtroppo non hai abbastanza monete per poterla aiutare!");
            }
            default ->{
                GameConsole.append("\n\n Complimenti. Allenandoti hai guadagnato 5 punti esperienza!");
                this.p.setEsperienza(5);
                this.aggiornaStatistiche();
            }
        }
        
            GameImgLabel.setVisible(false);
            PlayerChoice.setListData(avanti);
            NegozioBT.setEnabled(true);
            this.s.attivaSalvataggio();
        }
        
        //Scelta "No" delle quest secondarie
        else if(PlayerChoice.getSelectedValue().contains("No")){
            switch(this.stato){
                case "Aiuta"->{
                    GameConsole.append("\n\n Peccato. Non hai aiutatato il signore.\n In futuro potresti pentirtene!");
                    this.p.setScelteImp(false);
                }
                case "Dona"->{
                    GameConsole.append("\n\n Peccato. Non hai aiutatato la bambina.\n In futuro potresti pentirtene!");
                    this.p.setScelteImp(false);
                }
                default ->                    
                    GameConsole.append("\n\n Peccato. Hai perso un'occasione per migliorare!");
            }
            
            GameImgLabel.setVisible(false);
            PlayerChoice.setListData(avanti);
            NegozioBT.setEnabled(true);
            this.s.attivaSalvataggio();
        }
        
        //Fase difesa del combattimento
        else if(PlayerChoice.getSelectedValue().contains("Difenditi")){
            if(this.j == 2){
                this.j = 0;
                GameConsole.setText("\n La tua mossa:");
                GameConsole.append("\n\tTi sei difeso già due volte! Stai attaccando il nemico!");
                try{
                    this.fSuoni = new File(this.pathSuoni[2]);
                    this.inputMusica = AudioSystem.getAudioInputStream(this.fSuoni);
                    this.clipSuoni = AudioSystem.getClip();
                    this.clipSuoni.open(inputMusica);
                    this.clipSuoni.start();
                }
                catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){}
                if(rand.nextInt(2) == 0){ 
                    this.choice = true;
                    this.i = 0;
                    GameConsole.append("\n Mossa del nemico:");
                    GameConsole.append("\n\tSta attaccando!\n");
                    this.p.diminuisciSalute(this.nemico.attacco);
                    this.mortePersonaggio();
                    if(this.p.getSalute() > 0){
                        this.nemico.setSalute(this.tipoAttacco);
                        this.aggiornaStatisticheCombattimento();
                        if(this.nemico.getSalute() <= 0)
                            this.morteNemico();
                    } 
                }
                else
                {
                    this.choice = false;
                    this.i++;
                    GameConsole.append("\n Mossa del nemico:");
                    GameConsole.append("\n\tSi sta difendendo!\n");
                }
            }
            else
            {
                this.j++;
                GameConsole.setText("\n La tua mossa:");
                GameConsole.append("\n\tTi sei difeso!");
                try{
                    this.fSuoni = new File(this.pathSuoni[1]);
                    this.inputMusica = AudioSystem.getAudioInputStream(this.fSuoni);
                    this.clipSuoni = AudioSystem.getClip();
                    this.clipSuoni.open(inputMusica);
                    this.clipSuoni.start();
                }
                catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){}
                if(rand.nextInt(2) == 0){
                    this.choice = true;
                    this.i = 0;
                    GameConsole.append("\n Mossa del nemico:");
                    GameConsole.append("\n\tSta attaccando!\n");  
                }
                else
                {
                    if(this.i == 2){
                        this.choice = true;
                        this.i = 0;
                        GameConsole.append("\n Mossa del nemico:");
                        GameConsole.append("\n\tSta attaccando!\n"); 
                    }
                    else
                    {
                        this.choice = false;
                        this.i++;
                        GameConsole.append("\n Mossa del nemico:");
                        GameConsole.append("\n\tSi sta difendendo!\n");                    
                    }
                }
            }
        }
        
        //Fase attacco del combattimento
        else if(PlayerChoice.getSelectedValue().contains("Attacca")){
            this.j=0;
            GameConsole.setText("\n La tua mossa:");
            GameConsole.append("\n\tHai attaccato!");
            try{
                    this.fSuoni = new File(this.pathSuoni[2]);
                    this.inputMusica = AudioSystem.getAudioInputStream(this.fSuoni);
                    this.clipSuoni = AudioSystem.getClip();
                    this.clipSuoni.open(inputMusica);
                    this.clipSuoni.start();
            }
            catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){}
            if(rand.nextInt(2) == 0){
                this.choice = true;
                this.i = 0;
                GameConsole.append("\n Mossa del nemico:");
                GameConsole.append("\n\tSta attaccando!\n");
                this.p.diminuisciSalute(this.nemico.attacco);
                this.mortePersonaggio();
                if(this.p.getSalute() > 0){
                    this.nemico.setSalute(this.tipoAttacco);
                    this.aggiornaStatisticheCombattimento();
                    if(this.nemico.getSalute() <= 0)
                        this.morteNemico();
                } 
            }
            else
            {
                if(this.i == 2){
                    this.choice = true;
                    this.i = 0;
                    GameConsole.append("\n Mossa del nemico:");
                    GameConsole.append("\n\tSta attaccando!\n");
                    this.p.diminuisciSalute(this.nemico.attacco);
                    this.mortePersonaggio();
                    if(this.p.getSalute() > 0){
                        this.nemico.setSalute(this.tipoAttacco);
                        this.aggiornaStatisticheCombattimento();
                        if(this.nemico.getSalute() <= 0)
                            this.morteNemico();
                    } 
                }
                else
                {
                    this.choice = false;
                    this.i++;
                    GameConsole.append("\n Mossa del nemico:");
                    GameConsole.append("\n\tSi sta difendendo!\n");
                }
            }
        }
        
        //Fase fuggi del combattimento
        else if(PlayerChoice.getSelectedValue().contains("Fuggi")){
            if(this.k != 0){
                switch(this.cont){
                    case 1 ->
                        GameConsole.setText(" Non puoi fuggire da questo combattimento!");
                    
                    case 2 ->{
                        if(this.p.fuga == 0){
                            this.p.fuga = 1;
                            this.stato = "Fuga";
                            GameConsole.setText("\n Sei fuggito dal combattimento!");
                    
                            this.clipMusica.stop();
                            try{
                                this.fSuoni = new File(this.pathSuoni[4]);
                                this.inputMusica = AudioSystem.getAudioInputStream(this.fSuoni);
                                this.clipSuoni = AudioSystem.getClip();
                                this.clipSuoni.open(inputMusica);
                                this.clipSuoni.start();
                            }
                            catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){}
                    
                            this.s.attivaSalvataggio();
                            this.aggiornaStatistiche();
                            this.stato = " ";
                            GameImg.setEnabled(true);
                            StatNemico.setVisible(false);
                            GameImgLabel.setVisible(false);
                            NegozioBT.setEnabled(true);
                            PlayerChoice.setListData(avanti);
                        }
                        else
                            GameConsole.setText(" Non puoi fuggire da questo combattimento!");
                    }
                    
                    default->{
                        if((rand.nextInt(101)) < this.p.fortuna){
                            this.stato = "Fuga";
                            GameConsole.setText("\n Sei fuggito dal combattimento!");
                    
                            this.clipMusica.stop();
                            try{
                                this.fSuoni = new File(this.pathSuoni[4]);
                                this.inputMusica = AudioSystem.getAudioInputStream(this.fSuoni);
                                this.clipSuoni = AudioSystem.getClip();
                                this.clipSuoni.open(inputMusica);
                                this.clipSuoni.start();
                            }
                            catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){}
                    
                            this.s.attivaSalvataggio();
                            this.aggiornaStatistiche();
                            GameImg.setEnabled(true);
                            StatNemico.setVisible(false);
                            GameImgLabel.setVisible(false);
                            NegozioBT.setEnabled(true);
                            PlayerChoice.setListData(avanti);
                        }
                        else{
                            GameConsole.setText("\n La tua mossa:");
                            GameConsole.append("\n\tNon sei riuscito a fuggire dal combattimento!");
                            this.choice = true;
                            this.i = 0;
                            GameConsole.append("\n Mossa del nemico:");
                            GameConsole.append("\n\tSta attaccando!\n");
                    
                            try{
                                this.fSuoni = new File(this.pathSuoni[5]);
                                this.inputMusica = AudioSystem.getAudioInputStream(this.fSuoni);
                                this.clipSuoni = AudioSystem.getClip();
                                this.clipSuoni.open(inputMusica);
                                this.clipSuoni.start();
                            }
                            catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){}
                    
                            this.p.diminuisciSalute(this.nemico.attacco);
                            this.mortePersonaggio();
                            this.aggiornaStatisticheCombattimento();
                        }
                    }
                }
            }
            else
                GameConsole.setText(" Non puoi fuggire da questo combattimento!");
        }
    }//GEN-LAST:event_PlayerChoiceMouseClicked

    //Metodo per comprare gli oggetti dal negozio
    private void CompraBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompraBTActionPerformed
        if(ShopList.getSelectedIndex() != 0 &&
                    ShopList.getSelectedIndex() != 1+countArmature && 
                    ShopList.getSelectedIndex() != 2+countArmature+countArmi 
                    && ShopList.getSelectedValue()!= null){
            Object[] options = {"SI","NO"};
            int n = JOptionPane.showOptionDialog(rootPane, "Sicuro di voler comprare questo oggetto?",
            "COMPRA",JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

            if (n==JOptionPane.YES_OPTION){
                Oggetti o = (Oggetti)dmNegozio.getElementAt(ShopList.getSelectedIndex());
                if(this.p.getSoldi() >= o.getCosto()){
                    GameConsole.setText(o.getNome() + " Comprato per " + o.getCosto() + " Oro.");
                    
                    if(o.getId() == 3){
                        Consumabili c = (Consumabili) o;
                        if(c.getQuantità() > 0){
                            this.p.setSoldi(-o.getCosto());
                            this.p.setInventario(c);
                            this.n.acquista(c);
                            
                            try{
                                this.fSuoni = new File(this.pathSuoni[8]);
                                this.inputMusica = AudioSystem.getAudioInputStream(this.fSuoni);
                                this.clipSuoni = AudioSystem.getClip();
                                this.clipSuoni.open(inputMusica);
                                this.clipSuoni.start();
                            }
                            catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){}
                            
                        }
                        else
                        {
                            Object[] ok = {"OK"};
                            int m = JOptionPane.showOptionDialog(rootPane, "Scorte finite!",
                            "CONSUMABILI",JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE,null,ok,ok[0]);
                       }
                    }
                    else
                    {
                        this.p.setSoldi(-o.getCosto());
                        this.p.setInventario(o);
                        this.n.acquista(o);
                        
                        try{
                            this.fSuoni = new File(this.pathSuoni[8]);
                            this.inputMusica = AudioSystem.getAudioInputStream(this.fSuoni);
                            this.clipSuoni = AudioSystem.getClip();
                            this.clipSuoni.open(inputMusica);
                            this.clipSuoni.start();
                        }
                        catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){}
                        
                    }
                    this.aggiornaStatistiche();
                    this.aggiornaInventario();
                    this.aggiornaNegozio();
                }
                else
                {
                    Object[] ok = {"OK"};
                    int m = JOptionPane.showOptionDialog(rootPane, "Non hai abbastanza soldi!",
                    "FONDI INSUFFICIENTI",JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,null,ok,ok[0]);
                }
            }
        }
    }//GEN-LAST:event_CompraBTActionPerformed

    //Metodo per gestire i click sull'Inventario
    private void InventoryListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryListMouseClicked
        if(!InventoryList.isSelectionEmpty() && NegozioBT.getText() != "ESCI"){
            Oggetti o = (Oggetti) dmInventario.getElementAt(InventoryList.getSelectedIndex());
            this.p.setEquip(o);
            if(o.getId() == 3){
                Consumabili c = (Consumabili) o;
                switch(o.getNome()){
                    case "Salute +15" -> {
                        if(this.p.getSalute() == this.p.getMaxSalute())
                            GameConsole.setText("\n La tua salute è al massimo\n");
                        else{
                            GameConsole.append("\n Ti sei curato di "+c.getBonus()+" salute\n");
                            this.p.eliminaInventario(this.p.getInventario().indexOf(c));
                            this.p.setSalute(c.getBonus());
                        }                       
                    }
                    case "Magia +5" -> {
                        this.p.setAttMagico(5);
                        this.p.eliminaInventario(this.p.getInventario().indexOf(c));
                        GameConsole.append("\n La tua forza magica è aumentata di 5!");
                    }
                    case "Forza +5" -> {
                        this.p.setAttFisico(5);
                        this.p.eliminaInventario(this.p.getInventario().indexOf(c));
                        GameConsole.append("\n La tua forza fisica è aumentata di 5!");
                    }
                }
                
            } 
            if(this.stato.equals("Combattimento"))
                this.aggiornaStatisticheCombattimento();
            else
                this.aggiornaStatistiche();
            this.aggiornaInventario();
        }
    }//GEN-LAST:event_InventoryListMouseClicked

    //Metodo per attivare l'abilità del personaggio
    private void AbilitàMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AbilitàMouseClicked
        if(this.p.getA() == false && this.k != 0)
        {
            Object[] options = {"SI","NO"};
            int n = JOptionPane.showOptionDialog(rootPane, "Sicuro di voler usare l'abilità?",
            "ABILITÀ",JOptionPane.YES_OPTION,
            JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

            if (n==JOptionPane.YES_OPTION)
            {
                if(this.p.abilità == 1)
                {
                    this.p.setSalute(this.p.getMaxSalute());
                }
                if(this.p.abilità == 2)
                {
                    if(this.p.getSalute() > 20){
                        this.p.setAttMagico(+5);
                        this.p.setAttFisico(+5);
                        this.p.setSalute(-20);
                        this.p.setArmatura(-5);
                    }
                    else
                    {
                        Object[] ok = {"OK"};
                        int m = JOptionPane.showOptionDialog(rootPane, "Salute troppo bassa per poter usare l'abilità!",
                        "ABILITÀ",JOptionPane.OK_OPTION,
                        JOptionPane.WARNING_MESSAGE,null,ok,ok[0]);
                    }
                }
                if(this.p.abilità == 3)
                {                    
                    Oggetti o = this.n.listConsumabili.get(rand.nextInt(4));
                    Consumabili c = (Consumabili) o; 
                    this.p.setInventario(c);
                    this.n.acquista(c);
                }
                this.p.setA(true);
                this.aggiornaInventario();
                if(this.stato.equals("Combattimento"))
                    this.aggiornaStatisticheCombattimento();
                else
                    this.aggiornaStatistiche();
            }                
        }
        else
        {
                Object[] ok = {"OK"};
                int m = JOptionPane.showOptionDialog(rootPane, "Abilità già usata!",
                "ABILITÀ",JOptionPane.OK_OPTION,
                JOptionPane.WARNING_MESSAGE,null,ok,ok[0]);
        }
    }//GEN-LAST:event_AbilitàMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Abilità;
    private javax.swing.JTextField ArmaturaField;
    private javax.swing.JTextField AttFisField;
    private javax.swing.JTextField AttMagField;
    private javax.swing.JLabel ClasseIco;
    private javax.swing.JButton CompraBT;
    private javax.swing.JButton ExitBT;
    private javax.swing.JTextArea GameConsole;
    private javax.swing.JButton GameImg;
    private javax.swing.JLabel GameImgLabel;
    private javax.swing.JPanel GamePanel;
    private javax.swing.JTextField InventarioField;
    private javax.swing.JList<String> InventoryList;
    private javax.swing.JButton NegozioBT;
    private javax.swing.JList<String> PlayerChoice;
    private javax.swing.JList<String> ShopList;
    private javax.swing.JTextArea StatNemico;
    private javax.swing.JTextArea Statistiche;
    private javax.swing.JButton VendiBT;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    //PER SVOLGERE I TEST
    public void EquipaggiaChoice(String inventarioChoice){
        int y = 0;
        switch(inventarioChoice){
            case "Siciliana" :
                y = 0;
                break;
            
            case "Skipper" :
                y = 1;
                break;

            case "Salute1" : 
                y = 2;
                break;
                
            case "Salute2" :
                y = 3;
                break;

            default :
                break;
        }
        this.InventoryList.setSelectedValue(inventarioChoice, false);
        this.InventoryListMouseClicked(new MouseEvent(this, MOUSE_CLICKED, 1, 0, 30, y, 1, false));
    }
    public void bauleChoice(String playerChoice){
        int y = 0;
        switch(playerChoice){
            case "Apri" :
                y = 20;
                break;
                
            case "Ignora" :
                y = 40;
                break;

            case "Scassina" : 
                y = 20;
                break;

            default :
                break;
        }
        this.PlayerChoice.setSelectedValue(playerChoice, false);
        this.PlayerChoiceMouseClicked(new MouseEvent(this, MOUSE_CLICKED, 1, 0, 30, y, 1, false));
    }
    public void inventarioChoice(String inventarioChoice){
        int y = 0;
        switch(inventarioChoice){
            case "Siciliana" :
                y = 0;
                break;
            
            case "Skipper" :
                y = 1;
                break;

            case "Salute" : 
                y = 2;
                break;
                
            case "SicilianaV" :
                y = 0;
                break;
            
            case "SkipperV" :
                y = 0;
                break;

            case "SaluteV" : 
                y = 0;
                break;

            default :
                break;
        }
        this.InventoryList.setSelectedIndex(y);
        this.VendiBTActionPerformed(new ActionEvent(this, 1, ""));
    }
    public void negozioChoice(String negozioChoice){
        int y = 0;
        switch(negozioChoice){
            case "Siciliana" :
                y = 1;
                break;
            
            case "Skipper" :
                y = 4;
                break;

            case "Salute" : 
                y = 7;
                break;

            default :
                y = 12;
                break;
        }
        this.ShopList.setSelectedIndex(y);
        this.CompraBTActionPerformed(new ActionEvent(this, 1, ""));
    }
    public void playerChoice(String playerChoice){
        if(playerChoice.equals("Combattimento"))
                this.combattimento();
        else { 
            int y;
            switch(playerChoice){
                case "Avanti" : 
                    y = 20;
                    break;

                case "Attacca" :
                    y = 20;
                    break;

                case "Difenditi" :
                    y = 40;
                    break;

                case "Fuggi" :
                    y = 60;
                    break;

                default : 
                    y = 20;
                    break;
            }
            this.PlayerChoice.setSelectedValue(playerChoice, false);
            this.PlayerChoiceMouseClicked(new MouseEvent(this, MOUSE_CLICKED, 1, 0, 30, y, 1, false));
        }
    }
    public void negozio(String s){
            this.NegozioBT.setText(s);
        this.NegozioBTActionPerformed(new ActionEvent(this, 1, ""));
    }
    public Personaggio getP(){
        return this.p;
    }
    public Negozio getN(){
        return this.n;
    }
    public Salvataggio getS(){
        return this.s;
    }
    public File getFMusica(){
        return this.fMusica;
    }
    public String getPathFMusica(){
        return this.fMusica.getName();
    }
    public File getFSuoni(){
        return this.fSuoni;
    }
    public String getPathFSuoni(){
        return this.fSuoni.getName();
    }
    public boolean clipMusicaIsRunning(){
        return this.clipMusica.isRunning();
    }
    public boolean clipMusicaIsOpen(){
        return this.clipMusica.isOpen();
    }
    public boolean clipMusicaIsClose(){
        return !this.clipMusica.isOpen();
    }
    public boolean clipSuoniIsRunning(){
        return this.clipSuoni.isRunning();
    }
    public boolean clipSuoniIsOpen(){
        return this.clipSuoni.isOpen();
    }
    public boolean clipSuoniIsClose(){
        return !this.clipSuoni.isOpen();
    }
    public boolean negozioIsEnable(){
        return this.NegozioBT.isEnabled();
    }
    public String getNegozioText(){
        return this.NegozioBT.getText();
    }
    public boolean vendiIsEnable(){
        return this.VendiBT.isEnabled();
    }
    public boolean compraIsEnable(){
        return this.CompraBT.isEnabled();
    }
    public boolean shopListIsEnable(){
        return this.ShopList.isEnabled();
    }
    public int getI(){
        return this.i;
    }
    public String GameConsoleGetTesto(){
        return this.GameConsole.getText();
    }
    public String getStatistiche(){
        return this.Statistiche.getText();
    }
    public String getStatNemico(){
        return this.StatNemico.getText();
    }
    public Oggetti getShopList(int n){
        return (Oggetti)dmNegozio.getElementAt(n);
    }
    public Consumabili getShopListConsumabili(int n){
        return (Consumabili)dmNegozio.getElementAt(n);
    }
    public ArrayList<Oggetti> getNegozioElement(){
        ArrayList<Oggetti> oggetti = new ArrayList<Oggetti>();
        for(int q=0; q<dmNegozio.getSize(); q++)
            oggetti.add((Oggetti)dmNegozio.getElementAt(q));
        return oggetti;
    }
}