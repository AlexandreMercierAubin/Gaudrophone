package gaudrophone.Domaine.Instrument;

import gaudrophone.Domaine.Enums.NomNote;

public class Note extends Son {
    NomNote nom;
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
    
    public NomNote getNom()
    {
        return nom;
    }
    
    public void setNom(NomNote nom)
    {
        this.nom = nom;
    }
    
    public int getOctave()
    {
        return octave;
    }
    
    public void setOctave(int valeur)
    {
        octave = valeur;
    }
}
