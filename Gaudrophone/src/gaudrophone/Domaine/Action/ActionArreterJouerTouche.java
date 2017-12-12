package gaudrophone.Domaine.Action;

import gaudrophone.Domaine.Instrument.Touche;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ActionArreterJouerTouche extends AbstractAction {
    Touche touche;
        
    public ActionArreterJouerTouche(Touche touche){
        this.touche = touche;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        touche.arreterJouer();
    }
}
