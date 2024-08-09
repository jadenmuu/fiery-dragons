package com.example.fierydragons.services;

import com.example.fierydragons.factories.DragonTypeFactory;
import com.example.fierydragons.models.Player;
import com.example.fierydragons.models.caves.Cave;
import com.example.fierydragons.models.dragon_types.DragonType;
import com.example.fierydragons.models.power_ups.PowerUp;
import com.example.fierydragons.models.squares.Square;
import com.example.fierydragons.utils.GameBoardSetup;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.*;

/**
 * PlayerManager class manages the players and their actions in the game.
 * @author @jaden implemented the initial creation of players, @glenn and @kevin implemented the movement of player tokens
 * @glenn implemented the rotation of players on GUI.
 */
public class PlayerManager {

    // Current player in the game.
    private Player currentPlayer;
    // Singleton instance of PlayerManager to ensure only one instance is created.
    private static PlayerManager instance;
    // List to manage the turn order of players.
    private LinkedList<Player> players;

    /**
     * Private constructor to initialise players and available dragon types.
     */
    private PlayerManager() {
        this.players = new LinkedList<>();
    }

    /**
     * Singleton method to get the instance of PlayerManager.
     * @return The singleton instance of PlayerManager.
     */
    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    /**
     * Serializes the PlayerManager object to a JSONObject.
     * @return A JSONObject representing the PlayerManager.
     */
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        JSONArray playersArray = new JSONArray();

        // Serialise each player's information
        for (Player player : players) {
            JSONObject playerJson = new JSONObject();
            playerJson.put("name", player.getPlayerName());
            playerJson.put("dob", player.getPlayerDob().toString());
            playerJson.put("dragonType", player.getDragonToken().getTypeName());
            playerJson.put("startingCave", player.getStartingCave().toJSON());
            playerJson.put("currentCave", player.getCurrentCave().toJSON());
            playerJson.put("currentSquareIndex", player.getCurrentSquareIndex());
            playerJson.put("stepsTaken", player.getStepsTaken());
            playerJson.put("hasShield", player.isHasShield());

            PowerUp availablePowerUp = player.getAvailablePowerUp();
            if (availablePowerUp != null) {
                playerJson.put("availablePowerUp", availablePowerUp.toJSON());
            }

            // Add the constructed player JSON object to the players array
            playersArray.put(playerJson);
        }

        json.put("players", playersArray);

        // Serialise the current player's name as a simple way to identify them
        if (currentPlayer != null) {
            json.put("currentPlayer", currentPlayer.getPlayerName());
        } else {
            json.put("currentPlayer", JSONObject.NULL);
        }

        return json;
    }

    /**
     * Deserializes a JSONObject to a PlayerManager object.
     * @param json The JSONObject representing the PlayerManager.
     */
    public static void fromJSON(JSONObject json) {
        JSONArray playersArray = json.getJSONArray("players");
        LinkedList<Player> loadedPlayers = new LinkedList<>();

        for (int i = 0; i < playersArray.length(); i++) {
            JSONObject playerJson = playersArray.getJSONObject(i);
            String name = playerJson.getString("name");
            LocalDate dob = LocalDate.parse(playerJson.getString("dob")); // Convert string back to LocalDate
            DragonType dragonToken = DragonTypeFactory.getDragonType(playerJson.getString("dragonType"));
            ImageView tokenImageView = new ImageView(dragonToken.getDragonImage());
            Cave startingCave = Cave.fromJSON(playerJson.getJSONObject("startingCave"));
            Cave currentCave = Cave.fromJSON(playerJson.getJSONObject("currentCave"));
            int currentSquareIndex = playerJson.getInt("currentSquareIndex");
            boolean hasShield = playerJson.getBoolean("hasShield");

            Player player = new Player(name, dob);
            player.setDragonToken(dragonToken);
            player.setTokenImageView(tokenImageView);
            player.setStartingCave(startingCave);
            player.setCurrentCave(currentCave);

            Square square = SquareManager.getInstance().getBoardSquares().get(currentSquareIndex);
            if (square.getCave() != null) {
                if (square.getCave() == currentCave) {
                    currentCave.setPlayerOccupiedBy(player);
                }
            }

            Square currentSquare = SquareManager.getInstance().getBoardSquares().get(currentSquareIndex);
            player.setCurrentSquare(currentSquare);
            currentSquare.setCurrentPlayer(player);

            if (playerJson.has("availablePowerUp") && !playerJson.isNull("availablePowerUp")) {
                JSONObject availablePowerUpJson = playerJson.getJSONObject("availablePowerUp");
                PowerUp availablePowerUp = PowerUp.fromJSON(availablePowerUpJson);
                player.setAvailablePowerUp(availablePowerUp);
            }

            player.setHasShield(hasShield);

            if (!playerJson.isNull("stepsTaken")) {
                player.setStepsTaken(playerJson.getInt("stepsTaken"));
            }

            loadedPlayers.add(player);
        }

        // Reset the singleton instance with loaded players
        instance = getInstance();
        instance.setPlayers(loadedPlayers);

        // Set the current player based on the saved name
        String currentName = json.getString("currentPlayer");
        for (Player player : loadedPlayers) {
            if (player.getPlayerName().equals(currentName)) {
                instance.setCurrentPlayer(player);
                break;
            }
        }
    }

    /**
     * Creates a new player with a name and date of birth, and assigns a dragon type.
     * @param playerName The name of the player.
     * @param dateOfBirth The date of birth of the player.
     */
    public void createPlayer(String playerName, LocalDate dateOfBirth) {
        Player player = new Player(playerName, dateOfBirth);
        addPlayer(player);
    }

    /**
     * Adds a player to the player list.
     * @param playerToAdd The player to add.
     */
    public void addPlayer(Player playerToAdd) {
        this.players.add(playerToAdd);
    }

    /**
     * Sets the turn order of players based on their date of birth.
     */
    public void createPlayerTurnOrder() {
        // Compares the Date of Birth of each Player from players, earliest Date is considered "lower"
        players.sort(Comparator.comparing(Player::getPlayerDob));
        setCurrentPlayer(players.getFirst());
    }

    /**
     * Skips the next player's turn.
     */
    public void skipNextPlayer() {
        if (players.size() > 2) { // Check if it's possible to skip
            // Remove the next player and move them behind the current player
            Player nextPlayer = players.get(1);
            players.remove(1);
            players.addLast(nextPlayer);
        } else {
            GameManager.getInstance().endTurn();
        }
    }

    /**
     * Retrieves and updates the next player in the turn order.
     */
    public void getNextPlayer() {
        Player currentPlayer = players.poll(); // Retrieves and removes the first player
        players.addLast(currentPlayer); // Adds the player to the end of the list
        setCurrentPlayer(players.getFirst());
    }

    /**
     * Updates the number of steps taken by a player.
     * @param player The player whose steps are to be updated.
     * @param stepsCount The number of steps to update.
     */
    public void updateStepsCount(Player player, int stepsCount) {
        player.setStepsTaken(player.getStepsTaken() + stepsCount);
    }

    /**
     * Checks if the player goes over their cave.
     * @param newPosition The new position of the player.
     * @return True if the player goes over, false otherwise.
     */
    public boolean goesOver(int newPosition) {
        return newPosition > SquareManager.getInstance().getTotalSquares() + 1;
    }

    /**
     * Moves the player on the game board.
     * @param steps The number of steps to move.
     */
    public void movePlayer(int steps) {
        Player currentPlayer = PlayerManager.getInstance().getCurrentPlayer();
        int currentPlayerPosition = SquareManager.getInstance().findPlayerPosition(currentPlayer);
        int newPlayerPosition = Math.floorMod(currentPlayerPosition + steps, SquareManager.getInstance().getTotalSquares());   // ensure that stay within boundaries of board

        List<Square> boardSquares = SquareManager.getInstance().getBoardSquares();
        Square currentPlayerSquare = boardSquares.get(currentPlayerPosition);
        Square newPlayerSquare = boardSquares.get(Math.floorMod(newPlayerPosition, boardSquares.size()));

        int stepsToTake = currentPlayer.getStepsTaken() + steps;

        boolean isWin = GameManager.getInstance().checkWin(stepsToTake);
        boolean goesOver = goesOver(stepsToTake); // player goes over their cave
        boolean isEmptySquare = SquareManager.getInstance().emptySquare(newPlayerPosition, currentPlayer, isWin);

        // end their turn
        if (goesOver) {
            GameManager.getInstance().endTurn();
            return;
        }

        if (isEmptySquare) {
            SquareManager.getInstance().updatePlayerPosition(currentPlayer, currentPlayerSquare, newPlayerSquare);
        } else {
            GameManager.getInstance().displayCollisionPopUp();
            return;
        }

        // if the Player would win after moving
        if (isWin) {
            // rotate the Player to their cave
            rotatePlayerToken(steps > 1 ? steps - 1 : 0, isEmptySquare, currentPlayer);

            // move the Player into their cave after a delay
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(222 * steps),
                    ae -> {
                        moveTokenInCave(currentPlayer);
                        GameManager.getInstance().winGame();
                    }
            ));
            timeline.play();
            return;
        }

        // move Player out of their cave
        if (currentPlayer == currentPlayer.getCurrentCave().getPlayerOccupiedBy() && isEmptySquare) {
            movePlayerOutOfCave(currentPlayer);
        }

        // updatePlayerPosition returns false if there is another player in the new square, thus preventing rotations
        rotatePlayerToken(steps, isEmptySquare, currentPlayer);
        // update Player steps after moving
        updateStepsCount(currentPlayer, steps);

        // Activate the power up just stepped on
        Square playerNewSquare = currentPlayer.getCurrentSquare();
        PowerUp squarePowerUp = playerNewSquare.getPowerUp();

        if (squarePowerUp != null) {
            GameManager.getInstance().notifyPowerUpActive();
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(2000), // 2000 milliseconds delay
                    ae -> {
                        squarePowerUp.activatePowerUp(); // Activate the power up after the delay
                        squarePowerUp.deactivatePowerUp();
                        currentPlayer.setAvailablePowerUp(null); // Reset available power up
                        GameManager.getInstance().notifyPowerUpActive();
                    }));
            timeline.play(); // Start the timeline to initiate the delay
        }
    }

    /**
     * Moves the current player outside of cave.
     * @param currentPlayer The current player to move.
     */
    public void movePlayerOutOfCave(Player currentPlayer) {
        currentPlayer.getCurrentCave().setPlayerOccupiedBy(null);
        ImageView token = currentPlayer.getTokenImageView();

        // Get current token position
        double currentX = token.getX();
        double currentY = token.getY();

        // Center coordinates of the board
        double centerX = GameBoardSetup.getCenterX();
        double centerY = GameBoardSetup.getCenterY();

        // Calculate the direction towards the center
        double directionX = centerX - currentX;
        double directionY = centerY - currentY;

        // Normalise the direction
        double length = Math.sqrt(directionX * directionX + directionY * directionY);
        directionX /= length;
        directionY /= length;

        // Set a small step size to move towards the center
        double stepSize = 80;  // Adjust this value based on desired movement speed

        // Update token position to move it closer to the center
        token.setX(currentX + directionX * stepSize);
        token.setY(currentY + directionY * stepSize);
    }

    /**
     * Moves the player's token away from the center of the board when they win.
     * @param currentPlayer The current player to move.
     */
    public void moveTokenInCave(Player currentPlayer) {
        ImageView token = currentPlayer.getTokenImageView();
        // Get current token position
        double currentX = token.getX();
        double currentY = token.getY();

        // Center coordinates of the board
        double centerX = GameBoardSetup.getCenterX();
        double centerY = GameBoardSetup.getCenterY();

        // Calculate the direction away from the center
        double directionX = currentX - centerX;
        double directionY = currentY - centerY;

        // Normalise the direction
        double length = Math.sqrt(directionX * directionX + directionY * directionY);
        directionX /= length;
        directionY /= length;

        // Set a small step size to move away from the center
        double stepSize = 80;  // Adjust this value based on desired movement speed

        // Update token position to move it away from the center
        token.setX(currentX + directionX * stepSize);
        token.setY(currentY + directionY * stepSize);

        token.toFront();
    }

    /**
     * Rotates the player's token based on the number of steps taken.
     * @param steps The number of steps taken by the player.
     * @param emptySquare Indicates if the target square is empty.
     * @param currentPlayer The current player to rotate.
     */
    private void rotatePlayerToken(int steps, boolean emptySquare, Player currentPlayer) {
        ImageView token = currentPlayer.getTokenImageView();

        // rotate the Player's token
        if (Math.abs(steps) > 0 && emptySquare) {
            Rotate rotate = getRotation(token);

            // calculate rotate angle for the token
            double rotateAngle = (steps > 0) ? (360 / GameBoardSetup.getNumberOfSections()) : -(360 / GameBoardSetup.getNumberOfSections());   // set rotation angle depending on steps (forward or backwards)

            // Using Timeline to animate the rotation
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(222), event -> {
                        rotate.setAngle(rotate.getAngle() + rotateAngle); // increment angle by 15 degrees every 222ms
                    })
            );
            timeline.setCycleCount(Math.abs(steps)); // cycle through for how many steps
            timeline.play();
        }
    }

    /**
     * Method to extract the Rotate placed within an ImageView. Puts in a new rotate if no transformations are placed.
     * @param imageView The ImageView we want to place a Rotate within.
     * @return The Rotate object placed within the ImageView.
     */
    private Rotate getRotation(ImageView imageView) {
        if (imageView.getTransforms().isEmpty()) {
            Rotate rotate = new Rotate();
            rotate.setPivotX(GameBoardSetup.getCenterX());
            rotate.setPivotY(GameBoardSetup.getCenterY());
            imageView.getTransforms().add(rotate);
        }
        return (Rotate) imageView.getTransforms().get(0);
    }

    /**
     * Swaps the positions of two player tokens.
     * @param player1 The first player to swap.
     * @param player2 The second player to swap.
     */
    public void swapTokenPositions(Player player1, Player player2) {
        ImageView token1 = player1.getTokenImageView();
        ImageView token2 = player2.getTokenImageView();

        Timeline delayTimeline = new Timeline(new KeyFrame(Duration.millis(888), event -> {
            // Store the original positions of the tokens
            double token1X = token1.getX();
            double token1Y = token1.getY();
            Rotate token1Rotate = getRotation(token1);
            double token1Angle = token1Rotate.getAngle();

            double token2X = token2.getX();
            double token2Y = token2.getY();
            Rotate token2Rotate = getRotation(token2);
            double token2Angle = token2Rotate.getAngle();

            // Swap positions
            token1.setX(token2X);
            token1.setY(token2Y);
            token1Rotate.setAngle(token2Angle);

            token2.setX(token1X);
            token2.setY(token1Y);
            token2Rotate.setAngle(token1Angle);

            // Ensure tokens are properly displayed in the front
            token1.toFront();
            token2.toFront();
        }));
        delayTimeline.play();
    }

    /**
     * Finds a player by their name.
     * @param playerName The name of the player to find.
     * @return The player if found, otherwise null.
     */
    public Player findPlayerByName(String playerName) {
        for (Player player : players) {
            if (player.getPlayerName().equals(playerName)) {
                return player;
            }
        }
        return null; // Return null if no player is found with the given name
    }

    /**
     * Resets the list of players.
     */
    public void reset() {
        players.clear();
    }

    /**
     * Sets the list of players.
     * @param playerOrder The list of players to set.
     */
    public void setPlayers(LinkedList<Player> playerOrder) {
        players = playerOrder;
    }

    /**
     * Retrieves the list of players.
     * @return The list of players.
     */
    public LinkedList<Player> getPlayers() {
        return players;
    }

    /**
     * Retrieves the current player.
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the current player.
     * @param currentPlayer The current player to set.
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}

