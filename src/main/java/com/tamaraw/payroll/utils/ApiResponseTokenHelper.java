package com.tamaraw.payroll.utils;

import com.google.gson.reflect.TypeToken;
import com.tamaraw.payroll.models.*;

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
    public static Type DEDUCTION_TYPE_LIST = new TypeToken<ApiResponse<List<DeductionTypeDto>>>(){}.getType();
    public static Type DEDUCTION_TYPE = new TypeToken<ApiResponse<DeductionTypeDto>>(){}.getType();
    public static Type INCOME_SETTINGS = new TypeToken<ApiResponse<IncomeSettingsDto>>(){}.getType();
    public static Type INCOME_SETTINGS_LIST = new TypeToken<ApiResponse<List<IncomeSettingsDto>>>(){}.getType();
    public static Type EMPLOYEE_DEDUCTIONS_TOTAL_LIST = new TypeToken<ApiResponse<List<EmployeeDeductionsTotalDto>>>(){}.getType();
    public static Type EMPLOYEE_DEDUCTIONS_TOTAL = new TypeToken<ApiResponse<EmployeeDeductionsTotalDto>>(){}.getType();
    public static Type EMPLOYEE_DEDUCTION = new TypeToken<ApiResponse<EmployeeDeductionsDto>>(){}.getType();
    public static Type EMPLOYEE_DEDUCTION_LIST = new TypeToken<ApiResponse<List<EmployeeDeductionsDto>>>(){}.getType();
}
