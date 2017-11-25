package gaudrophone.Domaine.Instrument;

import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.Outils;
import javax.sound.midi.*;

public class Note extends Son {
    NomNote nom;
    int octave;
    int timbreInstrument;
    Synthesizer synthesizer;
    MidiChannel[] channels;
    
    public Note(int timbreInstr){
        timbreInstrument = timbreInstr;
        try{
            synthesizer = MidiSystem.getSynthesizer();
        }
        catch(Exception e){}        
        }
    
    @Override
    public void commencerJouer()
    {
        jouerSon = true;
        javax.sound.midi.Instrument instruments[], instr;
        int noInstrument = 1;
        int midiNoteNumber = Outils.getMidiNoteNumber(NomNote.GSharp, 4);
        
        try{            
            synthesizer.open();
            instruments = synthesizer.getLoadedInstruments();
            instr = instruments[noInstrument];
            Patch patch = instr.getPatch();

            channels = synthesizer.getChannels();
            channels[0].programChange(patch.getBank(),patch.getProgram());
            channels[0].noteOn(midiNoteNumber, 60);    
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
        jouerSon = false;
        System.out.println(jouerSon);
        if (!jouerSon){
            int midiNoteNumber = Outils.getMidiNoteNumber(NomNote.D, 4);
            try{
                channels[0].noteOff(midiNoteNumber, 60);
                synthesizer.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    
    public void setFrequence(float valeur){}
    
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