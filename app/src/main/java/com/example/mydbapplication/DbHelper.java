package com.example.mydbapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssurendran on 10/13/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "NamesDB";
    private static final String TABLE_NAME = "NamesTable";

    private static final String ID = "_id";
    private static final String NAME = "Name";
    private static final String AGE = "age";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + AGE + " INTEGRER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, contact.getName());
        values.put(AGE, contact.getAge());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, ID + "=?", new String[]{String.valueOf(id)}, null, null, AGE, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        Contact contact = new Contact(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));

        db.close();

        return contact;
    }

    public List<Contact> getAllContacts(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Contact> contactList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setAge(cursor.getInt(2));
                contactList.add(contact);
            }while(cursor.moveToNext());
        }

        return contactList;
    }

    public int getCount(){
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public void updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, contact.getName());
        values.put(AGE, contact.getAge());

        if(contact.getId() != -1) {
            db.update(TABLE_NAME, values, ID + "=?", new String[]{String.valueOf(contact.getId())});
        }
        db.close();
    }

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        if(id != -1) {
            db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
        }
        db.close();
    }

    public int getLastId(){
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if(cursor.moveToLast()){
            return cursor.getInt(0);
        }else{
            return -1;
        }

    }

    public void clearTable(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, null, null);
        db.close();

    }

}
