package com.tamaraw.payroll.models;

import javafx.beans.property.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {

    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty contactNumber = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private IntegerProperty employeeNumber = new SimpleIntegerProperty();
    private StringProperty birthday = new SimpleStringProperty();
    private LongProperty id = new SimpleLongProperty();
    private ObjectProperty<EmployeeCompensation> employeeCompensation = new SimpleObjectProperty<>();
    private BooleanProperty active = new SimpleBooleanProperty();

    public Employee(EmployeeDto dto) {
        this.setFirstName(dto.getFirstName());
        this.setLastName(dto.getLastName());
        this.setAddress(dto.getAddress());

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getBirthday());
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            this.setBirthday(format.format(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        this.setEmployeeNumber(dto.getEmployeeNumber());
        this.setId(dto.getId());
        this.setContactNumber(dto.getContactNumber());
        this.setActive(dto.getActive());
    }

    public ObjectProperty<EmployeeCompensation> getEmployeeCompensation() {
        return employeeCompensation;
    }

    public void setEmployeeCompensation(EmployeeCompensation employeeCompensation) {
        this.employeeCompensation.set(employeeCompensation);
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public BooleanProperty getActive() {
        return active;
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

    public LongProperty getId() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }
}

