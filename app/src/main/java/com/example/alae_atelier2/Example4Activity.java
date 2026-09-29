package com.example.alae_atelier2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Example4Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example4);

        Bundle bundle = getIntent().getBundleExtra(Keys.BUNDLE);
        final Bundle data = bundle != null ? bundle : new Bundle();

        TextView tvNom = findViewById(R.id.tvNom);
        TextView tvEmail = findViewById(R.id.tvEmail);
        if (tvNom != null) tvNom.setText(data.getString(Keys.NOM, ""));
        if (tvEmail != null) tvEmail.setText(data.getString(Keys.EMAIL, ""));

        findViewById(R.id.btnNext).setOnClickListener(v -> {
            Intent i = new Intent(this, Example5Activity.class);
            i.putExtra(Keys.BUNDLE, data);
            startActivity(i);
        });
    }
}