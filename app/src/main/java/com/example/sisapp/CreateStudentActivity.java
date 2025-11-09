package com.example.sisapp;

import static android.view.View.VISIBLE;

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

import com.google.android.material.appbar.MaterialToolbar;

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

    StudentsList studentsList;
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


        studentsList = new StudentsList(this);


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
                long isCreated = studentsList.addStudent(student);
                if(!(isCreated==-1)){
                    Toast.makeText(getApplicationContext(), "Student Created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateStudentActivity.this, ShowStudentsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);

        // Handle menu item clicks
        toolbar.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.action_clear) {
                etNic.setText(null);
                firstName.setText(null);
                lastName.setText(null);
                fullName.setText(null);
                nameWithInitials.setText(null);
                addressLine1.setText(null);
                addressLine2.setText(null);
                zipCode.setText(null);


                return true;
            }

            return true;
        });






    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ShowStudentsActivity.class);
        startActivity(intent);
        finish();
    }
}