package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ole.oleandroid.R;
import com.example.ole.oleandroid.controller.DAO.LoginDAO;

public class ForgotPassword extends AppCompatActivity {
    EditText email;
    TextInputEditText changePassword, confirmChangePassword;
    Button confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        final Drawable tickDone = getResources().getDrawable(R.drawable.ic_done_black_24dp);

        email = findViewById(R.id.email);
        changePassword = findViewById(R.id.changePassword);
        confirmChangePassword = findViewById(R.id.confirmChangePassword);
        confirmPassword = findViewById(R.id.confirmPassword);

        changePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (isStrong(s.toString())) {
                    changePassword.setError("Good", tickDone);
                } else {
                    changePassword.setError("8 or more characters \n" +
                            "Min 1 Upper Case\n" +
                            "Min 1 numeral (0-9)\n" +
                            "Min 1 letter in Lower Case");

                }

            }
        });

        confirmChangePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
                //System.out.println(confirmChangePassword.getText().toString() + " matches " + changePassword.getText().toString());
                if (confirmChangePassword.getText().toString().equals(changePassword.getText().toString())) {
                    confirmChangePassword.setError("Good", tickDone);
                } else {
                    confirmChangePassword.setError("Password confirmation does not match.");
                }
            }
        });

        confirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = email.getText().toString();
                String password = confirmChangePassword.getText().toString();
                Boolean status = LoginDAO.changePassword(inputEmail, password);

                if (status){
                    Toast.makeText(getBaseContext(), "Password change successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgotPassword.this, Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "Password change failed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean isStrong(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,20}$");
        //return password.matches("^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,20}$");
    }
}
