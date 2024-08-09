package com.example.fierydragons.models.animal_types;

import javafx.scene.image.Image;

/**
 * This class represents a BackwardDragon
 * @author: Kevin
 */
public class BackwardDragon extends Animal {

    /**
     * Constructor for creating a BackwardDragon instance.
     */
    public BackwardDragon() {
        // Set the name of the BackwardDragon
        this.setName("Backward Dragon");

        // Set the image path for the bat
        this.setAnimalImage(new Image(getClass().getResourceAsStream("/images/chit/backward.png")));
    }
}
