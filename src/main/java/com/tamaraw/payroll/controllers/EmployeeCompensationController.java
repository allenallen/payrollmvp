package com.tamaraw.payroll.controllers;

import com.tamaraw.payroll.HelloApplication;
import com.tamaraw.payroll.daos.EmployeeCompensationDAO;
import com.tamaraw.payroll.daos.EmployeeDAO;
import com.tamaraw.payroll.models.Employee;
import com.tamaraw.payroll.models.EmployeeCompensation;
import com.tamaraw.payroll.models.EmployeeCompensationDto;
import com.tamaraw.payroll.utils.Notification;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeCompensationController implements Initializable {

    @FXML
    private TableView<Employee> tableViewEmployees;

    @FXML
    private TableColumn<Employee, Integer> tableColumnEmployeeId;

    @FXML
    private TableColumn<Employee, String> tableColumnName;

    @FXML
    private TableColumn<Employee, Double> tableColumnDaily;

    @FXML
    private TableColumn<Employee, Boolean> tableColumnSss;

    @FXML
    private TableColumn<Employee, Boolean> tableColumnPagibig;

    @FXML
    private TableColumn<Employee, Boolean> tableColumnPhilHealth;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Employee> employees = EmployeeDAO.getAll();
        ObservableList<EmployeeCompensation> employeeCompensations = EmployeeCompensationDAO.getAll();
        employees.forEach(employee -> {
            Optional<EmployeeCompensation> employeeCompensation = employeeCompensations.stream()
                    .filter(e -> e.getEmployeeId().get() == employee.getId().get())
                    .findFirst();
            employeeCompensation.ifPresent(employee::setEmployeeCompensation);
        });

        this.tableColumnEmployeeId.setCellValueFactory(d -> d.getValue().getId().asObject());
        this.tableColumnName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getLastName().getValue() + ", " +
                d.getValue().getFirstName().getValue()));

        Callback<TableColumn<Employee, Double>, TableCell<Employee, Double>> dailyCallBack = new Callback<TableColumn<Employee, Double>, TableCell<Employee, Double>>() {
            @Override
            public TableCell<Employee, Double> call(TableColumn<Employee, Double> employeeDoubleTableColumn) {
                final TableCell<Employee, Double> cell = new TableCell<>() {

                    final TextField textField = new TextField();

                    @Override
                    protected void updateItem(Double aDouble, boolean b) {
                        super.updateItem(aDouble, b);
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            Employee employee = getTableView().getItems().get(getIndex());
                            textField.setText(String.valueOf(employee.getEmployeeCompensation().getValue()
                                    .getDaily().getValue()));
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
                        Employee employee = getTableRow().getItem();
                        employee.getEmployeeCompensation().getValue().setDaily(newValue);
                        updateEmployee(employee);
                        Notification.toast("Saved!", getTableView());
                    }
                };

                return cell;
            }
        };
        this.tableColumnDaily.setCellValueFactory(d -> d.getValue().getEmployeeCompensation().getValue().getDaily().asObject());
        this.tableColumnDaily.setCellFactory(dailyCallBack);
        this.tableColumnDaily.setEditable(true);

        Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>> sssCallBack = new Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>>() {
            @Override
            public TableCell<Employee, Boolean> call(TableColumn<Employee, Boolean> employeeBooleanTableColumn) {
                final TableCell<Employee, Boolean> cell = new TableCell<>() {

                    final CheckBox checkBox = new CheckBox("SSS");

                    @Override
                    protected void updateItem(Boolean aBoolean, boolean b) {
                        super.updateItem(aBoolean, b);
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            Employee employee = getTableView().getItems().get(getIndex());
                            checkBox.setSelected(employee.getEmployeeCompensation().getValue().getSss().getValue());
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
                        Employee employee = getTableRow().getItem();
                        employee.getEmployeeCompensation().getValue().setSss(newValue);
                        updateEmployee(employee);
                        Notification.toast("Saved!", getTableView());
                    }
                };

                return cell;
            }
        };
        Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>> pagibigCallBack = new Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>>() {
            @Override
            public TableCell<Employee, Boolean> call(TableColumn<Employee, Boolean> employeeBooleanTableColumn) {
                final TableCell<Employee, Boolean> cell = new TableCell<>() {

                    final CheckBox checkBox = new CheckBox("PagIBIG");

                    @Override
                    protected void updateItem(Boolean aBoolean, boolean b) {
                        super.updateItem(aBoolean, b);
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            Employee employee = getTableView().getItems().get(getIndex());
                            checkBox.setSelected(employee.getEmployeeCompensation().getValue().getPagibig().getValue());
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
                        Employee employee = getTableRow().getItem();
                        employee.getEmployeeCompensation().getValue().setPagibig(newValue);
                        updateEmployee(employee);
                        Notification.toast("Saved!", getTableView());
                    }
                };

                return cell;
            }
        };
        Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>> philHealthCallBack = new Callback<TableColumn<Employee, Boolean>, TableCell<Employee, Boolean>>() {
            @Override
            public TableCell<Employee, Boolean> call(TableColumn<Employee, Boolean> employeeBooleanTableColumn) {
                final TableCell<Employee, Boolean> cell = new TableCell<>() {

                    final CheckBox checkBox = new CheckBox("PhilHealth");

                    @Override
                    protected void updateItem(Boolean aBoolean, boolean b) {
                        super.updateItem(aBoolean, b);
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            Employee employee = getTableView().getItems().get(getIndex());
                            checkBox.setSelected(employee.getEmployeeCompensation().getValue().getPhilHealth().getValue());
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
                        Employee employee = getTableRow().getItem();
                        employee.getEmployeeCompensation().getValue().setPhilHealth(newValue);
                        updateEmployee(employee);
                        Notification.toast("Saved!", getTableView());
                    }
                };

                return cell;
            }
        };

        this.tableColumnSss.setCellFactory(sssCallBack);
        this.tableColumnSss.setEditable(true);
        this.tableColumnPagibig.setCellFactory(pagibigCallBack);
        this.tableColumnPagibig.setEditable(true);
        this.tableColumnPhilHealth.setCellFactory(philHealthCallBack);
        this.tableColumnPhilHealth.setEditable(true);
        this.tableViewEmployees.setItems(employees);
    }

    @FXML
    public void onMenuItemCloseClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/employees.fxml"));
        Stage mainStage = (Stage) this.tableViewEmployees.getScene().getWindow();
        mainStage.setTitle("Employees");
        mainStage.setScene(new Scene(fxmlLoader.load()));
    }

    private void updateEmployee(Employee employee) {
        EmployeeCompensationDto dto = new EmployeeCompensationDto(
                employee.getId().getValue(), employee.getEmployeeCompensation().getValue().getDaily().getValue(),
                employee.getEmployeeCompensation().getValue().getSss().getValue(),
                employee.getEmployeeCompensation().getValue().getPhilHealth().getValue(),
                employee.getEmployeeCompensation().getValue().getPagibig().getValue()
        );
        EmployeeCompensationDAO.update(dto);
    }
}
