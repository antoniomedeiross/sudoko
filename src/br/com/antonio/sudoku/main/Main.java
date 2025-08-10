package br.com.antonio.sudoku.main;

import br.com.antonio.sudoku.model.CellData;
import br.com.antonio.sudoku.ui.SudokuGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            List<CellData> initialData = parseArgs(args[0]);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new SudokuGUI(initialData).setVisible(true);
                }
            });
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new SudokuGUI().setVisible(true);
                }
            });
        }
    }

    private static List<CellData> parseArgs(String arg) {
        List<CellData> data = new ArrayList<>();
        String[] tokens = arg.split(" ");
        for (String token : tokens) {
            String[] parts = token.split(";");
            String[] coords = parts[0].split(",");
            String[] valueAndEditable = parts[1].split(",");
            int row = Integer.parseInt(coords[0]);
            int col = Integer.parseInt(coords[1]);
            int value = Integer.parseInt(valueAndEditable[0]);
            boolean isEditable = Boolean.parseBoolean(valueAndEditable[1]);
            data.add(new CellData(row, col, value, isEditable));
        }
        return data;
    }
}
