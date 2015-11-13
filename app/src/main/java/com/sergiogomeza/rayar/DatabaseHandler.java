package com.sergiogomeza.rayar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio on 10/11/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="ContactManager",
                                TABLE_CONTACTS="contactos",
                                KEY_ID="id",
                                KEY_NAME="name",
                                KEY_PHONE="phone",
                                KEY_EMAIL="email",
                                KEY_IMAGEURI="imageuri";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT," + KEY_PHONE + " TEXT," + KEY_EMAIL + " TEXT," + KEY_IMAGEURI + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        onCreate(db);
    }

    public void createContact(contact Contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, Contact.get_name());
        values.put(KEY_PHONE, Contact.get_phone());
        values.put(KEY_EMAIL, Contact.get_email());
        values.put(KEY_IMAGEURI, Contact.get_imageURI().toString());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public contact getContact(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,KEY_NAME,KEY_PHONE,KEY_EMAIL,KEY_IMAGEURI},KEY_ID + "=?",new String[]{String.valueOf(id)},null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        contact Contact = new contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),Uri.parse(cursor.getString(4)));
        return Contact;
    }

    public int getContactCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);
        int count = cursor.getCount();
        db.close();
        cursor.close();

        return count;
    }

    public List<contact> getAllcontacts(){
        List<contact> contacts = new ArrayList<contact>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS,null);

        if(cursor.moveToFirst()){
            do{
                contacts.add(new contact(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),Uri.parse(cursor.getString(4))));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contacts;
    }
}
