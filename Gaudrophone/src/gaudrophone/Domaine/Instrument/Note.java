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
//        javax.sound.midi.Instrument instruments[], instr;
//        int noInstrument = timbreInstrument;
//        int midiNoteNumber = Outils.getMidiNoteNumber(nom, octave);
//        
//        jouerSon = true;
//        try{            
//            synthesizer.open();
//            instruments = synthesizer.getLoadedInstruments();
//            instr = instruments[noInstrument];
//            Patch patch = instr.getPatch();
//
//            channels = synthesizer.getChannels();
//            channels[midiNoteNumber].programChange(patch.getBank(),patch.getProgram());
//            channels[midiNoteNumber].noteOn(midiNoteNumber, 60);    
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        try {

            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Sequence sequence = new Sequence(Sequence.PPQ,4);
            Track track = sequence.createTrack();

            MidiEvent event = null;

            ShortMessage first = new ShortMessage();
            first.setMessage(192,1,127,0);
            MidiEvent changeInstrument = new MidiEvent(first, 0);
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,60,100);
            MidiEvent noteOn = new MidiEvent(a, 0);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128,1,60,100);
            MidiEvent noteOff = new MidiEvent(b, (60000/(60*4)));
            track.add(noteOff);

            sequencer.setSequence(sequence);
            sequencer.start();
        } catch (Exception ex) { ex.printStackTrace(); }
        //Ajouter code commencerJouer
    }
    
    @Override
    public void arreterJouer()
    {
        if (!jouerSon){
            int midiNoteNumber = Outils.getMidiNoteNumber(nom, octave);
            try{
                channels[midiNoteNumber].noteOff(midiNoteNumber);
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
