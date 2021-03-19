package com.estudo.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        showDessertOrder();
        onRadioButtonClicked();
    }

    private void showDessertOrder() {
        TextView text_order = findViewById(R.id.text_order);
        Intent intent = getIntent();
        String message = "Order: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        text_order.setText(message);
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    private void onRadioButtonClicked() {
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.radio_same_day) {
                showToast(getString(R.string.radio_same_day));
            }
            if (checkedId == R.id.radio_next_day) {
                showToast(getString(R.string.radio_next_day));
            }
            if (checkedId == R.id.radio_pick_up) {
                showToast(getString(R.string.radio_pick_up));
            }
        });
    }
}