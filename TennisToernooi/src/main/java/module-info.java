module be.kuleuven.tennistoernooi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens be.kuleuven.tennistoernooi to javafx.fxml;
    exports be.kuleuven.tennistoernooi;
    opens be.kuleuven.tennistoernooi.controllers to javafx.fxml;
}