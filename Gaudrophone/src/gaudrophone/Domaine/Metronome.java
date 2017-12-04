package gaudrophone.Domaine;
import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Son;


public class Metronome {
    Son son;
    int frequence;
    int timbre;
    Thread metronome;
    boolean metronomeActif;

    
    public Metronome()
    {
        timbre=0;
        frequence=60;
        son=new Note(timbre);
        
        Thread metronome = new Thread() 
        {
            public void run() 
            {
                while(metronomeActif)
                {
                    jouerSon();
                    try 
                    {
                       Thread.sleep(frequence);
                    } catch (Exception e) {
                       System.out.println(e);
                    }
                    
                }
            }
        };
    }
    
    void jouerSon()
    {
        son.commencerJouer();
        son.arreterJouer();
    }
    
    public void arreter()
    {
        metronomeActif=false;
    }
    
    public void demarrer()
    {
        metronomeActif=true;
        metronome.start();

    }
    
    public Son getSon()
    {
        return son;
    }
    
    public void setSon(Son valeur)
    {
        son = valeur;
    }
    
    public int getTimbre() 
    {
        return timbre;
    }
    
    public void setTimbre(int timbre)
    {
        this.timbre=timbre;
        son = new Note(timbre);

    }
    
    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }
}
