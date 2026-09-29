package com.example.alae_atelier2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Example1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example1);

        EditText editNom = findViewById(R.id.editNom);
        Button btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            String nom = editNom.getText().toString().trim();
            if (nom.isEmpty()) {
                editNom.setError(getString(R.string.err_empty));
                return;
            }
            Bundle b = new Bundle();
            b.putString(Keys.NOM, nom);

            Intent i = new Intent(Example1Activity.this, Example2Activity.class);
            i.putExtra(Keys.BUNDLE, b);
            startActivity(i);
        });
    }
}