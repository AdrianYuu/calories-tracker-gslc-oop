package com.example.caloriestracker;

public class Food {
    private String name;
    private Double calories;
    public Food(String name, Double calories) {
        this.name = name;
        this.calories = calories;
    }
    public String getName(){
        return name;
    }
    public Double getCalories(){
        return calories;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCalories(Double calories){
        this.calories = calories;
    }
}
