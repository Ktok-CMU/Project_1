package se233.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Resize {

    @FXML
    private TextField widthField;

    @FXML
    private TextField heightField;

    @FXML
    private Text resultText;

    @FXML
    private ImageView imageView;

    @FXML
    private Button importButton;

    @FXML
    public void resizeImage() {
        // Get the user's input from the text fields
        String widthStr = widthField.getText();
        String heightStr = heightField.getText();

        // Convert the input to integers
        int width = Integer.parseInt(widthStr);
        int height = Integer.parseInt(heightStr);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // Load and display the selected image
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);

            // Call the resizeImage method and update resultText
            resizeImage(image, width, height);
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
            // Load and display the selected image
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        }
    }

    // Method for resizing the image
    private void resizeImage(Image image, int width, int height) {
        // Implement your logic to resize the image here
        // You can use the 'image', 'width', and 'height' parameters to perform the resizing

        // Update the resultText with the resizing information
        String result = "Resizing image to width: " + width + " and height: " + height;
        resultText.setText(result);
    }
}
