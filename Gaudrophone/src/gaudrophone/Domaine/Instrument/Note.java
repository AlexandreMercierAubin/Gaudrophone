package gaudrophone.Domaine.Instrument;

import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.Outils;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.midi.*;


public class Note extends Son implements Serializable{
    NomNote nom;
    int octave;
    int timbreInstrument;
    transient Synthesizer synthesizer;
    MidiChannel[] channels;
    
    public Note(int timbreInstr){
        octave = 4;
        nom = NomNote.C;
        persistance = 1000;
        timbreInstrument = timbreInstr;
        initialiserSynthesizer();
    }
    
    public Note(int timbreInstr, NomNote note, int octave)
    {
        octave = octave;
        nom = note;
        persistance = 1000;
        timbreInstrument = timbreInstr;
        initialiserSynthesizer();
    }
    
    @Override
    public void commencerJouer()
    {
        Timer timer;        
        javax.sound.midi.Instrument instruments[], instr;
        int noInstrument = timbreInstrument;
        int midiNoteNumber = Outils.getMidiNoteNumber(nom, octave);
        
        // Permet de préciser que l'instrument commence a émettre un son
        jouerSon = true;
        
        try{            
            synthesizer.open();
            instruments = synthesizer.getLoadedInstruments();
            instr = instruments[noInstrument];
            Patch patch = instr.getPatch();

            channels = synthesizer.getChannels();
            channels[0].programChange(patch.getBank(),patch.getProgram());
            channels[0].noteOn(midiNoteNumber, 60);
            
            // Faire durée un son au minimum le temps de la persistance
            timer = new Timer("Tick");
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    arreterJouer();   
                }
            }, persistance);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void arreterJouer()
    {
        System.out.println(jouerSon);
        if (!jouerSon){
            int midiNoteNumber = Outils.getMidiNoteNumber(nom, octave);
            try{
                channels[0].noteOff(midiNoteNumber, 60);
                synthesizer.close();
            }
            catch (Exception e)
            {
            e.printStackTrace();
            }
        }
        else
        {
            jouerSon = false;
        }
    }
    
    public void initialiserSynthesizer()
    {
        try
        {
            synthesizer = MidiSystem.getSynthesizer();
        }
        catch(Exception e)
        {        
        }
    }
    
    public NomNote getNom()
    {
        return nom;
    }
    
    public void setNom(NomNote nom)
    {
        this.nom = nom;
    }
    
    public int getOctave()
    {
        return octave;
    }
    
    public void setOctave(int valeur)
    {
        octave = valeur;
    }
    
    public void setTimbreInstrument(int timbreInstr)
    {
        timbreInstrument = timbreInstr;
    }
}