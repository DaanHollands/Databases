package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.exceptions.*;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.enums.Geslachten;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.models.SessionHolders.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import be.kuleuven.tennistoernooijava.view.SpelerSettingsView;
import javafx.collections.FXCollections;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class SpelerSettingsController extends BaseController
{
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
    private ChoiceBox<Geslachten> geslachtSelector;

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

    private final Integer[] dagen = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private final Integer[] maanden = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    private final SpelerService spelerService = new SpelerService(new SpelersDAO());
    private final Spelers speler = SpelerSessie.getSessie().getSpeler();
    private final SpelerSettingsView view = new SpelerSettingsView(spelerService);

    @FXML
    void initialize() {
        initializeFields();
        initializeEmailList();
        initializeSpelerData();
        addEventListeners();
    }

    private void initializeFields() {
        geslachtSelector.setItems(FXCollections.observableArrayList(Geslachten.values()));
        dagInput.setItems(FXCollections.observableArrayList(dagen));
        maandInput.setItems(FXCollections.observableArrayList(maanden));
    }

    private void initializeEmailList() {
        view.update();
        emailList.getChildren().add(view);
    }

    private void initializeSpelerData() {
        naamInput.setText(speler.getNaam());
        rankingInput.setText(speler.getRanking().toString());
        gewichtInput.setText(speler.getGewicht().toString());
        lengteInput.setText(speler.getLengte().toString());
        telefoonNummerInput.setText(speler.getTelefoonnummer());
        geslachtSelector.setValue(speler.getGeslacht());
        dagInput.setValue(speler.getDatumID().getDag());
        maandInput.setValue(speler.getDatumID().getMaand());
        jaarInput.setText(speler.getDatumID().getJaar().toString());
    }

    private void addEventListeners() {
        addEmail.setOnAction(e -> addEmail(emailInput.getText()));
        opslaanKnop.setOnAction(e -> opslaan());
    }

    private void addEmail(String email) {
        try {
            spelerService.addEmailToSpeler(speler, email);
            view.update();
            emailList.getChildren().clear();
            emailList.getChildren().add(view);
        } catch (InvalidEmailException e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void opslaan() {
        Geslachten selectedGeslacht = geslachtSelector.getValue();
        try {
            spelerService.updateSpeler(speler, naamInput.getText(), telefoonNummerInput.getText(),
                    dagInput.getValue(), maandInput.getValue(), jaarInput.getText(),
                    gewichtInput.getText(), lengteInput.getText(),
                    rankingInput.getText(), selectedGeslacht);
        } catch (InvalidPhoneNumberException | InvalidEmailException | EmptyInputException
                 | IllegalDateException | InvalidInputException e) {
            showAlert("Error", e.getMessage());
        }
    }
}
