package com.abao.milktealove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPageActivity extends AppCompatActivity {

    private EditText name, address, phone, email, password;
    private TextView login;
    private Button signUp;
    FirebaseAuth auth;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        auth = FirebaseAuth.getInstance();
        name = findViewById(R.id.etName);
        address = findViewById(R.id.etAddress);
        phone = findViewById(R.id.etPhone);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        signUp = findViewById(R.id.btnSignUp);
        login = findViewById(R.id.tvLogin);
        dialog = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpPageActivity.this, LoginPageActivity.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NAME = name.getText().toString().trim();
                String ADDRESS = address.getText().toString().trim();
                String PHONENUMBER = phone.getText().toString().trim();
                String EMAIL = email.getText().toString().trim();
                String PASSWORD = password.getText().toString().trim();

                if (TextUtils.isEmpty(NAME)){
                    name.setError("Enter you Name");
                    return;
                } else if (TextUtils.isEmpty(ADDRESS)) {
                    address.setError("Enter your Address");
                    return;
                } else if (TextUtils.isEmpty(PHONENUMBER)) {
                    phone.setError("Enter your Phone Number");
                    return;
                } else if (TextUtils.isEmpty(EMAIL)) {
                    email.setError("Enter your Email");
                    return;
                } else if (!ValidationEmail(EMAIL)) {
                    email.setError("Invalid Email");
                    return;
                } else if (TextUtils.isEmpty(ADDRESS)) {
                    password.setError("Enter your Address");
                    return;
                } else if (PASSWORD.length() < 5) {
                    password.setError("Password is too short");
                } else {
                    dialog.setMessage("Loading...");
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);

                    auth.createUserWithEmailAndPassword(EMAIL, PASSWORD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                User user = new User(NAME, ADDRESS, PHONENUMBER, EMAIL, PASSWORD);

                                FirebaseDatabase.getInstance().getReference("User")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUpPageActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SignUpPageActivity.this, LoginPageActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(SignUpPageActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(SignUpPageActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                            }
                            dialog.dismiss();
                        }
                    });
                }
            }
        });

    }

    private Boolean ValidationEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}