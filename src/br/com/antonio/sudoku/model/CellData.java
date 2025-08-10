package br.com.antonio.sudoku.model;

public class CellData {
    private int row;
    private int col;
    private int value;
    private boolean isEditable;

    public CellData(int row, int col, int value, boolean isEditable) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.isEditable = isEditable;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    public boolean isEditable() {
        return isEditable;
    }
}
