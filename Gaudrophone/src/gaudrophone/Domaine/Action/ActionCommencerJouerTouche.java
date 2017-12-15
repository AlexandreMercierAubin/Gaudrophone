package gaudrophone.Domaine.Action;

import gaudrophone.Domaine.ControleurInstrument;
import gaudrophone.Domaine.Instrument.Touche;
import gaudrophone.Presentation.PanneauAffichage;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ActionCommencerJouerTouche extends AbstractAction {
    Touche touche;
    PanneauAffichage panneauAffichage;
    ControleurInstrument controleur;
    
    public ActionCommencerJouerTouche(Touche touche, PanneauAffichage panneau, ControleurInstrument controleur)
    {
        this.touche = touche;
        panneauAffichage = panneau;
        this.controleur = controleur;
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
            controleur.ajouterToucheBoucles(touche, true);
        }
    }
}
