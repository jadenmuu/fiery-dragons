package com.example.fierydragons.models.caves;

import com.example.fierydragons.factories.CaveFactory;
import com.example.fierydragons.models.Player;
import com.example.fierydragons.models.animal_types.Animal;
import com.example.fierydragons.models.dragon_types.DragonType;
import com.example.fierydragons.services.DragonManager;
import com.example.fierydragons.services.PlayerManager;
import com.example.fierydragons.services.AnimalManager;

import javafx.scene.image.Image;
import org.json.JSONObject;

/**
 * This abstract class represents a cave in the game.
 * @author: Taken from Jaden's Sprint 2
 */
public abstract class Cave {
    private String caveType; // Description of the cave type
    private Image caveImage; // Visual representation of the cave
    private DragonType dragonToken; // The dragon associated with this cave
    private Animal animalType; // The specific animal linked to this cave
    private Player playerOccupiedBy; // The player currently occupying the cave

    // Method to convert Cave object to JSON
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("caveType", this.getCaveType());
        json.put("animalType", this.getAnimalType().getName()); // Assuming AnimalType has a getName() method
        json.put("dragonType", this.getDragonToken().getTypeName()); // Assuming DragonType has a getName() method

        if (this.getPlayerOccupiedBy() != null) {
            json.put("playerOccupiedByName", this.getPlayerOccupiedBy().getPlayerName()); // Store player name
        } else {
            json.put("playerOccupiedByName", JSONObject.NULL);
        }

        return json;
    }

    // Method to create a Cave object from JSON
    public static Cave fromJSON(JSONObject json) {
        String caveType = json.getString("caveType");
        String animalName = json.getString("animalType");
        String dragonName = json.getString("dragonType");
        String playerName = json.optString("playerOccupiedByName");

        Cave cave = CaveFactory.createCave(caveType); // Factory method to create a Cave based on Cave Type
        cave.setCaveType(caveType);
        cave.setAnimalType(AnimalManager.getInstance().getAnimalByName(animalName)); // Static method to find AnimalType by name
        cave.setDragonToken(DragonManager.getInstance().getDragonTypeByName(dragonName)); // Static method to find DragonType by name

        if (playerName != null) {
            Player player = PlayerManager.getInstance().findPlayerByName(playerName);
            cave.setPlayerOccupiedBy(player);
        }

        return cave;
    }

    /**
     * Getter for the cave image.
     * @return The visual representation of the cave
     */
    public Image getCaveImage() {
        return caveImage;
    }

    /**
     * Setter for the cave image.
     * @param caveImage The image to set as the visual representation of the cave
     */
    public void setCaveImage(Image caveImage) {
        this.caveImage = caveImage;
    }

    /**
     * Getter for the cave type description.
     * @return The description of the cave type
     */
    public String getCaveType() {
        return caveType;
    }

    /**
     * Setter for the cave type description.
     * @param caveType The description to set for the cave type
     */
    public void setCaveType(String caveType) {
        this.caveType = caveType;
    }

    /**
     * Getter for the dragon associated with the cave.
     * @return The dragon associated with the cave
     */
    public DragonType getDragonToken() {
        return dragonToken;
    }

    /**
     * Setter for the dragon associated with the cave.
     * @param dragonToken The dragon to set as associated with the cave
     */
    public void setDragonToken(DragonType dragonToken) {
        this.dragonToken = dragonToken;
    }

    /**
     * Getter for the animal type linked to the cave.
     * @return The specific animal linked to the cave
     */
    public Animal getAnimalType() {
        return animalType;
    }

    /**
     * Setter for the animal type linked to the cave.
     * @param animalType The animal to set as linked to the cave
     */
    public void setAnimalType(Animal animalType) {
        this.animalType = animalType;
    }

    /**
     * Getter for the player currently occupying the cave.
     * @return The player currently occupying the cave
     */
    public Player getPlayerOccupiedBy() {
        return playerOccupiedBy;
    }

    /**
     * Setter for the player currently occupying the cave.
     * @param playerOccupiedBy The player to set as occupying the cave
     */
    public void setPlayerOccupiedBy(Player playerOccupiedBy) {
        this.playerOccupiedBy = playerOccupiedBy;
    }
}
