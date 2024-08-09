package com.example.fierydragons.services;

import com.example.fierydragons.models.squares.Square;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * GameStateManager class manages the saving and loading of game states.
 * @author: Jaden
 */
public class GameStateManager {

    private static GameStateManager instance;
    private int fileCount = 1;

    /**
     * Private constructor to prevent instantiation.
     */
    private GameStateManager() {
    }

    /**
     * Singleton method to get the instance of GameStateManager.
     * @return The singleton instance of GameStateManager.
     */
    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    /**
     * Saves the current game state to a JSON file.
     */
    public void saveGame() {
        // Collect game state data from various managers and objects
        JSONObject gameState = new JSONObject();
        gameState.put("playerManager", PlayerManager.getInstance().toJSON());
        gameState.put("cardManager", CardManager.getInstance().toJSON());
        gameState.put("chitManager", ChitManager.getInstance().toJSON());
        gameState.put("squareManager", SquareManager.getInstance().toJSON());

        // Write the game state to a file
        try (FileWriter fileWriter = new FileWriter("game_save_" + fileCount + ".json")) {
            fileWriter.write(gameState.toString());
            fileCount++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a game state from a JSON file.
     * @param primaryStage The primary stage of the application.
     * @return True if the game state was loaded successfully, otherwise false.
     */
    public boolean loadGame(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Game Save File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON Files", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile == null) {
            return false;
        }

        // Read the game state from the selected file
        try (FileReader fileReader = new FileReader(selectedFile)) {
            JSONObject gameState = new JSONObject(new JSONTokener(fileReader));

            CardManager.fromJSON(gameState.getJSONObject("cardManager"));
            SquareManager.fromJSON(gameState.getJSONObject("squareManager"));
            PlayerManager.fromJSON(gameState.getJSONObject("playerManager"));
            ChitManager.fromJSON(gameState.getJSONObject("chitManager"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error Loading Game", "Failed to load the game: " + e.getMessage());
            return false;
        }
    }

    /**
     * Displays an alert with the specified title and message.
     * @param title The title of the alert.
     * @param message The message of the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}