package be.kuleuven.tennistoernooijava.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ChangeScene {
    private Stage stage;
    private Scene scene;
    private Parent parent;

    public ChangeScene() {
    }

    public void switchToScene(Node node, String sceneNaam) throws IOException {
        URL url = getClass().getResource("/be/kuleuven/tennistoernooijava/" + sceneNaam +".fxml");
        Parent root = FXMLLoader.load(getClass().getResource("/be/kuleuven/tennistoernooijava/" + sceneNaam + ".fxml"));
        stage = (Stage)node.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
