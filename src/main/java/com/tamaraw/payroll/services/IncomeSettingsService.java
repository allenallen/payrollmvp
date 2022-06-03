package com.tamaraw.payroll.services;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.*;
import com.tamaraw.payroll.utils.APIDefinitions;
import com.tamaraw.payroll.utils.ApiResponseTokenHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IncomeSettingsService extends HttpService<IncomeSettingsDto> {
    public IncomeSettingsService() {
        super(APIDefinitions.INCOME_SETTINGS_API, ApiResponseTokenHelper.INCOME_SETTINGS);
    }

    public IncomeSettings getOne(Long id) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getUrl() + id).asJson();
        ApiResponse<IncomeSettingsDto> incomeSettingsDto = new Gson().fromJson(response.getBody().toString(), ApiResponseTokenHelper.INCOME_SETTINGS);
        if (response.getStatus() == 200) {
            return new IncomeSettings(incomeSettingsDto.getBody());
        } else {
            throw new UnirestException(incomeSettingsDto.getMessage());
        }
    }

    public ObservableList<IncomeSettings> getAll(boolean queryAll) throws UnirestException {
        HttpResponse<JsonNode> response;
        if (queryAll) {
            response = Unirest.get(getUrl()).queryString("queryAll", "true").asJson();
        } else {
            response = Unirest.get(getUrl()).asJson();
        }
        if (response.getStatus() == 200) {
            ApiResponse<List<IncomeSettingsDto>> incomeSettingsDtos = new Gson().fromJson(response.getBody().toString(),
                    ApiResponseTokenHelper.INCOME_SETTINGS_LIST);
            List<IncomeSettings> incomeSettings = incomeSettingsDtos.getBody().stream().map(IncomeSettings::new).collect(Collectors.toList());
            return FXCollections.observableList(incomeSettings);
        } else {
            return FXCollections.observableList(new ArrayList<>());
        }
    }
}
