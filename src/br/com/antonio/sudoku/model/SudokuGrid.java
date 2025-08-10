package br.com.antonio.sudoku.model;

public class SudokuGrid {
    private int[][] grid;

    public SudokuGrid() {
        grid = new int[9][9];
    }

    public int getCell(int row, int col) {
        return grid[row][col];
    }

    public void setCell(int row, int col, int value) {
        grid[row][col] = value;
    }

    public boolean isValid(int row, int col, int value) {
        // Check row
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == value) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] == value) {
                return false;
            }
        }

        // Check 3x3 box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (grid[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }
}
