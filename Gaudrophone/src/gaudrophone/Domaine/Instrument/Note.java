package gaudrophone.Domaine.Instrument;

import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.Outils;
import javax.sound.midi.*;

public class Note extends Son {
    NomNote nom;
    int octave;
    
    public Note(){}
    
    @Override
    public void commencerJouer()
    {
        Outils outils = new Outils();
        int midiNoteNumber = outils.getMidiNoteNumber(nom, octave);
        try{
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        javax.sound.midi.Instrument instruments[] = synthesizer.getLoadedInstruments();
        synthesizer.loadInstrument(instruments[48]);
        for (int i = 0; i<instruments.length; i++)
        {
            System.out.println(i + " " + instruments[i].getName());
            
        }
        System.out.println(instruments[43].getName());
        
        int persis = persistance;

        
        MidiChannel[] channels = synthesizer.getChannels();
        channels[octave].programChange(0, 48);

        channels[octave].noteOn(midiNoteNumber, 100);
        Thread.sleep(2000);
        channels[octave].noteOff(midiNoteNumber);
        

        synthesizer.close();
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
        Outils outils = new Outils();
        int midiNoteNumber = outils.getMidiNoteNumber(nom, octave);
        try{
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();

        MidiChannel[] channels = synthesizer.getChannels();

        channels[octave].noteOff(midiNoteNumber);
        

        synthesizer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //Ajouter code arreterJouer
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
