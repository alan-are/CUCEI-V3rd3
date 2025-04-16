package com.example.resea_profesores;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class webview extends AppCompatActivity {
    private WebView webView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_xml);

        webView = findViewById(R.id.webView);

        // Configuración del WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Habilitar JavaScript

        // Forzar la apertura de enlaces en WebView en lugar del navegador
        webView.setWebViewClient(new WebViewClient());

        // Cargar una página web
        webView.loadUrl("https://es.wikipedia.org/wiki/Wikipedia:Portada");
    }

    // Manejo del botón "Atrás" para volver en la navegación del WebView
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}