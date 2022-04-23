package com.tamaraw.payroll.daos;

import com.tamaraw.payroll.models.Employee;
import com.tamaraw.payroll.models.EmployeeDto;
import com.tamaraw.payroll.utils.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private static final String TABLE_NAME = "EMPLOYEES";
    public static final String FIRST_NAME_COLUMN = "FIRST_NAME";
    public static final String LAST_NAME_COLUMN = "LAST_NAME";
    public static final String ADDRESS_COLUMN = "ADDRESS";
    public static final String CONTACT_NUMBER_COLUMN = "CONTACT_NUMBER";
    public static final String BIRTHDAY_COLUMN = "BIRTHDAY";
    public static final String EMPLOYEE_NUMBER_COLUMN = "EMPLOYEE_NUMBER";
    public static final String ID_COLUMN = "ID";

    public static ObservableList<Employee> getAll() {
        String stmt = "SELECT * FROM \"" + TABLE_NAME + "\"";

        List<Employee> employeeList = new ArrayList<>();

        try {
            ResultSet rs = DBUtil.dbExecuteQuery(stmt);
            while (rs.next()) {
                Employee employee = new Employee(rs);
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.disconnectDb();
        }

        return FXCollections.observableList(employeeList);
    }

    public static Employee getOne(String id) {
        String stmt = "SELECT * FROM \"" + TABLE_NAME + "\" WHERE ID = '" + id + "'";
        System.out.println(stmt);
        Employee employee = null;
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(stmt);
            if (rs.next()) {
                employee = new Employee(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.disconnectDb();
        }

        return employee;
    }

    public static void update(EmployeeDto dto) {
        String stmt = String.format("UPDATE %s SET %s = '%s', SET %s = '%s', SET %s = '%s', SET %s = '%s', SET %s = '%s', SET %s = '%s'",
                TABLE_NAME, FIRST_NAME_COLUMN, dto.getFirstName(), LAST_NAME_COLUMN, dto.getLastName(),
                ADDRESS_COLUMN, dto.getAddress(), CONTACT_NUMBER_COLUMN, dto.getContactNumber(),
                EMPLOYEE_NUMBER_COLUMN, dto.getEmployeeNumber(), BIRTHDAY_COLUMN, dto.getBirthday());
        DBUtil.dbExecuteUpdate(stmt);
        DBUtil.disconnectDb();
    }
}
