package com.example.milan.rftproject;

import android.content.Context;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static android.widget.Toast.LENGTH_SHORT;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment{
    private EditText editusername,editpassword;
    private Button loginbutton,registerbutton;
    View view;

    public LoginFragment() {
     //   this.context=context;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_login, container, false);
        editusername=view.findViewById(R.id.usernametext);
        editpassword=view.findViewById(R.id.passwordtext);

        loginbutton=(Button) view.findViewById(R.id.loginbtn);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editusername.getText().toString();
                String password = editpassword.getText().toString();
                if(validateLogin(username, password)){
                    doLogin(username, password);
                }
            }
        });

        registerbutton=(Button) view.findViewById(R.id.registerbtn);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,new RegisterFragment(),null)
                        .addToBackStack(null)
                        .commit();
            }

        });
        return view;
    }
    private boolean validateLogin(String username, String password){
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

    private void doLogin(final String username,final String password) {
        String loginurl="http://srv21.firstheberg.net:5000/login?username="+username+"&password="+password;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(loginurl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (myResponse.contains("Success")){
                                SharedUtils.saveUsername(username,getContext());
                                SharedUtils.savePassword(password,getContext());
                                Intent intent = new Intent(getActivity(), MenuActivity.class);
                                intent.putExtra("username", username);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getActivity(),"Login Failed!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }
}
