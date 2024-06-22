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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteReqController implements Initializable {

    @FXML
    private Text errorText;

    @FXML
    private TableView<Client> content_in_field;

    @FXML
    private TableColumn<Client, Long> idColumn;

    @FXML
    private TextField idField;

    @FXML
    private TableColumn<Client, Long> idSotColumn;

    @FXML
    private TableColumn<Client, String> mailColumn;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<Client, String> numberColumn;

    @FXML
    private TableColumn<Client, String> surnameColumn;

    @FXML
    void doDelete(ActionEvent event) {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                try {
                    Client client = session.get(Client.class, Integer.parseInt(idField.getText()));
                    if (client != null) {
                        session.delete(client);
                    }
                }catch (NumberFormatException e){
                    ErrorWindow.showError(errorText);
                }
            });
        });
        showInfo();
    }

    @FXML
    void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/hello-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage currentStage = (Stage) content_in_field.getScene().getWindow();

            currentStage.setScene(scene);

            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showInfo(){
        Platform.runLater(() -> {
                    HibernateSession.sessionFactory().inTransaction(session -> {
                                Query query = session.createQuery(
                                        "FROM Client ", Client.class
                                );
                                ObservableList<Client> list = FXCollections.observableArrayList(query.getResultList());
                                content_in_field.setItems(list);
                            }

                    );
                }

        );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorText.setVisible(false);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        idSotColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        showInfo();
    }
}

