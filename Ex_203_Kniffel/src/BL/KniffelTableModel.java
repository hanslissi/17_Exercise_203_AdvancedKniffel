/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author johannesriedmueller
 */
public class KniffelTableModel extends AbstractTableModel{
    private ArrayList<KniffelRow> rows = new ArrayList<>();
    private String[] colNames = {"Spiel", "Wahl", "Punkte"};
    
    {
        rows.add(new KniffelRow("einser"));
        rows.add(new KniffelRow("zeweier"));
        rows.add(new KniffelRow("dreier"));
        rows.add(new KniffelRow("vierer"));
        rows.add(new KniffelRow("fünfer"));
        rows.add(new KniffelRow("sechser"));
        rows.add(new KniffelRow("Pasch 3"));
        rows.add(new KniffelRow("Pasch 4"));
        rows.add(new KniffelRow("Full House"));
        rows.add(new KniffelRow("kleine Straße"));
        rows.add(new KniffelRow("große Straße"));
        rows.add(new KniffelRow("Kniffel"));
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    
    
    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows.get(rowIndex);
    }

    public KniffelRow getRowByIndex(int idx) {
        return rows.get(idx);
    }
    
    public void updateTable(int idx1, int idx2){
        fireTableRowsUpdated(idx1, idx2);
    }
}
