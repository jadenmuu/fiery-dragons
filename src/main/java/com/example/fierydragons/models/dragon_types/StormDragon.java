package com.example.fierydragons.models.dragon_types;

import javafx.scene.image.Image;

/**
 * StormDragon class extends DragonType, representing a Storm Dragon in the game.
 * @author: Taken from Jaden's Sprint 2
 */
public class StormDragon extends DragonType {

    /**
     * Constructor initialises the StormDragon with the name "Storm" and sets its image.
     */
    public StormDragon() {
        super("Storm"); // Initialises the dragon type as "Storm".
        // Sets the dragon image using a file located in the resources.
        this.setDragonImage(new Image(getClass().getResourceAsStream("/images/tokens/storm_dragon.png")));
    }
}
