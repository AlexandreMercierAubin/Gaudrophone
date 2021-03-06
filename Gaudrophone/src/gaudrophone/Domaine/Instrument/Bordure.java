package gaudrophone.Domaine.Instrument;

import java.awt.Color;
import java.io.Serializable;

public class Bordure implements Serializable{
    int cote;
    boolean visible;
    double largeur;
    Color couleur;
    
    public Bordure()
    {
        couleur = Color.DARK_GRAY;
        visible = true;
        largeur = 0.002;
    }
    
    public int getCote()
    {
        return cote;
    }
    
    public void setCote(int valeur)
    {
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
    
    public double getLargeur()
    {
        return largeur;
    }
    
    public void setLargeur(double largeur)
    {
        this.largeur = largeur;
    }
    
    public Color getCouleur()
    {
        return couleur;
    }
    
    public void setCouleur(Color couleur)
    {
        this.couleur = couleur;
    }
}
