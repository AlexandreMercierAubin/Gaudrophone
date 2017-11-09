package gaudrophone.Domaine.Generateur;

import gaudrophone.Domaine.Instrument.ApparenceTouche;
import gaudrophone.Domaine.Instrument.Instrument;
import gaudrophone.Domaine.Instrument.Touche;
import java.awt.geom.Point2D;
import java.util.List;

public class GenerateurGuitare extends GenerateurInstrument{
    List<Point2D> coords;
    
    @Override
    public Instrument generer()
    {
        Instrument instrument = new Instrument();
        for (int i = 0; i < 6; i++) // Cordes
        {
            for (int j = 0; j < 13; j++) // Frets
            {
                double x = j / 13.0 + 1.0 / 26.0;
                double y = (double)(i + 3) / 12.0 + 1.0 / 24.0;
                
                Touche touche = instrument.ajouterTouche(new Point2D.Double(x, y));
                ApparenceTouche apparence = touche.getApparence();
                
            }
        }
        
        return instrument;
    }
}
