package Project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Salvataggio extends Thread{
    
    protected boolean verifica;
    protected Personaggio p;
    protected Negozio n;
    protected File f, fNegozio, dir;
    protected ObjectOutputStream writer, writerNegozio;
    protected String path;

    public Salvataggio(Personaggio p, Negozio n) {
        this.p = p;
        this.n = n;
        try{
            this.f = new File("src\\main\\java\\Project\\File\\dati.dat");
            this.f.setWritable(true);
            this.writer = new ObjectOutputStream(new FileOutputStream(f));
            
            this.fNegozio = new File("src\\main\\java\\Project\\File\\negozio.dat");
            this.fNegozio.setWritable(true);
            this.writerNegozio = new ObjectOutputStream(new FileOutputStream(this.fNegozio));
            
            path = this.f.getAbsolutePath().substring(0, 2);
            this.dir = new File(path + "\\ARGV");
            if(!this.dir.exists()){
                this.dir.mkdir();
            }
            this.dir.setWritable(true);
        }
        catch(IOException ex){ }
    }
    
    @Override
    public void run(){
        try{
            this.writer.writeObject(this.p);
            this.writerNegozio.writeObject(this.n);
            do{
                Thread.sleep(60000);
                do{
                    Thread.sleep(5000);
                }while(this.verifica == true);  //true stato bloccante
                this.writer = new ObjectOutputStream(new FileOutputStream(f));
                this.writer.writeObject(this.p);
                
                this.writerNegozio = new ObjectOutputStream(new FileOutputStream(this.fNegozio));
                this.writerNegozio.writeObject(this.n);
            }while(true);
        }
        catch(InterruptedException | IOException ex){
            try{
                this.f.setWritable(false);
                this.writer.close();
                this.fNegozio.setWritable(false);
                this.writerNegozio.close();
                
                //Salvataggio Backup
                this.f = new File(path + "\\ARGV\\dati.dat");
                this.f.setWritable(true);
                this.writer = new ObjectOutputStream(new FileOutputStream(f));
                this.writer.writeObject(this.p);
                this.writer.close();
                
                this.fNegozio = new File(path + "\\ARGV\\negozio.dat");
                this.fNegozio.setWritable(true);
                this.writerNegozio = new ObjectOutputStream(new FileOutputStream(this.fNegozio));
                this.writerNegozio.writeObject(this.n);
                this.writerNegozio.close();
                
                this.dir.setWritable(false);
            }catch(IOException ex2){ }
        }
    }
        
    public void cambioStato(){
        if(this.verifica == false)
                this.verifica = true;
            else if(this.verifica == true)
                this.verifica = false;
    }
    
    public void attivaSalvataggio(){  //attiva il salvataggio
        this.verifica = false;
    }
    
    public void disattivaSalvataggio(){  //disattiva il salvataggio
        this.verifica = true;
    }
    
    //PER SVOLGERE I TEST
    public boolean getVerifica(){
        return this.verifica;
    }
}