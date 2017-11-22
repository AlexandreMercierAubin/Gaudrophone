package gaudrophone.Domaine;
import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Son;

public class Metronome {
    Son son;
    float frequence;
    int timbre;


    
    public Metronome()
    {
        timbre=0;
        frequence=60;
        son=new Note(timbre);
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
    
    public float getFrequence() {
        return frequence;
    }

    public void setFrequence(float frequence) {
        this.frequence = frequence;
    }
}
