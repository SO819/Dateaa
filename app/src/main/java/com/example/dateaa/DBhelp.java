package com.example.dateaa;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DBhelp {

    private static final String DATABASE_NAME = "first_DB";
    private static final String DATABASE_TABLE = "first_table";
    private static final int DATABASE_VERSION = 1;
    private final Context mCtx;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private SQLiteStatement insertStmt;

    private static final String DATABASE_CREATE="CREATE TABLE " +DATABASE_TABLE+ "" +
            " (    ID    INTEGER PRIMARY    KEY AUTOINCREMENT,    COIN INTEGER,    GARMENTS  TEXT,    ALARM INTEGER,    SEX INTEGER)";


    public static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }
        /**
         *
         * @param db         The database.
         * @param oldVersion The old database version.
         * @param newVersion The new database version.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    public void insertGarbage(){
        mDb.execSQL("INSERT INTO first_table VALUES(0,0,0,0,0);");
    }

    public void open() throws SQLException {

        mDbHelper = new DatabaseHelper(mCtx);

        mDb = mDbHelper.getWritableDatabase();
    }

    public DBhelp(Context ctx) {
        this.mCtx = ctx;
    }

    public void close() {
        mDbHelper.close();
    }

    public void updateCoin(int coin){
        insertStmt = mDb.compileStatement("UPDATE first_table SET COIN = ?");
        insertStmt.clearBindings();
        insertStmt.bindLong(1,coin);
        insertStmt.execute();
    }


    public Cursor AllRows() {
        return mDb.query(DATABASE_TABLE, null, null, null, null, null, null);
    }
    public boolean deleteAll() {
        return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }

}
