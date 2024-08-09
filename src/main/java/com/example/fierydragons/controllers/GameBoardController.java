package com.example.fierydragons.controllers;

import com.example.fierydragons.GameStateObserver;
import com.example.fierydragons.factories.ImageViewFactory;
import com.example.fierydragons.models.Player;
import com.example.fierydragons.models.squares.Square;
import com.example.fierydragons.services.*;
import com.example.fierydragons.utils.GameBoardSetup;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * Controller class for managing the game board UI and logic.
 * @author: Jaden
 */
public class GameBoardController extends BaseController implements GameStateObserver {

    // FXML attributes
    @FXML
    private Pane circlePane; // Pane for displaying circles representing game board
    @FXML
    private ImageView currentPlayerToken; // ImageView representing the current player's token
    @FXML
    private ImageView playerCave; // ImageView representing the current player's cave
    @FXML
    private ImageView animalNeeded; // ImageView representing the needed animal for the current player
    @FXML
    private ImageView powerUpImage; // ImageView representing the needed animal for the current player
    @FXML
    private Text currentPlayerArea; // Text displaying current player information
    @FXML
    private ImageView shieldImage; // ImageView representing a player's Shield Power Up

    /**
     * Initialises the controller and sets up necessary components.
     */
    @FXML
    private void initialize() {
        int numChits = ChitManager.getInstance().getNumChits();

        // Render all chits onto the game board.
        List<ImageView> chitViews = ImageViewFactory.populateChits(circlePane, numChits);

        GameManager.getInstance().addObserver(this);
        ChitManager.getInstance().addObserver(this);

        if (!GameManager.getInstance().isLoadGame()) {
            // Set Player turn order
            PlayerManager.getInstance().createPlayerTurnOrder();

            // Initialise cards and squares
            int numCards = CardManager.getInstance().getNumOfCards();
            int numSquares = SquareManager.getInstance().getSquaresPerCard();
            CardManager.getInstance().createCards(numCards, numSquares);
            SquareManager.getInstance().setTotalSquares(numSquares * numCards);
            SquareManager.getInstance().setPowerUps();

            // Full initialisation of chits
            ChitManager.getInstance().initialiseChitsToViews(chitViews);
        } else {
            // Game is loaded, only reattach event handlers.
            ChitManager.getInstance().initialiseChitsToViews(chitViews);
        }

        // initialise the game board and information about the game
        initialiseGameBoard();
        initialiseGameInfo();
    }

    /**
     * Save game
     */
    @FXML
    private void onSaveGameButtonClick(){
        GameStateManager.getInstance().saveGame();
    }

    /**
     * Initialises the game board layout.
     */
    private void initialiseGameBoard() {
        GameBoardSetup divider = new GameBoardSetup(circlePane, 328, 207, 325, 325);
        divider.initialiseBoard(SquareManager.getInstance().getTotalSquares());
    }

    /**
     * Initialises the game information.
     */
    private void initialiseGameInfo() {
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        Square currentSquare = currentPlayer.getCurrentSquare();

        initialisePowerUpImage();

        currentPlayerArea.setText("Current Player: " + currentPlayer.getPlayerName());
        currentPlayerArea.setStyle("-fx-font-size: 30px;");

        if (currentPlayer.getCurrentCave().getPlayerOccupiedBy() == currentPlayer) {
            animalNeeded.setImage(currentPlayer.getCurrentCave().getAnimalType().getAnimalImage());
        } else {
            animalNeeded.setImage(currentSquare.getAnimalType().getAnimalImage());
        }

        currentPlayerToken.setImage(currentPlayer.getDragonToken().getDragonImage());
        playerCave.setImage(currentPlayer.getStartingCave().getCaveImage());

    }

    /**
     * Initialise image for player power up
     */
    private void initialisePowerUpImage() {
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        if (currentPlayer.getAvailablePowerUp() != null) {
            powerUpImage.setImage(currentPlayer.getAvailablePowerUp().getPowerUpImage());
        } else {
            powerUpImage.setImage(null);
        }

        if (currentPlayer.isHasShield()) {
            shieldImage.setImage(new Image(getClass().getResourceAsStream("/images/icons/shield.png")));
        } else {
            shieldImage.setImage(null);
        }
    }

    /**
     * Update the state of the Game.
     */
    @Override
    public void updateGameState() {
        initialiseGameInfo();
    }

    @Override
    public void updatePowerUpImage() {
        initialisePowerUpImage();
    }

    /**
     * Handles the click event for ending the turn.
     */
    @FXML
    private void endTurn(){
        GameManager.getInstance().notifyNextTurn();
    }
}
