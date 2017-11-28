
package gaudrophone.Domaine.StrategieRecherche;

import gaudrophone.Domaine.Dictionnaire.dictCouleur;
import gaudrophone.Domaine.Instrument.ApparenceTouche;
import gaudrophone.Domaine.Instrument.Touche;
import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class StrategieCouleur extends StrategieRecherche implements Serializable{
    
    dictCouleur dictionaire;
    public StrategieCouleur()
    {
        dictionaire= new dictCouleur();
    }
    
    @Override
    public boolean comparer(Touche touche, String mots)
    {
        ApparenceTouche apparence=touche.getApparence();
        Color couleur= dictionaire.trouverParClee(mots.toUpperCase());
        if(apparence.getCouleurFond().equals(couleur))
        {
            return true;
        }
        return false;
    }
}
