package com.example.OrderFoodAppbyKienVu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoneOrderFood extends AppCompatActivity {
    Button btnhome,btnview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_order_food);
        btnhome = findViewById(R.id.btnHome);
        btnview = findViewById(R.id.btnviewHistory);

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(DoneOrderFood.this, MainActivity.class));
            }
        });
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoneOrderFood.this, HistoryOderFood.class));
            }
        });
    }
}