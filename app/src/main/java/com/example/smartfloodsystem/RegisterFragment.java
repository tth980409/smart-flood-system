package com.example.smartfloodsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class RegisterFragment extends Fragment {
    private User mUser;
    private EditText mEmailField;
    private String mEmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private EditText mPasswordField;
    private EditText mPasswordRepeatField;
    private EditText mFirstNameField;
    private EditText mLastNameField;
    private Spinner mLocationField;
    private Drawable mWarningIcon;
    private Button mRegisterButton;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        mWarningIcon = (Drawable) getResources().getDrawable(R.drawable.ic_alert_red_icon);
        mWarningIcon.setBounds(0,0, mWarningIcon.getIntrinsicWidth(), mWarningIcon.getIntrinsicHeight());

        mEmailField = (EditText) v.findViewById(R.id.user_email);
        mEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mEmailField.getText().toString().trim().isEmpty())
                {
                    mEmailField.setError(getResources().getString(R.string.register_error_empty_email), mWarningIcon);
                }
                else if (!mEmailField.getText().toString().trim().matches(mEmailPattern))
                {
                    mEmailField.setError(getResources().getString(R.string.register_error_invalid_email), mWarningIcon);
                }
                else
                {
                    String mEmail = mEmailField.getText().toString();
                }
            }
        });

        mPasswordField = (EditText) v.findViewById(R.id.user_password);
        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mPasswordField.getText().toString().isEmpty())
                {
                    mPasswordField.setError(getResources().getString(R.string.register_error_empty_password), mWarningIcon);
                }
                else if (mPasswordField.getText().toString().length() <= 8 || mPasswordField.getText().toString().length() >= 20)
                {
                    mPasswordField.setError(getResources().getString(R.string.register_error_invalid_password_length), mWarningIcon);
                }
            }
        });

        mPasswordRepeatField = (EditText) v.findViewById(R.id.user_repeat_password);
        mPasswordRepeatField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mPasswordRepeatField.getText().toString().isEmpty())
                {
                    mPasswordRepeatField.setError(getResources().getString(R.string.register_error_empty_repeat_password), mWarningIcon);
                }
                else if (!mPasswordField.getText().toString().equals(mPasswordRepeatField.getText().toString()))
                {
                    mPasswordRepeatField.setError(getResources().getString(R.string.register_error_different_password), mWarningIcon);
                }
                else
                {
                    String mPassword = mPasswordRepeatField.getText().toString();
                }
            }
        });

        mFirstNameField = (EditText) v.findViewById(R.id.user_first_name);
        mFirstNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mFirstNameField.getText().toString().isEmpty())
                {
                    mFirstNameField.setError(getResources().getString(R.string.register_error_empty_firstname), mWarningIcon);
                }
                else
                {
                    String mFirstName = mFirstNameField.getText().toString();
                }
            }
        });

        mLastNameField = (EditText) v.findViewById(R.id.user_last_name);
        mLastNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (mLastNameField.getText().toString().isEmpty())
                {
                    mLastNameField.setError(getResources().getString(R.string.register_error_empty_lastname), mWarningIcon);
                }
                else
                {
                    String mLastName = mLastNameField.getText().toString();
                }
            }
        });

        mLocationField = (Spinner) v.findViewById(R.id.user_location);
        String[] locations = new String[]{"PJS7", "PJS9", "PJS11", "Jalan Universiti"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, locations);
        mLocationField.setAdapter(adapter);

        mLocationField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mLocation = parent.getItemAtPosition(position).toString();
                //Log.d("myApp", mLocation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ((TextView)mLocationField.getSelectedView()).setError(getResources().getString(R.string.register_error_empty_location), mWarningIcon);
            }
        });

        mRegisterButton = (Button) v.findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEmailField.getError() != null || mPasswordField.getError() != null || mPasswordRepeatField.getError() != null ||
                        mFirstNameField.getError() != null || mLastNameField.getError() != null)
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage(R.string.register_error_invalid_field);
                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else if (mEmailField.getText().toString() == "" || mPasswordField.getText().toString() == "" || mPasswordRepeatField.getText().toString() == ""
                 || mFirstNameField.getText().toString() == "" || mLastNameField.getText().toString() == "")
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setMessage(R.string.register_error_empty_field);
                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else
                {
                    User user = new User();

                    UUID UID = user.getUID();
                    user.setUserEmail(mEmailField.getText().toString());
                    user.setUserPassword(mPasswordRepeatField.getText().toString());
                    user.setUserFirstName(mFirstNameField.getText().toString());
                    user.setUserLastName(mLastNameField.getText().toString());
                    user.setUserLocation(mLocationField.getSelectedItem().toString());

                    //Log.d("myApp", mLocationField.getSelectedItem().toString());

                    AlertDialog.Builder alertDialogBuilder_2 = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder_2.setMessage(R.string.register_successful);
                    alertDialogBuilder_2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog_2 = alertDialogBuilder_2.create();
                    alertDialog_2.show();
                }
            }
        });

        return v;
    }
}