package com.tamaraw.payroll.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EmployeeDto {

    private long id;
    private String firstName;
    private String lastName;
    private int employeeNumber;
    private String address;
    private String contactNumber;
    private String birthday;
    private boolean active;

    public EmployeeDto(String firstName, String lastName, int employeeNumber, String address, String contactNumber, String birthday, int id, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeNumber = employeeNumber;
        this.address = address;
        this.contactNumber = contactNumber;
        this.birthday = birthday;
        this.id = id;
        this.active = active;
    }

    public EmployeeDto(Employee employee) {
        this.id = employee.getId().getValue();
        this.firstName = employee.getFirstName().getValue();
        this.lastName = employee.getLastName().getValue();
        this.employeeNumber = employee.getEmployeeNumber().getValue();
        this.address = employee.getAddress().getValue();
        this.contactNumber = employee.getContactNumber().getValue();
        this.birthday = employee.getBirthday().getValue();
        this.active = employee.getActive().getValue();
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setParsedBirthday(String fxmlBirthday) {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.birthday = format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
