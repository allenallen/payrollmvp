package com.tamaraw.payroll.daos;

import com.tamaraw.payroll.models.DeductionType;
import com.tamaraw.payroll.models.DeductionTypeDto;
import com.tamaraw.payroll.utils.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeductionTypeDAO {

    public static final String TABLE_NAME = "DEDUCTION_TYPE";

    public static final String ID_COLUMN = "ID";

    public static final String TYPE_COLUMN = "TYPE";

    public static ObservableList<DeductionType> getAll() {
        String stmt = String.format("SELECT * FROM %s", TABLE_NAME);
        List<DeductionType> deductionTypes = new ArrayList<>();
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(stmt);
            while(rs.next()) {
                deductionTypes.add(new DeductionType(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.disconnectDb();
        }

        return FXCollections.observableList(deductionTypes);
    }

    public static DeductionType getOne(Long id) {
        String stmt = String.format("SELECT * FROM %s WHERE %s = %d", TABLE_NAME, ID_COLUMN, id);
        DeductionType deductionType = null;
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(stmt);
            if (rs.next()) {
                deductionType = new DeductionType(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.disconnectDb();
        }

        return deductionType;
    }

    public static void create(DeductionTypeDto dto) {
        DBUtil.executeQuery("INSERT INTO \"%s\" (%s) VALUES ('%s')", TABLE_NAME, TYPE_COLUMN, dto.getType());
    }

    public static void delete(long id) {
        DBUtil.executeQuery("DELETE FROM %s WHERE ID = '%s'", TABLE_NAME, id);
    }
}
