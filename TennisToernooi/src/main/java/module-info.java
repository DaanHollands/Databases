module be.kuleuven.tennistoernooi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens be.kuleuven.tennistoernooi to javafx.fxml;
    exports be.kuleuven.tennistoernooi;
}