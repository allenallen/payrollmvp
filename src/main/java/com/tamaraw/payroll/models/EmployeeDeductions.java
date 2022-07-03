package com.tamaraw.payroll.models;

import javafx.beans.property.*;

public class EmployeeDeductions {

    private LongProperty id = new SimpleLongProperty();

    private StringProperty type = new SimpleStringProperty();

    private DoubleProperty totalAmount = new SimpleDoubleProperty();

    public LongProperty getId() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public StringProperty getType() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public DoubleProperty getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount.set(totalAmount);
    }
}
