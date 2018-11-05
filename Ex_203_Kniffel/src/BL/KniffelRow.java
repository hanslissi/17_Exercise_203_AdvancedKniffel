/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import javax.swing.JCheckBox;

/**
 *
 * @author johannesriedmueller
 */
public class KniffelRow {
    private String text;
    private JCheckBox checked;
    private int points;

    public KniffelRow(String text) {
        this.text = text;
        this.checked = new JCheckBox();
        this.points = 0;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getText() {
        return text;
    }

    public JCheckBox getChecked() {
        return checked;
    }

    public int getPoints() {
        return points;
    }
    
    
}
