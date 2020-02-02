package com.example.kyrgyzpedigree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "persons_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Person.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Person.TABLE_NAME);
        onCreate(db);
    }

    public void insertPerson(Person person) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Person.COLUMN_NAME, person.getName());
        values.put(Person.COLUMN_EMAIL, person.getEmail());
        values.put(Person.COLUMN_MESTOJITELSTVA, person.getMestojitelstva());
        values.put(Person.COLUMN_GODROJDENIYA, person.getGodrojdeniya());
        values.put(Person.COLUMN_NAME_DAD, person.getNamedad());
        values.put(Person.COLUMN_NAME_MOM, person.getNamemom());
        values.put(Person.COLUMN_ROD, person.getRod());
        values.put(Person.COLUMN_PODROD, person.getPodrod());
        db.insert(Person.TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Person> getAllPersons() {
        ArrayList<Person> persons = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Person.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setId(cursor.getInt(cursor.getColumnIndex(Person.COLUMN_ID)));
                person.setName(cursor.getString(cursor.getColumnIndex(Person.COLUMN_NAME)));
                person.setEmail(cursor.getString(cursor.getColumnIndex(Person.COLUMN_EMAIL)));
                person.setMestojitelstva(cursor.getString(cursor.getColumnIndex(Person.COLUMN_MESTOJITELSTVA)));
                person.setGodrojdeniya(cursor.getString(cursor.getColumnIndex(Person.COLUMN_GODROJDENIYA)));
                person.setNamedad(cursor.getString(cursor.getColumnIndex(Person.COLUMN_NAME_MOM)));
                person.setNamemom(cursor.getString(cursor.getColumnIndex(Person.COLUMN_NAME_MOM)));
                person.setRod(cursor.getString(cursor.getColumnIndex(person.COLUMN_ROD)));
                person.setPodrod(cursor.getString(cursor.getColumnIndex(person.COLUMN_PODROD)));
                persons.add(person);
            } while (cursor.moveToNext());
        }
        db.close();
        return persons;
    }

    public ArrayList<Person> getAllPersonsSanjyra(String sanjyra) {
        ArrayList<Person> persons = new ArrayList<>();

        //String selectQuery = "SELECT  * FROM " + Person.TABLE_NAME + " where "+Person.COLUMN_ROD+"='"+sanjyra+"'";
        String selectQuery = "SELECT  * FROM " + Person.TABLE_NAME + " where "+Person.COLUMN_PODROD+"='"+sanjyra+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setId(cursor.getInt(cursor.getColumnIndex(Person.COLUMN_ID)));
                person.setName(cursor.getString(cursor.getColumnIndex(Person.COLUMN_NAME)));
                person.setEmail(cursor.getString(cursor.getColumnIndex(Person.COLUMN_EMAIL)));
                person.setMestojitelstva(cursor.getString(cursor.getColumnIndex(Person.COLUMN_MESTOJITELSTVA)));
                person.setGodrojdeniya(cursor.getString(cursor.getColumnIndex(Person.COLUMN_GODROJDENIYA)));
                person.setNamedad(cursor.getString(cursor.getColumnIndex(Person.COLUMN_NAME_DAD)));
                person.setNamemom(cursor.getString(cursor.getColumnIndex(Person.COLUMN_NAME_MOM)));
                person.setRod(cursor.getString(cursor.getColumnIndex(Person.COLUMN_ROD)));
                person.setPodrod(cursor.getString(cursor.getColumnIndex(Person.COLUMN_PODROD)));
                persons.add(person);
            } while (cursor.moveToNext());
        }
        db.close();
        return persons;
    }

    public void deletePerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Person.TABLE_NAME, Person.COLUMN_ID + " = ?",
                new String[]{String.valueOf(person.getId())});
        db.close();
    }

}
