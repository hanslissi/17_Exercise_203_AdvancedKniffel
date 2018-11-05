/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author johannesriedmueller
 */
public class DiceTableRenderer implements TableCellRenderer{
    private ArrayList<ImageIcon> dices = new ArrayList<>();
    
    {
        dices.add(new ImageIcon("./dice/Alea_1.png"));
        dices.add(new ImageIcon("./dice/Alea_2.png"));
        dices.add(new ImageIcon("./dice/Alea_3.png"));
        dices.add(new ImageIcon("./dice/Alea_4.png"));
        dices.add(new ImageIcon("./dice/Alea_5.png"));
        dices.add(new ImageIcon("./dice/Alea_6.png"));
        
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int diceValue = 0;
        if (value instanceof Integer) {
            diceValue = ((int) value)-1;
        }
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setIcon(dices.get(diceValue));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        if (isSelected) {
            label.setBackground(Color.red);
        }
        return label;
    }
    
}
