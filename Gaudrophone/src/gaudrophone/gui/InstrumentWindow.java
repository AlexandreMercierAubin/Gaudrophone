package gaudrophone.gui;
import gaudrophone.Toolbox;
import javax.swing.*;

public class InstrumentWindow extends javax.swing.JFrame {

    private enum enumMode
    {
        Play,
        AddKeys,
        Edition
    }
    
    ButtonGroup m_btnGroupMode;
    enumMode m_mode;
    
    public InstrumentWindow() {
        initComponents();
        initializeRadioButton();
        setModeAddKeys();
        txtAide.setVisible(false);
        btnOkAide.setVisible(false);
        scrlAide.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jSplitPane1.setDividerLocation(700);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 699, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
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
        menuFichier.add(miImporter);

        miImporterChanson.setText("Importer chanson");
        menuFichier.add(miImporterChanson);

        miEnregistrer.setText("Enregistrer");
        menuFichier.add(miEnregistrer);

        miEnregistrerSous.setText("Enregistrer sous");
        menuFichier.add(miEnregistrerSous);

        miQuitter.setText("Quitter");
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
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miJouerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miJouerActionPerformed
        setModePlay();
    }//GEN-LAST:event_miJouerActionPerformed

    private void miEditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEditionActionPerformed
        setModeEdition();
    }//GEN-LAST:event_miEditionActionPerformed

    private void miAjouterTouchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAjouterTouchesActionPerformed
        setModeAddKeys();
    }//GEN-LAST:event_miAjouterTouchesActionPerformed

    private void miAideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAideActionPerformed
        helpMenu("Aide","\\base.txt","\\aide.txt");
    }//GEN-LAST:event_miAideActionPerformed

    private void miAProposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAProposActionPerformed
        helpMenu("À propos","\\base.txt","\\apropos.txt");
    }//GEN-LAST:event_miAProposActionPerformed

    private void btnOkAideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkAideActionPerformed
        txtAide.setVisible(false);
        btnOkAide.setVisible(false);
        scrlAide.setVisible(false);
    }//GEN-LAST:event_btnOkAideActionPerformed
   
    private void helpMenu(String filename, String... optionalFilenames)
    {
        Toolbox tool = new Toolbox();
        
        String strText = tool.readFile(filename,"");
        
        for(int i=0;i<optionalFilenames.length;++i)
        {
            strText = tool.readFile(optionalFilenames[i],strText);
        }
        
        txtAide.setText(strText);
        txtAide.setCaretPosition(txtAide.getDocument().getLength());
        txtAide.setEditable(false);
        txtAide.setWrapStyleWord(true);
        txtAide.setLineWrap(true);
        txtAide.setVisible(true);
        btnOkAide.setVisible(true);
        scrlAide.setVisible(true);
    }
    
    private void setModePlay()
    {
        m_mode=enumMode.Play;
        this.setTitle("Gaudrophone - Jouer");
    }
    
    private void setModeAddKeys()
    {
        m_mode=enumMode.AddKeys;
        this.setTitle("Gaudrophone - Ajouter des touches");
    }
    
    private void setModeEdition()
    {
        m_mode=enumMode.Edition;
        this.setTitle("Gaudrophone - Édition");
    }
    
    private void initializeRadioButton()
    {
        m_btnGroupMode= new ButtonGroup();
        m_btnGroupMode.add(miAjouterTouches);
        m_btnGroupMode.add(miEdition);
        m_btnGroupMode.add(miJouer);
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
            java.util.logging.Logger.getLogger(InstrumentWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InstrumentWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InstrumentWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InstrumentWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InstrumentWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barreMenu;
    private javax.swing.JButton btnOkAide;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
