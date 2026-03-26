package com.example.a2_1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerCategory, spinnerFrom, spinnerTo;
    EditText editTextValue;
    Button buttonConvert;
    TextView textViewResult;

    String[] categories = {"Currency", "Fuel/Distance", "Temperature"};

    String[] currencyUnits = {"USD", "AUD", "EUR", "JPY", "GBP"};
    String[] fuelUnits = {"mpg", "km/L", "Gallon", "Liter", "Nautical Mile", "Kilometer"};
    String[] tempUnits = {"Celsius", "Fahrenheit", "Kelvin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        editTextValue = findViewById(R.id.editTextValue);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categories
        );
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        updateUnits("Currency");

        spinnerCategory.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                updateUnits(spinnerCategory.getSelectedItem().toString());
                textViewResult.setText("Result will appear here");
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });

        buttonConvert.setOnClickListener(v -> convert());
    }

    private void updateUnits(String category) {
        String[] units;

        if (category.equals("Currency")) {
            units = currencyUnits;
        } else if (category.equals("Fuel/Distance")) {
            units = fuelUnits;
        } else {
            units = tempUnits;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                units
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
    }

    private void convert() {

        String category = spinnerCategory.getSelectedItem().toString();
        String from = spinnerFrom.getSelectedItem().toString();
        String to = spinnerTo.getSelectedItem().toString();
        String input = editTextValue.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        double value;

        try {
            value = Double.parseDouble(input);
        } catch (Exception e) {
            Toast.makeText(this, "Invalid number entered", Toast.LENGTH_SHORT).show();
            return;
        }

        if (from.equals(to)) {
            textViewResult.setText("Result: " + String.format(Locale.getDefault(), "%.2f", value) + " (Same Unit)");
            Toast.makeText(this, "Same unit selected", Toast.LENGTH_SHORT).show();
            return;
        }

        if (category.equals("Fuel/Distance") && value < 0) {
            Toast.makeText(this, "Fuel values cannot be negative", Toast.LENGTH_SHORT).show();
            return;
        }

        double result = 0;

        if (category.equals("Currency")) {
            result = currency(from, to, value);
        } else if (category.equals("Fuel/Distance")) {
            result = fuel(from, to, value);

            if (result == 0) {
                Toast.makeText(this, "Invalid conversion for Fuel/Distance", Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (category.equals("Temperature")) {
            result = temperature(from, to, value);
        }

        textViewResult.setText("Result: " + String.format(Locale.getDefault(), "%.2f", result));
    }

    private double currency(String from, String to, double v) {

        double usd = 0;

        if (from.equals("USD")) usd = v;
        else if (from.equals("AUD")) usd = v / 1.55;
        else if (from.equals("EUR")) usd = v / 0.92;
        else if (from.equals("JPY")) usd = v / 148.50;
        else if (from.equals("GBP")) usd = v / 0.78;

        if (to.equals("USD")) return usd;
        if (to.equals("AUD")) return usd * 1.55;
        if (to.equals("EUR")) return usd * 0.92;
        if (to.equals("JPY")) return usd * 148.50;
        if (to.equals("GBP")) return usd * 0.78;

        return 0;
    }

    private double fuel(String from, String to, double v) {

        if (from.equals("mpg") && to.equals("km/L")) return v * 0.425;
        if (from.equals("km/L") && to.equals("mpg")) return v / 0.425;

        if (from.equals("Gallon") && to.equals("Liter")) return v * 3.785;
        if (from.equals("Liter") && to.equals("Gallon")) return v / 3.785;

        if (from.equals("Nautical Mile") && to.equals("Kilometer")) return v * 1.852;
        if (from.equals("Kilometer") && to.equals("Nautical Mile")) return v / 1.852;

        return 0;
    }

    private double temperature(String from, String to, double v) {

        if (from.equals("Celsius") && to.equals("Fahrenheit")) return (v * 1.8) + 32;
        if (from.equals("Fahrenheit") && to.equals("Celsius")) return (v - 32) / 1.8;

        if (from.equals("Celsius") && to.equals("Kelvin")) return v + 273.15;
        if (from.equals("Kelvin") && to.equals("Celsius")) return v - 273.15;

        if (from.equals("Fahrenheit") && to.equals("Kelvin")) return ((v - 32) / 1.8) + 273.15;
        if (from.equals("Kelvin") && to.equals("Fahrenheit")) return ((v - 273.15) * 1.8) + 32;

        return v;
    }
}