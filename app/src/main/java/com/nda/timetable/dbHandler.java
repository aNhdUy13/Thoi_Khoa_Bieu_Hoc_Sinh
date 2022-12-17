package com.nda.timetable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class dbHandler extends SQLiteOpenHelper {
    public static String tableTimetable = "Timetable";
    public static String col1_timetable_id = "timetableId";
    public static String col2_timetable_day = "timetableDay";
    public static String col3_timetable_slot = "timetableSlot";
    public static String col4_timetable_time = "timetableTime";
    public static String col5_timetable_subject = "timetableSubject";

    public dbHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory,
                     int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void queryData(String sqlCommand)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sqlCommand);

        return;
    }

    public Cursor getData(String sqlCommand)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        return sqLiteDatabase.rawQuery(sqlCommand, null);
    }

    public void createDB()
    {
        queryData("CREATE TABLE IF NOT EXISTS " + tableTimetable
                + "("
                + col1_timetable_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + col2_timetable_day + " VARCHAR(200), "
                + col3_timetable_slot + " VARCHAR(200), "
                + col4_timetable_time + " VARCHAR(200), "
                + col5_timetable_subject + " VARCHAR(200)  )"  );
        // queryData("DROP TABLE IF EXISTS " + tableTimetable);
    }

    public void INSERT_TIMETABLE(String day, String slot, String time
            , String subject)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sqlCommand = ("INSERT INTO Timetable VALUES (null, ?, ?, ? , ?)");

        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sqlCommand);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1, day);
        sqLiteStatement.bindString(2, slot);
        sqLiteStatement.bindString(3, time);
        sqLiteStatement.bindString(4, subject);
        sqLiteStatement.executeInsert();
    }
}
