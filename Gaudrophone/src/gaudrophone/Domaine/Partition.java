package gaudrophone.Domaine;

import java.io.BufferedReader;
import java.io.FileReader;

public class Partition {
    String chemin;
    String textePartition;
    int pulsation;
    
    public Partition()
    {
        
    }
    
    public void lirePartition(){
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            boolean bPulsation = false;
            String sReadLine;
            
            while ((sReadLine = reader.readLine()) != null) {
                if(!sReadLine.equals("")){
                    if(sReadLine.charAt(0) != '/' && sReadLine.charAt(1) != '/'){
                        if(!bPulsation){
                            bPulsation = true;
                            pulsation = Integer.parseInt(sReadLine);
                        }
                        else{
                            System.out.println(sReadLine);
                        }
                    }
                    else
                    {
                        System.out.println("not good" + sReadLine);
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    public void setChemin(String chemin) {
        this.chemin = chemin;
    }
}
