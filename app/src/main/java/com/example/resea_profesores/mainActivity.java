package com.example.resea_profesores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class mainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button google_sign_in;
    private Button b;
    private TextView t;
    private EditText username;
    private EditText password;
    private Handler handler = new Handler(Looper.getMainLooper());

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private GoogleSignInClient googleSignInClient;
    private int RC_SIGN_IN = 20;

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, menu_principal.class));
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuración de Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        // Configuración del login tradicional
        username = findViewById(R.id.editTextText);
        password = findViewById(R.id.editTextText2);
        t = findViewById(R.id.textView6);
        b = findViewById(R.id.button);
        b.setOnClickListener(this);

        // Configuración de Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        google_sign_in = findViewById(R.id.button2);
        google_sign_in.setOnClickListener(v -> signInWithGoogle());
    }


    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.e("GoogleSignIn", "Error en inicio de sesión", e);
                Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        updateUI(null);
                    }
                });
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
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
            }, 1500);
        } else {
            t.setText("Usuario o contraseña incorrectos.");
            handler.postDelayed(() -> t.setText(""), 3000);
        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(mainActivity.this, menu_principal.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(mainActivity.this, "Authentication Failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}