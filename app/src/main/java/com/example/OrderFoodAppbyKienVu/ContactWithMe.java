package com.example.OrderFoodAppbyKienVu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ContactWithMe extends AppCompatActivity {
    ImageView fb,ins,zalo,skype,tele,call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_with_me);
        fb = findViewById(R.id.ic_fb);
        ins = findViewById(R.id.ic_insta);
        zalo = findViewById(R.id.ic_zalo);
        skype = findViewById(R.id.ic_skype);
        tele = findViewById(R.id.ic_tele);
        call = findViewById(R.id.ic_call);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/vukien2k1")));
            }
        });
        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/kienvu19")));
            }
        });
        zalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
        skype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
        tele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }
    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("THÔNG TIN");
        builder.setMessage("Vui lòng liên hệ với tôi: "+"\n Zalo: 0398194201 \n Telegram: @kienvu19 \n Skype: 0398194201");
        builder.setPositiveButton("OK", (dialogInterface, i) -> {

        });

        builder.create().show();
    }
}