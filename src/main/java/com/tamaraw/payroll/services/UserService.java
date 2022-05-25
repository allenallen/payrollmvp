package com.tamaraw.payroll.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.ApiResponse;
import com.tamaraw.payroll.models.EmployeeDto;
import com.tamaraw.payroll.models.User;
import com.tamaraw.payroll.models.UserDto;
import com.tamaraw.payroll.utils.APIDefinitions;
import com.tamaraw.payroll.utils.ApiResponseTokenHelper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserService extends HttpService<UserDto> {

    public UserService() {
        super(APIDefinitions.USER_API, ApiResponseTokenHelper.USER);
    }

    public User getUser(String username) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getUrl() + username).asJson();
        ApiResponse<UserDto> dto = new Gson().fromJson(response.getBody().toString(), ApiResponseTokenHelper.USER);
        if (response.getStatus() == 200) {
            return new User(dto.getBody());
        } else {
            throw new UnirestException(dto.getMessage());
        }
    }

}
