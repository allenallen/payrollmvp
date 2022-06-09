package com.tamaraw.payroll.services;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.ApiResponse;
import com.tamaraw.payroll.models.EmployeeDeductionsTotalDto;
import com.tamaraw.payroll.utils.APIDefinitions;
import com.tamaraw.payroll.utils.ApiResponseTokenHelper;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDeductionsService extends HttpService<EmployeeDeductionsTotalDto> {
    public EmployeeDeductionsService() {
        super(APIDefinitions.EMPLOYEE_DEDUCTIONS_API, ApiResponseTokenHelper.EMPLOYEE_DEDUCTIONS_TOTAL);
    }

    public List<EmployeeDeductionsTotalDto> getAllDeductions() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getUrl()).asJson();
        if (response.getStatus() == 200) {
            ApiResponse<List<EmployeeDeductionsTotalDto>> employeeDtos = new Gson().fromJson(response.getBody().toString(), ApiResponseTokenHelper.EMPLOYEE_DEDUCTIONS_TOTAL_LIST);
            return employeeDtos.getBody();
        } else {
            return FXCollections.observableList(new ArrayList<>());
        }
    }
}
