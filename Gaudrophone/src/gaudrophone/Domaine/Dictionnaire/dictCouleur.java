package gaudrophone.Domaine.Dictionnaire;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;

public class dictCouleur implements Serializable {
    HashMap<String, Color> mapCouleur = new HashMap<String, Color>(){};
    
    public dictCouleur()
    {
        mapCouleur.put("JAUNE",Color.yellow);
        mapCouleur.put("ROUGE",Color.red);
        mapCouleur.put("VERT",Color.green);
        mapCouleur.put("BLEU",Color.blue);
        mapCouleur.put("NOIR",Color.black);
        mapCouleur.put("BEIGE",new Color(245,245,220));
        mapCouleur.put("BRUN",new Color(72,52,32));
        mapCouleur.put("BLANC",Color.white);
        
    }
    
    public Color trouverParClee(String clee)
    {      
        return mapCouleur.getOrDefault(clee, null);
    }
}
