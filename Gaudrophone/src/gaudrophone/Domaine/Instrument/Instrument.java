package gaudrophone.Domaine.Instrument;
import java.util.List;
import java.awt.geom.Point2D;

public class Instrument {
    int timbre;
    String nom;
    List<Touche> touches;
    int toucheSelectionee;
    
    public Instrument(){}
    
    public int getTimbre()
    {
        return timbre;
    }
    
    public void setTimbre(int noInstrument)
    {
        timbre=noInstrument;
    }
    
    public String getNom()
    {
        return nom;
    }
    
    public void setNom(String texte)
    {
        nom=texte;
    }
    
    public Touche getTouche(int index)
    {
        if(touches.size()>index)
        {
            return touches.get(index);
        }
        return null;
    }
    
    public Touche getTouche()
    {
        return getTouche(toucheSelectionee);
    }
    
    public int getNombreTouches()
    {
        return touches.size();
    }
    
    public Touche ajouterTouche(Point2D position)
    {
        //ajouter le contenu
        return null;
    }
    
    public List<Touche> rechercherTouche()
    {
        //ajouter contenu
        return touches;
    }
    
    public boolean selectionnerTouche(Point2D position)
    {
        //ajouter le contenu
        return false;
    }
    
    public List<Touche> getTouches()
    {
        return touches;
    }
}
