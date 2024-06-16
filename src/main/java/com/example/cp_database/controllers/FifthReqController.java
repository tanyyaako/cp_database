package com.example.cp_database.controllers;

import com.example.cp_database.HibernateSession;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FifthReqController implements Initializable {

    @FXML
    private TableColumn<Answer, String> addressDosColumn;

    @FXML
    private TableView<Answer> conten_in_table;

    @FXML
    private TableColumn<Answer, Long> idDosColumn;

    @FXML
    private TableColumn<Answer, String> nameKliColumn;

    @FXML
    private TableColumn<Answer, String> namePosColumn;

    @FXML
    private TextField search_field;

    @FXML
    private TableColumn<Answer, String> surnameKliColumn;

    protected class Answer{
        private Long id;
        private String deliveryAddress;
        private String firstName;
        private String lastName;
        private String name;

        public Answer(Long id, String deliveryAddress, String firstName, String lastName, String name) {
            this.id = id;
            this.deliveryAddress = deliveryAddress;
            this.firstName = firstName;
            this.lastName = lastName;
            this.name = name;
        }

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @FXML
    void dosearch(ActionEvent event) {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                Query query = session.createQuery(
                        "SELECT d.id, d.deliveryAddress, c.firstName, c.lastName, s.name FROM Delivery d JOIN Client c ON d.client.id = c.id JOIN Supplier s ON d.supplier.id = s.id WHERE d.supplier.id IN (SELECT id FROM Supplier WHERE name =:n)");
                query.setParameter("n", search_field.getText());
                List<Answer> list=new ArrayList<>(5);
                for (Object o: query.getResultList()){
                    Object[]answers=(Object[]) o;
                    list.add(new Answer((Long) answers[0],(String)answers[1],(String)answers[2],(String)answers[3],(String)answers[4]));
                }
                ObservableList<Answer> anslist = FXCollections.observableArrayList(list);
                conten_in_table.setItems(anslist);
            });
        });
    }

    @FXML
    void goback(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/hello-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage currentStage = (Stage) conten_in_table.getScene().getWindow();

            currentStage.setScene(scene);

            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idDosColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addressDosColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryAddress"));
        nameKliColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        surnameKliColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        namePosColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
}

