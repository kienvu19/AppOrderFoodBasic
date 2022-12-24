package  com.example.OrderFoodAppbyKienVu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.OrderFoodAppbyKienVu.DatabaseHistory.DBHelperHistory;

public class HistoryOderFood extends AppCompatActivity {
    DBHelperHistory dbHistory;
    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    ImageView imgback;
    int[]idd;
    String[]hotenn;
    String[]diachii;
    String[]sdtt;
    String[]magiamgiaa;
    String[]hinhthucttt;
    String[]tongtienn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_oder_food);
        dbHistory = new DBHelperHistory(this);
        listView = findViewById(R.id.lvhistory);
        imgback = findViewById(R.id.backhistory);
        hienthiHistory();
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void hienthiHistory() {
        sqLiteDatabase = dbHistory.getReadableDatabase();
        Cursor cursor =sqLiteDatabase.rawQuery("SELECT * FROM HistoryFoodApp",null);
        if(cursor.getCount()>0){
            idd = new int[cursor.getCount()];
            hotenn = new String[cursor.getCount()];
            diachii = new String[cursor.getCount()];
            sdtt = new String[cursor.getCount()];
            magiamgiaa = new String[cursor.getCount()];
            hinhthucttt = new String[cursor.getCount()];
            tongtienn = new String[cursor.getCount()];
            int i = 0;
            while(cursor.moveToNext()){
                idd[i] = cursor.getInt(0);
                hotenn[i] = cursor.getString(1);
                diachii[i] = cursor.getString(2);
                sdtt[i] = cursor.getString(3);
                magiamgiaa[i] = cursor.getString(4);
                hinhthucttt[i] = cursor.getString(5);
                tongtienn[i] = cursor.getString(6);
                i++;
            }
            Custom custom = new Custom();
            listView.setAdapter(custom);
        }
    }

    private class Custom extends BaseAdapter {

        @Override
        public int getCount() {
            return idd.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView id,hoten,diachi,sdt,magiamgia,hinhthuctt,tongtien;
            view = LayoutInflater.from(HistoryOderFood.this).inflate(R.layout.order_history, viewGroup, false);
            id = view.findViewById(R.id.iduser);
            hoten = view.findViewById(R.id.tenuser);
            diachi = view.findViewById(R.id.diachiuser);
            sdt = view.findViewById(R.id.sdtuser);
            magiamgia = view.findViewById(R.id.magiamuser);
            hinhthuctt = view.findViewById(R.id.thanhtoanuser);
            tongtien = view.findViewById(R.id.totaluser);

            id.setText(String.valueOf(idd[i]));
            hoten.setText(hotenn[i]);
            diachi.setText(diachii[i]);
            sdt.setText(sdtt[i]);
            magiamgia.setText(magiamgiaa[i]);
            hinhthuctt.setText(hinhthucttt[i]);
            tongtien.setText(tongtienn[i]);

            return view;
        }
    }
}