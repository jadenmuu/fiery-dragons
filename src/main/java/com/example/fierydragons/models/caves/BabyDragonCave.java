package com.example.fierydragons.models.caves;

import com.example.fierydragons.models.animal_types.BabyDragon;
import com.example.fierydragons.models.dragon_types.FireDragon;
import javafx.scene.image.Image;

/**
 * This class represents a specific type of cave in the game for Baby Dragons.
 * @author: Taken from Jaden's Sprint 2
 */
public class BabyDragonCave extends Cave {

    /**
     * Constructor for creating a BabyDragonCave instance.
     */
    public BabyDragonCave() {
        // Set the cave's type
        this.setCaveType("Baby Dragon");

        // Set the image for this cave
        this.setCaveImage(new Image(getClass().getResourceAsStream("/images/caves/babydragoncave.png")));

        // Associate a Fire Dragon token with this cave
        this.setDragonToken(new FireDragon());

        // Set the specific animal (Baby Dragon) type for this cave
        this.setAnimalType(new BabyDragon());
    }
}
