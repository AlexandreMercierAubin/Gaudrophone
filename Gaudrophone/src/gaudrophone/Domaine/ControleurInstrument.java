package gaudrophone.Domaine;

import gaudrophone.Domaine.Generateur.GenerateurInstrument;
import gaudrophone.Domaine.Instrument.Instrument;
import gaudrophone.Domaine.Enums.ModeVisuel;
import java.util.List;
import java.awt.geom.Point2D;

public class ControleurInstrument {
    Instrument instrument;
    Metronome metronome;
    List<Boucle> boucles;
    Partition partition;
    String cheminSauvegarde;
    ModeVisuel modeVisuel;
    boolean toucheEnDeplacement;
    
    public ControleurInstrument()
    {
    }
    
    public Instrument getInstrument()
    {
        return instrument;
    }
    
    public Metronome getMetronome()
    {
        return metronome;
    }
    
    public void importerPartition(){}
    
    public void jouerPartition(){}
    
    public Boucle getBoucle(int index)
    {
        if(boucles.size()>index)
        {
            return boucles.get(index);
        }
        return null;
    }
    
    public void enregistrerBoucle(int index){}
    
    public void finEnregistrerBoucle(int index){}
    
    public void cliquerSouris(Point2D coordRelative){}
    
    public void enfoncerSouris(Point2D coordRelative){}
   
    public void relacherSouris(Point2D coordRelative){}
    
    public void sauvegarderInstrument(){}
    
    public void sauvegarderSousInstrument(){}
    
    public void importerInstrument(){}
    
    public void modifierModeVisuel(ModeVisuel modeVisuel)
    {
        this.modeVisuel = modeVisuel;
    }
    
    public void genererInstrument(GenerateurInstrument generateurInstrument){}
}
