package com.tamaraw.payroll.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.HelloApplication;
import com.tamaraw.payroll.daos.EmployeeDAO;
import com.tamaraw.payroll.models.Employee;
import com.tamaraw.payroll.services.EmployeeService;
import com.tamaraw.payroll.utils.Notification;
import com.tamaraw.payroll.utils.SceneLoader;
import com.tamaraw.payroll.utils.Scenes;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
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
    private TableColumn<Employee, Integer> tableColumnId;

    @FXML
    private TableColumn<Employee, Integer> tableColumnEmployeeNumber;

    @FXML
    private TableColumn<Employee, String> tableColumnAddress;

    @FXML
    private TableColumn<Employee, String> tableColumnContactNumber;

    @FXML
    private TableColumn<Employee, String> tableColumnBirthday;

    @FXML
    private TableColumn<Employee, Integer> tableColumnEditBtn;

    @FXML
    private TableColumn<Employee, Integer> tableColumnDeleteBtn;

    private EmployeeService employeeService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.employeeService = new EmployeeService();
        try {
            initializeTable();
        } catch (UnirestException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onCloseMenuItemClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/main.fxml"));
        Stage mainStage = (Stage) this.tableViewEmployees.getScene().getWindow();
        mainStage.setResizable(false);
        mainStage.setTitle("Main");
        mainStage.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    public void onAddEmployeeMenuItemClicked() throws IOException {
        SceneLoader.loadSceneWithId((Stage) this.tableViewEmployees.getScene().getWindow(), Scenes.ADD_EMPLOYEES, "employeeId", "create");
    }

    @FXML
    public void onCompensationSettingsClicked() throws IOException {
        SceneLoader.loadScene((Stage) this.tableViewEmployees.getScene().getWindow(), Scenes.EMPLOYEE_COMPENSATION);
    }

    private void initializeTable() throws UnirestException {
        ObservableList<Employee> employees = employeeService.getEmployees();
        this.tableColumnId.setCellValueFactory(d -> d.getValue().getId().asObject());
        this.tableColumnEmployeeNumber.setCellValueFactory(d -> d.getValue().getEmployeeNumber().asObject());
        this.tableColumnFirstName.setCellValueFactory(d -> d.getValue().getFirstName());
        this.tableColumnLastName.setCellValueFactory(d -> d.getValue().getLastName());
        this.tableColumnAddress.setCellValueFactory(d -> d.getValue().getAddress());
        this.tableColumnContactNumber.setCellValueFactory(d -> d.getValue().getContactNumber());
        this.tableColumnBirthday.setCellValueFactory(d -> d.getValue().getBirthday());
        this.tableColumnEditBtn.setCellValueFactory(d -> d.getValue().getId().asObject());
        this.tableColumnDeleteBtn.setCellValueFactory(d -> d.getValue().getId().asObject());

        Callback<TableColumn<Employee, Integer>, TableCell<Employee, Integer>> editCallBack = new Callback<TableColumn<Employee, Integer>, TableCell<Employee, Integer>>() {
            @Override
            public TableCell<Employee, Integer> call(TableColumn<Employee, Integer> employeeIntegerTableColumn) {
                final TableCell<Employee, Integer> cell = new TableCell<>() {
                    final Button btn = new Button("Edit");

                    @Override
                    protected void updateItem(Integer integer, boolean b) {
                        super.updateItem(integer, b);
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Employee employee = getTableView().getItems().get(getIndex());
                                try {
                                    SceneLoader.loadSceneWithId((Stage) ((Node) event.getSource()).getScene().getWindow(),
                                            Scenes.ADD_EMPLOYEES, "employeeId", employee.getId().getValue());
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

        Callback<TableColumn<Employee, Integer>, TableCell<Employee, Integer>> deleteCallBack = new Callback<>() {
            @Override
            public TableCell<Employee, Integer> call(TableColumn<Employee, Integer> employeeIntegerTableColumn) {
                final TableCell<Employee, Integer> cell = new TableCell<>() {
                    final Button btn = new Button("Delete");

                    @Override
                    protected void updateItem(Integer integer, boolean b) {
                        super.updateItem(integer, b);
                        if (b) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Employee employee = getTableView().getItems().get(getIndex());
                                Optional<ButtonType> result = Notification.callAlert(Alert.AlertType.CONFIRMATION,
                                        "Are you sure you want to delete employee?");
                                if (result.isPresent()) {
                                    if (result.get().equals(ButtonType.OK)) {
                                        try {
                                            employeeService.delete((long) employee.getId().getValue());
                                            Notification.toast("Successfully deleted", this.getTableView());
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
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        this.tableColumnEditBtn.setCellFactory(editCallBack);
        this.tableColumnDeleteBtn.setCellFactory(deleteCallBack);

        this.tableViewEmployees.setItems(employees);
    }
}
