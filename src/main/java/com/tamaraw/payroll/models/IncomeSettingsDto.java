package com.tamaraw.payroll.models;

import java.math.BigDecimal;

public class IncomeSettingsDto {

    private Long id;

    private String name;

    private boolean active;

    private boolean fixed;

    private BigDecimal value;

    public IncomeSettingsDto(IncomeSettings incomeSettings) {
        setId(incomeSettings.getId().getValue());
        setName(incomeSettings.getName().getValue());
        setActive(incomeSettings.getActive().getValue());
        setFixed(incomeSettings.getFixed().getValue());
        setValue(BigDecimal.valueOf(incomeSettings.getValue().getValue()));
    }

    public IncomeSettingsDto(Long id, String name, boolean active, boolean fixed, BigDecimal value) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.fixed = fixed;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
