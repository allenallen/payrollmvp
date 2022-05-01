package com.tamaraw.payroll.models;

public class EmployeeCompensationDto {

    private int id;
    private double daily;
    private boolean sss;
    private boolean philHealth;
    private boolean pagibig;

    public EmployeeCompensationDto(int id, double daily, boolean sss, boolean philHealth, boolean pagibig) {
        this.id = id;
        this.daily = daily;
        this.sss = sss;
        this.philHealth = philHealth;
        this.pagibig = pagibig;
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

    public boolean isPhilHealth() {
        return philHealth;
    }

    public void setPhilHealth(boolean philHealth) {
        this.philHealth = philHealth;
    }

    public boolean isPagibig() {
        return pagibig;
    }

    public void setPagibig(boolean pagibig) {
        this.pagibig = pagibig;
    }
}
