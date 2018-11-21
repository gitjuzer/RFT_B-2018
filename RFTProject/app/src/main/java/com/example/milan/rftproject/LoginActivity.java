package com.example.milan.rftproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    List<User> users=new ArrayList<User>();
    EditText usernametext,passwordtext;
    Button loginbutton,registerbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernametext=(EditText) findViewById(R.id.usernameltext);
        passwordtext=(EditText) findViewById(R.id.passwordtext);
        loginbutton=(Button) findViewById(R.id.loginbtn);
        registerbutton=(Button) findViewById(R.id.registerbtn);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onClick(View v){
        if (v.getId()==R.id.loginbtn){

        }else
         if (v.getId()==R.id.registerbtn){

         }
    }
}
