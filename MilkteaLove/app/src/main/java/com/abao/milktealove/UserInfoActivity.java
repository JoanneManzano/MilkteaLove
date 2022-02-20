package com.abao.milktealove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserInfoActivity extends AppCompatActivity {

    TextView backToMenu, showname, showaddress,showphonenumber, showemail, showpassword;
    Button logout;
    FirebaseAuth auth;
    FirebaseUser fbuser;
    DatabaseReference reference;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        backToMenu = findViewById(R.id.btnBack);

        showname = findViewById(R.id.tvName);
        showaddress = findViewById(R.id.tvAddress);
        showphonenumber = findViewById(R.id.tvPhone);
        showemail = findViewById(R.id.tvEmail);
        showpassword = findViewById(R.id.tvPassword);

        logout = findViewById(R.id.btnLogout);

        auth = FirebaseAuth.getInstance();
        fbuser = auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = fbuser.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                if (user != null) {
                    String name, address, phonenumber, email, password;
                    name = user.name;
                    address = user.address;
                    phonenumber = user.phonenumber;
                    email = user.email;
                    password = user.password;

                    showname.setText(name);
                    showaddress.setText(address);
                    showphonenumber.setText(phonenumber);
                    showemail.setText(email);
                    showpassword.setText(password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserInfoActivity.this, "There is an Error", Toast.LENGTH_LONG).show();
            }
        });

        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserInfoActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserInfoActivity.this, LoginPageActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}