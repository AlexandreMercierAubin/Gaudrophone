package gaudrophone.Domaine.Dessin;

import gaudrophone.Domaine.ControleurInstrument;
import gaudrophone.Domaine.Dimension2D;
import gaudrophone.Domaine.Enums.Forme;
import gaudrophone.Domaine.Instrument.*;
import gaudrophone.Domaine.Outils;
import java.awt.BasicStroke;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
        Graphics2D g2 = (Graphics2D)g;
        List<Touche> touches = controleur.getInstrument().getTouches();
        
        for (Touche touche : touches)
        {
            ApparenceTouche apparence = touche.getApparence();
            Point2D position = Outils.conversionPointRelatifPixel(touche.getPosition(), dimensionPanneau);
            Dimension2D dimension = Outils.conversionDimensionRelatifPixel(apparence.getDimension(), dimensionPanneau);
            Forme forme = apparence.getForme();
            
            int x = 0;
            int y = 0;
            
            switch (forme)
            {
                case Cercle:
                    // Dessin du cercle intérieur
                    g2.setColor(apparence.getCouleurFond());
                    x = (int)position.getX() - (int)dimension.getWidth() / 2;
                    y = (int)position.getY() - (int)dimension.getHeight() / 2;
                    g2.fillOval(x, y, (int)dimension.getWidth(), (int)dimension.getHeight());
                    
                    // Dessin de la bordure
                    Bordure bordure = apparence.getBordure(0);
                    g2.setColor(bordure.getCouleur());
                    g2.setStroke(new BasicStroke(bordure.getLargeur()));
                    g2.drawOval(x, y, (int)dimension.getWidth(), (int)dimension.getHeight());
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
            
            if (bordureHorizontale != null)
            {
                g2.setColor(bordureHorizontale.getCouleur());
                g2.setStroke(new BasicStroke(bordureHorizontale.getLargeur()));
                int x1 = (int)position.getX() - (int)dimension.getWidth() / 2;
                int x2 = (int)position.getX() + (int)dimension.getWidth() / 2;
                y = (int)position.getY();
                g2.drawLine(x1, y, x2, y);
            }
            
            if (bordureVerticale != null)
            {
                g2.setColor(bordureVerticale.getCouleur());
                g2.setStroke(new BasicStroke(bordureVerticale.getLargeur()));
                int y1 = (int)position.getY() - (int)dimension.getHeight() / 2;
                int y2 = (int)position.getY() + (int)dimension.getHeight() / 2;
                x = (int)position.getX();
                g2.drawLine(x, y1, x, y2);
            }
        }
    }
}
