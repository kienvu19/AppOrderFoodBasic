package com.example.OrderFoodAppbyKienVu;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity{
    private FoodAdapter foodAdapter;
    private List<Food> list;
    private FloatingActionButton user_btn, dangxuat, shopping, history, gotouser, contact;
    Animation fabOpen, fabClose, Foward, BackFoward;
    Boolean show_hide_floatAction = false;
    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SearchView searchView = findViewById(R.id.SearchView);
        searchView.clearFocus();
        RecyclerView rc_Foods = findViewById(R.id.rc_Food);
        user_btn = findViewById(R.id.user);
        dangxuat = findViewById(R.id.dangxuat);
        shopping = findViewById(R.id.donhang);
        history = findViewById(R.id.history);

        gotouser = findViewById(R.id.gotouser);
        contact = findViewById(R.id.contact);

        fabOpen = AnimationUtils.loadAnimation(this,R.anim.rotate_open);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.rotate_close);
        Foward = AnimationUtils.loadAnimation(this,R.anim.to_bottom);
        BackFoward = AnimationUtils.loadAnimation(this,R.anim.from_bottom);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rc_Foods.setLayoutManager(linearLayoutManager);

        foodAdapter = new FoodAdapter(this ,getListFoods());
        rc_Foods.setAdapter(foodAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rc_Foods.addItemDecoration(itemDecoration);

//        rc_Foods.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                if(dy > 0){
//                    user_btn.hide();
//                    dangxuat.hide();
//                    shopping.hide();
//                    history.hide();
//                    gotouser.hide();
//                }else{
//                    user_btn.show();
//                }
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        user_btn.setOnClickListener(view -> {
            animateFAB();
        });
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, OrderFull.class);
            startActivity(intent);
            }
        });
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HistoryOderFood.class));
            }
        });

        gotouser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RememberInfoUserActivity.class));
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ContactWithMe.class));
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void filterList(String Text){
        List<Food> filteredList = new ArrayList<>();
        for(Food food : list){
            if(food.getName().toLowerCase().contains(Text.toLowerCase(Locale.ROOT))){
                filteredList.add(food);
            }
        }
        foodAdapter.setFilteredList(filteredList);
    }
    public List<Food> getListFoods() {
        list = new ArrayList<>();
        list.add(new Food(R.drawable.banhmi, "Bánh Mì Thập Cẩm", 35000, "Bánh mì, thịt nguội, ba tê, xà lách, cà chua,.."));
        list.add(new Food(R.drawable.bunthapcam, "Bún Thập Cẩm", 40000, "Bún, cá, bò, giò , mọc,…"));
        list.add(new Food(R.drawable.heoquay, "Heo Quay Giòn Bì ", 85000, "Thịt heo"));
        list.add(new Food(R.drawable.thitxaxiu, "Thịt Xá Xíu", 85000, "Thịt heo"));
        list.add(new Food(R.drawable.bachi, "Ba Chỉ Cháy Cạnh", 85000, "Thịt heo, hành lá, ớt,.."));
        list.add(new Food(R.drawable.suonxao, "Sườn Xào Chua Ngọt", 105000, "Sườn heo, ớt, tỏi,.."));
        list.add(new Food(R.drawable.thitkhotau, "Thịt Kho Tàu", 105000, "Thịt heo, trứng chim cút,.."));
        list.add(new Food(R.drawable.suonnaucaichua, "Sườn Nấu Cải Chua", 95000, "Sườn heo, cải chua, hành lá,.."));
        list.add(new Food(R.drawable.changiongamtuong, "Chân Giò Ngâm Tương", 85000, "Chân giò heo, nước tương"));
        list.add(new Food(R.drawable.changiogiacay, "Chân Giò Giả Cày", 95000, "Chân giò heo, giềng, xả,.."));
        list.add(new Food(R.drawable.bosotvang, "Bò Sốt Vang", 105000, "Thịt bò, cà rốt, khoai tây,.."));
        list.add(new Food(R.drawable.boxaoxaot, "Bò Xào Sả Ớt", 105000, "Thịt bò, sả, ớt,…"));
        list.add(new Food(R.drawable.boluclac, "Bò Lúc Lắc", 105000, "Thịt bò, ớt chuông, ớt hiểm,…"));
        list.add(new Food(R.drawable.boxaobongcai, "Bò Xào Bông Cải", 105000, "Thịt bò, bông cải,.."));
        list.add(new Food(R.drawable.echxaoxaot, "Ếch Xào Xả Ớt", 105000, "Thịt ếch, xả, ớt,.."));
        list.add(new Food(R.drawable.ehcchienbotoi, "Ếch Chiên Bơ Tỏi", 105000, "Thịt ếch, bơ, tỏi,.."));
        list.add(new Food(R.drawable.echxaochuangot, "Ếch Xào Chua Ngọt", 105000, "Thịt ếch, ớt chuông, ớt hiểm,.."));
        list.add(new Food(R.drawable.garang, "Gà Rang Muối", 95000, "Thịt gà,sả, ớt, lá chanh.."));
        list.add(new Food(R.drawable.gachienmam, "Cánh Gà Chiên Mắm", 95000, "Cánh gà,tỏi, ớt,..."));
        list.add(new Food(R.drawable.gaxaoxaot, "Gà Xào Xả Ớt", 95000, "Thịt gà, xả, ớt,.."));
        list.add(new Food(R.drawable.gatrongoi, "Gà Trộn Gỏi", 95000, "Thịt gà, hành tây, đu đủ, hành lá,..."));
        list.add(new Food(R.drawable.gaxaonam, "Gà Xào Nấm", 95000, "Thịt gà, nấm hương, nấm đùi gà,.."));
        list.add(new Food(R.drawable.vitnauchao, "Vịt Nấu Chao", 95000, "Thịt vịt, chao , khoai,…"));
        list.add(new Food(R.drawable.trungchienthitbam, "Trứng Chiên Thịt Băm", 65000, "Trứng vịt, thịt heo, hành lá,.."));
        list.add(new Food(R.drawable.cadieuhongsotcachua, "Cá Diêu Hồng Sốt Cà Chua", 85000, "Cá diêu hồng, cà chua, hành lá,.."));
        list.add(new Food(R.drawable.chacuadong, "Chả Cua Đồng", 85000, "Trứng vịt, cua đồng, thịt heo,.."));
        list.add(new Food(R.drawable.daunhoithit, "Đậu Nhồi Thịt", 85000, "Đậu phụ, thịt heo,..."));
        list.add(new Food(R.drawable.canhgalagiang, "Cánh Gà Lá Rang", 65000, "Thịt gà, lá giang, ..."));
        list.add(new Food(R.drawable.canhcamangchua, "Canh Cá Măng Chua", 65000, "Cải chua, cá mú,..."));
        list.add(new Food(R.drawable.raumuong, "Rau Muống Xào Tỏi", 45000, "Rau muống, tỏi,.."));
        list.add(new Food(R.drawable.canhkhoqua, "Canh Khổ Qua", 45000, "Khổ qua, thịt heo, hành lá,.."));
        list.add(new Food(R.drawable.canhbido, "Canh Bí Đỏ", 45000, "Bí đỏ, sườn heo, hành lá,..."));
        list.add(new Food(R.drawable.coca, "Coca", 25000, "..."));
        list.add(new Food(R.drawable.bohuc, "Bò Húc", 25000, "..."));
        list.add(new Food(R.drawable.tradaocamsa, "Trà Đào Cam Sả", 45000, "..."));
        list.add(new Food(R.drawable.dauxanhrauma, "Đậu Xanh Rau Má", 45000, "..."));
        list.add(new Food(R.drawable.cafeden, "Cafe Đen", 30000, "..."));
        list.add(new Food(R.drawable.cafesua, "Cafe Sữa", 35000, "..."));
        list.add(new Food(R.drawable.coffecotdua, "Cafe Cốt Dừa", 40000, "..."));
        list.add(new Food(R.drawable.lavie, "Lavie", 15000, "..."));
        list.add(new Food(R.drawable.nuocepcarot, "Nước Ép Cà Rốt", 30000, "..."));
        list.add(new Food(R.drawable.nuocepduahau, "Nước Ép Dưa Hấu", 30000, "..."));
        list.add(new Food(R.drawable.quatlacsua, "Quất Lắc Sữa", 30000, "..."));
        list.add(new Food(R.drawable.samdua, "Sâm Dứa", 30000, "..."));
        return list;
    }
    private void animateFAB(){
        if(show_hide_floatAction){
            user_btn.startAnimation(Foward);
            dangxuat.startAnimation(fabClose);
            shopping.startAnimation(fabClose);
            history.startAnimation(fabClose);
            gotouser.startAnimation(fabClose);
            contact.startAnimation(fabClose);
            dangxuat.setClickable(false);
            shopping.setClickable(false);
            show_hide_floatAction = false;
        }else{
            user_btn.startAnimation(BackFoward);
            dangxuat.startAnimation(fabOpen);
            shopping.startAnimation(fabOpen);
            history.startAnimation(fabOpen);
            gotouser.startAnimation(fabOpen);
            contact.startAnimation(fabOpen);
            dangxuat.setClickable(true);
            shopping.setClickable(true);
            show_hide_floatAction = true;
        }
    }
    private void dialog(){
// alertdialog for exit the app
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

// set the title of the Alert Dialog
        alertDialogBuilder.setTitle("Thoát chương trình?");

// set dialog message
        alertDialogBuilder
                .setMessage("Bạn có chắc chắn muốn thoát?")
                .setCancelable(false)
                .setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        // what to do if YES is tapped
                        finishAffinity();
                        System.exit(0);
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
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}