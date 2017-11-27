
package gaudrophone.Domaine.StrategieRecherche;

import gaudrophone.Domaine.Instrument.ApparenceTouche;
import gaudrophone.Domaine.Instrument.Touche;
import java.io.Serializable;


public class StrategieCouleur extends StrategieRecherche implements Serializable{
    @Override
    public boolean comparer(Touche touche, String mots)
    {
        ApparenceTouche apparence=touche.getApparence();
        if(apparence.getCouleurFond().toString().toUpperCase().equals(mots.toUpperCase()))
        {
            return true;
        }
        return false;
    }
}
