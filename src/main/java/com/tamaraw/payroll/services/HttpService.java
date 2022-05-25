package com.tamaraw.payroll.services;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.ApiResponse;
import com.tamaraw.payroll.models.EmployeeDto;
import com.tamaraw.payroll.utils.ApiResponseTokenHelper;

import java.lang.reflect.Type;
import java.util.ResourceBundle;

public abstract class HttpService<DTO> {
    private String url;

    private Type objectType;

    public HttpService(String apiUrl, Type singleObjectType) {
        String baseUrl = ResourceBundle.getBundle("bundle/resources").getString("baseUrl");
        this.url = baseUrl + apiUrl;
        this.objectType = singleObjectType;
    }

    public String getUrl() {
        return url;
    }

    public void create(DTO dto) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(getUrl())
                .header("Content-Type", "application/json")
                .body(new Gson().toJson(dto))
                .asJson();

        if (response.getStatus() == 500) {
            ApiResponse<DTO> res = new Gson().fromJson(response.getBody().toString(), this.objectType);
            throw new UnirestException(res.getMessage());
        }
    }

    public void update(DTO dto, Long id) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.put(getUrl() + id)
                .header("Content-Type", "application/json")
                .body(new Gson().toJson(dto))
                .asJson();

        if (response.getStatus() == 500) {
            ApiResponse<DTO> res = new Gson().fromJson(response.getBody().toString(), this.objectType);
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
