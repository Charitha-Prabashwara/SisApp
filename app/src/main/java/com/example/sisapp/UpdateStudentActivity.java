package com.example.sisapp;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

import java.util.concurrent.atomic.AtomicReference;

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

    StudentsList studentsList;

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

        studentsList = new StudentsList(this);



        etNic = findViewById(R.id.ecNic);
        firstName = findViewById(R.id.ecFirstName);
        lastName = findViewById(R.id.ecLastName);
        fullName = findViewById(R.id.ecFullName);
        nameWithInitials = findViewById(R.id.ecInitials);
        addressLine1 = findViewById(R.id.ecAddress1);
        addressLine2 = findViewById(R.id.ecAddress2);
        zipCode = findViewById(R.id.ecZipCode);

        btnUpdate = findViewById(R.id.btnUpdate);


        btnUpdate.setVisibility(INVISIBLE);


        // Get the student ID from the intent
        AtomicReference<Intent> intent = new AtomicReference<>(getIntent());
        studentId = intent.get().getStringExtra("STUDENT_ID");

        assert studentId != null;
        AtomicReference<Student> student = new AtomicReference<>(studentsList.getStudentById(Integer.parseInt(studentId)));
        if (!(student.get() == null)) {
            etNic.setText(student.get().getNic());
            firstName.setText(student.get().getFirstName());
            lastName.setText(student.get().getLastName());
            fullName.setText(student.get().getFullName());
            nameWithInitials.setText(student.get().getNameWithInitials());
            addressLine1.setText(student.get().getAddressLineOne());
            addressLine2.setText(student.get().getAddressLineTwo());
            zipCode.setText(String.valueOf(student.get().getZipCode()));


        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student(etNic.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), fullName.getText().toString(), nameWithInitials.getText().toString(), addressLine1.getText().toString(), addressLine2.getText().toString(), Integer.parseInt(zipCode.getText().toString()));
                studentsList.updateById(Integer.parseInt(studentId), student);
                Toast.makeText(getApplicationContext(), "Student Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateStudentActivity.this, ShowStudentsActivity.class);
                startActivity(intent);
                finish();

            }
        });


        // Setup Material Toolbar
       MaterialToolbar toolbar = findViewById(R.id.topUpdateAppBar);

        // Handle menu item clicks
        toolbar.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.action_refresh) {
                 student.set(studentsList.getStudentById(Integer.parseInt(studentId)));
                if (!(student.get() == null)) {
                    etNic.setText(student.get().getNic());
                    firstName.setText(student.get().getFirstName());
                    lastName.setText(student.get().getLastName());
                    fullName.setText(student.get().getFullName());
                    nameWithInitials.setText(student.get().getNameWithInitials());
                    addressLine1.setText(student.get().getAddressLineOne());
                    addressLine2.setText(student.get().getAddressLineTwo());
                    zipCode.setText(String.valueOf(student.get().getZipCode()));


                }
                Toast.makeText(getApplicationContext(), "Refreshed", Toast.LENGTH_SHORT).show();
                return true;
            }
            if (item.getItemId() == R.id.action_delete) {
                // Show confirmation dialog
                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Delete Student")
                        .setMessage("Are you sure you want to delete this student?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            // Delete student action
                            int deleted = studentsList.deleteById(Integer.parseInt(studentId));
                            if(!(deleted == -1)){
                                Toast.makeText(getApplicationContext(), "Student Deleted", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Student not Deleted", Toast.LENGTH_SHORT).show();
                            }

                            intent.set(new Intent(UpdateStudentActivity.this, ShowStudentsActivity.class));
                            startActivity(intent.get());
                            finish();


                        })
                        .setNegativeButton("Cancel", (dialog, which) -> {
                            // Do nothing, just dismiss
                            dialog.dismiss();
                        })
                        .show();



                return true;
            }

            if (item.getItemId() == R.id.action_enableUpdate){

                btnUpdate.setVisibility(VISIBLE);

                item.setEnabled(false);
                return true;
            }
            return true;
        });






    }





}