package com.example.fierydragons.services;

import com.example.fierydragons.GameStateObserver;
import com.example.fierydragons.models.Game;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * GameManager class manages the game state and its related operations.
 * @author @jaden implemented the win condition of GameManager
 */
public class GameManager {

    // Instance variable to hold the current game.
    private Game game;
    // Singleton instance of GameManager to ensure only one instance is created.
    private static GameManager instance;
    // List of observers for the Game State.
    private ArrayList<GameStateObserver> observers = new ArrayList<>();
    private boolean isLoadGame = false;

    /**
     * Private constructor to prevent instantiation, and to create a new game.
     */
    private GameManager() {
        game = new Game();
    }

    /**
     * Singleton method to get the instance of GameManager.
     * @return The singleton instance of GameManager.
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Checks if a player has won the game.
     * @param stepsTaken The steps taken by the player.
     * @return True if the player has won, otherwise false.
     */
    public boolean checkWin(int stepsTaken) {
        return stepsTaken == (SquareManager.getInstance().getTotalSquares() + 1);
    }

    /**
     * Displays an alert indicating that the game has been won.
     */
    public void winGame() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Information");
            alert.setHeaderText(null);
            alert.setContentText("You've reached the end of the game board!");
            alert.showAndWait();  // Now safely inside Platform.runLater
        });
    }

    /**
     * Adds a GameStateObserver to the list of observers.
     * @param observer The observer to add.
     */
    public void addObserver(GameStateObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies observers that the next turn is starting.
     */
    public void notifyNextTurn() {
        ChitManager.getInstance().checkFlippedChits();
        PlayerManager.getInstance().getNextPlayer();
        ChitManager.getInstance().enableChitFlipping();

        for (GameStateObserver observer : observers) {
            observer.updateGameState();
        }
    }

    /**
     * Notifies observers that a power-up is active.
     */
    public void notifyPowerUpActive() {
        for (GameStateObserver observer : observers) {
            observer.updatePowerUpImage();
        }
    }

    /**
     * Displays a pop-up indicating that a player collision has occurred.
     */
    public void displayCollisionPopUp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Player Collision");
        alert.setHeaderText(null);
        alert.setContentText("That square contains a Player!");
        alert.showAndWait();

        endTurn();
    }

    /**
     * Ends the current player's turn after a delay.
     */
    public void endTurn() {
        // Disabling all chit flips.
        for (ImageView view : ChitManager.getInstance().getEventHandlerMap().keySet()) {
            view.setOnMouseClicked(null); // Remove the event handler
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    GameManager.getInstance().notifyNextTurn();
                });
            }
        }, 1500); // 1500-millisecond delay before next turn.
    }

    /**
     * Gets the current game.
     * @return The current Game.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the current game.
     * @param game The Game to be set.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Checks if it is a loaded game.
     * @return True if it is a loaded game, otherwise false.
     */
    public boolean isLoadGame() {
        return isLoadGame;
    }

    /**
     * Sets whether it is a loaded game or not.
     * @param loadGame True if it is a loaded game, otherwise false.
     */
    public void setLoadGame(boolean loadGame) {
        isLoadGame = loadGame;
    }
}