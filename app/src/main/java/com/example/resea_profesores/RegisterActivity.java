package com.example.resea_profesores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private Button btnCreateAcc;

    private Button backButton;

    private ProgressBar progressBar;

    private EditText editEmailSignup;
    private EditText editPasswordSignUp;

    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        progressBar = findViewById(R.id.progressBarRegister);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, mainActivity.class));
                finish();
            }
            });

        mAuth = FirebaseAuth.getInstance();

        editEmailSignup = findViewById(R.id.editEmailSignup);
        editPasswordSignUp = findViewById(R.id.editPasswordSignup);
        btnCreateAcc = findViewById(R.id.btnCreateAcc);

        btnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = editEmailSignup.getText().toString();
                password = editPasswordSignUp.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "Por favor, ingrese todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(RegisterActivity.this, "Ingrese un email válido", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (password.length() < 6) {
                        Toast.makeText(RegisterActivity.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Usuario creado: " + Objects.requireNonNull(mAuth.getCurrentUser()).getDisplayName(), Toast.LENGTH_SHORT).show();
                                Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
                                mAuth.getCurrentUser().getUid();
                                startActivity(new Intent(RegisterActivity.this, mainActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,
                                        "Error: " + Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                }

            });
        }
}
