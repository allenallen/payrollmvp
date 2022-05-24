package com.tamaraw.payroll.services;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.ApiResponse;
import com.tamaraw.payroll.models.EmployeeCompensation;
import com.tamaraw.payroll.models.EmployeeCompensationDto;
import com.tamaraw.payroll.utils.APIDefinitions;
import com.tamaraw.payroll.utils.ApiResponseTokenHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeCompensationService extends HttpService {
    public EmployeeCompensationService() {
        super(APIDefinitions.EMPLOYEE_COMPENSATION_API);
    }

    public ObservableList<EmployeeCompensation> getAll() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getUrl()).asJson();
        if (response.getStatus() == 200) {
            ApiResponse<List<EmployeeCompensationDto>> employeeDtos = new Gson().fromJson(response.getBody().toString(), ApiResponseTokenHelper.EMPLOYEE_COMPENSATION_LIST);
            List<EmployeeCompensation> employees = employeeDtos.getBody().stream().map(EmployeeCompensation::new).collect(Collectors.toList());
            return FXCollections.observableList(employees);
        } else {
            return FXCollections.observableList(new ArrayList<>());
        }
    }

    public void update(EmployeeCompensationDto dto) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.put(getUrl())
                .header("Content-Type", "application/json")
                .body(new Gson().toJson(dto))
                .asJson();

        if (response.getStatus() == 500) {
            ApiResponse<EmployeeCompensationDto> res = new Gson().fromJson(response.getBody().toString(), ApiResponseTokenHelper.EMPLOYEE_COMPENSATION);
            throw new UnirestException(res.getMessage());
        }
    }
}
