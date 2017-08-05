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
    private TextView mTextView;
    private Button edit_button;
    private static Bundle back_bundle;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_display);

        bundle = this.getIntent().getExtras();

        String username = bundle.getString("UserName");
        String emailID = bundle.getString("E-mail");
        String gender = bundle.getString("Gender");
        String city = bundle.getString("City");

        mTextView = (TextView) findViewById(R.id.user_text);
        mTextView.setText(username);
        mTextView.append("\n");
        mTextView.append(emailID + "\n");
        mTextView.append(gender);
        if (!city.equals("---Select---"))
            mTextView.append("\n" + city);

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
