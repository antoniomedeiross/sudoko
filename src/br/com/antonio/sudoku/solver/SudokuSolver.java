package br.com.antonio.sudoku.solver;

import br.com.antonio.sudoku.model.SudokuGrid;

public class SudokuSolver {
    private SudokuGrid grid;

    public SudokuSolver(SudokuGrid grid) {
        this.grid = grid;
    }

    public boolean solve() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid.getCell(row, col) == 0) {
                    for (int value = 1; value <= 9; value++) {
                        if (grid.isValid(row, col, value)) {
                            grid.setCell(row, col, value);
                            if (solve()) {
                                return true;
                            } else {
                                grid.setCell(row, col, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
