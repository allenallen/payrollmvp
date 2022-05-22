package com.tamaraw.payroll.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.Employee;
import com.tamaraw.payroll.models.EmployeeDto;
import com.tamaraw.payroll.utils.APIDefinitions;
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

    public ObservableList<Employee> getEmployees() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getUrl()).asJson();
        if (response.getStatus() == 200) {
            Type employeeListType = new TypeToken<ArrayList<EmployeeDto>>(){}.getType();
            List<EmployeeDto> employeeDtos = new Gson().fromJson(response.getBody().toString(), employeeListType);
            List<Employee> employees = employeeDtos.stream().map(Employee::new).collect(Collectors.toList());
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
    }
}
