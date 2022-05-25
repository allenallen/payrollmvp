package com.tamaraw.payroll.services;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.ApiResponse;
import com.tamaraw.payroll.models.DeductionType;
import com.tamaraw.payroll.models.DeductionTypeDto;
import com.tamaraw.payroll.utils.APIDefinitions;
import com.tamaraw.payroll.utils.ApiResponseTokenHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeductionTypeService extends HttpService{
    public DeductionTypeService() {
        super(APIDefinitions.DEDUCTION_TYPE_API);
    }

    public ObservableList<DeductionType> getAll() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getUrl()).asJson();
        if (response.getStatus() == 200) {
            ApiResponse<List<DeductionTypeDto>> deductionTypes = new Gson().fromJson(response.getBody().toString(), ApiResponseTokenHelper.DEDUCTION_TYPE_LIST);
            List<DeductionType> deductionTypeList = deductionTypes.getBody().stream().map(DeductionType::new).collect(Collectors.toList());
            return FXCollections.observableList(deductionTypeList);
        } else {
            return FXCollections.observableList(new ArrayList<>());
        }
    }

    public void create(DeductionTypeDto dto) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(getUrl())
                .header("Content-Type","application/json")
                .body(new Gson().toJson(dto))
                .asJson();

        if (response.getStatus() == 500) {
            ApiResponse<DeductionTypeDto> res = new Gson().fromJson(response.getBody().toString(), ApiResponseTokenHelper.DEDUCTION_TYPE);
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
