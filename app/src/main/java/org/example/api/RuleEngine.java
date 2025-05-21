package org.example.api;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.example.boards.TicTacToeBoard;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.GameInfo;
import org.example.game.GameInfoBuilder;
import org.example.game.GameState;
import org.example.game.Move;
import org.example.game.Player;

public class RuleEngine {

    Map<String, RuleSet> ruleMap = new HashMap<>();

    public GameInfo getGameInfo(Board board) {
        if (board instanceof TicTacToeBoard) {

            GameState gameState = getState(board);

            final String[] players = new String[] { "X", "O" };
            for (int index = 0; index < 2; index++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Board b = board.copy();
                        Player player = new Player(players[index]);
                        b.move(new Move(new Cell(i, j), new Player("X")));
                        boolean canStillWin = false;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                Board b1 = b.copy();
                                b1.move(new Move(new Cell(k, l), player.flip()));
                                if (!getState(b1).getWinner().equals(player.flip().symbol())) {
                                    canStillWin = false;
                                    break;
                                }
                            }
                            if (!canStillWin) {
                                break;
                            }
                        }
                        if (!canStillWin) {
                            new GameInfoBuilder()
                                    .isOver(canStillWin)
                                    .winner(gameState.getWinner())
                                    .hasFork(false)
                                    .player(player.flip()).build();
                        }
                    }
                }
            }
            return new GameInfoBuilder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .hasFork(false)
                    .build();
        } else {
            throw new IllegalArgumentException();
        }

    }

    public RuleEngine() {
        ruleMap.put(TicTacToeBoard.class.getName(), TicTacToeBoard.getRules());
    }

    public GameState getState(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard b = (TicTacToeBoard) board;
            for (Rule<TicTacToeBoard> r : ruleMap.get(TicTacToeBoard.class.getName())) {
                GameState gameState = r.condition.apply(b);
                if (gameState.isOver()) {
                    return gameState;
                }
            }
            return new GameState(false, "-");
        } else {
            throw new IllegalArgumentException();
        }
    }

}
