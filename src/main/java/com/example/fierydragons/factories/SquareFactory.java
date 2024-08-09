package com.example.fierydragons.factories;

import com.example.fierydragons.models.animal_types.*;
import com.example.fierydragons.models.squares.Square;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * The SquareFactory class creates instances of Square objects.
 * @author: @william implemented creation of square factory method to represent squares on the game board. Changes from Jaden, Kevin and Glenn.
 */
public class SquareFactory {
    /** A secure random number generator. */
    private static final SecureRandom random = new SecureRandom();
    /** A list of available animal types. */
    private static final List<Animal> ANIMALS = new ArrayList<>() {{
        add(new BabyDragon());
        add(new Bat());
        add(new Salamander());
        add(new Spider());
    }};

    /**
     * Creates a square with the specified attributes.
     *
     * @param isCut      Indicates whether the square is a cut square.
     * @param animalType The type of animal associated with the square.
     * @return A new Square object.
     */
    public static Square createSquare(boolean isCut, Animal animalType) {
        if (animalType == null) {
            animalType = getRandomAnimal();
        }
        return new Square(animalType, isCut);
    }

    /**
     * Retrieves a random animal from the list of available animal types.
     *
     * @return A random Animal object.
     */
    private static Animal getRandomAnimal() {
        int index = random.nextInt(ANIMALS.size());
        return ANIMALS.get(index);
    }
}
