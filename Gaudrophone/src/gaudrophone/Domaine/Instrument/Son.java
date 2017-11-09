package gaudrophone.Domaine.Instrument;

public abstract class Son {
    float frequence;
    int persistance;
    boolean jouerSon; 
    
    public void commencerJouer(int aa){}
 
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
    
    public boolean isJouerSon() {
        return jouerSon;
    }

    public void setJouerSon(boolean jouerSon) {
        this.jouerSon = jouerSon;
    }
}
