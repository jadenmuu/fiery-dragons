package com.example.fierydragons.utils;

import com.example.fierydragons.models.animal_types.*;

import java.util.Arrays;
import java.util.Random;

/**
 * The CardConfiguration class provides configurations for Volcano cards.
 * @author: @william created the initial generation of cards
 */
public class CardConfiguration {
    /** An array indicating whether each card is a cut card or not. */
    public static final boolean[] CUT_CARD_INDICATORS = {true, false, true, false, true, false, true, false};

    /** The configurations of cards, where each element represents an animal within a VolcanoCard. */
    public static final Animal[][] CARD_CONFIGURATIONS = {
            {new BabyDragon(), new Bat(), new Spider()},
            {new Spider(), new Bat(), new Salamander()},
            {new Salamander(), new Spider(), new Bat()},
            {new BabyDragon(), new Salamander(), new Bat()},
            {new Spider(), new Salamander(), new BabyDragon()},
            {new Bat(), new BabyDragon(), new Salamander()},
            {new Bat(), new Spider(), new BabyDragon()},
            {new Salamander(), new BabyDragon(), new Spider()}
    };

    /**
     * Generates an array indicating whether each card is a cut card or not.
     *
     * @param numPlayers The number of players in the game.
     * @param totalCards The total number of cards in the game.
     * @return An array indicating whether each card is a cut card or not.
     * @throws IllegalArgumentException if the number of players is less than 2 or greater than the total number of cards.
     */
    public static boolean[] generateCutCardIndicators(int numPlayers, int totalCards) {
        boolean[] cutCardIndicators = new boolean[totalCards];

        if (numPlayers < 2 || numPlayers > totalCards) {
            throw new IllegalArgumentException("Invalid number of players or total cards.");
        }

        int numCutCards = numPlayers;

        // Randomly select positions for cut cards
        Random random = new Random();
        for (int i = 0; i < numCutCards; i++) {
            int position = random.nextInt(totalCards);
            while (cutCardIndicators[position]) {
                position = random.nextInt(totalCards);
            }
            cutCardIndicators[position] = true;
        }

        return cutCardIndicators;
    }

    /**
     * Shuffles the card configurations and returns a new array.
     *
     * @return A new array of shuffled card configurations.
     */
    public static Animal[][] shuffleCardConfigurations() {
        Animal[][] shuffledConfigurations = new Animal[CARD_CONFIGURATIONS.length][];

        // Create separate arrays for cut cards and non-cut cards
        Animal[][] cutCards = new Animal[CUT_CARD_INDICATORS.length / 2][];
        Animal[][] nonCutCards = new Animal[CUT_CARD_INDICATORS.length / 2][];

        int cutCardIndex = 0;
        int nonCutCardIndex = 0;
        for (int i = 0; i < CUT_CARD_INDICATORS.length; i++) {
            if (CUT_CARD_INDICATORS[i]) {
                cutCards[cutCardIndex++] = CARD_CONFIGURATIONS[i];
            } else {
                nonCutCards[nonCutCardIndex++] = CARD_CONFIGURATIONS[i];
            }
        }

        // Shuffle cut cards and non-cut cards separately
        shuffleArray(cutCards);
        shuffleArray(nonCutCards);

        // Merge the shuffled cut cards and non-cut cards back into the shuffledConfigurations array
        cutCardIndex = 0;
        nonCutCardIndex = 0;
        for (int i = 0; i < CUT_CARD_INDICATORS.length; i++) {
            if (CUT_CARD_INDICATORS[i]) {
                shuffledConfigurations[i] = cutCards[cutCardIndex++];
            } else {
                shuffledConfigurations[i] = nonCutCards[nonCutCardIndex++];
            }
        }

        return shuffledConfigurations;
    }

    private static void shuffleArray(Animal[][] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            Animal[] temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}