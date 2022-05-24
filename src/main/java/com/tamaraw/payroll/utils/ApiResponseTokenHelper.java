package com.tamaraw.payroll.utils;

import com.google.gson.reflect.TypeToken;
import com.tamaraw.payroll.models.ApiResponse;
import com.tamaraw.payroll.models.EmployeeDto;
import com.tamaraw.payroll.models.UserDto;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ApiResponseTokenHelper {
    public static Type EMPLOYEE_LIST = new TypeToken<ApiResponse<ArrayList<EmployeeDto>>>(){}.getType();
    public static Type EMPLOYEE = new TypeToken<ApiResponse<EmployeeDto>>(){}.getType();
    public static Type USER = new TypeToken<ApiResponse<UserDto>>(){}.getType();

    public static Type GENERIC_STRING = new TypeToken<ApiResponse<String>>(){}.getType();
}
