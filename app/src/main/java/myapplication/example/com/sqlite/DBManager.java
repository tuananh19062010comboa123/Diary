package myapplication.example.com.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import myapplication.example.com.entity.Diary;

public class DBManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }


    /**
     * @return diary
     */
    public Diary fetchDiary() {
        Diary diary = new Diary();
        String[] diary_colomns = new String[]{DatabaseHelper._ID, DatabaseHelper.TITLE, DatabaseHelper.CONTENT, DatabaseHelper.DATETIME ,DatabaseHelper.CATEGORY, DatabaseHelper.FAVORITE};
        Cursor cursor = database.query(DatabaseHelper.DIARY, diary_colomns, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String diary_Id = cursor.getString(cursor.getColumnIndex(DatabaseHelper._ID));
                Log.d("diary_Id", diary_Id);
                String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TITLE));
                Log.d("title", title);
                String content = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTENT));
                Log.d("content", content);
                String dateTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DATETIME));
                Log.d("dateTime", dateTime);
                String category = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CATEGORY));
                Log.d("category", category);
                String favorite = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FAVORITE));
                Log.d("favorite", favorite);
                diary.setDiaryId(Integer.parseInt(diary_Id));
                diary.setTitle(title);
                diary.setContent(content);
                diary.setDateTime(dateTime);
                diary.setCategory(category);
                diary.setFavorite(Integer.parseInt(favorite));
            }
            cursor.close();
        }
        return diary;
    }

    /*
    * insert diary
    * */
    public long insertDiary(String title, String content, String dateTime , String category, int favorite) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TITLE, title);
        contentValue.put(DatabaseHelper.CONTENT, content);
        contentValue.put(DatabaseHelper.DATETIME, dateTime);
        contentValue.put(DatabaseHelper.CATEGORY, category);
        contentValue.put(DatabaseHelper.FAVORITE, favorite);
        return database.insert(DatabaseHelper.DIARY, null, contentValue);
    }

    /**
     * Delete diary
     */
    public void deleteAllDiary()
    {
        database.execSQL("delete from " + DatabaseHelper.DIARY);
    }

    /**
     * @return diary
     */
    public ArrayList<Diary> arrlistFetchDiary() {
        ArrayList<Diary> diaryArrayList = new ArrayList<Diary>();
        String[] diary_colomns = new String[]{DatabaseHelper._ID, DatabaseHelper.TITLE, DatabaseHelper.CONTENT, DatabaseHelper.DATETIME,DatabaseHelper.CATEGORY, DatabaseHelper.FAVORITE};
        Cursor cursor = database.query(DatabaseHelper.DIARY, diary_colomns, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String diary_Id = cursor.getString(cursor.getColumnIndex(DatabaseHelper._ID));
                Log.d("diary_Id", diary_Id);
                String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TITLE));
                Log.d("title", title);
                String content = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTENT));
                Log.d("content", content);
                String dateTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DATETIME));
                Log.d("dateTime", dateTime);
                String category = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CATEGORY));
                Log.d("category", category);
                String favorite = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FAVORITE));
                Log.d("favorite", favorite);
                Diary diary = new Diary();
                diary.setDiaryId(Integer.parseInt(diary_Id));
                diary.setTitle(title);
                diary.setContent(content);
                diary.setDateTime(dateTime);
                diary.setCategory(category);
                diary.setFavorite(Integer.parseInt(favorite));
                diaryArrayList.add(diary);
            }
            cursor.close();
        }
        return diaryArrayList;
    }

    public int update_diary(int diary_Id, String title, String content, String dateTime ,String category, String favorite) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TITLE, title);
        contentValue.put(DatabaseHelper.CONTENT, content);
        contentValue.put(DatabaseHelper.DATETIME, dateTime);
        contentValue.put(DatabaseHelper.CATEGORY, category);
        contentValue.put(DatabaseHelper.FAVORITE, favorite);
        String sql;
        sql = DatabaseHelper._ID + " = '" + diary_Id + "'";
        return database.update(DatabaseHelper.DIARY, contentValue, sql, null);
    }

   /* public Diary checkDiary(String content, String startTime) {
        database = dbHelper.getReadableDatabase();
        Diary diary = null;
        Cursor cursor = database.rawQuery("select * from " + DatabaseHelper.DIARY + " where " + DatabaseHelper.CONTENT + " = '" + content + "'" + " and " + DatabaseHelper.START_TIME + " =  '" + startTime + "'", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper._ID));
                Log.d("id", id);
                String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TITLE));
                Log.d("title", title);
                String contens = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTENT));
                Log.d("contens", contens);
                String startTimes = cursor.getString(cursor.getColumnIndex(DatabaseHelper.START_TIME));
                Log.d("startTimea", startTimes);
                String endTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.END_TIME));
                Log.d("endTime", endTime);
                String imagePath = cursor.getString(cursor.getColumnIndex(DatabaseHelper.IMAGE_PATH));
                Log.d("imagePath", imagePath);
                diary = new Diary();
                diary.setDiaryId(Integer.parseInt(id));
                diary.setTitle(title);
                diary.setStartTime(startTimes);
                diary.setEndTime(endTime);
                diary.setContent(contens);
                diary.setImagePath(imagePath);
            }
            cursor.close();
        }
        return diary;
    }*/

    public void deleteDiaryWh_Id(int diary_Id) {
        String sql;
        sql = "delete from " + DatabaseHelper.DIARY + " where " + DatabaseHelper._ID + " = " + "'" + diary_Id + "'";
        database.execSQL(sql);
    }

    public Diary checkDiary_Id(int diary_id) {
        database = dbHelper.getReadableDatabase();
        Diary diary = null;
        Cursor cursor = database.rawQuery("select * from " + DatabaseHelper.DIARY + " where " + DatabaseHelper._ID + " = '" + diary_id + "'", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String diary_Id = cursor.getString(cursor.getColumnIndex(DatabaseHelper._ID));
                Log.d("diary_Id", diary_Id);
                String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TITLE));
                Log.d("title", title);
                String content = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTENT));
                Log.d("content", content);
                String dateTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DATETIME));
                Log.d("dateTime", dateTime);
                String category = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CATEGORY));
                Log.d("category", category);
                String favorite = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FAVORITE));
                Log.d("favorite", favorite);
                diary = new Diary();
                diary.setDiaryId(Integer.parseInt(diary_Id));
                diary.setTitle(title);
                diary.setContent(content);
                diary.setDateTime(dateTime);
                diary.setCategory(category);
                diary.setFavorite(Integer.parseInt(favorite));
            }
            cursor.close();
        }
        return diary;
    }

    // get List Favorites
    public ArrayList<Diary> getDataDiaryFavorites() {
        ArrayList<Diary> diaryArrayList = new ArrayList<Diary>();
        database = dbHelper.getReadableDatabase();
        Diary diary = null;
        Cursor cursor = database.rawQuery("select * from " + DatabaseHelper.DIARY + " where " + DatabaseHelper.FAVORITE + " = '" + 1 + "'", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String diary_Id = cursor.getString(cursor.getColumnIndex(DatabaseHelper._ID));
                Log.d("diary_Id", diary_Id);
                String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TITLE));
                Log.d("title", title);
                String content = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTENT));
                Log.d("content", content);
                String dateTime = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DATETIME));
                Log.d("dateTime", dateTime);
                String category = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CATEGORY));
                Log.d("category", category);
                String favorite = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FAVORITE));
                Log.d("favorite", favorite);
                diary = new Diary();
                diary.setDiaryId(Integer.parseInt(diary_Id));
                diary.setTitle(title);
                diary.setContent(content);
                diary.setDateTime(dateTime);
                diary.setCategory(category);
                diary.setFavorite(Integer.parseInt(favorite));
                diaryArrayList.add(diary);
            }
            cursor.close();
        }
        return diaryArrayList;
    }

}
