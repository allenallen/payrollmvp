package com.tamaraw.payroll.services;

import java.util.ResourceBundle;

public abstract class HttpService {
    private String url;

    public HttpService(String apiUrl) {
        String baseUrl = ResourceBundle.getBundle("bundle/resources").getString("baseUrl");
        this.url = baseUrl + apiUrl;
    }

    public String getUrl() {
        return url;
    }
}
