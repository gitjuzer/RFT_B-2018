package com.example.milan.rftproject;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.widget.Toast.LENGTH_SHORT;

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
                String emailtext = email.getText().toString();
                String usernametext = username.getText().toString();
                String passwordtext = password.getText().toString();
                if(validateRegister(emailtext,usernametext,passwordtext)){
                    doRegister(email.getText().toString(),username.getText().toString(),password.getText().toString());
                }
            }
        });
        return view;
    }

    private boolean validateRegister(String email,String username, String password){
        if(email==null || email.trim().length() ==0 ){
            Toast.makeText(this.getContext(), "Email is  required", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!email.contains("@")){
            Toast.makeText(this.getContext(), "This is not email format!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this.getContext(), "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this.getContext(), "Password is required", LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void doRegister(final String email, final String username, final String password){
        String registerurl="http://srv21.firstheberg.net:5000/register?username="+username+"&password="+password+"&email="+email;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(registerurl)
                .build();

        try {
            final Response response =  client.newCall(request).execute();
            final String respMsg = response.body().string();
            if(response.isRedirect()){
                Toast.makeText(getContext(),"Server unavaible",Toast.LENGTH_LONG);
            }else {
                final int respcode = response.code();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (respMsg.contains("missing")) {
                            Toast.makeText(getActivity(), "Missing parameters!", Toast.LENGTH_SHORT).show();
                        } else if (respcode == 500) {
                            Toast.makeText(getActivity(), "User already registered! ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Successfully registered! ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            SharedUtils.saveEmail(email,getContext());
                        }
                        //

                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
