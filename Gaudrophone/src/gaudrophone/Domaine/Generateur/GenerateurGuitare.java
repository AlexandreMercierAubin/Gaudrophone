package gaudrophone.Domaine.Generateur;

import gaudrophone.Domaine.Dimension2D;
import gaudrophone.Domaine.Enums.Forme;
import gaudrophone.Domaine.Instrument.ApparenceTouche;
import gaudrophone.Domaine.Instrument.Bordure;
import gaudrophone.Domaine.Instrument.Instrument;
import gaudrophone.Domaine.Instrument.Touche;
import java.awt.Color;
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
                
                apparence.setDimension(new Dimension2D(1.0 / 13.0, 1.01 / 12.0));
                apparence.setForme(Forme.Rectangle);
                apparence.setCouleurFond(new Color(72, 52, 32));
                
                apparence.getBordure(0).setVisible(false); // Haut
                apparence.getBordure(1).setCouleur(Color.gray); // Droite
                apparence.getBordure(2).setVisible(false); // Bas
                apparence.getBordure(3).setCouleur(Color.gray); // Gauche
                
                Bordure corde = apparence.getBordure(4);
                corde.setVisible(true);
                corde.setCouleur(new Color(198, 198, 198));
                corde.setLargeur(0.0015 * i);
            }
        }
        return instrument;
    }
}
