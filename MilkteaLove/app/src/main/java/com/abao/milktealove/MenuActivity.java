package com.abao.milktealove;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    LinearLayout bubbleTea, icedTea, smoothie, profile;
    TextView BubbleTea, IcedTea, Smoothie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        profile = findViewById(R.id.profile);

        // LinearLayout
        bubbleTea = findViewById(R.id.llBubbleTea);
        icedTea = findViewById(R.id.llIcedTea);
        smoothie = findViewById(R.id.llSmoothie);

        // TextView
        BubbleTea = findViewById(R.id.tvBubbleTea);
        IcedTea = findViewById(R.id.tvIcedTea);
        Smoothie = findViewById(R.id.tvSmoothie);

        bubbleTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String BANNER = BubbleTea.getText().toString();
                String s1 = "Mocha Bubble Tea";
                String s2 = "House Bubble Tea";
                String s3 = "Grass Jelly";
                String s4 = "Almond Milk Tea";
                String s5 = "Cream Cheese Float";
                Intent intent = new Intent(MenuActivity.this, BubbleTeaActivity.class);
                intent.putExtra("banner", BANNER);
                intent.putExtra("s1", s1);
                intent.putExtra("s2", s2);
                intent.putExtra("s3", s3);
                intent.putExtra("s4", s4);
                intent.putExtra("s5", s5);
                startActivity(intent);

            }
        });

        icedTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String BANNER = IcedTea.getText().toString();
                String s1 = "Black Tea";
                String s2 = "Blue Monster Tea";
                String s3 = "Green Tea";
                String s4 = "Apple Spice Tea";
                String s5 = "Cherry Vanilla Tea";
                Intent intent = new Intent(MenuActivity.this, BubbleTeaActivity.class);
                intent.putExtra("banner", BANNER);
                intent.putExtra("s1", s1);
                intent.putExtra("s2", s2);
                intent.putExtra("s3", s3);
                intent.putExtra("s4", s4);
                intent.putExtra("s5", s5);
                startActivity(intent);

            }
        });

        smoothie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String BANNER = Smoothie.getText().toString();
                String s1 = "Apple Crisp";
                String s2 = "Mango Smoothie";
                String s3 = "Banana Bread";
                String s4 = "Honey Lemon";
                String s5 = "Pumpkin Cheesecake";
                Intent intent = new Intent(MenuActivity.this, BubbleTeaActivity.class);
                intent.putExtra("banner", BANNER);
                intent.putExtra("s1", s1);
                intent.putExtra("s2", s2);
                intent.putExtra("s3", s3);
                intent.putExtra("s4", s4);
                intent.putExtra("s5", s5);
                startActivity(intent);

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, UserInfoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MenuActivity.super.onBackPressed();
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