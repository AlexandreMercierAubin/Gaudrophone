package gaudrophone.Domaine.Instrument;

import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.Outils;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.midi.*;


public class Note extends Son {
    NomNote nom;
    int octave;
    int timbreInstrument;
    Synthesizer synthesizer;
    MidiChannel[] channels;
    
    public Note(int timbreInstr){
        octave = 4;
        nom = NomNote.C;
        persistance = 1000;
        timbreInstrument = timbreInstr;
        try{
            synthesizer = MidiSystem.getSynthesizer();
        }
        catch(Exception e){}        
        }
    
    @Override
    public void commencerJouer()
    {
        Timer timer;        
        javax.sound.midi.Instrument instruments[], instr;
        int noInstrument = 1;
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
//        try {
//
//            Sequencer sequencer = MidiSystem.getSequencer();
//            sequencer.open();
//            Sequence sequence = new Sequence(Sequence.PPQ,4);
//            Track track = sequence.createTrack();
//
//            MidiEvent event = null;
//
//            ShortMessage first = new ShortMessage();
//            first.setMessage(192,1,0,0);
//            MidiEvent changeInstrument = new MidiEvent(first, 0);
//            track.add(changeInstrument);
//
//            ShortMessage a = new ShortMessage();
//            a.setMessage(144,1,60,100);
//            MidiEvent noteOn = new MidiEvent(a, 0);
//            track.add(noteOn);
//
//            ShortMessage b = new ShortMessage();
//            b.setMessage(128,1,60,100);
//            MidiEvent noteOff = new MidiEvent(b, 9900);
//            track.add(noteOff);
//
//            sequencer.setSequence(sequence);
//            sequencer.start();
//        } catch (Exception ex) { ex.printStackTrace(); }
        //Ajouter code commencerJouer
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
}