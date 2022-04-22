package com.tamaraw.payroll.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDBObject<MODEL> {
    abstract MODEL initFromResultSet(ResultSet resultSet) throws SQLException;
}
