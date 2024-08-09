package com.example.fierydragons.models.chits;

import com.example.fierydragons.models.Player;
import com.example.fierydragons.models.animal_types.BackwardDragon;
import com.example.fierydragons.models.caves.Cave;
import com.example.fierydragons.models.squares.Square;
import com.example.fierydragons.services.ChitManager;
import com.example.fierydragons.services.GameManager;
import com.example.fierydragons.services.PlayerManager;
import com.example.fierydragons.services.SquareManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * BackwardChit class extends Chit and handles Backward chits in the game.
 */
public class BackwardChit extends Chit {

    /**
     * Constructor initialises the BackwardChit with a specific count.
     * @param count The initial count for the chit
     */
    public BackwardChit(int count) {
        super(count); // Calls the superclass constructor to set the count.
        this.setAnimalType(new BackwardDragon()); // Sets the animal type to BackwardDragon.
    }

    /**
     * Overrides the getImage method to provide the specific image for Backward chits.
     * @return The image associated with the Backward chit
     */
    @Override
    public Image getImage() {
        String imagePath = "/images/chit/backward.png"; // Constructs the image path for Backward chits.
        return new Image(getClass().getResourceAsStream(imagePath)); // Returns the image from the path.
    }

    /**
     * Flipped chit does not match the current Square.
     * If the current player doesn't have a shield, their token is moved to an unoccupied cave.
     * If the current player has a shield, the shield is removed.
     */
    @Override
    public void handleMismatch() {
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();

        if (!currentPlayer.isHasShield()) {
            moveTokenToUnoccupiedCave();
        } else {
            currentPlayer.setHasShield(false);
        }
    }

    /**
     * Moves the current player's token to an unoccupied cave by moving backward on the board.
     */
    private void moveTokenToUnoccupiedCave() {
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        Square currentSquare = currentPlayer.getCurrentSquare();

        int movementCounter = SquareManager.getInstance().getBoardSquares().indexOf(currentSquare);
        // Get the total number of squares on the board
        int totalSquares = SquareManager.getInstance().getBoardSquares().size();

        // Adjust the loop to use Math.floorMod for backward movement
        for (int step = 0; step < totalSquares; step++) {
            // Calculate the index to check by wrapping around the board
            int indexToCheck = Math.floorMod(movementCounter - step, totalSquares);
            Square destinationSquare = SquareManager.getInstance().getBoardSquares().get(indexToCheck);
            Cave cave = destinationSquare.getCave();

            if (cave != null && cave.getPlayerOccupiedBy() == null) {
                int steps = movementCounter - indexToCheck;
                if (steps < 0) {
                    steps += totalSquares; // Adjust steps to be positive
                }
                PlayerManager.getInstance().movePlayer(-steps);

                // Move the Player into their cave after a delay
                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(222 * (steps + 1)),
                        ae -> {
                            PlayerManager.getInstance().moveTokenInCave(currentPlayer);
                            currentPlayer.setCurrentCave(cave);
                            cave.setPlayerOccupiedBy(currentPlayer);
                            destinationSquare.setCurrentPlayer(null);
                            ChitManager.getInstance().notifyUpdateGameInfo();
                        }
                ));
                timeline.play();

                break;
            }
        }
    }
}