package com.example.fierydragons;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class responsible for launching the Fiery Dragons application.
 * @author: Taken from Jaden's Sprint 2
 */
public class FieryDragonsApplication extends Application {
    /**
     * Entry point for launching the application.
     *
     * @param stage The primary stage for the application window.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FieryDragonsApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        stage.setTitle("Fiery Dragons");
        stage.setScene(scene);
        stage.show();
    }
}
