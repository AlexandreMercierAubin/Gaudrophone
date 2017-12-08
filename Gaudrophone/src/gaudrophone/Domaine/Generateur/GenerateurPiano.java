package gaudrophone.Domaine.Generateur;
import gaudrophone.Domaine.Dimension2D;
import gaudrophone.Domaine.Enums.Forme;
import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.Instrument.ApparenceTouche;
import gaudrophone.Domaine.Instrument.Instrument;
import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Touche;
import java.awt.Color;
import java.util.List;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class GenerateurPiano extends GenerateurInstrument{
    int timbre;
    List<List<Note>> notes;
    
    public GenerateurPiano()
    {
        timbre = 1; // Piano
        notes = new ArrayList<>();
        
        // Notes blanches
        notes.add(Arrays.asList(
                new Note(timbre, NomNote.C, 4),
                new Note(timbre, NomNote.D, 4),
                new Note(timbre, NomNote.E, 4),
                new Note(timbre, NomNote.F, 4),
                new Note(timbre, NomNote.G, 4),
                new Note(timbre, NomNote.A, 4),
                new Note(timbre, NomNote.B, 4),
                
                new Note(timbre, NomNote.C, 5),
                new Note(timbre, NomNote.D, 5),
                new Note(timbre, NomNote.E, 5),
                new Note(timbre, NomNote.F, 5),
                new Note(timbre, NomNote.G, 5),
                new Note(timbre, NomNote.A, 5),
                new Note(timbre, NomNote.B, 5),
                
                new Note(timbre, NomNote.C, 6),
                new Note(timbre, NomNote.D, 6),
                new Note(timbre, NomNote.E, 6),
                new Note(timbre, NomNote.F, 6),
                new Note(timbre, NomNote.G, 6),
                new Note(timbre, NomNote.A, 6),
                new Note(timbre, NomNote.B, 6)
        ));
        // Notes noires
        notes.add(Arrays.asList(
                new Note(timbre, NomNote.CSharp, 4),
                new Note(timbre, NomNote.DSharp, 4),
                new Note(timbre, NomNote.FSharp, 4),
                new Note(timbre, NomNote.GSharp, 4),
                new Note(timbre, NomNote.ASharp, 4),
                
                new Note(timbre, NomNote.CSharp, 5),
                new Note(timbre, NomNote.DSharp, 5),
                new Note(timbre, NomNote.FSharp, 5),
                new Note(timbre, NomNote.GSharp, 5),
                new Note(timbre, NomNote.ASharp, 5),
                
                new Note(timbre, NomNote.CSharp, 6),
                new Note(timbre, NomNote.DSharp, 6),
                new Note(timbre, NomNote.FSharp, 6),
                new Note(timbre, NomNote.GSharp, 6),
                new Note(timbre, NomNote.ASharp, 6)
        ));
    }
    
    @Override
    public Instrument generer()
    {
        Instrument instrument = new Instrument();
        instrument.setNom("Piano");
        instrument.setTimbre(timbre);
        
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < notes.get(i).size(); j++)
            {
                Touche touche = instrument.ajouterTouche(new Point2D.Double(0.0, 0.0));
                genererApparence(touche, i, j);
                genererSon(touche, i, j);
            }
        }
        
        return instrument;
    }
    
    private void genererApparence(Touche touche, int i, int j)
    {
        ApparenceTouche apparence = touche.getApparence();
        
        apparence.setForme(Forme.Rectangle);
        
        if (i == 0)
        {
            apparence.setPosition(new Point2D.Double(j / 21.0 + 1.0 / 42.0, 0.5));
            apparence.setDimension(new Dimension2D(1.0 / 21.0, 0.6));
            apparence.setCouleurFond(Color.white);
            
            for (int k = 0; k < 4; k++)
                apparence.getBordure(k).setCouleur(Color.black);
        }
        else
        {
            if (j % 5 < 2)
                apparence.setPosition(new Point2D.Double(j / 5 * 7.0 / 21.0 + (j % 5 + 1) / 21.0, 0.4));
            else
                apparence.setPosition(new Point2D.Double((j / 5 + 1) * 7.0 / 21.0 - (5 - j % 5) / 21.0, 0.4));
            
            apparence.setDimension(new Dimension2D((2.0 / 3.0) * (1.0 / 21.0), 0.4));
            apparence.setCouleurFond(Color.black);
        }
    }
    
    private void genererSon(Touche touche, int i, int j)
    {
        Note note = (Note)touche.getSon();
        note.setNom(notes.get(i).get(j).getNom());
        note.setOctave(notes.get(i).get(j).getOctave());
        note.setPersistance(500);
    }
}
;