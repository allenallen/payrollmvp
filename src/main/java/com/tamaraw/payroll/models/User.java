package com.tamaraw.payroll.models;

import com.tamaraw.payroll.daos.UserDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends BaseDBObject<User>{

    private StringProperty userName = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    public User(ResultSet rs) throws SQLException {
        this.setUserName(rs.getString(UserDAO.USERNAME_COLUMN));
        this.setPassword(rs.getString(UserDAO.PASSWORD_COLUMN));
    }

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

    @Override
    User initFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(resultSet);
    }
}
