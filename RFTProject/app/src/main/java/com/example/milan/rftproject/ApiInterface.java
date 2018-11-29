package com.example.milan.rftproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("register.php")
    Call<User> performRegistration(@Query("email") String email,@Query("username") String username,@Query("password") String password);

    @GET("login.php")
    Call<User> performLogin(@Query("username") String username,@Query("password") String password);
}
