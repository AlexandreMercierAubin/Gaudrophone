package gaudrophone.Domaine.Instrument;

import java.awt.Color;

public class Bordure {
    int cote;
    boolean visible;
    float largeur;
    Color couleur;
    
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
    
    public float getLargeur()
    {
        return largeur;
    }
    
    public void setLargeur(float largeur)
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
