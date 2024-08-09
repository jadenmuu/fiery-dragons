package com.example.fierydragons.factories;

import com.example.fierydragons.models.power_ups.PowerUp;
import com.example.fierydragons.models.power_ups.Shield;
import com.example.fierydragons.models.power_ups.SkipTurn;
import com.example.fierydragons.models.power_ups.SwapPlayer;

import java.security.SecureRandom;

/**
 * Factory class to create instances of PowerUp with specific probabilities using SecureRandom.
 * @author: Jaden
 */
public class PowerUpFactory {

    // random value
    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * Generates a PowerUp instance based on defined probabilities using SecureRandom.
     * @return A PowerUp object which can be Shield, SkipTurn, or SwapPlayer.
     */
    public static PowerUp createPowerUp() {
        long seed = System.nanoTime(); // Use nanoTime() as seed for randomness
        secureRandom.setSeed(seed);

        int probability = secureRandom.nextInt(100);  // Generate a number from 0 to 99

        if (probability < 40) {  // 40% chance for Shield
            return new Shield();
        } else if (probability < 80) {  // 40% chance for SkipTurn
            return new SkipTurn();
        } else {  // 20% chance for SwapPlayer
            return new SwapPlayer();
        }
    }
}
