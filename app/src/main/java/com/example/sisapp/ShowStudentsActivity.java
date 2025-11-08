package com.example.sisapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class ShowStudentsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Enable Edge-to-Edge
        EdgeToEdge.enable(this);

        // Set layout
        setContentView(R.layout.activity_show_students);

        recyclerView  = findViewById(R.id.recyclerViewList);

        List<Student> students = StudentsList.getStudents();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this, students));

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup Material Toolbar
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);

        // Handle menu item clicks
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_create_student) {
                // Example action: show a Toast
                Toast.makeText(this, "Create Student clicked", Toast.LENGTH_SHORT).show();

                // TODO: Open CreateStudentActivity or show a dialog
                // startActivity(new Intent(this, CreateStudentActivity.class));

                return true; // Menu handled
            }
            return false;
        });
    }
}
