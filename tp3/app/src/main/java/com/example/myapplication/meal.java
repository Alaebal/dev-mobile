package com.example.myapplication;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class meal {
    public String idMeal;
    public String strMeal;
    public String strMealThumb;
    public String strInstructions;

    public String strIngredient1, strIngredient2, strIngredient3, strIngredient4,
            strIngredient5, strIngredient6, strIngredient7, strIngredient8,
            strIngredient9, strIngredient10, strIngredient11, strIngredient12,
            strIngredient13, strIngredient14, strIngredient15, strIngredient16,
            strIngredient17, strIngredient18, strIngredient19, strIngredient20;

    public List<String> getIngredients() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            try {
                Field f = meal.class.getField("strIngredient" + i);
                String value = (String) f.get(this);
                if (value != null && !value.trim().isEmpty()) {
                    list.add(value);
                }
            } catch (Exception ignored) {
            }
        }
        return list;
    }
}