package com.example.fierydragons.models.dragon_types;

import javafx.scene.image.Image;

/**
 * EarthDragon class extends DragonType, representing an Earth Dragon in the game.
 * @author: Taken from Jaden's Sprint 2
 */
public class EarthDragon extends DragonType {

    /**
     * Constructor initialises the EarthDragon with the name "Earth" and sets its image.
     */
    public EarthDragon() {
        super("Earth"); // Initialises the dragon type as "Earth".
        // Sets the dragon image using a file located in the resources.
        this.setDragonImage(new Image(getClass().getResourceAsStream("/images/tokens/earth_dragon.png")));
    }
}
