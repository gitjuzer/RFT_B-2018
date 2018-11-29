package com.example.milan.rftproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String URL="";
    public static Retrofit retrofit=null;
    public static Retrofit getApiClient(){
        if(retrofit=null){
            retrofit=new Retrofit.Builder().baseUrl(URL).addCallAdapterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
