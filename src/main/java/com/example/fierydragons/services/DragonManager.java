package com.example.fierydragons.services;

import com.example.fierydragons.models.dragon_types.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The DragonManager class manages the different types of dragons in the Fiery Dragons game.
 * It follows the Singleton pattern to ensure only one instance of the class exists.
 * @author Jaden
 */
public class DragonManager {
    private static DragonManager instance;
    private final Map<String, DragonType> dragonTypes = new HashMap<>();

    /**
     * Private constructor to prevent instantiation and to initialise the dragon types.
     */
    private DragonManager() {
        initialiseDragonTypes();
    }

    /**
     * Singleton method to get the instance of DragonManager.
     * @return The singleton instance of DragonManager.
     */
    public static DragonManager getInstance() {
        if (instance == null) {
            instance = new DragonManager();
        }
        return instance;
    }

    /**
     * Initialises the dragon types and adds them to the dragonTypes map.
     */
    private void initialiseDragonTypes() {
        dragonTypes.put("Earth", new EarthDragon());
        dragonTypes.put("Fire", new FireDragon());
        dragonTypes.put("Ice", new IceDragon());
        dragonTypes.put("Storm", new StormDragon());
    }

    /**
     * Gets a DragonType object by its name.
     * @param name The name of the dragon type.
     * @return The DragonType object corresponding to the given name.
     */
    public DragonType getDragonTypeByName(String name) {
        return dragonTypes.get(name);
    }
}