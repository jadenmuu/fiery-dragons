package com.example.fierydragons.services;

import com.example.fierydragons.models.animal_types.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The AnimalManager class manages the Animal instances in the game.
 * It follows the singleton pattern to ensure only one instance is created.
 * @author: Jaden
 */
public class AnimalManager {
    private static AnimalManager instance;
    private final Map<String, Animal> animals = new HashMap<>();

    /**
     * Private constructor to prevent instantiation.
     * Initialises the animals map with default Animal instances.
     */
    private AnimalManager() {
        initialiseAnimals();
    }

    /**
     * Returns the singleton instance of AnimalManager.
     * If the instance doesn't exist, it creates a new one.
     *
     * @return The singleton instance of AnimalManager.
     */
    public static AnimalManager getInstance() {
        if (instance == null) {
            instance = new AnimalManager();
        }
        return instance;
    }

    /**
     * Initialises the animals map with default Animal instances.
     * Each Animal type is associated with a specific name key.
     */
    private void initialiseAnimals() {
        animals.put("Bat", new Bat());
        animals.put("Salamander", new Salamander());
        animals.put("Spider", new Spider());
        animals.put("Baby Dragon", new BabyDragon());
        animals.put("Pirate Dragon", new PirateDragon());
        animals.put("Backward Dragon", new BackwardDragon());
    }

    /**
     * Retrieves an Animal instance by its name.
     *
     * @param name The name of the Animal to retrieve.
     * @return The Animal instance associated with the given name, or null if not found.
     */
    public Animal getAnimalByName(String name) {
        return animals.get(name);
    }
}