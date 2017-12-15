package gaudrophone.Domaine;

import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Son;
import gaudrophone.Domaine.Instrument.Touche;
import gaudrophone.Presentation.PanneauAffichage;
import javax.swing.JSlider;
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Character.isDigit;
import java.util.ArrayList;
import java.util.List;

public class Partition {
    String chemin;
    String textePartition;
    int pulsation;
    int toucheSurbrillance;
    int compteurNote;
    int tempsTotal;
    List<Integer> tempsNoteJouer;
    List<List<Note>> noteJouer;
    List<List<Touche>> toucheSurbriller;
    PanneauAffichage panneauAffichage;
    JSlider slider;
    boolean partitionJouer;
    long tempsPartition;
    int indexPartition;
    
    public Partition(PanneauAffichage panneauAffichage, JSlider slider)
    {
        tempsNoteJouer = new ArrayList<>();
        noteJouer = new ArrayList<>();
        toucheSurbriller = new ArrayList<>();
        textePartition = "";
        this.panneauAffichage = panneauAffichage;
        this.slider = slider;
        tempsPartition = 0;
        indexPartition = 0;
    }
    
    public void lirePartition(int timbre, List<Touche> touches){        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
            boolean bPulsation = false;
            String sReadLine;
            int pos = -1;
            int longMax = 0;
            int compteurLigneNonNote = 0;
            List<String> aTextePartition = new ArrayList<>();
            
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
                                compteurLigneNonNote ++;
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
                                compteurLigneNonNote ++;
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
            noteTable = new String[aTextePartition.size()][];
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
                      
            
            String sNote;
            i = 0;
            int j = 0;
            int k = 0;
            NomNote nom = NomNote.A;
            int octave = 4;
            while (i < noteTable.length)
            {
                if (noteTable[i] != null){
                    noteJouer.add(new ArrayList<>());
                    j = 0;
                    k = 0;
                    while (j < noteTable[i].length){
                        if(!noteTable[i][j].equals(""))
                        {
                            noteJouer.get(i).add(new Note(timbre));
    //                        noteJouer.get(i).get(k).jouerMuet();
                            sNote = noteTable[i][j].toUpperCase();
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
                            k++;
                        }
                        j++;
                    }
                }
                i++;
            }
            
            tempsTotal = 0;
            i = 0;
            j = 0;
            if(tempsNoteTable != null){
                while(i < tempsNoteTable.length){
                    if(!tempsNoteTable[i].equals("")){                        
                        tempsNoteJouer.add(tempsTotal);
                        k = 0;
                        while (k < noteJouer.size()){
                            noteJouer.get(k).get(j).setPersistance(retourPersistance(tempsNoteTable[i]) * 60 / pulsation);
                            k++;
                        }
                        tempsTotal = tempsTotal + retourPersistance(tempsNoteTable[i]) * 60 / pulsation;
                        j++;
                    }
                    i++;
                }
            }
            else{
                i = 0;
                while(i < noteJouer.get(0).size()){
                    k = 0;
                    while (k < noteJouer.size()){
                        noteJouer.get(k).get(j).setPersistance(1000 * 60 / pulsation);
                        k++;
                    }
                    tempsNoteJouer.add(1000 * i * 60 / pulsation );  
                    i++;
                }
                tempsTotal = 1000 * i * 60 / pulsation;
            }  
            i = 0;
            while(i < noteJouer.size()){
                toucheSurbriller.add(new ArrayList<>());
                j = 0;
                while(j < noteJouer.get(i).size()){
                    boolean bTrouve = false;
                    k = 0;
                    while(k < touches.size() && !bTrouve){
                        Son son = touches.get(k).getSon();
                        if (((Note)son).getNom() == noteJouer.get(i).get(j).getNom() && ((Note)son).getOctave() == noteJouer.get(i).get(j).getOctave())
                            bTrouve = true;
                        else
                            k++;
                    }
                    if (bTrouve)
                        toucheSurbriller.get(i).add(touches.get(k));
                    else
                        toucheSurbriller.get(i).add(null);
                    j++;
                }
                i++;
            }
            
            this.slider.setMaximum(tempsTotal);
            this.slider.setMinimum(0);
            this.slider.setValue((int)tempsPartition);
            this.slider.setMajorTickSpacing((int)(tempsTotal / 5));
            this.slider.setPaintTicks(true);
            this.slider.setPaintLabels(true);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    public void pausePartition(){
        partitionJouer = false;
    }
    
    public void jouerPartition(){
        partitionJouer = true;
        tempsPartition = this.slider.getValue();
        Thread thread = new ThreadJouer(tempsPartition);
        thread.start();
    }
    
    private class ThreadJouer extends Thread {
        long tempsDepart;
        
        public ThreadJouer(long tempsDepart)
        {
            this.tempsDepart = tempsDepart;
        }
        
        @Override
        public void run()
        {
            long tempsDepartNano = System.nanoTime();
            tempsPartition = tempsDepart;
            while (indexPartition < tempsNoteJouer.size() && partitionJouer)
            {
                tempsPartition = (System.nanoTime() - tempsDepartNano) / 1000000 + tempsDepart;
                slider.setValue((int)tempsPartition);   
                if (tempsPartition >= tempsNoteJouer.get(indexPartition) && partitionJouer)
                {
                    boolean repaint = false;
                    System.out.println(tempsPartition);
                    
                    for (List<Note> liste: noteJouer)
                    {
                        liste.get(indexPartition).commencerJouer();
                        liste.get(indexPartition).arreterJouer();
                    }
                    
                    for (List<Touche> liste: toucheSurbriller)
                    {
                        if (indexPartition > 0)
                        {
                            Touche touchePrecedente = liste.get(indexPartition - 1);
                            if (touchePrecedente != null)
                            {
                                touchePrecedente.setSurbrillance(false);
                                repaint = true;
                            }
                        }
                        
                        Touche touche = liste.get(indexPartition);
                        if (touche != null)
                        {
                            touche.setSurbrillance(true);
                            repaint = true;
                        }
                    }
                    
                    if (repaint)
                        panneauAffichage.repaint();
                    
                    indexPartition++;
                }
            }
            
            if(partitionJouer){
                // Attente que la derniere touche finisse de jouer
                while (tempsPartition < tempsTotal)
                    tempsPartition = (System.nanoTime() - tempsDepartNano) / 1000000 + tempsDepart;

                boolean repaint = false;
                for (List<Touche> liste: toucheSurbriller)
                {
                    Touche touche = liste.get(liste.size() - 1);
                    if (touche != null)
                    {
                        touche.setSurbrillance(false);
                        repaint = true;
                    }
                }
                if (repaint)
                    panneauAffichage.repaint();
                partitionJouer = false;
                tempsPartition = 0;
                indexPartition = 0;
            }
        }
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
        switch (persistance) {
            case "_":
                return 1000;
            case ",":
                return 500;
            case ".":
                return 250;
            default:
                return Integer.parseInt(persistance) * 1000;
        }
    }
    
    public boolean ligneIsTemps(String ligne){
        String a = ligne.replaceAll("\\s+","");
        if (a.charAt(0) != '_' && a.charAt(0) != ',' && a.charAt(0) != '.' && !isDigit(a.charAt(0)))
            return false;
        else
            return true;
    }
    
    public void updateTimbre(int timbre){
        int i = 0;
        int j = 0;
        while (i < noteJouer.size()){
            while (j < noteJouer.get(i).size()){
                noteJouer.get(i).get(j).setTimbreInstrument(timbre);
                j++;
            }
            i++;
        }
    }
    
    public void setChemin(String chemin) {
        this.chemin = chemin;
    }
    
    public String getTextePartition() {
        return textePartition;
    }
    
}
