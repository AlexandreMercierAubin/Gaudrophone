package gaudrophone.Domaine.Instrument;

public class Bordure {
    int cote;
    boolean visible;
    
    public Bordure(){}
    
    public int getCote()
    {
        return cote;
    }
    
    public void setCote(int valeur)
    {
        //ajouter des validations si existe selon la forme
        cote = valeur;
    }
    
    public boolean getVisible()
    {
        return visible;
    }
    
    public void setVisible(boolean valeur)
    {
        visible = valeur;
    }
}
