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
        frequence=1000;
        note = new Note(timbre);
        note.setOctave(4);
        note.setPersistance(500);
        note.setNom(NomNote.C);
        son=note;
        
        preparerTimer();
    }
    
    void preparerTimer()
    {
        metronome = new Timer(true);
        tache = new TimerTask()
        {
            @Override
            public void run() 
            {

                jouerSon();
                
            }
        };
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
        preparerTimer();
    }
    
    public void demarrer()
    {
        metronome.scheduleAtFixedRate(tache,0,frequence);
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
        son.setTimbreInstrument(timbre);

    }
    
    public int getFrequence() {
        //(metronome)
        return frequence;
    }

    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }
}
