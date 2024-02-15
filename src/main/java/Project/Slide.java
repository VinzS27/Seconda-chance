package Project;

public class Slide {
    
    protected static enum TIPO{NARRAZIONE, EVENTO_CASUALE, COMBATTIMENTO, SCELTA_IMPORTANTE, MINIBOSS, BOSS, RICOMPENSE, BAULE, CAMBIO_SFONDO, DOPO_BOSS, FINALE};
    protected int tipo;  //0=Narrazione, 1=Evento casuale, 2=Combattimento, 3=Scelta importante, 4=MiniBoss, 5=Boss, 6=Ricompense, 7=Baule, 8=CAMBIO_SFONDO, 9=DOPO_BOSS, 10=FINALE 
    protected String testo;
    protected String img;
    protected String sfondoImg;
            
    public Slide(int tipo, String testo, String img, String sfondoImg){
        this.tipo = tipo;
        this.testo = testo;
        this.img = "src\\main\\java\\Project\\Immagini\\" + img;
        this.sfondoImg = "src\\main\\java\\Project\\Immagini\\" + sfondoImg;
    }
    //con enum
    public Slide(TIPO tipo, String testo, String img, String sfondoImg){
        this.tipo = tipo.ordinal();
        this.testo = testo;
        this.img = "src\\main\\java\\Project\\Immagini\\" + img;
        this.sfondoImg = "src\\main\\java\\Project\\Immagini\\" + sfondoImg;
    }

    public int getTipo(){
        return this.tipo;
    }
    public String getTesto(){
        return this.testo;
    }
    public String getImg(){
        return this.img;
    }
    public String getSfondoImg(){
        return this.sfondoImg;
    }
}