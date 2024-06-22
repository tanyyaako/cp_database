package com.example.cp_database.controllers;

import com.example.cp_database.ErrorWindow;
import com.example.cp_database.HibernateSession;
import com.example.cp_database.entities.Client;
import jakarta.persistence.Query;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class SecondReqController implements Initializable {

    @FXML
    private Text errorText;

    @FXML
    private TableView<Client> content_in_table;

    @FXML
    private TableColumn<Client, String> mailColumn;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TextField search_field;

    @FXML
    private TableColumn<Client, String> surnameColumn;

    @FXML
    void dosearch(ActionEvent event) {
        Platform.runLater(() -> {
                    HibernateSession.sessionFactory().inTransaction(session -> {
                                try {
                                    Query query = session.createQuery(
                                            "SELECT c.firstName, c.lastName, c.email FROM Client c JOIN Delivery d ON c.id = d.client.id WHERE d.deliveryDate =:n", Client.class
                                    );
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                                    Date date = null;
                                    try {
                                        date = dateFormat.parse(search_field.getText());
                                    } catch (ParseException e) {
                                        ErrorWindow.showError(errorText);
                                    }
                                    Timestamp timestamp = new Timestamp(date.getTime());
                                    query.setParameter("n", timestamp);
                                    ObservableList<Client> list = FXCollections.observableArrayList(query.getResultList());
                                    content_in_table.setItems(list);
                                }catch (NullPointerException e){
                                    ErrorWindow.showError(errorText);
                                }

                            }

                    );
                }

        );
    }

    @FXML
    void goback(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/hello-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage currentStage = (Stage) content_in_table.getScene().getWindow();

            currentStage.setScene(scene);

            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorText.setVisible(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
}

