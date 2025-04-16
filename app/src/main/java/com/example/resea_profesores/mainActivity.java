package com.example.resea_profesores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class mainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b;
    private TextView t;
    private EditText username;
    private EditText password;
    private Handler handler = new Handler(Looper.getMainLooper());

        @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editTextText);
        password = findViewById(R.id.editTextText2);
        t = findViewById(R.id.textView6);

        b = findViewById(R.id.button);
        b.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override    public void onClick(View view) {
        String Username = username.getText().toString(), Password = password.getText().toString();
        String Verified_User = "Admin", Verified_Password = "123";

        if (Username.equals(Verified_User) && Password.equals(Verified_Password)) {
            t.setText("Iniciando sesión");
            handler.postDelayed(() -> t.setText("Iniciando sesión."), 500);
            handler.postDelayed(() -> t.setText("Iniciando sesión.."), 500);
            handler.postDelayed(() -> t.setText("Iniciando sesión..."), 1000);
            handler.postDelayed(() -> {
                Intent intent = new Intent(mainActivity.this, menu_principal.class);
                startActivity(intent);
                t.setText("");
            }, 1500); //cambios
        } else {
            t.setText("Usuario o contraseña incorrectos.");
            handler.postDelayed(() -> t.setText(""), 3000);
        }
    }
}