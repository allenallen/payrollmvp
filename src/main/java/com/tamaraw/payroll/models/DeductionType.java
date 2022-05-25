package com.tamaraw.payroll.models;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DeductionType {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty type = new SimpleStringProperty();

    public DeductionType(LongProperty id, StringProperty type) {
        this.id = id;
        this.type = type;
    }

    public DeductionType(DeductionTypeDto dto) {
        setId(dto.getId());
        setType(dto.getType());
    }

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
}
