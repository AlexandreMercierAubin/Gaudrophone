package gaudrophone.Presentation;

import com.sun.glass.events.KeyEvent;
import gaudrophone.Domaine.Action.ActionArreterJouerTouche;
import gaudrophone.Domaine.Action.ActionBoucle;
import gaudrophone.Domaine.Action.ActionCommencerJouerTouche;
import gaudrophone.Domaine.Boucle;
import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.ControleurInstrument;
import gaudrophone.Domaine.Dictionnaire.dictCouleur;
import gaudrophone.Domaine.Dimension2D;
import gaudrophone.Domaine.Enums.Forme;
import gaudrophone.Domaine.Enums.ModeVisuel;
import gaudrophone.Domaine.Generateur.GenerateurGuitare;
import gaudrophone.Domaine.Generateur.GenerateurInstrument;
import gaudrophone.Domaine.Generateur.GenerateurPiano;
import gaudrophone.Domaine.Instrument.Bordure;
import gaudrophone.Domaine.Instrument.FichierAudio;
import gaudrophone.Domaine.Instrument.Instrument;
import gaudrophone.Domaine.Instrument.Note;
import gaudrophone.Domaine.Instrument.Son;
import gaudrophone.Domaine.Instrument.Touche;
import gaudrophone.Domaine.Outils;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FenetreInstrument extends javax.swing.JFrame {
    ButtonGroup m_btnGroupeMode;
    ControleurInstrument controleur;
    File m_fileToSaveImage;
    String m_pathToFileAudio;
    List<JButton> boutonsBoucle;
    
    public FenetreInstrument() {
        initComponents();
        initializeRadioButton();
        
        
        controleur = new ControleurInstrument();
        panneauAffichage.setFenetreInstrument(this);
        controleur.modifierModeVisuel(ModeVisuel.Ajouter);

        TPInfo.setEnabledAt(1,false);
        ((JSpinner.DefaultEditor) spinOctave.getEditor()).getTextField().setEditable(false);
        bgFond.add(rbCouleur);
        bgFond.add(rbImage);
        bgType.add(rbSon);
        bgType.add(rbFichierAudio);
        btnParcourirImage.setEnabled(false);
        btnParcourirFichierAudio.setEnabled(false);
        spGaudrophone.setResizeWeight(1);
        splitAffichage.setResizeWeight(1);
        
        txtMessage.setEditable(false);
        txtMessage.setWrapStyleWord(true);
        txtMessage.setLineWrap(true);
        
        spinFrequenceMetronome.setValue(controleur.getMetronome().getFrequence());
        spinPersistanceMetronome.setValue(controleur.getMetronome().getNote().getPersistance());
        spinOctaveMetronome.setValue(controleur.getMetronome().getNote().getOctave());
        
        final JTextField frequenceMetronome = ((JSpinner.DefaultEditor)spinFrequenceMetronome.getEditor()).getTextField();
        frequenceMetronome.getDocument().addDocumentListener(new DocumentListener(){              
                   
                @Override
                public void removeUpdate(DocumentEvent e) {
                    showChangedValue(e);    
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    showChangedValue(e);                
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    showChangedValue(e);    
                }
                
                private void showChangedValue(DocumentEvent e){
                    setMetronome();
                }
            });
        
            final JTextField octaveMetronome = ((JSpinner.DefaultEditor)spinOctaveMetronome.getEditor()).getTextField();
            octaveMetronome.getDocument().addDocumentListener(new DocumentListener(){              
                   
                @Override
                public void removeUpdate(DocumentEvent e) {
                    showChangedValue(e);    
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    showChangedValue(e);                
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    showChangedValue(e);    
                }
                
                private void showChangedValue(DocumentEvent e){
                    setMetronome();
                }
            });
            final JTextField persistanceMetronome = ((JSpinner.DefaultEditor)spinPersistanceMetronome.getEditor()).getTextField();
            persistanceMetronome.getDocument().addDocumentListener(new DocumentListener(){              
                   
                @Override
                public void removeUpdate(DocumentEvent e) {
                    showChangedValue(e);    
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    showChangedValue(e);                
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    showChangedValue(e);    
                }
                
                private void showChangedValue(DocumentEvent e){
                    setMetronome();
                }
            });
        
        TPInfo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(TPInfo.getSelectedIndex() == 0){
                    InstrumentUpdater(); 
                }
                else{
                    if(TPInfo.getSelectedIndex() == 1){
                        ToucheUpdater();
                    }
                }
            }
        });
        
        InstrumentUpdater();
        
        txtRechercher.getDocument().addDocumentListener(new DocumentListener() 
        {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            public void removeUpdate(DocumentEvent e) {
              warn();
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }

            public void warn() {
                controleur.getInstrument().rechercherTouche(txtRechercher.getText());
                panneauAffichage.repaint();
            }

        });
        
        boutonsBoucle = Arrays.asList(
                btnBoucle1,
                btnBoucle2,
                btnBoucle3,
                btnBoucle4,
                btnBoucle5,
                btnBoucle6,
                btnBoucle7,
                btnBoucle8,
                btnBoucle9
        );
        
        for (int i = 1; i <= 9; i++)
        {
            String cle = "" + i;
            panneauAffichage.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(cle), cle);
            panneauAffichage.getActionMap().put(cle, new ActionBoucle(controleur.getBoucle(i - 1), boutonsBoucle.get(i - 1)));
        }
    }
    
    private void InstrumentUpdater()
    {
        Instrument instru = controleur.getInstrument();
        int index = instru.getTimbre();
        
        txtNomInstrument.setText(instru.getNom());
        
        switch(index){
            case 1:{
                cbTimbre.setSelectedIndex(0);
                break;
            }
            case 25:{
                cbTimbre.setSelectedIndex(1);
                break;
            }
        }
    }
    
    private void ToucheUpdater(){
        int index = controleur.getInstrument().getToucheSelectionee();
        Touche touche = controleur.getInstrument().getTouche(index);
        //touche.getApparence().setForme(Forme.Hexagone);
        
        Forme forme = touche.getApparence().getForme();
        switch(forme){
            case Cercle:{
                cbForme.setSelectedIndex(0);
                break;
            }
            case Triangle:{
                cbForme.setSelectedIndex(1);
                break;
            }
            case Rectangle:{
                cbForme.setSelectedIndex(2);
                break;
            }
            case Pentagone:{
                cbForme.setSelectedIndex(3);
                break;
            }
            case Hexagone:{
                cbForme.setSelectedIndex(4);
                break;
            }
        }
        
        Image image = touche.getApparence().getImageFond();
        if(image == null){
            btnParcourirImage.setEnabled(false);
            bgFond.clearSelection();
            rbCouleur.setSelected(true);
            cbCouleur.setSelectedIndex(0);
            
            cbCouleur.setEnabled(true);
            spinRouge.setEnabled(true);
            lblRouge.setEnabled(true);
            spinVert.setEnabled(true);
            lblVert.setEnabled(true);
            spinBleu.setEnabled(true);
            lblBleu.setEnabled(true);
            
            Color couleur = touche.getApparence().getCouleurFond();
            spinRouge.setValue(couleur.getRed());
            spinBleu.setValue(couleur.getBlue());
            spinVert.setValue(couleur.getGreen());
            //ne pas oublier de remettre les composantes enabled disabled.
        }
        else{
            btnParcourirImage.setEnabled(true);
            bgFond.clearSelection();
            rbImage.setSelected(true);
            cbCouleur.setEnabled(false);
            spinRouge.setEnabled(false);
            lblRouge.setEnabled(false);
            spinVert.setEnabled(false);
            lblVert.setEnabled(false);
            spinBleu.setEnabled(false);
            lblBleu.setEnabled(false);
        }
        
        Dimension2D dim = touche.getApparence().getDimension();
        spinHauteur.setValue(dim.getHeight());
        spinLargeur.setValue(dim.getWidth());
        
        Son son = touche.getSon();
        if(son instanceof Note){
            btnParcourirFichierAudio.setEnabled(false);
            bgType.clearSelection();
            rbSon.setSelected(true);
            lblNote.setEnabled(true);
            cbNote.setEnabled(true);
            lblOctave.setEnabled(true);
            spinOctave.setEnabled(true);
            lblPersistance.setEnabled(true);
            spinPersistance.setEnabled(true);
            
            switch(((Note) son).getNom()){
                case C:{
                    cbNote.setSelectedIndex(0);
                    break;
                }
                case CSharp:{
                    cbNote.setSelectedIndex(1);
                    break;
                }
                case D:{
                    cbNote.setSelectedIndex(2);
                    break;
                }
                case DSharp:{
                    cbNote.setSelectedIndex(3);
                    break;
                }
                case E:{
                    cbNote.setSelectedIndex(4);
                    break;
                }
                case F:{
                    cbNote.setSelectedIndex(5);
                    break;
                }
                case FSharp:{
                    cbNote.setSelectedIndex(6);
                    break;
                }
                case G:{
                    cbNote.setSelectedIndex(7);
                    break;
                }
                case GSharp:{
                    cbNote.setSelectedIndex(8);
                    break;
                }
                case A:{
                    cbNote.setSelectedIndex(9);
                    break;
                }
                case ASharp:{
                    cbNote.setSelectedIndex(10);
                    break;
                }
                case B:{
                    cbNote.setSelectedIndex(11);
                    break;
                }
            }
            spinOctave.setValue(((Note) son).getOctave());
            spinPersistance.setValue(son.getPersistance());
        }
        else{
            btnParcourirFichierAudio.setEnabled(true);
            bgType.clearSelection();
            rbFichierAudio.setSelected(true);
            lblOctave.setEnabled(false);
            spinOctave.setEnabled(false);
            lblPersistance.setEnabled(false);
            spinPersistance.setEnabled(false);
            lblNote.setEnabled(false);
            cbNote.setEnabled(false);
        }
        int nbBordure = Outils.nbBordures(touche.getApparence().getForme()) + 2;
        if(nbBordure>0){
            if(cbBordure.getItemCount()>0){
                cbBordure.removeAllItems();
            }
            for(int i = 0; i < nbBordure; ++i){
                cbBordure.addItem("" + (i + 1));
            }
            BordureUpdater();
        }
        txtNom.setText(touche.getNom());
        checkAfficheNom.setSelected(touche.getApparence().isAfficherNom());
        checkAfficheNote.setSelected(touche.getApparence().isAfficherNote());
        checkAfficheOctave.setSelected(touche.getApparence().isAfficherOctave());
        checkAfficheCle.setSelected(touche.getApparence().isAfficherCle());
        
        // Touche reliée
        char cle = touche.getCleReliee();
        if (cle != '\u0000') // Null char
            txtCleReliee.setText("" + cle);
        else
            txtCleReliee.setText("");
    }
    
    private void ToucheEnregistrer()
    {
        int index = controleur.getInstrument().getToucheSelectionee();
        int indexBordure = cbBordure.getSelectedIndex();
        
        Touche touche = controleur.getInstrument().getTouche(index);
        
        touche.getApparence().setForme(Forme.valueOf(cbForme.getSelectedItem().toString()));
        Bordure bordure = touche.getApparence().getBordure(indexBordure);
        
        if(rbCouleur.isSelected()){
            touche.getApparence().setCouleurFond(new Color((int)spinRouge.getValue(), (int)spinVert.getValue(), (int)spinBleu.getValue()));
        }
        else{
            BufferedImage img = null;
            try 
            {
                img = ImageIO.read(m_fileToSaveImage);
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            touche.getApparence().setImageFond(img);
        }
        touche.getApparence().setDimension(new Dimension2D((double)spinLargeur.getValue(),(double)spinHauteur.getValue()));
        bordure.setVisible(checkVisible.isSelected());
        bordure.setLargeur((double)spinLargeurBordure.getValue());
        bordure.setCouleur(new Color((int)spinRougeBordure.getValue(), (int)spinVertBordure.getValue(), (int)spinBleuBordure.getValue()));
        if(rbSon.isSelected()){
            touche.enleverFichierAudio();
            ((Note)touche.getSon()).setNom(NomNote.valueOf(cbNote.getSelectedItem().toString().replaceAll("#", "Sharp")));
            ((Note)touche.getSon()).setOctave((int)spinOctave.getValue());
            ((Note)touche.getSon()).setPersistance((int)spinPersistance.getValue());
        }
        else{
            touche.importerFichierAudio(m_pathToFileAudio);
        }
        panneauAffichage.repaint();
        
        touche.setNom(txtNom.getText());
        touche.getApparence().setAfficherNom(checkAfficheNom.isSelected());
        touche.getApparence().setAfficherNote(checkAfficheNote.isSelected());
        touche.getApparence().setAfficherOctave(checkAfficheOctave.isSelected());
        touche.getApparence().setAfficherCle(checkAfficheCle.isSelected());
        
        // Touche reliee
        delierCle(touche);
        if (txtCleReliee.getText().length() > 0)
        {
            char ancienneCle = touche.getCleReliee();
            char cle = txtCleReliee.getText().toUpperCase().charAt(0);
            Object obj = panneauAffichage.getInputMap(JComponent.WHEN_FOCUSED).get(KeyStroke.getKeyStroke((int)cle, 0, false));
            
            if (Character.isAlphabetic((int)cle))
            {
                boolean changerCle = false;
                if (obj == null)
                    changerCle = true;
                else
                    changerCle = obj.toString().equals("D" + ancienneCle);
                
                if (changerCle)
                    lierCle(touche, cle);
            }
        }
        
        ToucheUpdater();
    }
    
    private void lierCle(Touche touche, char cle)
    {
        InputMap im = panneauAffichage.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap am = panneauAffichage.getActionMap();
        
        im.put(KeyStroke.getKeyStroke((int)cle, 0, false), "D" + cle);
        am.put("D" + cle, new ActionCommencerJouerTouche(touche, panneauAffichage));
        im.put(KeyStroke.getKeyStroke((int)cle, 0, true), "F" + cle);
        am.put("F" + cle, new ActionArreterJouerTouche(touche, panneauAffichage));
        
        touche.setCleReliee(cle);
    }
    
    private void delierCle(Touche touche)
    {
        InputMap im = panneauAffichage.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap am = panneauAffichage.getActionMap();
        char cle = touche.getCleReliee();
        
        im.remove(KeyStroke.getKeyStroke((int)cle, 0, false));
        im.remove(KeyStroke.getKeyStroke((int)cle, 0, true));
        am.remove("D" + cle);
        am.remove("F" + cle);
        
        touche.setCleReliee('\u0000');
    }
    
    public ControleurInstrument getControleur()
    {
        return controleur;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgFond = new javax.swing.ButtonGroup();
        bgType = new javax.swing.ButtonGroup();
        spGaudrophone = new javax.swing.JSplitPane();
        plNote = new javax.swing.JPanel();
        splitAffichage = new javax.swing.JSplitPane();
        panneauAffichage = new gaudrophone.Presentation.PanneauAffichage();
        plTextMessage = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        plParametres = new javax.swing.JPanel();
        TPInfo = new javax.swing.JTabbedPane();
        spGeneral = new javax.swing.JScrollPane();
        plGeneral = new javax.swing.JPanel();
        lblNomInstrument = new javax.swing.JLabel();
        txtNomInstrument = new javax.swing.JTextField();
        lblTimbre = new javax.swing.JLabel();
        cbTimbre = new javax.swing.JComboBox<>();
        lblMetronome = new javax.swing.JLabel();
        lblNoteMetronome = new javax.swing.JLabel();
        cbNoteMetronome = new javax.swing.JComboBox<>();
        spinOctaveMetronome = new javax.swing.JSpinner();
        spinPersistanceMetronome = new javax.swing.JSpinner();
        lblPersistanceMertronome = new javax.swing.JLabel();
        lblOctaveMetronome = new javax.swing.JLabel();
        lblFrequenceMetronome = new javax.swing.JLabel();
        spinFrequenceMetronome = new javax.swing.JSpinner();
        lblTimbreMetronome = new javax.swing.JLabel();
        cbTimbreMetronome = new javax.swing.JComboBox<>();
        btnActif = new javax.swing.JButton();
        lblRechercher = new javax.swing.JLabel();
        txtRechercher = new javax.swing.JTextField();
        lbBoucles = new javax.swing.JLabel();
        btnBoucle1 = new javax.swing.JButton();
        btnBoucle2 = new javax.swing.JButton();
        btnBoucle3 = new javax.swing.JButton();
        btnBoucle4 = new javax.swing.JButton();
        btnBoucle5 = new javax.swing.JButton();
        btnBoucle6 = new javax.swing.JButton();
        btnBoucle7 = new javax.swing.JButton();
        btnBoucle8 = new javax.swing.JButton();
        btnBoucle9 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        sliderPartition = new javax.swing.JSlider();
        btnJouerPartition = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        spTouche = new javax.swing.JScrollPane();
        plTouche = new javax.swing.JPanel();
        lblNote = new javax.swing.JLabel();
        lblOctave = new javax.swing.JLabel();
        spinOctave = new javax.swing.JSpinner();
        lblForme = new javax.swing.JLabel();
        cbForme = new javax.swing.JComboBox<>();
        lblFond = new javax.swing.JLabel();
        btnParcourirImage = new javax.swing.JButton();
        cbCouleur = new javax.swing.JComboBox<>();
        spinRouge = new javax.swing.JSpinner();
        lblRouge = new javax.swing.JLabel();
        lblVert = new javax.swing.JLabel();
        spinVert = new javax.swing.JSpinner();
        spinBleu = new javax.swing.JSpinner();
        lblBleu = new javax.swing.JLabel();
        rbImage = new javax.swing.JRadioButton();
        rbCouleur = new javax.swing.JRadioButton();
        lblDimension = new javax.swing.JLabel();
        lblHauteur = new javax.swing.JLabel();
        spinHauteur = new javax.swing.JSpinner();
        lblLargeur = new javax.swing.JLabel();
        spinLargeur = new javax.swing.JSpinner();
        lblBordure = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        rbSon = new javax.swing.JRadioButton();
        lblPersistance = new javax.swing.JLabel();
        spinPersistance = new javax.swing.JSpinner();
        rbFichierAudio = new javax.swing.JRadioButton();
        btnParcourirFichierAudio = new javax.swing.JButton();
        cbNote = new javax.swing.JComboBox<>();
        btnEnregistrerTouche = new javax.swing.JButton();
        cbBordure = new javax.swing.JComboBox<>();
        checkVisible = new javax.swing.JCheckBox();
        lblLargeurBordure = new javax.swing.JLabel();
        spinLargeurBordure = new javax.swing.JSpinner();
        lblCouleurBordure = new javax.swing.JLabel();
        cbCouleurBordure = new javax.swing.JComboBox<>();
        lblRougeBordure = new javax.swing.JLabel();
        lblVertBordure = new javax.swing.JLabel();
        lblBleuBordure = new javax.swing.JLabel();
        spinRougeBordure = new javax.swing.JSpinner();
        spinVertBordure = new javax.swing.JSpinner();
        spinBleuBordure = new javax.swing.JSpinner();
        btnEffacer = new javax.swing.JButton();
        btnTestSon = new javax.swing.JButton();
        lblNom = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        checkAfficheNom = new javax.swing.JCheckBox();
        checkAfficheNote = new javax.swing.JCheckBox();
        checkAfficheOctave = new javax.swing.JCheckBox();
        lblCleReliee = new javax.swing.JLabel();
        txtCleReliee = new javax.swing.JTextField();
        checkAfficheCle = new javax.swing.JCheckBox();
        barreMenu = new javax.swing.JMenuBar();
        menuFichier = new javax.swing.JMenu();
        miNouvelInstrument = new javax.swing.JMenuItem();
        miImporter = new javax.swing.JMenuItem();
        miImporterChanson = new javax.swing.JMenuItem();
        miEnregistrer = new javax.swing.JMenuItem();
        miEnregistrerSous = new javax.swing.JMenuItem();
        miQuitter = new javax.swing.JMenuItem();
        menuGenerer = new javax.swing.JMenu();
        miGuitare = new javax.swing.JMenuItem();
        miPiano = new javax.swing.JMenuItem();
        menuMode = new javax.swing.JMenu();
        miJouer = new javax.swing.JRadioButtonMenuItem();
        miEdition = new javax.swing.JRadioButtonMenuItem();
        miAjouterTouches = new javax.swing.JRadioButtonMenuItem();
        menuAide = new javax.swing.JMenu();
        miAPropos = new javax.swing.JMenuItem();
        miAide = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gaudrophone - Ajouter des touches");
        setMinimumSize(new java.awt.Dimension(625, 400));

        spGaudrophone.setDividerLocation(700);

        splitAffichage.setDividerLocation(800);
        splitAffichage.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        panneauAffichage.setMinimumSize(new java.awt.Dimension(300, 300));
        panneauAffichage.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panneauAffichageMouseDragged(evt);
            }
        });
        panneauAffichage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panneauAffichageMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panneauAffichageMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panneauAffichageMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panneauAffichageLayout = new javax.swing.GroupLayout(panneauAffichage);
        panneauAffichage.setLayout(panneauAffichageLayout);
        panneauAffichageLayout.setHorizontalGroup(
            panneauAffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 697, Short.MAX_VALUE)
        );
        panneauAffichageLayout.setVerticalGroup(
            panneauAffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        splitAffichage.setLeftComponent(panneauAffichage);

        txtMessage.setColumns(20);
        txtMessage.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        txtMessage.setRows(5);
        plTextMessage.setViewportView(txtMessage);

        splitAffichage.setRightComponent(plTextMessage);

        javax.swing.GroupLayout plNoteLayout = new javax.swing.GroupLayout(plNote);
        plNote.setLayout(plNoteLayout);
        plNoteLayout.setHorizontalGroup(
            plNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitAffichage)
        );
        plNoteLayout.setVerticalGroup(
            plNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitAffichage, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
        );

        spGaudrophone.setLeftComponent(plNote);

        TPInfo.setMinimumSize(new java.awt.Dimension(300, 54));

        spGeneral.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        plGeneral.setPreferredSize(new java.awt.Dimension(250, 828));

        lblNomInstrument.setText("Nom : ");

        txtNomInstrument.setText("NomInstrument");
        txtNomInstrument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomInstrumentActionPerformed(evt);
            }
        });
        txtNomInstrument.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNomInstrumentKeyTyped(evt);
            }
        });

        lblTimbre.setText("Timbre :");

        cbTimbre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Piano", "Guitare" }));
        cbTimbre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTimbreActionPerformed(evt);
            }
        });

        lblMetronome.setText("Métronome : ");

        lblNoteMetronome.setText("Note :");

        cbNoteMetronome.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" }));
        cbNoteMetronome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNoteMetronomeActionPerformed(evt);
            }
        });

        spinOctaveMetronome.setModel(new javax.swing.SpinnerNumberModel(5, 0, 9, 1));

        spinPersistanceMetronome.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        lblPersistanceMertronome.setText("Persistance (ms) :");

        lblOctaveMetronome.setText("Octave :");

        lblFrequenceMetronome.setText("Fréquence (ms) :");
        lblFrequenceMetronome.setToolTipText("");

        spinFrequenceMetronome.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        lblTimbreMetronome.setText("Timbre :");

        cbTimbreMetronome.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Steel Drums", "Piano", "Guitare" }));
        cbTimbreMetronome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTimbreMetronomeActionPerformed(evt);
            }
        });

        btnActif.setText("Activer");
        btnActif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActifActionPerformed(evt);
            }
        });

        lblRechercher.setText("Rechercher :");

        lbBoucles.setText("Boucles :");

        btnBoucle1.setText("1");
        btnBoucle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoucle1ActionPerformed(evt);
            }
        });

        btnBoucle2.setText("2");

        btnBoucle3.setText("3");

        btnBoucle4.setText("4");

        btnBoucle5.setText("5");

        btnBoucle6.setText("6");

        btnBoucle7.setText("7");

        btnBoucle8.setText("8");

        btnBoucle9.setText("9");

        jLabel2.setText("Partition :");

        btnJouerPartition.setText("Jouer");
        btnJouerPartition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJouerPartitionActionPerformed(evt);
            }
        });

        jLabel1.setText("Instrument :");

        javax.swing.GroupLayout plGeneralLayout = new javax.swing.GroupLayout(plGeneral);
        plGeneral.setLayout(plGeneralLayout);
        plGeneralLayout.setHorizontalGroup(
            plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addComponent(jSeparator4)
            .addGroup(plGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plGeneralLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTimbre)
                            .addComponent(lblNomInstrument))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTimbre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNomInstrument)))
                    .addGroup(plGeneralLayout.createSequentialGroup()
                        .addComponent(lblRechercher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRechercher))
                    .addGroup(plGeneralLayout.createSequentialGroup()
                        .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNoteMetronome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblOctaveMetronome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPersistanceMertronome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFrequenceMetronome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTimbreMetronome, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTimbreMetronome, 0, 152, Short.MAX_VALUE)
                            .addComponent(spinFrequenceMetronome)
                            .addComponent(spinPersistanceMetronome)
                            .addComponent(spinOctaveMetronome)
                            .addComponent(btnActif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbNoteMetronome, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(plGeneralLayout.createSequentialGroup()
                        .addComponent(btnBoucle7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBoucle8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBoucle9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(plGeneralLayout.createSequentialGroup()
                        .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBoucle4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBoucle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBoucle5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBoucle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBoucle6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBoucle3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(sliderPartition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(plGeneralLayout.createSequentialGroup()
                        .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lblMetronome)
                            .addComponent(lbBoucles)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnJouerPartition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jSeparator5)
        );
        plGeneralLayout.setVerticalGroup(
            plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plGeneralLayout.createSequentialGroup()
                        .addComponent(txtNomInstrument, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(cbTimbre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plGeneralLayout.createSequentialGroup()
                        .addComponent(lblNomInstrument)
                        .addGap(21, 21, 21)
                        .addComponent(lblTimbre)))
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMetronome)
                .addGap(18, 18, 18)
                .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plGeneralLayout.createSequentialGroup()
                        .addComponent(lblNoteMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(lblOctaveMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblPersistanceMertronome, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblFrequenceMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plGeneralLayout.createSequentialGroup()
                        .addComponent(cbNoteMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(spinOctaveMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(spinPersistanceMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(spinFrequenceMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTimbreMetronome)
                    .addComponent(cbTimbreMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnActif)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRechercher)
                    .addComponent(txtRechercher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBoucles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBoucle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBoucle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBoucle3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBoucle4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBoucle5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBoucle6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBoucle7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBoucle8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBoucle9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderPartition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnJouerPartition)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(275, 275, 275))
        );

        lblTimbre.getAccessibleContext().setAccessibleDescription("");

        spGeneral.setViewportView(plGeneral);

        TPInfo.addTab("Général", spGeneral);

        spTouche.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        plTouche.setPreferredSize(new java.awt.Dimension(250, 888));

        lblNote.setText("Note : ");

        lblOctave.setText("Octave :");

        spinOctave.setModel(new javax.swing.SpinnerNumberModel(5, 0, 9, 1));

        lblForme.setText("Forme :");

        cbForme.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cercle", "Triangle", "Rectangle", "Pentagone", "Hexagone" }));
        cbForme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFormeActionPerformed(evt);
            }
        });

        lblFond.setText("Fond : ");

        btnParcourirImage.setText("Parcourir");
        btnParcourirImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParcourirImageActionPerformed(evt);
            }
        });

        cbCouleur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Rouge", "Vert", "Bleu", "Brun", "Beige", "Jaune", "Blanc", "Noir" }));
        cbCouleur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCouleurActionPerformed(evt);
            }
        });

        spinRouge.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));

        lblRouge.setText("Rouge");

        lblVert.setText("Vert");

        spinVert.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));

        spinBleu.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));

        lblBleu.setText("Bleu");

        rbImage.setText("Image");
        rbImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbImageActionPerformed(evt);
            }
        });

        rbCouleur.setSelected(true);
        rbCouleur.setText("Couleur");
        rbCouleur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCouleurActionPerformed(evt);
            }
        });

        lblDimension.setText("Dimension :");

        lblHauteur.setText("Hauteur :");

        spinHauteur.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 1.0d, 0.01d));

        lblLargeur.setText("Largeur :");

        spinLargeur.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 1.0d, 0.01d));

        lblBordure.setText("Bordure(s) :");

        lblType.setText("Type :");

        rbSon.setSelected(true);
        rbSon.setText("Son");
        rbSon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSonActionPerformed(evt);
            }
        });

        lblPersistance.setText("Persistance :");

        spinPersistance.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        rbFichierAudio.setText("Fichier Audio");
        rbFichierAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFichierAudioActionPerformed(evt);
            }
        });

        btnParcourirFichierAudio.setText("Parcourir");
        btnParcourirFichierAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParcourirFichierAudioActionPerformed(evt);
            }
        });

        cbNote.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" }));
        cbNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNoteActionPerformed(evt);
            }
        });

        btnEnregistrerTouche.setText("Enregistrer");
        btnEnregistrerTouche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerToucheActionPerformed(evt);
            }
        });

        cbBordure.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                cbBordureComponentShown(evt);
            }
        });
        cbBordure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBordureActionPerformed(evt);
            }
        });

        checkVisible.setSelected(true);
        checkVisible.setText("Visible");

        lblLargeurBordure.setText("Largeur :");

        spinLargeurBordure.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 1.0d, 0.001d));

        lblCouleurBordure.setText("Couleur :");

        cbCouleurBordure.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Rouge", "Vert", "Bleu", "Brun", "Beige", "Jaune", "Blanc", "Noir" }));
        cbCouleurBordure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCouleurBordureActionPerformed(evt);
            }
        });

        lblRougeBordure.setText("Rouge");

        lblVertBordure.setText("Vert");

        lblBleuBordure.setText("Bleu");

        spinRougeBordure.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));

        spinVertBordure.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));

        spinBleuBordure.setModel(new javax.swing.SpinnerNumberModel(0, 0, 255, 1));

        btnEffacer.setText("Effacer la Touche");
        btnEffacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEffacerActionPerformed(evt);
            }
        });

        btnTestSon.setText("Aperçu du son");
        btnTestSon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestSonActionPerformed(evt);
            }
        });

        lblNom.setText("Nom :");

        txtNom.setText("NomDeLaTouche");
        txtNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomActionPerformed(evt);
            }
        });

        lblCleReliee.setText("Clé reliée:");

        javax.swing.GroupLayout plToucheLayout = new javax.swing.GroupLayout(plTouche);
        plTouche.setLayout(plToucheLayout);
        plToucheLayout.setHorizontalGroup(
            plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plToucheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnParcourirImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(plToucheLayout.createSequentialGroup()
                                .addComponent(rbImage)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(plToucheLayout.createSequentialGroup()
                                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbCouleur)
                                    .addComponent(cbCouleur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblBleu)
                                    .addComponent(lblVert)
                                    .addComponent(lblRouge))
                                .addGap(12, 12, 12)
                                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(spinVert, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                    .addComponent(spinRouge)
                                    .addComponent(spinBleu)))))
                    .addComponent(btnEnregistrerTouche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEffacer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plToucheLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNote, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblOctave, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPersistance, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(plToucheLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(spinPersistance))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, plToucheLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(plToucheLayout.createSequentialGroup()
                                        .addComponent(checkAfficheOctave)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spinOctave))
                                    .addGroup(plToucheLayout.createSequentialGroup()
                                        .addComponent(checkAfficheNote)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbNote, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnParcourirFichierAudio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLargeur)
                            .addComponent(lblHauteur))
                        .addGap(18, 18, 18)
                        .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinHauteur)
                            .addComponent(spinLargeur)))
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(plToucheLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbBordure, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLargeurBordure)
                                    .addComponent(lblCouleurBordure))
                                .addGap(23, 23, 23))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plToucheLayout.createSequentialGroup()
                                .addComponent(cbCouleurBordure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(plToucheLayout.createSequentialGroup()
                                .addComponent(checkVisible)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(spinLargeurBordure)
                            .addGroup(plToucheLayout.createSequentialGroup()
                                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblRougeBordure)
                                    .addComponent(lblVertBordure)
                                    .addComponent(lblBleuBordure))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spinRougeBordure)
                                    .addComponent(spinVertBordure)
                                    .addComponent(spinBleuBordure)))))
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addComponent(rbSon)
                        .addGap(31, 31, 31)
                        .addComponent(btnTestSon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addComponent(lblForme)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbForme, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addComponent(lblNom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkAfficheNom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNom))
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFond)
                            .addComponent(lblType)
                            .addComponent(lblDimension)
                            .addComponent(rbFichierAudio)
                            .addComponent(lblBordure))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addComponent(lblCleReliee)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkAfficheCle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCleReliee)))
                .addContainerGap())
        );
        plToucheLayout.setVerticalGroup(
            plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plToucheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNom)
                        .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(checkAfficheNom))
                .addGap(13, 13, 13)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkAfficheCle)
                    .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCleReliee)
                        .addComponent(txtCleReliee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblForme)
                    .addComponent(cbForme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFond)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbCouleur)
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addComponent(spinRouge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinVert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCouleur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinBleu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblRouge)
                        .addGap(10, 10, 10)
                        .addComponent(lblVert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBleu)))
                .addGap(13, 13, 13)
                .addComponent(rbImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnParcourirImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDimension)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHauteur)
                    .addComponent(spinHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLargeur)
                    .addComponent(spinLargeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblBordure)
                .addGap(11, 11, 11)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBordure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkVisible))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLargeurBordure)
                    .addComponent(spinLargeurBordure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addComponent(lblCouleurBordure)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCouleurBordure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinRougeBordure, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRougeBordure))
                        .addGap(13, 13, 13)
                        .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinVertBordure, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVertBordure))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spinBleuBordure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBleuBordure))))
                .addGap(35, 35, 35)
                .addComponent(lblType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbSon)
                    .addComponent(btnTestSon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNote)
                    .addComponent(cbNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkAfficheNote))
                .addGap(7, 7, 7)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOctave)
                    .addComponent(spinOctave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkAfficheOctave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPersistance)
                    .addComponent(spinPersistance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbFichierAudio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnParcourirFichierAudio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEnregistrerTouche)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEffacer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        spTouche.setViewportView(plTouche);

        TPInfo.addTab("Touche", spTouche);

        javax.swing.GroupLayout plParametresLayout = new javax.swing.GroupLayout(plParametres);
        plParametres.setLayout(plParametresLayout);
        plParametresLayout.setHorizontalGroup(
            plParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TPInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        plParametresLayout.setVerticalGroup(
            plParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TPInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        spGaudrophone.setRightComponent(plParametres);

        menuFichier.setLabel("Fichier");

        miNouvelInstrument.setText("Nouvel Instrument");
        miNouvelInstrument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNouvelInstrumentActionPerformed(evt);
            }
        });
        menuFichier.add(miNouvelInstrument);

        miImporter.setText("Importer");
        miImporter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miImporterActionPerformed(evt);
            }
        });
        menuFichier.add(miImporter);

        miImporterChanson.setText("Importer chanson");
        miImporterChanson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miImporterChansonActionPerformed(evt);
            }
        });
        menuFichier.add(miImporterChanson);

        miEnregistrer.setText("Enregistrer");
        miEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEnregistrerActionPerformed(evt);
            }
        });
        menuFichier.add(miEnregistrer);

        miEnregistrerSous.setText("Enregistrer sous");
        miEnregistrerSous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEnregistrerSousActionPerformed(evt);
            }
        });
        menuFichier.add(miEnregistrerSous);

        miQuitter.setText("Quitter");
        miQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miQuitterActionPerformed(evt);
            }
        });
        menuFichier.add(miQuitter);

        barreMenu.add(menuFichier);

        menuGenerer.setText("Générer");

        miGuitare.setText("Guitare");
        miGuitare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miGuitareActionPerformed(evt);
            }
        });
        menuGenerer.add(miGuitare);

        miPiano.setText("Piano");
        miPiano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPianoActionPerformed(evt);
            }
        });
        menuGenerer.add(miPiano);

        barreMenu.add(menuGenerer);

        menuMode.setText("Mode");

        miJouer.setSelected(true);
        miJouer.setText("Jouer");
        miJouer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miJouerActionPerformed(evt);
            }
        });
        menuMode.add(miJouer);

        miEdition.setSelected(true);
        miEdition.setText("Édition");
        miEdition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEditionActionPerformed(evt);
            }
        });
        menuMode.add(miEdition);

        miAjouterTouches.setSelected(true);
        miAjouterTouches.setText("Ajouter des touches");
        miAjouterTouches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAjouterTouchesActionPerformed(evt);
            }
        });
        menuMode.add(miAjouterTouches);

        barreMenu.add(menuMode);

        menuAide.setText("?");

        miAPropos.setText("À propos");
        miAPropos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAProposActionPerformed(evt);
            }
        });
        menuAide.add(miAPropos);

        miAide.setText("Aide");
        miAide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAideActionPerformed(evt);
            }
        });
        menuAide.add(miAide);

        barreMenu.add(menuAide);

        setJMenuBar(barreMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(spGaudrophone)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spGaudrophone)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miJouerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miJouerActionPerformed
        controleur.modifierModeVisuel(ModeVisuel.Jouer);
        TPInfo.setSelectedIndex(0); 
        TPInfo.setEnabledAt(1,false);
        setTitle("Gaudrophone - Jouer");
        panneauAffichage.repaint();
    }//GEN-LAST:event_miJouerActionPerformed

    private void miEditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEditionActionPerformed
        controleur.modifierModeVisuel(ModeVisuel.Editer);
        TPInfo.setSelectedIndex(0); 
        TPInfo.setEnabledAt(1,false);
        setTitle("Gaudrophone - Édition");
        panneauAffichage.repaint();
    }//GEN-LAST:event_miEditionActionPerformed

    private void miAjouterTouchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAjouterTouchesActionPerformed
        controleur.modifierModeVisuel(ModeVisuel.Ajouter);
        TPInfo.setSelectedIndex(0); 
        TPInfo.setEnabledAt(1,false);
        setTitle("Gaudrophone - Ajouter des touches");
        panneauAffichage.repaint();
    }//GEN-LAST:event_miAjouterTouchesActionPerformed

    private void miAideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAideActionPerformed
        afficherAider("\\base.txt","\\aide.txt");
    }//GEN-LAST:event_miAideActionPerformed

    private void miAProposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAProposActionPerformed
        afficherAider("\\base.txt","\\apropos.txt");
    }//GEN-LAST:event_miAProposActionPerformed

    private void cbNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNoteActionPerformed

    private void btnParcourirFichierAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParcourirFichierAudioActionPerformed
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Audio Files", "wav");
        fc.setFileFilter(filter);
        fc.setDialogTitle("Spécifier le fichier audio à utiliser.");
        int returnVal = fc.showOpenDialog(plGeneral);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m_pathToFileAudio = fc.getSelectedFile().getAbsolutePath();
        }
    }//GEN-LAST:event_btnParcourirFichierAudioActionPerformed

    private void rbFichierAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFichierAudioActionPerformed
        btnParcourirFichierAudio.setEnabled(true);
        lblOctave.setEnabled(false);
        spinOctave.setEnabled(false);
        lblPersistance.setEnabled(false);
        spinPersistance.setEnabled(false);
        lblNote.setEnabled(false);
        cbNote.setEnabled(false);
        checkAfficheNote.setEnabled(false);
        checkAfficheOctave.setEnabled(false);
    }//GEN-LAST:event_rbFichierAudioActionPerformed

    private void rbSonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSonActionPerformed
        btnParcourirFichierAudio.setEnabled(false);
        lblOctave.setEnabled(true);
        spinOctave.setEnabled(true);
        lblPersistance.setEnabled(true);
        spinPersistance.setEnabled(true);
        lblNote.setEnabled(true);
        cbNote.setEnabled(true);
        checkAfficheNote.setEnabled(true);
        checkAfficheOctave.setEnabled(true);
    }//GEN-LAST:event_rbSonActionPerformed

    private void rbCouleurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCouleurActionPerformed
        btnParcourirImage.setEnabled(false);
        cbCouleur.setEnabled(true);
        spinRouge.setEnabled(true);
        lblRouge.setEnabled(true);
        spinVert.setEnabled(true);
        lblVert.setEnabled(true);
        spinBleu.setEnabled(true);
        lblBleu.setEnabled(true);
    }//GEN-LAST:event_rbCouleurActionPerformed

    private void rbImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbImageActionPerformed
        btnParcourirImage.setEnabled(true);
        cbCouleur.setEnabled(false);
        spinRouge.setEnabled(false);
        lblRouge.setEnabled(false);
        spinVert.setEnabled(false);
        lblVert.setEnabled(false);
        spinBleu.setEnabled(false);
        lblBleu.setEnabled(false);
    }//GEN-LAST:event_rbImageActionPerformed

    private void setSpinColor(String strCouleur)
    {
        dictCouleur couleurs= new dictCouleur();
        Color couleur;
        couleur=couleurs.trouverParClee(strCouleur);
        spinRouge.setValue(couleur.getRed());
        spinVert.setValue(couleur.getGreen());
        spinBleu.setValue(couleur.getBlue());
    }
    
    private void cbCouleurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCouleurActionPerformed
        String value = cbCouleur.getSelectedItem().toString();
        switch(value){
            case "Rouge":
            setSpinColor("ROUGE");
            break;
            case "Vert":
            setSpinColor("VERT");
            break;
            case "Bleu":
            setSpinColor("BLEU");
            break;
            case "Brun":
            setSpinColor("BRUN");
            break;
            case "Beige":
            setSpinColor("BEIGE");
            break;
            case "Jaune":
            setSpinColor("JAUNE");
            break;
            case "Blanc":
            setSpinColor("BLANC");
            break;
            case "Noir":
            setSpinColor("NOIR");
            break;
        }
    }//GEN-LAST:event_cbCouleurActionPerformed

    private void btnParcourirImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParcourirImageActionPerformed
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "bmp", "jpeg");
        fc.setFileFilter(filter);
        fc.setDialogTitle("Spécifier l'image à utiliser.");
        int returnVal = fc.showOpenDialog(plGeneral);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m_fileToSaveImage = fc.getSelectedFile();
        }
    }//GEN-LAST:event_btnParcourirImageActionPerformed

    private void cbNoteMetronomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNoteMetronomeActionPerformed
        setMetronome();
    }//GEN-LAST:event_cbNoteMetronomeActionPerformed

    private void txtNomInstrumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomInstrumentActionPerformed
        
    }//GEN-LAST:event_txtNomInstrumentActionPerformed

    private void panneauAffichageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panneauAffichageMouseClicked
        panneauAffichage.requestFocusInWindow();
        int dimension=(panneauAffichage.getWidth()>panneauAffichage.getHeight()?panneauAffichage.getHeight():panneauAffichage.getWidth());
        controleur.cliquerSouris(Outils.conversionPointPixelRelatif( evt.getPoint(),dimension));
        panneauAffichage.repaint();
    }//GEN-LAST:event_panneauAffichageMouseClicked

    private void panneauAffichageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panneauAffichageMousePressed
        boolean clickTouche;
        int dimension=(panneauAffichage.getWidth()>panneauAffichage.getHeight()?panneauAffichage.getHeight():panneauAffichage.getWidth());
        clickTouche = controleur.enfoncerSouris(Outils.conversionPointPixelRelatif( evt.getPoint(),dimension));
        panneauAffichage.repaint();
        if(clickTouche == true)
        {
            TPInfo.setEnabledAt(1,true);
            ToucheUpdater();
        }
        else
        {
            TPInfo.setSelectedIndex(0); 
            TPInfo.setEnabledAt(1,false);
        }
    }//GEN-LAST:event_panneauAffichageMousePressed

    private void panneauAffichageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panneauAffichageMouseReleased
        int dimension=(panneauAffichage.getWidth()>panneauAffichage.getHeight()?panneauAffichage.getHeight():panneauAffichage.getWidth());
        controleur.relacherSouris(Outils.conversionPointPixelRelatif( evt.getPoint(),dimension));
        panneauAffichage.repaint();
    }//GEN-LAST:event_panneauAffichageMouseReleased

    private void panneauAffichageMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panneauAffichageMouseDragged
        int dimension=(panneauAffichage.getWidth()>panneauAffichage.getHeight()?panneauAffichage.getHeight():panneauAffichage.getWidth());
        controleur.glisserSouris(Outils.conversionPointPixelRelatif( evt.getPoint(),dimension));
        panneauAffichage.repaint();
    }//GEN-LAST:event_panneauAffichageMouseDragged

    private void BordureUpdater(){
        int indexBordure = cbBordure.getSelectedIndex();
        if(indexBordure >= 0){
            int indexTouche = controleur.getInstrument().getToucheSelectionee();
            Touche touche = controleur.getInstrument().getTouche(indexTouche);
            Bordure bordure = touche.getApparence().getBordure(indexBordure);
            Color couleur = bordure.getCouleur();

            checkVisible.setSelected(bordure.getVisible());
            spinLargeurBordure.setValue(bordure.getLargeur());

            cbCouleurBordure.setSelectedIndex(0);
            spinRougeBordure.setValue(couleur.getRed());
            spinBleuBordure.setValue(couleur.getBlue());
            spinVertBordure.setValue(couleur.getGreen());
        }
    }
    
    private void cbBordureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBordureActionPerformed
        BordureUpdater();
    }//GEN-LAST:event_cbBordureActionPerformed

    private void miNouvelInstrumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNouvelInstrumentActionPerformed
        for (Touche touche: controleur.getInstrument().getTouches())
            delierCle(touche);
        controleur.nouvelInstrument();
        panneauAffichage.repaint();
    }//GEN-LAST:event_miNouvelInstrumentActionPerformed

    private void btnEffacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEffacerActionPerformed
        delierCle(controleur.getInstrument().getTouche());
        controleur.getInstrument().retirerTouche(controleur.getInstrument().getToucheSelectionee());
        TPInfo.setSelectedIndex(0); 
        TPInfo.setEnabledAt(1,false);
        panneauAffichage.repaint();
    }//GEN-LAST:event_btnEffacerActionPerformed

    private void cbCouleurBordureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCouleurBordureActionPerformed
        String value = cbCouleurBordure.getSelectedItem().toString();
        switch(value){
            case "Rouge":
            spinRougeBordure.setValue(255);
            spinVertBordure.setValue(0);
            spinBleuBordure.setValue(0);
            break;
            case "Vert":
            spinRougeBordure.setValue(0);
            spinVertBordure.setValue(255);
            spinBleuBordure.setValue(0);
            break;
            case "Bleu":
            spinRougeBordure.setValue(0);
            spinVertBordure.setValue(0);
            spinBleuBordure.setValue(255);
            break;
            case "Brun":
            spinRougeBordure.setValue(72);
            spinVertBordure.setValue(52);
            spinBleuBordure.setValue(32);
            break;
            case "Beige":
            spinRougeBordure.setValue(245);
            spinVertBordure.setValue(245);
            spinBleuBordure.setValue(220);
            break;
            case "Jaune":
            spinRougeBordure.setValue(255);
            spinVertBordure.setValue(255);
            spinBleuBordure.setValue(0);
            break;
            case "Blanc":
            spinRougeBordure.setValue(255);
            spinVertBordure.setValue(255);
            spinBleuBordure.setValue(255);
            break;
            case "Noir":
            spinRougeBordure.setValue(0);
            spinVertBordure.setValue(0);
            spinBleuBordure.setValue(0);
            break;
        }
    }//GEN-LAST:event_cbCouleurBordureActionPerformed

    private void cbBordureComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_cbBordureComponentShown

    }//GEN-LAST:event_cbBordureComponentShown

    private void miGuitareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miGuitareActionPerformed
        genererInstrument(new GenerateurGuitare());
    }//GEN-LAST:event_miGuitareActionPerformed

    private void btnEnregistrerToucheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerToucheActionPerformed
        ToucheEnregistrer();
    }//GEN-LAST:event_btnEnregistrerToucheActionPerformed

    private void miPianoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPianoActionPerformed
        genererInstrument(new GenerateurPiano());
    }//GEN-LAST:event_miPianoActionPerformed

    private void genererInstrument(GenerateurInstrument generateur)
    {
        for (Touche touche: controleur.getInstrument().getTouches())
            delierCle(touche);
        controleur.genererInstrument(generateur);
        panneauAffichage.repaint();
        InstrumentUpdater();
    }
    
    private void btnTestSonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestSonActionPerformed
        if(rbSon.isSelected()){
            int timbre = 1;
            switch(cbTimbre.getSelectedIndex()){
                case 0:{
                    timbre = 1;
                    break;
                }
                case 1:{
                    timbre = 25;
                    break;
                }
            }
            Note noteTest = new Note(timbre, NomNote.valueOf(cbNote.getSelectedItem().toString().replaceAll("#", "Sharp")), (int)spinOctave.getValue());
            noteTest.setPersistance((int)spinPersistance.getValue());
            noteTest.commencerJouer();
            noteTest.arreterJouer();
        }
        else{
            FichierAudio fichierAudioTest = new FichierAudio(m_pathToFileAudio);
            fichierAudioTest.commencerJouer();
            
        }
        
    }//GEN-LAST:event_btnTestSonActionPerformed

    private void txtNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomActionPerformed

    private void setMetronome()
    {
        int timbre=0;
        switch(cbTimbreMetronome.getSelectedIndex()){
            case 0:{
                timbre = 115;
                break;
            }
            case 1:{
                timbre = 1;
                break;
            }
            case 2:{
                timbre = 25;
                break;
            }
        }
        Note note= new Note(timbre);

        int frequence=(int)spinFrequenceMetronome.getValue();
        int persistance=(int) spinPersistanceMetronome.getValue();

        int octave=(int)spinOctaveMetronome.getValue();
        NomNote nomNote=NomNote.valueOf(cbNoteMetronome.getSelectedItem().toString().replaceAll("#", "Sharp"));

        note.setOctave(octave);
        note.setNom(nomNote);
        note.setPersistance(persistance);

        controleur.getMetronome().setNote(note);
        controleur.getMetronome().setTimbre(timbre);
        controleur.getMetronome().setFrequence(frequence);

    }
    
    private void btnActifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActifActionPerformed
            if(btnActif.getText().equals("Activer"))
            {
                
                controleur.getMetronome().demarrer();
                
                setMetronome();
                
                btnActif.setText("Désactiver");
            }
            else
            {
                controleur.getMetronome().arreter();
                btnActif.setText("Activer");
            }

    }//GEN-LAST:event_btnActifActionPerformed

    private void btnBoucle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoucle1ActionPerformed
        
        
    }//GEN-LAST:event_btnBoucle1ActionPerformed

    private void cbTimbreMetronomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTimbreMetronomeActionPerformed
        setMetronome();
    }//GEN-LAST:event_cbTimbreMetronomeActionPerformed

    private void txtNomInstrumentKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomInstrumentKeyTyped
                
        char character =evt.getKeyChar();
        String nomInstrument=txtNomInstrument.getText();
        if(Character.isDigit(character) || Character.isLetter(character) )
        {
            nomInstrument=nomInstrument+character;
        }
        
        controleur.getInstrument().setNom(nomInstrument);
        
    }//GEN-LAST:event_txtNomInstrumentKeyTyped

    private void cbTimbreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTimbreActionPerformed
        int index = cbTimbre.getSelectedIndex();
        switch(index){
            case 0:{
                controleur.getInstrument().setTimbre(1);
                if(controleur.getPartition() != null)
                    controleur.getPartition().updateTimbre(1);
                break;
            }
            case 1:{
                controleur.getInstrument().setTimbre(25);
                if(controleur.getPartition() != null)
                    controleur.getPartition().updateTimbre(25);
                break;
            }
        }
    }//GEN-LAST:event_cbTimbreActionPerformed

    private void cbFormeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFormeActionPerformed
        
    }//GEN-LAST:event_cbFormeActionPerformed

    private void btnJouerPartitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJouerPartitionActionPerformed
        if (btnJouerPartition.isSelected())
        {
            String partition = controleur.jouerPartition();
            txtMessage.setWrapStyleWord(false);
            txtMessage.setLineWrap(false);
            txtMessage.setText(partition);
        }
        else
        {
            
        }
    }//GEN-LAST:event_btnJouerPartitionActionPerformed
   
    private void miImporterChansonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        controleur.importerPartition(panneauAffichage, sliderPartition);
    }

    private void miEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {
        controleur.sauvegarderInstrument();
    }

    private void miEnregistrerSousActionPerformed(java.awt.event.ActionEvent evt) {
       controleur.sauvegarderSousInstrument();
    }

    private void miImporterActionPerformed(java.awt.event.ActionEvent evt) {
        controleur.importerInstrument();
        for (Touche touche: controleur.getInstrument().getTouches())
            lierCle(touche, touche.getCleReliee());
        panneauAffichage.repaint();
        InstrumentUpdater();
    }

    private void miQuitterActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    
    private void afficherAider(String filename, String... optionalFilenames)
    {
        Outils tool = new Outils();
        
        String strTexte = tool.readFile(filename,"");
        
        for(int i=0;i<optionalFilenames.length;++i)
        {
            strTexte = tool.readFile(optionalFilenames[i],strTexte);
        }
        txtMessage.setWrapStyleWord(true);
        txtMessage.setLineWrap(true);
        txtMessage.setText(strTexte);
        txtMessage.setCaretPosition(0);
    }
        
    private void initializeRadioButton()
    {
        m_btnGroupeMode= new ButtonGroup();
        m_btnGroupeMode.add(miAjouterTouches);
        m_btnGroupeMode.add(miEdition);
        m_btnGroupeMode.add(miJouer);
        miAjouterTouches.setSelected(true);
        
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenetreInstrument.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreInstrument.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreInstrument.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreInstrument.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetreInstrument().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TPInfo;
    private javax.swing.JMenuBar barreMenu;
    private javax.swing.ButtonGroup bgFond;
    private javax.swing.ButtonGroup bgType;
    private javax.swing.JButton btnActif;
    private javax.swing.JButton btnBoucle1;
    private javax.swing.JButton btnBoucle2;
    private javax.swing.JButton btnBoucle3;
    private javax.swing.JButton btnBoucle4;
    private javax.swing.JButton btnBoucle5;
    private javax.swing.JButton btnBoucle6;
    private javax.swing.JButton btnBoucle7;
    private javax.swing.JButton btnBoucle8;
    private javax.swing.JButton btnBoucle9;
    private javax.swing.JButton btnEffacer;
    private javax.swing.JButton btnEnregistrerTouche;
    private javax.swing.JToggleButton btnJouerPartition;
    private javax.swing.JButton btnParcourirFichierAudio;
    private javax.swing.JButton btnParcourirImage;
    private javax.swing.JButton btnTestSon;
    private javax.swing.JComboBox<String> cbBordure;
    private javax.swing.JComboBox<String> cbCouleur;
    private javax.swing.JComboBox<String> cbCouleurBordure;
    private javax.swing.JComboBox<String> cbForme;
    private javax.swing.JComboBox<String> cbNote;
    private javax.swing.JComboBox<String> cbNoteMetronome;
    private javax.swing.JComboBox<String> cbTimbre;
    private javax.swing.JComboBox<String> cbTimbreMetronome;
    private javax.swing.JCheckBox checkAfficheCle;
    private javax.swing.JCheckBox checkAfficheNom;
    private javax.swing.JCheckBox checkAfficheNote;
    private javax.swing.JCheckBox checkAfficheOctave;
    private javax.swing.JCheckBox checkVisible;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lbBoucles;
    private javax.swing.JLabel lblBleu;
    private javax.swing.JLabel lblBleuBordure;
    private javax.swing.JLabel lblBordure;
    private javax.swing.JLabel lblCleReliee;
    private javax.swing.JLabel lblCouleurBordure;
    private javax.swing.JLabel lblDimension;
    private javax.swing.JLabel lblFond;
    private javax.swing.JLabel lblForme;
    private javax.swing.JLabel lblFrequenceMetronome;
    private javax.swing.JLabel lblHauteur;
    private javax.swing.JLabel lblLargeur;
    private javax.swing.JLabel lblLargeurBordure;
    private javax.swing.JLabel lblMetronome;
    private javax.swing.JLabel lblNom;
    private javax.swing.JLabel lblNomInstrument;
    private javax.swing.JLabel lblNote;
    private javax.swing.JLabel lblNoteMetronome;
    private javax.swing.JLabel lblOctave;
    private javax.swing.JLabel lblOctaveMetronome;
    private javax.swing.JLabel lblPersistance;
    private javax.swing.JLabel lblPersistanceMertronome;
    private javax.swing.JLabel lblRechercher;
    private javax.swing.JLabel lblRouge;
    private javax.swing.JLabel lblRougeBordure;
    private javax.swing.JLabel lblTimbre;
    private javax.swing.JLabel lblTimbreMetronome;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblVert;
    private javax.swing.JLabel lblVertBordure;
    private javax.swing.JMenu menuAide;
    private javax.swing.JMenu menuFichier;
    private javax.swing.JMenu menuGenerer;
    private javax.swing.JMenu menuMode;
    private javax.swing.JMenuItem miAPropos;
    private javax.swing.JMenuItem miAide;
    private javax.swing.JRadioButtonMenuItem miAjouterTouches;
    private javax.swing.JRadioButtonMenuItem miEdition;
    private javax.swing.JMenuItem miEnregistrer;
    private javax.swing.JMenuItem miEnregistrerSous;
    private javax.swing.JMenuItem miGuitare;
    private javax.swing.JMenuItem miImporter;
    private javax.swing.JMenuItem miImporterChanson;
    private javax.swing.JRadioButtonMenuItem miJouer;
    private javax.swing.JMenuItem miNouvelInstrument;
    private javax.swing.JMenuItem miPiano;
    private javax.swing.JMenuItem miQuitter;
    private gaudrophone.Presentation.PanneauAffichage panneauAffichage;
    private javax.swing.JPanel plGeneral;
    private javax.swing.JPanel plNote;
    private javax.swing.JPanel plParametres;
    private javax.swing.JScrollPane plTextMessage;
    private javax.swing.JPanel plTouche;
    private javax.swing.JRadioButton rbCouleur;
    private javax.swing.JRadioButton rbFichierAudio;
    private javax.swing.JRadioButton rbImage;
    private javax.swing.JRadioButton rbSon;
    private javax.swing.JSlider sliderPartition;
    private javax.swing.JSplitPane spGaudrophone;
    private javax.swing.JScrollPane spGeneral;
    private javax.swing.JScrollPane spTouche;
    private javax.swing.JSpinner spinBleu;
    private javax.swing.JSpinner spinBleuBordure;
    private javax.swing.JSpinner spinFrequenceMetronome;
    private javax.swing.JSpinner spinHauteur;
    private javax.swing.JSpinner spinLargeur;
    private javax.swing.JSpinner spinLargeurBordure;
    private javax.swing.JSpinner spinOctave;
    private javax.swing.JSpinner spinOctaveMetronome;
    private javax.swing.JSpinner spinPersistance;
    private javax.swing.JSpinner spinPersistanceMetronome;
    private javax.swing.JSpinner spinRouge;
    private javax.swing.JSpinner spinRougeBordure;
    private javax.swing.JSpinner spinVert;
    private javax.swing.JSpinner spinVertBordure;
    private javax.swing.JSplitPane splitAffichage;
    private javax.swing.JTextField txtCleReliee;
    private javax.swing.JTextArea txtMessage;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtNomInstrument;
    private javax.swing.JTextField txtRechercher;
    // End of variables declaration//GEN-END:variables
}
