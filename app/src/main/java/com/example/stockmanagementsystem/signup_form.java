package com.example.stockmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class signup_form extends AppCompatActivity {

    EditText compnyname_s,username_S,email_s,password_s,confpassword_s;
    Button btn_signup;
    ProgressBar progressbar_s;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        compnyname_s = findViewById(R.id.compnyname_s);
        username_S =findViewById(R.id.username_S);
        email_s=findViewById(R.id.email_s);
        password_s=findViewById(R.id.password_s);
        confpassword_s=findViewById(R.id.confpassword_s);
        btn_signup=findViewById(R.id.btn_signup);
        progressbar_s=findViewById(R.id.progressbar_s);

        firebaseAuth=FirebaseAuth.getInstance();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String compyname = compnyname_s.getText().toString().trim();
                String username= username_S.getText().toString().trim();
                String email= email_s.getText().toString().trim();
                String password = password_s.getText().toString().trim();
                String confpassword = confpassword_s.getText().toString().trim();

                if (TextUtils.isEmpty(compyname)){
                    Toast.makeText(signup_form.this, "Please Enter Compny Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(signup_form.this, "Please Enter User Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(signup_form.this, "Please Enter E-mail", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(signup_form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(signup_form.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length()<6){
                    Toast.makeText(signup_form.this, "Please Enter Minimum 6 digit", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}