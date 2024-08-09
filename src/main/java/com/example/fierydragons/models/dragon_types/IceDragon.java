package com.example.fierydragons.models.dragon_types;

import javafx.scene.image.Image;

/**
 * IceDragon class extends DragonType, representing an Ice Dragon in the game.
 * @author: Taken from Jaden's Sprint 2
 */
public class IceDragon extends DragonType {

    /**
     * Constructor initialises the IceDragon with the name "Ice" and sets its image.
     */
    public IceDragon() {
        super("Ice"); // Initialises the dragon type as "Ice".
        // Sets the dragon image using a file located in the resources.
        this.setDragonImage(new Image(getClass().getResourceAsStream("/images/tokens/ice_dragon.png")));
    }
}
