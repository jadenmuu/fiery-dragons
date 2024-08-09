package com.example.fierydragons.models.chits;

import com.example.fierydragons.models.animal_types.Salamander;
import javafx.scene.image.Image;

/**
 * SalamanderChit class extends Chit and deals with Salamander chits in the game.
 * @author: Taken from Jaden's Sprint 2
 */
public class SalamanderChit extends Chit {

    /**
     * Constructor initialises the SalamanderChit with a specific count.
     * @param count The initial count for the chit
     */
    public SalamanderChit(int count) {
        super(count); // Calls the superclass constructor to set the count.
        this.setAnimalType(new Salamander()); // Sets the animal type to Salamander.
    }

    /**
     * Overrides the getImage method to provide the specific image for Salamander chits.
     * @return The image associated with the Salamander chit
     */
    @Override
    public Image getImage() {
        String imagePath = "/images/chit/" + getCount() + "xSalamander.png"; // Constructs the image path for Salamander chits.
        return new Image(getClass().getResourceAsStream(imagePath)); // Returns the image from the path.
    }
}
