package com.example.fierydragons.factories;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The ImageViewFactory class creates instances of ImageViews to show pictures in GUI.
 * @author: @william implemented creation of imageview factory method to represent chit cards, Jaden and Kevin made changes
 */
public class ImageViewFactory {
    /** The radius of the inner circle where chits are placed */
    private static final double INNER_CIRCLE_RADIUS = 175.0;
    /** The x-coordinate of the center of the circle */
    private static final double CIRCLE_CENTER_X = 328.0;
    /** The y-coordinate of the center of the circle */
    private static final double CIRCLE_CENTER_Y = 328.0;
    /** Padding between chits */
    private static final double PADDING = 20.0;

    /**
     * Populates chits on a circular pane.
     *
     * @param circlePane The pane where chits are placed.
     * @param numChits   The number of chits to be placed.
     * @return A list of ImageViews representing the placed chits.
     */
    public static List<ImageView> populateChits(Pane circlePane, int numChits) {
        List<ImageView> placedChits = new ArrayList<>();
        int numRows = (int) Math.ceil(Math.sqrt(numChits));
        int numCols = (int) Math.ceil((double) numChits / numRows);

        // Calculate the dimensions for each cell
        double gridWidth = INNER_CIRCLE_RADIUS * 2 - PADDING * 2;
        double gridHeight = INNER_CIRCLE_RADIUS * 2 - PADDING * 2;
        double cellWidth = gridWidth / numCols;
        double cellHeight = gridHeight / numRows;

        // Calculate the width and height of each chit
        double chitWidth = cellWidth - PADDING;
        double chitHeight = cellHeight - PADDING;

        // Calculate the starting position for placing chits
        double startX = CIRCLE_CENTER_X - INNER_CIRCLE_RADIUS + PADDING + cellWidth / 2;
        double startY = CIRCLE_CENTER_Y - INNER_CIRCLE_RADIUS + PADDING + cellHeight / 2;

        int count = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (count >= numChits) {
                    break;
                }

                double x = startX + col * cellWidth;
                double y = startY + row * cellHeight;

                // Create and configure the chit ImageView
                ImageView chitView = createChitView(chitWidth, chitHeight);
                chitView.setLayoutX(x - chitWidth / 2);
                chitView.setLayoutY(y - chitHeight / 2);
                circlePane.getChildren().add(chitView);
                placedChits.add(chitView);
                count++;
            }
        }
        return placedChits;
    }

    /**
     * Creates an ImageView for a chit with the specified width and height.
     *
     * @param chitWidth  The width of the chit ImageView.
     * @param chitHeight The height of the chit ImageView.
     * @return The created chit ImageView.
     */
    private static ImageView createChitView(double chitWidth, double chitHeight) {
        // Load the chit image from resources
        Image chitImage = new Image(Objects.requireNonNull(ImageViewFactory.class.getResourceAsStream("/images/chit/chit.png")));
        // Create the ImageView and configure its properties
        ImageView chitView = new ImageView(chitImage);
        chitView.setFitWidth(chitWidth);
        chitView.setFitHeight(chitHeight);
        chitView.setPreserveRatio(true);
        chitView.setPickOnBounds(true);
        return chitView;
    }
}
