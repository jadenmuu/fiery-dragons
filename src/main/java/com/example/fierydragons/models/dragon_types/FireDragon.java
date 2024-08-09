package com.example.fierydragons.models.dragon_types;

import javafx.scene.image.Image;

/**
 * FireDragon class extends DragonType, representing a Fire Dragon in the game.
 * @author: Taken from Jaden's Sprint 2
 */
public class FireDragon extends DragonType {

    /**
     * Constructor initialises the FireDragon with the name "Fire" and sets its image.
     */
    public FireDragon() {
        super("Fire"); // Initialises the dragon type as "Fire".
        // Sets the dragon image using a file located in the resources.
        this.setDragonImage(new Image(getClass().getResourceAsStream("/images/tokens/fire_dragon.png")));
    }
}
