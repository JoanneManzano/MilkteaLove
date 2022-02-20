package com.abao.milktealove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPageActivity extends AppCompatActivity {

    private TextView signUp;
    private Button login;
    private EditText email, password;
    private ProgressDialog dialog;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.tvSignUp);
        dialog = new ProgressDialog(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPageActivity.this, SignUpPageActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });


    }

    private void Login() {
        // ADMIN ACCOUNT
        String ADUSERNAME = "admin";
        String ADPASSWORD = "admin123";
        String EMAIL = email.getText().toString().trim();
        String PASSWORD = password.getText().toString().trim();

        if (TextUtils.isEmpty(EMAIL)) {
            email.setError("Enter your Email");
            return;
        }
        else if (TextUtils.isEmpty(PASSWORD)) {
            password.setError("Enter your Password");
            return;
        }
        else if ((EMAIL.equals(ADUSERNAME)) && (PASSWORD.equals(ADPASSWORD))) {
            dialog.setMessage("Loading...");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
            Toast.makeText( LoginPageActivity.this, "Logged in as an Admin", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginPageActivity.this, OrderListActivity.class);
            startActivity(intent);
        }
        else {

            dialog.setMessage("Loading...");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
            auth.signInWithEmailAndPassword(EMAIL, PASSWORD).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginPageActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginPageActivity.this, MenuActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginPageActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                    }
                    dialog.dismiss();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoginPageActivity.super.onBackPressed();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}