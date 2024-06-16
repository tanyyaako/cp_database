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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FourthReqController implements Initializable {

    @FXML
    private TableColumn<Answer, Integer> addressColumn;

    @FXML
    private TableView<Answer> conten_in_field;

    @FXML
    private TableColumn<Answer, Integer> nameColumn;

    @FXML
    private TextField search_field;

    @FXML
    private TableColumn<Answer, Integer> typeColumn;

    protected class Answer{
        private String name;
        private String productType;
        private String address;

        public Answer(String name, String productType,String address) {
            this.address = address;
            this.productType = productType;
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public String getName() {
            return name;
        }

        public String getProductType() {
            return productType;
        }
    }
    @FXML
    void dosearch(ActionEvent event) {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                Query query = session.createQuery(
                        "SELECT pr.name, pr.productType, w.address FROM Product pr JOIN Warehouse w ON pr.warehouse.id = w.id JOIN Supplier s ON w.id = s.warehouse.id WHERE s.name=:n");
                query.setParameter("n", search_field.getText());
                List<Answer> list=new ArrayList<>(3);
                for (Object o: query.getResultList()){
                    Object[]answers=(Object[]) o;
                    list.add(new Answer((String)answers[0],(String)answers[1],(String)answers[2]));
                }
                ObservableList<Answer> anslist = FXCollections.observableArrayList(list);
                conten_in_field.setItems(anslist);
            });
        });
    }

    @FXML
    void goback(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/hello-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage currentStage = (Stage) conten_in_field.getScene().getWindow();

            currentStage.setScene(scene);

            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("productType"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
}
