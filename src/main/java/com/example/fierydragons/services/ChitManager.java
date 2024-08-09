package com.example.fierydragons.services;

import com.example.fierydragons.GameStateObserver;
import com.example.fierydragons.factories.ChitFactory;
import com.example.fierydragons.factories.ImageViewFactory;
import com.example.fierydragons.models.Player;
import com.example.fierydragons.models.animal_types.*;
import com.example.fierydragons.models.caves.Cave;
import com.example.fierydragons.models.chits.*;
import com.example.fierydragons.models.squares.Square;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.util.*;

/**
 * The ChitManager class manages the handling of chits within the game.
 * @author @jaden implemented the observer pattern for chit cards, @kevin implemented the shuffling and flipping of chits
 */
public class ChitManager {

    // EventHandler map to handle events (mouse clicks) on chits when player turn ends.
    private Map<ImageView, EventHandler<Event>> eventHandlerMap = new HashMap<>();
    // List to hold all chits in the game.
    private static ArrayList<Chit> chitDeck = new ArrayList<>();
    // Map to track the flipped state of each chit associated with an ImageView.
    private static HashMap<ImageView, Boolean> imageViewChitMap = new HashMap<>();
    // Singleton instance of ChitManager to ensure only one instance is created.
    private static ChitManager instance;
    // List of observers for the Game State.
    private ArrayList<GameStateObserver> observers = new ArrayList<>();
    private int numChits;

    /**
     * Private constructor to prevent instantiation, and to initialise the chit deck.
     */
    private ChitManager() {
        initialiseChitDeck();
    }

    /**
     * Initialises the chit deck with different types of chits.
     */
    public void initialiseChitDeck() {
        chitDeck.add(ChitFactory.createChit(new BabyDragon(), 1));
        chitDeck.add(ChitFactory.createChit(new BabyDragon(), 2));
        chitDeck.add(ChitFactory.createChit(new BabyDragon(), 3));
        chitDeck.add(ChitFactory.createChit(new Bat(), 1));
        chitDeck.add(ChitFactory.createChit(new Bat(), 2));
        chitDeck.add(ChitFactory.createChit(new Bat(), 3));
        chitDeck.add(ChitFactory.createChit(new Salamander(), 1));
        chitDeck.add(ChitFactory.createChit(new Salamander(), 2));
        chitDeck.add(ChitFactory.createChit(new Salamander(), 3));
        chitDeck.add(ChitFactory.createChit(new Spider(), 1));
        chitDeck.add(ChitFactory.createChit(new Spider(), 2));
        chitDeck.add(ChitFactory.createChit(new Spider(), 3));
        chitDeck.add(ChitFactory.createChit(new PirateDragon(), 1));
        chitDeck.add(ChitFactory.createChit(new PirateDragon(), 1));
        chitDeck.add(ChitFactory.createChit(new PirateDragon(), 1));
        chitDeck.add(ChitFactory.createChit(new PirateDragon(), 2));
        chitDeck.add(ChitFactory.createChit(new PirateDragon(), 2));
        chitDeck.add(ChitFactory.createChit(new PirateDragon(), 2));
        chitDeck.add(ChitFactory.createChit(new BackwardDragon(), 1));
        chitDeck.add(ChitFactory.createChit(new BackwardDragon(), 1));

        setNumChits(chitDeck.size());

        shuffleChits();
    }

    /**
     * Singleton method to get the instance of ChitManager.
     * @return The singleton instance of ChitManager.
     */
    public static ChitManager getInstance() {
        if (instance == null) {
            instance = new ChitManager();
        }
        return instance;
    }

    /**
     * Serializes the ChitManager object to a JSONObject.
     * @return A JSONObject representing the ChitManager.
     */
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        JSONArray chitArray = new JSONArray();

        for (Chit chit : chitDeck) {
            chitArray.put(chit.toJSON());
        }

        json.put("chits", chitArray);
        json.put("numChits", this.numChits);

        return json;
    }

    /**
     * Deserializes a JSONObject to a ChitManager object.
     * @param json The JSONObject representing the ChitManager.
     */
    public static void fromJSON(JSONObject json) {
        JSONArray chitArray = json.getJSONArray("chits");
        ArrayList<Chit> chits = new ArrayList<>();

        chitDeck.clear();

        for (int i = 0; i < chitArray.length(); i++) {
            JSONObject chitJson = chitArray.getJSONObject(i);
            Chit chit = ChitFactory.fromJSON(chitJson);
            chits.add(chit);
        }

        instance = getInstance();
        chitDeck = chits;

        instance.setNumChits(json.getInt("numChits"));
    }

    /**
     * Adds a game state observer to the list of observers.
     * @param observer The observer to be added.
     */
    public void addObserver(GameStateObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all observers of a game state update.
     */
    public void notifyUpdateGameInfo() {
        for (GameStateObserver observer : observers) {
            observer.updateGameState();
        }
    }

    /**
     * Shuffles the chits in the deck to ensure randomness.
     */
    public void shuffleChits() {
        SecureRandom random = new SecureRandom();
        long seed = System.nanoTime(); // Use nanoTime() as seed for randomness
        random.setSeed(seed);

        Collections.shuffle(chitDeck, random); // shuffle chits
    }

    /**
     * Assigns each ImageView to a chit and sets all chits to be un-flipped initially,
     * or reattaches event handlers if specified.
     * @param chitViews The list of ImageView objects to be associated with chits.
     */
    public void initialiseChitsToViews(List<ImageView> chitViews) {
        for (int i = 0; i < chitViews.size(); i++) {
            ImageView view = chitViews.get(i);
            Chit chit = chitDeck.get(i);
            view.setUserData(chit);

            if (chit.isFlipped()) {
                view.setImage(chit.getImage());
                imageViewChitMap.put(view, true); // Marks the ImageView as un-flipped.
            } else {
                imageViewChitMap.put(view, false); // Marks the ImageView as un-flipped.
            }

            EventHandler<javafx.event.Event> eventHandler = event -> flipChit(view);
            view.setOnMouseClicked(eventHandler);
            eventHandlerMap.put(view, eventHandler); // Store or restore the event handler
        }
    }

    /**
     * Flips the chit to show its image or resets it to the default if already flipped.
     * @param view The ImageView representing the chit to be flipped.
     */
    public void flipChit(ImageView view) {
        Chit chit = (Chit) view.getUserData();
        boolean isFlipped = imageViewChitMap.get(view);

        // if the Chit is not flipped over, flip it.
        if (!isFlipped) {
            view.setImage(chit.getImage()); // Sets the image to the chit's face.
            imageViewChitMap.put(view, true); // Marks the ImageView as flipped.
            chit.setFlipped(true);
            onChitFlip(chit);
        }
    }

    /**
     * Clears and reinitialise the chit deck and ImageView map for a new game.
     */
    public void reset() {
        chitDeck.clear(); // Clears the chit deck.
        initialiseChitDeck(); // Refills the chit deck.
        imageViewChitMap.clear(); // Clears the map of ImageViews.
    }

    /**
     * Handles the effects to be executed when a chit is flipped.
     * @param chit The chit that has been flipped by the player.
     */
    public void onChitFlip(Chit chit) {
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        Square currentSquare = currentPlayer.getCurrentSquare();
        String chitAnimalName = chit.getAnimalType().getName();

        // player in their cave
        Cave currentCave = currentSquare.getCave();
        if (currentCave != null && currentCave.getPlayerOccupiedBy() == currentPlayer) {
            if (currentCave.getAnimalType().getName().equals(chitAnimalName)) {
                chit.move(true);
            } else if (chitAnimalName.equals("Backward Dragon")) {
                notifyUpdateGameInfo();
            } else {
                GameManager.getInstance().endTurn();
            }

        } else if (currentSquare.getAnimalType().getName().equals(chitAnimalName)) {
            chit.move(false);
        } else {
            chit.handleMismatch(); // Delegate to chit-specific mismatch handling
        }
    }

    /**
     * Checks all chits to see if they are flipped over and if they are, un-flip them.
     */
    public void checkFlippedChits() {
        for (Map.Entry<ImageView, Boolean> entry : imageViewChitMap.entrySet()) {
            ImageView view = entry.getKey();
            boolean isFlipped = entry.getValue();

            // If the chit is flipped, un-flip it
            if (isFlipped) {
                Chit chit = (Chit) view.getUserData();
                chit.setFlipped(false);
                view.setImage(new Image(getClass().getResourceAsStream("/images/chit/chit.png")));
                imageViewChitMap.put(view, false); // Mark the ImageView as unflipped
            }
        }
    }

    /**
     * Enables all chits to flip again.
     */
    public void enableChitFlipping() {
        for (Map.Entry<ImageView, EventHandler<javafx.event.Event>> entry : ChitManager.getInstance().eventHandlerMap.entrySet()) {
            entry.getKey().setOnMouseClicked(entry.getValue());
        }
    }

    /**
     * Gets the EventHandler map for chits.
     * @return The EventHandler map for chits.
     */
    public Map<ImageView, EventHandler<Event>> getEventHandlerMap() {
        return eventHandlerMap;
    }

    /**
     * Sets the EventHandler map for chits.
     * @param eventHandlerMap The EventHandler map for chits.
     */
    public void setEventHandlerMap(Map<ImageView, EventHandler<Event>> eventHandlerMap) {
        this.eventHandlerMap = eventHandlerMap;
    }

    /**
     * Gets the number of chits.
     * @return The number of chits.
     */
    public int getNumChits() {
        return numChits;
    }

    /**
     * Sets the number of chits.
     * @param numChits The number of chits.
     */
    public void setNumChits(int numChits) {
        this.numChits = numChits;
    }

    /**
     * Gets the chit deck.
     * @return The chit deck.
     */
    public static List<Chit> getChitDeck() {
        return chitDeck;
    }

    /**
     * Gets the ImageView-Chit map.
     * @return The ImageView-Chit map.
     */
    public static HashMap<ImageView, Boolean> getImageViewChitMap() {
        return imageViewChitMap;
    }

    /**
     * Sets the ImageView-Chit map.
     * @param imageViewChitMap The ImageView-Chit map.
     */
    public static void setImageViewChitMap(HashMap<ImageView, Boolean> imageViewChitMap) {
        ChitManager.imageViewChitMap = imageViewChitMap;
    }
}