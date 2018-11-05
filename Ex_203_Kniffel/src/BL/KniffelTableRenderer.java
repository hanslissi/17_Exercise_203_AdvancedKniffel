/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author johannesriedmueller
 */
public class KniffelTableRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        KniffelRow kr = (KniffelRow) value;
        
        try{
        switch (column) {
            case 0:
                JLabel label1 = new JLabel();
                label1.setOpaque(true);
                label1.setText(kr.getText());
                return label1;
            case 1:
                JCheckBox cb = kr.getChecked();
                return cb;

            case 2:
                JLabel label2 = new JLabel();
                label2.setOpaque(true);
                label2.setText(Integer.toString(kr.getPoints()));
                return label2;
            default:
                return new JLabel();
        }
        }
        catch(NullPointerException npe){
            //just to catch
        }
        return new JLabel();
    }

}
