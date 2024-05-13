package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.dao.SpelerEmailadressenDAO;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.models.SpelerEmailadressen;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import be.kuleuven.tennistoernooijava.view.SpelerSettingsView;
import javafx.collections.FXCollections;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class SpelerSettingsController {
    @FXML
    private TextField naamInput;

    @FXML
    private TextField emailInput;

    @FXML
    private Button addEmail;

    @FXML
    private TextField rankingInput;

    @FXML
    private TextField gewichtInput;

    @FXML
    private TextField lengteInput;

    @FXML
    private TextField telefoonNummerInput;

    @FXML
    private ChoiceBox<String> geslachtSelector;

    @FXML
    private ChoiceBox<Integer> dagInput;

    @FXML
    private ChoiceBox<Integer> maandInput;

    @FXML
    private Button opslaanKnop;

    @FXML
    private AnchorPane emailList;

    @FXML
    private TextField jaarInput;

    private String geslachten[]  = {"man", "vrouw", "in de war"};
    private Integer dagen[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    private Integer maanden[] = {1,2,3,4,5,6,7,8,9,10,11,12};

    private SpelerService service;
    private SpelerSettingsView view;

    @FXML
    void initialize() {
        service = new SpelerService(new SpelersDAO());

        view = new SpelerSettingsView(service);
        view.update();
        emailList.getChildren().add(view);

        geslachtSelector.setItems(FXCollections.observableArrayList(geslachten));
        dagInput.setItems(FXCollections.observableArrayList((dagen)));
        maandInput.setItems(FXCollections.observableArrayList((maanden)));

        Integer spelerID = SpelerSessie.getSessie().getSpeler().getSpelerID();
        Spelers speler = service.getSpeler(spelerID);

        naamInput.setText(speler.getNaam());
        rankingInput.setText(speler.getRanking().toString());
        gewichtInput.setText(speler.getGewicht().toString());
        lengteInput.setText(speler.getLengte().toString());
        telefoonNummerInput.setText(speler.getTelefoonnummer());
        geslachtSelector.setValue(speler.getGeslacht().toString());
        dagInput.setValue(speler.getDatumID().getDag());
        maandInput.setValue(speler.getDatumID().getMaand());
        jaarInput.setText(speler.getDatumID().getJaar().toString());

        addEmail.setOnAction(e -> addEmail(emailInput.getText()));
    }

    private void addEmail(String email) {
        SpelerEmailadressen newEmail = new SpelerEmailadressen();
        newEmail.setEmail(email);
        newEmail.setSpelerID(SpelerSessie.getSessie().getSpeler());
        newEmail = new SpelerEmailadressenDAO().create(newEmail);
        service.getSpeler(SpelerSessie.getSessie().getSpeler().getSpelerID()).addEmails(newEmail);

        view.update();
        emailList.getChildren().add(view);
    }
}
