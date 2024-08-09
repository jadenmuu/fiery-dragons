package com.example.fierydragons.models.caves;

import com.example.fierydragons.models.animal_types.Bat;
import com.example.fierydragons.models.dragon_types.EarthDragon;
import javafx.scene.image.Image;

/**
 * This class represents a specific type of cave in the game for Bats.
 * @author: Taken from Jaden's Sprint 2
 */
public class BatCave extends Cave {

    /**
     * Constructor for creating a BatCave instance.
     */
    public BatCave() {
        // Set the cave's type to "Bat"
        this.setCaveType("Bat");

        // Set the image for this cave
        this.setCaveImage(new Image(getClass().getResourceAsStream("/images/caves/batcave.png")));

        // Associate an Earth Dragon token with this cave
        this.setDragonToken(new EarthDragon());

        // Set the specific animal type (Bat) for this cave
        this.setAnimalType(new Bat());
    }
}
