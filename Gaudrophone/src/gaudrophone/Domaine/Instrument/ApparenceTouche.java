package gaudrophone.Domaine.Instrument;

import gaudrophone.Domaine.Dimension2D;
import gaudrophone.Domaine.Enums.Forme;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.util.List;

public class ApparenceTouche {
    Forme forme;
    Color couleurFond;
    Image imageFond;
    Dimension2D dimension;
    List<Bordure> bordures;
    List<Point2D> coins;
    
    public ApparenceTouche()
    {
        forme = Forme.Cercle;
        couleurFond = Color.BLACK;
        dimension = new Dimension2D(1,1);
        
    }
    
    public Forme getForme()
    {
        return forme;
    }
    
    public void setForme(Forme forme)
    {
        this.forme = forme;
    }
    
    public Color getCouleurFond() {
        return couleurFond;
    }

    public void setCouleurFond(Color couleurFond) {
        this.couleurFond = couleurFond;
    }

    public Image getImageFond() {
        return imageFond;
    }

    public void setImageFond(Image imageFond) {
        this.imageFond = imageFond;
    }

    public Dimension2D getDimension() {
        return dimension;
    }

    public void setDimension(Dimension2D dimension) {
        this.dimension = dimension;
    }
    
    public Bordure getBordure(int index)
    {
        if (bordures.size() > index)
            return bordures.get(index);
        else
            return null;
    }
    
    public List<Point2D> getCoins() {
        return coins;
    }

    public void setCoins(List<Point2D> coins) {
        this.coins = coins;
    }
    
}
