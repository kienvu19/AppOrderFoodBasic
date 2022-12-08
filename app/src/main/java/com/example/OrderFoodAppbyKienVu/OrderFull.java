package com.example.OrderFoodAppbyKienVu;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.OrderFoodAppbyKienVu.DatabaseFood.DBHelper;
import java.util.ArrayList;


public class OrderFull extends AppCompatActivity{
    RecyclerView recyclerView;
    ImageView back;
    Button delete_all,order;
    DBHelper myDB;
    ArrayList<String> fook_id, food_title, food_sl, food_price;
    CartAdapter cartAdapter;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_full);
        recyclerView = findViewById(R.id.recyclerView);
        back = findViewById(R.id.back);
        delete_all = findViewById(R.id.delete_all);
        order = findViewById(R.id.btnPlaceOrder);

        textView = findViewById(R.id.total);

        myDB = new DBHelper(OrderFull.this);

        fook_id = new ArrayList<>();
        food_title = new ArrayList<>();
        food_sl = new ArrayList<>();
        food_price = new ArrayList<>();

        storeDataInArrays();
        cartAdapter = new CartAdapter(OrderFull.this,this, fook_id, food_title, food_sl, food_price);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderFull.this));


        textView.setText(String.format("%s", myDB.getPrice()));
        String thanhtien = textView.getText().toString().trim();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        delete_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    confirmDialog();
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderFull.this, PaymentFood.class);
                i.putExtra("total", thanhtien);
                if(myDB.isMasterEmpty()){
                    Toast.makeText(OrderFull.this, "Ôi bạn ôi giỏ hàng không có gì!", Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(i);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllFood();
        if(cursor.getCount() == 0){
            Toast.makeText(OrderFull.this, "Giỏ hàng chưa có gì!", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                fook_id.add(cursor.getString(0));
                food_title.add(cursor.getString(1));
                food_sl.add(cursor.getString(2));
                food_price.add(cursor.getString(3));
            }
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xóa tất cả!");
        builder.setMessage("Bạn có muốn xóa hết tất cả món ăn đã order?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(OrderFull.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(OrderFull.this, OrderFull.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}