package gaudrophone.Domaine.Instrument;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import gaudrophone.Domaine.Outils;
import gaudrophone.Domaine.StrategieRecherche.StrategieChemin;
import gaudrophone.Domaine.StrategieRecherche.StrategieCouleur;
import gaudrophone.Domaine.StrategieRecherche.StrategieForme;
import gaudrophone.Domaine.StrategieRecherche.StrategieRecherche;
import java.awt.geom.Path2D;
import java.awt.Polygon;
import java.io.Serializable;

public class Instrument implements Serializable{
    int timbre;
    String nom;
    String chemin;
    List<Touche> touches;
    int toucheSelectionee;
    int cleeTouche;
    List<StrategieRecherche> strategies;
    
    public Instrument()
    {
        strategies = new ArrayList<StrategieRecherche>();
        cleeTouche=0;
        touches = new ArrayList<Touche>();
        nom="template";
        strategies.add(new StrategieChemin());
        strategies.add(new StrategieCouleur());
        strategies.add(new StrategieForme());
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
        touches.add(new Touche(cleeTouche, timbre));
        ++cleeTouche;
        
        //inserer sa position et la selectionner
        Touche toucheAjoutee = touches.get(touches.size()-1);
        toucheAjoutee.getApparence().setPosition(position);
        toucheSelectionee=touches.size()-1;
        
        //ajouter les points dans Path2D selon la dim du constructeur d'apparence
        ApparenceTouche apparence= toucheAjoutee.getApparence();
        Polygon poly = Outils.calculerPolygone(36, position,apparence.getDimension());
        apparence.getCoins().reset();
        apparence.getCoins().append(poly,true);
        
        return toucheAjoutee;
    }
    
    public void rechercherTouche(String requete)
    {   
        String[] mots= requete.split("\\s+");
        
        for(int j=0; j<touches.size();++j)
        {
            Touche touche=touches.get(j);
            boolean correspond=false;
            for(int i=0;i<mots.length && !correspond;++i)
            {
                for(int k=0;k<strategies.size()&& !correspond;++k)
                {
                    if(strategies.get(k).comparer(touche, mots[i]))
                    {
                        correspond=true;
                    }
                }
            }
            touche.setSurbrillance(correspond);
            
        }
        
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
    
        public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }
}
