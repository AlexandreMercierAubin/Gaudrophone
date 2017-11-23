
package gaudrophone.Domaine.Instrument;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FichierAudio extends Son{
    String chemin ;
    Clip clip;
    
    public FichierAudio(String cheminFichierAudio)
    {
        chemin = cheminFichierAudio;
    }
    
    @Override
    public void commencerJouer()
    {
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
        try{
            clip.stop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public String getChemin()
    {
        return chemin;
    }
}
