package gaudrophone.Domaine.Dessin;

import gaudrophone.Domaine.ControleurInstrument;
import gaudrophone.Domaine.Dimension2D;
import gaudrophone.Domaine.Enums.Forme;
import gaudrophone.Domaine.Instrument.*;
import gaudrophone.Domaine.Outils;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

public class DessinateurInstrument {
    ControleurInstrument controleur;
    Dimension2D dimensionPanneau;
    
    public DessinateurInstrument(ControleurInstrument controleur,Dimension2D dimensionPanneau)
    {
        this.controleur = controleur;
        this.dimensionPanneau = dimensionPanneau;
    }
    
    public void dessiner(Graphics g)
    {
        List<Touche> touches = controleur.getInstrument().getTouches();
        
        for (Touche touche : touches)
        {
            ApparenceTouche apparence = touche.getApparence();
            Point2D position = Outils.conversionPointRelatifPixel(touche.getPosition(), dimensionPanneau);
            Dimension2D dimension = Outils.conversionDimensionRelatifPixel(apparence.getDimension(), dimensionPanneau);
            Forme forme = apparence.getForme();
            
            switch (forme)
            {
                case Cercle:
                    Bordure bordure0 = apparence.getBordure(0);
                    if (bordure0 != null)
                    {
                        // Dessin d'un grand cercle qui servira de bordure principale au cerlce
                        g.setColor(bordure0.getCouleur());
                        Dimension2D dimensionBordure = Outils.conversionDimensionRelatifPixel(new Dimension2D(bordure0.getLargeur(), bordure0.getLargeur()), dimensionPanneau);
                        Dimension2D dimensionTotale = new Dimension2D(dimension.getWidth() + dimensionBordure.getWidth() * 2, dimension.getHeight() + dimensionBordure.getHeight() * 2);
                        g.fillOval((int)position.getX(), (int)position.getY(), (int)dimensionTotale.getWidth(), (int)dimensionTotale.getHeight());
                    }
                    
                    // Dessin du cercle
                    g.setColor(apparence.getCouleurFond());
                    g.fillOval((int)position.getX(), (int)position.getY(), (int)dimension.getWidth(), (int)dimension.getHeight());
                    break;
                    
                case Rectangle:
                    // TO-DO
                    break;
                    
                // Triangle, pentagone, hexagone
                default:
                    // TO-DO
                    break;
            }
            
            // Dessin des bordures transversales
            int premiereBordureTransversale = Outils.nbBordures(forme);
            Bordure bordureHorizontale = apparence.getBordure(premiereBordureTransversale);
            Bordure bordureVerticale = apparence.getBordure(premiereBordureTransversale + 1);
            Dimension2D dimensionBordures = Outils.conversionDimensionRelatifPixel(new Dimension2D(bordureHorizontale.getLargeur(), bordureVerticale.getLargeur()), dimensionPanneau);
            
            if (bordureHorizontale != null)
            {
                int x = (int)position.getX() - (int)dimension.getWidth() / 2;
                int y = (int)position.getY() - (int)dimensionBordures.getHeight() / 2;
                g.fillRect(x, y, (int)dimension.getWidth(), (int)dimensionBordures.getHeight());
            }
            
            if (bordureVerticale != null)
            {
                int x = (int)position.getX() - (int)dimensionBordures.getWidth() / 2;
                int y = (int)position.getY() - (int)dimension.getHeight() / 2;
                g.fillRect(x, y, (int)dimensionBordures.getWidth(), (int)dimension.getHeight());
            }
        }
    }
}
