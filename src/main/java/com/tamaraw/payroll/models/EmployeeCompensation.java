package com.tamaraw.payroll.models;

import javafx.beans.property.*;

public class EmployeeCompensation {

    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty employeeId = new SimpleIntegerProperty();
    private DoubleProperty daily = new SimpleDoubleProperty();
    private BooleanProperty sss = new SimpleBooleanProperty();
    private BooleanProperty philHealth = new SimpleBooleanProperty();
    private BooleanProperty pagibig = new SimpleBooleanProperty();

    private ObjectProperty<Employee> employee = new SimpleObjectProperty<>();

    public EmployeeCompensation(EmployeeCompensationDto dto) {
        this.setId(dto.getId());
        this.setEmployee(new Employee(dto.getEmployee()));
        this.setDaily(dto.getDaily());
        this.setSss(dto.isSss());
        this.setPagibig(dto.isPagibig());
        this.setPhilHealth(dto.isPhilhealth());
    }

    public ObjectProperty<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee.set(employee);
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
