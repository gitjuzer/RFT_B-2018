package com.example.milan.rftproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private String username;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Homefragment()).commit();
        drawerLayout =(DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.menuview);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.usernameheadertext);
        String data = getIntent().getExtras().getString("username","Null");
        navUsername.setText(data);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.home_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Homefragment()).commit();
                break;
            case R.id.profile_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Myprofile()).commit();
                break;
            case R.id.phighscores_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PersonalHsFragment()).commit();
                break;
            case R.id.ghighscores_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new GlobalHsFragment()).commit();
                break;
            case R.id.logout_id:
                SharedUtils.saveUsername(null,this);
                SharedUtils.savePassword(null,this);
                SharedUtils.saveEmail(null,this);
                Intent intent=new Intent(MenuActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
