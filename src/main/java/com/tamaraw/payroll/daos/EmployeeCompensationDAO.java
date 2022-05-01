package com.tamaraw.payroll.daos;

import com.tamaraw.payroll.models.EmployeeCompensation;
import com.tamaraw.payroll.models.EmployeeCompensationDto;
import com.tamaraw.payroll.utils.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCompensationDAO {

    public static final String TABLE_NAME = "EMPLOYEE_COMPENSATION";
    public static final String EMPLOYEE_ID_COLUMN = "EMPLOYEE_ID";
    public static final String DAILY_COLUMN = "DAILY";
    public static final String SSS_COLUMN = "SSS";
    public static final String PAGIBIG_COLUMN = "PAGIBIG";
    public static final String PHILHEALTH_COLUMN = "PHILHEALTH";

    public static ObservableList<EmployeeCompensation> getAll() {
        String stmt = String.format("SELECT * FROM \"%s\"", TABLE_NAME);
        List<EmployeeCompensation> employeeCompensations = new ArrayList<>();
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(stmt);
            while (rs.next()) {
                employeeCompensations.add(new EmployeeCompensation(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.disconnectDb();
        }
        return FXCollections.observableList(employeeCompensations);
    }

    public static void update(EmployeeCompensationDto dto) {
        DBUtil.executeQuery("UPDATE %s SET %s = %f, %s = %s, %s = %s, %s = %s WHERE %s = '%s'",
                TABLE_NAME, DAILY_COLUMN, dto.getDaily(), SSS_COLUMN, dto.isSss(),
                PHILHEALTH_COLUMN, dto.isPhilHealth(), PAGIBIG_COLUMN, dto.isPagibig(),
                EMPLOYEE_ID_COLUMN, dto.getId());
    }
}
