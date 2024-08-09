package com.example.fierydragons.models.power_ups;

import com.example.fierydragons.models.Player;
import com.example.fierydragons.models.caves.Cave;
import com.example.fierydragons.models.squares.Square;
import com.example.fierydragons.services.ChitManager;
import com.example.fierydragons.services.PlayerManager;
import com.example.fierydragons.services.SquareManager;
import javafx.scene.image.Image;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * The SwapPlayer class extends the PowerUp class and represents the Swap Player power-up.
 * It allows the current player to swap positions with another player on the board.
 * @author: Jaden
 */
public class SwapPlayer extends PowerUp {

    /**
     * Constructor for the SwapPlayer class.
     * Initialises the name and sets the image for the Swap Player power-up.
     */
    public SwapPlayer() {
        this.setName("Swap Player");
        this.setPowerUpImage(new Image(getClass().getResourceAsStream("/images/icons/swap_player.png")));
    }

    /**
     * Activates the Swap Player power-up.
     * Swaps the positions of the current player and a randomly selected other player on the board.
     */
    public void activatePowerUp() {
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        LinkedList<Player> players = PlayerManager.getInstance().getPlayers();

        // Filter out other players who are not the current player and are either not on a cave square,
        // or are on a cave square but the cave is not currently occupied by any player.
        LinkedList<Player> otherPlayers = players.stream()
                .filter(player -> !player.equals(currentPlayer)) // Exclude the current player
                .filter(player -> {
                    Square currentSquare = player.getCurrentSquare();
                    Cave cave = currentSquare.getCave();
                    return (cave == null || cave.getPlayerOccupiedBy() == null); // Include players not on cave squares or on cave squares not occupied by any player
                })
                .collect(Collectors.toCollection(LinkedList::new));

        if (!otherPlayers.isEmpty()) {
            SecureRandom random = new SecureRandom();
            long seed = System.nanoTime(); // Use nanoTime() as seed for randomness
            random.setSeed(seed);

            // Get a random player from the filtered list
            Player randomPlayer = otherPlayers.get(random.nextInt(otherPlayers.size()));

            // swap player token positions
            PlayerManager.getInstance().swapTokenPositions(currentPlayer, randomPlayer);

            // Perform the swap logic
            Square currentPlayerSquare = currentPlayer.getCurrentSquare();
            Square randomPlayerSquare = randomPlayer.getCurrentSquare();

            // Swap both player's current square they are standing on
            currentPlayer.setCurrentSquare(randomPlayerSquare);
            randomPlayer.setCurrentSquare(currentPlayerSquare);

            // Swap both square's currentPlayer attribute
            currentPlayerSquare.setCurrentPlayer(randomPlayer);
            randomPlayerSquare.setCurrentPlayer(currentPlayer);

            ChitManager.getInstance().notifyUpdateGameInfo();

            // update steps required to win the game (may require more or less steps now)
            int currentPlayerSquareIndex = SquareManager.getInstance().getBoardSquares().indexOf(currentPlayerSquare);
            int randomPlayerSquareIndex = SquareManager.getInstance().getBoardSquares().indexOf(randomPlayerSquare);

            Cave currentPlayerStartingCave = currentPlayer.getStartingCave();
            Cave randomPlayerStartingCave = randomPlayer.getStartingCave();

            int currentPlayerStartingCaveSquareIndex = 0;
            int randomPlayerStartingCaveSquareIndex = 0;

            for (Square square : SquareManager.getInstance().getBoardSquares()) {
                if (square.getCave() == currentPlayerStartingCave) {
                    currentPlayerStartingCaveSquareIndex = SquareManager.getInstance().getBoardSquares().indexOf(square);
                } else if (square.getCave() == randomPlayerStartingCave) {
                    randomPlayerStartingCaveSquareIndex = SquareManager.getInstance().getBoardSquares().indexOf(square);
                }
            }

            // calculations for steps taken
            currentPlayer.setStepsTaken(calculateNewStepsTaken(currentPlayerStartingCaveSquareIndex, randomPlayerSquareIndex));
            randomPlayer.setStepsTaken(calculateNewStepsTaken(randomPlayerStartingCaveSquareIndex, currentPlayerSquareIndex));
        }
    }

    /**
     * Deactivate Swap Player power up
     */
    @Override
    public void deactivatePowerUp() {

    }

    /**
     * Calculates the new number of steps taken by a player after swapping positions.
     * @param oldIndex The index of the player's previous position on the board.
     * @param newIndex The index of the player's new position on the board.
     * @return The number of steps taken by the player after the position swap.
     */
    private int calculateNewStepsTaken(int oldIndex, int newIndex) {
        int totalSquares = SquareManager.getInstance().getTotalSquares();
        return Math.floorMod((newIndex - oldIndex), totalSquares);
    }
}