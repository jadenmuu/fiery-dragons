package com.example.fierydragons.services;

import com.example.fierydragons.factories.CaveFactory;
import com.example.fierydragons.models.Player;
import com.example.fierydragons.models.cards.VolcanoCard;
import com.example.fierydragons.models.caves.*;
import com.example.fierydragons.models.squares.Square;
import org.json.JSONArray;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The CaveManager class manages the creation and handling of caves within the game.
 * @author: @jaden and @will implemented the initialising and shuffling of caves
 */
public class CaveManager {
    private static CaveManager instance;
    private List<Cave> caves;

    private CaveManager() {
        caves = CaveFactory.initialiseCaves();
        shuffleCaves();
    }

    /**
     * Singleton method to get the instance of CaveManager.
     * @return The singleton instance of CaveManager.
     */
    public static CaveManager getInstance() {
        if (instance == null) {
            instance = new CaveManager();
        }
        return instance;
    }

    /**
     * Randomises the order of caves.
     */
    public void shuffleCaves() {
        SecureRandom random = new SecureRandom();
        long seed = System.nanoTime(); // Use nanoTime() as seed for randomness
        random.setSeed(seed);
        Collections.shuffle(caves, random);
    }

    /**
     * Resets the caves to their initial state.
     */
    public void reset() {
        caves = CaveFactory.initialiseCaves();
        shuffleCaves();
    }

    /**
     * Assigns a specific cave to a cut card based on the card's characteristics.
     * @param cutCard The cut card to assign a cave to.
     */
    public void setCavesToCutCard(VolcanoCard cutCard) {
        Square cutSquare = cutCard.getCutSquare();
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        Cave randomCave = caves.remove(0);
        cutSquare.setCave(randomCave); // Set the cave to the chosen square

        // if the player does not have a starting card, set one.
        if (currentPlayer.getStartingCave() == null) {
            currentPlayer.setStartingCave(randomCave);
            currentPlayer.setCurrentCave(randomCave);
            currentPlayer.setDragonToken(randomCave.getDragonToken());
            currentPlayer.setCurrentSquare(cutSquare);
            randomCave.setPlayerOccupiedBy(currentPlayer);
        }

        PlayerManager.getInstance().getNextPlayer();

        caves.add(randomCave);
    }

    /**
     * Gets the list of caves.
     * @return The list of caves.
     */
    public List<Cave> getCaves() {
        return caves;
    }

    /**
     * Sets the list of caves.
     * @param caves The list of caves to set.
     */
    public void setCaves(List<Cave> caves) {
        this.caves = caves;
    }
}