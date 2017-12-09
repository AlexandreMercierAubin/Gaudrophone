package gaudrophone.Domaine;

import java.io.BufferedReader;
import java.io.FileReader;

public class Partition {
    String chemin;
    String textePartition;
    String [] aTextePartition;
    int pulsation;
    String note;
    
    public Partition()
    {
        textePartition = "";
        note = "";        
    }
    
    public String lirePartition(){
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            boolean bPulsation = false;
            String sReadLine;
            
            while ((sReadLine = reader.readLine()) != null) {
                if (bPulsation){
                        textePartition = textePartition + sReadLine;
                        if (sReadLine.equals("")){
                            textePartition = textePartition + "\r\n";
                        }
                }
                else{
                    if(!sReadLine.equals("")){
                        if(sReadLine.charAt(0) != '/' && sReadLine.charAt(1) != '/'){
                            if(!bPulsation){
                                bPulsation = true;
                                pulsation = Integer.parseInt(sReadLine);
                            }
                            else{
                                note = note + sReadLine.toUpperCase();
                            }
                        }
                    }
                }
            }
            aTextePartition = textePartition.split(".6.6");
            int i = 0;
            while (i < aTextePartition.length){
                System.out.println(aTextePartition[i]);
                i++;
            }
            
//            System.out.println(textePartition);
//            System.out.println("Pulsation" + pulsation);
//            System.out.println("note");
//            System.out.println(note);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return textePartition;
    }
    
    public void setChemin(String chemin) {
        this.chemin = chemin;
    }
}
