package gaudrophone.Domaine.Dessin;

import gaudrophone.Domaine.ControleurInstrument;
import java.awt.Graphics;
import java.awt.geom.Dimension2D;


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
    }
}
