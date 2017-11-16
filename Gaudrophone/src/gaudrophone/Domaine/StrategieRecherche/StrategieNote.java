
package gaudrophone.Domaine.StrategieRecherche;

import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Son;
import gaudrophone.Domaine.Instrument.Touche;


public class StrategieNote extends StrategieRecherche{
   @Override
    public boolean comparer(Touche touche, String mots)
    {
        Son son = touche.getSon();
        if(son instanceof Note)
        {
            String motsCoupe= mots.trim();
            
            //si sous le bon format
            if(this.estOctaveNote(motsCoupe))
            {
                //si de longueur 3
                if(motsCoupe.length()==3)
                {
                    //comparer les 2 premiers char
                    String nomNote=""+motsCoupe.charAt(0)+motsCoupe.charAt(1);
                    if(((Note)son).getNom().toString().contains(nomNote))
                    {
                        return true;
                    }
                }
                //si de longueur 2
                else if(motsCoupe.length()==2)
                {
                    //comparer le premier char
                    String nomNote=""+motsCoupe.charAt(0);
                    if(((Note)son).getNom().toString().contains(nomNote))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
