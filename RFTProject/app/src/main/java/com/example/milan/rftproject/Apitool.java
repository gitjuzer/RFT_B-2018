package com.example.milan.rftproject;

import retrofit2.Retrofit;

public class Apitool {
    public static final String URL="http://srv21.firstheberg.net:5000/";

    public static ApiInterface getApiInterface(){
        return ApiClient.getApiClient(URL).create(ApiInterface.class);
    }
}
