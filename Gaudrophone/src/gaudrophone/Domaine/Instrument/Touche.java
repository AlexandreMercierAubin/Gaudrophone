package gaudrophone.Domaine.Instrument;
import java.awt.geom.Point2D;

public class Touche {
    int index;
    Point2D position;
    String texteAffichage;
    ApparenceTouche apparence;
    Son son;
    boolean surbrillance;
    
    public Touche(int index)
    {
        this.index = index;
        texteAffichage="";
        apparence = new ApparenceTouche();
        surbrillance = false;
    }
    
    public Point2D getPosition()
    {
        return position;
    }
    
    public void setPosition(Point2D valeur)
    {
        position = valeur;
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
    
    public void commencerJouer(){}
    
    public void arreterJouer(){}
    
    public void ImporterFichierAudio(){}
    
    public void enleverFichierAudio(){}
    
    public boolean getSurbrillance()
    {
        return surbrillance;
    }
    
    public void setSurbrillance(boolean valeur)
    {
        surbrillance = valeur;
    }
}
