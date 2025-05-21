package org.example.boards;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.example.api.Rule;
import org.example.api.RuleSet;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.GameState;
import org.example.game.Move;

public class TicTacToeBoard implements Board {
    String cells[][] = new String[3][3];

    public static RuleSet<TicTacToeBoard> getRules() {
        RuleSet rules = new RuleSet();
        rules.add(new Rule<TicTacToeBoard>(board -> outerTraversal((i, j) -> board.getSymbol(i, j))));
        rules.add(new Rule<TicTacToeBoard>(board -> outerTraversal((i, j) -> board.getSymbol(j, i))));
        rules.add(new Rule<TicTacToeBoard>(board -> traverse(i -> board.getSymbol(i, i))));
        rules.add(new Rule<TicTacToeBoard>(board -> traverse(i -> board.getSymbol(i, 2 - i))));
        rules.add(new Rule<TicTacToeBoard>(board -> {
            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getSymbol(i, j) != null) {
                        countOfFilledCells++;
                    }
                }
            }
            if (countOfFilledCells == 9) {
                return new GameState(true, "-");
            }
            return new GameState(false, "-");
        }));
        return rules;
    }

    public String getSymbol(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        if (cells[cell.getRow()][cell.getCol()] == null) {
            cells[cell.getRow()][cell.getCol()] = symbol;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            final int ii = i;
            String test = next.apply(ii, 0);
            GameState traversal = traverse(j -> next.apply(ii, j));
            if (traversal.isOver()) {
                result = traversal;
                break;
            }
        }
        return result;
    }

    public static GameState traverse(Function<Integer, String> diag) {
        GameState result = new GameState(false, "-");
        boolean isStreak = true;
        for (int i = 0; i < 3; i++) {
            if (diag.apply(i) == null || !diag.apply(0).equals(diag.apply(i))) {
                isStreak = false;
                break;
            }
        }
        if (isStreak) {
            result = new GameState(true, diag.apply(0));
        }
        return result;
    }

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().symbol());
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

    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            System.arraycopy(cells[i], 0, ticTacToeBoard.cells[i], 0, 0);
        }
        return ticTacToeBoard;
    }
}