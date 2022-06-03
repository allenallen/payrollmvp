package com.tamaraw.payroll.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.IncomeSettings;
import com.tamaraw.payroll.services.IncomeSettingsService;
import com.tamaraw.payroll.utils.Notification;
import com.tamaraw.payroll.utils.SceneLoader;
import com.tamaraw.payroll.utils.Scenes;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class IncomeSettingsController implements Initializable {

    @FXML
    private TableView<IncomeSettings> tableViewIncomeSettings;

    @FXML
    private TableColumn<IncomeSettings, String> tableColumnIncomeSettingsName;

    @FXML
    private TableColumn<IncomeSettings, Long> tableColumnIncomeSettingsEdit;

    @FXML
    private TableColumn<IncomeSettings, Long> tableColumnIncomeSettingsDelete;

    @FXML
    private MenuItem showAllMenuItem;

    private IncomeSettingsService incomeSettingsService;

    private boolean queryAll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.incomeSettingsService = new IncomeSettingsService();
        this.queryAll = false;
        initializeTable();
    }

    @FXML
    public void onAddIncomeSettingsClicked() throws IOException {
        SceneLoader.loadSceneWithId((Stage) this.tableViewIncomeSettings.getScene().getWindow(), Scenes.ADD_INCOME_SETTINGS,
                "create");
    }

    @FXML
    public void onShowAllClicked() {
        this.queryAll = !this.queryAll;
        if (this.queryAll) {
            this.showAllMenuItem.setText("Hide inactive");
        } else {
            this.showAllMenuItem.setText("Show all");
        }

        initializeTable();
    }

    @FXML
    public void onCloseMenuItemClicked(ActionEvent event) throws IOException {
        SceneLoader.loadScene((Stage) this.tableViewIncomeSettings.getScene().getWindow(), Scenes.MAIN);
    }

    private void initializeTable() {
        ObservableList<IncomeSettings> incomeSettings;
        try {
            incomeSettings = incomeSettingsService.getAll(this.queryAll);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

        this.tableColumnIncomeSettingsName.setCellValueFactory(d -> d.getValue().getName());
        this.tableColumnIncomeSettingsDelete.setCellValueFactory(d -> d.getValue().getId().asObject());
        this.tableColumnIncomeSettingsEdit.setCellValueFactory(d -> d.getValue().getId().asObject());

        Callback<TableColumn<IncomeSettings, Long>, TableCell<IncomeSettings, Long>> editCallback = new Callback<>() {
            @Override
            public TableCell<IncomeSettings, Long> call(TableColumn<IncomeSettings, Long> param) {
                return new TableCell<>() {
                    final Button btn = new Button("Edit");

                    @Override
                    protected void updateItem(Long item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                IncomeSettings settings = getTableView().getItems().get(getIndex());
                                try {
                                    SceneLoader.loadSceneWithId((Stage) ((Node) event.getSource()).getScene().getWindow(),
                                            Scenes.ADD_INCOME_SETTINGS, settings.getId().getValue());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
            }
        };
        Callback<TableColumn<IncomeSettings, Long>, TableCell<IncomeSettings, Long>> deleteCallback = new Callback<>() {
            @Override
            public TableCell<IncomeSettings, Long> call(TableColumn<IncomeSettings, Long> param) {
                return new TableCell<>() {
                    final Button btn = new Button("Delete");

                    @Override
                    protected void updateItem(Long item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            IncomeSettings settings = getTableView().getItems().get(getIndex());
                            String buttonName = settings.getActive().getValue() ? "Deactivate" : "Activate";
                            btn.setText(buttonName);
                            btn.setOnAction(event -> {
                                Optional<ButtonType> result = Notification.callAlert(Alert.AlertType.CONFIRMATION,
                                        "Are you sure you want to " + buttonName + " setting?");
                                if (result.isPresent()) {
                                    if (result.get().equals(ButtonType.OK)) {
                                        try {
                                            incomeSettingsService.delete(settings.getId().get());
                                            Notification.toast("Successful", this.getTableView());
                                        } catch (UnirestException e) {
                                            Notification.toast(e.getMessage(), this.getTableView());
                                        }

                                        initializeTable();
                                    }
                                }
                            });
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        this.tableColumnIncomeSettingsEdit.setCellFactory(editCallback);
        this.tableColumnIncomeSettingsDelete.setCellFactory(deleteCallback);
        this.tableViewIncomeSettings.setItems(incomeSettings);
    }
}
