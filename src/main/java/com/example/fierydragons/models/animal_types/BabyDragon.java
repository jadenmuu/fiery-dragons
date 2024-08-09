package com.example.fierydragons.models.animal_types;

import javafx.scene.image.Image;

/**
 * This class represents a Baby Dragon.
 * @author: Taken from Jaden's Sprint 2
 */
public class BabyDragon extends Animal {

    /**
     * Constructor for creating a BabyDragon instance.
     */
    public BabyDragon() {
        // Set the name of the baby dragon
        this.setName("Baby Dragon");

        // Set the image path for the baby dragon
        this.setAnimalImage(new Image(getClass().getResourceAsStream("/images/icons/baby_dragon.png")));
    }
}
