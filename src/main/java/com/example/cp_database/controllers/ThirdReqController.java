package com.example.cp_database.controllers;

import com.example.cp_database.HibernateSession;
import com.example.cp_database.entities.Supplier;
import jakarta.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.application.Platform;
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

public class ThirdReqController implements Initializable {

    @FXML
    private TableColumn<Answer, String> addressNumber;

    @FXML
    private TableView<Answer> content_in_table;

    @FXML
    private TableColumn<Answer, String> nameColumn;

    @FXML
    private TableColumn<Answer, String> numberColumn;

    @FXML
    private TextField text_fiald;

    protected class Answer{
        private String name;
        private String contactNumber;
        private String address;

        public Answer(String name, String contactNumber,String address) {
            this.address = address;
            this.contactNumber = contactNumber;
            this.name = name;
        }
    }

    @FXML
    void dosearch(ActionEvent event) {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                Query query = session.createQuery(
                        "SELECT s.name, s.contactNumber, w.address FROM Supplier s JOIN Warehouse w ON s.warehouse.id = w.id WHERE w.capacity >:n");
                query.setParameter("n", Integer.parseInt(text_fiald.getText()));
                List<Answer>list=new ArrayList<>(3);
                for (Object o: query.getResultList()){
                    Object[]answers=(Object[]) o;
                    list.add(new Answer((String)answers[0],(String)answers[1],(String)answers[2]));
                }
                ObservableList<Answer> anslist = FXCollections.observableArrayList(list);
                content_in_table.setItems(anslist);
            });
        });
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
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        addressNumber.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
}

