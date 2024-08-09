package com.example.fierydragons.services;

import com.example.fierydragons.factories.CardFactory;
import com.example.fierydragons.models.cards.VolcanoCard;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The CardManager class manages the handling of cards within the game.
 * @author Taken from Jaden's Sprint 2
 */
public class CardManager {

    // Singleton instance of the CardManager.
    private static CardManager instance;
    // List to hold all the VolcanoCards in the game.
    private List<VolcanoCard> cards;
    private int numOfCards;

    /**
     * Private constructor to prevent instantiation.
     */
    private CardManager() {}

    /**
     * Singleton method to get the instance of CardManager.
     * @return The singleton instance of CardManager.
     */
    public static CardManager getInstance() {
        if (instance == null) {
            instance = new CardManager();
        }
        return instance;
    }

    /**
     * Serializes the CardManager object to a JSONObject.
     * @return A JSONObject representing the CardManager.
     */
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        JSONArray cardsArray = new JSONArray();

        // Serialize each card using its toJSON method
        for (VolcanoCard card : cards) {
            cardsArray.put(new JSONObject(card.toJSON()));
        }

        json.put("cards", cardsArray);
        json.put("numOfCards", numOfCards);

        return json;
    }

    /**
     * Deserializes a JSONObject to a CardManager object.
     * @param json The JSONObject representing the CardManager.
     */
    public static void fromJSON(JSONObject json) {
        JSONArray cardsArray = json.getJSONArray("cards");
        List<VolcanoCard> loadedCards = new ArrayList<>();

        // Deserialize each card from JSON
        for (int i = 0; i < cardsArray.length(); i++) {
            JSONObject cardJson = cardsArray.getJSONObject(i);
            VolcanoCard card = VolcanoCard.fromJSON(cardJson);
            loadedCards.add(card);
        }

        // Reset the singleton instance with loaded cards
        instance = getInstance();
        instance.cards = loadedCards;
        instance.numOfCards = json.getInt("numOfCards");
    }

    /**
     * Creates cards based on the total number specified and configures them.
     * @param totalCards The total number of cards to create.
     * @param numSquares The number of squares per card.
     */
    public void createCards(int totalCards, int numSquares) {
        int numPlayers = PlayerManager.getInstance().getPlayers().size();
        VolcanoCard[] createdCards = CardFactory.createCards(totalCards, numSquares, numPlayers);
        cards = Arrays.asList(createdCards);
    }

    /**
     * Gets the list of cards.
     * @return The list of cards.
     */
    public List<VolcanoCard> getCards() {
        return cards;
    }

    /**
     * Sets the list of cards.
     * @param cards The list of cards to set.
     */
    public void setCards(List<VolcanoCard> cards) {
        this.cards = cards;
    }

    /**
     * Gets the number of cards.
     * @return The number of cards.
     */
    public int getNumOfCards() {
        return numOfCards;
    }

    /**
     * Sets the number of cards.
     * @param numOfCards The number of cards to set.
     */
    public void setNumOfCards(int numOfCards) {
        this.numOfCards = numOfCards;
    }
}