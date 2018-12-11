package com.example.milan.rftproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
    List<User> users=new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        //config=new Config(this);
       // apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (findViewById(R.id.fragment_container)!=null){
            if (savedInstanceState!=null){
                return;
            }
            if(SharedUtils.getUsername(this)!=null){
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("username", SharedUtils.getUsername(this));
                startActivity(intent);
            }else{
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new LoginFragment()).commit();
            }
        }
    }
}
