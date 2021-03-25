package com.estudo.droidcafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "droid_cafe_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        imageClicked();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void imageClicked() {
        ImageView donut = findViewById(R.id.img_donut);
        ImageView ice_cream = findViewById(R.id.img_ice_cream);
        ImageView froyo = findViewById(R.id.img_froyo);

        donut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageForEachImage(getString(R.string.donut_order_message));
                cartClicked(getString(R.string.donut_order_message));
            }
        });

        ice_cream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageForEachImage(getString(R.string.ice_cream_order_message));
                cartClicked(getString(R.string.ice_cream_order_message));
            }
        });

        froyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageForEachImage(getString(R.string.froyo_order_message));
                cartClicked(getString(R.string.froyo_order_message));
            }
        });
    }

    private void messageForEachImage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void cartClicked(String order) {
        FloatingActionButton fab = findViewById(R.id.fab_shopping);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, order);
                startActivity(intent);
            }
        });
    }
}