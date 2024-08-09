package com.example.fierydragons.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The SwitchScene class is responsible for handling scene transitions in the JavaFX application.
 * @author: Taken from Jaden's Sprint 2
 */
public class SwitchScene {
    private static final String BASE_PATH = "/com/example/fierydragons/";

    /**
     * Switches the scene to the specified FXML file.
     *
     * @param event        The ActionEvent triggering the scene switch.
     * @param classInstance The class instance used to access the resource.
     * @param fxmlFile     The name of the FXML file to switch to.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public void switchScene(ActionEvent event, Class<?> classInstance, String fxmlFile) throws IOException {
        Parent nextView = FXMLLoader.load(classInstance.getResource(BASE_PATH + fxmlFile));
        Scene nextScene = new Scene(nextView, 1366, 768);

        // Get the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(nextScene);
        window.show();
    }
}
