package com.example.OrderFoodAppbyKienVu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.OrderFoodAppbyKienVu.DatabaseLogin.DBHelperLogin;

public class ResetPassUserActivity extends AppCompatActivity {
    TextView username;
    EditText pass,repass;
    Button confirm;
    ImageView imgback;
    DBHelperLogin DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass_user);
        username = (TextView) findViewById(R.id.username_reset_text);
        pass = (EditText)  findViewById(R.id.password_reset);
        repass = (EditText) findViewById(R.id.repassword_reset);
        confirm = (Button) findViewById(R.id.btnconfirm);
        imgback = findViewById(R.id.back456);
        DB = new DBHelperLogin(this);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();
                if(password.length() < 8){
                    Toast.makeText(ResetPassUserActivity.this, "Mật khẩu phải lớn hơn 8 ký tự!", Toast.LENGTH_SHORT).show();
                }
                else if(password.equals(repassword)){
                    Boolean checkpasswordupdate = DB.updatepassword(user,password);
                    if (checkpasswordupdate) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetPassUserActivity.this, "Cập nhật mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ResetPassUserActivity.this, "Cập nhật mật khẩu thất bại.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ResetPassUserActivity.this, "Mật khẩu nhập lại không đúng!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}