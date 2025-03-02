package com.example.resea_profesores;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class initialscreen extends AppCompatActivity implements View.OnClickListener {

    private Button btnCalcular, btnAdd, btnSubtract, btnMultiply, btnDivide;
    private TextView tvResult, tvOperator;
    private EditText etNum1, etNum2;
    private String currentOperation = "+"; // Default operation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        // Initialize EditText fields
        etNum1 = findViewById(R.id.editTextText1);
        etNum2 = findViewById(R.id.editTextText3);

        // Initialize TextViews
        tvResult = findViewById(R.id.textView5);
        tvOperator = findViewById(R.id.textViewOperator);
        tvOperator.setText("+"); // Set default operator

        // Initialize operation buttons
        btnAdd = findViewById(R.id.buttonAdd);
        btnSubtract = findViewById(R.id.buttonSubtract);
        btnMultiply = findViewById(R.id.buttonMultiply);
        btnDivide = findViewById(R.id.buttonDivide);

        // Initialize calculate button
        btnCalcular = findViewById(R.id.button2);

        // Set click listeners for all buttons
        btnAdd.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnCalcular.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        int id = view.getId();

        // Handle operation button clicks
        if (id == R.id.buttonAdd) {
            currentOperation = "+";
            tvOperator.setText("+");
        } else if (id == R.id.buttonSubtract) {
            currentOperation = "-";
            tvOperator.setText("-");
        } else if (id == R.id.buttonMultiply) {
            currentOperation = "*";
            tvOperator.setText("×");
        } else if (id == R.id.buttonDivide) {
            currentOperation = "/";
            tvOperator.setText("÷");
        } else if (id == R.id.button2) {
            // Calculate button pressed - perform the calculation
            calculateResult();
        }
    }

    @SuppressLint("SetTextI18n")
    private void calculateResult() {
        // Get input values
        String strNum1 = etNum1.getText().toString().trim();
        String strNum2 = etNum2.getText().toString().trim();

        // Validate inputs
        if (strNum1.isEmpty() || strNum2.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa ambos números.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(strNum1);
            double num2 = Double.parseDouble(strNum2);
            double resultado = 0;

            // Perform the selected operation
            switch (currentOperation) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        Toast.makeText(this, "No se puede dividir por cero.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    resultado = num1 / num2;
                    break;
            }

            // Display the result
            tvResult.setText("El resultado es: " + resultado);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, ingresa números válidos.", Toast.LENGTH_SHORT).show();
        }
    }
}