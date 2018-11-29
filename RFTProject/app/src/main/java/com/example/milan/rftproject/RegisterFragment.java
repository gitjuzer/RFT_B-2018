package com.example.milan.rftproject;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private EditText email,username,password;
    private Button registerbtn;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        email=view.findViewById(R.id.emailtext);
        username=view.findViewById(R.id.usernametext);
        password=view.findViewById(R.id.passwordtext);
        registerbtn=view.findViewById(R.id.registerbtn);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            performRegister();
            }
        });
        return view;
    }
    public void performRegister(){
        String Email=email.getText().toString();
        String Username=username.getText().toString();
        String Password=password.getText().toString();
        Call<User> call=MainActivity.apiInterface.performRegistration(Email,Username,Password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")){
                    MainActivity.config.diplayToast("Register success");
                }else if(response.body().equals("exist")){
                    MainActivity.config.diplayToast("Username already exist");
                }else if(response.body().equals("error")){
                    MainActivity.config.diplayToast("Something wrong");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        email.setText("");
        username.setText("");
        password.setText("");
    }
}
