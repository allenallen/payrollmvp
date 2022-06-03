package com.tamaraw.payroll.models;

import javafx.beans.property.*;

public class IncomeSettings {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty name = new SimpleStringProperty();
    private BooleanProperty active = new SimpleBooleanProperty();
    private BooleanProperty fixed = new SimpleBooleanProperty();
    private DoubleProperty value = new SimpleDoubleProperty();

    public IncomeSettings(IncomeSettingsDto dto) {
        setId(dto.getId());
        setName(dto.getName());
        setActive(dto.isActive());
        setFixed(dto.isFixed());
        setValue(dto.getValue() != null ? dto.getValue().doubleValue() : 0);
    }

    public IncomeSettings(LongProperty id, StringProperty name, BooleanProperty active, BooleanProperty fixed, DoubleProperty value) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.fixed = fixed;
        this.value = value;
    }

    public LongProperty getId() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public StringProperty getName() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public BooleanProperty getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public BooleanProperty getFixed() {
        return this.fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed.set(fixed);
    }

    public DoubleProperty getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value.set(value);
    }
}
