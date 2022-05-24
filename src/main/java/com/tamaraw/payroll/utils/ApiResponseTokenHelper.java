package com.tamaraw.payroll.utils;

import com.google.gson.reflect.TypeToken;
import com.tamaraw.payroll.models.ApiResponse;
import com.tamaraw.payroll.models.EmployeeCompensationDto;
import com.tamaraw.payroll.models.EmployeeDto;
import com.tamaraw.payroll.models.UserDto;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ApiResponseTokenHelper {
    public static Type EMPLOYEE_LIST = new TypeToken<ApiResponse<ArrayList<EmployeeDto>>>(){}.getType();
    public static Type EMPLOYEE = new TypeToken<ApiResponse<EmployeeDto>>(){}.getType();
    public static Type USER = new TypeToken<ApiResponse<UserDto>>(){}.getType();
    public static Type GENERIC_STRING = new TypeToken<ApiResponse<String>>(){}.getType();
    public static Type EMPLOYEE_COMPENSATION_LIST = new TypeToken<ApiResponse<List<EmployeeCompensationDto>>>(){}.getType();
    public static Type EMPLOYEE_COMPENSATION = new TypeToken<ApiResponse<EmployeeCompensationDto>>(){}.getType();

}
