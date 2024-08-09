package com.example.fierydragons.models.chits;

import com.example.fierydragons.ChitAction;
import com.example.fierydragons.models.animal_types.Animal;
import com.example.fierydragons.services.ChitManager;
import com.example.fierydragons.services.GameManager;
import com.example.fierydragons.services.PlayerManager;
import javafx.scene.image.Image;
import org.json.JSONObject;

/**
 * Abstract class Chit defines the core properties of Chit Cards used in Fiery Dragons.
 * @author: Jaden and Kevin
 */
public abstract class Chit implements ChitAction {
    private final int count; // The count of how many times the chit has been used
    private Animal animalType; // The type of animal associated with the chit
    private boolean isFlipped = false;

    /**
     * Constructor to initialise the chit with a count for the number of animals on the chit.
     * @param count The initial count for the chit
     */
    public Chit(int count) {
        this.count = count;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("count", this.getCount());
        json.put("animalType", this.getAnimalType().getName());
        json.put("isFlipped", this.isFlipped());

        return json;
    }

    @Override
    public void move(boolean playerInCave) {
        int stepsToMove = getCount();

        if (playerInCave) {
            stepsToMove -= 1;
        }

        PlayerManager.getInstance().movePlayer(stepsToMove);
        ChitManager.getInstance().notifyUpdateGameInfo();
    }

    @Override
    public void handleMismatch() {
        GameManager.getInstance().endTurn();
    }

    /**
     * Getter for the count of the chit.
     * @return The count of the chit
     */
    public int getCount() {
        return count;
    }

    /**
     * Getter for the type of animal associated with the chit.
     * @return The type of animal associated with the chit
     */
    public Animal getAnimalType() {
        return animalType;
    }

    /**
     * Setter for the type of animal associated with the chit.
     * @param animalType The type of animal to associate with the chit
     */
    public void setAnimalType(Animal animalType) {
        this.animalType = animalType;
    }

    /**
     * If a Chit is flipped or not
     * @return boolean True if Chit is flipped, false otherwise
     */
    public boolean isFlipped() {
        return isFlipped;
    }

    /**
     * Setter for isFlipped attribute
     * @param flipped boolean to be set
     */
    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    /**
     * Abstract method to retrieve the image associated with the chit.
     * Subclasses must implement this method to return the appropriate image.
     * @return The image associated with the chit
     */
    public abstract Image getImage();
}



