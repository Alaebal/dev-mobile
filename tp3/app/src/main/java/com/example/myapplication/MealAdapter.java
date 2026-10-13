package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {
    public interface OnMealClickListener {
        void onMealClick(meal meal);
    }

    private OnMealClickListener listener;

    public void setOnMealClickListener(OnMealClickListener listener) {
        this.listener = listener;
    }
    private List<meal> meals = new ArrayList<>();

    public void setMeals(List<meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        meal meal = meals.get(position);
        holder.title.setText(meal.strMeal);
        Glide.with(holder.image.getContext()).load(meal.strMealThumb).into(holder.image);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onMealClick(meal);
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.mealImage);
            title = itemView.findViewById(R.id.mealTitle);
        }
    }
}