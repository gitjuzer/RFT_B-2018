package com.example.milan.rftproject;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private EditText username,password;
    private Button loginbutton,registerbutton;

    OnLoginFormActivityListener loginFormActivityListener;

    public LoginFragment() {

        // Required empty public constructor
    }

    public interface OnLoginFormActivityListener{
        public void performRegister();
        public void performLogin(String username);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       /* usernametext=(EditText) findViewById(R.id.usernameltext);
        passwordtext=(EditText) findViewById(R.id.passwordtext);
        */

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        username=view.findViewById(R.id.usernametext);
        password=view.findViewById(R.id.passwordtext);

        loginbutton=(Button) view.findViewById(R.id.loginbtn);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            performLogin();
            }
        });

        registerbutton=(Button) view.findViewById(R.id.registerbtn);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFormActivityListener.performRegister();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity=(Activity) context;
        loginFormActivityListener=(OnLoginFormActivityListener)activity;

    }
    private void performLogin(){
        String Username=username.getText().toString();
        String Password=password.getText().toString();

       Call<User> call=MainActivity.apiInterface.performLogin(Username,Password);
       call.enqueue(new Callback<User>() {
           @Override
           public void onResponse(Call<User> call, Response<User> response) {
               if(response.body().equals("ok")){
                   MainActivity.config.writeLoginStatus(true);
                   loginFormActivityListener.performLogin(response.body().getUsername());
               }else if(response.body().getResponse().equals("failed")){
                   MainActivity.config.diplayToast("Login failed");
               }
           }

           @Override
           public void onFailure(Call<User> call, Throwable t) {

           }
       });

       username.setText("");
       password.setText("");
    }
}
