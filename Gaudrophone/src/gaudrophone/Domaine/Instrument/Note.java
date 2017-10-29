package gaudrophone.Domaine.Instrument;

public class Note extends Son {
    //NomNote nom;
    int octave;
    
    public Note(){}
    
    @Override
    public void commencerJouer()
    {
    }
    
    @Override
    public void arreterJouer(){}
    
    @Override
    public void setFrequence(float valeur){}
    
    //public NomNote getNom(){}
    
    //public NomNote setNom(){}
    
    public int getOctave()
    {
        return octave;
    }
    
    public void setOctave(int valeur)
    {
        octave = valeur;
    }
}
