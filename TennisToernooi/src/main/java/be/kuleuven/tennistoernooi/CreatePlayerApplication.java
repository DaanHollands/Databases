package be.kuleuven.tennistoernooi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class CreatePlayerApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CreatePlayerApplication.class.getResource("CreatePlayer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 500);
        stage.setTitle("CandyCrush");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
