package com.tamaraw.payroll.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private StringProperty userName = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    public User(UserDto dto) {
        this.setUserName(dto.getUsername());
        this.setPassword(dto.getPassword());
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

}
