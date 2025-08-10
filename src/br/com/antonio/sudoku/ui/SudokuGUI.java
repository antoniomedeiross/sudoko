package br.com.antonio.sudoku.ui;

import br.com.antonio.sudoku.model.SudokuGrid;
import br.com.antonio.sudoku.solver.SudokuSolver;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuGUI extends JFrame {
    private SudokuGrid grid;
    private JTextField[][] cells;
    private JButton solveButton;
    private JButton clearButton;

    public SudokuGUI() {
        grid = new SudokuGrid();
        cells = new JTextField[9][9];

        setTitle("Sudoku Solver");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                Border border;
                if (i % 3 == 2 && j % 3 == 2) {
                    border = BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK);
                } else if (i % 3 == 2) {
                    border = BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK);
                } else if (j % 3 == 2) {
                    border = BorderFactory.createMatteBorder(1, 1, 1, 3, Color.BLACK);
                } else {
                    border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
                }
                cells[i][j].setBorder(border);
                gridPanel.add(cells[i][j]);
            }
        }

        JPanel buttonPanel = new JPanel();
        solveButton = new JButton("Solve");
        clearButton = new JButton("Clear");
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solve();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
    }

    private void solve() {
        try {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    String text = cells[i][j].getText();
                    if (text.isEmpty()) {
                        grid.setCell(i, j, 0);
                    } else {
                        int value = Integer.parseInt(text);
                        if (value >= 1 && value <= 9) {
                            grid.setCell(i, j, value);
                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numbers between 1 and 9.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
            }

            SudokuSolver solver = new SudokuSolver(grid);
            if (solver.solve()) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        cells[i][j].setText(String.valueOf(grid.getCell(i, j)));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Unsolvable Sudoku.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numbers only.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clear() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setText("");
            }
        }
    }
}
