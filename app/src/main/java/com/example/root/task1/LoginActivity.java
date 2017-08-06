package com.example.root.task1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    private EditText mUsernameView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private RadioGroup mButtonView;
    private Toolbar main_toolbar;

    public static String username;
    public static String emailID;
    public static String gender;
    public static String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        main_toolbar = (Toolbar) findViewById(R.id.normal_toolbar);
        setSupportActionBar(main_toolbar);
        getSupportActionBar().setTitle("LoginScreen");

        mUsernameView = (EditText) findViewById(R.id.username);
        mEmailView = (EditText) findViewById(R.id.email);
        mButtonView = (RadioGroup) findViewById(R.id.button_group);
        mPasswordView = (EditText) findViewById(R.id.password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner_city_list);
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.city_list, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(dataAdapter);

        savedInstanceState = this.getIntent().getExtras();
        if (savedInstanceState != null) {
            mUsernameView.setText(savedInstanceState.getString("UserName"));
            mEmailView.setText(savedInstanceState.getString("E-mail"));
            mySpinner.setSelection(dataAdapter.getPosition(savedInstanceState.getString("City")));
            mButtonView.check(gender.equals("M") ? R.id.male : R.id.female);
        }

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attemptLogin()) {
                    int checkedButtonId = mButtonView.getCheckedRadioButtonId();
                    RadioButton checkedButton = (RadioButton) findViewById(checkedButtonId);
                    Intent i = new Intent(LoginActivity.this, DisplayActivity.class);
                    username = mUsernameView.getText().toString();
                    emailID = mEmailView.getText().toString();
                    gender = checkedButton.getText().toString();
                    city = mySpinner.getSelectedItem().toString();

                    i.putExtra("UserName", username);
                    i.putExtra("E-mail", emailID);
                    i.putExtra("Gender", gender);
                    i.putExtra("City", city);
                    startActivity(i);
                    finish();
                }
            }
        });

    }

    private boolean attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mUsernameView.setError(null);

        String name = mUsernameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        } else if (!isUsernameValid(name)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        }
        return !cancel;
    }

    private boolean isEmailValid(String email) {
        return email.matches("(\\w+)@[a-zA-Z]+[\\.][a-zA-Z]+");
    }

    private boolean isPasswordValid(String password) {
        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasLowerCase = !password.equals(password.toUpperCase());
        boolean hasDigit = !password.matches("[a-zA-Z]*") && password.matches("[a-zA-Z0-9]*");
        return (hasUpperCase && hasLowerCase && hasDigit && (password.length() > 5));
    }

    private boolean isUsernameValid(String name) {
        boolean hasNoSpace = !name.contains(" ");
        return (hasNoSpace && name.length() > 4);
    }
}