package com.example.root.task1;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class LoginActivity extends AppCompatActivity {

    private Toolbar main_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        main_toolbar = (Toolbar) findViewById(R.id.normal_toolbar);
        setSupportActionBar(main_toolbar);
        getSupportActionBar().setTitle("LoginScreen");

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();

    }





}