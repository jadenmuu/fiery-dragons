package com.example.fierydragons.models.power_ups;

import com.example.fierydragons.models.Player;
import com.example.fierydragons.services.PlayerManager;
import javafx.scene.image.Image;

import java.util.LinkedList;

/**
 * The SkipTurn class extends the PowerUp class and represents the Skip Turn power-up.
 * @author: Jaden
 */
public class SkipTurn extends PowerUp {

    private LinkedList<Player> playerOrderList;

    /**
     * Constructor for the SkipTurn class.
     */
    public SkipTurn() {
        this.setName("Skip Turn");
        this.setPowerUpImage(new Image(getClass().getResourceAsStream("/images/icons/skip_turn.png")));
    }

    /**
     * Activate SkipTurn PowerUp
     */
    @Override
    public void activatePowerUp() {
        playerOrderList = PlayerManager.getInstance().getPlayers();
        PlayerManager.getInstance().skipNextPlayer();
    }

    /**
     * Dectivate SkipTurn PowerUp
     */
    @Override
    public void deactivatePowerUp() {
        PlayerManager.getInstance().setPlayers(playerOrderList);
    }
}
