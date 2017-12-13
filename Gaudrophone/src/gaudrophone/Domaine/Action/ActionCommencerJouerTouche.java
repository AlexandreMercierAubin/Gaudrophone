package gaudrophone.Domaine.Action;

import gaudrophone.Domaine.Instrument.Touche;
import gaudrophone.Presentation.PanneauAffichage;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ActionCommencerJouerTouche extends AbstractAction {
    Touche touche;
    PanneauAffichage panneauAffichage;
    
    public ActionCommencerJouerTouche(Touche touche, PanneauAffichage panneau)
    {
        this.touche = touche;
        panneauAffichage = panneau;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!touche.getCleAppuyee())
        {
            touche.commencerJouer();
            touche.setSurbrillance(true);
            touche.setCleAppuyee(true);
            panneauAffichage.repaint();
        }
    }
}
