package be.kuleuven.tennistoernooijava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AanmakenSpelerApplicatie extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AanmakenSpelerApplicatie.class.getResource("AanmakenSpelerFXML.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Maak nieuwe speler");
        stage.setScene(scene);
        stage.show();
    }
}
