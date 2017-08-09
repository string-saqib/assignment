package com.example.root.task1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
            //mTextView.setText("Username: " + username + "\n" + "E-mail: " + emailID + "\n" + "Gender: "  + gender);
            SpannableString text = new SpannableString("Username: " + username + "\n" + "E-mail: " + emailID + "\n" + "Gender: "  + gender);
            int a1 = (username + "\n").length() + 10;
            int a2 = 8 + a1;
            int a3 = (emailID + "\n").length() + a2;
            int a4 = 8 + a3;
            int a5 = (gender).length() + a4;

            text.setSpan(new ForegroundColorSpan(Color.RED),0,10,0);
            text.setSpan(new ForegroundColorSpan(Color.BLUE),10,a1-1, 0);

            text.setSpan(new ForegroundColorSpan(Color.RED), a1,a2-1,0);
            text.setSpan(new ForegroundColorSpan(Color.BLUE),a2,a3-1, 0);

            text.setSpan(new ForegroundColorSpan(Color.RED), a3,a4-1,0);
            text.setSpan(new ForegroundColorSpan(Color.BLUE),a4,a5, 0);

            mTextView.append(text);
            if (!city.equals("---Select---")) {
                //mTextView.append("City: " + city);
                SpannableString text2 = new SpannableString("\n" + "City: " + city);
                text2.setSpan(new ForegroundColorSpan(Color.RED), 0,7,0);
                text2.setSpan(new ForegroundColorSpan(Color.BLUE), 8, 8 + city.length()-1, 0);
                mTextView.append(text2);
            }
        }
        return inflatedView;
    }
}
