package com.estudo.droidcafe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        showDessertOrder();
        onRadioButtonClicked();
        spinnerSetup();
        editTextEditor();
    }

    private void showDessertOrder() {
        TextView text_order = findViewById(R.id.text_order);
        Intent intent = getIntent();
        String message = "Order: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        text_order.setText(message);
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
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

    private void spinnerSetup() {
        Spinner spinner = findViewById(R.id.spinner_label);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.labels_array,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }

    private void editTextEditor() {
        EditText editText = findViewById(R.id.edit_phone);

        if (editText != null)
            editText.setOnEditorActionListener(
                    new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(
                                TextView textView, int actionId, KeyEvent keyEvent) {
                            boolean handled = false;

                            if (actionId == EditorInfo.IME_ACTION_SEND) {
                                dialNumber();
                                handled = true;
                            }
                            return handled;
                        }
                    });
    }

    private void dialNumber() {
        EditText editText = findViewById(R.id.edit_phone);
        String phoneNum = null;

        if (editText != null) phoneNum = "tel:" + editText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(phoneNum));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("", "ImplicitIntents: Can't handle this!");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerLabel = parent.getItemAtPosition(position).toString();
        showToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }
}