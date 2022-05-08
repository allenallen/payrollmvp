package com.tamaraw.payroll.utils;

import com.tamaraw.payroll.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
            default:
                throw new IOException("Scene not recognized");
        }

        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(new Scene(fxmlLoader.load()));
    }

    public static void loadSceneWithId(Stage stage, Scenes scene, String idKey, Object id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.getNamespace().put(idKey, id);
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
            default:
                throw new IOException("Scene not recognized");
        }

        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }
}
