package com.example.fierydragons.models;

/**
 * Game class manages the state and the number of players in the game.
 * @author: Taken from Jaden's Sprint 2
 */
public class Game {

    private int playerCount; // Number of players participating in the game.

    /**
     * Constructor initialises the game.
     */
    public Game() {
    }

    /**
     * Gets the number of players participating in the game.
     * @return The number of players
     */
    public int getPlayerCount() {
        return playerCount;
    }

    /**
     * Sets the number of players participating in the game.
     * @param playerCount The number of players to set
     */
    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
}
