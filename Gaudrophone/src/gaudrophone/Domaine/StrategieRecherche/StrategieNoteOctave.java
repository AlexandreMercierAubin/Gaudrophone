
package gaudrophone.Domaine.StrategieRecherche;

import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Son;
import gaudrophone.Domaine.Instrument.Touche;
import java.io.Serializable;


public class StrategieNoteOctave extends StrategieRecherche implements Serializable{

    @Override
    public boolean comparer(Touche touche, String mots)
    {
        Son son = touche.getSon();
        if(son instanceof Note)
        {
            String motsCoupe= mots.trim().toUpperCase();
            if(((Note)son).getNom()!=null)
            {
                String nomNote=((Note)son).getNom().toString().toUpperCase();
                String octave=""+((Note)son).getOctave();
                octave=octave.toUpperCase();

                nomNote.replaceAll("SHARP","#");

                if(motsCoupe.equals(nomNote)||motsCoupe.equals(octave)||motsCoupe.equals(nomNote+octave))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
