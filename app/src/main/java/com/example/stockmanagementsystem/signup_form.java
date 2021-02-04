package com.example.stockmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup_form extends AppCompatActivity {

    TextView login_click;
    EditText compnyname_s,username_S,phoneno_s,password_s,confpassword_s;
    Button btn_signup;
    ProgressBar progressbar_s;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        login_click=findViewById(R.id.login_click);
        compnyname_s = findViewById(R.id.compnyname_s);
        username_S =findViewById(R.id.username_S);
        phoneno_s=findViewById(R.id.phoneno_s);
        password_s=findViewById(R.id.password_s);
        btn_signup=findViewById(R.id.btn_signup);
        progressbar_s=findViewById(R.id.progressbar_s);

        firebaseAuth=FirebaseAuth.getInstance();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String compyname = compnyname_s.getText().toString().trim();
                String username= username_S.getText().toString().trim();
                String phoneno= phoneno_s.getText().toString().trim();
                String password = password_s.getText().toString().trim();

                Intent intent = new Intent(getApplicationContext(),VerifyPhoneNo.class);
                intent.putExtra( "compyname",compyname );
                intent.putExtra( "username",username );
                intent.putExtra( "phoneno",phoneno );
                intent.putExtra( "password",password );

                startActivity( intent );
                finish();

            }

        });

        login_click.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup_form.this,login_form.class);
                startActivity( intent );
            }
        } );
    }
}
