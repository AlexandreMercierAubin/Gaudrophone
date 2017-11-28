package gaudrophone.Domaine.Dessin;

import gaudrophone.Domaine.ControleurInstrument;
import gaudrophone.Domaine.Dimension2D;
import gaudrophone.Domaine.Instrument.*;
import gaudrophone.Domaine.Outils;
import java.awt.BasicStroke;
import java.awt.FontMetrics;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
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
        
        redimensionner(touches, g2);
        
        for (Touche touche : touches)
        {   
            ApparenceTouche apparence = touche.getApparence();
            switch (apparence.getForme())
            {
                case Cercle:
                    dessinerCercle(touche, g2);
                    break;
                    
                case Rectangle:
                    dessinerRectangle(touche, g2);
                    break;
                    
                // Triangle, pentagone, hexagone
                default:
                    dessinerPolygone(touche, g2);
                    break;
            }
            dessinerBorduresTransversales(touche, g2);
            dessinerTexte(touche, g2);
        }
    }
    
    // Ajuste l'échelle du dessin des touches pour qu'elles soient toutes visibles
    private void redimensionner(List<Touche> touches, Graphics2D g2)
    {
        int minDimPanneau = (int)Math.min(dimensionPanneau.getWidth(), dimensionPanneau.getHeight());
        int maxX = 0;
        int maxY = 0;
        
        for (Touche touche: touches)
        {
            ApparenceTouche apparence = touche.getApparence();
            Point2D position = Outils.conversionPointRelatifPixel(touche.getApparence().getPosition(), minDimPanneau);
            Dimension2D dimension = Outils.conversionDimensionRelatifPixel(apparence.getDimension(), minDimPanneau);
            
            maxX = Math.max(maxX, (int)position.getX() + (int)dimension.getWidth() / 2);
            maxY = Math.max(maxY, (int)position.getY() + (int)dimension.getHeight() / 2);
        }
        
        double echelleX = dimensionPanneau.getWidth() / maxX;
        double echelleY = dimensionPanneau.getHeight() / maxY;
        
        if (echelleX < 1.0 || echelleY < 1.0)
        {
            double echelle = Math.min(echelleX, echelleY);
            g2.scale(echelle, echelle);
            controleur.setEchelleAffichage(echelle);
        }
    }
    
    private void dessinerCercle(Touche touche, Graphics2D g2)
    {
        int minDimPanneau = (int)Math.min(dimensionPanneau.getWidth(), dimensionPanneau.getHeight());
        
        ApparenceTouche apparence = touche.getApparence();
        Image image = apparence.getImageFond();
        Point2D position = Outils.conversionPointRelatifPixel(touche.getApparence().getPosition(), minDimPanneau);
        Dimension2D dimension = Outils.conversionDimensionRelatifPixel(apparence.getDimension(), minDimPanneau);
        boolean surbrillance = touche.getSurbrillance();
        
        // Dessin du cercle intérieur
        int x = (int)position.getX() - (int)dimension.getWidth() / 2;
        int y = (int)position.getY() - (int)dimension.getHeight() / 2;
        int width = (int)dimension.getWidth();
        int height = (int)dimension.getHeight();
        Ellipse2D ellipse = new Ellipse2D.Double(x, y, width, height);
        
        if (image == null)
        {
            g2.setColor(apparence.getCouleurFond());
            g2.fill(ellipse);
        }
        else
        {
            g2.setClip(ellipse);
            g2.drawImage(image, x, y, x + width, y + height, 0, 0, image.getWidth(null), image.getHeight(null), null);
            g2.setClip(null);
        }

        // Dessin de la bordure
        Bordure bordure = apparence.getBordure(0);

        int largeurBordure = Outils.conversionRelatifPixel(bordure.getLargeur(), minDimPanneau);
        if (surbrillance)
            largeurBordure += 2;
        
        if (bordure.getVisible() || surbrillance)
        {
            g2.setColor(bordure.getCouleur());
            g2.setStroke(new BasicStroke(largeurBordure));
            g2.drawOval(x, y, (int)dimension.getWidth(), (int)dimension.getHeight());
        }
    }
    
    private void dessinerRectangle(Touche touche, Graphics2D g2)
    {
        int minDimPanneau = (int)Math.min(dimensionPanneau.getWidth(), dimensionPanneau.getHeight());
        
        ApparenceTouche apparence = touche.getApparence();
        Image image = apparence.getImageFond();
        Point2D position = Outils.conversionPointRelatifPixel(touche.getApparence().getPosition(), minDimPanneau);
        Dimension2D dimension = Outils.conversionDimensionRelatifPixel(apparence.getDimension(), minDimPanneau);
        boolean surbrillance = touche.getSurbrillance();
        
        // Dessin du rectangle intérieur
        int x = (int)position.getX() - (int)dimension.getWidth() / 2;
        int y = (int)position.getY() - (int)dimension.getHeight() / 2;
        int width = (int)dimension.getWidth();
        int height = (int)dimension.getHeight();
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, width, height);
        
        if (image == null)
        {
            g2.setColor(apparence.getCouleurFond());
            g2.fill(rectangle);
        }
        else
        {
            g2.setClip(rectangle);
            g2.drawImage(image, x, y, x + width, y + height, 0, 0, image.getWidth(null), image.getHeight(null), null);
            g2.setClip(null);
        }
        
        // Dessin des bordures
        for (int i = 0; i < 4; i++)
        {
            Bordure bordure = apparence.getBordure(i);
            if (bordure.getVisible() || surbrillance)
            {
                int largeurBordure = Outils.conversionRelatifPixel(bordure.getLargeur(), minDimPanneau);
                if (surbrillance)
                    largeurBordure += 2;
                
                g2.setColor(bordure.getCouleur());
                g2.setStroke(new BasicStroke(largeurBordure));

                int x1 = i == 1 ? (int)rectangle.getMaxX() : (int)rectangle.getMinX();
                int y1 = i == 2 ? (int)rectangle.getMaxY() : (int)rectangle.getMinY();
                int x2 = i % 2 == 1 ? x1 : (int)rectangle.getMaxX();
                int y2 = i % 2 == 0 ? y1 : (int)rectangle.getMaxY();

                g2.drawLine(x1, y1, x2, y2);
            }
        }
    }
    
    private void dessinerPolygone(Touche touche, Graphics2D g2)
    {
        int minDimPanneau = (int)Math.min(dimensionPanneau.getWidth(), dimensionPanneau.getHeight());
        
        ApparenceTouche apparence = touche.getApparence();
        Image image = apparence.getImageFond();
        Point2D position = Outils.conversionPointRelatifPixel(touche.getApparence().getPosition(), minDimPanneau);
        Dimension2D dimension = Outils.conversionDimensionRelatifPixel(apparence.getDimension(), minDimPanneau);
        boolean surbrillance = touche.getSurbrillance();
        
        // Dessin du polygone intérieur
        int nbBordures = Outils.nbBordures(apparence.getForme());
        Polygon polygone = Outils.calculerPolygone(nbBordures, position, dimension);
        
        if (image == null)
        {
            g2.setColor(apparence.getCouleurFond());
            g2.fillPolygon(polygone);
        }
        else
        {
            int x1 = (int)position.getX() - (int)dimension.getWidth() / 2;
            int y1 = (int)position.getY() - (int)dimension.getHeight() / 2;
            int x2 = (int)position.getX() + (int)dimension.getWidth() / 2;
            int y2 = (int)position.getY() + (int)dimension.getHeight() / 2;
            
            g2.setClip(polygone);
            g2.drawImage(image, x1, y1, x2, y2, 0, 0, image.getWidth(null), image.getHeight(null), null);
            g2.setClip(null);
        }

        // Dessin des bordures
        for (int i = 0; i < nbBordures; i++)
        {
            Bordure bordure = apparence.getBordure(i);
            if (bordure.getVisible() || surbrillance)
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

                int largeurBordure = Outils.conversionRelatifPixel(bordure.getLargeur(), minDimPanneau);
                if (surbrillance)
                    largeurBordure += 2;
                
                g2.setColor(bordure.getCouleur());
                g2.setStroke(new BasicStroke(largeurBordure));
                g2.drawLine(x1, y1, x2, y2);
            }
        }
    }
    
    private void dessinerBorduresTransversales(Touche touche, Graphics2D g2)
    {
        int minDimPanneau = (int)Math.min(dimensionPanneau.getWidth(), dimensionPanneau.getHeight());
        
        ApparenceTouche apparence = touche.getApparence();
        Point2D position = Outils.conversionPointRelatifPixel(touche.getApparence().getPosition(), minDimPanneau);
        Dimension2D dimension = Outils.conversionDimensionRelatifPixel(apparence.getDimension(), minDimPanneau);
        
        int premiereBordureTransversale = Outils.nbBordures(apparence.getForme());
        Bordure bordureHorizontale = apparence.getBordure(premiereBordureTransversale);
        Bordure bordureVerticale = apparence.getBordure(premiereBordureTransversale + 1);

        if (bordureHorizontale.getVisible())
        {
            int largeurBordure = Outils.conversionRelatifPixel(bordureHorizontale.getLargeur(), minDimPanneau);
            g2.setColor(bordureHorizontale.getCouleur());
            g2.setStroke(new BasicStroke(largeurBordure));
            int x1 = (int)position.getX() - (int)dimension.getWidth() / 2;
            int x2 = (int)position.getX() + (int)dimension.getWidth() / 2;
            int y = (int)position.getY();
            g2.drawLine(x1, y, x2, y);
        }

        if (bordureVerticale.getVisible())
        {
            int largeurBordure = Outils.conversionRelatifPixel(bordureVerticale.getLargeur(), minDimPanneau);
            g2.setColor(bordureVerticale.getCouleur());
            g2.setStroke(new BasicStroke(largeurBordure));
            int y1 = (int)position.getY() - (int)dimension.getHeight() / 2;
            int y2 = (int)position.getY() + (int)dimension.getHeight() / 2;
            int x = (int)position.getX();
            g2.drawLine(x, y1, x, y2);
        }
    }
    
    private void dessinerTexte(Touche touche, Graphics2D g2)
    {
        ApparenceTouche apparence = touche.getApparence();
        Son son = touche.getSon();
        String texte = "";
        
        if (apparence.isAfficherNom())
            texte += touche.getNom();
        if (apparence.isAfficherNote() && son instanceof Note)
            texte += " " + ((Note)son).getNom();
        if (apparence.isAfficherOctave() && son instanceof Note)
            texte += " " + ((Note)son).getOctave();
        
        texte = texte.trim();
        
        if (!texte.equals(""))
        {
            int minDimPanneau = (int)Math.min(dimensionPanneau.getWidth(), dimensionPanneau.getHeight());
            Point2D position = Outils.conversionPointRelatifPixel(touche.getApparence().getPosition(), minDimPanneau);
            Dimension2D dimension = Outils.conversionDimensionRelatifPixel(apparence.getDimension(), minDimPanneau);
            int x = (int)position.getX() - (int)dimension.getWidth() / 2;
            int y = (int)position.getY() - (int)dimension.getHeight() / 2;
            int width = (int)dimension.getWidth();
            int height = (int)dimension.getHeight();
            
            FontMetrics metrics = g2.getFontMetrics();
            int xTexte = x + (width - metrics.stringWidth(texte)) / 2;
            int yTexte = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
            g2.drawString(texte, xTexte, yTexte);
        }
    }
}
