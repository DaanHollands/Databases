package be.kuleuven.tennistoernooijava.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class HeaderController {
    @FXML
    private MenuBar titleBar;

    private Menu[] paginas= {
            new Menu("Home"),
            new Menu("Maak speler"),
    };

    @FXML
    void initialize() {
        titleBar.getMenus().addAll(paginas);
    }
}
