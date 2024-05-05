module be.kuleuven.tennistoernooijava {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.persistence;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;

    opens be.kuleuven.tennistoernooijava to javafx.fxml;
    opens be.kuleuven.tennistoernooijava.controller to javafx.fxml;
    opens be.kuleuven.tennistoernooijava.database to org.hibernate.orm.core;
    exports be.kuleuven.tennistoernooijava;
    exports be.kuleuven.tennistoernooijava.controller;
    exports be.kuleuven.tennistoernooijava.view;
    exports be.kuleuven.tennistoernooijava.model;
}