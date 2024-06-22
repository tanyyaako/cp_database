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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ThirdAgregationController implements Initializable {


    @FXML
    private TableView<Answer> content_in_table;

    @FXML
    private TableColumn<Answer, Long> maxColumn;

    @FXML
    private TextField typeField;

    @FXML
    void doSearch(ActionEvent event) {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                Query query = session.createQuery(
                        "SELECT MAX(pr.pricePerUnit) " +
                                "FROM Product pr " +
                                "WHERE EXISTS ( " +
                                "SELECT 1 " +
                                "FROM Supplier s " +
                                "WHERE s.warehouse.id = pr.warehouse.id " +
                                "AND s.name =:n) ");
                query.setParameter("n", typeField.getText());
                List<Answer> list = new ArrayList<>(1);
                Object result = query.getSingleResult();
                if (result != null) {
                    Long maxvalue = (result instanceof Integer) ? Long.valueOf((Integer) result) : (Long) result;
                    list.add(new Answer(maxvalue));
                }
                ObservableList<Answer> anslist = FXCollections.observableArrayList(list);
                content_in_table.setItems(anslist);
            });
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        maxColumn.setCellValueFactory(new PropertyValueFactory<>("maxvalue"));
    }

    protected class Answer{
        public Long maxvalue;

        public Answer(Long maxvalue) {
            this.maxvalue = maxvalue;
        }

        public Long getMaxvalue() {
            return maxvalue;
        }

        public void setMaxvalue(Long maxvalue) {
            this.maxvalue = maxvalue;
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

