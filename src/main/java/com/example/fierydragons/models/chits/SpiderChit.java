package com.example.fierydragons.models.chits;

import com.example.fierydragons.models.animal_types.Spider;
import javafx.scene.image.Image;

/**
 * SpiderChit class extends Chit and is handles Spider chits in the game.
 * @author: Taken from Jaden's Sprint 2
 */
public class SpiderChit extends Chit {

    /**
     * Constructor initialises the SpiderChit with a specific count.
     * @param count The initial count for the chit
     */
    public SpiderChit(int count) {
        super(count); // Calls the superclass constructor to set the count.
        this.setAnimalType(new Spider()); // Sets the animal type to Spider.
    }

    /**
     * Overrides the getImage method to provide the specific image for Spider chits.
     * @return The image associated with the Spider chit
     */
    @Override
    public Image getImage() {
        String imagePath = "/images/chit/" + getCount() + "xSpider.png"; // Constructs the image path for Spider chits.
        return new Image(getClass().getResourceAsStream(imagePath)); // Returns the image from the path.
    }
}
