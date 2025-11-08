package com.example.sisapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateStudentActivity extends AppCompatActivity {
    EditText etNic;
    EditText firstName;
    EditText lastName;
    EditText fullName;
    EditText nameWithInitials;
    EditText addressLine1;
    EditText addressLine2;
    EditText zipCode;

    String studentId;

    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNic = findViewById(R.id.ecNic);
        firstName = findViewById(R.id.ecFirstName);
        lastName = findViewById(R.id.ecLastName);
        fullName = findViewById(R.id.ecFullName);
        nameWithInitials = findViewById(R.id.ecInitials);
        addressLine1 = findViewById(R.id.ecAddress1);
        addressLine2 = findViewById(R.id.ecAddress2);
        zipCode = findViewById(R.id.ecZipCode);

        btnUpdate = findViewById(R.id.btnUpdate);

        // Get the student ID from the intent
        Intent intent = getIntent();
        studentId = intent.getStringExtra("STUDENT_ID");

        assert studentId != null;
        Student student = StudentsList.getStudentById(Integer.parseInt(studentId));
        if (!(student == null)) {
            etNic.setText(student.getNic());
            firstName.setText(student.getFirstName());
            lastName.setText(student.getLastName());
            fullName.setText(student.getFullName());
            nameWithInitials.setText(student.getNameWithInitials());
            addressLine1.setText(student.getAddressLineOne());
            addressLine2.setText(student.getAddressLineTwo());
            zipCode.setText(String.valueOf(student.getZipCode()));


        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student(etNic.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), fullName.getText().toString(), nameWithInitials.getText().toString(), addressLine1.getText().toString(), addressLine2.getText().toString(), Integer.parseInt(zipCode.getText().toString()));
                StudentsList.updateById(Integer.parseInt(studentId), student);
                Toast.makeText(getApplicationContext(), "Student Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateStudentActivity.this, ShowStudentsActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }
}