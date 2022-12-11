package com.snhu.itrack;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // initializing all our variables.
        EditText editEmail = findViewById(R.id.email);
        EditText editPassword = findViewById(R.id.password);
        EditText editConfirmPassword = findViewById(R.id.confirmPassword);
        Button clickRegister = findViewById(R.id.register);
        Button clickCancel = findViewById(R.id.cancel);

        // creating a new dbhandler class
        // and passing our context to it.
//        dbHandler = new DBHandler(Register.this);
//
//        // below line is to add on click listener for our add course button.
//        clickRegister.setOnClickListener(v -> {
//
//            System.out.println("Register button clicked");
//
//            // below line is to get data from all edit text fields.
//            String email = editEmail.getText().toString();
//            String password = editPassword.getText().toString();
//            String confirmPassword = editConfirmPassword.getText().toString();
//
//            // validating if the text fields are empty or not.
//            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
//                Toast.makeText(Register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            // on below line we are calling a method to add new
//            // course to sqlite data and pass all our values to it.
//            dbHandler.registerUser(email, password, confirmPassword);
//
//            // after adding the data we are displaying a toast message.
//            Toast.makeText(Register.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
//            email.setText("");
//            password.setText("");
//            confirmPassword.setText("");
//        });
    }
}
