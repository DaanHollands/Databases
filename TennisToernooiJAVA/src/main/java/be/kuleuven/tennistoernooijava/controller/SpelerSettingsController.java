package be.kuleuven.tennistoernooijava.controller;

import be.kuleuven.tennistoernooijava.Exceptions.*;
import be.kuleuven.tennistoernooijava.dao.SpelerEmailadressenDAO;
import be.kuleuven.tennistoernooijava.dao.SpelersDAO;
import be.kuleuven.tennistoernooijava.enums.Geslachten;
import be.kuleuven.tennistoernooijava.models.SpelerEmailadressen;
import be.kuleuven.tennistoernooijava.models.Spelers;
import be.kuleuven.tennistoernooijava.service.SpelerSessie;
import be.kuleuven.tennistoernooijava.service.SpelerService;
import be.kuleuven.tennistoernooijava.view.SpelerSettingsView;
import javafx.collections.FXCollections;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

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

    private String geslachten[] = Arrays.stream(Geslachten.values()).map(Enum::toString).toArray(String[]::new);
    private Integer dagen[] =  IntStream.rangeClosed(1, 31).boxed().toArray(Integer[]::new);
    private Integer maanden[] =  IntStream.rangeClosed(1, 12).boxed().toArray(Integer[]::new);

    private SpelerService service;
    private SpelerSettingsView view;
    private Spelers speler = SpelerSessie.getSessie().getSpeler();

    @FXML
    void initialize() {
        service = new SpelerService(new SpelersDAO());

        view = new SpelerSettingsView(service);
        view.update();
        emailList.getChildren().add(view);

        geslachtSelector.setItems(FXCollections.observableArrayList(geslachten));
        dagInput.setItems(FXCollections.observableArrayList((dagen)));
        maandInput.setItems(FXCollections.observableArrayList((maanden)));

        naamInput.setText(speler.getNaam());
        rankingInput.setText(speler.getRanking().toString());
        gewichtInput.setText(speler.getGewicht().toString());
        lengteInput.setText(speler.getLengte().toString());
        telefoonNummerInput.setText(speler.getTelefoonnummer());

        if(Objects.equals(speler.getGeslacht(), Geslachten.M)) {
            geslachtSelector.setValue("man");
        }

        else if(Objects.equals(speler.getGeslacht(), Geslachten.V)) {
            geslachtSelector.setValue("vrouw");
        }

        else if(Objects.equals(speler.getGeslacht(), Geslachten.X)) {
            geslachtSelector.setValue("in de war");
        }

        dagInput.setValue(speler.getDatumID().getDag());
        maandInput.setValue(speler.getDatumID().getMaand());
        jaarInput.setText(speler.getDatumID().getJaar().toString());

        addEmail.setOnAction(e -> addEmail(emailInput.getText()));

        opslaanKnop.setOnAction(e -> opslaan());
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

    public void opslaan() {
        Geslachten selectedGeslacht = Geslachten.valueOf(geslachtSelector.getValue());

        try {
            service.updateSpeler(
                    speler, naamInput.getText(), telefoonNummerInput.getText(), dagInput.getValue(),
                    maandInput.getValue(), jaarInput.getText(),
                    gewichtInput.getText(), lengteInput.getText(),
                    rankingInput.getText(), selectedGeslacht
            );
        } catch (InvalidPhoneNumberException | InvalidEmailException | EmptyInputException | IllegalDateException | InvalidInputException e){
            System.out.println(e.getMessage());
        }
    }
}
