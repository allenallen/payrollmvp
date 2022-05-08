package com.tamaraw.payroll.controllers;

import com.tamaraw.payroll.HelloApplication;
import com.tamaraw.payroll.utils.SceneLoader;
import com.tamaraw.payroll.utils.Scenes;
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
        SceneLoader.loadScene((Stage) ((Node)event.getSource()).getScene().getWindow(), Scenes.EMPLOYEES);
    }

    @FXML
    public void onSettingsBtnClicked(ActionEvent event) throws IOException {
        SceneLoader.loadScene((Stage) ((Node)event.getSource()).getScene().getWindow(), Scenes.SETTINGS);
    }
}
