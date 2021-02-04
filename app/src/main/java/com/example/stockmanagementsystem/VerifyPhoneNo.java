package com.example.stockmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneNo extends AppCompatActivity {
    Button btnverify;
    EditText user_enter_verify_code;
    ProgressBar progressBar;
    String verificationCodeBySystem;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_no);

        btnverify = findViewById(R.id.btnverify);
        user_enter_verify_code = findViewById(R.id.user_enter_verify_code);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        String compyname = getIntent().getStringExtra("compyname");
        String username = getIntent().getStringExtra("username");
        String phoneno = getIntent().getStringExtra("phoneno");
        String password = getIntent().getStringExtra("password");

        sendVerificationCodeToUser(phoneno);

        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = user_enter_verify_code.getText().toString();

                if (code.isEmpty() || code.length() < 6) {
                    user_enter_verify_code.setError("Wront OTP...");
                    user_enter_verify_code.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);

            }
        });
    }

    private void sendVerificationCodeToUser(String phoneno) {

        mAuth = FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth) //  FirebaseAuth.getInstance()
                        .setPhoneNumber("+91" + phoneno)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationCodeBySystem = s;

        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }

        }
        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Toast.makeText( VerifyPhoneNo.this,e.getMessage(),Toast.LENGTH_SHORT).show() ;

        }
    };

    private void verifyCode(String verificationCode){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential( verificationCodeBySystem,verificationCode );

        signInUserByCredential(credential);

    }

    private void signInUserByCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential( credential )
                .addOnCompleteListener( VerifyPhoneNo.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            String compyname = getIntent().getStringExtra( "compyname" );
                            String username = getIntent().getStringExtra( "username" );
                            String phoneno = getIntent().getStringExtra( "phoneno" );
                            String password = getIntent().getStringExtra( "password" );

                            rootNode = FirebaseDatabase.getInstance();
                            reference = rootNode.getReference("users");

                            UserHelperClass helperClass = new UserHelperClass(compyname,username,phoneno,password);

                            reference.child(phoneno).setValue(helperClass);

                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            intent.putExtra( "compyname",compyname );
                            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                            startActivity( intent );
                            finish();
                        }

                        else{
                            Toast.makeText( VerifyPhoneNo.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                } );

    }
}