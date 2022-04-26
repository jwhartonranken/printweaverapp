package tech.jameswharton.pwcompanionapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "MagicItemLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "items";
    private static final String COLUMN_NAME = "item_name";
    private static final String COLUMN_DESC = "item_description";
    private static final String COLUMN_TYPE = "item_type";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_NAME + " TEXT, " +
                COLUMN_DESC + " TEXT, " +
                COLUMN_TYPE + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addItem(String name, String desc, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESC, desc);
        cv.put(COLUMN_TYPE, type);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            // Insertion failed
            Toast.makeText(context, "INSERT FAILURE", Toast.LENGTH_LONG).show();
        }
        else {
            // Insertion success
            Toast.makeText(context, "INSERT SUCCESS", Toast.LENGTH_LONG).show();
        }
    }

    public void updateData(String name, String desc, String type, String oldDesc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESC, desc);
        cv.put(COLUMN_TYPE, type);

        long result = db.update(TABLE_NAME, cv, "item_description=?", new String[]{oldDesc});
        if (result == -1) {
            Toast.makeText(context, "UPDATE FAILURE", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "UPDATE SUCCESS", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteOneRecord(String desc) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME, "item_description=?", new String[]{desc});
        if (result == -1 ) {
            Toast.makeText(context, "DELETE FAILURE", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context,"DELETE SUCCESS", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteAllRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM `sqlite_sequence` WHERE `name` = '" + TABLE_NAME + "';");
        db.execSQL("DELETE FROM " + TABLE_NAME);

    }

    Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
