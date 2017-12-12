package gaudrophone.Domaine;

import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Touche;
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Character.isDigit;
import java.util.ArrayList;
import java.util.List;

public class Partition {
    String chemin;
    String textePartition;
    int pulsation;
    List<List<String>> note;
    List<String> tempsNote;
    
    public Partition()
    {
        textePartition = "";  
        note = new ArrayList<>();
        tempsNote = new ArrayList<>();
    }
    
    public void lirePartition(){
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            boolean bPulsation = false;
            String sReadLine;
            int pos = -1;
            int longMax = 0;
            int compteurLigneComment = 0;
            List<String> aTextePartition = new ArrayList<String>();
            
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
                            if(sReadLine.charAt(0) == '/' && sReadLine.charAt(1) == '/'){
                                compteurLigneComment ++;
                                if(aTextePartition.get(pos).charAt(0) != '/' && aTextePartition.get(pos).charAt(1) != '/')
                                    aTextePartition.add(sReadLine);
                                else
                                    aTextePartition.set(pos, aTextePartition.get(pos) + " " + sReadLine);
                            }  
                            else{
                                aTextePartition.set(pos, aTextePartition.get(pos) + " " + sReadLine); 
                            }
                        }
                        else{
                            if(sReadLine.charAt(0) == '/' && sReadLine.charAt(1) == '/')
                                compteurLigneComment ++;
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
                        }
                    }
                }
            }
            String[][] noteTable;
            String[] tempsNoteTable = null;
            
            int i = 0;
            int y = 0;
            int accordMax = aTextePartition.size() - compteurLigneComment;
            noteTable = new String[accordMax][];
            String a = "";
            while (i < aTextePartition.size() && !aTextePartition.get(i).equals("")){
                if (aTextePartition.get(i).charAt(0) != '/' && aTextePartition.get(i).charAt(1) != '/'){
                    if (ligneIsTemps(aTextePartition.get(i))){
                        tempsNoteTable = aTextePartition.get(i).split(" +");
                    }
                    else{
                        a = aTextePartition.get(i).replace("|", " ");
                        noteTable[y] = a.split(" +");
                        y++;
                    }
                }
                textePartition = textePartition + "\r\n" + aTextePartition.get(i);
                i++;
            }
            i = 0;
            while (i < noteTable.length)
            {
                note.add(new ArrayList<>());
                y = 0;
                while (y < noteTable[i].length){
                    if(!noteTable[i][y].equals(""))
                    {
                        note.get(i).add(noteTable[i][y]);
                        System.out.println(noteTable[i][y]);
                    }
                    y++;
                }
                i++;
            }
            
            i = 0;
            if(tempsNoteTable != null){
                while(i < tempsNoteTable.length){
                    if(!tempsNoteTable[i].equals("")){
                        tempsNote.add(tempsNoteTable[i]);
                    }
                    i++;
                }
            }
            else{
                i = 0;
                while(i < note.get(0).size()){
                    tempsNote.add("_");
                    i++;
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    public void jouerPartition(List<Touche> touches, int timbre){
        Note[] noteJouer = new Note[note.size()];
        int i = 0;
        int y = 0;
        while(i < tempsNote.size()){
            while(y < note.size()){
                y++;
            }
            i++;
        }
    }
    
    public boolean ligneIsTemps(String ligne){
        String a = ligne.replaceAll("\\s+","");
        if (a.charAt(0) != '_' && a.charAt(0) != ',' && a.charAt(0) != '.' && !isDigit(a.charAt(0)))
            return false;
        else
            return true;
    }
    
    public void setChemin(String chemin) {
        this.chemin = chemin;
    }
    
    public String getTextePartition() {
        return textePartition;
    }
    
}
