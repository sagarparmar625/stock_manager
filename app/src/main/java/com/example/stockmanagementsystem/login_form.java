package com.example.stockmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class login_form extends AppCompatActivity {
     TextView signup_clk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

                signup_clk = findViewById(R.id.signup_click);

        signup_clk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login_form.this,signup_form.class);
                startActivity(intent);
            }
        });
    }
}