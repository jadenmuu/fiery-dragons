package com.example.fierydragons.controllers;

import com.example.fierydragons.services.CardManager;
import com.example.fierydragons.services.SquareManager;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller class for setting the card and square information for the game.
 * @author: Jaden
 */
public class SetCardSquareInfoController extends BaseController {

    // volcanoCard input
    @FXML
    private TextField cardField;

    // squares per card input
    @FXML
    private TextField squareField;

    // confirm button
    @FXML
    private Button confirmButton;

    /**
     * Initializes the controller class.
     * Sets up bindings and default values for the text fields.
     */
    @FXML
    private void initialize() {
        // Create a boolean binding for when both text fields are empty
        BooleanBinding bothFieldsEmpty = cardField.textProperty().isEmpty()
                .and(squareField.textProperty().isEmpty());

        // Bind the disable property of the confirm button to the boolean binding
        confirmButton.disableProperty().bind(bothFieldsEmpty);

        // Set prompt texts for the TextField and DatePicker.
        cardField.setPromptText("Enter the number of Cards for the Game");
        cardField.setText("8"); // set default value

        squareField.setPromptText("Enter the number of Squares per Card");
        squareField.setText("3"); // set default value
    }

    /**
     * Event handler for the confirm button click.
     * Sets the number of cards and squares per card in the game managers.
     * Switches the scene to the main game board.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If there is an error switching the scene.
     */
    @FXML
    private void onConfirmButtonClick(ActionEvent event) throws IOException {
        int numOfCards = Integer.parseInt(cardField.getText());
        int numOfSquares = Integer.parseInt(squareField.getText());

        CardManager.getInstance().setNumOfCards(numOfCards);
        SquareManager.getInstance().setSquaresPerCard(numOfSquares);

        switcher.switchScene(event, this.getClass(), "main-game-board.fxml");
    }

    /**
     * Event handler for the back button click.
     * Switches the scene to the enter player info view.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If there is an error switching the scene.
     */
    @FXML
    private void onBackButtonClick(ActionEvent event) throws IOException {
        classInstance = getClass();
        switcher.switchScene(event, classInstance, "enter-player-info-view.fxml");
    }

}