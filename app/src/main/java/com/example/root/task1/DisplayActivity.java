package com.example.root.task1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class DisplayActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button editButton;

    private static Bundle infoBundle;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_display);

        DisplayFragment details = new DisplayFragment();
        details.setArguments(this.getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().commit();

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DisplayScreen");

        infoBundle = bundle;
        editButton = (Button) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(DisplayActivity.this, LoginActivity.class);
                backIntent.putExtras(infoBundle);
                startActivity(backIntent);
                finish();
            }

        });
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

}
