package br.com.antonio.sudoku.ui;

import br.com.antonio.sudoku.model.CellData;
import br.com.antonio.sudoku.model.SudokuGrid;
import br.com.antonio.sudoku.solver.SudokuSolver;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SudokuGUI extends JFrame {
    private SudokuGrid grid;
    private JTextField[][] cells;
    private JButton solveButton;
    private JButton clearButton;
    private JButton finishButton;

    public SudokuGUI() {
        this(createDefaultPuzzle());
    }

    public SudokuGUI(List<CellData> initialData) {
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

        if (initialData != null) {
            for (CellData data : initialData) {
                cells[data.getRow()][data.getCol()].setText(String.valueOf(data.getValue()));
                cells[data.getRow()][data.getCol()].setEditable(data.isEditable());
                grid.setCell(data.getRow(), data.getCol(), data.getValue());
            }
        }

        JPanel buttonPanel = new JPanel();
        solveButton = new JButton("Solve");
        clearButton = new JButton("Clear");
        finishButton = new JButton("Finish");
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(finishButton);

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

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finish();
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

    private void finish() {
        // Check if the grid is full
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (cells[i][j].getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please complete the puzzle before finishing.", "Incomplete Puzzle", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }

        // Check if the solution is correct
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = Integer.parseInt(cells[i][j].getText());
                grid.setCell(i, j, 0); // Temporarily set to 0 to check validity
                if (!grid.isValid(i, j, value)) {
                    JOptionPane.showMessageDialog(this, "The solution is incorrect.", "Incorrect Solution", JOptionPane.ERROR_MESSAGE);
                    grid.setCell(i, j, value); // Restore the value
                    return;
                }
                grid.setCell(i, j, value); // Restore the value
            }
        }

        JOptionPane.showMessageDialog(this, "Congratulations! You have solved the puzzle.", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
    }

    private static List<CellData> createDefaultPuzzle() {
        List<CellData> data = new ArrayList<>();
        data.add(new CellData(0, 0, 5, false));
        data.add(new CellData(0, 1, 3, false));
        data.add(new CellData(0, 4, 7, false));
        data.add(new CellData(1, 0, 6, false));
        data.add(new CellData(1, 3, 1, false));
        data.add(new CellData(1, 4, 9, false));
        data.add(new CellData(1, 5, 5, false));
        data.add(new CellData(2, 1, 9, false));
        data.add(new CellData(2, 2, 8, false));
        data.add(new CellData(2, 7, 6, false));
        data.add(new CellData(3, 0, 8, false));
        data.add(new CellData(3, 4, 6, false));
        data.add(new CellData(3, 8, 3, false));
        data.add(new CellData(4, 0, 4, false));
        data.add(new CellData(4, 3, 8, false));
        data.add(new CellData(4, 5, 3, false));
        data.add(new CellData(4, 8, 1, false));
        data.add(new CellData(5, 0, 7, false));
        data.add(new CellData(5, 4, 2, false));
        data.add(new CellData(5, 8, 6, false));
        data.add(new CellData(6, 1, 6, false));
        data.add(new CellData(6, 6, 2, false));
        data.add(new CellData(6, 7, 8, false));
        data.add(new CellData(7, 3, 4, false));
        data.add(new CellData(7, 4, 1, false));
        data.add(new CellData(7, 5, 9, false));
        data.add(new CellData(7, 8, 5, false));
        data.add(new CellData(8, 4, 8, false));
        data.add(new CellData(8, 7, 9, false));
        return data;
    }
}
