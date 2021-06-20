package com.example.sqlquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmployeeDB {
    public static final String KEY_ID="_id";
    public static final String KEY_NAME="person_name";
    public static final String KEY_NUMBER="_number";

    private final String DATABASE_NAME ="EmployeeDB";
    private final String DATABASE_TABLE ="EmployeeDB";
    private final int DATABASE_VERSION = 1;

    private DBhelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDataBase;

    public EmployeeDB(Context context)
    {

        ourContext = context;
    }

    private class DBhelper extends SQLiteOpenHelper{
        public DBhelper(Context context)
        {
            super(context, DATABASE_NAME,null,DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String sqlCode = "CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_NAME + " TEXT NOT NULL,"
                    + KEY_NUMBER  + " TEXT NOT NULL);";
            db.execSQL(sqlCode);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);

        }
    }

    public EmployeeDB open()
    {
        ourHelper = new DBhelper(ourContext);
        ourDataBase = ourHelper.getWritableDatabase();
        return this;

    }
    public void close()
    {
        ourHelper.close();
    }
    public long addData(String name, String number)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_NUMBER,number);
        return ourDataBase.insert(DATABASE_TABLE,null,cv);



    }
    public String getData()
    {
        String []columns = new String[]{KEY_ID,KEY_NAME,KEY_NUMBER};
        Cursor c;
        c = ourDataBase.query(DATABASE_TABLE, columns, null,null,null,null,null);
        String result = "";
       int iRowId = c.getColumnIndex(KEY_ID);
       int iRowName = c.getColumnIndex(KEY_NAME);
       int iRowNumber = c.getColumnIndex(KEY_NUMBER);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            result = result + c.getString(iRowId) + ": "+ c.getString(iRowName) + ": "
                    +  c.getString(iRowNumber)+ "\n";
        }
        c.close();
        return result;

    }

    public long update(String id, String name, String number)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_NUMBER,number);
        return ourDataBase.update(DATABASE_TABLE, cv, KEY_ID + "=?", new String[]{id});
    }
    public long delete(String id)
    {
        return ourDataBase.delete(DATABASE_TABLE, KEY_ID + "=?", new String[]{id});

    }


}
