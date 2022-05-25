package com.tamaraw.payroll.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.EmployeeCompensation;
import com.tamaraw.payroll.models.EmployeeCompensationDto;
import com.tamaraw.payroll.services.EmployeeCompensationService;
import com.tamaraw.payroll.utils.Notification;
import com.tamaraw.payroll.utils.SceneLoader;
import com.tamaraw.payroll.utils.Scenes;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeCompensationController implements Initializable {

    @FXML
    private TableView<EmployeeCompensation> tableViewEmployees;

    @FXML
    private TableColumn<EmployeeCompensation, Integer> tableColumnEmployeeId;

    @FXML
    private TableColumn<EmployeeCompensation, String> tableColumnName;

    @FXML
    private TableColumn<EmployeeCompensation, Double> tableColumnDaily;

    @FXML
    private TableColumn<EmployeeCompensation, Boolean> tableColumnSss;

    @FXML
    private TableColumn<EmployeeCompensation, Boolean> tableColumnPagibig;

    @FXML
    private TableColumn<EmployeeCompensation, Boolean> tableColumnPhilHealth;

    private EmployeeCompensationService employeeCompensationService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.employeeCompensationService = new EmployeeCompensationService();
        ObservableList<EmployeeCompensation> employeeCompensations;
        try {
            employeeCompensations = employeeCompensationService.getAll();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

        this.tableColumnEmployeeId.setCellValueFactory(d -> d.getValue().getId().asObject());
        this.tableColumnName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getEmployee().getValue().getLastName().getValue() + ", " + d.getValue().getEmployee().getValue().getFirstName().getValue()));

        Callback<TableColumn<EmployeeCompensation, Double>, TableCell<EmployeeCompensation, Double>> dailyCallBack = new Callback<>() {
            @Override
            public TableCell<EmployeeCompensation, Double> call(TableColumn<EmployeeCompensation, Double> employeeDoubleTableColumn) {

                return new TableCell<>() {

                    final TextField textField = new TextField();

                    @Override
                    protected void updateItem(Double aDouble, boolean b) {
                        super.updateItem(aDouble, b);
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            EmployeeCompensation employee = getTableView().getItems().get(getIndex());
                            textField.setText(String.valueOf(employee.getDaily().getValue()));
                            setGraphic(textField);
                            textField.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
                                if (!newValue) {
                                    commitEdit(Double.valueOf(textField.getText()));
                                }
                            });
                        }
                    }

                    @Override
                    public void commitEdit(Double newValue) {
                        super.commitEdit(newValue);
                        EmployeeCompensation employee = getTableRow().getItem();
                        employee.setDaily(newValue);
                        updateEmployee(employee);
                        Notification.toast("Saved!", getTableView());
                    }
                };
            }
        };
        this.tableColumnDaily.setCellValueFactory(d -> d.getValue().getDaily().asObject());
        this.tableColumnDaily.setCellFactory(dailyCallBack);
        this.tableColumnDaily.setEditable(true);

        Callback<TableColumn<EmployeeCompensation, Boolean>, TableCell<EmployeeCompensation, Boolean>> sssCallBack = new Callback<>() {
            @Override
            public TableCell<EmployeeCompensation, Boolean> call(TableColumn<EmployeeCompensation, Boolean> employeeBooleanTableColumn) {

                return new TableCell<>() {

                    final CheckBox checkBox = new CheckBox();

                    @Override
                    protected void updateItem(Boolean aBoolean, boolean b) {
                        super.updateItem(aBoolean, b);
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            EmployeeCompensation employee = getTableView().getItems().get(getIndex());
                            checkBox.setSelected(employee.getSss().getValue());
                            setGraphic(checkBox);
                            setText(null);
                            checkBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
                                if (!newValue) {
                                    commitEdit(checkBox.isSelected());
                                }
                            });
                        }
                    }

                    @Override
                    public void commitEdit(Boolean newValue) {
                        super.commitEdit(newValue);
                        EmployeeCompensation employee = getTableRow().getItem();
                        employee.setSss(newValue);
                        updateEmployee(employee);
                        Notification.toast("Saved!", getTableView());
                    }
                };
            }
        };
        Callback<TableColumn<EmployeeCompensation, Boolean>, TableCell<EmployeeCompensation, Boolean>> pagibigCallBack = new Callback<>() {
            @Override
            public TableCell<EmployeeCompensation, Boolean> call(TableColumn<EmployeeCompensation, Boolean> employeeBooleanTableColumn) {

                return new TableCell<>() {

                    final CheckBox checkBox = new CheckBox();

                    @Override
                    protected void updateItem(Boolean aBoolean, boolean b) {
                        super.updateItem(aBoolean, b);
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            EmployeeCompensation employee = getTableView().getItems().get(getIndex());
                            checkBox.setSelected(employee.getPagibig().getValue());
                            setGraphic(checkBox);
                            setText(null);
                            checkBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
                                if (!newValue) {
                                    commitEdit(checkBox.isSelected());
                                }
                            });
                        }
                    }

                    @Override
                    public void commitEdit(Boolean newValue) {
                        super.commitEdit(newValue);
                        EmployeeCompensation employee = getTableRow().getItem();
                        employee.setPagibig(newValue);
                        updateEmployee(employee);
                        Notification.toast("Saved!", getTableView());
                    }
                };
            }
        };
        Callback<TableColumn<EmployeeCompensation, Boolean>, TableCell<EmployeeCompensation, Boolean>> philHealthCallBack = new Callback<>() {
            @Override
            public TableCell<EmployeeCompensation, Boolean> call(TableColumn<EmployeeCompensation, Boolean> employeeBooleanTableColumn) {

                return new TableCell<>() {

                    final CheckBox checkBox = new CheckBox();

                    @Override
                    protected void updateItem(Boolean aBoolean, boolean b) {
                        super.updateItem(aBoolean, b);
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            EmployeeCompensation employee = getTableView().getItems().get(getIndex());
                            checkBox.setSelected(employee.getPhilHealth().getValue());
                            setGraphic(checkBox);
                            setText(null);
                            checkBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
                                if (!newValue) {
                                    commitEdit(checkBox.isSelected());
                                }
                            });
                        }
                    }

                    @Override
                    public void commitEdit(Boolean newValue) {
                        super.commitEdit(newValue);
                        EmployeeCompensation employee = getTableRow().getItem();
                        employee.setPhilHealth(newValue);
                        updateEmployee(employee);
                        Notification.toast("Saved!", getTableView());
                    }
                };
            }
        };

        this.tableColumnSss.setCellFactory(sssCallBack);
        this.tableColumnSss.setEditable(true);
        this.tableColumnPagibig.setCellFactory(pagibigCallBack);
        this.tableColumnPagibig.setEditable(true);
        this.tableColumnPhilHealth.setCellFactory(philHealthCallBack);
        this.tableColumnPhilHealth.setEditable(true);
        this.tableViewEmployees.setItems(employeeCompensations);
    }

    @FXML
    public void onMenuItemCloseClick() throws IOException {
        SceneLoader.loadScene((Stage) this.tableViewEmployees.getScene().getWindow(), Scenes.EMPLOYEES);
    }

    private void updateEmployee(EmployeeCompensation employee) {
        EmployeeCompensationDto dto = new EmployeeCompensationDto(employee.getId().getValue(), employee.getDaily().getValue(),
                employee.getSss().getValue(), employee.getPhilHealth().getValue(), employee.getPagibig().getValue(), null);
        try {
            employeeCompensationService.update(dto, (long) dto.getId());
        } catch (UnirestException e) {
            Notification.toast(e.getMessage(), this.tableViewEmployees);
        }
    }
}
