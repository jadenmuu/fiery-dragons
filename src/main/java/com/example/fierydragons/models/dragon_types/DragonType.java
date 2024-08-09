package com.example.fierydragons.models.dragon_types;

import javafx.scene.image.Image;

/**
 * Abstract class DragonType serves as a base for different dragon types in the game.
 * @author: Taken from Jaden's Sprint 2
 */
public abstract class DragonType {
    private String typeName; // The name of the dragon type
    private Image dragonImage; // Visual representation of the dragon

    /**
     * Constructor to initialise the dragon type with a name.
     * @param typeName The name of the dragon type
     */
    public DragonType(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Getter for the name of the dragon type.
     * @return The name of the dragon type
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Setter for the name of the dragon type.
     * @param typeName The name of the dragon type to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Getter for the visual representation of the dragon.
     * @return The visual representation of the dragon
     */
    public Image getDragonImage() {
        return dragonImage;
    }

    /**
     * Setter for the visual representation of the dragon.
     * @param dragonImage The image to set as the visual representation of the dragon
     */
    public void setDragonImage(Image dragonImage) {
        this.dragonImage = dragonImage;
    }
}
