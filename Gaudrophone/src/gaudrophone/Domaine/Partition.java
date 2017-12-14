package gaudrophone.Domaine;

import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Touche;
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Character.isDigit;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Partition {
    String chemin;
    String textePartition;
    int pulsation;
    int compteurNote;
    List<Integer> tempsNoteJouer;
    List<List<Note>> noteJouer;
    public static Timer timer;
    
    public Partition()
    {
        tempsNoteJouer = new ArrayList<>();
        noteJouer = new ArrayList<>();
        textePartition = "";  
    }
    
    public void lirePartition(int timbre){        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            boolean bPulsation = false;
            String sReadLine;
            int pos = -1;
            int longMax = 0;
            int compteurLigneComment = 0;
            List<String> aTextePartition = new ArrayList<>();
            List<List<String>> note = new ArrayList<>();
            List<String> tempsNote = new ArrayList<>();
            
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
            
            int compteurTemps = 0;
            i = 0;
            if(tempsNoteTable != null){
                while(i < tempsNoteTable.length){
                    if(!tempsNoteTable[i].equals("")){                        
                        tempsNoteJouer.add(compteurTemps);     
                        compteurTemps = compteurTemps + retourPersistance(tempsNoteTable[i]);
                    }
                    i++;
                }
            }
            else{
                i = 0;
                while(i < note.get(0).size()){
                    tempsNoteJouer.add(1000 * i);  
                    i++;
                }
            }            
            
            String sNote;
            i = 0;
            int j = 0;
            int k = 0;
            NomNote nom = NomNote.A;
            int octave = 4;
            while (i < noteTable.length)
            {
                noteJouer.add(new ArrayList<>());
                j = 0;
                k = 0;
                while (j < noteTable[i].length){
                    if(!noteTable[i][j].equals(""))
                    {
                        noteJouer.get(i).add(new Note(timbre));
                        sNote = noteTable[i][y].toUpperCase();
                        switch (sNote.length()) {
                            case 1:
                                if (!sNote.equals("X"))
                                {
                                    nom = retourNomNote(sNote);
                                    octave = 4;
                                }
                                else{
                                    octave = 22;
                                }
                                break;
                            case 2:
                                if (sNote.charAt(1) == '#')
                                {
                                    nom = retourNomNote(sNote);
                                    octave = 4;
                                }
                                else
                                {
                                    nom = retourNomNote(String.valueOf(sNote.charAt(0)));
                                    octave = Character.getNumericValue(sNote.charAt(1));
                                }   break;
                            default:
                                nom = retourNomNote(String.valueOf(sNote.charAt(0)) + String.valueOf(sNote.charAt(2)));
                                octave = Character.getNumericValue(sNote.charAt(1));
                                break;
                        }
                        noteJouer.get(i).get(k).setNom(nom);
                        noteJouer.get(i).get(k).setOctave(octave);
                        if( k == 0 )
                            noteJouer.get(i).get(k).setPersistance(tempsNoteJouer.get(k));
                        else 
                            noteJouer.get(i).get(k).setPersistance(tempsNoteJouer.get(k) - tempsNoteJouer.get(k - 1));
                        k++;
                    }
                    j++;
                }
                i++;
            }
            
            
            
            while (i < note.size()){
                noteJouer.add(new ArrayList<>());
                j = 0;
                while(j < note.get(i).size()){
                    noteJouer.get(i).add(new Note(timbre));
                    sNote = note.get(i).get(compteurNote);
                    switch (sNote.length()) {
                        case 1:
                            if (!sNote.equals("X"))
                            {
                                nom = retourNomNote(sNote);
                                octave = 4;
                            }   break;
                        case 2:
                            if (sNote.charAt(1) == '#')
                            {
                                nom = retourNomNote(sNote);
                                octave = 4;
                            }
                            else
                            {
                                nom = retourNomNote(String.valueOf(sNote.charAt(0)));
                                octave = Character.getNumericValue(sNote.charAt(1));
                            }   break;
                        default:
                            nom = retourNomNote(String.valueOf(sNote.charAt(0)) + String.valueOf(sNote.charAt(2)));
                            octave = Character.getNumericValue(sNote.charAt(1));
                            break;
                    }
                    noteJouer.get(i).get(j).setNom(nom);
                    noteJouer.get(i).get(j).setOctave(octave);
                    noteJouer.get(i).get(j).setPersistance((retourPersistance(tempsNote.get(compteurNote)) * pulsation / 60));
                    j++;
                }
                i++;
            }
            i = 0;
            while(i < tempsNote.size()){
                compteurTemps = compteurTemps + retourPersistance(tempsNote.get(compteurNote));
                tempsNoteJouer.add(compteurTemps);
                i++;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    public void jouerPartition(List<Touche> touches){
        compteurNote = 0;
        TimerTask timerTask = new TimerTask(){

            @Override
            public void run() {
                System.out.println(Instant.now());
                int i = 0;
                
                update();
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 0);
    }
    
    public void update() {
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                System.out.println(Instant.now());               
                update();
            }
        };
        timer.cancel();
        timer = new Timer();
        timer.schedule(timerTask, tempsNoteJouer.get(compteurNote));
        compteurNote++;
    }
    
    public NomNote retourNomNote(String sNote) {
        switch (sNote) {
            case "A":
                return NomNote.A;
            case "A#":
                return NomNote.ASharp;
            case "B":
                return NomNote.B;
            case "C":
                return NomNote.C;
            case "C#":
                return NomNote.CSharp;
            case "D":
                return NomNote.D;
            case "D#":
                return NomNote.DSharp;
            case "E":
                return NomNote.E;
            case "F":
                return NomNote.F;
            case "F#":
                return NomNote.FSharp;
            case "G":
                return NomNote.G;
            case "G#":
                return NomNote.GSharp;
            default:
                return NomNote.A;
        }        
    }
    
    public int retourPersistance(String persistance) {
        if (persistance.equals("_"))
            return 1000;
        else if (persistance.equals(","))
            return 500;
        else if (persistance.equals("."))
            return 250;
        else
            return Integer.parseInt(persistance) * 1000;
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
