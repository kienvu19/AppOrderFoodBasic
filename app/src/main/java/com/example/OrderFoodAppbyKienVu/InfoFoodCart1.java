package com.example.OrderFoodAppbyKienVu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.OrderFoodAppbyKienVu.DatabaseFood.DBHelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class InfoFoodCart1 extends AppCompatActivity{
    private TextView price_Food;
    private TextView SL;
    private ImageView back;
    int soluong = 1;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_food_cart1);
        CircleImageView imgFood = findViewById(R.id.img_food);
        TextView nameFood = findViewById(R.id.tv_nameFood);
        price_Food = findViewById(R.id.price_food);
        SL = findViewById(R.id.quantity);
        back = findViewById(R.id.back);
        TextView detail1 = findViewById(R.id.descriptioninfo);
        ImageButton add = findViewById(R.id.addquantity);
        ImageButton minus = findViewById(R.id.subquantity);
        Button addtocart = findViewById(R.id.addtocart);
        Button check1 = findViewById(R.id.check);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Food img = (Food) bundle.get("imgFood");
        Food name = (Food) bundle.get("nameFood");
        Food price = (Food) bundle.get("priceFood");
        Food detail = (Food) bundle.get("detailFood");

        imgFood.setImageResource(img.getImage());
        nameFood.setText(name.getName());
        detail1.setText(detail.getGioithieu());

        price_Food.setText(currencyVN.format(price.getGia()));
        displayQuantity();
        add.setOnClickListener(view -> {
            soluong++;
                displayQuantity();
                long price_FOOD = (long) price.getGia() * soluong;
                String setnewPrice = String.valueOf(price_FOOD);
                price_Food.setText(currencyVN.format(Integer.parseInt(setnewPrice)));
//            }
        });
        minus.setOnClickListener(view -> {
            if (soluong <= 1) {
                finish();
            }
            else{
                soluong--;
                displayQuantity();
                long price_FOOD = (long) price.getGia() * soluong;
                String setnewPrice = String.valueOf(price_FOOD);
                price_Food.setText(currencyVN.format(Integer.parseInt(setnewPrice)));
            }
        });
        addtocart.setOnClickListener(view -> {
                DB = new DBHelper(InfoFoodCart1.this);
                int b = Integer.parseInt(SL.getText().toString().trim()) * price.getGia();
                DB.addFood(nameFood.getText().toString().trim(), SL.getText().toString().trim(), String.valueOf(b));
        });
        check1.setOnClickListener(view -> {
                Intent i = new Intent(InfoFoodCart1.this, OrderFull.class);
                startActivity(i);
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void displayQuantity() {
        SL.setText(String.valueOf(soluong));
    }
}