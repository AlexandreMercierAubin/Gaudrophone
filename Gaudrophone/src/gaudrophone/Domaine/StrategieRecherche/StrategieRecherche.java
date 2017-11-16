
package gaudrophone.Domaine.StrategieRecherche;
import gaudrophone.Domaine.Instrument.Touche;

public abstract class StrategieRecherche 
{
    public boolean comparer(Touche touche, String mots)
    {
        return false;
    }
    
    public boolean estOctaveNote(String mots)
    {
        String regexpression="/[a-g]#?[0-9]/gi";
        if(mots.matches(regexpression))
        {
            return true;
        }
        return false;
    }
}
