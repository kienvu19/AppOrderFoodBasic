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
        list.add(new Food(R.drawable.banhmi, "B??nh M?? Th???p C???m", 35000, "B??nh m??, th???t ngu???i, ba t??, x?? l??ch, c?? chua,.."));
        list.add(new Food(R.drawable.bunthapcam, "B??n Th???p C???m", 40000, "B??n, c??, b??, gi?? , m???c,???"));
        list.add(new Food(R.drawable.heoquay, "Heo Quay Gi??n B?? ", 85000, "Th???t heo"));
        list.add(new Food(R.drawable.thitxaxiu, "Th???t X?? X??u", 85000, "Th???t heo"));
        list.add(new Food(R.drawable.bachi, "Ba Ch??? Ch??y C???nh", 85000, "Th???t heo, h??nh l??, ???t,.."));
        list.add(new Food(R.drawable.suonxao, "S?????n X??o Chua Ng???t", 105000, "S?????n heo, ???t, t???i,.."));
        list.add(new Food(R.drawable.thitkhotau, "Th???t Kho T??u", 105000, "Th???t heo, tr???ng chim c??t,.."));
        list.add(new Food(R.drawable.suonnaucaichua, "S?????n N???u C???i Chua", 95000, "S?????n heo, c???i chua, h??nh l??,.."));
        list.add(new Food(R.drawable.changiongamtuong, "Ch??n Gi?? Ng??m T????ng", 85000, "Ch??n gi?? heo, n?????c t????ng"));
        list.add(new Food(R.drawable.changiogiacay, "Ch??n Gi?? Gi??? C??y", 95000, "Ch??n gi?? heo, gi???ng, x???,.."));
        list.add(new Food(R.drawable.bosotvang, "B?? S???t Vang", 105000, "Th???t b??, c?? r???t, khoai t??y,.."));
        list.add(new Food(R.drawable.boxaoxaot, "B?? X??o S??? ???t", 105000, "Th???t b??, s???, ???t,???"));
        list.add(new Food(R.drawable.boluclac, "B?? L??c L???c", 105000, "Th???t b??, ???t chu??ng, ???t hi???m,???"));
        list.add(new Food(R.drawable.boxaobongcai, "B?? X??o B??ng C???i", 105000, "Th???t b??, b??ng c???i,.."));
        list.add(new Food(R.drawable.echxaoxaot, "???ch X??o X??? ???t", 105000, "Th???t ???ch, x???, ???t,.."));
        list.add(new Food(R.drawable.ehcchienbotoi, "???ch Chi??n B?? T???i", 105000, "Th???t ???ch, b??, t???i,.."));
        list.add(new Food(R.drawable.echxaochuangot, "???ch X??o Chua Ng???t", 105000, "Th???t ???ch, ???t chu??ng, ???t hi???m,.."));
        list.add(new Food(R.drawable.garang, "G?? Rang Mu???i", 95000, "Th???t g??,s???, ???t, l?? chanh.."));
        list.add(new Food(R.drawable.gachienmam, "C??nh G?? Chi??n M???m", 95000, "C??nh g??,t???i, ???t,..."));
        list.add(new Food(R.drawable.gaxaoxaot, "G?? X??o X??? ???t", 95000, "Th???t g??, x???, ???t,.."));
        list.add(new Food(R.drawable.gatrongoi, "G?? Tr???n G???i", 95000, "Th???t g??, h??nh t??y, ??u ?????, h??nh l??,..."));
        list.add(new Food(R.drawable.gaxaonam, "G?? X??o N???m", 95000, "Th???t g??, n???m h????ng, n???m ????i g??,.."));
        list.add(new Food(R.drawable.vitnauchao, "V???t N???u Chao", 95000, "Th???t v???t, chao , khoai,???"));
        list.add(new Food(R.drawable.trungchienthitbam, "Tr???ng Chi??n Th???t B??m", 65000, "Tr???ng v???t, th???t heo, h??nh l??,.."));
        list.add(new Food(R.drawable.cadieuhongsotcachua, "C?? Di??u H???ng S???t C?? Chua", 85000, "C?? di??u h???ng, c?? chua, h??nh l??,.."));
        list.add(new Food(R.drawable.chacuadong, "Ch??? Cua ?????ng", 85000, "Tr???ng v???t, cua ?????ng, th???t heo,.."));
        list.add(new Food(R.drawable.daunhoithit, "?????u Nh???i Th???t", 85000, "?????u ph???, th???t heo,..."));
        list.add(new Food(R.drawable.canhgalagiang, "C??nh G?? L?? Rang", 65000, "Th???t g??, l?? giang, ..."));
        list.add(new Food(R.drawable.canhcamangchua, "Canh C?? M??ng Chua", 65000, "C???i chua, c?? m??,..."));
        list.add(new Food(R.drawable.raumuong, "Rau Mu???ng X??o T???i", 45000, "Rau mu???ng, t???i,.."));
        list.add(new Food(R.drawable.canhkhoqua, "Canh Kh??? Qua", 45000, "Kh??? qua, th???t heo, h??nh l??,.."));
        list.add(new Food(R.drawable.canhbido, "Canh B?? ?????", 45000, "B?? ?????, s?????n heo, h??nh l??,..."));
        list.add(new Food(R.drawable.coca, "Coca", 25000, "..."));
        list.add(new Food(R.drawable.bohuc, "B?? H??c", 25000, "..."));
        list.add(new Food(R.drawable.tradaocamsa, "Tr?? ????o Cam S???", 45000, "..."));
        list.add(new Food(R.drawable.dauxanhrauma, "?????u Xanh Rau M??", 45000, "..."));
        list.add(new Food(R.drawable.cafeden, "Cafe ??en", 30000, "..."));
        list.add(new Food(R.drawable.cafesua, "Cafe S???a", 35000, "..."));
        list.add(new Food(R.drawable.coffecotdua, "Cafe C???t D???a", 40000, "..."));
        list.add(new Food(R.drawable.lavie, "Lavie", 15000, "..."));
        list.add(new Food(R.drawable.nuocepcarot, "N?????c ??p C?? R???t", 30000, "..."));
        list.add(new Food(R.drawable.nuocepduahau, "N?????c ??p D??a H???u", 30000, "..."));
        list.add(new Food(R.drawable.quatlacsua, "Qu???t L???c S???a", 30000, "..."));
        list.add(new Food(R.drawable.samdua, "S??m D???a", 30000, "..."));
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
        alertDialogBuilder.setTitle("Tho??t ch????ng tr??nh?");

// set dialog message
        alertDialogBuilder
                .setMessage("B???n c?? ch???c ch???n mu???n tho??t?")
                .setCancelable(false)
                .setPositiveButton("C??", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        // what to do if YES is tapped
                        finishAffinity();
                        System.exit(0);
                    }
                });

        alertDialogBuilder.setNeutralButton("H???Y", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        // code to do on CANCEL tapped
                        dialog.cancel();
                    }
                });

        alertDialogBuilder.setNegativeButton("KH??NG", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // code to do on NO tapped
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}