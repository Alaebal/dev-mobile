package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private String mealId;
    private String mealName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView image = findViewById(R.id.detailImage);
        TextView title = findViewById(R.id.detailTitle);
        TextView ingredients = findViewById(R.id.detailIngredients);
        TextView instructions = findViewById(R.id.detailInstructions);
        Button btnFavorite = findViewById(R.id.btnFavorite);

        // Lecture du Bundle envoyé par MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mealId = extras.getString("mealId");
            mealName = extras.getString("mealName");
            title.setText(mealName);
        }

        // Renvoi d'un résultat à MainActivity
        btnFavorite.setOnClickListener(v -> {
            Bundle result = new Bundle();
            result.putString("mealName", mealName);
            result.putBoolean("favorite", true);

            Intent data = new Intent();
            data.putExtras(result);
            setResult(RESULT_OK, data);
            finish();
        });

        RetrofitClient.getApi().getById(mealId).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null
                        && response.body().meals != null) {
                    meal meal = response.body().meals.get(0);
                    instructions.setText(meal.strInstructions);
                    ingredients.setText("• " + String.join("\n• ", meal.getIngredients()));
                    Glide.with(DetailActivity.this).load(meal.strMealThumb).into(image);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Erreur réseau", Toast.LENGTH_SHORT).show();
            }
        });
    }
}