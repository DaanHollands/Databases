package be.kuleuven.tennistoernooijava.view;

import be.kuleuven.tennistoernooijava.models.SpelerEmailadressen;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SpelerSettingsView extends Region {
    private SpelerService service;
    private int height = 20;

    public SpelerSettingsView(SpelerService service) {
        this.service = service;
    }

    public AnchorPane addEmail(Integer emailCount, String email) {
        AnchorPane box = new AnchorPane();
        Text emailText = new Text(email);

        Button button = new Button("verwijder");
        button.setPrefHeight(height);

        button.setOnAction(e -> deleteEmail(email));


        box.getChildren().addAll(emailText, button);
        box.setPrefWidth(250);
        box.setStyle("-fx-border-color: BLACK");
        AnchorPane.setTopAnchor(emailText, 0.0);
        AnchorPane.setTopAnchor(button, 0.0);
        AnchorPane.setRightAnchor(button, 0.0);
        AnchorPane.setLeftAnchor(emailText,0.0);

        box.setTranslateY(30 * emailCount );
        return box;
    }

    public void update() {
        Set<SpelerEmailadressen> emailsSet = service.getSpeler(SpelerSessie.getSessie().getSpeler().getSpelerID()).getEmails();
        List<SpelerEmailadressen> emailsList = new ArrayList<>(emailsSet);
        getChildren().clear();
        for(int i = 0; i < emailsList.size(); i++) {
            AnchorPane emailPane = addEmail(i, emailsList.get(i).getEmail());
            getChildren().add(emailPane);
        }
    }

    public void deleteEmail(String email) {
        service.removeEmailFromSpeler(SpelerSessie.getSessie().getSpeler().getSpelerID(), email);
//        SpelerSessie.getSessie().getSpeler().removeEmailAdres();
        update();
    }


}
