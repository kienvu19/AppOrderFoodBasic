package com.example.OrderFoodAppbyKienVu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.OrderFoodAppbyKienVu.DatabaseFood.DBHelper;
import com.example.OrderFoodAppbyKienVu.DatabaseHistory.DBHelperHistory;

import java.util.Locale;

public class PaymentFood extends AppCompatActivity {
    ImageView img_back;
    Button payment;
    RadioGroup gr1;
    RadioButton tienmat, thenganhang;
    TextView total;
    EditText edt_disc, nameuser, adduser, numberphoneuser;
    DBHelperHistory DBHistory;
    DBHelper DBFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_food);
        total = findViewById(R.id.total_price);
        img_back = findViewById(R.id.back);
        gr1 = findViewById(R.id.radiogr);
        tienmat = findViewById(R.id.radioButton1);
        thenganhang = findViewById(R.id.radioButton2);
        edt_disc = findViewById(R.id.edt_discount);
        payment = findViewById(R.id.payment);

        nameuser = findViewById(R.id.user_name);
        adduser = findViewById(R.id.address_user);
        numberphoneuser = findViewById(R.id.numberphone_user);

        total.setText(getIntent().getStringExtra("total"));
        String a = total.getText().toString().trim();
        long x = Integer.parseInt(a);
        DBHistory = new DBHelperHistory(this);
        DBFood = new DBHelper(this);
        edt_disc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                total.setText(String.valueOf(x));
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(edt_disc.getText().toString().equals("kienvu")){
                    total.setText(String.valueOf(x-50000));
                    Toast.makeText(PaymentFood.this, "Quý khách được giảm 50K!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameuser.getText().toString().isEmpty() || adduser.getText().toString().isEmpty() || numberphoneuser.getText().toString().isEmpty() || !tienmat.isChecked() && !thenganhang.isChecked()){
                    Toast.makeText(PaymentFood.this, "Thiếu thông tin liên hệ!", Toast.LENGTH_SHORT).show();
                }else{
                    confirmDialog();
                }
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gr1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButton1:
                        Toast.makeText(PaymentFood.this, "Tiền mặt!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton2:
                        Toast.makeText(PaymentFood.this, "Thẻ ngân hàng!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận thông tin");
        builder.setMessage("Kiểm tra lại thông tin cá nhân trước khi thanh toán!");
        builder.setPositiveButton("THANH TOÁN", (dialogInterface, i) -> {
            DBFood.deleteAllData();
            startActivity(new Intent(PaymentFood.this, DoneOrderFood.class));
            if(tienmat.isChecked()){
                DBHistory.addInformationUser(nameuser.getText().toString().trim(), adduser.getText().toString().trim(), numberphoneuser.getText().toString().trim(), edt_disc.getText().toString().trim(), tienmat.getText().toString().trim(), total.getText().toString().trim());
            }else{
                DBHistory.addInformationUser(nameuser.getText().toString().trim(), adduser.getText().toString().trim(), numberphoneuser.getText().toString().trim(), edt_disc.getText().toString().trim(), thenganhang.getText().toString().trim(), total.getText().toString().trim());
            }
        });
        builder.setNegativeButton("HỦY", (dialogInterface, i) -> {

        });
        builder.create().show();
    }
}