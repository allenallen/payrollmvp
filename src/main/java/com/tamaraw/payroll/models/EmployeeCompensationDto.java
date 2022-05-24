package com.tamaraw.payroll.models;

public class EmployeeCompensationDto {

    private int id;
    private double daily;
    private boolean sss;
    private boolean philhealth;
    private boolean pagibig;

    private EmployeeDto employee;

    public EmployeeCompensationDto(int id, double daily, boolean sss, boolean philhealth, boolean pagibig, EmployeeDto employeeDto) {
        this.id = id;
        this.daily = daily;
        this.sss = sss;
        this.philhealth = philhealth;
        this.pagibig = pagibig;
        this.employee = employeeDto;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDaily() {
        return daily;
    }

    public void setDaily(double daily) {
        this.daily = daily;
    }

    public boolean isSss() {
        return sss;
    }

    public void setSss(boolean sss) {
        this.sss = sss;
    }

    public boolean isPhilhealth() {
        return philhealth;
    }

    public void setPhilhealth(boolean philhealth) {
        this.philhealth = philhealth;
    }

    public boolean isPagibig() {
        return pagibig;
    }

    public void setPagibig(boolean pagibig) {
        this.pagibig = pagibig;
    }
}
