package com.example.fierydragons.models.caves;

import com.example.fierydragons.models.animal_types.Salamander;
import com.example.fierydragons.models.dragon_types.IceDragon;
import javafx.scene.image.Image;

/**
 * This class represents a specific type of cave in the game for Salamanders.
 * @author: Taken from Jaden's Sprint 2
 */
public class SalamanderCave extends Cave {

    /**
     * Constructor for creating a SalamanderCave instance.
     */
    public SalamanderCave() {
        // Set the cave's type to "Salamander"
        this.setCaveType("Salamander");

        // Set the image for this cave
        this.setCaveImage(new Image(getClass().getResourceAsStream("/images/caves/salamandercave.png")));

        // Associate an Ice Dragon token with this cave
        this.setDragonToken(new IceDragon());

        // Set the specific animal type (Salamander) for this cave
        this.setAnimalType(new Salamander());
    }
}
