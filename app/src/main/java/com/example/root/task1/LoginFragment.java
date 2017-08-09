package com.example.root.task1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class LoginFragment extends Fragment {

    private EditText mUsernameView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private RadioGroup mButtonView;

    public static String username;
    public static String emailID;
    public static String gender;
    public static String city;

    public LoginFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_login, container, false);

        mUsernameView = inflatedView.findViewById(R.id.username);
        mEmailView = inflatedView.findViewById(R.id.email);
        mButtonView = inflatedView.findViewById(R.id.button_group);
        mPasswordView = inflatedView.findViewById(R.id.password);

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

        final Spinner mySpinner = inflatedView.findViewById(R.id.spinner_city_list);
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.city_list, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(dataAdapter);

        if (getArguments()!= null) {
            mUsernameView.setText(getArguments().getString("UserName"));
            mEmailView.setText(getArguments().getString("E-mail"));
            mySpinner.setSelection(dataAdapter.getPosition(getArguments().getString("City")));
            mButtonView.check(gender.equals("M") ? R.id.male : R.id.female);
        }

        Button mEmailSignInButton = inflatedView.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attemptLogin()) {
                    int checkedButtonId = mButtonView.getCheckedRadioButtonId();
                    RadioButton checkedButton = getView().findViewById(checkedButtonId);
                    Intent i = new Intent(getActivity(), DisplayActivity.class);
                    Bundle b = new Bundle();

                    username = mUsernameView.getText().toString();
                    emailID = mEmailView.getText().toString();
                    gender = checkedButton.getText().toString();
                    city = mySpinner.getSelectedItem().toString();

                    b.putString("UserName", username);
                    b.putString("E-mail", emailID);
                    b.putString("Gender", gender);
                    b.putString("City", city);
                    i.putExtras(b);
                    startActivity(i);
                    getActivity().finish();
                }
            }
        });
        return inflatedView;
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
