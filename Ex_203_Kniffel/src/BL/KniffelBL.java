/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

public class KniffelBL {

    private DiceTableModel diceModel = new DiceTableModel();
    private KniffelTableModel kniffelModel = new KniffelTableModel();
    private int[] allDiceNumbers = new int[5];
    private int sumOfTop;
    private int topBonus;
    private int sumOfBottom;
    private int sumOfAll;
    private int turns;
    private final int lengthOfDiceArray;

    public KniffelBL() {
        sumOfAll = 0;
        sumOfBottom = 0;
        sumOfTop = 0;
        topBonus = 0;
        turns = 3;
        lengthOfDiceArray = 5;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public DiceTableModel getDiceModel() {
        return diceModel;
    }

    public KniffelTableModel getKniffelModel() {
        return kniffelModel;
    }

    public void calculatePoints(int index) {
        updateAllDiceNumbers();
        if (index >= 0 && index <= 5) {
            int countByValue = countValueByNum(index + 1);
            sumOfTop += countByValue;
            kniffelModel.getRowByIndex(index).setPoints(countByValue);
            kniffelModel.getRowByIndex(index).getChecked().setSelected(true);
        }

        switch (index) {
            case 6:
                int countPasch3Value = countPasch(3);
                sumOfBottom += countPasch3Value;
                kniffelModel.getRowByIndex(index).setPoints(countPasch3Value);
                kniffelModel.getRowByIndex(index).getChecked().setSelected(true);
                break;
            case 7:
                int countPasch4Value = countPasch(4);
                sumOfBottom += countPasch4Value;
                kniffelModel.getRowByIndex(index).setPoints(countPasch4Value);
                kniffelModel.getRowByIndex(index).getChecked().setSelected(true);
                break;
            case 8:
                if (checkIfFullhouse()) {
                    sumOfBottom += 25;
                    kniffelModel.getRowByIndex(index).setPoints(25);
                }
                kniffelModel.getRowByIndex(index).getChecked().setSelected(true);
                break;
            case 9:
                if (checkIfStreet(4)) {
                    sumOfBottom += 30;
                    kniffelModel.getRowByIndex(index).setPoints(30);
                }
                kniffelModel.getRowByIndex(index).getChecked().setSelected(true);
                break;
            case 10:
                if (checkIfStreet(5)) {
                    sumOfBottom += 40;
                    kniffelModel.getRowByIndex(index).setPoints(40);
                }
                kniffelModel.getRowByIndex(index).getChecked().setSelected(true);
                break;
            case 11:
                if (checkIfKniffel()) {
                    sumOfBottom += 50;
                    kniffelModel.getRowByIndex(index).setPoints(50);
                }
                kniffelModel.getRowByIndex(index).getChecked().setSelected(true);
                break;

        }
        updateAllSumsOfGame();
        kniffelModel.updateTable(index, index);
    }

    public void checkSelection(int index)throws Exception{
        if(kniffelModel.getRowByIndex(index).getChecked().isSelected()){
            throw new Exception("Already checked!");
        }
        else{
            calculatePoints(index);
        }
    }
    
    private void updateAllSumsOfGame() {
        if (sumOfTop >= 63) {
            topBonus = 35;
        }
        sumOfAll = sumOfTop + topBonus + sumOfBottom;
    }

    private int countValueByNum(int num) {
        int value = 0;
        for (int i = 0; i <= 4; i++) {
            if (allDiceNumbers[i] == num) {
                value += allDiceNumbers[i];
            }
        }
        return value;
    }

    private int countPasch(int whichPasch) {
        sort(0, lengthOfDiceArray - 1);
        //first index of this array is the number which has the most appearance of all, the second index indicates how often this number appears
        int[] mostAppearance = {0, 0};
        int[] tempMostAppearance = {0, 0};
        for (int i = 0; i < lengthOfDiceArray; i++) {
            tempMostAppearance[0] = allDiceNumbers[i];
            tempMostAppearance[1] = 1;
            if (i + 1 < 5) {
                int j = i + 1;
                while (allDiceNumbers[j] == tempMostAppearance[0]) {
                    tempMostAppearance[1] += 1;
                    j++;
                    i++;
                    if (j > lengthOfDiceArray - 1) {
                        break;
                    }
                }
            }
            if (tempMostAppearance[1] > mostAppearance[1]) {
                mostAppearance[0] = tempMostAppearance[0];
                mostAppearance[1] = tempMostAppearance[1];
            }
        }
        if (mostAppearance[1] >= whichPasch) {
            return countSumOfAllDice();
        } else {
            return 0;
        }
    }

    private void updateAllDiceNumbers() {
        int i = 0;
        for (int diceNumber : diceModel.getDiceNumbers()) {
            allDiceNumbers[i] = diceNumber;
            i++;
        }
    }

    private boolean checkIfFullhouse() {
        sort(0, lengthOfDiceArray - 1);
        int[] tempMostAppearance = {0, 0};
        //true if first number has 2 or 3 appearances in the array.
        boolean firstNumberRight = false;
        for (int i = 0; i < lengthOfDiceArray; i++) {
            tempMostAppearance[0] = allDiceNumbers[i];
            tempMostAppearance[1] = 1;
            if (i + 1 < 5) {
                int j = i + 1;
                while (allDiceNumbers[j] == tempMostAppearance[0]) {
                    tempMostAppearance[1] += 1;
                    j++;
                    i++;
                    if (j > lengthOfDiceArray - 1) {
                        break;
                    }
                }
            }
            if (tempMostAppearance[1] >= 2 && !firstNumberRight) {
                firstNumberRight = true;
            } else if (firstNumberRight && tempMostAppearance[1] >= 2) {
                return true;
            }
        }
        return false;
    }

    public int countSumOfAllDice() {
        int sum = 0;
        for (int i = 0; i < lengthOfDiceArray; i++) {
            sum += diceModel.getDiceNumbers()[i];
        }
        return sum;
    }

    private void sort(int li, int re) {
        if (li > re) {
            return;
        }
        int pivot = allDiceNumbers[li];
        int i = li;
        int j = re;

        while (i < j) {
            while (allDiceNumbers[j] >= pivot && i < j) {
                j--;
            }
            allDiceNumbers[i] = allDiceNumbers[j];
            while (allDiceNumbers[i] <= pivot && i < j) {
                i++;
            }
            allDiceNumbers[j] = allDiceNumbers[i];
        }
        allDiceNumbers[j] = pivot;
        sort(li, j - 1);
        sort(j + 1, re);
    }

    private boolean checkIfStreet(int streetLength) {

        sort(0, lengthOfDiceArray - 1);
        int currentNum = allDiceNumbers[0];
        int streetCount = 1;
        for (int i = 1; i < lengthOfDiceArray; i++) {
            if (allDiceNumbers[i] == (currentNum + 1)) {
                streetCount++;
                currentNum++;
            } else if (allDiceNumbers[i] == currentNum) {

            } else if (streetCount < streetLength) {
                streetCount = 1;
            }

        }
        if (streetCount >= streetLength) {
            return true;
        }
        return false;
    }

    private boolean checkIfKniffel() {

        boolean isKniffel = false;
        int kniffelNum = allDiceNumbers[0];
        int i = 1;
        while (kniffelNum == allDiceNumbers[i]) {
            i++;
            if (i > lengthOfDiceArray - 1) {
                isKniffel = true;
                break;
            }
        }
        return isKniffel;
    }

    public void scrambleNotSelectedDice(int[] numsNotToScramble) {
        diceModel.scramble(numsNotToScramble);
    }

    public void scrambleAllDice() {
        diceModel.scramble();
    }

    public int getSumOfTop() {
        return sumOfTop;
    }

    public int getTopBonus() {
        return topBonus;
    }

    public int getSumOfBottom() {
        return sumOfBottom;
    }

    public int getSumOfAll() {
        return sumOfAll;
    }

}
