package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.example.api.GameEngine;
import org.example.api.RuleEngine;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;

public class AppTest {
    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup() {
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 } };
        int secondPlayerMoves[][] = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 } };
        playGame(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");

    }

    @Test
    public void checkForColumnWin() {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 } };
        int secondPlayerMoves[][] = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 1 } };
        playGame(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");

    }

    @Test
    public void checkForDiagonalWin() {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][] { { 0, 0 }, { 1, 1 }, { 2, 2 } };
        int secondPlayerMoves[][] = new int[][] { { 0,1 }, { 0,2 }, { 1,0 } };
        playGame(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");

    }

    @Test
    public void checkForReverseDiagonalWin() {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][] { { 0, 2 }, { 1, 1 }, { 2, 0 } };
        int secondPlayerMoves[][] = new int[][] { { 0,0 }, { 0, 1 }, { 1, 2 } };
        playGame(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");

    }

    @Test
    public void checkForComputerWin() {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][] { { 0, 2 }, { 0, 1 }, { 2, 0 } };
        int secondPlayerMoves[][] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 } };
        playGame(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "O");

    }

    public void playGame(Board board, int firstPlayerMoves[][], int secondPlayerMoves[][]) {
        int row, col;
        int next = 0;
        while (!ruleEngine.getState(board).isOver()) {
            System.out.println("Make Your Move!");
            Player first = new Player("X"), second = new Player("O");
            row = firstPlayerMoves[next][0];
            col = firstPlayerMoves[next][1];
            Move firstPlayerMove = new Move(new Cell(row, col), first);
            gameEngine.move(board, firstPlayerMove);
            System.out.println("Doint testing");
            System.out.println(board);
            if (!ruleEngine.getState(board).isOver()) {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                Move secondPlayerMove = new Move(new Cell(sRow, sCol), second);
                gameEngine.move(board, secondPlayerMove);
            }
            next++;        
        }
    }

}