package com.tamaraw.payroll.controllers;

import com.tamaraw.payroll.daos.DeductionTypeDAO;
import com.tamaraw.payroll.models.DeductionType;
import com.tamaraw.payroll.models.Employee;
import com.tamaraw.payroll.utils.Notification;
import com.tamaraw.payroll.utils.SceneLoader;
import com.tamaraw.payroll.utils.Scenes;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeductionTypeController implements Initializable {

    @FXML
    private TableView<DeductionType> tableViewDeductionTypes;

    @FXML
    private TableColumn<DeductionType, String> tableColumnType;

    @FXML
    private TableColumn<DeductionType, Long> tableColumnDelete;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
    }

    @FXML
    public void onAddMenuItemClicked() throws IOException {
        SceneLoader.loadScene((Stage) this.tableViewDeductionTypes.getScene().getWindow(), Scenes.ADD_DEDUCTION_TYPES);
    }

    @FXML
    public void onExitMenuItemClicked() throws IOException {
        SceneLoader.loadScene((Stage) this.tableViewDeductionTypes.getScene().getWindow(), Scenes.SETTINGS);
    }

    private void initializeTable() {
        ObservableList<DeductionType> deductionTypes = DeductionTypeDAO.getAll();
        this.tableColumnType.setCellValueFactory(d -> d.getValue().getType());
        this.tableColumnDelete.setCellValueFactory(d -> d.getValue().getId().asObject());
        Callback<TableColumn<DeductionType,Long>, TableCell<DeductionType, Long>> deleteCallBack = new Callback<>() {
            @Override
            public TableCell<DeductionType, Long> call(TableColumn<DeductionType, Long> employeeLongTableColumn) {
                final TableCell<DeductionType, Long> cell = new TableCell<>() {
                  final Button btn = new Button("Delete");

                    @Override
                    protected void updateItem(Long aLong, boolean b) {
                        super.updateItem(aLong, b);
                        if (b || deductionTypeGovernment(getTableView().getItems().get(getIndex()))) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                DeductionType deductionType = getTableView().getItems().get(getIndex());
                                Optional<ButtonType> result = Notification.callAlert(Alert.AlertType.CONFIRMATION,
                                        "Are you sure you want to delete deduction type?");
                                if (result.isPresent()) {
                                    if (result.get().equals(ButtonType.OK)) {
                                        DeductionTypeDAO.delete(deductionType.getId().getValue());
                                        initializeTable();
                                    }
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };

                return cell;
            }
        };
        this.tableColumnDelete.setCellFactory(deleteCallBack);
        this.tableViewDeductionTypes.setItems(deductionTypes);
    }

    private boolean deductionTypeGovernment(DeductionType deductionType) {
        return deductionType.getType().getValue().equals("SSS") || deductionType.getType().getValue().equals("PAGIBIG")
                || deductionType.getType().getValue().equals("PHILHEALTH");
    }
}
