package com.example.resea_profesores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

public class menu_principal extends AppCompatActivity {

    private VideoView v;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.menu_principal);

        v = (VideoView) findViewById(R.id.videoView);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);

        v.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        v.setMediaController(mediaController);
        mediaController.setAnchorView(v);
        v.start();



    }
}
