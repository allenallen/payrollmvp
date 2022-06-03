package com.tamaraw.payroll.controllers;

import com.tamaraw.payroll.utils.SceneLoader;
import com.tamaraw.payroll.utils.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {

    @FXML
    public void onDeductionTypesClicked(ActionEvent event) throws IOException {
        SceneLoader.loadScene((Stage) ((Node) event.getSource()).getScene().getWindow(), Scenes.DEDUCTION_TYPES);
    }

    @FXML
    public void onIncomeSettingsClicked(ActionEvent event) throws IOException {
        SceneLoader.loadScene((Stage) ((Node) event.getSource()).getScene().getWindow(), Scenes.INCOME_SETTINGS);
    }

    @FXML
    public void onExitBtnClicked(ActionEvent event) throws IOException {
        SceneLoader.loadScene((Stage) ((Node) event.getSource()).getScene().getWindow(), Scenes.MAIN);
    }
}
