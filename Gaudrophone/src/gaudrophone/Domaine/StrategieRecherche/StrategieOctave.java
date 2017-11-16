
package gaudrophone.Domaine.StrategieRecherche;

import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Son;
import gaudrophone.Domaine.Instrument.Touche;


public class StrategieOctave extends StrategieRecherche{
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
                if(motsCoupe.length()==3 || motsCoupe.length()==2)
                {
                    //comparer le dernier char
                    try 
                    {  
                        int octave=Integer.parseInt(""+motsCoupe.charAt(motsCoupe.length()-1));
                        if(((Note)son).getOctave()==octave)
                        {
                            return true;  
                        }
                        
                     } catch (NumberFormatException e) {  
                        return false;  
                     } 
                }
                
            }
        }
        return false;
    }
}
