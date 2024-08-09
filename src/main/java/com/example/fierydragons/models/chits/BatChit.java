package com.example.fierydragons.models.chits;

import com.example.fierydragons.models.animal_types.Bat;
import javafx.scene.image.Image;

/**
 * BatChit class extends Chit to specifically handle chits for Bats.
 * @author: Taken from Jaden's Sprint 2
 */
public class BatChit extends Chit {

    /**
     * Constructor initialises the BatChit with a specific count.
     * @param count The initial count for the chit
     */
    public BatChit(int count) {
        super(count); // Calls the superclass constructor to set the count.
        this.setAnimalType(new Bat()); // Sets the animal type to Bat.
    }

    /**
     * Overrides the getImage method to provide the specific image for Bat chits.
     * @return The image associated with the Bat chit
     */
    @Override
    public Image getImage() {
        String imagePath = "/images/chit/" + getCount() + "xBat.png"; // Constructs the image path for Bat chits.
        return new Image(getClass().getResourceAsStream(imagePath)); // Returns the image from the path.
    }
}
