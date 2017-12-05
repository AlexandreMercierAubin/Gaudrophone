package gaudrophone.Domaine;
import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Son;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Metronome {
    Note son;
    int frequence;
    int timbre;
    Timer metronome;
    Note note;
    TimerTask tache;
    
    public Metronome()
    {
        timbre=1;
        frequence=500;
        note = new Note(timbre);
        note.setOctave(4);
        note.setPersistance(500);
        note.setNom(NomNote.C);
        son=note;
        
        
        tache = new TimerTask()
        {
            @Override
            public void run() 
            {

                jouerSon();
                
            }
        };
        
        metronome = new Timer();
    }
    
    void jouerSon()
    {
        son.commencerJouer();
        son.arreterJouer();
    }
    
    public void arreter()
    {
        metronome.cancel();
        metronome.purge();
    }
    
    public void demarrer()
    {
        metronome.scheduleAtFixedRate(tache,new Date(),frequence);

    }
    
    public Note getNote()
    {
        return son;
    }
    
    public void setNote(Note valeur)
    {
        son = valeur;
    }
    
    public int getTimbre() 
    {
        return timbre;
    }
    
    public void setTimbre(int timbre)
    {
        this.timbre=timbre;
        son = new Note(timbre);

    }
    
    public int getFrequence() {
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }
}
