package com.practice.guestbook.Identification;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.practice.guestbook.IdentificationActivity;
import com.practice.guestbook.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorizationFragment extends Fragment implements View.OnClickListener {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView registerText;

    public AuthorizationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authorization, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        loginButton = view.findViewById(R.id.loginButton);
        registerText = view.findViewById(R.id.registerText);

        loginButton.setOnClickListener(this);
        registerText.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                IdentificationActivity.networkController.loginUser(emailEditText.getText().toString(), passwordEditText.getText().toString());
                break;
            case R.id.registerText:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.identificationMainConstraint, new RegistrationFragment()).addToBackStack(null).commit();
                break;
        }
    }
}
