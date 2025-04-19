package org.example.boards;

import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;

public class TicTacToeBoard extends Board {
    String cells[][] = new String[3][3];

    public String getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        cells[cell.getRow()][cell.getCol()] = symbol;
    }

    @Override
    public void move(Move move){
        setCell(move.getCell(),move.getPlayer().symbol());
    }

    @Override
    public String toString() {
        // Printing a tic tac toe board very nicely on console
        String board = "+-----+-----+-----+\n";
        for (int i = 0; i < 3; i++) {
            board += "|  " + (cells[i][0] != null ? cells[i][0] : "-") + "  |  "
                    + (cells[i][1] != null ? cells[i][1] : "-") + "  |  " + (cells[i][2] != null ? cells[i][2] : "-")
                    + "  |\n";
            board += "+-----+-----+-----+\n";
        }
        return board;
    }
}