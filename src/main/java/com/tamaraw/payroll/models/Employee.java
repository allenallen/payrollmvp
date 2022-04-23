package com.tamaraw.payroll.models;

import com.tamaraw.payroll.daos.EmployeeDAO;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee{

    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty contactNumber = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private IntegerProperty employeeNumber = new SimpleIntegerProperty();
    private StringProperty birthday = new SimpleStringProperty();
    private IntegerProperty id = new SimpleIntegerProperty();

    public Employee(ResultSet rs) throws SQLException {
        this.setFirstName(rs.getString(EmployeeDAO.FIRST_NAME_COLUMN));
        this.setLastName(rs.getString(EmployeeDAO.LAST_NAME_COLUMN));
        this.setAddress(rs.getString(EmployeeDAO.ADDRESS_COLUMN));
        this.setContactNumber(rs.getString(EmployeeDAO.CONTACT_NUMBER_COLUMN));
        this.setBirthday(rs.getDate(EmployeeDAO.BIRTHDAY_COLUMN).toString());
        this.setEmployeeNumber(rs.getInt(EmployeeDAO.EMPLOYEE_NUMBER_COLUMN));
        this.setId(rs.getInt(EmployeeDAO.ID_COLUMN));
    }

    public StringProperty getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber.set(contactNumber);
    }

    public StringProperty getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public IntegerProperty getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber.set(employeeNumber);
    }

    public StringProperty getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }
}

