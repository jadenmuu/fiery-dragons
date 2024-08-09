package com.example.fierydragons.utils;

import com.example.fierydragons.models.Player;
import com.example.fierydragons.models.caves.Cave;
import com.example.fierydragons.models.squares.Square;
import com.example.fierydragons.services.PlayerManager;
import com.example.fierydragons.services.SquareManager;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.util.List;

/**
 * The GameBoardSetup class is responsible for setting up the game board layout, including squares, lines, and images.
 * @author: Taken from Jaden's Sprint 2
 */
public class GameBoardSetup {

    // Public static variables used to get the angle to rotate on the board later on
    private static int numberOfSections;
    private static double centerX; // Center X of both circles
    private static double centerY; // Center Y of both circles
    private final Pane boardPane; // The Pane to which you add the ImageView and lines
    private final double outerCircleRadius; // Blue circle radius
    private final double innerCircleRadius; // Green circle radius

    /**
     * Constructs a GameBoardSetup object with the specified parameters.
     *
     * @param boardPane        The Pane where the board setup will be applied.
     * @param outerCircleRadius The radius of the outer circle.
     * @param innerCircleRadius The radius of the inner circle.
     * @param centerX           The X-coordinate of the center of both circles.
     * @param centerY           The Y-coordinate of the center of both circles.
     */
    public GameBoardSetup(Pane boardPane, double outerCircleRadius, double innerCircleRadius, double centerX, double centerY) {
        this.boardPane = boardPane;
        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    /**
     * Initialises the game board layout with the specified number of sections.
     *
     * @param numberOfSections The number of sections on the game board.
     */
    public void initialiseBoard(int numberOfSections) {
        this.numberOfSections = numberOfSections;

        List<Square> boardSquares = SquareManager.getInstance().getBoardSquares();

        double angleStep = 2 * Math.PI / numberOfSections;
        double halfAngleStep = angleStep / 2;  // Half the angle between sections

        for (int i = 0; i < numberOfSections; i++) {
            Square square = boardSquares.get(i);
            Image animalImage = square.getAnimalType().getAnimalImage();

            double angleForLine = i * angleStep;
            double angleForImageView = angleForLine + halfAngleStep;  // Offset to place ImageView in the center of the section

            double bottomPlacement = innerCircleRadius + 20;
            double imageViewX = centerX + bottomPlacement * Math.cos(angleForImageView);
            double imageViewY = centerY + bottomPlacement * Math.sin(angleForImageView);
            ImageView animalImageView = new ImageView(animalImage);
            addImageView(animalImageView, imageViewX, imageViewY, 35, 35);

            // Position for the cave image
            double caveImageViewX = centerX + (outerCircleRadius + 20) * Math.cos(angleForImageView);
            double caveImageViewY = centerY + (outerCircleRadius + 20) * Math.sin(angleForImageView);

            // Add the cave ImageView
            Cave currentCave = square.getCave();
            if (currentCave != null) {
                ImageView caveImageView = new ImageView(currentCave.getCaveImage());
                addImageView(caveImageView, caveImageViewX, caveImageViewY, 100, 100);
                // If the current cave is occupied by a player, add a token for this player
                if (currentCave.getPlayerOccupiedBy() != null) {
                    Player playerOnCave = currentCave.getPlayerOccupiedBy();
                    ImageView tokenImageView = new ImageView(playerOnCave.getDragonToken().getDragonImage());
                    playerOnCave.setTokenImageView(tokenImageView);
                    addImageView(tokenImageView, caveImageViewX, caveImageViewY, 50, 50);
                    tokenImageView.toFront();
                }
            } else {
                Player currentPlayer = square.getCurrentPlayer();
                if (currentPlayer != null) {
                    ImageView playerToken = new ImageView(currentPlayer.getDragonToken().getDragonImage());
                    currentPlayer.setTokenImageView(playerToken);
                    addImageView(playerToken, caveImageViewX, caveImageViewY, 50, 50);
                    PlayerManager.getInstance().movePlayerOutOfCave(currentPlayer);
                    playerToken.toFront();
                }
            }

            // Calculate positions for separator lines
            Line separatorLine = createLine(angleForLine);
            boardPane.getChildren().add(separatorLine);
        }
    }


    /**
     * Creates a line segment between the outer and inner circles at the specified angle.
     *
     * @param angleForLine The angle at which to create the line segment.
     * @return A Line object representing the line segment.
     */
    private Line createLine(double angleForLine) {
        double outerX = centerX + outerCircleRadius * Math.cos(angleForLine);
        double outerY = centerY + outerCircleRadius * Math.sin(angleForLine);
        double innerX = centerX + innerCircleRadius * Math.cos(angleForLine);
        double innerY = centerY + innerCircleRadius * Math.sin(angleForLine);

        Line separatorLine = new Line(outerX, outerY, innerX, innerY);
        separatorLine.setStroke(Color.BLACK);
        separatorLine.setStrokeWidth(2); // Adjust the line thickness as needed
        return separatorLine;
    }

    /**
     * Adds an ImageView to the boardPane at the specified position and size.
     *
     * @param imageView    The ImageView to add.
     * @param imageViewX   The X-coordinate of the ImageView's position.
     * @param imageViewY   The Y-coordinate of the ImageView's position.
     * @param imageHeight  The height of the ImageView.
     * @param imageWidth   The width of the ImageView.
     */
    private void addImageView(ImageView imageView, double imageViewX, double imageViewY, int imageHeight, int imageWidth) {
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(imageHeight); // Adjust the size as needed
        imageView.setFitWidth(imageWidth);  // Adjust the size as needed
        imageView.setX(imageViewX - imageView.getFitWidth() / 2); // Adjust for centering
        imageView.setY(imageViewY - imageView.getFitHeight() / 2); // Adjust for centering
        boardPane.getChildren().add(imageView);
    }

    /**
     * Returns the number of sections in the board.
     *
     * @return the number of sections in the board.
     */
    public static int getNumberOfSections() {
        return numberOfSections;
    }

    /**
     * Returns the X-coordinate of the center of the board.
     *
     * @return the X-coordinate of the center of the board.
     */
    public static double getCenterX() {
        return centerX;
    }

    /**
     * Returns the Y-coordinate of the center of the board.
     *
     * @return the Y-coordinate of the center of the board.
     */
    public static double getCenterY() {
        return centerY;
    }


}
