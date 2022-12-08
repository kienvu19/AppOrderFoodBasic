package com.example.OrderFoodAppbyKienVu.DatabaseFood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME = "FoodApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_food";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "food_title";
    private static final String COLUMN_SL = "food_sl";
    private static final String COLUMN_PRICE = "food_price";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_SL + " TEXT, " +
                COLUMN_PRICE + " INTEGER);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void addFood(String title, String sl, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_SL, sl);
        cv.put(COLUMN_PRICE, price);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Thêm món thất bại", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Thêm món thành công!", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor readAllFood(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public void updateData(String row_id, String title, String sl, int price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_SL, sl);
        cv.put(COLUMN_PRICE, price);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }
    public String getPrice(){
        SQLiteDatabase db = getReadableDatabase();
        String sAmount;
        String sQuery = "SELECT SUM(food_price) FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(sQuery, null);
        if(cursor.moveToFirst()){
            sAmount = String.valueOf(cursor.getInt(0));
        }
        else{
            sAmount = "0";
        }
        cursor.close();
        db.close();
        return sAmount;
    }
    public boolean isMasterEmpty() {

        boolean flag;
        String quString = "select exists(select 1 from " + TABLE_NAME  + ");";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(quString, null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        if (count ==1) {
            flag =  false;
        } else {
            flag = true;
        }
        cursor.close();
        db.close();

        return flag;
    }
}
