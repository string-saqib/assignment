package com.example.root.task1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button edit_button;

    private static Bundle back_bundle;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fragment_details);

        DisplayFragment details = new DisplayFragment();
        details.setArguments(this.getIntent().getExtras());

        back_bundle = bundle;
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DisplayScreen");

        edit_button = (Button) findViewById(R.id.edit_button);

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_intent = new Intent(DisplayActivity.this, LoginActivity.class);
                back_intent.putExtras(back_bundle);
                startActivity(back_intent);
                finish();
            }

        });
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

}
