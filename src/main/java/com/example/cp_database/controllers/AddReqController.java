package com.example.cp_database.controllers;


import com.example.cp_database.ErrorWindow;
import com.example.cp_database.HibernateSession;
import com.example.cp_database.entities.Client;
import com.example.cp_database.entities.Delivery;
import com.example.cp_database.entities.Employee;
import com.example.cp_database.entities.Supplier;
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

public class AddReqController implements Initializable {

    @FXML
    private Text errorText;

    @FXML
    private TextField IDemploeeField;

    @FXML
    private TableColumn<Client, Long> IDsotrudColumn;

    @FXML
    private TableView<Client> content_in_table;

    @FXML
    private TableColumn<Client, String> gmailColumn;

    @FXML
    private TextField gmailField;

    @FXML
    private TableColumn<Client, Long> idColumn;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn<Client, String> numberColumn;

    @FXML
    private TextField numberField;

    @FXML
    private TableColumn<Client, String> surnameColumn;

    @FXML
    private TextField surnameField;

    @FXML
    void doAdd(ActionEvent event) {
        Platform.runLater(() -> {
                    HibernateSession.sessionFactory().inTransaction(session -> {
                                try {
                                    Employee employee = session.get(Employee.class, Long.parseLong(IDemploeeField.getText()));
                                    Client client = new Client();
                                    if (!nameField.getText().isEmpty())client.setFirstName(nameField.getText());
                                    if (!surnameField.getText().isEmpty())client.setLastName(surnameField.getText());
                                    if (!gmailField.getText().isEmpty())client.setEmail(gmailField.getText());
                                    if (!numberField.getText().isEmpty())client.setContactNumber(numberField.getText());
                                    client.setEmployee(employee);
                                    if(!numberField.getText().isEmpty() && !gmailField.getText().isEmpty() && employee!=null)session.save(client);
                                }catch (NumberFormatException e){
                                    ErrorWindow.showError(errorText);
                                }
                            }

                    );
                }

        );
        showInfo();
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

    private void showInfo() {
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
        gmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        IDsotrudColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        showInfo();
    }
}

