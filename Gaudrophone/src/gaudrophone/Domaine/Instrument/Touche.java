package gaudrophone.Domaine.Instrument;
import gaudrophone.Domaine.Outils;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class Touche implements Serializable{
    int index;
    
    String texteAffichage;
    ApparenceTouche apparence;
    Son son;
    boolean surbrillance;
    int timbreInstrument;
    
    public Touche(int index, int timbre)
    {
        this.index = index;
        texteAffichage="";
        apparence = new ApparenceTouche();
        surbrillance = false;
        timbreInstrument = timbre;
        son = new Note(timbreInstrument);
    }
    
    public String getTexteAffichage()
    {
        return texteAffichage;
    }
    
    public void setTexteAffichage(String valeur)
    {
        texteAffichage = valeur;
    }
    
    public ApparenceTouche getApparence()
    {
        return apparence;
    }
    
    public Son getSon()
    {
        return son;
    }
    
    public void commencerJouer()
    {
        surbrillance = true;
        son.commencerJouer();
    }
    
    public void arreterJouer()
    {
        surbrillance = false;
        son.arreterJouer();
    }
    
    public void importerFichierAudio(String chemin)
    {
        // Ajouter le code pour importer le fichier audio si on veut mettre le fichier audio dans les dossiers du gaudrophone
        son = new FichierAudio(chemin);
    }
    
    public void enleverFichierAudio()
    {
        son = new Note(timbreInstrument);
    }
    
    public boolean getSurbrillance()
    {
        return surbrillance;
    }
    
    public void setSurbrillance(boolean valeur)
    {
        surbrillance = valeur;
    }
}
