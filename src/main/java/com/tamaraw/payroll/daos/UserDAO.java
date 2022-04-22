package com.tamaraw.payroll.daos;

import com.tamaraw.payroll.models.User;
import com.tamaraw.payroll.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static final String TABLE_NAME = "USER";
    public static final String USERNAME_COLUMN = "USERNAME";
    public static final String PASSWORD_COLUMN = "PASSWORD";

    public static User getSuperUser() {
        String stmt = "SELECT * FROM \""+ TABLE_NAME + "\"";
        ResultSet rs = DBUtil.dbExecuteQuery(stmt);
        User user = null;
        try {
            if (rs.next()) {
                user = new User(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.disconnectDb();
        }
        return user;
    }

}
