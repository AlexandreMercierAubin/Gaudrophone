
package gaudrophone.Domaine.StrategieRecherche;

import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Son;
import gaudrophone.Domaine.Instrument.Touche;


public class StrategieNoteOctave extends StrategieRecherche{

    @Override
    public boolean comparer(Touche touche, String mots)
    {
        Son son = touche.getSon();
        if(son instanceof Note)
        {
            String motsCoupe= mots.trim();
            String nomNote=((Note)son).getNom().toString();
            String octave=""+((Note)son).getOctave();
            
            nomNote.replaceAll("Sharp","#");
            
            if(motsCoupe==nomNote||motsCoupe==octave||motsCoupe==nomNote+octave)
            {
                return true;
            }
            
        }
        return false;
    }
}
