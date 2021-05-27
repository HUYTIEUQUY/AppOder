package com.example.appoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    EditText email,pass;
    Button btndangnhap;
    TextView txtdangky;
    FirebaseAuth mAut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAut =FirebaseAuth.getInstance();
        AnhXa();
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();
            }
        });
        txtdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }




    //hàm đăng nhập
    private  void DangNhap(){
        String temail= email.getText().toString();
        String password=pass.getText().toString();
        mAut.signInWithEmailAndPassword(temail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent =new Intent(SignIn.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(SignIn.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignIn.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    //Ánh xạ
    private void AnhXa(){
        email=findViewById(R.id.editEmail);
        pass=findViewById(R.id.Password);
        btndangnhap=findViewById(R.id.buttonDangNhap);
        txtdangky=findViewById(R.id.textDangKy);
    }

    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAut.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(SignIn.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}