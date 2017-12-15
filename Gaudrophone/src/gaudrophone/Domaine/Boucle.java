package gaudrophone.Domaine;
import gaudrophone.Domaine.Enums.EtatBoucle;
import java.util.List;
import gaudrophone.Domaine.Instrument.Touche;
import gaudrophone.Presentation.PanneauAffichage;
import java.util.ArrayList;

public class Boucle {
    List<Touche> listeTouches;
    List<Long> listeTemps;
    List<Boolean> listeEnJeu;
    EtatBoucle etat;
    Thread thread;
    long tempsDebutEnregistrement;
    long tempsFinEnregistrement;
    PanneauAffichage panneauAffichage;
    
    public Boucle()
    {
        listeTouches = new ArrayList<>();
        listeTemps = new ArrayList<>();
        listeEnJeu = new ArrayList<>();
        etat = EtatBoucle.Inactif;
    }
    
    private void jouer()
    {
        thread = new ThreadJouer();
        thread.start();
    }
    
    private class ThreadJouer extends Thread {
        @Override
        public void run()
        {
            Touche toucheAArreter = null;
            while (etat == EtatBoucle.Jouer)
            {
                int index = 0;
                long tempsDepart = System.nanoTime();
                long tempsActuel = System.nanoTime() - tempsDepart;
                
                while (etat == EtatBoucle.Jouer && tempsActuel <= tempsFinEnregistrement)
                {
                    tempsActuel = System.nanoTime() - tempsDepart;
                    if (index < listeTemps.size())
                    {
                        if (tempsActuel >= listeTemps.get(index))
                        {
                            Touche touche = listeTouches.get(index);
                            
                            if (listeEnJeu.get(index))
                            {
                                touche.setSurbrillance(true);
                                touche.commencerJouer();
                                toucheAArreter = touche;
                            }
                            else
                            {
                                touche.setSurbrillance(false);
                                touche.arreterJouer();
                                toucheAArreter = touche;
                            }
                            
                            panneauAffichage.repaint();
                            index++;
                        }
                    }
                }
            }
            listeTouches = new ArrayList<>();
            listeTemps = new ArrayList<>();
            listeEnJeu = new ArrayList<>();
            if (toucheAArreter != null)
            {
                toucheAArreter.arreterJouer();
                toucheAArreter.setSurbrillance(false);
                panneauAffichage.repaint();
            }
        }
    }
    
    public void ajouterTouche(Touche touche, boolean enJeu)
    {
        if (etat == EtatBoucle.Enregistrer)
        {
            long temps = System.nanoTime() - tempsDebutEnregistrement;
            listeTouches.add(touche);
            listeTemps.add(temps);
            listeEnJeu.add(enJeu);
        }
    }
    
    public EtatBoucle getEtat()
    {
        return etat;
    }
    
    public EtatBoucle changerEtat()
    {
        switch (etat)
        {
            case Inactif:
                etat = EtatBoucle.Enregistrer;
                tempsDebutEnregistrement = System.nanoTime();
                break;
            
            case Enregistrer:
                tempsFinEnregistrement = System.nanoTime() - tempsDebutEnregistrement;
                etat = EtatBoucle.Jouer;
                jouer();
                break;
                
            case Jouer:
                etat = EtatBoucle.Inactif;
        }
        
        return etat;
    }
    
    public void setPanneauAffichage(PanneauAffichage panneauAffichage)
    {
        this.panneauAffichage = panneauAffichage;
    }
}
