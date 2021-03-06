/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BL.DiceTableRenderer;
import BL.KniffelBL;
import BL.KniffelTableRenderer;
import javax.swing.JOptionPane;

/**
 *
 * @author johannesriedmueller
 */
public class KniffelGUI extends javax.swing.JFrame {

    private KniffelTableRenderer kniffelRenderer = new KniffelTableRenderer();
    private KniffelBL bl = new KniffelBL();
    private DiceTableRenderer diceRenderer = new DiceTableRenderer();
    private boolean firstTurnDone = false;

    public KniffelGUI() {
        initComponents();
        taDice.setCellSelectionEnabled(false);
        taGewinn.setModel(bl.getKniffelModel());
        taGewinn.setDefaultRenderer(Object.class, kniffelRenderer);
        taDice.setModel(bl.getDiceModel());
        taDice.setDefaultRenderer(Object.class, diceRenderer);
        taGewinn.setEnabled(firstTurnDone);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDice = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btTurn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfTopSum = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfTopBonus = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfBottomSum = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfSum = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taGewinn = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(328, 60));
        jPanel1.setLayout(new java.awt.BorderLayout());

        taDice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        taDice.setRowHeight(50);
        taDice.setTableHeader(null);
        jScrollPane1.setViewportView(taDice);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        btTurn.setText("Würfeln (3 übrig)");
        btTurn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTurnActionPerformed(evt);
            }
        });
        jPanel2.add(btTurn, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Punkte"));
        jPanel3.setPreferredSize(new java.awt.Dimension(328, 200));
        jPanel3.setLayout(new java.awt.GridLayout(4, 2));

        jLabel1.setText("Obere Summe:");
        jPanel3.add(jLabel1);

        tfTopSum.setEditable(false);
        tfTopSum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfTopSum.setText("0");
        jPanel3.add(tfTopSum);

        jLabel2.setText("Oberer Bunus:");
        jPanel3.add(jLabel2);

        tfTopBonus.setEditable(false);
        tfTopBonus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfTopBonus.setText("0");
        jPanel3.add(tfTopBonus);

        jLabel3.setText("Untere Summe:");
        jPanel3.add(jLabel3);

        tfBottomSum.setEditable(false);
        tfBottomSum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfBottomSum.setText("0");
        jPanel3.add(tfBottomSum);

        jLabel4.setText("Gesamt-Punkte:");
        jPanel3.add(jLabel4);

        tfSum.setEditable(false);
        tfSum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfSum.setText("0");
        jPanel3.add(tfSum);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Gewinnkarte"));
        jPanel4.setLayout(new java.awt.BorderLayout());

        taGewinn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        taGewinn.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        taGewinn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                taGewinnMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(taGewinn);

        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updatePoints() {
        tfTopSum.setText(Integer.toString(bl.getSumOfTop()));
        tfTopBonus.setText(Integer.toString(bl.getTopBonus()));
        tfBottomSum.setText(Integer.toString(bl.getSumOfBottom()));
        tfSum.setText(Integer.toString(bl.getSumOfAll()));
    }

    private void btTurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTurnActionPerformed
        firstTurnDone = true;
        taGewinn.setEnabled(firstTurnDone);
        if (!taDice.getCellSelectionEnabled()) {
            taDice.setCellSelectionEnabled(true);
        }
        if (bl.getTurns() < 3 && taDice.getSelectedColumnCount() > 0) {
            bl.scrambleNotSelectedDice(taDice.getSelectedColumns());
        } else if (bl.getTurns() < 3 && taDice.getSelectedColumnCount() == 0) {
            bl.scrambleAllDice();
        } else if (bl.getTurns() == 3) {
            bl.scrambleAllDice();
        }
        bl.setTurns(bl.getTurns() - 1);
        if (bl.getTurns() < 1) {
            btTurn.setEnabled(false);
        }
        btTurn.setText("Würfeln (" + bl.getTurns() + " übrig)");

    }//GEN-LAST:event_btTurnActionPerformed

    private void taGewinnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taGewinnMousePressed
        if (firstTurnDone) {
            if (taGewinn.getSelectedRow() != -1) {
                try{
                bl.checkSelection(taGewinn.getSelectedRow());
                bl.setTurns(3);
                btTurn.setEnabled(true);
                btTurn.setText("Würfeln (" + bl.getTurns() + " übrig)");
                taDice.clearSelection();
                updatePoints();
                firstTurnDone = false;
                taGewinn.setEnabled(firstTurnDone);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "You have to click the 'Würfeln' button first.");
        }
    }//GEN-LAST:event_taGewinnMousePressed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(KniffelGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KniffelGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KniffelGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KniffelGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KniffelGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btTurn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable taDice;
    private javax.swing.JTable taGewinn;
    private javax.swing.JTextField tfBottomSum;
    private javax.swing.JTextField tfSum;
    private javax.swing.JTextField tfTopBonus;
    private javax.swing.JTextField tfTopSum;
    // End of variables declaration//GEN-END:variables

}
