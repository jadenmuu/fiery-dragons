package com.example.fierydragons.models.animal_types;

import javafx.scene.image.Image;

/**
 * This class represents a Pirate Dragon.
 * @author: Taken from Jaden's Sprint 2
 */
public class PirateDragon extends Animal {

    /**
     * Constructor for creating a PirateDragon instance.
     */
    public PirateDragon() {
        // Set the name of the pirate dragon
        this.setName("Pirate Dragon");

        // Set the image for the pirate dragon
        this.setAnimalImage(new Image(getClass().getResourceAsStream("/images/icons/pirate_dragon.png")));
    }
}
