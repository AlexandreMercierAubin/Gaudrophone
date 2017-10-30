package gaudrophone.Domaine;
import gaudrophone.Domaine.Instrument.Son;

public class Metronome {
    Son son;
    float frequence;
    
    public Metronome(){}
    
    public Son getSon()
    {
        return son;
    }
    
    public void setSon(Son valeur)
    {
        son = valeur;
    }
    
    public float getFrequence() {
        return frequence;
    }

    public void setFrequence(float frequence) {
        this.frequence = frequence;
    }
}
