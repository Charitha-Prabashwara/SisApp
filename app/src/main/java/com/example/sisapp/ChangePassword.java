package com.example.sisapp;

import static android.view.View.INVISIBLE;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.android.material.textfield.TextInputLayout;

public class ChangePassword extends AppCompatActivity {
    EditText currentPassword, newPassword, confirmPassword;
    Button changePasswordBtn;

    String getCurrentPassword;

    TextInputLayout layout_current_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        currentPassword = findViewById(R.id.input_current_password);
        newPassword = findViewById(R.id.input_new_password);
        confirmPassword= findViewById(R.id.input_confirm_password);
        changePasswordBtn = findViewById(R.id.btn_change_password);
        layout_current_password = findViewById(R.id.layout_current_password);
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        getCurrentPassword = sharedPreferences.getString("password", ""); // null if not found

        if(getCurrentPassword.isEmpty()){
            layout_current_password.setVisibility(View.GONE);
            currentPassword.setVisibility(INVISIBLE);
        }

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!getCurrentPassword.isEmpty()) {

                    if(!(getCurrentPassword.equals(currentPassword.getText().toString()))){
                        Toast.makeText(getApplicationContext(), "Old password is incorrect", Toast.LENGTH_SHORT).show();
                    }else{
                        if(!(newPassword.getText().toString().equals(confirmPassword.getText().toString()))){
                            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }else {
                            //password change
                            SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("password", confirmPassword.getText().toString());
                            editor.apply(); // or editor.commit();

                            Toast.makeText(getApplicationContext(), "Password Changed", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ChangePassword.this, ShowStudentsActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }


                }else{
                    if(!(newPassword.getText().toString().equals(confirmPassword.getText().toString()))){
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }else {
                        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("password", confirmPassword.getText().toString());
                        editor.apply(); // or editor.commit();

                        Toast.makeText(getApplicationContext(), "Password Changed", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ChangePassword.this, ShowStudentsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }


            }
        });






    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ChangePassword.this, ShowStudentsActivity.class);
        startActivity(intent);
        // optionally finish(); if you don't want user to come back
    }
}