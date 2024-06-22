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
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/add_request.fxml"));
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
        void doDeleteAction(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/delete_request.fxml"));
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
        void doFifthAction(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/fifth_request.fxml"));
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
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/First_agregation.fxml"));
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
        void doFourthAction(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/fourth_request.fxml"));
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
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/Second_agregation.fxml"));
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
        void doThirdAction(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/third_request.fxml"));
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
        void doThird_thirdAction(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/Third_agregation.fxml"));
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
        void doUpdateAction(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cp_database/update_request.fxml"));
                        Parent root = loader.load();

                        Scene scene = new Scene(root);

                        Stage currentStage = (Stage) firstReq.getScene().getWindow();

                        currentStage.setScene(scene);

                        currentStage.show();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

}


