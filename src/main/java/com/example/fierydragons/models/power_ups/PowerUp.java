package com.example.fierydragons.models.power_ups;

import javafx.scene.image.Image;
import org.json.JSONObject;

/**
 * The abstract PowerUp class represents a power-up in the game.
 * @author: Jaden
 */
public abstract class PowerUp {

    private String name; // name of the PowerUp
    private Image powerUpImage; // image of the power-up

    /**
     * Abstract method to activate the power-up.
     * Subclasses must implement this method to provide the specific behavior of the power-up.
     */
    public abstract void activatePowerUp();

    /**
     * Abstract method to deactivate the power-up.
     * Subclasses must implement this method to provide the specific behavior of the power-up.
     */
    public abstract void deactivatePowerUp();

    /**
     * Converts the PowerUp object to a JSONObject representation.
     * @return A JSONObject representing the PowerUp object.
     */
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("type", this.getName()); // Identify the specific type of PowerUp
        return json;
    }

    /**
     * Creates a PowerUp object from a JSONObject.
     * @param json The JSONObject containing the power-up data.
     * @return A PowerUp object of the specified type.
     * @throws IllegalArgumentException If the power-up type is unknown.
     */
    public static PowerUp fromJSON(JSONObject json) {
        String type = json.getString("type");
        return switch (type) {
            case "Shield" -> new Shield();
            case "Skip Turn" -> new SkipTurn();
            case "Swap Player" -> new SwapPlayer();
            default -> throw new IllegalArgumentException("Unknown PowerUp type");
        };
    }

    /**
     * Returns the name of the power-up.
     * @return The name of the power-up.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the power-up.
     * @param name The name of the power-up.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the image of the power-up.
     * @return The image of the power-up.
     */
    public Image getPowerUpImage() {
        return powerUpImage;
    }

    /**
     * Sets the image representation of the power-up.
     * @param powerUpImage The image representation of the power-up.
     */
    public void setPowerUpImage(Image powerUpImage) {
        this.powerUpImage = powerUpImage;
    }
}