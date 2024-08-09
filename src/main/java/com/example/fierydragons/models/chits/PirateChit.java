package com.example.fierydragons.models.chits;

import com.example.fierydragons.models.Player;
import com.example.fierydragons.models.animal_types.PirateDragon;
import com.example.fierydragons.services.ChitManager;
import com.example.fierydragons.services.PlayerManager;
import javafx.scene.image.Image;

/**
 * PirateChit class extends Chit and is madeA for Pirate Dragon chits in the game.
 * @author: Taken from Jaden's Sprint 2
 */
public class PirateChit extends Chit {

    /**
     * Constructor initialises the PirateChit with a specific count.
     * @param count The initial count for the chit
     */
    public PirateChit(int count) {
        super(count); // Calls the superclass constructor to set the count.
        this.setAnimalType(new PirateDragon()); // Sets the animal type to Pirate Dragon.
    }

    /**
     * Overrides the getImage method to provide the specific image for Pirate Dragon chits.
     * @return The image associated with the Pirate Dragon chit
     */
    @Override
    public Image getImage() {
        String imagePath = "/images/chit/" + getCount() + "xPirate.png"; // Constructs the image path for Pirate chits.
        return new Image(getClass().getResourceAsStream(imagePath)); // Returns the image from the path.
    }

    /**
     * Flipped chit does not match the current Square.
     * If the current player doesn't have a shield, their token is moved backward by the count of the chit.
     * If the current player has a shield, the shield is removed.
     */
    @Override
    public void handleMismatch() {
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();

        if (!currentPlayer.isHasShield()) {
            PlayerManager.getInstance().movePlayer(-getCount()); // Move player backwards for Pirate Dragon
            ChitManager.getInstance().notifyUpdateGameInfo();
        } else {
            currentPlayer.setHasShield(false);
        }
    }
}