package com.tamaraw.payroll.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.DeductionTypeDto;
import com.tamaraw.payroll.services.DeductionTypeService;
import com.tamaraw.payroll.utils.Notification;
import com.tamaraw.payroll.utils.SceneLoader;
import com.tamaraw.payroll.utils.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddDeductionTypeController implements Initializable {

    @FXML
    private TextField type;

    private DeductionTypeService deductionTypeService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.deductionTypeService = new DeductionTypeService();
    }

    @FXML
    public void onCancelBtnClick(ActionEvent event) throws IOException {
        SceneLoader.loadScene((Stage) ((Node) event.getSource()).getScene().getWindow(), Scenes.DEDUCTION_TYPES);
    }

    @FXML
    public void onSaveClick(ActionEvent event) throws IOException {
        DeductionTypeDto dto = new DeductionTypeDto(null, this.type.getText());
        try {
            deductionTypeService.create(dto);
        } catch (UnirestException e) {
            Notification.toast(e.getMessage(), this.type);
        }

        SceneLoader.loadScene((Stage) ((Node) event.getSource()).getScene().getWindow(), Scenes.DEDUCTION_TYPES);
    }

}
