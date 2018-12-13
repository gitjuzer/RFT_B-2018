package com.example.milan.rftproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Myprofile extends Fragment {
    TextView usernametext,emailtext;
    View view;
    public Myprofile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_myprofile, container, false);
        usernametext=(TextView) view.findViewById(R.id.myusername);
        emailtext=(TextView)view.findViewById(R.id.myemail);
        usernametext.setText(SharedUtils.getUsername(getContext()));
        emailtext.setText(SharedUtils.getEmail(getContext()));
        return view;
    }

}
