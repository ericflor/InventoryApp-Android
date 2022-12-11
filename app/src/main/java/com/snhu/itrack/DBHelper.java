package com.snhu.itrack;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "inventory.db";

    public static final String INVENTORY_TABLE_NAME = "inventory";
    public static final String INVENTORY_COLUMN_ID = "id";
    public static final String INVENTORY_COLUMN_NAME = "name";
    public static final String INVENTORY_COLUMN_COUNT = "count";

    public static final String USERS_TABLE_NAME = "users";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_EMAIL = "email";
    public static final String USERS_COLUMN_PASSWORD = "password";

    public DBHelper(Context context){
        super(context, DATABASE_NAME,null,1);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS inventory");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table users " +
                        "(id integer primary key, email text, password text)"
        );

        db.execSQL(
                "create table inventory " +
                        "(id integer primary key, name text, count integer)"
        );
    }
    public boolean insertItem (String name, String count) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("count", count);
        db.insert("users", null, contentValues);
        return true;
    }

    public void insertUser (String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        db.insert("users", null, contentValues);
    }

    public Cursor getUserByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from users where id= "+ id +" ", null );
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, USERS_TABLE_NAME);
    }

    public boolean updateItem (Integer id, String name, String count) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("count", count);
        db.update("users", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteItem (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("users",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    @SuppressLint("Range")
    public List<String> getAllUsers() {

        List<String> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from users", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(res.getString(res.getColumnIndex(USERS_COLUMN_EMAIL)));
            res.moveToNext();
        }
        System.out.println(array_list);
        return array_list;
    }

    @SuppressLint("Range")
    public List<String> getAllItems() {

        List<String> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from inventory", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(res.getString(res.getColumnIndex(INVENTORY_COLUMN_NAME)));
            res.moveToNext();
        }
        System.out.println(array_list);
        return array_list;
    }
}
