
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
            AudioInputStream din = null;
            AudioFormat baseFormat = aIS.getFormat();
            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 
                                            baseFormat.getSampleRate(),
                                            16,
                                            baseFormat.getChannels(),
                                            baseFormat.getChannels() * 2,
                                            baseFormat.getSampleRate(),
                                            false);
            din = AudioSystem.getAudioInputStream(decodedFormat, aIS);
            clip = AudioSystem.getClip();
            clip.open(din);
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
