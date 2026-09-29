package com.example.alae_atelier2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Example3Activity extends AppCompatActivity {

    private static final int INTERVALLE_MS = 200;

    private ProgressBar progressBar;
    private TextView tvProgress;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private int progression = 0;
    private boolean enCours = false;

    // Bundle reçu de Example2, retransmis à Example4
    private Bundle data;

    private final Runnable miseAJour = new Runnable() {
        @Override
        public void run() {
            progression++;
            if (progression > 100) progression = 0;

            progressBar.setProgress(progression);
            tvProgress.setText(progression + " %");

            if (enCours) {
                handler.postDelayed(this, INTERVALLE_MS);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example3);

        progressBar = findViewById(R.id.progressBar);
        tvProgress = findViewById(R.id.tvProgress);

        // Réception du Bundle
        Bundle bundle = getIntent().getBundleExtra(Keys.BUNDLE);
        data = (bundle != null) ? bundle : new Bundle();

        // Envoi vers Example4
        findViewById(R.id.btnNext).setOnClickListener(v -> {
            Intent i = new Intent(Example3Activity.this, Example4Activity.class);
            i.putExtra(Keys.BUNDLE, data);
            startActivity(i);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!enCours) {
            enCours = true;
            handler.postDelayed(miseAJour, INTERVALLE_MS);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        enCours = false;
        handler.removeCallbacks(miseAJour);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}