package gaudrophone.Domaine.Instrument;

import gaudrophone.Domaine.Dimension2D;
import gaudrophone.Domaine.Enums.Forme;
import gaudrophone.Domaine.Outils;
import java.awt.Color;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;


public class ApparenceTouche 
{
    Forme forme;
    Color couleurFond;
    Image imageFond;
    Dimension2D dimension;
    List<Bordure> bordures;
    Point2D position;
    
    public Point2D getPosition()
    {
        return position;
    }
    
    public void setPosition(Point2D valeur)
    {
        position = valeur;
    }
    
    public ApparenceTouche()
    {
        forme = Forme.Cercle;
        couleurFond = Color.BLACK;
        dimension = new Dimension2D(0.05,0.05);
        
        initialiserBordures();
    }
    
    public Forme getForme()
    {
        return forme;
    }
    
    public void setForme(Forme forme)
    {
        this.forme = forme;
        initialiserBordures();
    }
    
    public Color getCouleurFond() 
    {
        return couleurFond;
    }

    public void setCouleurFond(Color couleurFond) 
    {
        this.couleurFond = couleurFond;
    }

    public Image getImageFond() 
    {
        return imageFond;
    }

    public void setImageFond(Image imageFond) 
    {
        this.imageFond = imageFond;
    }

    public Dimension2D getDimension() 
    {
        return dimension;
    }

    public void setDimension(Dimension2D dimension) 
    {
        this.dimension = dimension;
    }
    
    public Bordure getBordure(int index)
    {
        if (bordures.size() > index)
            return bordures.get(index);
        else
            return null;
    }
    
    public int getNbBordures()
    {
        return bordures.size();
    }
    
    private void initialiserBordures()
    {
        bordures = new ArrayList<Bordure>();
        
        int nbBordures = Outils.nbBordures(forme) + 2;
        for (int i = 0; i < nbBordures; i++)
            bordures.add(new Bordure());
        
        // Bordures transversales invisibles
        bordures.get(nbBordures - 2).setVisible(false);
        bordures.get(nbBordures - 1).setVisible(false);
    }
}
