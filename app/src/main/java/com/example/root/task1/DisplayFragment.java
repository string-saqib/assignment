package com.example.root.task1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class DisplayFragment extends Fragment {

    public static Bundle backBundle;
    private TextView mTextView;
    public DisplayFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backBundle = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_details, container, false);

        if(backBundle != null) {
            String username = backBundle.getString("UserName");
            String emailID = backBundle.getString("E-mail");
            String gender = backBundle.getString("Gender");
            String city = backBundle.getString("City");

            mTextView = inflatedView.findViewById(R.id.user_text);
            mTextView.setText(username);
            mTextView.append("\n");
            mTextView.append(emailID + "\n");
            mTextView.append(gender);
            if (!city.equals("---Select---"))
                mTextView.append("\n" + city);
        }
        return inflatedView;
    }
}
