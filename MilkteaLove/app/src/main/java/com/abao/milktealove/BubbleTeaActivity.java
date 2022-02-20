package com.abao.milktealove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BubbleTeaActivity extends AppCompatActivity {

    LinearLayout Layout1, Layout2, Layout3, Layout4, Layout5;
    TextView Banner, Flavor1, Flavor2, Flavor3, Flavor4, Flavor5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_tea);

        Banner = findViewById(R.id.tvTeaTypeFlavor);
        // Flavor Layout for ClickListener
        Layout1 = findViewById(R.id.Mocha);
        Layout2 = findViewById(R.id.House);
        Layout3 = findViewById(R.id.Grass);
        Layout4 = findViewById(R.id.Almond);
        Layout5 = findViewById(R.id.Cream);

        // Flavor Text
        Flavor1 = findViewById(R.id.flvr1);
        Flavor2 = findViewById(R.id.flvr2);
        Flavor3 = findViewById(R.id.flvr3);
        Flavor4 = findViewById(R.id.flvr4);
        Flavor5 = findViewById(R.id.flvr5);

        //Displaying the Banner
        String BANNER = getIntent().getStringExtra("banner");
        Banner.setText(BANNER);

        // Getting the Intent Extra
        String s1 = getIntent().getStringExtra("s1");
        String s2 = getIntent().getStringExtra("s2");
        String s3 = getIntent().getStringExtra("s3");
        String s4 = getIntent().getStringExtra("s4");
        String s5 = getIntent().getStringExtra("s5");

        // Displaying the Flavors
        Flavor1.setText(s1);
        Flavor2.setText(s2);
        Flavor3.setText(s3);
        Flavor4.setText(s4);
        Flavor5.setText(s5);



        Layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String BANNER = Banner.getText().toString();
                String FLAVOR = Flavor1.getText().toString();
                Intent intent = new Intent(BubbleTeaActivity.this, BubbleTeaSizeActivity.class);
                intent.putExtra("banner", BANNER);
                intent.putExtra("flavor", FLAVOR);
                startActivity(intent);

            }
        });

        Layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String BANNER = Banner.getText().toString();
                String FLAVOR = Flavor2.getText().toString();
                Intent intent = new Intent(BubbleTeaActivity.this, BubbleTeaSizeActivity.class);
                intent.putExtra("banner", BANNER);
                intent.putExtra("flavor", FLAVOR);
                startActivity(intent);

            }
        });

        Layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String BANNER = Banner.getText().toString();
                String FLAVOR = Flavor3.getText().toString();
                Intent intent = new Intent(BubbleTeaActivity.this, BubbleTeaSizeActivity.class);
                intent.putExtra("banner", BANNER);
                intent.putExtra("flavor", FLAVOR);
                startActivity(intent);

            }
        });

        Layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String BANNER = Banner.getText().toString();
                String FLAVOR = Flavor4.getText().toString();
                Intent intent = new Intent(BubbleTeaActivity.this, BubbleTeaSizeActivity.class);
                intent.putExtra("banner", BANNER);
                intent.putExtra("flavor", FLAVOR);
                startActivity(intent);

            }
        });

        Layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String BANNER = Banner.getText().toString();
                String FLAVOR = Flavor5.getText().toString();
                Intent intent = new Intent(BubbleTeaActivity.this, BubbleTeaSizeActivity.class);
                intent.putExtra("banner", BANNER);
                intent.putExtra("flavor", FLAVOR);
                startActivity(intent);

            }
        });

    }

}