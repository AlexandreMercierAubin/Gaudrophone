package gaudrophone.Presentation;

import gaudrophone.Domaine.Dessin.DessinateurInstrument;
import gaudrophone.Domaine.Dimension2D;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanneauAffichage extends JPanel {
    FenetreInstrument fenetreInstrument;
    
    public PanneauAffichage()
    {
        
    }

    public PanneauAffichage(FenetreInstrument fenetreInstrument)
    {
        this.fenetreInstrument = fenetreInstrument;
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        Dimension size = getSize();
        Dimension2D taille = new Dimension2D(size.getWidth(), size.getHeight());
        DessinateurInstrument dessinateur = new DessinateurInstrument(fenetreInstrument.getControleur(), taille);
        dessinateur.dessiner(g);
    }    
    
    public FenetreInstrument getFenetreInstrument()
    {
        return fenetreInstrument;
    }

    public void setFenetreInstrument(FenetreInstrument fenetreInstrument)
    {
        this.fenetreInstrument = fenetreInstrument;
    }
}
