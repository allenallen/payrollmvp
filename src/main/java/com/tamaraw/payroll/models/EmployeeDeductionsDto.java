package com.tamaraw.payroll.models;

import java.math.BigDecimal;

public class EmployeeDeductionsDto {

    private Long id;

    private String deductionType;

    private BigDecimal totalAmount;

    public EmployeeDeductionsDto(Long id, String deductionType, BigDecimal totalAmount) {
        this.id = id;
        this.deductionType = deductionType;
        this.totalAmount = totalAmount;
    }

    public EmployeeDeductionsDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
