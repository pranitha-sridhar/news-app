package com.example.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText id,password;
    Button button;
    private FirebaseAuth mAuth;


    private void registerUser() {
        String userid = id.getText().toString();
        String userpassword = password.getText().toString();

        if (userid.isEmpty()) {
            id.setError("Email is needed");
            return;
        }
        if (userpassword.isEmpty()) {
            password.setError("Password is needed");
            return;
        }
        if (userpassword.length() < 6) {
            password.setError("Password too short");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userid).matches()) {
            id.setError("Invalid EmailID");
            return;
        }
        mAuth.createUserWithEmailAndPassword(userid,userpassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "User Registered Successfully", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Try another password",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

        id = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }
}
