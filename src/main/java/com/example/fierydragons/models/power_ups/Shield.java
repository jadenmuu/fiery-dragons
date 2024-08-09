package com.example.fierydragons.models.power_ups;

import com.example.fierydragons.services.PlayerManager;
import javafx.scene.image.Image;

/**
 * The Shield class represents a power-up that provides a shield to the current player.
 * @author: Jaden
 */
public class Shield extends PowerUp {

    /**
     * Constructor for the Shield class.
     * Initialises the name and sets the image for the shield power-up.
     */
    public Shield() {
        this.setName("Shield");
        this.setPowerUpImage(new Image(getClass().getResourceAsStream("/images/icons/shield.png")));
    }

    /**
     * Activates the shield power-up for the current player.
     * Sets the hasShield flag to true for the current player.
     */
    @Override
    public void activatePowerUp() {
        PlayerManager.getInstance().getCurrentPlayer().setHasShield(true);
    }

    /**
     * Deactivates the shield power-up
     */
    @Override
    public void deactivatePowerUp() {

    }
}