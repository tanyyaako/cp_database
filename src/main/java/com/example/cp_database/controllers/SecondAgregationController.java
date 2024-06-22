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

public class SecondAgregationController implements Initializable {

    @FXML
    private TableColumn<Answer, Double> avgColumn;

    @FXML
    private TableView<Answer> content_in_table;

    @FXML
    private TableColumn<Answer, String> typeColumn;

    @FXML
    private TextField typeField;

    @FXML
    void doSearch(ActionEvent event) {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                Query query = session.createQuery(
                        "SELECT p.productType, AVG(p.pricePerUnit) " +
                                "FROM Product p " +
                                "WHERE p.productType =:n " +
                                "GROUP BY p.productType ");
                query.setParameter("n", typeField.getText());
                List<Answer> list=new ArrayList<>(2);
                for (Object o: query.getResultList()){
                    Object[]answers=(Object[]) o;
                    list.add(new Answer((Double) answers[1],(String) answers[0]));
                }
                ObservableList<Answer> anslist = FXCollections.observableArrayList(list);
                content_in_table.setItems(anslist);
            });
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        avgColumn.setCellValueFactory(new PropertyValueFactory<>("avg"));
    }

    protected class Answer{
        public String type;
        public Double avg;

        public Answer(Double avg, String type) {
            this.avg = avg;
            this.type = type;
        }

        public Double getAvg() {
            return avg;
        }

        public void setAvg(Double avg) {
            this.avg = avg;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    @FXML
    void goBack(ActionEvent event) {
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

}

