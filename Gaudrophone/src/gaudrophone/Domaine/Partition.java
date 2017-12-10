package gaudrophone.Domaine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Partition {
    String chemin;
    String textePartition;
    List<String> aTextePartition; //Changer en ArrayList optimalement
    int pulsation;
    String note;
    
    public Partition()
    {
        aTextePartition = new ArrayList<String>();
//        Arrays.fill(aTextePartition, "");
        textePartition = "";
        note = "";        
    }
    
    public String lirePartition(){
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            boolean bPulsation = false;
            String sReadLine;
            int pos = -1;
            int longMax = 0;
            
            while ((sReadLine = reader.readLine()) != null) {
                if (bPulsation){  
                    pos ++;
                    if (sReadLine.equals("")){
                        if (longMax != 0){
                            int i = 0;
                            while (i < aTextePartition.size() && !aTextePartition.get(i).equals("")){
                                aTextePartition.set(i, String.format("%1$-" + (longMax + 1) + "s", aTextePartition.get(i))) ;
                                i++;
                            }
                        }
                        longMax = 0;                        
                        pos = -1;
                    }
                    else{
                        if( pos < aTextePartition.size()){
                            aTextePartition.set(pos, aTextePartition.get(pos) + " " + sReadLine); 
                        }
                        else{
                            aTextePartition.add(sReadLine);
                        }
                        
                        if (longMax < sReadLine.length())
                                longMax = sReadLine.length();
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
            int i = 0;
            while (i < aTextePartition.size() && !aTextePartition.get(i).equals("")){
                System.out.println(aTextePartition.get(i));
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
