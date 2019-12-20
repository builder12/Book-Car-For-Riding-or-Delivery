package com.jrteamtech.clonebla.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.jrteamtech.clonebla.R;

import java.util.Objects;

import fr.ganfra.materialspinner.MaterialSpinner;

public class SignUpFragment extends Fragment implements View.OnClickListener {

    private EditText gender,firstname,lastname,yearofbirth,email,password,passwordconfirm;
    private TextView visible_gender,visible_firstname,visible_lastname,visible_yearofbirth,visible_email,visible_password,
                     visible_passwordconfirm;
    private MaterialSpinner spinnergender,spinneryear;
    private TextInputEditText edt_first_name,edt_last_name,edt_email,edt_password,edt_confirm;
    private TextView edt_sendtext;
    private CheckBox chk_t_and_c;


    private ArrayAdapter<String> adapter;
    private String[] genderItem = {"Male", "Female"};
    private TextView tvLogin;
    private TextView tvSignUpWithEmail;
    private String[] yearItem = { "2010", "2009" ,"2008", "2007", "2006", "2005", "2004" ,"2003" ,"2002" ,"2001", "2000", "1999", "1998", "1997",
                                  "1996", "1995" ,"1994", "1993", "1992", "1991", "1990" ,"1989" ,"1988" ,"1987", "1986", "1985", "1984", "1983",
                                  "1982", "1981" ,"1980", "1979", "1978", "1977", "1976" ,"1975" ,"1974" ,"1973", "1972", "1971", "1970", "1969",
                                  "1968", "1967" ,"1966", "1965", "1964", "1963", "1962" ,"1961" ,"1960" ,"1959", "1958", "1957", "1956", "1955",
                                  "1954", "1953" ,"1952", "1951", "1950" };
    private ArrayAdapter<String> yearadapter;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_sign_up, viewGroup, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);

       this.spinnergender = (MaterialSpinner) view.findViewById(R.id.spinner_gender);
       this.spinneryear = (MaterialSpinner) view.findViewById(R.id.spinner_year);


      //  this.gender    = (EditText) view.findViewById(R.id.spinner_gender);
        this.firstname = (EditText) view.findViewById(R.id.edt_first_name);
        this.lastname  = (EditText) view.findViewById(R.id.edt_last_name);
    //    this.yearofbirth = (EditText) view.findViewById(R.id.spinner_year);
        this.email     = (EditText) view.findViewById(R.id.edt_email);
        this.password  = (EditText) view.findViewById(R.id.edt_password);
        this.passwordconfirm = (EditText) view.findViewById(R.id.edt_confirm);


        this.visible_gender   = (TextView) view.findViewById(R.id.visible_gender);
        this.visible_firstname = (TextView) view.findViewById(R.id.visible_first_name);
        this.visible_lastname = (TextView) view.findViewById(R.id.visible_last_name);
        this.visible_yearofbirth = (TextView) view.findViewById(R.id.visible_spinner_year);
        this.visible_email = (TextView) view.findViewById(R.id.visible_edit_email);
        this.visible_password = (TextView) view.findViewById(R.id.visible_edt_password);
        this.visible_passwordconfirm = (TextView) view.findViewById(R.id.visible_edt_confirm);
        this.edt_sendtext = (TextView) view.findViewById(R.id.edt_sendtext);
        this.chk_t_and_c  = (CheckBox) view.findViewById(R.id.chk_t_and_c);


        this.tvLogin = (TextView) view.findViewById(R.id.tv_login);
        this.tvSignUpWithEmail = (TextView) view.findViewById(R.id.tv_sign_up_with_email);
        this.tvLogin.setOnClickListener(this);
        this.tvSignUpWithEmail.setOnClickListener(this);

        this.adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, this.genderItem);
        this.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((MaterialSpinner) view.findViewById(R.id.spinner_gender)).setAdapter((SpinnerAdapter) this.adapter);
        this.yearadapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, this.yearItem);
        this.adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((MaterialSpinner) view.findViewById(R.id.spinner_year)).setAdapter((SpinnerAdapter) this.yearadapter);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_login) {
            openLoginFragment();
        } else if (id == R.id.tv_sign_up_with_email) {

            spinnergender.setVisibility(View.VISIBLE);
            firstname.setVisibility(View.VISIBLE);
            spinneryear.setVisibility(View.VISIBLE);
            lastname.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
            passwordconfirm.setVisibility(View.VISIBLE);
            edt_sendtext.setVisibility(View.VISIBLE);
            chk_t_and_c.setVisibility(View.VISIBLE);

            visible_gender.setVisibility(View.INVISIBLE);
            visible_firstname.setVisibility(View.INVISIBLE);
            visible_lastname.setVisibility(View.INVISIBLE);
            visible_yearofbirth.setVisibility(View.INVISIBLE);
            visible_email.setVisibility(View.INVISIBLE);
            visible_password.setVisibility(View.INVISIBLE);
            visible_passwordconfirm.setVisibility(View.INVISIBLE);

            openAddMobileFragment();
        }
    }

    private void openLoginFragment() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction beginTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
        beginTransaction.replace(R.id.frame_container, loginFragment);
        beginTransaction.disallowAddToBackStack();
        beginTransaction.commit();
    }

    private void openAddMobileFragment() {


        if(this.genderItem.equals("") || this.firstname.getText().toString().equals("") ||
                this.lastname.getText().toString().equals("") || this.yearItem.equals("") ||
                this.email.getText().toString().equals("") || this.password.getText().toString().equals("") ||
                this.passwordconfirm.getText().toString().equals("")) {
            visible_gender.setVisibility(View.VISIBLE);
            visible_firstname.setVisibility(View.VISIBLE);
            visible_lastname.setVisibility(View.VISIBLE);
            visible_yearofbirth.setVisibility(View.VISIBLE);
            visible_email.setVisibility(View.VISIBLE);
            visible_password.setVisibility(View.VISIBLE);
            visible_passwordconfirm.setVisibility(View.VISIBLE);
        }else {
            AddMobileFragment addMobileFragment = new AddMobileFragment();
            FragmentTransaction beginTransaction = Objects.requireNonNull(getFragmentManager()).beginTransaction();
            beginTransaction.replace(R.id.frame_container, addMobileFragment);
            beginTransaction.disallowAddToBackStack();
            beginTransaction.commit();
        }
    }
}
