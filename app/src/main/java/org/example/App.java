package org.example;

import java.util.Scanner;
import org.example.api.AIEngine;
import org.example.api.GameEngine;
import org.example.api.RuleEngine;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;

public class App {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacToe");
        AIEngine aiPlayer = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Scanner scanner = new Scanner(System.in);
        // mak,e moves
        while (!ruleEngine.getState(board).isOver()) {
            System.out.println("Make Your Move!");
            Player computer = new Player("O"), human = new Player("X");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Move oppMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, oppMove);
            System.out.println(board);
            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiPlayer.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
            System.out.println(board);
        }
        System.out.println("GameResult: " + ruleEngine.getState(board));
    }
}
