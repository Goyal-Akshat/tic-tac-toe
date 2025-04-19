package org.example.game;

public class GameState {
    boolean isOver;
    String winner;

    public GameState(boolean isOver, String winner) {
        this.isOver = isOver;
        this.winner = winner;
    }

    public boolean isOver() {
        return isOver;
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        if (isOver) {
            return "Game Over! Winner: " + winner;
        } else {
            return "Game In Progress";
        }
    }
}