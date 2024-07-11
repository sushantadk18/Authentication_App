package com.example.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Login_Page extends AppCompatActivity {
    TextView email,password,forgotPassword,signup;
    Button loginBtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        firebaseAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.txt_email);
        password=findViewById(R.id.txt_password);
        forgotPassword=findViewById(R.id.txt_forgot);
        loginBtn=findViewById(R.id.btn_login);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString().trim();
                String Password=password.getText().toString().trim();
                if (Email.isEmpty()){
                    Toast.makeText(Login_Page.this,"Please enter email",Toast.LENGTH_SHORT).show();
                }
                if (Password.isEmpty()){
                    Toast.makeText(Login_Page.this,"Please enter Password",Toast.LENGTH_SHORT).show();
                }
                if (password.length()>6){
                    Toast.makeText(Login_Page.this,"Password too short",Toast.LENGTH_SHORT).show();
                }
                firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login_Page.this,"Signup Complete",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();

                        }
                        else {
                            Toast.makeText(Login_Page.this,"Signup Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}