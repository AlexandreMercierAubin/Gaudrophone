package gaudrophone.Domaine.Action;

import gaudrophone.Domaine.ControleurInstrument;
import gaudrophone.Domaine.Instrument.Touche;
import gaudrophone.Presentation.PanneauAffichage;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ActionArreterJouerTouche extends AbstractAction {
    Touche touche;
    PanneauAffichage panneauAffichage;
    ControleurInstrument controleur;
        
    public ActionArreterJouerTouche(Touche touche, PanneauAffichage panneau, ControleurInstrument controleur){
        this.touche = touche;
        this.panneauAffichage = panneau;
        this.controleur = controleur;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (touche.getCleAppuyee())
        {
            touche.arreterJouer();
            touche.setSurbrillance(false);
            touche.setCleAppuyee(false);
            panneauAffichage.repaint();
            controleur.ajouterToucheBoucles(touche, false);
        }
    }
}
