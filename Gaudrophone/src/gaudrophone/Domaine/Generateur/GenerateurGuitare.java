package gaudrophone.Domaine.Generateur;

import gaudrophone.Domaine.Dimension2D;
import gaudrophone.Domaine.Enums.Forme;
import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.Instrument.ApparenceTouche;
import gaudrophone.Domaine.Instrument.Bordure;
import gaudrophone.Domaine.Instrument.Instrument;
import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Touche;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateurGuitare extends GenerateurInstrument{
    int timbre;
    List<List<Note>> notes;
    
    public GenerateurGuitare()
    {
        timbre = 25; // Guitare
        
        notes = new ArrayList<>();
        
        notes.add(Arrays.asList(
                new Note(timbre, NomNote.F, 6),
                new Note(timbre, NomNote.FSharp, 6),
                new Note(timbre, NomNote.G, 6),
                new Note(timbre, NomNote.GSharp, 6),
                new Note(timbre, NomNote.A, 6),
                new Note(timbre, NomNote.ASharp, 6),
                new Note(timbre, NomNote.B, 6),
                new Note(timbre, NomNote.C, 7),
                new Note(timbre, NomNote.CSharp, 7),
                new Note(timbre, NomNote.D, 7),
                new Note(timbre, NomNote.DSharp, 7),
                new Note(timbre, NomNote.E, 7),
                new Note(timbre, NomNote.F, 7)
        ));
        notes.add(Arrays.asList(
                new Note(timbre, NomNote.ASharp, 5),
                new Note(timbre, NomNote.B, 5),
                new Note(timbre, NomNote.C, 6),
                new Note(timbre, NomNote.CSharp, 6),
                new Note(timbre, NomNote.D, 6),
                new Note(timbre, NomNote.DSharp, 6),
                new Note(timbre, NomNote.E, 6),
                new Note(timbre, NomNote.F, 6),
                new Note(timbre, NomNote.FSharp, 6),
                new Note(timbre, NomNote.G, 6),
                new Note(timbre, NomNote.GSharp, 6),
                new Note(timbre, NomNote.A, 6),
                new Note(timbre, NomNote.ASharp, 6)
        ));
        notes.add(Arrays.asList(
                new Note(timbre, NomNote.DSharp, 5),
                new Note(timbre, NomNote.E, 5),
                new Note(timbre, NomNote.F, 5),
                new Note(timbre, NomNote.FSharp, 5),
                new Note(timbre, NomNote.G, 5),
                new Note(timbre, NomNote.GSharp, 5),
                new Note(timbre, NomNote.A, 5),
                new Note(timbre, NomNote.ASharp, 5),
                new Note(timbre, NomNote.B, 5),
                new Note(timbre, NomNote.C, 6),
                new Note(timbre, NomNote.CSharp, 6),
                new Note(timbre, NomNote.D, 6),
                new Note(timbre, NomNote.DSharp, 6)
        ));
        notes.add(Arrays.asList(
                new Note(timbre, NomNote.GSharp, 4),
                new Note(timbre, NomNote.A, 4),
                new Note(timbre, NomNote.ASharp, 4),
                new Note(timbre, NomNote.B, 4),
                new Note(timbre, NomNote.C, 5),
                new Note(timbre, NomNote.CSharp, 5),
                new Note(timbre, NomNote.D, 5),
                new Note(timbre, NomNote.DSharp, 5),
                new Note(timbre, NomNote.E, 5),
                new Note(timbre, NomNote.F, 5),
                new Note(timbre, NomNote.FSharp, 5),
                new Note(timbre, NomNote.G, 5),
                new Note(timbre, NomNote.GSharp, 5)
        ));
        notes.add(Arrays.asList(
                new Note(timbre, NomNote.C, 4),
                new Note(timbre, NomNote.CSharp, 4),
                new Note(timbre, NomNote.D, 4),
                new Note(timbre, NomNote.DSharp, 4),
                new Note(timbre, NomNote.E, 4),
                new Note(timbre, NomNote.F, 4),
                new Note(timbre, NomNote.FSharp, 4),
                new Note(timbre, NomNote.G, 4),
                new Note(timbre, NomNote.GSharp, 4),
                new Note(timbre, NomNote.A, 4),
                new Note(timbre, NomNote.ASharp, 4),
                new Note(timbre, NomNote.B, 4),
                new Note(timbre, NomNote.C, 5)
        ));
        notes.add(Arrays.asList(
                new Note(timbre, NomNote.F, 3),
                new Note(timbre, NomNote.FSharp, 3),
                new Note(timbre, NomNote.G, 3),
                new Note(timbre, NomNote.GSharp, 3),
                new Note(timbre, NomNote.A, 3),
                new Note(timbre, NomNote.ASharp, 3),
                new Note(timbre, NomNote.B, 3),
                new Note(timbre, NomNote.C, 4),
                new Note(timbre, NomNote.CSharp, 4),
                new Note(timbre, NomNote.D, 4),
                new Note(timbre, NomNote.DSharp, 4),
                new Note(timbre, NomNote.E, 4),
                new Note(timbre, NomNote.F, 4)
        ));
    }
    
    @Override
    public Instrument generer()
    {
        Instrument instrument = new Instrument();
        instrument.setNom("Guitare");
        instrument.setTimbre(timbre);
        
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 13; j++)
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
        double x = j / 13.0 + 1.0 / 26.0;
        double y = (double)(i + 3) / 12.0 + 1.0 / 24.0;
        ApparenceTouche apparence = touche.getApparence();
        
        apparence.setPosition(new Point2D.Double(x, y));
        apparence.setDimension(new Dimension2D(1.0 / 13.0, 1.02 / 12.0));
        apparence.setForme(Forme.Rectangle);
        apparence.setCouleurFond(new Color(72, 52, 32));

        apparence.getBordure(0).setVisible(false); // Haut
        apparence.getBordure(1).setCouleur(Color.gray); // Droite
        apparence.getBordure(2).setVisible(false); // Bas
        apparence.getBordure(3).setCouleur(Color.gray); // Gauche

        Bordure corde = apparence.getBordure(4);
        corde.setVisible(true);
        corde.setCouleur(new Color(198, 198, 198));
        corde.setLargeur(0.0003 * i + 0.001);
    }
    
    private void genererSon(Touche touche, int i, int j)
    {
        Note note = (Note)touche.getSon();
        note.setNom(notes.get(i).get(j).getNom());
        note.setOctave(notes.get(i).get(j).getOctave());
        note.setPersistance(500);
    }
}
