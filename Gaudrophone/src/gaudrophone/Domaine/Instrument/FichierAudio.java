
package gaudrophone.Domaine.Instrument;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FichierAudio extends Son{
    String chemin ;
    
    public FichierAudio(){}
    
    @Override
    public void commencerJouer()
    {
        try {
            File f = new File(chemin);
            AudioInputStream aIS = AudioSystem.getAudioInputStream(f);
            Clip clip = AudioSystem.getClip();
            clip.open(aIS);
            clip.start();
            Thread.sleep(100);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void arreterJouer(){}
    
    public String getChemin()
    {
        return chemin;
    }
}
