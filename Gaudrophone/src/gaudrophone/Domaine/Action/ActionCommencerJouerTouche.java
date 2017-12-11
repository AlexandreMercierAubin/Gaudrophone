package gaudrophone.Domaine.Action;

import gaudrophone.Domaine.Instrument.Touche;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ActionCommencerJouerTouche extends AbstractAction {
    Touche touche;
        
    public ActionCommencerJouerTouche(Touche touche){
        this.touche = touche;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        touche.commencerJouer();
    }
}
