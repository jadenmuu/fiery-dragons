package com.example.fierydragons.services;

import com.example.fierydragons.factories.PowerUpFactory;
import com.example.fierydragons.models.Player;
import com.example.fierydragons.models.caves.Cave;
import com.example.fierydragons.models.power_ups.PowerUp;
import com.example.fierydragons.models.squares.Square;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * SquareManager class manages the board squares and player positions.
 * @author @jaden, @glenn and @kevin worked on updating player movements using the square manager
 */
public class SquareManager {
    // Private instance of the SquareManager class
    private static SquareManager instance;
    // List of Squares for the current board
    private LinkedList<Square> boardSquares = new LinkedList<>();
    // Number of Squares on the current board
    private int totalSquares;
    private int squaresPerCard;

    /**
     * Private constructor to prevent instantiation.
     */
    private SquareManager() {}

    /**
     * Singleton method to get the instance of SquareManager.
     * @return The singleton instance of SquareManager.
     */
    public static SquareManager getInstance() {
        if (instance == null) {
            instance = new SquareManager();
        }
        return instance;
    }

    /**
     * Serializes the SquareManager object to a JSONObject.
     * @return A JSONObject representing the SquareManager.
     */
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        json.put("totalSquares", this.totalSquares);
        json.put("squaresPerCard", this.squaresPerCard);

        return json;
    }

    /**
     * Deserializes a JSONObject to a SquareManager object.
     * @param json The JSONObject representing the SquareManager.
     */
    public static void fromJSON(JSONObject json) {
        instance = getInstance();

        instance.totalSquares = json.getInt("totalSquares");
        instance.squaresPerCard = json.getInt("squaresPerCard");
    }

    /**
     * Finds the position of a player on the board.
     * @param player The player whose position is to be found.
     * @return The position of the player on the board.
     */
    public int findPlayerPosition(Player player) {
        return boardSquares.indexOf(player.getCurrentSquare());
    }

    /**
     * Updates the position of a player on the board.
     * @param player The player whose position is to be updated.
     * @param currentPlayerSquare The current square of the player.
     * @param newPlayerSquare The new square of the player.
     */
    public void updatePlayerPosition(Player player, Square currentPlayerSquare, Square newPlayerSquare) {
        PowerUp newSquarePowerUp = newPlayerSquare.getPowerUp();

        // Set the current Square of the Player to empty
        currentPlayerSquare.setCurrentPlayer(null);

        // Update the player's current square
        player.setCurrentSquare(newPlayerSquare);

        // Move current Player to the new square
        newPlayerSquare.setCurrentPlayer(player);

        // Sets the power up on the square to the player.
        if (newSquarePowerUp != null) {
            player.setAvailablePowerUp(newSquarePowerUp);
        }
    }

    /**
     * Checks if a square is empty or can be moved to by the player.
     * @param newPlayerPosition The new position of the player.
     * @param player The player moving to the new position.
     * @param isWin Indicates if the move is a winning move.
     * @return True if the square is empty or can be moved to, false otherwise.
     */
    public boolean emptySquare(int newPlayerPosition, Player player, boolean isWin) {
        Square targetSquare = isWin ? boardSquares.get(Math.floorMod(newPlayerPosition - 1, boardSquares.size()))
                : boardSquares.get(Math.floorMod(newPlayerPosition, boardSquares.size()));
        Player playerOnTargetSquare = targetSquare.getCurrentPlayer();
        Cave targetSquareCave = targetSquare.getCave();

        // If checking win condition and previous square's cave is occupied, block the move
        if (isWin) {
            Cave winConditionCave = targetSquare.getCave();
            if (winConditionCave != null && winConditionCave.getPlayerOccupiedBy() != null) {
                return false;
            }
        }

        // Check for the regular non-win condition
        if (targetSquareCave != null) {
            // If the cave is occupied but the square itself isn't, it's still considered empty
            if (targetSquareCave.getPlayerOccupiedBy() != null && playerOnTargetSquare == null) {
                return true;
            }
        }

        // Return true if the target square is unoccupied or occupied by the same player moving
        return playerOnTargetSquare == null || playerOnTargetSquare == player;
    }

    /**
     * Sets power-ups on random squares of the board.
     */
    public void setPowerUps() {
        int numOfSquares = totalSquares / 4;  // Adjust this to set the proportion of squares receiving power-ups
        SecureRandom random = new SecureRandom();
        long seed = System.nanoTime(); // Use nanoTime() as seed for randomness
        random.setSeed(seed);

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < boardSquares.size(); i++) {
            if (boardSquares.get(i).getCave() == null) {  // Only add index if square has no cave
                indices.add(i);
            }
        }

        Collections.shuffle(indices, random);  // Shuffle the list of indices

        // Adjust the number of power-ups to the actual number of eligible squares if necessary
        int powerUpCount = Math.min(numOfSquares, indices.size());
        for (int i = 0; i < powerUpCount; i++) {
            PowerUp randomPowerUp = PowerUpFactory.createPowerUp();  // Get a random PowerUp
            int squareIndex = indices.get(i);  // Get a unique shuffled index
            Square squareToSet = boardSquares.get(squareIndex);  // Retrieve the square at the unique index

            squareToSet.setPowerUp(randomPowerUp);  // Set the PowerUp to the square
        }
    }

    /**
     * Adds a square to the list of board squares.
     * @param square The square to add.
     */
    public void addSquare(Square square) {
        boardSquares.add(square);
    }

    /**
     * Returns the list of board squares.
     * @return The list of board squares.
     */
    public List<Square> getBoardSquares() {
        return boardSquares;
    }

    /**
     * Returns the total number of squares on the board.
     * @return The total number of squares on the board.
     */
    public int getTotalSquares() {
        return totalSquares;
    }

    /**
     * Sets the total number of squares on the board.
     * @param totalSquares The total number of squares to set.
     */
    public void setTotalSquares(int totalSquares) {
        this.totalSquares = totalSquares;
    }

    /**
     * Returns the number of squares per card.
     * @return The number of squares per card.
     */
    public int getSquaresPerCard() {
        return squaresPerCard;
    }

    /**
     * Sets the number of squares per card.
     * @param squaresPerCard The number of squares per card to set.
     */
    public void setSquaresPerCard(int squaresPerCard) {
        this.squaresPerCard = squaresPerCard;
    }
}