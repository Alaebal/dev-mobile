package com.example.alae_atelier2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Example2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example2);

        Bundle bundle = getIntent().getBundleExtra(Keys.BUNDLE);
        if (bundle == null) bundle = new Bundle();   // sécurité
        final Bundle data = bundle;

        TextView tvNom = findViewById(R.id.userName);
        tvNom.setText(data.getString(Keys.NOM, ""));

        EditText editEmail = findViewById(R.id.editEmail);
        EditText editAge = findViewById(R.id.editAge);

        findViewById(R.id.btnNext).setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String ageStr = editAge.getText().toString().trim();
            if (email.isEmpty() || ageStr.isEmpty()) {
                Toast.makeText(this, R.string.err_empty, Toast.LENGTH_SHORT).show();
                return;
            }
            data.putString(Keys.EMAIL, email);
            try {
                data.putInt(Keys.AGE, Integer.parseInt(ageStr));
            } catch (NumberFormatException e) {
                data.putInt(Keys.AGE, 0);
            }

            Intent i = new Intent(this, Example3Activity.class);
            i.putExtra(Keys.BUNDLE, data);   // on retransmet le même Bundle
            startActivity(i);
        });
    }
}