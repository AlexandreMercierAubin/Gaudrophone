
package gaudrophone.Domaine.Instrument;

import java.io.File;
import java.io.Serializable;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FichierAudio extends Son implements Serializable{
    String chemin ;
    transient Clip clip;
    
    public FichierAudio(String cheminFichierAudio)
    {
        chemin = cheminFichierAudio;
        jouerSon = false;
    }
    
    @Override
    public void commencerJouer()
    {
        if(!jouerSon){ arreterJouer(); }
        jouerSon = true;
        
        try {
            File f = new File(chemin);
            AudioInputStream aIS = AudioSystem.getAudioInputStream(f); 
            clip = AudioSystem.getClip();
            clip.open(aIS);
            clip.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void arreterJouer()
    {
        if (!jouerSon && clip != null){
            try{
                clip.stop();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            jouerSon = false;
        }
    }
    
    public String getChemin()
    {
        return chemin;
    }
}
