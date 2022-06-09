package com.tamaraw.payroll.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.Employee;
import com.tamaraw.payroll.models.EmployeeDeductionsTotal;
import com.tamaraw.payroll.models.EmployeeDeductionsTotalDto;
import com.tamaraw.payroll.services.EmployeeDeductionsService;
import com.tamaraw.payroll.services.EmployeeService;
import com.tamaraw.payroll.utils.Notification;
import com.tamaraw.payroll.utils.SceneLoader;
import com.tamaraw.payroll.utils.Scenes;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeesController implements Initializable {
    @FXML
    private TableView<Employee> tableViewEmployees;

    @FXML
    private TableColumn<Employee, String> tableColumnFirstName;

    @FXML
    private TableColumn<Employee, String> tableColumnLastName;

    @FXML
    private TableColumn<Employee, Long> tableColumnId;

    @FXML
    private TableColumn<Employee, Integer> tableColumnEmployeeNumber;

    @FXML
    private TableColumn<Employee, EmployeeDeductionsTotal> tableColumnDeductions;

    @FXML
    private TableColumn<Employee, Long> tableColumnEditBtn;

    @FXML
    private TableColumn<Employee, Long> tableColumnDeleteBtn;

    @FXML
    private MenuItem queryAllMenu;

    private EmployeeService employeeService;

    private EmployeeDeductionsService employeeDeductionsService;

    private boolean queryAll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.employeeService = new EmployeeService();
        this.employeeDeductionsService = new EmployeeDeductionsService();
        this.queryAll = false;
        this.queryAllMenu.setText("Show all employees");
        try {
            initializeTable();
        } catch (UnirestException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onCloseMenuItemClicked(ActionEvent event) throws IOException {
        SceneLoader.loadScene((Stage) this.tableViewEmployees.getScene().getWindow(), Scenes.MAIN);
    }

    @FXML
    public void onAddEmployeeMenuItemClicked() throws IOException {
        SceneLoader.loadSceneWithId((Stage) this.tableViewEmployees.getScene().getWindow(), Scenes.ADD_EMPLOYEES, "create");
    }

    @FXML
    public void onQueryAllMenuClicked() throws UnirestException {
        this.queryAll = !this.queryAll;
        if (this.queryAll) {
            this.queryAllMenu.setText("Show all employees");
        } else {
            this.queryAllMenu.setText("Hide inactive employees");
        }
        initializeTable();
    }

    @FXML
    public void onCompensationSettingsClicked() throws IOException {
        SceneLoader.loadScene((Stage) this.tableViewEmployees.getScene().getWindow(), Scenes.EMPLOYEE_COMPENSATION);
    }

    private void initializeTable() throws UnirestException {
        ObservableList<Employee> employees;
        employees = employeeService.getEmployees(this.queryAll);
        List<EmployeeDeductionsTotalDto> employeeDeductionsTotal = employeeDeductionsService.getAllDeductions();
        mapDeductions(employees, employeeDeductionsTotal);

        this.tableColumnId.setCellValueFactory(d -> d.getValue().getId().asObject());
        this.tableColumnEmployeeNumber.setCellValueFactory(d -> d.getValue().getEmployeeNumber().asObject());
        this.tableColumnFirstName.setCellValueFactory(d -> d.getValue().getFirstName());
        this.tableColumnLastName.setCellValueFactory(d -> d.getValue().getLastName());
        this.tableColumnDeductions.setCellValueFactory(d -> d.getValue().getEmployeeDeductions());
        this.tableColumnEditBtn.setCellValueFactory(d -> d.getValue().getId().asObject());
        this.tableColumnDeleteBtn.setCellValueFactory(d -> d.getValue().getId().asObject());

        Callback<TableColumn<Employee, EmployeeDeductionsTotal>, TableCell<Employee, EmployeeDeductionsTotal>> deductionsCallBack = new Callback<>() {
            @Override
            public TableCell<Employee, EmployeeDeductionsTotal> call(TableColumn<Employee, EmployeeDeductionsTotal> param) {
                return new TableCell<>() {
                    final VBox vBox = new VBox();
                }
            }
        };

        Callback<TableColumn<Employee, Long>, TableCell<Employee, Long>> editCallBack = new Callback<>() {
            @Override
            public TableCell<Employee, Long> call(TableColumn<Employee, Long> employeeIntegerTableColumn) {
                final TableCell<Employee, Long> cell = new TableCell<>() {
                    final Button btn = new Button("Edit");

                    @Override
                    protected void updateItem(Long integer, boolean b) {
                        super.updateItem(integer, b);
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Employee employee = getTableView().getItems().get(getIndex());
                                try {
                                    SceneLoader.loadSceneWithId((Stage) ((Node) event.getSource()).getScene().getWindow(),
                                            Scenes.ADD_EMPLOYEES, employee.getId().getValue());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
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

        Callback<TableColumn<Employee, Long>, TableCell<Employee, Long>> deleteCallBack = new Callback<>() {
            @Override
            public TableCell<Employee, Long> call(TableColumn<Employee, Long> employeeIntegerTableColumn) {
                return new TableCell<>() {
                    final Button btn = new Button("Delete");

                    @Override
                    protected void updateItem(Long integer, boolean b) {
                        super.updateItem(integer, b);
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            Employee employee = getTableView().getItems().get(getIndex());
                            String buttonName = employee.getActive().getValue() ? "Deactivate" : "Activate";
                            btn.setText(buttonName);
                            btn.setOnAction(event -> {
                                Optional<ButtonType> result = Notification.callAlert(Alert.AlertType.CONFIRMATION,
                                        "Are you sure you want to " + buttonName + " employee?");
                                if (result.isPresent()) {
                                    if (result.get().equals(ButtonType.OK)) {
                                        try {
                                            employeeService.delete((long) employee.getId().getValue());
                                            Notification.toast("Successful", this.getTableView());
                                        } catch (UnirestException e) {
                                            Notification.toast(e.getMessage(), this.getTableView());
                                        }
                                        try {
                                            initializeTable();
                                        } catch (UnirestException e) {
                                            e.printStackTrace();
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }
                            });
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        this.tableColumnEditBtn.setCellFactory(editCallBack);
        this.tableColumnDeleteBtn.setCellFactory(deleteCallBack);

        this.tableViewEmployees.setItems(employees);
    }

    private void mapDeductions(ObservableList<Employee> employees, List<EmployeeDeductionsTotalDto> employeeDeductionsTotal) {
        employees.forEach(employee -> {
            employeeDeductionsTotal.stream().filter(employeeDeductionsTotalDto -> employeeDeductionsTotalDto.getEmployee().getId() ==
                    employee.getId().getValue()).findFirst().ifPresent(employeeDeductionsTotalDto -> {
                        employee.setEmployeeDeductions(new EmployeeDeductionsTotal(employeeDeductionsTotalDto.getTotal().doubleValue()));
            });
        });
    }
}
