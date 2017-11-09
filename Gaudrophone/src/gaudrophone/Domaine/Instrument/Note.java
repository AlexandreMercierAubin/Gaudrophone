package gaudrophone.Domaine.Instrument;

import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.Outils;
import javax.sound.midi.*;

public class Note extends Son {
    NomNote nom;
    int octave;
    Synthesizer synthesizer;
    MidiChannel[] channels;
    
    public Note(){
        try{
            synthesizer = MidiSystem.getSynthesizer();
        }
        catch(Exception e){}
        
        }
    
    @Override
    public void commencerJouer(int timbre)
    {
        jouerSon = true;
        javax.sound.midi.Instrument instruments[], instr;
        int noInstrument = timbre;
        int midiNoteNumber = Outils.getMidiNoteNumber(nom, octave);
        try{
            
            synthesizer.open();
            instruments = synthesizer.getLoadedInstruments();
            instr = instruments[noInstrument];
            Patch patch = instr.getPatch();

            channels = synthesizer.getChannels();
            channels[0].programChange(patch.getBank(),patch.getProgram());
            channels[0].noteOn(midiNoteNumber, 100);    
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
                channels[0].allNotesOff();
                channels[0].allSoundOff();

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
