package gaudrophone.Domaine.Action;

import gaudrophone.Domaine.Boucle;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;

public class ActionBoucle extends AbstractAction {
    Boucle boucle;
    JButton bouton;
        
    public ActionBoucle(Boucle boucle, JButton bouton){
        this.boucle = boucle;
        this.bouton = bouton;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // switch (état boucle)
        // Modifier état boucle + couleur bouton
        
        bouton.setBackground(Color.red); // TEST PLZ REMOVE
    }
}
