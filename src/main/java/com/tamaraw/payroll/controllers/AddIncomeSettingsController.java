package com.tamaraw.payroll.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.IncomeSettings;
import com.tamaraw.payroll.models.IncomeSettingsDto;
import com.tamaraw.payroll.services.IncomeSettingsService;
import com.tamaraw.payroll.utils.IDHolder;
import com.tamaraw.payroll.utils.Notification;
import com.tamaraw.payroll.utils.SceneLoader;
import com.tamaraw.payroll.utils.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class AddIncomeSettingsController implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private ToggleButton fixed;

    @FXML
    private TextField value;

    @FXML
    private Label valueLabel;

    @FXML
    private Label percentageLabel;

    private IncomeSettingsService incomeSettingsService;

    private Long id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.incomeSettingsService = new IncomeSettingsService();
        this.id = IDHolder.getInstance().getId();
        if (id > 0) {
            try {
                IncomeSettings incomeSettings = this.incomeSettingsService.getOne(id);
                this.name.setText(incomeSettings.getName().getValue());
                this.fixed.setSelected(incomeSettings.getFixed().getValue());
                if (incomeSettings.getFixed().getValue()) {
                    this.fixed.setText("Fixed");
                    this.value.setVisible(false);
                    this.valueLabel.setVisible(false);
                    this.percentageLabel.setVisible(false);
                } else {
                    this.value.setVisible(true);
                    this.value.setText(incomeSettings.getValue().getValue().toString());
                    this.percentageLabel.setVisible(true);
                }
            } catch (UnirestException e) {
                throw new RuntimeException(e);
            }
        }

        this.fixed.setOnMouseClicked(event -> {
            if (this.fixed.isSelected()) {
                this.fixed.setText("Fixed");
                this.valueLabel.setVisible(false);
                this.value.setVisible(false);
                this.percentageLabel.setVisible(false);
            } else {
                this.fixed.setText("Percentage");
                this.valueLabel.setVisible(true);
                this.value.setVisible(true);
                this.percentageLabel.setVisible(true);
            }
        });
    }

    @FXML
    public void onCancelBtnClick(ActionEvent event) throws IOException {
        SceneLoader.loadScene((Stage) ((Node) event.getSource()).getScene().getWindow(), Scenes.INCOME_SETTINGS);
    }

    @FXML
    public void onSaveClick(ActionEvent event) throws IOException {
        IncomeSettingsDto dto = new IncomeSettingsDto(
                id,
                this.name.getText(),
                true,
                this.fixed.isSelected(),
                this.fixed.isSelected() ? null : new BigDecimal(this.value.getText())
        );
        try {
            if (id < 0) {
                this.incomeSettingsService.create(dto);
            } else {
                this.incomeSettingsService.update(dto, id);
            }
            SceneLoader.loadScene((Stage) ((Node) event.getSource()).getScene().getWindow(), Scenes.INCOME_SETTINGS);
        } catch (UnirestException e) {
            Notification.toast(e.getMessage(), ((Node) event.getSource()).getScene().getWindow());
        }
    }
}
