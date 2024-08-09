package com.example.fierydragons.models.chits;

import com.example.fierydragons.models.animal_types.BabyDragon;
import javafx.scene.image.Image;

/**
 * BabyDragonChit class extends Chit to specifically handle chits for Baby Dragons.
 * @author: Taken from Jaden's Sprint 2
 */
public class BabyDragonChit extends Chit {

    /**
     * Constructor initialises the BabyDragonChit with a specific count.
     * @param count The initial count for the chit
     */
    public BabyDragonChit(int count) {
        super(count); // Calls the superclass constructor to set the count.
        this.setAnimalType(new BabyDragon()); // Sets the animal type to Baby Dragon.
    }

    /**
     * Overrides the getImage method to provide the specific image for Baby Dragon chits.
     * @return The image associated with the Baby Dragon chit
     */
    @Override
    public Image getImage() {
        String imagePath = "/images/chit/" + getCount() + "xBabyDragon.png"; // Constructs the image path.
        return new Image(getClass().getResourceAsStream(imagePath)); // Returns the image from the path.
    }
}
