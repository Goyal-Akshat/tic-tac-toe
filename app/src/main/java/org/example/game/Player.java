package org.example.game;

import org.example.user.User;

public class Player {

    private int timeUsedInMillis;

    private User id;

    private String playerSymbol;

    public Player(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String symbol() {
        return playerSymbol;
    }

    public Player flip() {
        return new Player(playerSymbol == "X" ? "O" : "X");
    }

    public void setTimeTaken(int timeInMillis) {
        timeUsedInMillis += timeInMillis;
    }

    public int getTimeUsedInMillis() {
        return timeUsedInMillis;
    }
}
