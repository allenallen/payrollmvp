package com.tamaraw.payroll.utils;

import com.tamaraw.payroll.HelloApplication;
import com.tamaraw.payroll.controllers.AddEmployeeController;
import com.tamaraw.payroll.controllers.AddIncomeSettingsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class SceneLoader {
    public static void loadScene(Stage stage, Scenes scene) throws IOException {
        FXMLLoader fxmlLoader;
        String title;
        switch (scene) {
            case MAIN:
                fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/main.fxml"));
                title = "Home";
                break;
            case EMPLOYEES:
                fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/employees.fxml"));
                title = "Employees";
                break;
            case ADD_EMPLOYEES:
                fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/addEmployee.fxml"));
                title = "Add Employee";
                break;
            case EMPLOYEE_COMPENSATION:
                fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/employeeCompensation.fxml"));
                title = "Employee Compensation";
                break;
            case SETTINGS:
                fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/settings.fxml"));
                title = "Settings";
                break;
            case DEDUCTION_TYPES:
                fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/deductionType.fxml"));
                title = "Deduction Types";
                break;
            case ADD_DEDUCTION_TYPES:
                fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/addDeductionType.fxml"));
                title = "Add Deduction Type";
                break;
            case INCOME_SETTINGS:
                fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/incomeSettings.fxml"));
                title = "Income Settings";
                break;
            default:
                throw new IOException("Scene not recognized");
        }

        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(new Scene(fxmlLoader.load()));
    }

    public static void loadSceneWithId(Stage stage, Scenes scene, Object id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        IDHolder.setId(id.equals("create") ? -1 : (Long) id);
        String title;
        switch (scene) {
            case MAIN:
                fxmlLoader.setLocation(HelloApplication.class.getResource("controllers/main.fxml"));
                title = "Home";
                break;
            case EMPLOYEES:
                fxmlLoader.setLocation(HelloApplication.class.getResource("controllers/employees.fxml"));
                title = "Employees";
                break;
            case ADD_EMPLOYEES:
                fxmlLoader.setLocation(HelloApplication.class.getResource("controllers/addEmployee.fxml"));
                title = "Add Employee";
                break;
            case EMPLOYEE_COMPENSATION:
                fxmlLoader.setLocation(HelloApplication.class.getResource("controllers/employeeCompensation.fxml"));
                title = "Employee Compensation";
                break;
            case SETTINGS:
                fxmlLoader.setLocation(HelloApplication.class.getResource("controllers/settings.fxml"));
                title = "Settings";
                break;
            case DEDUCTION_TYPES:
                fxmlLoader.setLocation(HelloApplication.class.getResource("controllers/deductionType.fxml"));
                title = "Deduction Types";
                break;
            case ADD_DEDUCTION_TYPES:
                fxmlLoader.setLocation(HelloApplication.class.getResource("controllers/addDeductionType.fxml"));
                title = "Add Deduction Type";
                break;
            case INCOME_SETTINGS:
                fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/incomeSettings.fxml"));
                title = "Income Settings";
                break;
            case ADD_INCOME_SETTINGS:
                fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/addIncomeSetting.fxml"));
                title = "Add Income Setting";
                break;
            default:
                throw new IOException("Scene not recognized");
        }

        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }
}
