package com.tamaraw.payroll.services;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.ApiResponse;
import com.tamaraw.payroll.models.Employee;
import com.tamaraw.payroll.models.EmployeeDto;
import com.tamaraw.payroll.utils.APIDefinitions;
import com.tamaraw.payroll.utils.ApiResponseTokenHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService extends HttpService<EmployeeDto> {
    public EmployeeService() {
        super(APIDefinitions.EMPLOYEE_API, ApiResponseTokenHelper.EMPLOYEE);
    }

    public Employee getEmployee(Long id) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getUrl() + id).asJson();
        ApiResponse<EmployeeDto> employeeDto = new Gson().fromJson(response.getBody().toString(), ApiResponseTokenHelper.EMPLOYEE);
        if (response.getStatus() == 200) {
            return new Employee(employeeDto.getBody());
        } else {
            throw new UnirestException(employeeDto.getMessage());
        }
    }

    public ObservableList<Employee> getEmployees() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getUrl()).asJson();
        if (response.getStatus() == 200) {
            ApiResponse<List<EmployeeDto>> employeeDtos = new Gson().fromJson(response.getBody().toString(), ApiResponseTokenHelper.EMPLOYEE_LIST);
            List<Employee> employees = employeeDtos.getBody().stream().map(Employee::new).collect(Collectors.toList());
            return FXCollections.observableList(employees);
        } else {
            return FXCollections.observableList(new ArrayList<>());
        }
    }
}
