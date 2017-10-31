package gaudrophone.Domaine.Instrument;

public abstract class Son {
    float frequence;
    int persistance;
    
    public void commencerJouer()
    {
    }
    
    public void arreterJouer(){}
    
    public float getFrequence()
    {
        return frequence;
    }
    
    public void setFrequence(float valeur)
    {
        frequence=valeur;
    }
    public int getPersistance()
    {
        return persistance;
    }
    
    public void setPersistance(int valeur)
    {
        persistance=valeur;
    }
}
