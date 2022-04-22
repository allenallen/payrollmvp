package com.tamaraw.payroll.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesController implements Initializable {

    @FXML
    private MenuItem menuItemAdd;

    @FXML
    private MenuItem menuItemClose;

    @FXML
    private TableView tableViewEmployees;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewEmployees = new TableView();

    }
}
