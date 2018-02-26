package myapplication.example.com.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String DIARY = "DIARY";

    // Table columns User
   /* public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASS = "pass";
    public static final String CODE = "code";*/

    // Table colums Diary
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String DATETIME = "dateTime";
    public static final String CATEGORY = "category";
    public static final String FAVORITE = "favorite";

    // Database Information
    static final String DB_NAME = "DVD.DB";

    // database version
    static final int DB_VERSION = 1;

    // Create table diary
    private static final String CREATE_TABLE_DIARY = "create table " + DIARY + "(" + _ID
           + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  TITLE + " TEXT NOT NULL, " + CONTENT + " TEXT NOT NULL, " + DATETIME + " TEXT, " + CATEGORY + " TEXT, " + FAVORITE + " INTEGER NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DIARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DIARY );
        onCreate(db);
    }
}
