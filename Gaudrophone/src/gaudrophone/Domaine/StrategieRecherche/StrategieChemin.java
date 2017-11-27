
package gaudrophone.Domaine.StrategieRecherche;

import gaudrophone.Domaine.Instrument.FichierAudio;
import gaudrophone.Domaine.Instrument.Touche;
import gaudrophone.Domaine.Instrument.Son;
import java.io.Serializable;

public class StrategieChemin extends StrategieRecherche implements Serializable{
    @Override
    public boolean comparer(Touche touche, String mots)
    {
        Son son= touche.getSon();
        if(son instanceof FichierAudio)
        {
            String chemin = ((FichierAudio)son).getChemin();
            return chemin.contains(mots);
        }
        return false;
    }
}
