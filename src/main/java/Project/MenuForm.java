package Project;

import java.awt.event.ActionEvent;
import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class MenuForm extends javax.swing.JFrame {

    protected File f, fNegozio;
    protected ObjectInputStream reader;
    protected Personaggio p;
    protected Negozio negozio;
    protected Salvataggio s;
    
    protected String pathMusica[] = {"src\\main\\java\\Project\\Musica\\Menu.wav", "src\\main\\java\\Project\\Musica\\Intro.wav"};
    protected File fMusica;
    protected AudioInputStream inputMusica;
    protected Clip clip;
    
    public MenuForm() {
        initComponents();
        
        MenuIMG.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\menu.jpg"));
        GodIMG.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\Goddess.jpg"));
        GuerrieroBT.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\CharGuerriero.png"));
        MagoBT.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\CharMago.png"));
        StrategaBT.setIcon(new ImageIcon("src\\main\\java\\Project\\Immagini\\CharStratega.png"));
        
        
        try{
            this.fMusica = new File(this.pathMusica[0]);
            this.inputMusica = AudioSystem.getAudioInputStream(this.fMusica);
            this.clip = AudioSystem.getClip();
            this.clip.open(inputMusica);
            this.clip.start();
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            
            
            this.f = new File("src\\main\\java\\Project\\File\\dati.dat");
            this.fNegozio = new File("src\\main\\java\\Project\\File\\negozio.dat");
            //File Backup
            String backupPath = f.getAbsolutePath().substring(0, 2)+"\\ARGV\\";
            File fBackup = new File(backupPath+"dati.dat");
            File fNegozioBackup = new File(backupPath+"negozio.dat");
            
            if(this.f.exists() && this.fNegozio.exists())
                this.ContinuaBT.setEnabled(true);
            else{
                //Recupero dei Backup nel caso in cui uno o più file venissero cancellati
                if(fBackup.exists())
                    this.f = fBackup;
                if(fNegozioBackup.exists())
                    this.fNegozio = fNegozioBackup;
                if(this.f.exists() && this.fNegozio.exists())
                    this.ContinuaBT.setEnabled(true);
                else
                    this.ContinuaBT.setEnabled(false);
            }
        }
        catch(Exception ex){ }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        MenuPanel1 = new javax.swing.JPanel();
        ContinuaBT = new javax.swing.JButton();
        RiconoscimentiBT1 = new javax.swing.JButton();
        EsciBT = new javax.swing.JButton();
        NuovaPartitaBT = new javax.swing.JButton();
        MenuIMG = new javax.swing.JLabel();
        IntroPanel = new javax.swing.JPanel();
        IntroArea = new javax.swing.JTextArea();
        NameField = new javax.swing.JTextField();
        NameLabel = new javax.swing.JLabel();
        IntroAvantBT = new javax.swing.JButton();
        GodIMG = new javax.swing.JLabel();
        CharPanel = new javax.swing.JPanel();
        GuerrieroBT = new javax.swing.JButton();
        MagoBT = new javax.swing.JButton();
        StrategaBT = new javax.swing.JButton();
        GuerrieroText = new javax.swing.JTextArea();
        MagoText = new javax.swing.JTextArea();
        StrategaText = new javax.swing.JTextArea();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Seconda Chance");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setEnabled(false);

        MenuPanel1.setBackground(new java.awt.Color(0, 51, 102));
        MenuPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 153, 153)));
        MenuPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ContinuaBT.setBackground(new java.awt.Color(0, 0, 0));
        ContinuaBT.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ContinuaBT.setForeground(new java.awt.Color(255, 255, 255));
        ContinuaBT.setText("CONTINUA");
        ContinuaBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));
        ContinuaBT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ContinuaBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContinuaBTActionPerformed(evt);
            }
        });
        MenuPanel1.add(ContinuaBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 196, 334, 76));

        RiconoscimentiBT1.setBackground(new java.awt.Color(0, 0, 0));
        RiconoscimentiBT1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        RiconoscimentiBT1.setForeground(new java.awt.Color(255, 255, 255));
        RiconoscimentiBT1.setText("RICONOSCIMENTI");
        RiconoscimentiBT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));
        RiconoscimentiBT1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        RiconoscimentiBT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RiconoscimentiBT1ActionPerformed(evt);
            }
        });
        MenuPanel1.add(RiconoscimentiBT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 299, 334, 76));

        EsciBT.setBackground(new java.awt.Color(0, 0, 0));
        EsciBT.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        EsciBT.setForeground(new java.awt.Color(255, 255, 255));
        EsciBT.setText("ESCI");
        EsciBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));
        EsciBT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        EsciBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EsciBTActionPerformed(evt);
            }
        });
        MenuPanel1.add(EsciBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 402, 334, 76));

        NuovaPartitaBT.setBackground(new java.awt.Color(0, 0, 0));
        NuovaPartitaBT.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        NuovaPartitaBT.setForeground(new java.awt.Color(255, 255, 255));
        NuovaPartitaBT.setText("NUOVA PARTITA");
        NuovaPartitaBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));
        NuovaPartitaBT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        NuovaPartitaBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuovaPartitaBTActionPerformed(evt);
            }
        });
        MenuPanel1.add(NuovaPartitaBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 94, 334, 76));
        MenuPanel1.add(MenuIMG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 600));

        jTabbedPane1.addTab("tab1", MenuPanel1);

        IntroPanel.setBackground(new java.awt.Color(0, 51, 102));
        IntroPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 153, 153)));

        IntroArea.setEditable(false);
        IntroArea.setBackground(new java.awt.Color(0, 0, 0));
        IntroArea.setColumns(20);
        IntroArea.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        IntroArea.setForeground(new java.awt.Color(255, 255, 255));
        IntroArea.setRows(5);
        IntroArea.setText(" Hey..finalmente ti sei svegliato! La tua anima ha \n viaggiato per molti mondi prima di arrivare a me. \n Sarai un pò confuso , quindi mi presento, sono ????? \n la dea del tempo e dello spazio. Nel tuo pianeta \n sei morto per un brutto incidente perciò ti ho \n richiamato qui perchè voglio affidarti un compito \n molto importante e se lo porterai a termine, come \n premio, avrai una seconda vita.\n Non preoccuparti, ti darò delle abilità uniche con \n cui potrai affrontare tutti gli ostacoli che ti troverai \n davanti. Ma prima di tutto scegli un nome con cui \n posso chiamarti:");
        IntroArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));

        NameField.setBackground(new java.awt.Color(0, 0, 0));
        NameField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NameField.setForeground(new java.awt.Color(255, 255, 255));
        NameField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));
        NameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NameFieldKeyTyped(evt);
            }
        });

        NameLabel.setBackground(new java.awt.Color(0, 0, 0));
        NameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        NameLabel.setForeground(new java.awt.Color(255, 255, 255));
        NameLabel.setText(" inserisci nome");
        NameLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));

        IntroAvantBT.setBackground(new java.awt.Color(0, 0, 0));
        IntroAvantBT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        IntroAvantBT.setForeground(new java.awt.Color(255, 255, 255));
        IntroAvantBT.setText("AVANTI");
        IntroAvantBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));
        IntroAvantBT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        IntroAvantBT.setEnabled(false);
        IntroAvantBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IntroAvantBTActionPerformed(evt);
            }
        });

        GodIMG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 102), 3));

        javax.swing.GroupLayout IntroPanelLayout = new javax.swing.GroupLayout(IntroPanel);
        IntroPanel.setLayout(IntroPanelLayout);
        IntroPanelLayout.setHorizontalGroup(
            IntroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IntroPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(IntroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IntroAvantBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(IntroPanelLayout.createSequentialGroup()
                        .addComponent(GodIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addGroup(IntroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IntroArea, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IntroPanelLayout.createSequentialGroup()
                                .addComponent(NameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(38, 38, 38))
        );
        IntroPanelLayout.setVerticalGroup(
            IntroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IntroPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(IntroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(IntroPanelLayout.createSequentialGroup()
                        .addComponent(IntroArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(IntroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NameField)))
                    .addComponent(GodIMG, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(IntroAvantBT, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        jTabbedPane1.addTab("tab2", IntroPanel);

        CharPanel.setBackground(new java.awt.Color(0, 51, 102));
        CharPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 153, 153)));

        GuerrieroBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 102), 3));
        GuerrieroBT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        GuerrieroBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuerrieroBTActionPerformed(evt);
            }
        });

        MagoBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 102), 3));
        MagoBT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MagoBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MagoBTActionPerformed(evt);
            }
        });

        StrategaBT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 102), 3));
        StrategaBT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        StrategaBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StrategaBTActionPerformed(evt);
            }
        });

        GuerrieroText.setEditable(false);
        GuerrieroText.setBackground(new java.awt.Color(0, 0, 0));
        GuerrieroText.setColumns(20);
        GuerrieroText.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        GuerrieroText.setForeground(new java.awt.Color(255, 255, 255));
        GuerrieroText.setRows(5);
        GuerrieroText.setText(" Eccelle nell’attacco corpo a\n corpo,riesce a resistere a\n innumerevoli colpi.");
        GuerrieroText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));

        MagoText.setEditable(false);
        MagoText.setBackground(new java.awt.Color(0, 0, 0));
        MagoText.setColumns(20);
        MagoText.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        MagoText.setForeground(new java.awt.Color(255, 255, 255));
        MagoText.setRows(5);
        MagoText.setText(" Maestro nell'uso della magia\n arcana, si sbarazza facilmente \n dei suoi avversari.");
        MagoText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));

        StrategaText.setEditable(false);
        StrategaText.setBackground(new java.awt.Color(0, 0, 0));
        StrategaText.setColumns(20);
        StrategaText.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        StrategaText.setForeground(new java.awt.Color(255, 255, 255));
        StrategaText.setRows(5);
        StrategaText.setText(" Utilizza le migliore strategia\n grazie alla sua grande \n fortuna.");
        StrategaText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jTextArea1.setText(" Adesso scegli il tuo personaggio, attenzione ognuno di essi avrà abilità diverse perciò scegli con cura");
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255), 3));

        javax.swing.GroupLayout CharPanelLayout = new javax.swing.GroupLayout(CharPanel);
        CharPanel.setLayout(CharPanelLayout);
        CharPanelLayout.setHorizontalGroup(
            CharPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CharPanelLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(CharPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextArea1)
                    .addGroup(CharPanelLayout.createSequentialGroup()
                        .addGroup(CharPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(GuerrieroText, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(GuerrieroBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(95, 95, 95)
                        .addGroup(CharPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(MagoBT, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MagoText, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(CharPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(StrategaBT, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(StrategaText, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                .addGap(87, 87, 87))
        );
        CharPanelLayout.setVerticalGroup(
            CharPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CharPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(CharPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MagoBT, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CharPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(StrategaBT, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(GuerrieroBT, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(CharPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GuerrieroText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MagoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StrategaText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        jTabbedPane1.addTab("tab3", CharPanel);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, -1, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NuovaPartitaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuovaPartitaBTActionPerformed
        try{
            
            this.clip.stop();
            this.fMusica = new File(this.pathMusica[1]);
            this.inputMusica = AudioSystem.getAudioInputStream(this.fMusica);
            this.clip = AudioSystem.getClip();
            this.clip.open(inputMusica);
            this.clip.start();
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException ex){ }

        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_NuovaPartitaBTActionPerformed

    private void EsciBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EsciBTActionPerformed
        System.exit(0);
    }//GEN-LAST:event_EsciBTActionPerformed

    private void IntroAvantBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IntroAvantBTActionPerformed
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_IntroAvantBTActionPerformed

    private void RiconoscimentiBT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RiconoscimentiBT1ActionPerformed
        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(rootPane, "Grazie per aver giocato!\nARGV TEAM.",
        "RICONOSCIMENTI",JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
    }//GEN-LAST:event_RiconoscimentiBT1ActionPerformed

    private void ContinuaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContinuaBTActionPerformed
        try {

            this.clip.stop();
            this.clip.close();
            try{
                this.inputMusica.close();
            }catch(IOException ex){ }

            this.negozio = new Negozio();
            this.reader = new ObjectInputStream(new FileInputStream(this.fNegozio));
            this.negozio = (Negozio)this.reader.readObject();
            this.reader.close();
            
            this.reader = new ObjectInputStream(new FileInputStream(f));
            this.p = (Personaggio)this.reader.readObject();
            this.s = new Salvataggio(this.p, this.negozio);
            this.s.start();
            this.reader.close();
            
            new SchermataPrincipale(this.p, this.s, this.negozio).setVisible(true);
            
            dispose(); //close menuform after open schermata
        }
        catch(IOException | ClassNotFoundException ex){}
    }//GEN-LAST:event_ContinuaBTActionPerformed

    private void GuerrieroBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuerrieroBTActionPerformed
        Object[] options = {"SI","NO"};
        int n = JOptionPane.showOptionDialog(rootPane, "Vuoi cominciare una nuova partita con questo personaggio?",
        "SELEZIONE PERSONAGGIO",JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        
        if (n==JOptionPane.YES_OPTION)
        {
            this.clip.stop();
            this.clip.close();
            try{
                this.inputMusica.close();
            }catch(IOException ex){ }

            this.p = new Personaggio(this.NameField.getText(),"Guerriero");
            this.negozio = new Negozio();
            this.s = new Salvataggio(this.p, this.negozio);
            this.s.start();
            new SchermataPrincipale(this.p, this.s, this.negozio).setVisible(true);
            dispose(); //close menuform after open schermata
        }
    }//GEN-LAST:event_GuerrieroBTActionPerformed

    private void MagoBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MagoBTActionPerformed
        Object[] options = {"SI","NO"};
        int n = JOptionPane.showOptionDialog(rootPane, "Vuoi cominciare una nuova partita con questo personaggio?",
        "SELEZIONE PERSONAGGIO",JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        
        if (n==JOptionPane.YES_OPTION)
        {
            this.clip.stop();
            this.clip.close();
            try{
                this.inputMusica.close();
            }catch(IOException ex){ }

            this.p = new Personaggio(this.NameField.getText(),"Mago");
            this.negozio = new Negozio();
            this.s = new Salvataggio(this.p, this.negozio);
            this.s.start();
            new SchermataPrincipale(this.p, this.s, this.negozio).setVisible(true);
            dispose(); //close menuform after open schermata
        }
    }//GEN-LAST:event_MagoBTActionPerformed

    private void StrategaBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StrategaBTActionPerformed
        Object[] options = {"SI","NO"};
        int n = JOptionPane.showOptionDialog(rootPane, "Vuoi cominciare una nuova partita con questo personaggio?",
        "SELEZIONE PERSONAGGIO",JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        
        if (n==JOptionPane.YES_OPTION)
        {
            this.clip.stop();
            this.clip.close();
            try{
                this.inputMusica.close();
            }catch(IOException ex){ }

            this.p = new Personaggio(this.NameField.getText(),"Stratega");
            this.negozio = new Negozio();
            this.s = new Salvataggio(this.p, this.negozio);
            this.s.start();
            new SchermataPrincipale(this.p, this.s, this.negozio).setVisible(true);
            dispose(); //close menuform after open schermata
        }
    }//GEN-LAST:event_StrategaBTActionPerformed

    private void NameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NameFieldKeyTyped
    char c = evt.getKeyChar();
    if(CheckLetter(c)){
        NameField.setEditable(true);
        if(NameField.getText().length()>1){
            IntroAvantBT.setEnabled(true);
        }else
            IntroAvantBT.setEnabled(false);
    }else
        NameField.setEditable(false);
        
    }//GEN-LAST:event_NameFieldKeyTyped

    private boolean CheckLetter(char c)
    {
        return c >='0' && c<='9' || c >='a' && c<='z' || c >='A' && c<='Z'|| c == VK_BACK_SPACE;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CharPanel;
    private javax.swing.JButton ContinuaBT;
    private javax.swing.JButton EsciBT;
    private javax.swing.JLabel GodIMG;
    private javax.swing.JButton GuerrieroBT;
    private javax.swing.JTextArea GuerrieroText;
    private javax.swing.JTextArea IntroArea;
    private javax.swing.JButton IntroAvantBT;
    private javax.swing.JPanel IntroPanel;
    private javax.swing.JButton MagoBT;
    private javax.swing.JTextArea MagoText;
    private javax.swing.JLabel MenuIMG;
    private javax.swing.JPanel MenuPanel1;
    private javax.swing.JTextField NameField;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JButton NuovaPartitaBT;
    private javax.swing.JButton RiconoscimentiBT1;
    private javax.swing.JButton StrategaBT;
    private javax.swing.JTextArea StrategaText;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    
    //PER TEST
    public int nuovaPartita(){
       this.NuovaPartitaBTActionPerformed(new ActionEvent(this, 1, null));
       return this.jTabbedPane1.getSelectedIndex();
    }
    public int avanti(){
       this.IntroAvantBTActionPerformed(new ActionEvent(this, 1, null));
       return this.jTabbedPane1.getSelectedIndex();
    }
    public void continua(){
        this.ContinuaBTActionPerformed(new ActionEvent(this, 1, null));
    }
    public void guerriero(){
        this.GuerrieroBTActionPerformed(new ActionEvent(this, 1, null));
    }
    public void mago(){
        this.MagoBTActionPerformed(new ActionEvent(this, 1, null));
    }
    public void stratega(){
        this.StrategaBTActionPerformed(new ActionEvent(this, 1, null));
    }
    public File getF(){
        return this.f;
    }
    public File getFNegozio(){
        return this.fNegozio;
    }
    public Personaggio getP(){
        return this.p;
    }
    public Negozio getNegozio(){
        return this.negozio;
    }
    public Salvataggio getS(){
        return this.s;
    }
    public boolean sIsStarted(){
        return this.s.isAlive();
    }
    public File getFMusica(){
        return this.fMusica;
    }
    public String getPathFMusica(){
        return this.fMusica.getName();
    }
    public boolean clipMusicaIsRunning(){
        return this.clip.isRunning();
    }
    public boolean clipMusicaIsOpen(){
        return this.clip.isOpen();
    }
    public boolean clipMusicaIsClose(){
        return !this.clip.isOpen();
    }
}