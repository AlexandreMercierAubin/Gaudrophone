package gaudrophone.Domaine.Instrument;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import gaudrophone.Domaine.Outils;
import java.awt.geom.Path2D;
import java.awt.Polygon;

public class Instrument {
    int timbre;
    String nom;
    List<Touche> touches;
    int toucheSelectionee;
    int cleeTouche;
    
    public Instrument()
    {
        cleeTouche=0;
        touches = new ArrayList<Touche>();
        nom="template";
    }
    
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
    
    public List<Touche> rechercherTouche(String requete)
    {   
        List<Touche> retour= new ArrayList<Touche>();
        String[] mots= requete.split("\\s+");
        
        for(int j=0; j<touches.size();++j)
        {
            Touche touche=touches.get(j);
            String texteAffichage=touche.getTexteAffichage();

            ApparenceTouche apparence =touche.apparence;
            String forme = apparence.forme.toString();
            String couleur = apparence.couleurFond.toString();
            
            for(int i=0;i<mots.length;++i)
            {
                if(texteAffichage.contains(mots[i])
                   || forme.contains(mots[i])
                   || couleur.contains(mots[i]))
                {
                    retour.add(touche);
                }

            }
        }
        return retour;
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
