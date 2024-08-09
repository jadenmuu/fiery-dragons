package com.example.fierydragons;

/**
 * Interface for observing changes in the game state.
 * @author: Observer pattern implemented by @jaden
 */
public interface GameStateObserver {
    /**
     * Method called to update the game state.
     */
    void updateGameState();
    void updatePowerUpImage();
}
