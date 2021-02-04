package com.example.stockmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );

        uname = findViewById( R.id.uname );

        String name = getIntent().getStringExtra( "compyname" );

        uname.setText( "Hey "+name +"!" );

    }
}