package gaudrophone.Presentation;

import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.ControleurInstrument;
import gaudrophone.Domaine.Enums.ModeVisuel;
import gaudrophone.Domaine.Outils;
import java.awt.Color;
import javax.swing.*;

public class FenetreInstrument extends javax.swing.JFrame {
    ButtonGroup m_btnGroupeMode;
    ControleurInstrument controleur;
    
    
    public FenetreInstrument() {
        initComponents();
        initializeRadioButton();
        
        controleur = new ControleurInstrument();
        panneauAffichage.setFenetreInstrument(this);
        controleur.modifierModeVisuel(ModeVisuel.Ajouter);
        
        //txtAide.setVisible(false);
        //btnOkAide.setVisible(false);
        //scrlAide.setVisible(false);
        TPInfo.setEnabledAt(1,false);
        ((JSpinner.DefaultEditor) spinOctave.getEditor()).getTextField().setEditable(false);
        bgFond.add(rbCouleur);
        bgFond.add(rbImage);
        bgType.add(rbSon);
        bgType.add(rbFichierAudio);
        btnParcourirImage.setEnabled(false);
        btnParcourirFichierAudio.setEnabled(false);
        spGaudrophone.setResizeWeight(1);


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
        panneauAffichage = new gaudrophone.Presentation.PanneauAffichage();
        plParametres = new javax.swing.JPanel();
        TPInfo = new javax.swing.JTabbedPane();
        plInstrument = new javax.swing.JPanel();
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
        spinFrequence = new javax.swing.JSpinner();
        lblTimbreMetronome = new javax.swing.JLabel();
        cbTimbreMetronome = new javax.swing.JComboBox<>();
        btnEnregistrerInstrument = new javax.swing.JButton();
        btnActif = new javax.swing.JButton();
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
        barreMenu = new javax.swing.JMenuBar();
        menuFichier = new javax.swing.JMenu();
        miImporter = new javax.swing.JMenuItem();
        miImporterChanson = new javax.swing.JMenuItem();
        miEnregistrer = new javax.swing.JMenuItem();
        miEnregistrerSous = new javax.swing.JMenuItem();
        miQuitter = new javax.swing.JMenuItem();
        menuMode = new javax.swing.JMenu();
        miJouer = new javax.swing.JRadioButtonMenuItem();
        miEdition = new javax.swing.JRadioButtonMenuItem();
        miAjouterTouches = new javax.swing.JRadioButtonMenuItem();
        menuAide = new javax.swing.JMenu();
        miAPropos = new javax.swing.JMenuItem();
        miAide = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gaudrophone - Ajouter des touches");

        spGaudrophone.setDividerLocation(700);

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
            .addGap(0, 699, Short.MAX_VALUE)
        );
        panneauAffichageLayout.setVerticalGroup(
            panneauAffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 688, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout plNoteLayout = new javax.swing.GroupLayout(plNote);
        plNote.setLayout(plNoteLayout);
        plNoteLayout.setHorizontalGroup(
            plNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panneauAffichage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        plNoteLayout.setVerticalGroup(
            plNoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panneauAffichage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        spGaudrophone.setLeftComponent(plNote);

        lblNomInstrument.setText("Nom : ");

        txtNomInstrument.setText("NomInstrument");
        txtNomInstrument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomInstrumentActionPerformed(evt);
            }
        });

        lblTimbre.setText("Timbre :");

        cbTimbre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guitare", "Piano" }));

        lblMetronome.setText("Métronôme : ");

        lblNoteMetronome.setText("Note :");

        cbNoteMetronome.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" }));
        cbNoteMetronome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNoteMetronomeActionPerformed(evt);
            }
        });

        spinOctaveMetronome.setModel(new javax.swing.SpinnerNumberModel(5, 0, 9, 1));

        spinPersistanceMetronome.setModel(new javax.swing.SpinnerNumberModel());

        lblPersistanceMertronome.setText("Persistance (ms) :");

        lblOctaveMetronome.setText("Octave :");

        lblFrequenceMetronome.setText("Fréquence (ms) :");
        lblFrequenceMetronome.setToolTipText("");

        spinFrequence.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        lblTimbreMetronome.setText("Timbre :");

        cbTimbreMetronome.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guitare", "Piano" }));

        btnEnregistrerInstrument.setText("Enregistrer");
        btnEnregistrerInstrument.setToolTipText("");
        btnEnregistrerInstrument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerInstrumentActionPerformed(evt);
            }
        });

        btnActif.setText("Activer");

        javax.swing.GroupLayout plInstrumentLayout = new javax.swing.GroupLayout(plInstrument);
        plInstrument.setLayout(plInstrumentLayout);
        plInstrumentLayout.setHorizontalGroup(
            plInstrumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plInstrumentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plInstrumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plInstrumentLayout.createSequentialGroup()
                        .addGroup(plInstrumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTimbre)
                            .addComponent(lblNomInstrument))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(plInstrumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTimbre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNomInstrument)))
                    .addGroup(plInstrumentLayout.createSequentialGroup()
                        .addComponent(lblMetronome)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnEnregistrerInstrument, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(plInstrumentLayout.createSequentialGroup()
                        .addGroup(plInstrumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNoteMetronome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblOctaveMetronome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPersistanceMertronome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFrequenceMetronome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTimbreMetronome, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(plInstrumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTimbreMetronome, 0, 134, Short.MAX_VALUE)
                            .addComponent(spinFrequence)
                            .addComponent(spinPersistanceMetronome)
                            .addComponent(spinOctaveMetronome)
                            .addComponent(btnActif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbNoteMetronome, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        plInstrumentLayout.setVerticalGroup(
            plInstrumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plInstrumentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plInstrumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomInstrument)
                    .addComponent(txtNomInstrument, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(plInstrumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimbre)
                    .addComponent(cbTimbre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblMetronome)
                .addGap(18, 18, 18)
                .addGroup(plInstrumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plInstrumentLayout.createSequentialGroup()
                        .addComponent(lblNoteMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(lblOctaveMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblPersistanceMertronome, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(lblFrequenceMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(plInstrumentLayout.createSequentialGroup()
                        .addComponent(cbNoteMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(spinOctaveMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(spinPersistanceMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(spinFrequence, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(plInstrumentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTimbreMetronome)
                    .addComponent(cbTimbreMetronome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnActif)
                .addGap(18, 18, 18)
                .addComponent(btnEnregistrerInstrument)
                .addContainerGap())
        );

        lblTimbre.getAccessibleContext().setAccessibleDescription("");

        TPInfo.addTab("Instrument", plInstrument);

        lblNote.setText("Note : ");

        lblOctave.setText("Octave :");

        spinOctave.setModel(new javax.swing.SpinnerNumberModel(5, 0, 9, 1));

        lblForme.setText("Forme :");

        cbForme.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cercle", "Triangle", "Rectangle", "Pentagone", "Hexagone" }));

        lblFond.setText("Fond : ");

        btnParcourirImage.setText("Parcourir");
        btnParcourirImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParcourirImageActionPerformed(evt);
            }
        });

        cbCouleur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rouge", "Vert", "Bleu", "Brun", "Beige", "Jaune", "Blanc", "Noir" }));
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

        javax.swing.GroupLayout plToucheLayout = new javax.swing.GroupLayout(plTouche);
        plTouche.setLayout(plToucheLayout);
        plToucheLayout.setHorizontalGroup(
            plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plToucheLayout.createSequentialGroup()
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
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
                                    .addComponent(spinVert)
                                    .addComponent(spinRouge)
                                    .addComponent(spinBleu)))))
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFond)
                            .addComponent(lblBordure)
                            .addComponent(lblType)
                            .addComponent(lblDimension)
                            .addGroup(plToucheLayout.createSequentialGroup()
                                .addComponent(lblForme)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbForme, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(plToucheLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblLargeur)
                                    .addComponent(lblHauteur))
                                .addGap(18, 18, 18)
                                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spinHauteur)
                                    .addComponent(spinLargeur)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plToucheLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNote, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblOctave, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblPersistance, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(5, 5, 5)
                                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbNote, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(spinOctave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(spinPersistance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)))
                            .addComponent(rbFichierAudio)
                            .addGroup(plToucheLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(btnParcourirFichierAudio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(rbSon)))
                    .addGroup(plToucheLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnEnregistrerTouche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        plToucheLayout.setVerticalGroup(
            plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plToucheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblForme)
                    .addComponent(cbForme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
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
                .addGap(61, 61, 61)
                .addComponent(lblType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbSon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNote)
                    .addComponent(cbNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOctave)
                    .addComponent(spinOctave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(plToucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPersistance)
                    .addComponent(spinPersistance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbFichierAudio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnParcourirFichierAudio)
                .addGap(15, 15, 15)
                .addComponent(btnEnregistrerTouche)
                .addContainerGap())
        );

        TPInfo.addTab("Touche", plTouche);

        javax.swing.GroupLayout plParametresLayout = new javax.swing.GroupLayout(plParametres);
        plParametres.setLayout(plParametresLayout);
        plParametresLayout.setHorizontalGroup(
            plParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TPInfo)
        );
        plParametresLayout.setVerticalGroup(
            plParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TPInfo, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        spGaudrophone.setRightComponent(plParametres);

        menuFichier.setLabel("Fichier");

        miImporter.setText("Importer");
        miImporter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miImporterActionPerformed(evt);
            }
        });
        menuFichier.add(miImporter);

        miImporterChanson.setText("Importer chanson");
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
    }//GEN-LAST:event_miJouerActionPerformed

    private void miEditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEditionActionPerformed
        controleur.modifierModeVisuel(ModeVisuel.Editer);
    }//GEN-LAST:event_miEditionActionPerformed

    private void miAjouterTouchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAjouterTouchesActionPerformed
        controleur.modifierModeVisuel(ModeVisuel.Ajouter);
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
        int returnVal = fc.showOpenDialog(plInstrument);
    }//GEN-LAST:event_btnParcourirFichierAudioActionPerformed

    private void rbFichierAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFichierAudioActionPerformed
        btnParcourirFichierAudio.setEnabled(true);
        lblOctave.setEnabled(false);
        spinOctave.setEnabled(false);
        lblPersistance.setEnabled(false);
        spinPersistance.setEnabled(false);
        lblNote.setEnabled(false);
        cbNote.setEnabled(false);
    }//GEN-LAST:event_rbFichierAudioActionPerformed

    private void rbSonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSonActionPerformed
        btnParcourirFichierAudio.setEnabled(false);
        lblOctave.setEnabled(true);
        spinOctave.setEnabled(true);
        lblPersistance.setEnabled(true);
        spinPersistance.setEnabled(true);
        lblNote.setEnabled(true);
        cbNote.setEnabled(true);
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

    private void cbCouleurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCouleurActionPerformed
        String value = cbCouleur.getSelectedItem().toString();
        switch(value){
            case "Rouge":
            spinRouge.setValue(255);
            spinVert.setValue(0);
            spinBleu.setValue(0);
            break;
            case "Vert":
            spinRouge.setValue(0);
            spinVert.setValue(255);
            spinBleu.setValue(0);
            break;
            case "Bleu":
            spinRouge.setValue(0);
            spinVert.setValue(0);
            spinBleu.setValue(255);
            break;
            case "Brun":
            spinRouge.setValue(72);
            spinVert.setValue(52);
            spinBleu.setValue(32);
            break;
            case "Beige":
            spinRouge.setValue(245);
            spinVert.setValue(245);
            spinBleu.setValue(220);
            break;
            case "Jaune":
            spinRouge.setValue(255);
            spinVert.setValue(255);
            spinBleu.setValue(0);
            break;
            case "Blanc":
            spinRouge.setValue(255);
            spinVert.setValue(255);
            spinBleu.setValue(255);
            break;
            case "Noir":
            spinRouge.setValue(0);
            spinVert.setValue(0);
            spinBleu.setValue(0);
            break;
        }
    }//GEN-LAST:event_cbCouleurActionPerformed

    private void btnParcourirImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParcourirImageActionPerformed
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(plInstrument);
    }//GEN-LAST:event_btnParcourirImageActionPerformed

    private void btnEnregistrerInstrumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerInstrumentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnregistrerInstrumentActionPerformed

    private void cbNoteMetronomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNoteMetronomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNoteMetronomeActionPerformed

    private void txtNomInstrumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomInstrumentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomInstrumentActionPerformed

    private void panneauAffichageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panneauAffichageMouseClicked
        int dimension=(panneauAffichage.getWidth()>panneauAffichage.getHeight()?panneauAffichage.getHeight():panneauAffichage.getWidth());
        controleur.cliquerSouris(Outils.conversionPointPixelRelatif( evt.getPoint(),dimension));
        panneauAffichage.repaint();
    }//GEN-LAST:event_panneauAffichageMouseClicked

    private void panneauAffichageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panneauAffichageMousePressed
        int dimension=(panneauAffichage.getWidth()>panneauAffichage.getHeight()?panneauAffichage.getHeight():panneauAffichage.getWidth());
        controleur.enfoncerSouris(Outils.conversionPointPixelRelatif( evt.getPoint(),dimension));
        panneauAffichage.repaint();
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
   
    private void miEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {
        controleur.sauvegarderInstrument();
    }

    private void miEnregistrerSousActionPerformed(java.awt.event.ActionEvent evt) {
       controleur.sauvegarderSousInstrument();
    }

    private void miImporterActionPerformed(java.awt.event.ActionEvent evt) {
        controleur.importerInstrument();
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
        
        /*txtAide.setText(strTexte);
        txtAide.setCaretPosition(txtAide.getDocument().getLength());
        txtAide.setEditable(false);
        txtAide.setWrapStyleWord(true);
        txtAide.setLineWrap(true);
        txtAide.setVisible(true);
        btnOkAide.setVisible(true);
        scrlAide.setVisible(true);*/
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
    private javax.swing.JButton btnEnregistrerInstrument;
    private javax.swing.JButton btnEnregistrerTouche;
    private javax.swing.JButton btnParcourirFichierAudio;
    private javax.swing.JButton btnParcourirImage;
    private javax.swing.JComboBox<String> cbCouleur;
    private javax.swing.JComboBox<String> cbForme;
    private javax.swing.JComboBox<String> cbNote;
    private javax.swing.JComboBox<String> cbNoteMetronome;
    private javax.swing.JComboBox<String> cbTimbre;
    private javax.swing.JComboBox<String> cbTimbreMetronome;
    private javax.swing.JLabel lblBleu;
    private javax.swing.JLabel lblBordure;
    private javax.swing.JLabel lblDimension;
    private javax.swing.JLabel lblFond;
    private javax.swing.JLabel lblForme;
    private javax.swing.JLabel lblFrequenceMetronome;
    private javax.swing.JLabel lblHauteur;
    private javax.swing.JLabel lblLargeur;
    private javax.swing.JLabel lblMetronome;
    private javax.swing.JLabel lblNomInstrument;
    private javax.swing.JLabel lblNote;
    private javax.swing.JLabel lblNoteMetronome;
    private javax.swing.JLabel lblOctave;
    private javax.swing.JLabel lblOctaveMetronome;
    private javax.swing.JLabel lblPersistance;
    private javax.swing.JLabel lblPersistanceMertronome;
    private javax.swing.JLabel lblRouge;
    private javax.swing.JLabel lblTimbre;
    private javax.swing.JLabel lblTimbreMetronome;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel lblVert;
    private javax.swing.JMenu menuAide;
    private javax.swing.JMenu menuFichier;
    private javax.swing.JMenu menuMode;
    private javax.swing.JMenuItem miAPropos;
    private javax.swing.JMenuItem miAide;
    private javax.swing.JRadioButtonMenuItem miAjouterTouches;
    private javax.swing.JRadioButtonMenuItem miEdition;
    private javax.swing.JMenuItem miEnregistrer;
    private javax.swing.JMenuItem miEnregistrerSous;
    private javax.swing.JMenuItem miImporter;
    private javax.swing.JMenuItem miImporterChanson;
    private javax.swing.JRadioButtonMenuItem miJouer;
    private javax.swing.JMenuItem miQuitter;
    private gaudrophone.Presentation.PanneauAffichage panneauAffichage;
    private javax.swing.JPanel plInstrument;
    private javax.swing.JPanel plNote;
    private javax.swing.JPanel plParametres;
    private javax.swing.JPanel plTouche;
    private javax.swing.JRadioButton rbCouleur;
    private javax.swing.JRadioButton rbFichierAudio;
    private javax.swing.JRadioButton rbImage;
    private javax.swing.JRadioButton rbSon;
    private javax.swing.JSplitPane spGaudrophone;
    private javax.swing.JSpinner spinBleu;
    private javax.swing.JSpinner spinFrequence;
    private javax.swing.JSpinner spinHauteur;
    private javax.swing.JSpinner spinLargeur;
    private javax.swing.JSpinner spinOctave;
    private javax.swing.JSpinner spinOctaveMetronome;
    private javax.swing.JSpinner spinPersistance;
    private javax.swing.JSpinner spinPersistanceMetronome;
    private javax.swing.JSpinner spinRouge;
    private javax.swing.JSpinner spinVert;
    private javax.swing.JTextField txtNomInstrument;
    // End of variables declaration//GEN-END:variables
}
