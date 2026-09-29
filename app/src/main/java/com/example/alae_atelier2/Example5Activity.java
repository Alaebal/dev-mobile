package com.example.alae_atelier2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Example5Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example5);

        Bundle bundle = getIntent().getBundleExtra(Keys.BUNDLE);
        if (bundle != null) {
            String nom = bundle.getString(Keys.NOM, "");
            int age = bundle.getInt(Keys.AGE, 0);
            String email = bundle.getString(Keys.EMAIL, "");

            TextView tvRecapNom = findViewById(R.id.tvRecapNom);
            TextView tvRecapEmail = findViewById(R.id.tvRecapEmail);
            TextView tvRecapAge = findViewById(R.id.tvRecapAge);

            if (tvRecapNom != null) tvRecapNom.setText(getString(R.string.hello_fmt, nom, age));
            if (tvRecapEmail != null) tvRecapEmail.setText(email);
            if (tvRecapAge != null) tvRecapAge.setText(String.valueOf(age));
        }

        findViewById(R.id.btnFinish).setOnClickListener(v -> {
            Intent i = new Intent(this, Example1Activity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        });
    }
}