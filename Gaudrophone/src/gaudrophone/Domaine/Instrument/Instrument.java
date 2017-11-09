package gaudrophone.Domaine.Instrument;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import gaudrophone.Domaine.Outils;
import java.awt.geom.Path2D;
import java.awt.Polygon;
import java.io.Serializable;

public class Instrument implements Serializable{
    javax.sound.midi.Instrument timbre;
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
            String texteAffichage=touche.getTexteAffichage();

            ApparenceTouche apparence =touche.apparence;
            String forme = apparence.forme.toString();
            String couleur = apparence.couleurFond.toString();
            
            Son son = touche.getSon();
            int instanceOfIndex=0;
            String nomNote = "";
            String octave = "";
            String chemin = "";
            
            if(son instanceof Note)
            {
                nomNote = ((Note)son).getNom().toString();
                octave += ((Note)son).getOctave();
                instanceOfIndex=1;
            }
            else if(son instanceof FichierAudio)
            {
                chemin = ((FichierAudio)son).getChemin();
                instanceOfIndex=2;
            }
            
            for(int i=0;i<mots.length;++i)
            {
                if(texteAffichage.contains(mots[i])
                   || forme.contains(mots[i])
                   || couleur.contains(mots[i])
                   || instanceOfIndex==1&& nomNote.contains(mots[i])
                   || instanceOfIndex==1&& octave.contains(mots[i])
                   || instanceOfIndex==2&& chemin.contains(mots[i]))
                {
                    touche.setSurbrillance(true);
                }
            }
            
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
}
