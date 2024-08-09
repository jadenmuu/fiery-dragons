package com.example.fierydragons.models;

import com.example.fierydragons.models.caves.Cave;
import com.example.fierydragons.models.dragon_types.DragonType;
import com.example.fierydragons.models.power_ups.PowerUp;
import com.example.fierydragons.models.squares.Square;
import com.example.fierydragons.services.SquareManager;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

/**
 * Player class manages the details of a player in the game.
 * @author: Taken from Jaden's Sprint 2
 */
public class Player {

    private String playerName; // Name of the player.
    private LocalDate playerDob; // Date of birth of the player.
    private DragonType dragonToken; // Dragon token assigned to the player.
    private Cave startingCave; // Current cave assigned to the player.
    private Cave currentCave; // Current cave assigned to the player.
    private ImageView tokenImageView; // Image view of the player's token.
    private Square currentSquare; // Square where the player is currently located.
    private int currentSquareIndex;
    private int stepsTaken = 0; // Number of steps taken by the player.
    private PowerUp availablePowerUp = null; // a power up available to the player
    private boolean hasShield = false; // if the player has a shield power up active

    /**
     * Constructor initialises a player with their name and date of birth.
     * @param name The name of the player
     * @param dateOfBirth The date of birth of the player
     */
    public Player(String name, LocalDate dateOfBirth) {
        setPlayerName(name);
        setPlayerDob(dateOfBirth);
    }

    /**
     * Gets the name of the player.
     * @return The name of the player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the name of the player.
     * @param playerName The name of the player to set
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Gets the date of birth of the player.
     * @return The date of birth of the player
     */
    public LocalDate getPlayerDob() {
        return playerDob;
    }

    /**
     * Sets the date of birth of the player.
     * @param playerDob The date of birth of the player to set
     */
    public void setPlayerDob(LocalDate playerDob) {
        this.playerDob = playerDob;
    }

    /**
     * Gets the dragon token assigned to the player.
     * @return The dragon token
     */
    public DragonType getDragonToken() {
        return dragonToken;
    }

    /**
     * Sets the dragon token assigned to the player.
     * @param dragonToken The dragon token to set
     */
    public void setDragonToken(DragonType dragonToken) {
        this.dragonToken = dragonToken;
    }

    /**
     * Gets the starting cave assigned to the player.
     * @return The starting cave
     */
    public Cave getCurrentCave() {
        return currentCave;
    }

    /**
     * Sets the starting cave assigned to the player.
     * @param currentCave The starting cave to set
     */
    public void setCurrentCave(Cave currentCave) {
        this.currentCave = currentCave;
    }

    /**
     * Gets the image view of the player's token.
     * @return The image view of the token
     */
    public ImageView getTokenImageView() {
        return tokenImageView;
    }

    /**
     * Sets the image view of the player's token.
     * @param tokenImageView The image view of the token to set
     */
    public void setTokenImageView(ImageView tokenImageView) {
        this.tokenImageView = tokenImageView;
    }

    /**
     * Gets the square where the player is currently located.
     * @return The current square
     */
    public Square getCurrentSquare() {
        return currentSquare;
    }

    /**
     * Sets the square where the player is currently located.
     * @param currentSquare The current square to set
     */
    public void setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
        setCurrentSquareIndex(SquareManager.getInstance().getBoardSquares().indexOf(currentSquare));
    }

    /**
     * Gets the number of steps taken by the player.
     * @return The number of steps taken
     */
    public int getStepsTaken() {
        return stepsTaken;
    }

    /**
     * Sets the number of steps taken by the player.
     * @param stepsTaken The number of steps taken to set
     */
    public void setStepsTaken(int stepsTaken) {
        this.stepsTaken = stepsTaken;
    }

    /**
     * Getter for the Player's starting cave.
     * @return starting Cave of the Player
     */
    public Cave getStartingCave() {
        return startingCave;
    }

    /**
     * Setter for the starting cave of the Player
     * @param startingCave of the player
     */
    public void setStartingCave(Cave startingCave) {
        this.startingCave = startingCave;
    }

    /**
     * Getter for the available Power Up
     * @return PowerUp available
     */
    public PowerUp getAvailablePowerUp() {
        return availablePowerUp;
    }

    /**
     * Setter for available Power Up
     * @param availablePowerUp of the Player
     */
    public void setAvailablePowerUp(PowerUp availablePowerUp) {
        this.availablePowerUp = availablePowerUp;
    }

    /**
     * If the Player has a Shield Power Up active
     * @return boolean true if the Player has a shield, false otherwise.
     */
    public boolean isHasShield() {
        return hasShield;
    }

    /**
     * Setter for hasShield
     * @param hasShield boolean to be set
     */
    public void setHasShield(boolean hasShield) {
        this.hasShield = hasShield;
    }

    /**
     * Getter for current Square Index
     * @return int of the Player's current Square Index
     */
    public int getCurrentSquareIndex() {
        return currentSquareIndex;
    }

    /**
     * Setter for current Square Index
     * @param currentSquareIndex to be set
     */
    public void setCurrentSquareIndex(int currentSquareIndex) {
        this.currentSquareIndex = currentSquareIndex;
    }
}
