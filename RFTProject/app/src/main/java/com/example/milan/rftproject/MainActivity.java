package com.example.milan.rftproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;


public class MainActivity extends Activity implements LoginFragment.OnLoginFormActivityListener {
    List<User> users=new ArrayList<User>();
    public static Config config;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        config=new Config(this);
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);

        if (findViewById(R.id.fragment_container)!=null){
            if (savedInstanceState!=null){
                return;
            }
            if(config.readLoginStatus()){
                getFragmentManager().beginTransaction().add(R.id.fragment_container, new MenuFragment()).commit();
            }else{
                getFragmentManager().beginTransaction().add(R.id.fragment_container, new LoginFragment()).commit();
            }
        }
    }


    @Override
    public void performRegister() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new RegisterFragment()).addToBackStack(null).commit();

    }

    @Override
    public void performLogin(String username) {
        config.writeUsername(username);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new MenuFragment()).commit();
    }
}
