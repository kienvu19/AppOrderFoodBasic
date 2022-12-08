package com.example.OrderFoodAppbyKienVu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.OrderFoodAppbyKienVu.DatabaseFood.DBHelper;

public class UpdateDeleteActivity extends AppCompatActivity {
    TextView title_input, price_input, sl_input;
    ImageView  back;
    String id, title, price, sl;
    Button button, delete_button;
    ImageView cong,tru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        title_input = findViewById(R.id.title_input2);
        sl_input = findViewById(R.id.SL_input2);
        price_input = findViewById(R.id.price_input2);
        delete_button = findViewById(R.id.delete_button);
        back = findViewById(R.id.back);
        cong = findViewById(R.id.imgcong);
        tru = findViewById(R.id.imgtru);

        button = findViewById(R.id.buttonupdate);

        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }
        delete_button.setOnClickListener(view -> confirmDialog());
        back.setOnClickListener(view -> finish());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper myDB = new DBHelper(UpdateDeleteActivity.this);
                title = title_input.getText().toString().trim();
                sl = sl_input.getText().toString().trim();
                price = price_input.getText().toString().trim();

                int price_tong = Integer.parseInt(price) * Integer.parseInt(sl);
                myDB.updateData(id, title, sl, price_tong);
                finish();
            }
        });
        tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String soluong = sl_input.getText().toString().trim();
                int soluongx = Integer.parseInt(soluong);
                soluongx--;
                if(soluongx<1){
                    Toast.makeText(UpdateDeleteActivity.this, "Không nhỏ hơn 1", Toast.LENGTH_SHORT).show();
                }
                else{
                    sl_input.setText(String.valueOf(soluongx));
                }
            }
        });
        cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String soluong = sl_input.getText().toString().trim();
                int soluongx = Integer.parseInt(soluong);
                soluongx++;
                if(soluongx>20){
                    Toast.makeText(UpdateDeleteActivity.this, "Không lớn hơn 20", Toast.LENGTH_SHORT).show();
                }
                else{
                    sl_input.setText(String.valueOf(soluongx));
                }
            }
        });
    }

    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa " + title + " ?");
        builder.setMessage("Bạn có chắc chắn muốn xóa " + title + " ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            DBHelper myDB = new DBHelper(UpdateDeleteActivity.this);
            myDB.deleteOneRow(id);
            finish();
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {

        });
        builder.create().show();
    }

    private void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("sl") && getIntent().hasExtra("price")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            sl = getIntent().getStringExtra("sl");
            price = getIntent().getStringExtra("price");


            int price_goc = Integer.parseInt(price) / Integer.parseInt(sl);
            //Setting Intent Data
            title_input.setText(title);
            sl_input.setText(sl);
            price_input.setText(String.valueOf(price_goc));
//            Log.d("stev", title+" "+sl+" "+price);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}