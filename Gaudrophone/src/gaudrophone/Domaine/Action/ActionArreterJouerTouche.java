package gaudrophone.Domaine.Action;

import gaudrophone.Domaine.Instrument.Touche;
import gaudrophone.Presentation.PanneauAffichage;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ActionArreterJouerTouche extends AbstractAction {
    Touche touche;
    PanneauAffichage panneauAffichage;
        
    public ActionArreterJouerTouche(Touche touche, PanneauAffichage panneau){
        this.touche = touche;
        this.panneauAffichage = panneau;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (touche.getCleAppuyee())
        {
            touche.arreterJouer();
            touche.setSurbrillance(false);
            touche.setCleAppuyee(false);
            panneauAffichage.repaint();
        }
    }
}
