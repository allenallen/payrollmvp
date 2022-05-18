package com.tamaraw.payroll.services;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.tamaraw.payroll.models.User;
import com.tamaraw.payroll.models.UserDto;

public class UserService extends HttpService {

    public UserService(String apiUrl) {
        super(apiUrl);
    }

    public User getUser(String username) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(getUrl() + username).asJson();
        if (response.getStatus() == 200) {
            UserDto dto = new Gson().fromJson(response.getBody().toString(), UserDto.class);
            return new User(dto);
        } else {
            return null;
        }
    }

}
