package be.kuleuven.tennistoernooijava.view;

import be.kuleuven.tennistoernooijava.service.SpelerService;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.*;

public class AanmakenSpelerView extends Region {
    private SpelerService model;

    public AanmakenSpelerView(SpelerService model) {
        this.model = model;
    }

    public void displayID(Integer id) {
        AnchorPane anchorPane = new AnchorPane();
        Text textId = new Text();
        textId.setText("Dit is jouw persoonlijke spelerID. Verlies deze niet en deel deze met niemand!: " + id.toString());
        textId.setFont(Font.font(15));
        anchorPane.getChildren().add(textId);

        Button genoteerd = new Button();
        genoteerd.setText("genoteerd");
        genoteerd.setTranslateY(20);
        anchorPane.getChildren().add(genoteerd);
        anchorPane.setTranslateY(100);
        anchorPane.setTranslateX(100);


        getChildren().add(anchorPane);
    }

}
