package com.example.OrderFoodAppbyKienVu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.OrderFoodAppbyKienVu.DatabaseLogin.DBHelperLogin;

public class CheckUserActivity extends AppCompatActivity {
    EditText username;
    Button reset;
    DBHelperLogin DB;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_user);
        username = (EditText) findViewById(R.id.username_reset);
        reset = (Button) findViewById(R.id.btnreset);
        DB = new DBHelperLogin(this);
        back = (ImageView) findViewById(R.id.back123);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckUserActivity.this, LoginActivity.class));
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user  = username.getText().toString();

                Boolean checkuser = DB.checkusername(user);
                if(checkuser)
                {
                    Intent intent = new Intent(getApplicationContext(), ResetPassUserActivity.class);
                    intent.putExtra("username",user);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(CheckUserActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}