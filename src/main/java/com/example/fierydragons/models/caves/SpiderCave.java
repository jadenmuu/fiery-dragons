package com.example.fierydragons.models.caves;

import com.example.fierydragons.models.animal_types.Spider;
import com.example.fierydragons.models.dragon_types.StormDragon;
import javafx.scene.image.Image;

/**
 * This class represents a specific type of cave in the game for Spiders.
 * @author: Taken from Jaden's Sprint 2
 */
public class SpiderCave extends Cave {

    /**
     * Constructor for creating a SpiderCave instance.
     */
    public SpiderCave() {
        // Set the cave's type to "Spider"
        this.setCaveType("Spider");

        // Set the image for this cave
        this.setCaveImage(new Image(getClass().getResourceAsStream("/images/caves/spidercave.png")));

        // Associate a Storm Dragon token with this cave
        this.setDragonToken(new StormDragon());

        // Set the specific animal type (Spider) for this cave
        this.setAnimalType(new Spider());
    }
}
