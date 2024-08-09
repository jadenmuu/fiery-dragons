package com.example.fierydragons.factories;

import com.example.fierydragons.models.animal_types.*;
import com.example.fierydragons.models.cards.VolcanoCard;
import com.example.fierydragons.services.CaveManager;
import com.example.fierydragons.utils.CardConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The CardFactory class creates instances of VolcanoCard objects.
 * @author: @william implemented creation of Volcano cards using factory method
 */
public class CardFactory {
    /**
     * Creates an array of VolcanoCard objects based on the specified parameters.
     *
     * @param totalCards  The total number of cards to create.
     * @param numSquares  The number of squares on the card.
     * @param numPlayers  The number of players in the game.
     * @return An array of VolcanoCard objects.
     */
    public static VolcanoCard[] createCards(int totalCards, int numSquares, int numPlayers) {
        VolcanoCard[] cards = new VolcanoCard[totalCards];
        Animal[][] cardConfigurations = generateCardConfigurations(totalCards, numSquares);
        boolean[] cutCardIndicators = CardConfiguration.generateCutCardIndicators(numPlayers, totalCards);

        for (int i = 0; i < totalCards; i++) {
            Animal[] configuration = cardConfigurations[i];
            boolean isCut = cutCardIndicators[i];

            VolcanoCard card = createVolcanoCard(configuration, isCut);

            if (isCut) {
                CaveManager.getInstance().setCavesToCutCard(card);
            }

            cards[i] = card;
        }

        return cards;
    }

    /**
     * Creates a VolcanoCard object with the specified configuration and cut indicator.
     *
     * @param configuration The configuration of animal types on the card.
     * @param isCut         Indicates whether the card is cut or not.
     * @return A VolcanoCard object.
     */
    private static VolcanoCard createVolcanoCard(Animal[] configuration, boolean isCut) {
        return new VolcanoCard(configuration, isCut);
    }

    /**
     * Generates configurations for multiple VolcanoCard objects based on the specified parameters.
     *
     * @param totalCards The total number of cards to generate configurations for.
     * @param numSquares The number of squares on each card.
     * @return A 2D array containing configurations for multiple cards.
     */
    /**
     * Generates configurations for multiple VolcanoCard objects based on the specified parameters.
     *
     * @param totalCards The total number of cards to generate configurations for.
     * @param numSquares The number of squares on each card.
     * @return A 2D array containing configurations for multiple cards.
     */
    private static Animal[][] generateCardConfigurations(int totalCards, int numSquares) {
        if (totalCards == 8 && numSquares == 3) {
            return CardConfiguration.shuffleCardConfigurations();
        }

        Animal[][] cardConfigurations = new Animal[totalCards][numSquares];
        Random random = new Random();

        for (int i = 0; i < totalCards; i++) {
            List<Animal> availableAnimals = new ArrayList<>(Arrays.asList(ANIMAL_TYPES));

            for (int j = 0; j < numSquares; j++) {
                if (availableAnimals.isEmpty()) {
                    availableAnimals = new ArrayList<>(Arrays.asList(ANIMAL_TYPES));
                }

                int animalIndex = random.nextInt(availableAnimals.size());
                cardConfigurations[i][j] = availableAnimals.get(animalIndex);
                availableAnimals.remove(animalIndex);
            }
        }

        return cardConfigurations;
    }

    /** Array containing all possible animal types for card configuration */
    private static final Animal[] ANIMAL_TYPES = {
            new BabyDragon(), new Bat(), new Salamander(), new Spider()
    };

}
