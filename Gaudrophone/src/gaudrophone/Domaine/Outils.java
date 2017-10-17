
package gaudrophone.Domaine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Outils {
    public String readFile(String strNomFichier, String strTexteInitial)
    {
        String strTexte=strTexteInitial;
        String strChemin = new File("").getAbsolutePath();
        try (BufferedReader brLecteur = new BufferedReader(new InputStreamReader(new FileInputStream(strChemin.concat(strNomFichier)),"ISO-8859-1"))) 
        {

            String strLigne;

            while ((strLigne = brLecteur.readLine()) != null) 
            {
                   strTexte+=strLigne + "\r\n";
            }

        } catch (IOException e) 
        {
                e.printStackTrace();
        }
        return strTexte;
    }

}
