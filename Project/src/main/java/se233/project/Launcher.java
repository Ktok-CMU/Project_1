
package se233.project;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader menuLoader = new FXMLLoader(Launcher.class.getResource("Menu.fxml"));
        Scene scene = new Scene(menuLoader.load());
        primaryStage.setTitle("menu selector");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}