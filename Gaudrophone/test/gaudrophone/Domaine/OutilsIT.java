/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaudrophone.Domaine;

import gaudrophone.Domaine.Dimension2D;
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
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
//
//    /**
//     * Test of conversionPointPixelRelatif method, of class Outils.
//     */
//    @Test
//    public void testConversionPointPixelRelatif() {
//        System.out.println("conversionPointPixelRelatif");
//        Point2D coordPixel = null;
//        Dimension2D dimensionPanneau = null;
//        Outils instance = new Outils();
//        Point2D expResult = null;
//        Point2D result = instance.conversionPointPixelRelatif(coordPixel, dimensionPanneau);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of conversionPointRelatifPixel method, of class Outils.
//     */
//    @Test
//    public void testConversionPointRelatifPixel() {
//        System.out.println("conversionPointRelatifPixel");
//        Point2D coordRelatif = null;
//        Dimension2D dimensionPanneau = null;
//        Outils instance = new Outils();
//        Point2D expResult = null;
//        Point2D result = instance.conversionPointRelatifPixel(coordRelatif, dimensionPanneau);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of conversionDimensionPixelRelatif method, of class Outils.
//     */
//    @Test
//    public void testConversionDimensionPixelRelatif() {
//        System.out.println("conversionDimensionPixelRelatif");
//        Dimension2D dimensionPixel = null;
//        Dimension2D dimensionPanneau = null;
//        Outils instance = new Outils();
//        Dimension2D expResult = null;
//        Dimension2D result = instance.conversionDimensionPixelRelatif(dimensionPixel, dimensionPanneau);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of conversionDimensionRelatifPixel method, of class Outils.
//     */
//    @Test
//    public void testConversionDimensionRelatifPixel() {
//        System.out.println("conversionDimensionRelatifPixel");
//        Dimension2D dimensionRelative = null;
//        Dimension2D dimensionPanneau = null;
//        Outils instance = new Outils();
//        Dimension2D expResult = null;
//        Dimension2D result = instance.conversionDimensionRelatifPixel(dimensionRelative, dimensionPanneau);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
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
        
        int[] xPoly= {50,0,-50,0};
        int[] yPoly= {0,50,0,-50};
       
        Polygon result = instance.calculerPolygone(nbSommets, centrePoly, dimension);
        assertEquals(4,result.npoints);
        for(int i=0;i<4;i++)
        {
            assertEquals(xPoly[i],result.xpoints[i]);
            assertEquals(yPoly[i],result.ypoints[i]);
        }
        
        
        //test avec un centre autre
        centrePoly = new Point2D.Double(50,50);
        int[] xPoly2= {100,50,0,50};
        int[] yPoly2= {50,100,50,0};
        result = instance.calculerPolygone(nbSommets, centrePoly, dimension);
        assertEquals(4,result.npoints);
        for(int i=0;i<4;i++)
        {
            assertEquals(xPoly2[i],result.xpoints[i]);
            assertEquals(yPoly2[i],result.ypoints[i]);
        }
    }
    
}
