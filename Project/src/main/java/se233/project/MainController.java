package se233.project;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
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

    @FXML
    private CheckBox keepRatioCheckbox;

    private Image importedImage;
    private Image previewImage;

    private boolean keepAspectRatio = true;

    @FXML
    public void initialize() {
        // Initialize the checkbox to be selected by default
        keepRatioCheckbox.setSelected(true);
    }

    @FXML
    public void resizeImage() {
        // Get the user's input for width, height, and percentage
        String widthStr = widthField.getText();
        String heightStr = heightField.getText();
        String percentStr = percentField.getText();

        // Convert the input to doubles
        double width = Double.parseDouble(widthStr);
        double height = Double.parseDouble(heightStr);

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
            width = width * percent / 100.0;
            height = height * percent / 100.0;

            BufferedImage resizedImage = new BufferedImage((int) width, (int) height, bufferedImage.getType());
            resizedImage.createGraphics().drawImage(bufferedImage, 0, 0, (int) width, (int) height, null);

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

            // Reset the preview image when a new image is loaded
            previewImage = null;

            // Always keep aspect ratio after importing an image
            keepAspectRatio = true;
            keepRatioCheckbox.setSelected(true);
            updateImageViewAspectRatio();
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

    @FXML
    public void checkBox() {
        // Toggle the value of keepAspectRatio when the checkbox is clicked
        keepAspectRatio = keepRatioCheckbox.isSelected();

        // If keepAspectRatio is true, update the aspect ratio of the image view
        if (keepAspectRatio) {
            updateImageViewAspectRatio();
        } else {
            // Reset the preview image when aspect ratio is not maintained
            importedImageView.setImage(importedImage);
            previewImage = null;

            // Clear the width and height fields
            widthField.clear();
            heightField.clear();

            // Clear the result label
            resultLabel.setText("");
        }
    }

    private void updateImageViewAspectRatio() {
        if (importedImage != null) {
            double originalWidth = importedImage.getWidth();
            double originalHeight = importedImage.getHeight();

            // Calculate the new width and height to maintain the aspect ratio
            double newWidth = originalWidth;
            double newHeight = originalHeight;

            if (!widthField.getText().isEmpty()) {
                try {
                    newWidth = Double.parseDouble(widthField.getText());
                    newHeight = newWidth * (originalHeight / originalWidth);
                } catch (NumberFormatException e) {
                    // Handle the exception (e.g., show an error message)
                    e.printStackTrace();
                }
            } else if (!heightField.getText().isEmpty()) {
                try {
                    newHeight = Double.parseDouble(heightField.getText());
                    newWidth = newHeight * (originalWidth / originalHeight);
                } catch (NumberFormatException e) {
                    // Handle the exception (e.g., show an error message)
                    e.printStackTrace();
                }
            }

            // Update the width and height fields with the calculated values
            widthField.setText(String.format("%.2f", newWidth));
            heightField.setText(String.format("%.2f", newHeight));

            // Create a preview image with the new aspect ratio
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(importedImage, null);
            BufferedImage previewBufferedImage = new BufferedImage((int) newWidth, (int) newHeight, bufferedImage.getType());
            previewBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, (int) newWidth, (int) newHeight, null);

            // Convert the preview image to FXImage
            previewImage = SwingFXUtils.toFXImage(previewBufferedImage, null);

            // Display the preview image
            importedImageView.setImage(previewImage);

            // Update the resultLabel with aspect ratio information
            String result = "Aspect ratio maintained: Width = " + newWidth + ", Height = " + newHeight;
            resultLabel.setText(result);
        }
    }
}
