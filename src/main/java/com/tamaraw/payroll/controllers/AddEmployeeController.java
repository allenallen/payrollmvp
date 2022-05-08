package com.tamaraw.payroll.controllers;

import com.tamaraw.payroll.HelloApplication;
import com.tamaraw.payroll.daos.EmployeeDAO;
import com.tamaraw.payroll.models.Employee;
import com.tamaraw.payroll.models.EmployeeDto;
import com.tamaraw.payroll.utils.DBUtil;
import com.tamaraw.payroll.utils.SceneLoader;
import com.tamaraw.payroll.utils.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {

    @FXML
    private Label employeeIdLabel;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField employeeNumber;

    @FXML
    private TextField contactNumber;

    @FXML
    private TextField address;

    @FXML
    private DatePicker birthday;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String id = employeeIdLabel.getText();
        if (!id.equals("create")) {
            Employee employee = EmployeeDAO.getOne(id);
            this.firstName.setText(employee.getFirstName().getValue());
            this.lastName.setText(employee.getLastName().getValue());
            this.employeeNumber.setText(String.valueOf(employee.getEmployeeNumber().getValue()));
            this.contactNumber.setText(employee.getContactNumber().getValue());
            this.address.setText(employee.getAddress().getValue());
            this.birthday.getEditor().setText(employee.getBirthday().getValue());
        }
    }

    @FXML
    public void onCancelBtnClick(ActionEvent event) throws IOException {
        SceneLoader.loadScene((Stage) ((Node) event.getSource()).getScene().getWindow(), Scenes.EMPLOYEES);
    }

    @FXML
    public void onSaveClick(ActionEvent event) throws IOException {
        String employeeId = this.employeeIdLabel.getText();
        EmployeeDto dto = new EmployeeDto(
                this.firstName.getText(),
                this.lastName.getText(),
                Integer.parseInt(this.employeeNumber.getText()),
                this.address.getText(),
                this.contactNumber.getText(),
                this.birthday.getEditor().getText(),
                employeeId.equals("create") ? 0 : Integer.parseInt(employeeId)
        );
        try {
            if (employeeId.equals("create")) {
                EmployeeDAO.create(dto);
            } else {
                EmployeeDAO.update(dto);
            }
            SceneLoader.loadScene((Stage) ((Node) event.getSource()).getScene().getWindow(), Scenes.EMPLOYEES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
