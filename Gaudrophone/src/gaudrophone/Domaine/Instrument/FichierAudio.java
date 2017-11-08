
package gaudrophone.Domaine.Instrument;

public class FichierAudio extends Son{
    String chemin;
    
    public FichierAudio(){}
    
    @Override
    public void commencerJouer(){}
    
    @Override
    public void arreterJouer(){}
    
    public String getChemin()
    {
        return chemin;
    }
}
