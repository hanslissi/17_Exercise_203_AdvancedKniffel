/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author johannesriedmueller
 */
public class DiceTableModel extends AbstractTableModel {

    private int[] diceNumbers = {1, 2, 3, 4, 5};

    public void scramble(int[] numsNotToScramble) {
        Random r = new Random();
        int notScrambleCounter = 0;
        for (int i = 0; i < diceNumbers.length; i++) {
            if (!(i == numsNotToScramble[notScrambleCounter])) {
                diceNumbers[i] = 1 + r.nextInt(5);
            } else if (notScrambleCounter != numsNotToScramble.length - 1) {
                notScrambleCounter++;
            }
        }
        fireTableRowsUpdated(0, diceNumbers.length - 1);
    }

    public void scramble() {
        Random r = new Random();
        for (int i = 0; i < diceNumbers.length; i++) {
            diceNumbers[i] = 1 + r.nextInt(5);
        }
        fireTableRowsUpdated(0, diceNumbers.length - 1);
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return diceNumbers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return diceNumbers[columnIndex];
    }

    public int[] getDiceNumbers() {
        return diceNumbers;
    }
    
}
