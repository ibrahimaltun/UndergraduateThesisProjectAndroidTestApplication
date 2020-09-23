package com.example.healtech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerName;
    private EditText registerEmail;
    private EditText registerPassword;
    private TextView registerToLogin;
    private Button registerButton;
    private ProgressDialog registerProgress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerName = (EditText) findViewById(R.id.reg_name);
        registerEmail = (EditText) findViewById(R.id.reg_email);
        registerPassword = (EditText) findViewById(R.id.reg_password);
        registerButton = (Button) findViewById(R.id.reg_buton);
        registerToLogin = (TextView) findViewById(R.id.reg_to_login);
        registerProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        registerToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginIntent= new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = registerName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();

                if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password))
                {

                    registerProgress.setTitle("Kaydediliyor");
                    registerProgress.setMessage("Hesabınız oluşturuluyor, lütfen bekleyiniz...");
                    registerProgress.setCanceledOnTouchOutside(false);
                    registerProgress.show();
                    register_user(password,email);

                }
            }
        });

    }

    private void register_user(String password, String email) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    registerProgress.dismiss();
                    Intent mainIntent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    Toast.makeText(RegisterActivity.this, "Hesabınız oluşturuldu ve oturum açıldı.", Toast.LENGTH_LONG).show();
                                    }
                else
                {
                    registerProgress.dismiss();
                    Toast.makeText(RegisterActivity.this, "Hata:" + " " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
