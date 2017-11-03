
package gaudrophone.Domaine;

import gaudrophone.Domaine.Enums.NomNote;
import java.awt.Polygon;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

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
    
    public int getMidiNoteNumber(NomNote note, int octave)
    {
        //Calcul du nombre de la note Midi
        int midiNote;
        int midiOctave = octave * 12 ;
        int numNote = note.getNumNote();        
        
        midiNote = midiOctave + numNote;
        return midiNote;
    }
    
    public Polygon calculerPolygone(int nbSommets, Point2D centrePoly, Dimension2D dimension)
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
                double x = Math.cos(Math.toRadians(ecart*i));
                double y = Math.sin(Math.toRadians(ecart*i));

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
}
