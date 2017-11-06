package gaudrophone.Domaine.Instrument;
import java.util.List;
import java.awt.geom.Point2D;
import gaudrophone.Domaine.Outils;
import java.awt.geom.Path2D;
import java.awt.Polygon;

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
        
        //ajouter les points dans Path2D selon la dim du constructeur d'apparence
        ApparenceTouche apparence= toucheAjoutee.getApparence();
        Polygon poly = Outils.calculerPolygone(36, position,apparence.getDimension());
        apparence.getCoins().reset();
        apparence.getCoins().append(poly,true);
        
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
        for(int i=0;i<touches.size();++i)
        {
            Touche touche=touches.get(i);
            ApparenceTouche apparence= touche.getApparence();
            Path2D coins=apparence.getCoins();
            
            if(coins.contains(position))
            {
                toucheSelectionee=i;
                return true;
            }
        }
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
