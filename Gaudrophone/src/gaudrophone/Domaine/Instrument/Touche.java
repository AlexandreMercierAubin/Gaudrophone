package gaudrophone.Domaine.Instrument;
import gaudrophone.Domaine.Outils;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class Touche implements Serializable{
    int index;
    
    String nom;
    ApparenceTouche apparence;
    Son son;
    boolean surbrillance;
    int timbreInstrument;
    
    public Touche(int index, int timbre)
    {
        this.index = index;
        nom="";
        apparence = new ApparenceTouche();
        surbrillance = false;
        timbreInstrument = timbre;
        son = new Note(timbreInstrument);
    }
    
    public String getNom()
    {
        return nom;
    }
    
    public void setTexteAffichage(String valeur)
    {
        nom = valeur;
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
    
    public void setTimbreInstrument(int timbreInstr) {
        timbreInstrument = timbreInstr;
        if (son instanceof Note)
        {
            ((Note)son).setTimbreInstrument(timbreInstr);
        }
    }
}
