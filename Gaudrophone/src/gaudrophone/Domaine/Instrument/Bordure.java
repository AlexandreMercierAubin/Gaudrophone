package gaudrophone.Domaine.Instrument;

import java.awt.Color;

public class Bordure {
    int cote;
    boolean visible;
    int largeur;
    Color couleur;
    
    public Bordure()
    {
        couleur = Color.DARK_GRAY;
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
    
    public int getLargeur()
    {
        return largeur;
    }
    
    public void setLargeur(int largeur)
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
