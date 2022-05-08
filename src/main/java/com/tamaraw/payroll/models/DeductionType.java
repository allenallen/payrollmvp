package com.tamaraw.payroll.models;

import com.tamaraw.payroll.daos.DeductionTypeDAO;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeductionType {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty type = new SimpleStringProperty();

    public DeductionType(LongProperty id, StringProperty type) {
        this.id = id;
        this.type = type;
    }

    public DeductionType(ResultSet rs) throws SQLException {
        this.setId(rs.getLong(DeductionTypeDAO.ID_COLUMN));
        this.setType(rs.getString(DeductionTypeDAO.TYPE_COLUMN));
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
