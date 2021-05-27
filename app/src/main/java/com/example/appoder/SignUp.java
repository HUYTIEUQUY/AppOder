package com.example.appoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText edtemail,edtpass,edtpass2,edtuser;
    Button btndangky;
    FirebaseAuth mAut;
    DatabaseReference root; //con của nút đầu
    DatabaseReference mdata;// nút đầu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        AnhXa();
        mAut= FirebaseAuth.getInstance();

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(KTinput()== true){
                    DangKy();
               }
            }
        });
    }

    private  void DangKy(){
        String email = edtemail.getText().toString();
        String password = edtpass.getText().toString();

        mAut.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            root=FirebaseDatabase.getInstance().getReference().child("account");
                            mdata=root.child(mAut.getCurrentUser().getUid());
                            TaiKhoan tk =new TaiKhoan(edtemail.getText().toString(),edtuser.getText().toString(),"","");
                            mdata.setValue(tk);
                            Intent intent = new Intent(SignUp.this,SignIn.class);
                            startActivity(intent);
                            Toast.makeText(SignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(SignUp.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


        private boolean KTinput() {
            return true;
        }


    private void AnhXa(){
        edtemail    =findViewById(R.id.editEmail);
        edtpass     =findViewById(R.id.Password);
        edtpass2    =findViewById(R.id.editVerifyPassword);
        edtuser     =findViewById(R.id.editUsername);
        btndangky   =findViewById(R.id.buttonDangky);
    }
}