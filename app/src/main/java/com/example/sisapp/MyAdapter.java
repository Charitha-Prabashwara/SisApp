package com.example.sisapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    List<Student> students;

    public MyAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.student_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.firstName.setText(students.get(position).getFirstName());
        holder.LastName.setText(students.get(position).getLastName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateStudentActivity.class);
            // Pass the student data to the Update Activity
            intent.putExtra("STUDENT_ID", String.valueOf(students.get(position).getId())); // pass ID or whole object
            context.startActivity(intent);
            //Toast.makeText(context.getApplicationContext(), String.valueOf(students.get(position).getId()), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
