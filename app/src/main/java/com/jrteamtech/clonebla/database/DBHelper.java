package com.jrteamtech.clonebla.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jrteamtech.clonebla.model.CarInfoModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "blablacar_db";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(CarInfoModel.CREATE_TABLE_QUERY);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + CarInfoModel.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertCarInfo(CarInfoModel carInfoModel) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(CarInfoModel.COLUMN_IMAGE, carInfoModel.getImage());
        values.put(CarInfoModel.COLUMN_MAKE, carInfoModel.getMake());
        values.put(CarInfoModel.COLUMN_MODEL, carInfoModel.getModel());
        values.put(CarInfoModel.COLUMN_TYPE, carInfoModel.getType());
        values.put(CarInfoModel.COLUMN_COLOR_LABEL, carInfoModel.getColor_label());
        values.put(CarInfoModel.COLUMN_COLOR_VALUE, carInfoModel.getColor_value());
        values.put(CarInfoModel.COLUMN_YEAR, carInfoModel.getYear());

        // insert row
        long id = db.insert(CarInfoModel.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

//    public Note getNote(long id) {
//        // get readable database as we are not inserting anything
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(Note.TABLE_NAME,
//                new String[]{Note.COLUMN_ID, Note.COLUMN_NOTE, Note.COLUMN_TIMESTAMP},
//                Note.COLUMN_ID + "=?",
//                new String[]{String.valueOf(id)}, null, null, null, null);
//
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        // prepare note object
//        Note note = new Note(
//                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
//                cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)),
//                cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));
//
//        // close the db connection
//        cursor.close();
//
//        return note;
//    }

    public List<CarInfoModel> getAllCarInfos() {
        List<CarInfoModel> carInfoModels = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + CarInfoModel.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CarInfoModel info = new CarInfoModel();
                info.setId(cursor.getInt(cursor.getColumnIndex(CarInfoModel.COLUMN_ID)));
                info.setImage(cursor.getInt(cursor.getColumnIndex(CarInfoModel.COLUMN_IMAGE)));
                info.setMake(cursor.getString(cursor.getColumnIndex(CarInfoModel.COLUMN_MAKE)));
                info.setModel(cursor.getString(cursor.getColumnIndex(CarInfoModel.COLUMN_MODEL)));
                info.setType(cursor.getString(cursor.getColumnIndex(CarInfoModel.COLUMN_TYPE)));
                info.setColor_label(cursor.getString(cursor.getColumnIndex(CarInfoModel.COLUMN_COLOR_LABEL)));
                info.setColor_value(cursor.getInt(cursor.getColumnIndex(CarInfoModel.COLUMN_COLOR_VALUE)));
                info.setYear(cursor.getString(cursor.getColumnIndex(CarInfoModel.COLUMN_YEAR)));

                carInfoModels.add(info);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return carInfoModels;
    }

    public int getCarInfoCount() {
        String countQuery = "SELECT  * FROM " + CarInfoModel.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public int updateCarInfo(CarInfoModel carInfoModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CarInfoModel.COLUMN_IMAGE, carInfoModel.getImage());
        values.put(CarInfoModel.COLUMN_MAKE, carInfoModel.getMake());
        values.put(CarInfoModel.COLUMN_MODEL, carInfoModel.getModel());
        values.put(CarInfoModel.COLUMN_TYPE, carInfoModel.getType());
        values.put(CarInfoModel.COLUMN_COLOR_LABEL, carInfoModel.getColor_label());
        values.put(CarInfoModel.COLUMN_COLOR_VALUE, carInfoModel.getColor_value());
        values.put(CarInfoModel.COLUMN_YEAR, carInfoModel.getYear());

        // updating row
        return db.update(CarInfoModel.TABLE_NAME, values, CarInfoModel.COLUMN_ID + " = ?",
                new String[]{String.valueOf(carInfoModel.getId())});
    }

    public void deleteCarInfo(CarInfoModel carInfoModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CarInfoModel.TABLE_NAME, CarInfoModel.COLUMN_ID + " = ?",
                new String[]{String.valueOf(carInfoModel.getId())});
        db.close();
    }
}
