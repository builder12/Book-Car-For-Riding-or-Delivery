package com.jrteamtech.clonebla.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.jrteamtech.clonebla.R;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private TextInputEditText edtEmail;
    private TextView tvForgotPassword;
    private TextView tvSignUp;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_login, viewGroup, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.tvSignUp = (TextView) view.findViewById(R.id.tv_sign_up);
        this.tvForgotPassword = (TextView) view.findViewById(R.id.tv_forgot_password);
        this.tvSignUp.setOnClickListener(this);
        this.tvForgotPassword.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_forgot_password) {
            openForgotPasswordFragment();
        } else if (id == R.id.tv_sign_up) {
            openSignUpFragment();
        }
    }

    private void openSignUpFragment() {
        SignUpFragment signUpFragment = new SignUpFragment();
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.frame_container, signUpFragment);
        beginTransaction.disallowAddToBackStack();
        beginTransaction.commit();
    }

    private void openForgotPasswordFragment() {
        ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.frame_container, forgotPasswordFragment);
        beginTransaction.disallowAddToBackStack();
        beginTransaction.commit();
    }
}