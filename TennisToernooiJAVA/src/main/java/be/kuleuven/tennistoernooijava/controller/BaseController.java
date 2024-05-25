package be.kuleuven.tennistoernooijava.controller;

import javafx.scene.control.Alert;

public abstract class BaseController {
    protected void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
