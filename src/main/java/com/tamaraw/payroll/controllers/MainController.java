package com.tamaraw.payroll.controllers;

import com.tamaraw.payroll.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    public void onEmployeesBtnClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/employees.fxml"));
        Stage mainStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        mainStage.setTitle("Employees");
        mainStage.setScene(new Scene(fxmlLoader.load()));
    }

}
