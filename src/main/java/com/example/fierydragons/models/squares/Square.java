package com.example.fierydragons.models.squares;

import com.example.fierydragons.models.Player;
import com.example.fierydragons.models.animal_types.Animal;
import com.example.fierydragons.models.caves.Cave;
import com.example.fierydragons.models.power_ups.PowerUp;
import com.example.fierydragons.services.AnimalManager;
import org.json.JSONObject;

/**
 * Square representing individual spaces within the game board and make up VolcanoCards.
 * @author: Jaden and Kevin.
 */
public class Square {
    private String squareType; // Type of square.
    private Animal animalType; // The type of animal associated with this square.
    private Player currentPlayer; // The player currently occupying this square.
    private Cave cave; // The cave associated with this square, if any.
    private boolean isCut; // Indicates if this square has a "cut".
    private PowerUp powerUp; // Power Up on a Square

    /**
     * Constructor that initialises a square with an animal type and cut status.
     *
     * @param animalType The type of animal associated with the square.
     * @param isCut      Indicates if the square has a "cut".
     */
    public Square(Animal animalType, boolean isCut) {
        this.squareType = isCut ? "Cut" : "Normal"; // Determine square type based on cut status.
        this.animalType = animalType;
        this.isCut = isCut;
        this.powerUp = null;
    }

    /**
     * Converts the Square object to a JSONObject representation.
     * @return A JSONObject representing the Square object.
     */
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("squareType", this.getSquareType());
        json.put("isCut", this.isCut());
        json.put("animalType", this.getAnimalType().getName());

        if (this.getCave() != null) {
            json.put("cave", this.getCave().toJSON());
        } else {
            json.put("cave", JSONObject.NULL);
        }

        if (this.getPowerUp() != null) {
            json.put("powerUp", this.getPowerUp().toJSON());
        } else {
            json.put("powerUp", JSONObject.NULL);
        }
        return json;
    }

    /**
     * Creates a Square object from a JSONObject.
     * @param json The JSONObject containing the square data.
     * @return A Square object created from the JSONObject.
     */
    public static Square fromJSON(JSONObject json) {
        String squareType = json.getString("squareType");
        boolean isCut = json.getBoolean("isCut");
        Animal animalType = AnimalManager.getInstance().getAnimalByName(json.getString("animalType"));

        Square square = new Square(animalType, isCut);
        square.setSquareType(squareType);

        if (!json.isNull("cave")) {
            Cave cave = Cave.fromJSON(json.getJSONObject("cave"));
            square.setCave(cave);
        }

        if (!json.isNull("powerUp")) {
            PowerUp powerUp = PowerUp.fromJSON(json.getJSONObject("powerUp"));
            square.setPowerUp(powerUp);
        }

        return square;
    }

    /**
     * Gets the type of the square.
     * @return The type of the square.
     */
    public String getSquareType() {
        return squareType;
    }

    /**
     * Sets the type of the square.
     * @param squareType The type to set.
     */
    public void setSquareType(String squareType) {
        this.squareType = squareType;
    }

    /**
     * Gets the type of animal associated with the square.
     * @return The type of animal.
     */
    public Animal getAnimalType() {
        return animalType;
    }

    /**
     * Sets the type of animal associated with the square.
     * @param animalType The type of animal to set.
     */
    public void setAnimalType(Animal animalType) {
        this.animalType = animalType;
    }

    /**
     * Gets the player currently occupying the square.
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the player currently occupying the square.
     * @param currentPlayer The player to set.
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Gets the cave associated with the square.
     * @return The associated cave.
     */
    public Cave getCave() {
        return cave;
    }

    /**
     * Sets the cave associated with the square.
     * @param cave The cave to set.
     */
    public void setCave(Cave cave) {
        this.cave = cave;
    }

    /**
     * Checks if the square has a cut or not.
     * @return True if the square is "cut", otherwise false.
     */
    public boolean isCut() {
        return isCut;
    }

    /**
     * Sets the cut status of the square.
     * @param isCut The cut status to set.
     */
    public void setCut(boolean isCut) {
        this.isCut = isCut;
    }

    /**
     * Gets the power-up associated with the square.
     * @return The power-up associated with the square.
     */
    public PowerUp getPowerUp() {
        return powerUp;
    }

    /**
     * Sets the power-up associated with the square.
     * @param powerUp The power-up to set.
     */
    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }
}