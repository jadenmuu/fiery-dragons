package com.example.fierydragons.models.animal_types;

import javafx.scene.image.Image;

/**
 * This class represents a Spider.
 * @author: Taken from Jaden's Sprint 2
 */
public class Spider extends Animal {

    /**
     * Constructor for creating a Spider instance.
     */
    public Spider() {
        // Set the name of the spider
        this.setName("Spider");

        // Set the image path for the spider
        this.setAnimalImage(new Image(getClass().getResourceAsStream("/images/icons/spider.png")));
    }
}
