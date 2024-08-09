module com.example.fierydragons {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.json;

    opens com.example.fierydragons to javafx.fxml;
    exports com.example.fierydragons;
    exports com.example.fierydragons.controllers;
    exports com.example.fierydragons.utils;
    opens com.example.fierydragons.controllers to javafx.fxml;
    exports com.example.fierydragons.models;
    exports com.example.fierydragons.models.caves;
    opens com.example.fierydragons.models to javafx.fxml;
    exports com.example.fierydragons.models.squares;
    opens com.example.fierydragons.models.squares to javafx.fxml;
    exports com.example.fierydragons.models.dragon_types;
    exports com.example.fierydragons.models.animal_types;
}