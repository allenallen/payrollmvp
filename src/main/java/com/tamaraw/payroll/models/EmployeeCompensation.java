package com.tamaraw.payroll.models;

import com.tamaraw.payroll.daos.EmployeeCompensationDAO;
import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeCompensation {

    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty employeeId = new SimpleIntegerProperty();
    private DoubleProperty daily = new SimpleDoubleProperty();
    private BooleanProperty sss = new SimpleBooleanProperty();
    private BooleanProperty philHealth = new SimpleBooleanProperty();
    private BooleanProperty pagibig = new SimpleBooleanProperty();

    public EmployeeCompensation(ResultSet rs) throws SQLException {
        this.setId(rs.getInt(EmployeeCompensationDAO.EMPLOYEE_ID_COLUMN));
        this.setEmployeeId(rs.getInt(EmployeeCompensationDAO.EMPLOYEE_ID_COLUMN));
        this.setDaily(rs.getDouble(EmployeeCompensationDAO.DAILY_COLUMN));
        this.setSss(rs.getBoolean(EmployeeCompensationDAO.SSS_COLUMN));
        this.setPhilHealth(rs.getBoolean(EmployeeCompensationDAO.PHILHEALTH_COLUMN));
        this.setPagibig(rs.getBoolean(EmployeeCompensationDAO.PAGIBIG_COLUMN));
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId.set(employeeId);
    }

    public DoubleProperty getDaily() {
        return daily;
    }

    public void setDaily(double daily) {
        this.daily.set(daily);
    }

    public BooleanProperty getSss() {
        return sss;
    }

    public void setSss(boolean sss) {
        this.sss.set(sss);
    }

    public BooleanProperty getPhilHealth() {
        return philHealth;
    }

    public void setPhilHealth(boolean philHealth) {
        this.philHealth.set(philHealth);
    }

    public BooleanProperty getPagibig() {
        return pagibig;
    }

    public void setPagibig(boolean pagibig) {
        this.pagibig.set(pagibig);
    }
}
