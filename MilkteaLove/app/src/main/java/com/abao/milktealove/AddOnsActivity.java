package com.abao.milktealove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class AddOnsActivity extends AppCompatActivity {

    CheckBox cb1, cb2, cb3, cb4, cb5, cb6;
    TextView Banner, tvquantity, quantity, hiddenFlavor, hiddenSize, hiddenPrice, hiddenAddOns, totalPrice;
    Button proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ons);

        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        cb5 = findViewById(R.id.cb5);
        cb6 = findViewById(R.id.cb6);

        Banner = findViewById(R.id.tvTeaTypeAddOns);
        tvquantity = findViewById(R.id.tvQuantity);
        quantity = findViewById(R.id.etQuantity);
        hiddenFlavor = findViewById(R.id.tvHiddenFlavor);
        hiddenSize = findViewById(R.id.tvHiddenSize);
        hiddenPrice = findViewById(R.id.tvHiddenPrice);
        hiddenAddOns = findViewById(R.id.tvHiddenAddOns);
        totalPrice = findViewById(R.id.tvHiddenTotalPrice);
        proceed = findViewById(R.id.btnProceed);

        //Displaying the Banner
        String BANNER = getIntent().getStringExtra("banner");
        Banner.setText(BANNER);

        // Passing the Extra
        String flavor = getIntent().getStringExtra("flavor");
        String size = getIntent().getStringExtra("size");
        String price = getIntent().getStringExtra("price");

        // Displaying the Flavor for Quantity TextView
        tvquantity.setText(flavor + " Quantity:");

        // Displaying in the Hidden TextView for intent Extra
        hiddenFlavor.setText(flavor);
        hiddenSize.setText(size);
        hiddenPrice.setText(price);

        // Hide the HiddenTextView
        hiddenFlavor.setVisibility(View.GONE);
        hiddenSize.setVisibility(View.GONE);
        hiddenPrice.setVisibility(View.GONE);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "";
                int p = 0;

                if (cb1.isChecked()){
                    s += cb1.getText().toString() + ", ";
                    p += 15;
                }
                if (cb2.isChecked()){
                    s += cb2.getText().toString() + ", ";
                    p += 15;
                }
                if (cb3.isChecked()){
                    s += cb3.getText().toString() + ", ";
                    p += 15;
                }
                if (cb4.isChecked()){
                    s += cb4.getText().toString() + ", ";
                    p += 15;
                }
                if (cb5.isChecked()){
                    s += cb5.getText().toString() + ", ";
                    p += 15;
                }
                if (cb6.isChecked()){
                    s += cb6.getText().toString() + ", ";
                    p += 15;
                }


                // Converting string to int and adding the addon price
                int INT_TOTALPRICE = Integer.parseInt(hiddenPrice.getText().toString()) + p;
                // Converting int to string
                String STRING_SIZEPRICE = String.valueOf(INT_TOTALPRICE);

                hiddenAddOns.setText(s);

                String ADDONS = hiddenAddOns.getText().toString();
                String FLAVOR = hiddenFlavor.getText().toString();
                String SIZE = hiddenSize.getText().toString();
                String QUANTITY = quantity.getText().toString();
                Intent intent = new Intent(AddOnsActivity.this, PlaceOrderActivity.class);
                intent.putExtra("flavor", FLAVOR);
                intent.putExtra("size", SIZE);
                intent.putExtra("addons", ADDONS);
                intent.putExtra("totalprice", STRING_SIZEPRICE);
                intent.putExtra("quantity", QUANTITY);
                startActivity(intent);

            }
        });
    }
}