package com.example.caloriestracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Food> foodList;

    public CustomAdapter(Context context, ArrayList foodList){
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.foodDataTxt.setText(String.valueOf(foodList.get(position).getName() + " - " + String.format("%.2f", foodList.get(position).getCalories())) + " calories");
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView foodDataTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodDataTxt = itemView.findViewById(R.id.foodDataTxt);
        }
    }
}
