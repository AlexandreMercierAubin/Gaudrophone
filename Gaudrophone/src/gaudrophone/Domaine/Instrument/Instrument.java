package gaudrophone.Domaine.Instrument;
import java.util.List;
import java.awt.geom.Point2D;

public class Instrument {
    javax.sound.midi.Instrument timbre;
    String nom;
    List<Touche> touches;
    int toucheSelectionee;
    int cleeTouche;
    
    public Instrument()
    {
        cleeTouche=0;
    }
    
    public javax.sound.midi.Instrument getTimbre()
    {
        return timbre;
    }
    
    public void setTimbre(){}
    
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
        //ajouter une nouvelle touche à la fin de la liste
        touches.add(new Touche(cleeTouche));
        ++cleeTouche;
        
        //inserer sa position et la selectionner
        Touche toucheAjoutee = touches.get(touches.size()-1);
        toucheAjoutee.setPosition(position);
        toucheSelectionee=touches.size()-1;
        
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
        toucheSelectionee=-1;
        return false;
    }
    
    public List<Touche> getTouches()
    {
        return touches;
    }
    
    public int getToucheSelectionee() {
        return toucheSelectionee;
    }
}
