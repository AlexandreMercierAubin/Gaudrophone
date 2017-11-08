package gaudrophone.Presentation;

import gaudrophone.Domaine.ControleurInstrument;
import gaudrophone.Domaine.Instrument.Instrument;
import java.awt.geom.Point2D;

public class FenetreTestDessin extends javax.swing.JFrame {
    private ControleurInstrument controleur;

    public FenetreTestDessin() {
        initComponents();
        
        FenetreInstrument fenetreInstrument = new FenetreInstrument();
        controleur = fenetreInstrument.getControleur();
        
        panneauAffichage1.setFenetreInstrument(fenetreInstrument);
        ajouterTouches();
        
        panneauAffichage1.invalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panneauAffichage1 = new gaudrophone.Presentation.PanneauAffichage();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panneauAffichage1Layout = new javax.swing.GroupLayout(panneauAffichage1);
        panneauAffichage1.setLayout(panneauAffichage1Layout);
        panneauAffichage1Layout.setHorizontalGroup(
            panneauAffichage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panneauAffichage1Layout.setVerticalGroup(
            panneauAffichage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panneauAffichage1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panneauAffichage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FenetreTestDessin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreTestDessin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreTestDessin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreTestDessin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetreTestDessin().setVisible(true);
            }
        });
    }
    
    void ajouterTouches()
    {
        Instrument instrument = controleur.getInstrument();
        
        instrument.ajouterTouche(new Point2D.Double(0.2, 0.5));
        instrument.ajouterTouche(new Point2D.Double(0.8, 0.1));
        instrument.ajouterTouche(new Point2D.Double(0.75, 0.75));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gaudrophone.Presentation.PanneauAffichage panneauAffichage1;
    // End of variables declaration//GEN-END:variables
}
