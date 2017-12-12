package gaudrophone.Domaine;

import gaudrophone.Domaine.Generateur.GenerateurInstrument;
import gaudrophone.Domaine.Instrument.Instrument;
import gaudrophone.Domaine.Instrument.Touche;
import gaudrophone.Domaine.Enums.ModeVisuel;
import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Son;
import gaudrophone.Presentation.FenetreInstrument;
import java.util.List;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControleurInstrument {
    Instrument instrument;
    Metronome metronome;
    List<Boucle> boucles;
    Partition partition;
    String cheminSauvegarde;
    ModeVisuel modeVisuel;
    boolean toucheEnDeplacement;
    int toucheEnJeu;
    double echelleAffichage;
    
    public ControleurInstrument()
    {
        instrument = new Instrument();
        metronome = new Metronome();
        toucheEnDeplacement = false;
        toucheEnJeu=-1;
        modeVisuel=ModeVisuel.Ajouter;
        echelleAffichage = 1.0;
        boucles = new ArrayList<>();
        
        for (int i = 0; i < 9; i++)
        {
            boucles.add(new Boucle());
        }
    }
    
    public Instrument getInstrument()
    {
        return instrument;
    }
    
    public void nouvelInstrument()
    {
        instrument = new Instrument();
    }
    
    public Metronome getMetronome()
    {
        return metronome;
    }
    
    public void setEchelleAffichage(double echelle)
    {
        echelleAffichage = echelle;
    }
    
    public void importerPartition(){}
    
    public void jouerPartition(){}
    
    public Boucle getBoucle(int index)
    {
        if(boucles.size()>index)
        {
            return boucles.get(index);
        }
        return null;
    }
    
    public void enregistrerBoucle(int index){}
    
    public void finEnregistrerBoucle(int index){}
    
    public void cliquerSouris(Point2D coordRelative)
    {
        coordRelative = new Point2D.Double(
                coordRelative.getX() / echelleAffichage, 
                coordRelative.getY() / echelleAffichage);
        
        switch(modeVisuel)
        {    
            case Editer:
                instrument.selectionnerTouche(coordRelative);
                break;
        }
    }
    
    public boolean enfoncerSouris(Point2D coordRelative)
    {
        coordRelative = new Point2D.Double(
                coordRelative.getX() / echelleAffichage, 
                coordRelative.getY() / echelleAffichage);
        
        boolean clickTouche = false;
        switch(modeVisuel)
        {
            case Editer:
                //detecter si une touche est selectionee
                clickTouche = instrument.selectionnerTouche(coordRelative);
                if(clickTouche)
                {
                    instrument.replacerToucheDessus();
                    toucheEnDeplacement=true;
                }
                break;
                
            case Jouer:
                //envoyer un message pour commencer le son de la touche
                if(instrument.selectionnerTouche(coordRelative))
                {
                    int indexTouche = instrument.getToucheSelectionee();
                    instrument.getTouche(indexTouche).commencerJouer();
                    toucheEnJeu=indexTouche;
                }
                break;
                
            case Ajouter:
                instrument.ajouterTouche(coordRelative);
                toucheEnDeplacement=true;
                clickTouche=true;
                break;
        }
        return clickTouche;
    }

   
   
   
    public void relacherSouris(Point2D coordRelative)
    {
        coordRelative = new Point2D.Double(
                coordRelative.getX() / echelleAffichage, 
                coordRelative.getY() / echelleAffichage);
        
        switch(modeVisuel)
        {
            case Editer:
                toucheEnDeplacement = false;
                break;
                
            case Jouer:
                // envoyer un message d'arret a la touche relachee
                if(toucheEnJeu >= 0)
                {
                    int indexTouche = instrument.getToucheSelectionee();
                    instrument.getTouche(indexTouche).arreterJouer();
                    toucheEnJeu=-1;
                }
                break;
                
            case Ajouter:
                toucheEnDeplacement=false;
                break;
        }
    }
   
    public boolean glisserSouris(Point2D coordRelative)
    {
        coordRelative = new Point2D.Double(
                coordRelative.getX() / echelleAffichage, 
                coordRelative.getY() / echelleAffichage);
        
        switch (modeVisuel)
        {
            case Editer:
            case Ajouter:
                // deplacement de la touche selectionnee a l'enfoncement
                if (toucheEnDeplacement)
                {
                    int indexTouche = instrument.getToucheSelectionee();
                    Touche touche = instrument.getTouche(indexTouche);
                    touche.getApparence().setPosition(coordRelative);
                }
                break;
            
            case Jouer:
                if (instrument.selectionnerTouche(coordRelative))
                {
                    int indexTouche = instrument.getToucheSelectionee();
                    if (indexTouche != toucheEnJeu)
                    {
                        if (toucheEnJeu >= 0)
                            instrument.getTouche(toucheEnJeu).arreterJouer();
                        instrument.getTouche(indexTouche).commencerJouer();
                        toucheEnJeu = indexTouche;
                    }
                }
                else
                    toucheEnJeu = -1;
        }
        
        return toucheEnDeplacement;
    }
    
    public void sauvegarderInstrument()
    {
        if(instrument.getChemin()!="")
        {
            try 
            {
                FileOutputStream fichier = new FileOutputStream(instrument.getChemin());
                ObjectOutputStream oosEnregistrer = new ObjectOutputStream(fichier);
                
                oosEnregistrer.writeObject(instrument);
                oosEnregistrer.flush();
                
            } catch (final java.io.IOException e) 
            {

                e.printStackTrace();

            }
        }else
        {
            sauvegarderSousInstrument();
        }
    }
    
    public void sauvegarderSousInstrument()
    { 
        JFileChooser dialogueEnregistrer = new JFileChooser();
        // dialogue de sauvegarde
        dialogueEnregistrer.setSelectedFile(new File(instrument.getNom()));
        int rVal = dialogueEnregistrer.showSaveDialog(null);
        
        if (rVal == JFileChooser.APPROVE_OPTION) 
        {
            String filename = dialogueEnregistrer.getSelectedFile().getName();
            String dir = dialogueEnregistrer.getCurrentDirectory().toString();
            try 
            {
                String chemin = dir+"\\"+filename+".kys";
                FileOutputStream fichier = new FileOutputStream(chemin,false);
                instrument.setChemin(chemin);
                ObjectOutputStream oosEnregistrer = new ObjectOutputStream(fichier);
                
                oosEnregistrer.writeObject(instrument);
                oosEnregistrer.flush();
                
            } catch (final java.io.IOException e) 
            {

                e.printStackTrace();

            }
        }
        if (rVal == JFileChooser.CANCEL_OPTION) {
            
        }
    }
    
    public void importerInstrument()
    {
        JFileChooser dialogueImporter = new JFileChooser();
        // dialogue de sauvegarde
        FileNameExtensionFilter filter = new FileNameExtensionFilter("instrument gaudrophone", "kys");
        dialogueImporter.setFileFilter(filter);
        
        int rVal = dialogueImporter.showOpenDialog(null);
        
        if (rVal == JFileChooser.APPROVE_OPTION) 
        {
            File fichier = dialogueImporter.getSelectedFile();
            try 
            {
                FileInputStream streamFichier = new FileInputStream(fichier.getAbsolutePath());
                ObjectInputStream oisImporter = new ObjectInputStream(streamFichier);
                
                instrument=(Instrument)(oisImporter.readObject());
                
            } catch (final java.io.IOException e) 
            {

                e.printStackTrace();

            }catch(ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        if (rVal == JFileChooser.CANCEL_OPTION) {
            
        }
    }
    
    public void modifierModeVisuel(ModeVisuel modeVisuel)
    {
        this.modeVisuel = modeVisuel;
        instrument.deselectionnerTouche();
    }
    
    public void genererInstrument(GenerateurInstrument generateurInstrument)
    {
        instrument = generateurInstrument.generer();
    }
}
