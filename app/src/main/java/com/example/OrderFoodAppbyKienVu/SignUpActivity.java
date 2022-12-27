package com.example.OrderFoodAppbyKienVu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.OrderFoodAppbyKienVu.DatabaseLogin.DBHelperLogin;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {
    EditText tendangnhap, pass, repass;
    Button dangky;
    TextView login;
    DBHelperLogin DB;
    String user,pass1,repass1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tendangnhap = findViewById(R.id.txtUser2);
        pass = findViewById(R.id.txtPass2);
        repass = findViewById(R.id.txtRePass2);
        dangky = findViewById(R.id.btnReg);
        login = findViewById(R.id.login);
        DB = new DBHelperLogin(this);
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = tendangnhap.getText().toString();
                pass1 = pass.getText().toString();
                repass1 = repass.getText().toString();

                if (user.equals("") || pass1.equals("") || repass1.equals("")){
                    Toast.makeText(SignUpActivity.this, "Vui lòng điền tất cả ô trống.", Toast.LENGTH_SHORT).show();
                }else if(pass1.length() < 8){
                    Toast.makeText(SignUpActivity.this, "Mật khẩu phải lớn hơn 8 ký tự!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pass1.equals(repass1)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass1);
                            if (insert) {
                                Toast.makeText(SignUpActivity.this, "Đăng ký thành công.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(SignUpActivity.this, "Đăng ký thất bại.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUpActivity.this, "Người dùng đã tồn tại!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "Mật khẩu không khớp.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }
}