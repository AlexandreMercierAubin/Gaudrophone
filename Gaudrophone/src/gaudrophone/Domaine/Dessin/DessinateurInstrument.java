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
import java.awt.Polygon;
import java.awt.Rectangle;
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
            
            int x, y;
            Bordure bordure;
            
            switch (forme)
            {
                case Cercle:
                    // Dessin du cercle intérieur
                    g2.setColor(apparence.getCouleurFond());
                    x = (int)position.getX() - (int)dimension.getWidth() / 2;
                    y = (int)position.getY() - (int)dimension.getHeight() / 2;
                    g2.fillOval(x, y, (int)dimension.getWidth(), (int)dimension.getHeight());
                    
                    // Dessin de la bordure
                    bordure = apparence.getBordure(0);
                    if (bordure != null)
                    {
                        g2.setColor(bordure.getCouleur());
                        g2.setStroke(new BasicStroke(bordure.getLargeur()));
                        g2.drawOval(x, y, (int)dimension.getWidth(), (int)dimension.getHeight());
                    }
                    break;
                    
                case Rectangle:
                    // Dessin du rectangle intérieur
                    g2.setColor(apparence.getCouleurFond());
                    x = (int)position.getX() - (int)dimension.getWidth() / 2;
                    y = (int)position.getY() - (int)dimension.getHeight() / 2;
                    Rectangle rectangle = new Rectangle(x, y, (int)dimension.getWidth(), (int)dimension.getHeight());
                    g2.fill(rectangle);
                    
                    // Dessin des bordures
                    for (int i = 0; i < 4; i++)
                    {
                        bordure = apparence.getBordure(i);
                        g2.setColor(bordure.getCouleur());
                        g2.setStroke(new BasicStroke(bordure.getLargeur()));
                        
                        int x1 = i == 1 ? (int)rectangle.getMaxX() : (int)rectangle.getMinX();
                        int y1 = i == 2 ? (int)rectangle.getMaxY() : (int)rectangle.getMinY();
                        int x2 = i % 2 == 1 ? x1 : (int)rectangle.getMaxX();
                        int y2 = i % 2 == 0 ? y1 : (int)rectangle.getMaxY();
                        
                        g2.drawLine(x1, y1, x2, y2);
                    }
                    break;
                    
                // Triangle, pentagone, hexagone
                default:
                    // Dessin du polygone intérieur
                    int nbBordures = Outils.nbBordures(forme);
                    Polygon polygone = Outils.calculerPolygone(nbBordures, position, dimension);
                    g2.setColor(apparence.getCouleurFond());
                    g2.fillPolygon(polygone);
                    
                    // Dessin des bordures
                    for (int i = 0; i < nbBordures; i++)
                    {
                        int x1, y1, x2, y2;
                        
                        x1 = polygone.xpoints[i];
                        y1 = polygone.ypoints[i];
                        
                        if (i == nbBordures - 1)
                        {
                            x2 = polygone.xpoints[0];
                            y2 = polygone.ypoints[0];
                        }
                        else
                        {
                            x2 = polygone.xpoints[i + 1];
                            y2 = polygone.ypoints[i + 1];
                        }
                        
                        bordure = apparence.getBordure(i);
                        g2.setColor(bordure.getCouleur());
                        g2.setStroke(new BasicStroke(bordure.getLargeur()));
                        g2.drawLine(x1, y1, x2, y2);
                    }
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
