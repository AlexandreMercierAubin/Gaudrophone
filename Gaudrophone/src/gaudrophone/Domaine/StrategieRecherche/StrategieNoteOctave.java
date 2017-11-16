
package gaudrophone.Domaine.StrategieRecherche;

import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Son;
import gaudrophone.Domaine.Instrument.Touche;


public class StrategieNoteOctave extends StrategieRecherche{
   
    private boolean comparerOctave(String mot,Son son)
    {
        //comparer le dernier char
        try 
        {  
            int octave=Integer.parseInt(""+mot.charAt(mot.length()-1));
            if(((Note)son).getOctave()==octave)
            {
                return true;  
            }

         } catch (NumberFormatException e) {  
            return false;  
         } 
        return false;
    }
    private boolean comparerNote(String mot,Son son)
    {
        //si de longueur 3
        if(mot.length()==3)
        {
            //comparer les 2 premiers char
            String nomNote=""+mot.charAt(0)+mot.charAt(1);
            if(((Note)son).getNom().toString().contains(nomNote))
            {
                return true;
            }
        }
        //si de longueur 2
        else if(mot.length()==2)
        {
            //comparer le premier char
            String nomNote=""+mot.charAt(0);
            if(((Note)son).getNom().toString().contains(nomNote))
            {
                return true;
            }
        }
        return false;
    }
    
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
                
            }
        }
        return false;
    }
}
