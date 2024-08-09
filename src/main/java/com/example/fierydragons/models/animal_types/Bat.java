package com.example.fierydragons.models.animal_types;

import javafx.scene.image.Image;

/**
 * This class represents a Bat
 * @author: Taken from Jaden's Sprint 2
 */
public class Bat extends Animal {

    /**
     * Constructor for creating a Bat instance.
     */
    public Bat() {
        // Set the name of the bat
        this.setName("Bat");

        // Set the image path for the bat
        this.setAnimalImage(new Image(getClass().getResourceAsStream("/images/icons/bat.png")));
    }
}
