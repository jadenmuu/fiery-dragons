package com.example.fierydragons.models.animal_types;

import javafx.scene.image.Image;

/**
 * This abstract class represents an animal.
 * @author: Taken from Jaden's Sprint 2
 */
public abstract class Animal {
    private String name; // The name of the animal
    private Image animalImage; // The image representing the animal

    /**
     * Getter for the name of the animal.
     * @return The name of the animal.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the animal.
     * @param name The name to set for the animal.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the image representing the animal.
     * @return The image representing the animal.
     */
    public Image getAnimalImage() {
        return animalImage;
    }

    /**
     * Setter for the image representing the animal.
     * @param animalImage The image to set for the animal.
     */
    public void setAnimalImage(Image animalImage) {
        this.animalImage = animalImage;
    }
}
