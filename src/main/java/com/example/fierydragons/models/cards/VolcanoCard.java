package com.example.fierydragons.models.cards;

import com.example.fierydragons.factories.SquareFactory;
import com.example.fierydragons.models.squares.Square;
import com.example.fierydragons.models.animal_types.Animal;
import com.example.fierydragons.services.SquareManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class represents a volcano card used in the game. which holds squares
 * @author: @jaden and @kevin implemented the creation of volcano cards
 */
public class VolcanoCard {
    private List<Square> squares = new ArrayList<>(); // List of squares on the card
    private boolean isCutCard; // Indicates if this is a cut card
    private Square cutSquare; // The randomly chosen cut square

    /**
     * Constructor to initialise card squares with animal types and cut behavior.
     * @param animalTypes Array of animal types for the squares
     * @param isCutCard Indicates if this is a cut card
     */
    public VolcanoCard(Animal[] animalTypes, boolean isCutCard) {
        this.isCutCard = isCutCard;

        // Create squares for the card
        for (Animal animalType : animalTypes) {
            Square newSquare = SquareFactory.createSquare(false, animalType);
            this.squares.add(newSquare);
            SquareManager.getInstance().addSquare(newSquare);
        }

        // Initialise cut card behaviour if this is a cut card
        if (isCutCard) {
            initialiseCutCard();
        }
    }

    public VolcanoCard(List<Square> squares) {
        this.squares = squares;

        for (Square square : squares) {
            SquareManager.getInstance().addSquare(square);
        }
    }

    /**
     * Initialises a random square as a "Cut" square.
     */
    private void initialiseCutCard() {
        int randomIndex = new Random().nextInt(squares.size());
        Square randomSquare = squares.get(randomIndex);
        randomSquare.setSquareType("Cut");
        randomSquare.setCut(true);
        this.cutSquare = randomSquare;
    }

    /**
     * Serialises this VolcanoCard to a JSON string.
     * @return JSON string representing this VolcanoCard.
     */
    public String toJSON() {
        JSONObject json = new JSONObject();
        
        JSONArray squaresJson = new JSONArray();
        for (Square square : squares) {
            squaresJson.put(square.toJSON());
        }
        json.put("squares", squaresJson);

        return json.toString();
    }

    /**
     * Constructs a VolcanoCard from a JSON object.
     * @param json The JSON object representing a VolcanoCard.
     * @return A VolcanoCard instance.
     */
    public static VolcanoCard fromJSON(JSONObject json) {
        JSONArray squaresJson = json.getJSONArray("squares");
        List<Square> squares = new ArrayList<>();

        for (int i = 0; i < squaresJson.length(); i++) {
            squares.add(Square.fromJSON(squaresJson.getJSONObject(i)));
        }

        return new VolcanoCard(squares);

    }

    /**
     * Retrieves the list of squares on the card.
     * @return The list of squares on the card
     */
    public List<Square> getSquares() {
        return squares;
    }

    /**
     * Sets the list of squares on the card.
     * @param squares The list of squares to set
     */
    public void setSquares(List<Square> squares) {
        this.squares = squares;
    }

    /**
     * Checks if the card is a cut card.
     * @return True if the card is a cut card, false otherwise
     */
    public boolean isCutCard() {
        return isCutCard;
    }

    /**
     * Sets whether the card is a cut card or not.
     * @param isCutCard True if the card is a cut card, false otherwise
     */
    public void setCutCard(boolean isCutCard) {
        this.isCutCard = isCutCard;
    }

    /**
     * Retrieves the randomly chosen cut square.
     * @return The randomly chosen cut square
     */
    public Square getCutSquare() {
        return cutSquare;
    }
}