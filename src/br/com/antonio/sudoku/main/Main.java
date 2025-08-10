package br.com.antonio.sudoku.main;

import br.com.antonio.sudoku.ui.SudokuGUI;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuGUI().setVisible(true);
            }
        });
    }
}
