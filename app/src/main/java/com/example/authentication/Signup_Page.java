package com.example.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_Page extends AppCompatActivity {

    EditText email, password, confirmpassword;
    Button signup;
    TextView signin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_page);

        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.txt_email);
        password = findViewById(R.id.txt_password);
        confirmpassword = findViewById(R.id.txt_cpassword);
        signup = findViewById(R.id.btn_signup);
        signin = findViewById(R.id.txt_signin);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                String Cpassword = confirmpassword.getText().toString().trim();

                if (Email.isEmpty()) {
                    Toast.makeText(Signup_Page.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Password.isEmpty()) {
                    Toast.makeText(Signup_Page.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Password.length() < 6) {
                    Toast.makeText(Signup_Page.this, "Password too short, minimum 6 characters required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Cpassword.equals(Password)) {
                    Toast.makeText(Signup_Page.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Signup_Page.this, "Signup Complete", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Signup_Page.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup_Page.this, Login_Page.class);
                startActivity(intent);
            }
        });
    }
}
