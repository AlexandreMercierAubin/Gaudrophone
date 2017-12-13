package gaudrophone.Domaine.Instrument;

import gaudrophone.Domaine.Dimension2D;
import gaudrophone.Domaine.Enums.Forme;
import gaudrophone.Domaine.Outils;
import java.awt.Color;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class ApparenceTouche  implements Serializable
{
    Forme forme;
    Color couleurFond;
    transient Image imageFond;
    Dimension2D dimension;
    List<Bordure> bordures;
    Point2D position;
    boolean afficherNom;
    boolean afficherNote;
    boolean afficherOctave;
    boolean afficherCle;

    public boolean isAfficherCle() {
        return afficherCle;
    }

    public void setAfficherCle(boolean afficherCle) {
        this.afficherCle = afficherCle;
    }

    public boolean isAfficherNom() {
        return afficherNom;
    }

    public void setAfficherNom(boolean afficherNom) {
        this.afficherNom = afficherNom;
    }

    public boolean isAfficherNote() {
        return afficherNote;
    }

    public void setAfficherNote(boolean afficherNote) {
        this.afficherNote = afficherNote;
    }

    public boolean isAfficherOctave() {
        return afficherOctave;
    }

    public void setAfficherOctave(boolean afficherOctave) {
        this.afficherOctave = afficherOctave;
    }
    
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
        afficherNom = false;
        afficherNote = false;
        afficherOctave = false;
        
        initialiserBordures();
    }
    
    public Forme getForme()
    {
        return forme;
    }
    
    public void setForme(Forme forme)
    {
        if (forme != this.forme)
        {
            this.forme = forme;
            initialiserBordures();
        }
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
        if (bordures == null)
            bordures = new ArrayList<Bordure>();
        
        int nbBordures = Outils.nbBordures(forme) + 2;
        if (nbBordures > bordures.size())
        {
            for (int i = 0; i < nbBordures; i++)
            {
                bordures.add(new Bordure());
                bordures.get(i).setVisible(true);
            }
        }
        else
        {
            for (int i = bordures.size() - 1; i >= nbBordures; i--)
                bordures.remove(i);
        }
        
        // Bordures transversales invisibles
        bordures.get(nbBordures - 2).setVisible(false);
        bordures.get(nbBordures - 1).setVisible(false);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
        boolean ecrireImage = imageFond != null;
        out.writeBoolean(ecrireImage);
        if (ecrireImage)
            ImageIO.write((RenderedImage)imageFond, "png", out);
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        final boolean lireImage = in.readBoolean();
        if (lireImage)
            imageFond = ImageIO.read(in);
    }
}
