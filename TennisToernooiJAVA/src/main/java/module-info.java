module be.kuleuven.tennistoernooijava {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    requires org.slf4j;
    requires java.logging;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.persistence;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
    requires org.hibernate.orm.core;
    requires com.fasterxml.jackson.core;

    opens be.kuleuven.tennistoernooijava to javafx.fxml;
    opens be.kuleuven.tennistoernooijava.controller to javafx.fxml;
    opens be.kuleuven.tennistoernooijava.models to org.hibernate.orm.core;
    exports be.kuleuven.tennistoernooijava.controller;
    exports be.kuleuven.tennistoernooijava.view;
    exports be.kuleuven.tennistoernooijava.models;
    exports be.kuleuven.tennistoernooijava;
}