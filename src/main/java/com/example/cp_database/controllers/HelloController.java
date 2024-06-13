package com.example.cp_database.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelloController {
        @FXML
        private Button firstReq;
        @FXML
        void doAddAction(ActionEvent event) {

        }

        @FXML
        void doDeleteAction(ActionEvent event) {

        }

        @FXML
        void doFifthAction(ActionEvent event) {

        }

        @FXML
        void doFirstAction(ActionEvent event) {

                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/first_request.fxml"));
                        Parent root = loader.load();

                        Scene scene = new Scene(root);

                        Stage currentStage = (Stage) firstReq.getScene().getWindow();

                        currentStage.setScene(scene);

                        currentStage.show();
                } catch (Exception e) {
                        e.printStackTrace();
                }

        }

        @FXML
        void doFirst_firstAction(ActionEvent event) {

        }

        @FXML
        void doFourthAction(ActionEvent event) {

        }

        @FXML
        void doSecondAction(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/second_request.fxml"));
                        Parent root = loader.load();

                        Scene scene = new Scene(root);

                        Stage currentStage = (Stage) firstReq.getScene().getWindow();

                        currentStage.setScene(scene);

                        currentStage.show();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        @FXML
        void doSecond_secondAction(ActionEvent event) {

        }

        @FXML
        void doThirdAction(ActionEvent event) {

        }

        @FXML
        void doThird_thirdAction(ActionEvent event) {

        }

        @FXML
        void doUpdateAction(ActionEvent event) {

        }

}


