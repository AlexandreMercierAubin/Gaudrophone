package gaudrophone.Domaine.Instrument;
import gaudrophone.Domaine.Dimension2D;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import gaudrophone.Domaine.Outils;
import gaudrophone.Domaine.StrategieRecherche.StrategieChemin;
import gaudrophone.Domaine.StrategieRecherche.StrategieCouleur;
import gaudrophone.Domaine.StrategieRecherche.StrategieForme;
import gaudrophone.Domaine.StrategieRecherche.StrategieNoteOctave;
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
        timbre = 1;
        strategies = new ArrayList<StrategieRecherche>();
        cleeTouche=0;
        touches = new ArrayList<Touche>();
        nom="template";
        timbre=0;
        strategies= new ArrayList<StrategieRecherche>();
        //strategies.add(new StrategieChemin());
        //strategies.add(new StrategieCouleur());
        //strategies.add(new StrategieForme());
        //strategies.add(new StrategieNoteOctave());
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
        // Ajuste les valeurs relatives si nécessaire (valeur supérieur à 1
        // lorsqu'on redimensionne la fenêtre et qu'on place une touche plus loin
        // que la limite précédente)
        ajusterTouches(position);
        
        //ajouter une nouvelle touche à la fin de la liste
        touches.add(new Touche(cleeTouche, timbre));
        ++cleeTouche;
        
        //inserer sa position et la selectionner
        Touche toucheAjoutee = touches.get(touches.size()-1);
        toucheAjoutee.getApparence().setPosition(position);
        toucheSelectionee=touches.size()-1;
        
        //ajouter les points dans Path2D selon la dim du constructeur d'apparence
        ApparenceTouche apparence= toucheAjoutee.getApparence();
        
        return toucheAjoutee;
    }
    
    public void rechercherTouche(String requete)
    {   
        //si la requete est vide, effacer la surbrillance
        if(requete.equals(""))
        {
            for(int j=0; j<touches.size();++j)
            {
                if(j!=toucheSelectionee)
                {
                    touches.get(j).setSurbrillance(false);
                }
            }
            
            return;
        }
        
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
        for(int i=touches.size()-1;i>=0;--i)
        {
            Touche touche=touches.get(i);
            ApparenceTouche apparence=touche.getApparence();
            Point2D positionTouche=apparence.getPosition();
            double width=apparence.getDimension().getWidth();
            double height=apparence.getDimension().getHeight();
            if(position.getX()<positionTouche.getX()+ width/2 &&
               position.getX()>positionTouche.getX()- width/2 &&
               position.getY()<positionTouche.getY()+ height/2 &&
               position.getY()>positionTouche.getY()- height/2 
               )
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
    
    // Ajuste la position et la dimension des touches existantes si 
    private void ajusterTouches(Point2D nouvellePosition)
    {
        double x = nouvellePosition.getX();
        double y = nouvellePosition.getY();
        boolean ajusterX = x > 1;
        boolean ajusterY = y > 1;
        
        if (ajusterX || ajusterY)
        {
            for (Touche touche: touches)
            {
                ApparenceTouche apparence = touche.getApparence();
                Point2D position = apparence.getPosition();
                Dimension2D dimension = apparence.getDimension();
                
                double nouveauX = ajusterX ? position.getX() / x : position.getX();
                double nouveauY = ajusterY ? position.getY() / y : position.getY();
                double nouveauWidth = ajusterX ? dimension.getWidth() / x : dimension.getWidth();
                double nouveauHeight = ajusterY ? dimension.getHeight() / y : dimension.getHeight();
                
                apparence.setPosition(new Point2D.Double(nouveauX, nouveauY));
                apparence.setDimension(new Dimension2D(nouveauWidth, nouveauHeight));
            }
        }
    }
    
    public boolean retirerTouche(int index)
    {
        if(index>=touches.size())
        {
            return false;
        }
        
        touches.remove(index);
        
        return true;
    }
    
    public void replacerToucheDessus()
    {
        Touche touche = touches.get(toucheSelectionee);
        touches.remove(toucheSelectionee);
        touches.add(touche);
        toucheSelectionee=touches.size()-1;
    }
}
