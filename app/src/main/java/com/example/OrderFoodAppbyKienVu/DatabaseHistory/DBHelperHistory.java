package com.example.OrderFoodAppbyKienVu.DatabaseHistory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelperHistory extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME = "FoodAppHistory.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "HistoryFoodApp";
    private static final String COLUMN_ID = "_id";

    private static final String COLUMN_NAME = "name_user";
    private static final String COLUMN_ADDRESS = "address_user";
    private static final String COLUMN_NUMBERPHONE = "numberphone_user";
    private static final String COLUMN_DISCOUNT = "discount_user";
    private static final String COLUMN_CART = "cart_user";
    private static final String COLUMN_TOTAL = "total_user";
    public DBHelperHistory(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_NUMBERPHONE + " TEXT, " +
                COLUMN_DISCOUNT + " TEXT, " +
                COLUMN_CART + " TEXT, " +
                COLUMN_TOTAL+ " TEXT );";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public void addInformationUser(String name, String addr, String numberphone, String discount, String cart, String total){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_ADDRESS, addr);
        cv.put(COLUMN_NUMBERPHONE, numberphone);
        cv.put(COLUMN_DISCOUNT, discount);
        cv.put(COLUMN_CART, cart);
        cv.put(COLUMN_TOTAL, total);

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Thất bại~", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Thành công!!", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor readAllInformation(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
