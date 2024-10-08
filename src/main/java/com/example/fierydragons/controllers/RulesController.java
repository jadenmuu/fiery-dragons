package com.example.fierydragons.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * The RulesController class handles navigation within the rules sections of the game.
 * @author: Taken from Jaden's Sprint 2
 */
public class RulesController extends BaseController {

    /**
     * Handles the action when the "Back" button is clicked on the first rules page.
     *
     * @param event The ActionEvent generated by clicking the "Back" button.
     * @throws IOException If an error occurs while switching to the home view.
     */
    @FXML
    private void onBackButtonClick(ActionEvent event) throws IOException {
        classInstance = getClass(); // Update classInstance with the current class for consistency.
        switcher.switchScene(event, classInstance, "home-view.fxml"); // Navigate back to the home view.
    }

    /**
     * Handles the action when the "Next Page" button is clicked on the first rules page.
     *
     * @param event The ActionEvent generated by clicking the "Next Page" button.
     * @throws IOException If an error occurs while switching to the second page of the rules.
     */
    @FXML
    private void onNextPageClick(ActionEvent event) throws IOException {
        classInstance = getClass(); // Update classInstance with the current class.
        switcher.switchScene(event, classInstance, "rules-page-2-view.fxml"); // Navigate to the second page of the rules.
    }

    /**
     * Handles the action when the "Previous Page" button is clicked on the second rules page.
     *
     * @param event The ActionEvent generated by clicking the "Previous Page" button.
     * @throws IOException If an error occurs while switching back to the first page of the rules.
     */
    @FXML
    private void onPageTwoPrevPageClick(ActionEvent event) throws IOException {
        classInstance = getClass(); // Update classInstance with the current class.
        switcher.switchScene(event, classInstance, "rules-view.fxml"); // Navigate back to the first page of the rules.
    }
}
