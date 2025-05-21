package org.example.api;

import org.example.boards.TicTacToeBoard;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;

public class AIEngine {

    public Move suggestMove(Player computer, Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;

            Move suggestion;
            if (isStarting(board1, 3)) {
                suggestion = (Move) getBasicMove(computer, board1);
            } else {
                suggestion = getSmartMove(computer, board1);
            }
            if (suggestion != null) {
                return suggestion;
            }
            throw new IllegalStateException();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Move getBasicMove(Player computer, TicTacToeBoard board1) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getSymbol(i, j) == null) {
                    return new Move(new Cell(i, j), computer);
                }
            }
        }

        return null;
    }

    private boolean isStarting(TicTacToeBoard board1, int threshold) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }
        return count < threshold;
    }

    private Move getSmartMove(Player player, TicTacToeBoard board) {
        RuleEngine ruleEngine = new RuleEngine();

        // Victory moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return move;
                    }
                }
            }
        }

        // Defensive moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
        }

        return getBasicMove(player, board);
    }
}
