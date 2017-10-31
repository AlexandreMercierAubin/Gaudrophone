package gaudrophone.Presentation;

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
