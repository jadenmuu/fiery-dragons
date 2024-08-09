package com.example.fierydragons.models.animal_types;

import javafx.scene.image.Image;

/**
 * This class represents a Salamander.
 * @author: Taken from Jaden's Sprint 2
 */
public class Salamander extends Animal {

    /**
     * Constructor for creating a Salamander instance.
     */
    public Salamander() {
        // Set the name of the salamander
        this.setName("Salamander");

        // Set the image path for the salamander
        this.setAnimalImage(new Image(getClass().getResourceAsStream("/images/icons/salamander.png")));
    }
}
