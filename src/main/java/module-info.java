module com.example.cp_database {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens com.example.cp_database to javafx.fxml;

    opens com.example.cp_database.entities to org.hibernate.orm.core, javafx.base;
    exports com.example.cp_database;
    exports com.example.cp_database.controllers;
    opens com.example.cp_database.controllers to javafx.fxml;


}