package com.example.OrderFoodAppbyKienVu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import DatabaseInfomationUser.DBInfoUser;

public class ShowInfoUserRememberActivity extends AppCompatActivity {
    DBInfoUser dbInfo;
    RecyclerView recyclerView;
    ImageView backsave;
    ArrayList<String> user_id, user_ten, user_diachi, user_sdt;
    InfoUserAdapter infoUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info_user_remember);
        recyclerView = findViewById(R.id.lvuser);
        backsave = findViewById(R.id.backsave);
        dbInfo = new DBInfoUser(ShowInfoUserRememberActivity.this);
        user_id = new ArrayList<>();
        user_ten = new ArrayList<>();
        user_diachi = new ArrayList<>();
        user_sdt = new ArrayList<>();
        storeDataInArrays();
        infoUserAdapter = new InfoUserAdapter(ShowInfoUserRememberActivity.this,this, user_id, user_ten, user_diachi, user_sdt);
        recyclerView.setAdapter(infoUserAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowInfoUserRememberActivity.this));


        backsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void storeDataInArrays() {
        Cursor cursor = dbInfo.readAllInfoUser();
        if(cursor.getCount() == 0){
            Toast.makeText(ShowInfoUserRememberActivity.this, "Chưa có thông tin!", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                user_id.add(cursor.getString(0));
                user_ten.add(cursor.getString(1));
                user_diachi.add(cursor.getString(2));
                user_sdt.add(cursor.getString(3));
            }
        }
    }

}