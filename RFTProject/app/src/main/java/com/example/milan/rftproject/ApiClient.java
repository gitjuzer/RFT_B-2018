package com.example.milan.rftproject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
public class ApiClient {
    public static final OkHttpClient client = new OkHttpClient();

    public static final String loginurl = "http://srv21.firstheberg.net:5000/login" ;

    public static final String registerurl = "http://srv21.firstheberg.net:5000/register" ;

    public static Request postloginrequest = new Request.Builder()
            .url(loginurl)
            .build();


    }
