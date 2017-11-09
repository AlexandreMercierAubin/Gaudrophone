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
        javax.sound.midi.Instrument instruments[], instr;
        int noInstrument = timbreInstrument;
        int midiNoteNumber = Outils.getMidiNoteNumber(nom, octave);
        
        jouerSon = true;
        try{            
            synthesizer.open();
            instruments = synthesizer.getLoadedInstruments();
            instr = instruments[noInstrument];
            Patch patch = instr.getPatch();

            channels = synthesizer.getChannels();
            channels[midiNoteNumber].programChange(patch.getBank(),patch.getProgram());
            channels[midiNoteNumber].noteOn(midiNoteNumber, 100);    
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //Ajouter code commencerJouer
    }
    
    @Override
    public void arreterJouer()
    {
        if (!jouerSon){
            int midiNoteNumber = Outils.getMidiNoteNumber(nom, octave);
            try{
                channels[midiNoteNumber].allNotesOff();
                channels[midiNoteNumber].allSoundOff();

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
