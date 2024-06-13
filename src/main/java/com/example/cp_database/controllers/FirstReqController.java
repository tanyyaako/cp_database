package com.example.cp_database.controllers;

import com.example.cp_database.HibernateSession;
import com.example.cp_database.entities.Delivery;
import com.example.cp_database.entities.Supplier;
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
import org.hibernate.query.Query;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class FirstReqController implements Initializable {

    @FXML
    private TextField address_field;

    @FXML
    private TableView<Delivery> content_in_table;

    @FXML
    private TableColumn<Delivery, Date> dataColumn;

    @FXML
    private TableColumn<Delivery, Long> idColumn;

    @FXML
    private TableColumn<Delivery, Long> idDelColumn;
    @FXML

//    void dosearch(ActionEvent event) {
//        Platform.runLater(()-> {
//            HibernateSession.sessionFactory().inTransaction(session -> {
//                        Query query = session.createQuery("SELECT d.id, d.deliveryDate,d.supplier FROM Delivery d WHERE d.deliveryAddress =:n ORDER BY d.deliveryDate",Delivery.class);
//                        query.setParameter("n",address_field.getText());
//                        ObservableList<Delivery> list = FXCollections.observableArrayList(query.getResultList());
//                        content_in_table.setItems(list);
//                    }
//            );
//        });
//    }
    void dosearch(ActionEvent event) {
        Platform.runLater(()-> {
            HibernateSession.sessionFactory().inTransaction(session -> {
                Query<Object[]> query = session.createQuery(
                        "SELECT d.id, d.deliveryDate, d.supplier FROM Delivery d WHERE d.deliveryAddress =:n ORDER BY d.deliveryDate",
                        Object[].class
                );
                query.setParameter("n", address_field.getText());
                List<Object[]> resultList = query.getResultList();
                ObservableList<Delivery> list = FXCollections.observableArrayList();

                for (Object[] row : resultList) {
                    Long id = (Long) row[0];
                    Timestamp deliveryDate = (Timestamp) row[1];
                    Supplier supplier = (Supplier) row[2];

                    Delivery delivery = new Delivery(id, supplier, deliveryDate);


                    list.add(delivery);
                }

                content_in_table.setItems(list);
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
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idDelColumn.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));

    }
}

