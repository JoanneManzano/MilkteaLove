package com.abao.milktealove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class PlaceOrderActivity extends AppCompatActivity {

    TextView randomID, finalFlavor, finalSize, finalPrice, addOns, showquantity, hiddenQuantity, hiddenPrice;
    EditText receiver, phone, location, quantity;
    Button placeOrder;
    FirebaseAuth auth;
    FirebaseUser fbuser;
    FirebaseDatabase db;
    DatabaseReference reference;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        
        final Random myRandom = new Random();
        randomID = findViewById(R.id.tvRandomNumer);
        randomID.setText(String.valueOf(myRandom.nextInt(100)));

        receiver = findViewById(R.id.etName);
        phone = findViewById(R.id.etPhone);
        location = findViewById(R.id.etAddress);

        finalFlavor = findViewById(R.id.tvFinalFlavor);
        finalSize = findViewById(R.id.tvFinalSize);
        quantity = findViewById(R.id.etQuantity);
        showquantity = findViewById(R.id.tvShowQuantity);
        finalPrice = findViewById(R.id.tvFinalPrice);
        addOns = findViewById(R.id.tvAddOns);
        placeOrder = findViewById(R.id.btnPlaceOrder);

        hiddenQuantity = findViewById(R.id.tvHiddenQuantity);
        hiddenPrice = findViewById(R.id.tvHiddenPrice);

        // Displaying Credentials from Firebase Database
        auth = FirebaseAuth.getInstance();
        fbuser = auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("User");
        userID = fbuser.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                if (user != null) {
                    String name, address, phonenumber;
                    name = user.name;
                    address = user.address;
                    phonenumber = user.phonenumber;

                    receiver.setText(name);
                    location.setText(address);
                    phone.setText(phonenumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PlaceOrderActivity.this, "There is an Error", Toast.LENGTH_LONG).show();
            }
        });


        String FINALFLAVOR = getIntent().getStringExtra("flavor");
        String FINALSIZE = getIntent().getStringExtra("size");
        String FINALADDONS = getIntent().getStringExtra("addons");
        String FINALQUANTITY = getIntent().getStringExtra("quantity");
        String FINALPRICE = getIntent().getStringExtra("totalprice");

        finalFlavor.setText(FINALFLAVOR);
        finalSize.setText(FINALSIZE);
        addOns.setText(FINALADDONS);
        showquantity.setText(FINALQUANTITY);

        // Multiplying the Final price in Quantity
        hiddenQuantity.setText(FINALQUANTITY);
        hiddenPrice.setText(FINALPRICE);
        int INT_FINALQUANTITY = Integer.parseInt(hiddenQuantity.getText().toString());
        int INT_FINALPRICE = Integer.parseInt(hiddenPrice.getText().toString());
        int INT_TOTAL = INT_FINALQUANTITY * INT_FINALPRICE;
        finalPrice.setText(INT_TOTAL + ".00");

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Database Path
                db = FirebaseDatabase.getInstance();
                reference = db.getReference("Order");

                String RANDOMID = randomID.getText().toString();
                String PASSEDFLAVOR = finalFlavor.getText().toString();
                String PASSEDSIZE = finalSize.getText().toString();
                String PASSEDADDONS = addOns.getText().toString();
                String PASSEDQUANTITY = showquantity.getText().toString();
                String RECEIVER = receiver.getText().toString();
                String ADDRESS = location.getText().toString();
                String PHONE = phone.getText().toString();
                String PASSEDPRICE = finalPrice.getText().toString();

                Order order = new Order(RANDOMID, PASSEDFLAVOR, PASSEDSIZE, PASSEDADDONS, PASSEDQUANTITY, RECEIVER, ADDRESS, PHONE, PASSEDPRICE);


                reference.child(RANDOMID).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(PlaceOrderActivity.this, "Your order has been submitted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PlaceOrderActivity.this, MenuActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(PlaceOrderActivity.this, "Your order was not able to submit, please try again", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        }
}