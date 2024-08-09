package com.example.fierydragons.factories;

import com.example.fierydragons.models.dragon_types.*;

/**
 * The DragonTypeFactory class creates instances of DragonType objects.
 * @author: Jaden
 */
public class DragonTypeFactory {
    /**
     * Gets a DragonType object based on the type name.
     * @param type The type name of the dragon.
     * @return A DragonType object corresponding to the given type.
     */
    public static DragonType getDragonType(String type) {
        if (type == null) {
            return null;
        }

        // check type
        switch (type) {
            case "Fire":
                return new FireDragon();
            case "Ice":
                return new IceDragon();
            case "Earth":
                return new EarthDragon();
            case "Storm":
                return new StormDragon();
            default:
                throw new IllegalArgumentException("Unknown Dragon Type: " + type);
        }
    }
}