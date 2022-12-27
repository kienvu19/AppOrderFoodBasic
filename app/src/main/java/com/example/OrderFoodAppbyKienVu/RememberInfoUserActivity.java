package com.example.OrderFoodAppbyKienVu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import DatabaseInfomationUser.DBInfoUser;

public class RememberInfoUserActivity extends AppCompatActivity {
    ImageView imgback;
    EditText edt1,edt2,edt3;
    Button saveinfo;
    DBInfoUser myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember_info_user);
        imgback = findViewById(R.id.back789);
        edt1 = findViewById(R.id.saveuser_name);
        edt2 = findViewById(R.id.saveaddress_user);
        edt3 = findViewById(R.id.savenumberphone_user);
        saveinfo = findViewById(R.id.saveinfo);

        myDB = new DBInfoUser(this);

        saveinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt1.getText().toString().isEmpty() || edt2.getText().toString().isEmpty() || edt3.getText().toString().isEmpty()){
                    Toast.makeText(RememberInfoUserActivity.this, "Thiếu thông tin!", Toast.LENGTH_SHORT).show();
                }else{
                    confirmDialog();
                }
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận thông tin");
        builder.setMessage("Kiểm tra lại thông tin!");
        builder.setPositiveButton("LƯU", (dialogInterface, i) -> {
            myDB.addInformationUser(edt1.getText().toString().trim(), edt2.getText().toString().trim(), edt3.getText().toString().trim());
            finish();
        });
        builder.setNegativeButton("HỦY", (dialogInterface, i) -> {

        });
        builder.create().show();
    }
}