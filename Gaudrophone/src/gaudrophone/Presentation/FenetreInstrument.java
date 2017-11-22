package gaudrophone.Presentation;

import gaudrophone.Domaine.Enums.NomNote;
import gaudrophone.Domaine.ControleurInstrument;
import gaudrophone.Domaine.Enums.ModeVisuel;
import gaudrophone.Domaine.Outils;
import javax.swing.*;

public class FenetreInstrument extends javax.swing.JFrame {
    ButtonGroup m_btnGroupeMode;
    ControleurInstrument controleur;
    PanneauAffichage panneauAffichage;
    
    
    public FenetreInstrument() {
        initComponents();
        initializeRadioButton();
        
        controleur = new ControleurInstrument();
        panneauAffichage = new PanneauAffichage(this);
        controleur.modifierModeVisuel(ModeVisuel.Ajouter);
        
        txtAide.setVisible(false);
        btnOkAide.setVisible(false);
        scrlAide.setVisible(false);
    }
    
    public ControleurInstrument getControleur()
    {
        return controleur;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        scrlAide = new javax.swing.JScrollPane();
        txtAide = new javax.swing.JTextArea();
        btnOkAide = new javax.swing.JButton();
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

        jSplitPane1.setDividerLocation(700);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 699, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 657, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel1);

        txtAide.setColumns(20);
        txtAide.setRows(5);
        scrlAide.setViewportView(txtAide);

        btnOkAide.setText("Ok");
        btnOkAide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkAideActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrlAide, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
            .addComponent(btnOkAide, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(scrlAide, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOkAide))
        );

        jSplitPane1.setRightComponent(jPanel3);

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
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 986, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
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

    private void btnOkAideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkAideActionPerformed
        txtAide.setVisible(false);
        btnOkAide.setVisible(false);
        scrlAide.setVisible(false);
    }//GEN-LAST:event_btnOkAideActionPerformed

    private void miEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEnregistrerActionPerformed
        controleur.sauvegarderInstrument();
    }//GEN-LAST:event_miEnregistrerActionPerformed

    private void miEnregistrerSousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEnregistrerSousActionPerformed
       controleur.sauvegarderSousInstrument();
    }//GEN-LAST:event_miEnregistrerSousActionPerformed

    private void miImporterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miImporterActionPerformed
        controleur.importerInstrument();
    }//GEN-LAST:event_miImporterActionPerformed

    private void miQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_miQuitterActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        controleur.cliquerSouris(evt.getPoint());
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        controleur.enfoncerSouris(evt.getPoint());
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
        controleur.relacherSouris(evt.getPoint());
    }//GEN-LAST:event_jPanel1MouseReleased
   
    private void afficherAider(String filename, String... optionalFilenames)
    {
        Outils tool = new Outils();
        
        String strTexte = tool.readFile(filename,"");
        
        for(int i=0;i<optionalFilenames.length;++i)
        {
            strTexte = tool.readFile(optionalFilenames[i],strTexte);
        }
        
        txtAide.setText(strTexte);
        txtAide.setCaretPosition(txtAide.getDocument().getLength());
        txtAide.setEditable(false);
        txtAide.setWrapStyleWord(true);
        txtAide.setLineWrap(true);
        txtAide.setVisible(true);
        btnOkAide.setVisible(true);
        scrlAide.setVisible(true);
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
    private javax.swing.JMenuBar barreMenu;
    private javax.swing.JButton btnOkAide;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSplitPane jSplitPane1;
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
    private javax.swing.JScrollPane scrlAide;
    private javax.swing.JTextArea txtAide;
    // End of variables declaration//GEN-END:variables
}
