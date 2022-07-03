package com.tamaraw.payroll.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.EmployeeDeductionsDto;
import com.tamaraw.payroll.utils.APIDefinitions;
import com.tamaraw.payroll.utils.ApiResponseTokenHelper;

import java.util.List;

public class EmployeeDeductionService extends HttpService<EmployeeDeductionsDto> {
    public EmployeeDeductionService() {
        super(APIDefinitions.EMPLOYEE_DEDUCTIONS_API, ApiResponseTokenHelper.EMPLOYEE_DEDUCTION);
    }

    public List<EmployeeDeductionsDto> getByEmployee(Long employeeId) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getUrl() + employeeId).asJson();
        if (response.getStatus() == 200) {

        }
    }
}
