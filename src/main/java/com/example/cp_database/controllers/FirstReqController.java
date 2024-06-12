package com.example.cp_database.controllers;

import com.example.cp_database.HibernateSession;
import com.example.cp_database.entities.Delivery;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import jakarta.persistence.Query;

public class FirstReqController implements Initializable {

    @FXML
    private TextField address_field;

    @FXML
    private TableView<Delivery> content_in_table;

    @FXML
    private TableColumn<Delivery, Date> dataColumn;

    @FXML
    private TableColumn<Delivery, Long> idColumn;

    @FXML
    private TableColumn<Delivery, Long> idDelColumn;
    @FXML
    void dosearch(ActionEvent event) {
        Platform.runLater(()-> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                        Query query = session.createQuery("SELECT d.id, d.deliveryDate, d.supplier FROM Delivery d WHERE d.deliveryAddress =:n ORDER BY d.deliveryDate",Delivery.class);
                        query.setParameter("n",address_field.getText());
                        ObservableList<Delivery> list = FXCollections.observableArrayList(query.getResultList());
                        content_in_table.setItems(list);
                    }
            );
        });
    }

    @FXML
    void goback(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idDelColumn.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
    }
}

