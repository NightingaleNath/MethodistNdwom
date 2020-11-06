package ideanity.oceans.methodistndwom.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseAccess {

    private static DatabaseAccess instance;
    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = this.openHelper.getWritableDatabase();
    }

    public void close() {
        SQLiteDatabase sQLiteDatabase = this.database;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    public ArrayList<HashMap<String, String>> getHymns() {
        ArrayList<HashMap<String, String>> hymn = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("SELECT * FROM tblHymn", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("title", cursor.getString(1));
                map.put("hymn", cursor.getString(2));
                map.put("stanzas", cursor.getString(3));
                map.put("content", cursor.getString(4));
                map.put("author", cursor.getString(5));
                map.put("info", cursor.getString(6));
                hymn.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.database.close();
        return hymn;
    }

    public ArrayList<HashMap<String, String>> getNdwom() {
        ArrayList<HashMap<String, String>> ndwom = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("SELECT * FROM tblNdowm", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("title", cursor.getString(1));
                map.put("hymn", cursor.getString(2));
                map.put("stanzas", cursor.getString(3));
                map.put("content", cursor.getString(4));
                map.put("author", cursor.getString(5));
                map.put("info", cursor.getString(6));
                ndwom.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.database.close();
        return ndwom;
    }

    public ArrayList<HashMap<String, String>> searchHymns(String s) {
        ArrayList<HashMap<String, String>> hymn = new ArrayList<>();
        SQLiteDatabase sQLiteDatabase = this.database;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tblHymn WHERE title LIKE '%");
        sb.append(s);
        sb.append("%' ORDER BY id");
        Cursor cursor = sQLiteDatabase.rawQuery(sb.toString(), null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("title", cursor.getString(1));
                map.put("hymn", cursor.getString(2));
                map.put("stanzas", cursor.getString(3));
                map.put("content", cursor.getString(4));
                map.put("author", cursor.getString(5));
                map.put("info", cursor.getString(6));
                hymn.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.database.close();
        return hymn;
    }

    public ArrayList<HashMap<String, String>> searchNdwom(String s) {
        ArrayList<HashMap<String, String>> ndwom = new ArrayList<>();
        SQLiteDatabase sQLiteDatabase = this.database;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tblNdowm WHERE title LIKE '%");
        sb.append(s);
        sb.append("%' ORDER BY id");
        Cursor cursor = sQLiteDatabase.rawQuery(sb.toString(), null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("title", cursor.getString(1));
                map.put("hymn", cursor.getString(2));
                map.put("stanzas", cursor.getString(3));
                map.put("content", cursor.getString(4));
                map.put("author", cursor.getString(5));
                map.put("info", cursor.getString(6));
                ndwom.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.database.close();
        return ndwom;
    }

    public boolean addNote(String theme_name, String message_body, String date) {
        ContentValues values = new ContentValues();
        values.put("theme_name", theme_name);
        values.put("message_body", message_body);
        values.put("note_date", date);
        //values.put("note_time", time);
        long check = this.database.insert("note", null, values);
        this.database.close();
        if (check == -1) {
            return false;
        }
        return true;
    }

    public boolean updateNote(String note_id, String theme_name, String message_body, String date) {
        ContentValues values = new ContentValues();
        values.put("theme_name", theme_name);
        values.put("message_body", message_body);
        values.put("note_date", date);
        //values.put("note_time", time);
        long check = (long) this.database.update("note", values, "note_id=?", new String[]{note_id});
        this.database.close();
        return check != -1;
    }

    public ArrayList<HashMap<String, String>> getAllNote() {
        ArrayList<HashMap<String, String>> notes = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("SELECT * FROM note", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                String str = "note_id";
                map.put(str, cursor.getString(cursor.getColumnIndex(str)));
                String str2 = "theme_name";
                map.put(str2, cursor.getString(cursor.getColumnIndex(str2)));
                String str3 = "message_body";
                map.put(str3, cursor.getString(cursor.getColumnIndex(str3)));
                String str4 = "note_date";
                map.put(str4, cursor.getString(cursor.getColumnIndex(str4)));
//                String str5 = "note_time";
//                map.put(str5, cursor.getString(cursor.getColumnIndex(str5)));
                notes.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.database.close();
        return notes;
    }

    public boolean deleteNote(String note_id) {
        long check = (long) this.database.delete("note", "note_id=?", new String[]{note_id});
        this.database.close();
        return check == 1;
    }

    public ArrayList<HashMap<String, String>> getCanticles() {
        ArrayList<HashMap<String, String>> canticle = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("SELECT * FROM tblcanticle", null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("hymn_lyrics", cursor.getString(1));
                map.put("hymn_title", cursor.getString(2));
                canticle.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.database.close();
        return canticle;
    }

    public ArrayList<HashMap<String, String>> searchCanticles(String s) {
        ArrayList<HashMap<String, String>> canticle = new ArrayList<>();
        SQLiteDatabase sQLiteDatabase = this.database;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tblcanticle WHERE hymn_title LIKE '%");
        sb.append(s);
        sb.append("%' ORDER BY id");
        Cursor cursor = sQLiteDatabase.rawQuery(sb.toString(), null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("hymn_lyrics", cursor.getString(1));
                map.put("hymn_title", cursor.getString(2));
                canticle.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.database.close();
        return canticle;
    }

}
