
package gaudrophone.Domaine.StrategieRecherche;

import gaudrophone.Domaine.Instrument.ApparenceTouche;
import gaudrophone.Domaine.Instrument.Touche;

public class StrategieForme extends StrategieRecherche{
    @Override
    public boolean comparer(Touche touche, String mots)
    {
        ApparenceTouche apparence=touche.getApparence();
        if(apparence.getForme().toString().contains(mots))
        {
            return true;
        }
        return false;
    }
}
