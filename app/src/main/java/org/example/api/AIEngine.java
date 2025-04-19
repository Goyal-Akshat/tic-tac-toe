package org.example.api;

import org.example.boards.TicTacToeBoard;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;

public class AIEngine {

    public Move suggestMove(Player player, Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getCell(i, j) == null) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
