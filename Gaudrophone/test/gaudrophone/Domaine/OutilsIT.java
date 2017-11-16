/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaudrophone.Domaine;

import gaudrophone.Domaine.Enums.Forme;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OutilsIT {
    
    public OutilsIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

//    /**
//     * Test of readFile method, of class Outils.
//     */
//    @Test
//    public void testReadFile() {
//        System.out.println("readFile");
//        String strNomFichier = "";
//        String strTexteInitial = "";
//        Outils instance = new Outils();
//        String expResult = "";
//        String result = instance.readFile(strNomFichier, strTexteInitial);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of conversionPointPixelRelatif method, of class Outils.
     */
    @Test
    public void testConversionPointPixelRelatif() {
        System.out.println("conversionPointPixelRelatif");
        Point2D coordPixel = new Point2D.Double(250, 500);
        Point2D expResult = new Point2D.Double(0.125, 0.25);
        Point2D result = Outils.conversionPointPixelRelatif(coordPixel, 2000);
        assertEquals(expResult.getX(), result.getX(), 0.0);
        assertEquals(expResult.getY(), result.getY(), 0.0);
    }

    /**
     * Test of conversionPointRelatifPixel method, of class Outils.
     */
    @Test
    public void testConversionPointRelatifPixel() {
        System.out.println("conversionPointRelatifPixel");
        Point2D coordRelatif = new Point2D.Double(0.125, 0.25);
        Point2D expResult = new Point2D.Double(250, 500);
        Point2D result = Outils.conversionPointRelatifPixel(coordRelatif, 2000);
        assertEquals(expResult.getX(), result.getX(), 0.0);
        assertEquals(expResult.getY(), result.getY(), 0.0);
    }

    /**
     * Test of conversionDimensionPixelRelatif method, of class Outils.
     */
    @Test
    public void testConversionDimensionPixelRelatif() {
        System.out.println("conversionDimensionPixelRelatif");
        Dimension2D dimensionPixel = new Dimension2D(250, 500);
        Dimension2D expResult = new Dimension2D(0.125, 0.25);
        Dimension2D result = Outils.conversionDimensionPixelRelatif(dimensionPixel, 2000);
        assertEquals(expResult.getWidth(), result.getWidth(), 0.0);
        assertEquals(expResult.getHeight(), result.getHeight(), 0.0);
    }

    /**
     * Test of conversionDimensionRelatifPixel method, of class Outils.
     */
    @Test
    public void testConversionDimensionRelatifPixel() {
        System.out.println("conversionDimensionRelatifPixel");
        Dimension2D dimensionRelative = new Dimension2D(0.125, 0.25);
        Dimension2D expResult = new Dimension2D(250, 500);
        Dimension2D result = Outils.conversionDimensionRelatifPixel(dimensionRelative, 2000);
        assertEquals(expResult.getWidth(), result.getWidth(), 0.0);
        assertEquals(expResult.getHeight(), result.getHeight(), 0.0);
    }
    
    @Test
    public void testConversionPixelRelatif() {
        System.out.println("conversionPixelRelatif");
        double expResult = 0.25;
        double result = Outils.conversionPixelRelatif(500, 2000);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testConversionRelatifPixel() {
        System.out.println("conversionRelatifPixel");
        int expResult = 500;
        int result = Outils.conversionRelatifPixel(0.25, 2000);
        assertEquals(expResult, result);
    }

//    /**
//     * Test of getMidiNoteNumber method, of class Outils.
//     */
//    @Test
//    public void testGetMidiNoteNumber() {
//        System.out.println("getMidiNoteNumber");
//        int octave = 0;
//        Outils instance = new Outils();
//        int expResult = 0;
//        int result = instance.getMidiNoteNumber(octave);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of calculerPolygone method, of class Outils.
     */
    @Test
    public void testCalculerPolygone() {
        System.out.println("calculerPolygone");
        int nbSommets = 4;
        Point2D.Double centrePoly = new Point2D.Double(0,0);
        Dimension2D dimension = new Dimension2D(100,100);
        Outils instance = new Outils();
        
        int[] xPoly= {0,50,0,-50};
        int[] yPoly= {-50,0,50,0};
       
        Polygon result = instance.calculerPolygone(nbSommets, centrePoly, dimension);
        assertEquals(4,result.npoints);
        for(int i=0;i<4;i++)
        {
            assertEquals(xPoly[i],result.xpoints[i]);
            assertEquals(yPoly[i],result.ypoints[i]);
        }
        
        
        //test avec un centre autre
        centrePoly = new Point2D.Double(50,50);
        int[] xPoly2= {50,100,50,0};
        int[] yPoly2= {0,50,100,50};
        result = instance.calculerPolygone(nbSommets, centrePoly, dimension);
        assertEquals(4,result.npoints);
        for(int i=0;i<4;i++)
        {
            assertEquals(xPoly2[i],result.xpoints[i]);
            assertEquals(yPoly2[i],result.ypoints[i]);
        }
    }
    
    @Test
    public void testNbBordures()
    {
        System.out.println("nbBordures");
        assertEquals(1, Outils.nbBordures(Forme.Cercle));
        assertEquals(3, Outils.nbBordures(Forme.Triangle));
        assertEquals(4, Outils.nbBordures(Forme.Rectangle));
        assertEquals(5, Outils.nbBordures(Forme.Pentagone));
        assertEquals(6, Outils.nbBordures(Forme.Hexagone));
    }
}
