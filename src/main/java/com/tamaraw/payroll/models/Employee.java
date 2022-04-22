package com.tamaraw.payroll.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;

public class Employee{

    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty contactNumber = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private IntegerProperty id = new SimpleIntegerProperty();

    public Employee(ResultSet rs) {

    }

    public String getFirstName() {
        return firstName.get();
    }
}
