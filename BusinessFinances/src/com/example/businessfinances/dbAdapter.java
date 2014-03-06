package com.example.businessfinances;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
public class dbAdapter {

    private SQLiteDatabase db;
    private dbHelper dbHelper;
    private final Context context;

    private static final String DB_NAME = "ent.db";
    private static final int DB_VERSION = 2;  // added semester, when you add or delete fields, you must update the version number!

    private static final String ENTRY_TABLE = "courses";
    public static final String ENT_ID = "ent_id";   // column 0
    public static final String ENT_NAME = "ent_name";
    public static final String ENT_PRICE = "ent_price";
    public static final String[] ENT_COLS = {ENT_ID, ENT_NAME, ENT_PRICE};

    public dbAdapter(Context ctx) {
        context = ctx;
        dbHelper = new dbHelper(context, DB_NAME, null, DB_VERSION);
    }

    public void open() throws SQLiteException {
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    public void close() {
        db.close();
    }

    // database update methods

    public long insertEntry(Entry ent) {
        // create a new row of values to insert
        ContentValues cvalues = new ContentValues();
        // assign values for each col
        cvalues.put(ENT_NAME, ent.getName());
        cvalues.put(ENT_PRICE, ent.getPrice());
        // add to course table in database
        return db.insert(ENTRY_TABLE, null, cvalues);
    }

    public boolean removeEntry(long ri) {
        return db.delete(ENTRY_TABLE, ENT_ID+"="+ri, null) > 0;
    }

    public boolean updatePrice(long ri, String pc) {
        ContentValues cvalue = new ContentValues();
        cvalue.put(ENT_PRICE, pc);
        return db.update(ENTRY_TABLE, cvalue, ENT_ID+"="+ri, null) > 0;
    }

    // database query methods
    public Cursor getAllEntries() {
        return db.query(ENTRY_TABLE, ENT_COLS, null, null, null, null, null);
    }
    
    public Cursor getAlphabetical() {
        return db.query(ENTRY_TABLE, ENT_COLS, null, null, null, null, ENT_NAME);
    }
    
    public Cursor getAcending() {
        return db.query(ENTRY_TABLE, ENT_COLS, null, null, null, null, ENT_PRICE);
    }

    public Cursor getDecending() {
        return db.query(ENTRY_TABLE, ENT_COLS, null, null, null, null, ENT_PRICE + " DESC");
    }
    
    public Cursor getCourseCursor(long ri) throws SQLException {
        Cursor result = db.query(true, ENTRY_TABLE, ENT_COLS, ENT_ID+"="+ri, null, null, null, null, null);
        if ((result.getCount() == 0) || !result.moveToFirst()) {
            throw new SQLException("No course items found for row: " + ri);
        }
        return result;
    }

    public Entry getEntryItem(long ri) throws SQLException {
        Cursor cursor = db.query(true, ENTRY_TABLE, ENT_COLS, ENT_ID+"="+ri, null, null, null, null, null);
        if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
            throw new SQLException("No course items found for row: " + ri);
        }
        // must use column indices to get column values
        int whatIndex = cursor.getColumnIndex(ENT_NAME);
        Entry result = new Entry(cursor.getString(whatIndex), cursor.getDouble(2));
        return result;
    }


    private static class dbHelper extends SQLiteOpenHelper {

        // SQL statement to create a new database
        private static final String DB_CREATE = "CREATE TABLE " + ENTRY_TABLE
                + " (" + ENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ENT_NAME + " TEXT,"
                + ENT_PRICE + " REAL);";

        public dbHelper(Context context, String name, CursorFactory fct, int version) {
            super(context, name, fct, version);
        }

        @Override
        public void onCreate(SQLiteDatabase adb) {
            // TODO Auto-generated method stub
            adb.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase adb, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            Log.w("GPAdb", "upgrading from version " + oldVersion + " to "
                    + newVersion + ", destroying old data");
            // drop old table if it exists, create new one
            // better to migrate existing data into new table
            adb.execSQL("DROP TABLE IF EXISTS " + ENTRY_TABLE);
            onCreate(adb);
        }
    } // GPAdbHelper class

} // GPAdbAdapter class

