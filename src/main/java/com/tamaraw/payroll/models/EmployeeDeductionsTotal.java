package com.tamaraw.payroll.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class EmployeeDeductionsTotal {

    private DoubleProperty totalAmount = new SimpleDoubleProperty();

    public EmployeeDeductionsTotal(double totalAmount) {
        this.totalAmount.set(totalAmount);
    }

    public DoubleProperty getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount.set(totalAmount);
    }
}
