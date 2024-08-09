package com.example.fierydragons.controllers;

import com.example.fierydragons.services.GameManager;
import com.example.fierydragons.services.GameStateManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The HomeController class controls the home view of the application and handles button click events to navigate to other views.
 * @author: Taken from Jaden's Sprint 2
 */
public class HomeController extends BaseController {

    /**
     * Handles the 'Start' button click event.
     * Changes the scene to the 'Select Players' view.
     *
     * @param event The ActionEvent generated by the button click.
     * @throws IOException if an error occurs while loading the FXML file.
     */
    @FXML
    private void onStartButtonClick(ActionEvent event) throws IOException {
        classInstance = getClass(); // Store the current class type in classInstance for logging or future reference.
        switcher.switchScene(event, classInstance, "select-players-view.fxml"); // Use switcher to change the scene to the 'Select Players' view.
    }

    /**
     * Handles the "Load" button
     * @param event The ActionEvent generated by the button click.
     * @throws IOException if an error occurs while loading the FXML file.
     */
    @FXML
    private void onLoadGameButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        boolean loadSuccessful = GameStateManager.getInstance().loadGame(stage);

        if (loadSuccessful) {
            GameManager.getInstance().setLoadGame(true);
            switcher.switchScene(event, classInstance, "main-game-board.fxml"); // Use switcher to change the scene to the Main Game Board.
        }
    }

    /**
     * Handles the 'Rules' button click event.
     * Changes the scene to the 'Rules' view.
     *
     * @param event The ActionEvent generated by the button click.
     * @throws IOException if an error occurs while loading the FXML file.
     */
    @FXML
    private void onRulesButtonClick(ActionEvent event) throws IOException {
        classInstance = getClass(); // Store the current class type in classInstance.
        switcher.switchScene(event, classInstance, "rules-view.fxml"); // Use switcher to change the scene to the 'Rules' view.
    }
}