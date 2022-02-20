package com.abao.milktealove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BubbleTeaSizeActivity extends AppCompatActivity {

    LinearLayout small, medium, large;
    TextView Banner, myFlavor, smallSize, mediumSize, largeSize, priceSmall, priceMedium, priceLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_tea_size);

        Banner = findViewById(R.id.tvTeaTypeSize);
        // Layout for ClickListener
        small = findViewById(R.id.small);
        medium = findViewById(R.id.medium);
        large = findViewById(R.id.large);
        // Text
        smallSize = findViewById(R.id.tvSmall);
        mediumSize = findViewById(R.id.tvMedium);
        largeSize = findViewById(R.id.tvLarge);
        // Price
        priceSmall = findViewById(R.id.tvPriceSmall);
        priceMedium = findViewById(R.id.tvPriceMedium);
        priceLarge = findViewById(R.id.tvPriceLarge);

        //Displaying the Banner
        String BANNER = getIntent().getStringExtra("banner");
        Banner.setText(BANNER);

        // Displaying the chosen tea
        myFlavor = findViewById(R.id.tvMyFlavor);
        String FLAVOR = getIntent().getStringExtra("flavor");
        myFlavor.setText(FLAVOR);

        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String BANNER = Banner.getText().toString();
                String FLAVOR = myFlavor.getText().toString();
                String SIZE = smallSize.getText().toString();
                String PRICE = priceSmall.getText().toString();
                Intent intent = new Intent(BubbleTeaSizeActivity.this, AddOnsActivity.class);
                intent.putExtra("banner", BANNER);
                intent.putExtra("flavor", FLAVOR);
                intent.putExtra("size", SIZE);
                intent.putExtra("price", PRICE);
                startActivity(intent);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String BANNER = Banner.getText().toString();
                String FLAVOR = myFlavor.getText().toString();
                String SIZE = mediumSize.getText().toString();
                String PRICE = priceMedium.getText().toString();
                Intent intent = new Intent(BubbleTeaSizeActivity.this, AddOnsActivity.class);
                intent.putExtra("banner", BANNER);
                intent.putExtra("flavor", FLAVOR);
                intent.putExtra("size", SIZE);
                intent.putExtra("price", PRICE);
                startActivity(intent);

            }
        });

        large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String BANNER = Banner.getText().toString();
                String FLAVOR = myFlavor.getText().toString();
                String SIZE = largeSize.getText().toString();
                String PRICE = priceLarge.getText().toString();
                Intent intent = new Intent(BubbleTeaSizeActivity.this, AddOnsActivity.class);
                intent.putExtra("banner", BANNER);
                intent.putExtra("flavor", FLAVOR);
                intent.putExtra("size", SIZE);
                intent.putExtra("price", PRICE);
                startActivity(intent);

            }
        });

    }
}