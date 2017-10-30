
package gaudrophone.Domaine;

import java.awt.Polygon;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Outils {
    public String readFile(String strNomFichier, String strTexteInitial)
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
    
    public Point2D conversionPointPixelRelatif(Point2D coordPixel,Dimension2D dimensionPanneau)
    {
        //ajouter calcul
        return null;
    }
    
    public Point2D conversionPointRelatifPixel(Point2D coordRelatif,Dimension2D dimensionPanneau)
    {
        //ajouter calcul
        return null;
    }
    
    public Dimension2D conversionDimensionPixelRelatif(Dimension2D dimensionPixel,Dimension2D dimensionPanneau)
    {
        //ajouter calcul
        return null;
    }
    public Dimension2D conversionDimensionRelatifPixel(Dimension2D dimensionRelative,Dimension2D dimensionPanneau)
    {
        //ajouter calcul
        return null;
    }
    
    public int getMidiNoteNumber(/*NomNote note,*/int octave)
    {
        //ajouter code
        return -1;
    }
    
    public Polygon calculerPolygone(int nbSommets, Point2D centrePoly, Dimension2D dimension)
    {
        //ajouter calcul
        return null;
    }
}
