package com.tamaraw.payroll.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService extends HttpService{
    public EmployeeService() {
        super(APIDefinitions.EMPLOYEE_API);
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

    public void create(EmployeeDto dto) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(getUrl())
                .header("Content-Type","application/json")
                .body(new Gson().toJson(dto))
                .asJson();

        if (response.getStatus() == 500) {
            ApiResponse<EmployeeDto> res = new Gson().fromJson(response.getBody().toString(), ApiResponseTokenHelper.EMPLOYEE);
            throw new UnirestException(res.getMessage());
        }
    }

    public void update(EmployeeDto dto) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.put(getUrl() + dto.getId())
                .header("Content-Type","application/json")
                .body(new Gson().toJson(dto))
                .asJson();

        if (response.getStatus() == 500) {
            ApiResponse<EmployeeDto> res = new Gson().fromJson(response.getBody().toString(), ApiResponseTokenHelper.EMPLOYEE);
            throw new UnirestException(res.getMessage());
        }
    }

    public void delete(Long id) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.delete(getUrl() + id).asJson();

        if (response.getStatus() == 500) {
            ApiResponse<String> res = new Gson().fromJson(response.getBody().toString(), ApiResponseTokenHelper.GENERIC_STRING);
            throw new UnirestException(res.getMessage());
        }
    }
}
