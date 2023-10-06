package se233.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private void openWaterDrop(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("watermark.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Watermark");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void openResizeDrop(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resizes.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Resize");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
