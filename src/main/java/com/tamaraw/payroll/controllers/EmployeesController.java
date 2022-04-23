package com.tamaraw.payroll.controllers;

import com.tamaraw.payroll.HelloApplication;
import com.tamaraw.payroll.daos.EmployeeDAO;
import com.tamaraw.payroll.models.Employee;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Employee> employees = EmployeeDAO.getAll();
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
                                FXMLLoader loader = new FXMLLoader();
                                loader.getNamespace().put("employeeId", employee.getId().getValue());
                                loader.setLocation(HelloApplication.class.getResource("controllers/addEmployee.fxml"));
                                try {
                                    Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    mainStage.setTitle("Add Employee");
                                    mainStage.setScene(new Scene(loader.load()));
                                    mainStage.show();
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

        Callback<TableColumn<Employee, Integer>, TableCell<Employee, Integer>> deleteCallBack = new Callback<TableColumn<Employee, Integer>, TableCell<Employee, Integer>>() {
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
                                System.out.println("DELETE: " + employee.getId());
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

    @FXML
    public void onCloseMenuItemClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/main.fxml"));
        Stage mainStage = (Stage) this.tableViewEmployees.getScene().getWindow();
        mainStage.setTitle("Main");
        mainStage.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    public void onAddEmployeeMenuItemClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.getNamespace().put("employeeId", "create");
        loader.setLocation(HelloApplication.class.getResource("controllers/addEmployee.fxml"));
        try {
            Stage mainStage = (Stage) this.tableViewEmployees.getScene().getWindow();
            mainStage.setTitle("Add Employee");
            mainStage.setScene(new Scene(loader.load()));
            mainStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
