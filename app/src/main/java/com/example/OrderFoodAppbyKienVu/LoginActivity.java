package com.example.OrderFoodAppbyKienVu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.OrderFoodAppbyKienVu.DatabaseLogin.DBHelperLogin;

import java.awt.font.TextAttribute;

public class LoginActivity extends AppCompatActivity {
    EditText edt1,edt2;
    CheckBox ckremember;
    TextView forgotpass, register;
    Button btdangnhap;
    DBHelperLogin myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt1 = findViewById(R.id.edtUserLog1);
        edt2 = findViewById(R.id.edtPassLog1);
        ckremember = findViewById(R.id.ckbRemember);
        btdangnhap = findViewById(R.id.btnLog);
        forgotpass = findViewById(R.id.forgotPass);
        register = findViewById(R.id.register);
        myDB = new DBHelperLogin(this);
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(LoginActivity.this, CheckUserActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
        btdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edt1.getText().toString();
                String pass = edt2.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Vui lòng điền tất cả ô trống.", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = myDB.checkusernamepassword(user, pass);
                    if(checkuserpass){
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Thông tin không hợp lệ.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}