package com.tamaraw.payroll.controllers;

import com.tamaraw.payroll.HelloApplication;
import com.tamaraw.payroll.daos.DeductionTypeDAO;
import com.tamaraw.payroll.models.DeductionTypeDto;
import com.tamaraw.payroll.utils.SceneLoader;
import com.tamaraw.payroll.utils.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddDeductionTypeController {

    @FXML
    private TextField type;

    @FXML
    public void onCancelBtnClick(ActionEvent event) throws IOException {
        SceneLoader.loadScene((Stage) ((Node) event.getSource()).getScene().getWindow(), Scenes.DEDUCTION_TYPES);
    }

    @FXML
    public void onSaveClick(ActionEvent event) throws IOException {
        DeductionTypeDto dto = new DeductionTypeDto(null, this.type.getText());
        DeductionTypeDAO.create(dto);

        SceneLoader.loadScene((Stage) ((Node) event.getSource()).getScene().getWindow(), Scenes.DEDUCTION_TYPES);
    }

}
