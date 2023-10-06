package se233.project;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainController {

    @FXML
    private TextField widthField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField percentField;

    @FXML
    private ImageView importedImageView;

    @FXML
    private Label resultLabel;

    private Image importedImage;

    @FXML
    public void resizeImage() {
        // Get the user's input for width, height, and percentage
        String widthStr = widthField.getText();
        String heightStr = heightField.getText();
        String percentStr = percentField.getText();

        // Convert the input to integers
        int width = Integer.parseInt(widthStr);
        int height = Integer.parseInt(heightStr);

        // Initialize the percentage value with 100% (no change by default)
        double percent = 100.0;

        // Parse the percentage input if it's not empty
        if (!percentStr.isEmpty()) {
            percent = Double.parseDouble(percentStr);
        }

        // Resize the imported image based on the width, height, and percentage
        if (importedImage != null) {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(importedImage, null);

            // Adjust width and height based on the percentage
            width = (int) (width * percent / 100.0);
            height = (int) (height * percent / 100.0);

            BufferedImage resizedImage = new BufferedImage(width, height, bufferedImage.getType());
            resizedImage.createGraphics().drawImage(bufferedImage, 0, 0, width, height, null);

            importedImage = SwingFXUtils.toFXImage(resizedImage, null);
            importedImageView.setImage(importedImage);

            // Update the resultLabel with resizing information
            String result = "Resized image to width: " + width + ", height: " + height + ", percentage: " + percent + "%";
            resultLabel.setText(result);
        }
    }

    @FXML
    public void importImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // Load the selected image
            importedImage = new Image(selectedFile.toURI().toString());
            importedImageView.setImage(importedImage);
        }
    }

    @FXML
    public void saveImage() {
        if (importedImage != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                    new FileChooser.ExtensionFilter("JPEG Files", "*.jpeg", "*.jpg")
            );

            // Show the save file dialog
            File file = fileChooser.showSaveDialog(new Stage());

            if (file != null) {
                // Save the resized image to the selected file
                try {
                    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(importedImage, null);
                    ImageIO.write(bufferedImage, "png", file);

                    // Update the resultLabel with the save information
                    String result = "Saved resized image to: " + file.getAbsolutePath();
                    resultLabel.setText(result);
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the exception (e.g., show an error message)
                }
            }
        }
    }
}

