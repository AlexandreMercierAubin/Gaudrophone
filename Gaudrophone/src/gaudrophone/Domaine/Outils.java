
package gaudrophone.Domaine;

import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.Enums.Forme;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

public class Outils {
    public static String readFile(String strNomFichier, String strTexteInitial)
    {
        String strTexte=strTexteInitial;
        String strChemin = new File("").getAbsolutePath();
        try (BufferedReader brLecteur = new BufferedReader(new InputStreamReader(new FileInputStream(strChemin.concat(strNomFichier)),"ISO-8859-1"))) 
        {
            
            String strLigne;

            while ((strLigne = brLecteur.readLine()) != null) 
            {
                   strTexte+=strLigne + "\r\n";
            }

        } catch (IOException e) 
        {
                e.printStackTrace();
        }
        return strTexte;
    }
    
    // Retourne l'équivalent en coordonnées relatives d'un point donné en
    // coordonnées pixel où (0.0, 0.0) est le coin supérieur gauche et
    // (1.0, 1.0) est le coin inférieur droit.
    public static Point2D conversionPointPixelRelatif(Point2D coordPixel,Dimension2D dimensionPanneau)
    {
        double x = coordPixel.getX() / dimensionPanneau.getWidth();
        double y = coordPixel.getY() / dimensionPanneau.getHeight();
        return new Point2D.Double(x, y);
    }
    
    // Retourne l'équivalent en coordonnées pixel d'un point donné en
    // coordonnées relatives où (0.0, 0.0) est le coin supérieur gauche et
    // (1.0, 1.0) est le coin inférieur droit.
    public static Point2D conversionPointRelatifPixel(Point2D coordRelatif,Dimension2D dimensionPanneau)
    {
        int x = (int)(coordRelatif.getX() * dimensionPanneau.getWidth());
        int y = (int)(coordRelatif.getY() * dimensionPanneau.getHeight());
        return new Point2D.Double(x, y);
    }
    
    // Retourne l'équivalent en dimension relative d'une dimension donnée en
    // pixels, où chaque dimension est donnée comme fraction de la dimension
    // totale du panneau.
    public static Dimension2D conversionDimensionPixelRelatif(Dimension2D dimensionPixel,Dimension2D dimensionPanneau)
    {
        double largeur = dimensionPixel.getWidth() / dimensionPanneau.getWidth();
        double hauteur = dimensionPixel.getHeight() / dimensionPanneau.getHeight();
        return new Dimension2D(largeur, hauteur);
    }
    
    // Retourne l'équivalent en dimension pixel d'une dimension donnée en
    // valeurs relatives, où chaque dimension est donnée comme fraction de la 
    // dimension totale du panneau.
    public static Dimension2D conversionDimensionRelatifPixel(Dimension2D dimensionRelative,Dimension2D dimensionPanneau)
    {
        int largeur = (int)(dimensionRelative.getWidth() * dimensionPanneau.getWidth());
        int hauteur = (int)(dimensionRelative.getHeight() * dimensionPanneau.getHeight());
        return new Dimension2D(largeur, hauteur);
    }
    
    public static int getMidiNoteNumber(NomNote note, int octave)
    {
        //Calcul du nombre de la note Midi
        int midiNote;
        int midiOctave = octave * 12 ;
        int numNote = note.getNumNote();        
        
        midiNote = midiOctave + numNote;
        return midiNote;
    }
    
    public static Polygon calculerPolygone(int nbSommets, Point2D centrePoly, Dimension2D dimension)
    {
        if(nbSommets>0)
        {
            //la fonction prend les points en pixels pour son usage de polygone
            double ecart = 360 / nbSommets;
            int xPoly[]=new int[nbSommets];
            int yPoly[]=new int[nbSommets];

            //pour trouver tous les points
            for (int i = 0; i < nbSommets; i++)
            {
                //Calcul d'un point selon le nombre de sommets 
                //en pourcentage
                double x = Math.sin(Math.toRadians(ecart*i));
                double y = -Math.cos(Math.toRadians(ecart*i));

                //remise à l'échelle du point
                x = (dimension.getWidth()/2)*x;
                y = (dimension.getHeight()/2)*y;

                //réajustement par rapport au point centre
                x = centrePoly.getX()+x;
                y = centrePoly.getY()+y;

                //ajouter la valeur au tableau du Polygone
                xPoly[i]=(int)Math.round(x);
                yPoly[i]=(int)Math.round(y);

            }

            Polygon poly = new Polygon(xPoly, yPoly, nbSommets);
            return poly;
        }
        else
        {
            return null;
        }
    }
    
    public static int nbBordures(Forme forme)
    {
        switch(forme)
        {
            case Cercle:
                return 1;
            case Triangle:
                return 3;
            case Rectangle:
                return 4;
            case Pentagone:
                return 5;
            case Hexagone:
                return 6;
            default:
                return 0;
        }
    }
}
