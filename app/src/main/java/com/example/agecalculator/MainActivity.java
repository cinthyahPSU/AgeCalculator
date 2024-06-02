package com.example.agecalculator;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText firstName_EditText; //creating inputs for name and dob
    private EditText lastName_EditText;
    private EditText dateofBirth_EditText;
    private Button calculate_AgeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //setting up layout
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        firstName_EditText = findViewById(R.id.firstName_EditText); //referencing id and setting resource
        lastName_EditText = findViewById(R.id.lastName_EditText);
        dateofBirth_EditText = findViewById(R.id.dob_EditText);
        calculate_AgeButton = findViewById(R.id.calculate_AgeButton);

        calculate_AgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAge();
            }
        });
    }
    private void calculateAge() {
        // recieve the values from user input as string
        String firstName = firstName_EditText.getText().toString();
        String lastName = lastName_EditText.getText().toString();
        String dobString = dateofBirth_EditText.getText().toString();

        // Check for invalid parameter where inputs are emptu
        if (firstName.isEmpty() || lastName.isEmpty() || dobString.isEmpty()) {
            Toast.makeText(this, "Please fill in all inputs", Toast.LENGTH_SHORT).show();
            return;
        }

        // parse the date of birth input
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dob = null;
        try {
            dob = sdf.parse(dobString);
        } catch (ParseException e) { //check for invalid parameter where date is not correct
            Toast.makeText(this, "Invalid date input. Please use dd/mm/yyyy", Toast.LENGTH_SHORT).show();
            return;
        }

        // calculate age
        Calendar dobCalendar = Calendar.getInstance(); //get current date
        dobCalendar.setTime(dob);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR); //subtract todays year from date of birth year

        // display age
        String message = firstName + " " + lastName + " is " + age + " years old.";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}