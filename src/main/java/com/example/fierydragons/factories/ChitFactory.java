package com.example.fierydragons.factories;

import com.example.fierydragons.models.animal_types.Animal;
import com.example.fierydragons.models.chits.*;
import com.example.fierydragons.services.AnimalManager;
import org.json.JSONObject;

/**
 * The ChitFactory class creates instances of Chit objects.
 * @author: @william implemented creation of chit cards using factory method
 */
public class ChitFactory {
    /**
     * Creates a chit with the specified animal type and count.
     *
     * @param animalType The type of animal associated with the chit.
     * @param count      The count of the chit.
     * @return A new Chit object.
     * @throws IllegalArgumentException if the animal type is invalid.
     */
    public static Chit createChit(Animal animalType, int count) {
        return switch (animalType.getName()) {
            case "Baby Dragon" -> new BabyDragonChit(count);
            case "Bat" -> new BatChit(count);
            case "Salamander" -> new SalamanderChit(count);
            case "Spider" -> new SpiderChit(count);
            case "Pirate Dragon" -> new PirateChit(count);
            case "Backward Dragon" -> new BackwardChit(count);
            default -> throw new IllegalArgumentException("Invalid chit type: " + animalType.getName());
        };
    }

    /**
     * Creates a Chit object from a JSONObject.
     *
     * @param json The JSONObject containing the chit data.
     * @return A new Chit object.
     */
    public static Chit fromJSON(JSONObject json) {
        String animalType = json.getString("animalType");
        int count = json.getInt("count");
        boolean isFlipped = json.getBoolean("isFlipped");
        Chit chit = createChit(AnimalManager.getInstance().getAnimalByName(animalType), count);
        chit.setFlipped(isFlipped);

        return chit;
    }
}