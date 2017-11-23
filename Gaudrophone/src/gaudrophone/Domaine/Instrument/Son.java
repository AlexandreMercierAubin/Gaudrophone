package gaudrophone.Domaine.Instrument;

public abstract class Son {
    int persistance;
    boolean jouerSon; 
    
    public void commencerJouer(){}
 
    public void arreterJouer(){}
    
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
