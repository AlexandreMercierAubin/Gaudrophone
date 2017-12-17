package gaudrophone.Domaine.Action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;

public class ActionBoucle extends AbstractAction {
    JButton bouton;
        
    public ActionBoucle(JButton bouton){
        this.bouton = bouton;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        bouton.doClick();
    }
}
