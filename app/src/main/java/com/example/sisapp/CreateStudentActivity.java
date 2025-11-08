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

public class CreateStudentActivity extends AppCompatActivity {
    EditText etNic;
    EditText firstName;
    EditText lastName;
    EditText fullName;
    EditText nameWithInitials;
    EditText addressLine1;
    EditText addressLine2;
    EditText zipCode;

    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        etNic = findViewById(R.id.etNic);
        firstName = findViewById(R.id.etFirstName);
        lastName = findViewById(R.id.etLastName);
        fullName = findViewById(R.id.etFullName);
        nameWithInitials = findViewById(R.id.etInitials);
        addressLine1 = findViewById(R.id.etAddress1);
        addressLine2 = findViewById(R.id.etAddress2);
        zipCode = findViewById(R.id.etZipCode);

        btnSave = findViewById(R.id.btnSave);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student(etNic.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), fullName.getText().toString(), nameWithInitials.getText().toString(), addressLine1.getText().toString(), addressLine2.getText().toString(), Integer.parseInt(zipCode.getText().toString()));
                boolean isCreated = StudentsList.addStudent(student);
                if(isCreated){
                    Toast.makeText(getApplicationContext(), "Student Created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateStudentActivity.this, ShowStudentsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });






    }
}