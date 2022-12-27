package com.example.OrderFoodAppbyKienVu;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.OrderFoodAppbyKienVu.DatabaseFood.DBHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;


public class OrderFull extends AppCompatActivity{
    RecyclerView recyclerView;
    ImageView back;
    Button delete_all,order;
    DBHelper myDB;
    ArrayList<String> fook_id;
    ArrayList<String> food_title;
    ArrayList<String> food_sl;
    ArrayList<Long> food_price;
    CartAdapter cartAdapter;

    TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        food_price = new ArrayList<Long>();

        storeDataInArrays();
        cartAdapter = new CartAdapter(OrderFull.this,this, fook_id, food_title, food_sl, food_price);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderFull.this));

        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);


        String tongtien = myDB.getPrice();
        textView.setText(currencyVN.format(Integer.parseInt(tongtien)));

        Intent i = new Intent(OrderFull.this, InfoUserAdapter.class);
        String thanhtien = textView.getText().toString().trim();
        i.putExtra("total", thanhtien);

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
                if(myDB.isMasterEmpty()){
                    Toast.makeText(OrderFull.this, "Giỏ hàng rỗng!", Toast.LENGTH_SHORT).show();
                }
                else{
                    selectDialog();
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
            Toast.makeText(OrderFull.this, "Giỏ hàng rỗng!", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                fook_id.add(cursor.getString(0));
                food_title.add(cursor.getString(1));
                food_sl.add(cursor.getString(2));
                food_price.add(cursor.getLong(3));
            }
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xóa tất cả!");
        builder.setMessage("Bạn có muốn xóa hết tất cả món ăn trong giỏ hàng?");
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
    private void selectDialog(){
// alertdialog for exit the app
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

// set the title of the Alert Dialog
        alertDialogBuilder.setTitle("Lựa chọn của bạn?");

// set dialog message
        alertDialogBuilder
                .setMessage("Bạn có muốn dùng thông tin đã có không?")
                .setCancelable(false)
                .setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        // what to do if YES is tapped
                        Intent i = new Intent(OrderFull.this, ShowInfoUserRememberActivity.class);
                        String thanhtien = textView.getText().toString().trim();
                        Intent j = new Intent(OrderFull.this, PaymentFood.class);
                        j.putExtra("total", thanhtien);
                        if(myDB.isMasterEmpty()){
                            Toast.makeText(OrderFull.this, "Giỏ hàng rỗng!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            startActivity(i);
                        }
                    }
                });

        alertDialogBuilder.setNeutralButton("HỦY", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int id) {
                // code to do on CANCEL tapped
                dialog.cancel();
            }
        });

        alertDialogBuilder.setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // code to do on NO tapped
                Intent i = new Intent(OrderFull.this, PaymentFood.class);
                String thanhtien = textView.getText().toString().trim();
                i.putExtra("total", thanhtien);
                if(myDB.isMasterEmpty()){
                    Toast.makeText(OrderFull.this, "Ôi bạn ôi giỏ hàng không có gì!", Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(i);
                }
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}