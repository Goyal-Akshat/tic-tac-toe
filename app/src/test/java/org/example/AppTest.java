package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.example.api.AIEngine;
import org.example.api.GameEngine;
import org.example.api.RuleEngine;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;

public class AppTest {
    GameEngine gameEngine;
    AIEngine aiEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup() {
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start("TicTacToe");
        int row, col;
        int next = 0;
        int moves[][] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 } };
        while (!ruleEngine.getState(board).isOver()) {
            System.out.println("Make Your Move!");
            Player computer = new Player("O"), human = new Player("X");
            row = moves[next][0];
            col = moves[next][1];
            next++;
            Move oppMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, oppMove);
            System.out.println(board);
            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
            System.out.println(board);
        }
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");

    }

    @Test
    public void checkForColumnWin() {
        Board board = gameEngine.start("TicTacToe");
        int row, col;
        int next = 0;
        int moves[][] = new int[][] { { 0, 0 }, { 1,0 }, { 2,0 } };
        
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");

    }

    function(){
        while (!ruleEngine.getState(board).isOver()) {
            System.out.println("Make Your Move!");
            Player computer = new Player("O"), human = new Player("X");
            row = moves[next][0];
            col = moves[next][1];
            next++;
            Move oppMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, oppMove);
            System.out.println(board);
            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
            System.out.println(board);
        }
    }

}