package com.example.dethimau;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NguyenVanAn_Sqlite extends SQLiteOpenHelper {
    public static final String TableName = "Contacct_An";
    public static final String Id = "Id";
    public static final String Name = "Fullname";
    public static final String Phone = "Phonenumber";

    public NguyenVanAn_Sqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // sql tạo bảng
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS " + TableName + "(" +
                Id + " Integer PRIMARY KEY AUTOINCREMENT, " +
                Name + " Text, " +
                Phone + " Text); ";


//        Thực thi
       sqLiteDatabase.execSQL(sqlCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //xóa table đã có
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableName);
        //tạo lại bảng
        onCreate(sqLiteDatabase);
    }
    public void addHoaDon(Contact_An hd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, hd.getId());
        values.put(Name, hd.getName());
        values.put(Phone, hd.getPhoneNum());
        long rowInserted = db.insert(TableName, null, values);
        db.close();
        if (rowInserted != -1) {
            //Insert success.

        } else {
            //Inser failed.
        }
    }

    public void editHoaDon(int id,Contact_An hd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, hd.getId());
        values.put(Name, hd.getName());
        values.put(Phone, hd.getPhoneNum());
        db.update(TableName,values,Id + "=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteHoaDon(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TableName +" WHERE ID = " + id;
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<Contact_An> getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Contact_An> list = new ArrayList<>();

        String sql = "SELECT * FROM " + TableName +" ORDER BY "+Name +" ASC";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                Contact_An ct = new Contact_An();
                ct.setId(cursor.getInt(0));
                ct.setName(cursor.getString(1));
                ct.setPhoneNum(cursor.getString(2));
                list.add(ct);
            }
        }
        return list;
    }

    public ArrayList<Contact_An> searchByName(String key){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Contact_An> list = new ArrayList<>();

        String sql = "SELECT * FROM " + TableName + " WHERE " + Name + " LIKE '%"+
                key + "%' " + " ORDER BY " + Name ;
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                Contact_An ct = new Contact_An();
                ct.setId(cursor.getInt(0));
                ct.setName(cursor.getString(1));
                ct.setPhoneNum(cursor.getString(2));
                list.add(ct);
            }
        }
        return list;
    }


//    public ArrayList<Contact_An> searchByPrice(int key){
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<Contact_An> list = new ArrayList<>();
//
//        String sql = "SELECT * FROM " + TableName + " WHERE " + DonGia +"*"+SoNgayLuuTru + " > "+ key + " ORDER BY " + SoPhong + " DESC";
//        Cursor cursor = db.rawQuery(sql,null);
//        if(cursor!=null){
//            while (cursor.moveToNext()){
//                Contact_An ct = new Contact_An();
//                ct.setId(cursor.getInt(0));
//                ct.setName(cursor.getString(1));
//                ct.setPhoneNum(cursor.getString(2));
//                list.add(ct);
//            }
//        }
//        return list;
//    }

    public Cursor getData(String sql){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(sql,null);
    }

    public void queryData(String sql){
        SQLiteDatabase db = this.getWritableDatabase() ;
        db.execSQL(sql);
    }
}
