package com.example.cp_database.controllers;

import com.example.cp_database.ErrorWindow;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FirstAgregationController implements Initializable {

    @FXML
    private Text errorText;

    @FXML
    private TableView<Answer> content_in_table;



    @FXML
    private TextField idField;

    @FXML
    private TableColumn<Answer, String> nameColumn;

    @FXML
    private TableColumn<Answer, String> surnameColumn;

    @FXML
    void doSearch(ActionEvent event) {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                try{
                    Query query = session.createQuery(
                            "SELECT o.client.id, COUNT(*)  " +
                                    "FROM Orders o" +
                                    " GROUP BY o.client.id ");
                    List<Answer> list=new ArrayList<>(2);
                    for (Object o: query.getResultList()){
                        Object[]answers=(Object[]) o;
                        list.add(new Answer((Long) answers[0],(Long) answers[1]));
                    }
                    ObservableList<Answer> anslist = FXCollections.observableArrayList(list);
                    content_in_table.setItems(anslist);
                }catch (NumberFormatException e){
                    ErrorWindow.showError(errorText);
                }
            });
        });
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

    protected class Answer{
        private Long id;
        private Long count;

        public Answer(Long count, Long id) {
            this.count = count;
            this.id = id;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorText.setVisible(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
    }
}

