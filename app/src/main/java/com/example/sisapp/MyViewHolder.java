package com.example.sisapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView firstName, LastName;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.student_image);
        firstName = itemView.findViewById(R.id.first_name);
        LastName = itemView.findViewById(R.id.last_name);
    }
}
