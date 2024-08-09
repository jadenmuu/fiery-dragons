package com.example.fierydragons.factories;

import com.example.fierydragons.models.caves.*;

import java.util.List;
import java.util.ArrayList;

/**
 * A factory class responsible for creating and managing instances of different types of caves.
 * @author: Jaden
 */
public class CaveFactory {

    /**
     * A list to store all created cave instances.
     */
    public static List<Cave> caves = new ArrayList<>();

    /**
     * Creates or retrieves a cave instance based on the given type.
     *
     * @param type The type of cave to create or retrieve.
     * @return The cave instance corresponding to the given type.
     * @throws IllegalArgumentException If the given cave type is unknown.
     */
    public static Cave createCave(String type) {
        // Check if a cave of the given type already exists in the list
        for (Cave cave : caves) {
            if (cave.getCaveType().equals(type)) {
                return cave;
            }
        }

        // Create a new cave instance based on the given type
        Cave newCave;
        switch (type) {
            case "Salamander":
                newCave = new SalamanderCave();
                break;
            case "Bat":
                newCave = new BatCave();
                break;
            case "Spider":
                newCave = new SpiderCave();
                break;
            case "Baby Dragon":
                newCave = new BabyDragonCave();
                break;
            default:
                throw new IllegalArgumentException("Unknown cave type: " + type);
        }

        // Add the new cave instance to the list and return it
        caves.add(newCave);
        return newCave;
    }

    /**
     * Initialises a list of caves with different types.
     *
     * @return A list containing instances of different cave types.
     */
    public static List<Cave> initialiseCaves() {
        List<Cave> caves = new ArrayList<>();
        caves.add(createCave("Salamander"));
        caves.add(createCave("Bat"));
        caves.add(createCave("Spider"));
        caves.add(createCave("Baby Dragon"));
        return caves;
    }
}