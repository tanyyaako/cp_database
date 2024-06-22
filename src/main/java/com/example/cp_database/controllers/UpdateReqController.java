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

public class UpdateReqController implements Initializable {

    @FXML
    private Text errorText;

    @FXML
    private TableView<Client> content_in_table;
    @FXML
    private TextField idField;
    @FXML
    private TextField mailField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField numberField;
    @FXML
    private TextField surnameField;

    @FXML
    private TableColumn<Client, Long> idSotColumn;

    @FXML
    private TableColumn<Client, Integer> mailColumn;

    @FXML
    private TableColumn<Client, Integer> nameColumn;

    @FXML
    private TableColumn<Client, Integer> numberColumn;

    @FXML
    private TableColumn<Client, Integer> surnameColumn;
    @FXML
    private TableColumn<Client, Long> idColumn;

    @FXML
    void doUpdate(ActionEvent event) {
        Platform.runLater(() -> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                try {
                    Client client = session.get(Client.class, Integer.parseInt(idField.getText()));
                    if (client != null) {
                        if (!nameField.getText().isEmpty()) client.setFirstName(nameField.getText());
                        if (!surnameField.getText().isEmpty()) client.setLastName(surnameField.getText());
                        if (!numberField.getText().isEmpty()) client.setContactNumber(numberField.getText());
                        if (!mailField.getText().isEmpty()) client.setEmail(mailField.getText());
                        session.save(client);
                        showInfo();
                    }
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

    private void showInfo(){
        Platform.runLater(() -> {
                    HibernateSession.sessionFactory().inTransaction(session -> {
                                Query query = session.createQuery(
                                        "FROM Client ", Client.class
                                );
                                ObservableList<Client> list = FXCollections.observableArrayList(query.getResultList());
                                content_in_table.setItems(list);
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

