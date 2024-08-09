package com.example.fierydragons.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for confirming the number of players before proceeding to enter player information.
 * @author: Taken from Jaden's Sprint 2
 */
public class ConfirmPlayerController extends BaseController {

    /** Variable to store the total number of players selected. */
    private int totalPlayerCount;

    /** ImageView for two players. */
    @FXML
    private ImageView twoPlayers;

    /** ImageView for three players. */
    @FXML
    private ImageView threePlayers;

    /** ImageView for four players. */
    @FXML
    private ImageView fourPlayers;

    /**
     * Handles the "Yes" button click event.
     * @param event The ActionEvent object representing the button click event.
     * @throws IOException If an error occurs while loading the next scene.
     */
    @FXML
    private void onYesButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fierydragons/enter-player-info-view.fxml"));
        Scene nextScene = new Scene(loader.load());

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(nextScene);

        SetPlayerInfoController controller = loader.getController();
        controller.setPlayerCount(this.totalPlayerCount);

        window.show();
    }

    /**
     * Handles the "Back" button click event.
     * @param event The ActionEvent object representing the button click event.
     * @throws IOException If an error occurs while switching scenes.
     */
    @FXML
    private void onBackButtonClick(ActionEvent event) throws IOException {
        classInstance = getClass();
        switcher.switchScene(event, classInstance, "select-players-view.fxml");
    }

    /**
     * Initialises the controller with the number of players.
     * @param playerCount The total number of players selected.
     */
    @FXML
    public void initialiseWithPlayerCount(int playerCount) {
        this.totalPlayerCount = playerCount;

        switch (playerCount) {
            case 2:
                twoPlayers.setVisible(true);
                break;
            case 3:
                threePlayers.setVisible(true);
                break;
            case 4:
                fourPlayers.setVisible(true);
                break;
        }
    }
}
